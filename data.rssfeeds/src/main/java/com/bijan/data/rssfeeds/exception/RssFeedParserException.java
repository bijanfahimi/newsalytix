package com.bijan.data.rssfeeds.exception;


@SuppressWarnings("serial")
public class RssFeedParserException extends RuntimeException {

	public RssFeedParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RssFeedParserException(String message) {
		super(message);
	}

	
}
