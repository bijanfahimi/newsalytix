package com.bijan.data.rssfeeds.service;

import com.bijan.data.rssfeeds.model.RssFeedEntry;
import com.rometools.rome.feed.synd.SyndEntry;

public interface RssConverter {
	RssFeedEntry newEntry(SyndEntry paramSyndEntry) ;

}