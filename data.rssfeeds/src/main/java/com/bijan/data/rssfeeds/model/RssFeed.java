package com.bijan.data.rssfeeds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representing a rss feed
 */
@Entity
@Table(name = "rssfeeds")
public class RssFeed {

	@Id
	@GeneratedValue
	private long id;

	private String url;

	private String title;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RssFeed [id=" + id + ", url=" + url + ", title=" + title + "]";
	}
	
	
}
