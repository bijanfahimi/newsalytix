package com.bijan.data.rssfeeds.model;

public class RssFeedEntry {

	private RssFeed sourceFeed;
	private String title;
	private String description;
	private String url;

	public RssFeed getSourceFeed() {
		return sourceFeed;
	}

	public void setSourceFeed(RssFeed sourceFeed) {
		this.sourceFeed = sourceFeed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "RssFeedEntry [sourceFeed=" + sourceFeed + ", title=" + title + ", description=" + description + ", url="
				+ url + "]";
	}

}
