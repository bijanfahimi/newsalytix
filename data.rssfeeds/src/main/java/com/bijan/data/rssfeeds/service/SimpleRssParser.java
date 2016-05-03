package com.bijan.data.rssfeeds.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.springframework.stereotype.Service;

import com.bijan.data.rssfeeds.exception.RssFeedParserException;
import com.bijan.data.rssfeeds.model.RssFeedEntry;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;

@Service
public class SimpleRssParser implements RssConverter {

	@Override
	public RssFeedEntry newEntry(SyndEntry entry) {
		RssFeedEntry rssentry = new RssFeedEntry();
		rssentry.setDescription(getDescription(entry));
		rssentry.setTitle(getTitle(entry));
		rssentry.setUrl(getUrl(entry));
		
		return rssentry;
	}

	private String filterAllTags(String text) {
		Document dirty = Jsoup.parseBodyFragment(text);

		dirty.outputSettings().escapeMode(EscapeMode.xhtml);
		return dirty.body().text();

	}
	
	private String getDescription(SyndEntry entry) {
		StringBuffer contentStr = new StringBuffer();
		List<SyndContent> contents = entry.getContents();
		for (SyndContent content : contents) {
			contentStr.append(content.getValue());
		}

		if (contentStr.length() < 10 && entry.getDescription() != null) {
			contentStr = new StringBuffer(entry.getDescription().getValue());
		}
		return filterAllTags(contentStr.toString());
	}

	private String getUrl(SyndEntry entry) {
		String url = entry.getUri();
		if(StringUtils.isBlank(url)){
			throw new RssFeedParserException("No uri in Feed: " + entry.toString());
		}
		
		return url;
	}
	
	private String getTitle(SyndEntry entry) {
		String title = entry.getTitle();
		if (title == null) {
			title = "";
		}
		return filterAllTags(title);
	}
}
