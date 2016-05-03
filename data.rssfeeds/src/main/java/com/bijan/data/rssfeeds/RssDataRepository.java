package com.bijan.data.rssfeeds;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bijan.data.rssfeeds.model.RssFeed;

public interface RssDataRepository  extends JpaRepository<RssFeed, Long> {

}
