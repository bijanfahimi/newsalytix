package com.bijan.client.similaritems.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bijan.api.model.content.Content;
import com.bijan.data.rssfeeds.converter.RssToContentConvert;
import com.bijan.data.rssfeeds.model.RssFeed;
import com.bijan.data.rssfeeds.service.FeedReader;

@Service
public class RssFeedsToContentCenverter {

	private FeedReader feedReader;

	private RssToContentConvert converter;

	@Autowired
	public RssFeedsToContentCenverter(FeedReader feedReader, RssToContentConvert converter) {
		super();
		this.feedReader = feedReader;
		this.converter = converter;
	}

	public Stream<Content> convertToStream(Collection<RssFeed> feeds){
		return feedReader.readRssFeeds(feeds).stream().map(converter::convert).flatMap(l -> {
			return l.stream();
		});
	}
	
	public List<Content> convert(Collection<RssFeed> feeds){
		return convertToStream(feeds).collect(Collectors.toList());
	}
}
