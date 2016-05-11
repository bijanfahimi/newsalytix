package com.bijan.client.similaritems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bijan.client.similaritems.config.SimilarItemsConfig;
import com.bijan.data.rssfeeds.config.RssFeedDataConfig;


/**
 * Application Runner for finding similar items in rss feeds 
 *
 */
@SpringBootApplication
public class FindSimilarItems {

		
	// Start the App by running the ApplicationRunner and initializing Spring
	public static void main(String[] args) {

		Object[] configs = { SimilarItemsConfig.class, RssFeedDataConfig.class };

		ApplicationContext ctx = SpringApplication.run(configs, args);
		ApplicationRunner appRunner = ctx.getBean(ApplicationRunner.class);
		appRunner.run();
	}
}
