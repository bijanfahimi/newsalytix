package com.bijan.data.rssfeeds.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bijan.data.rssfeeds.exception.RssFeedReaderException;
import com.bijan.data.rssfeeds.model.RssFeed;
import com.bijan.data.rssfeeds.model.RssFeedEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class FeedReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedReader.class);

	private RssConverter rssConverter;

	@Autowired
	public FeedReader(RssConverter rssConverter) {
		this.rssConverter = rssConverter;
	}

	public List<List<RssFeedEntry>> readRssFeeds(Collection<RssFeed> feeds) {
		return feeds.stream().map(feed -> {

			try {
				return readRssFeed(feed);
			} catch (RssFeedReaderException e) {
				LOGGER.error(e.getMessage(), e);
			}

			return java.util.Collections.EMPTY_LIST;
		}).collect(Collectors.toList());
	}

	@Async
	public List<RssFeedEntry> readRssFeed(RssFeed feed) throws RssFeedReaderException {
		URL feedUrl;
		try {
			feedUrl = new URL(feed.getUrl());
		} catch (MalformedURLException e) {
			throw new RssFeedReaderException("Malformed URL " + feed.getUrl(), e);
		}

		SyndFeedInput input = new SyndFeedInput();
		SyndFeed syndFeed;
		try {
			syndFeed = input.build(new XmlReader(feedUrl));
		} catch (IllegalArgumentException | FeedException | IOException e) {
			throw new RssFeedReaderException(
					"Exception while processing rss feed: " + feed.getUrl() + " id: " + feed.getId(), e);
		}

		return syndFeed.getEntries().stream().map(entry -> {
			RssFeedEntry newEntry = rssConverter.newEntry(entry);
			newEntry.setSourceFeed(feed);
			return newEntry;
		}).filter(rssFeedEntry -> {
			return rssFeedEntry.getDescription().length() > 10;
		}).collect(Collectors.toList());

	}
}
