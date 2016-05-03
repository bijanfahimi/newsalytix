package com.bijan.client.similaritems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bijan.client.similaritems.config.SimilarItemsConfig;
import com.bijan.data.rssfeeds.config.RssFeedDataConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SimilarItemsConfig.class, RssFeedDataConfig.class  })
public class SimilarItemsTest {

	@Autowired
	private ApplicationRunner runner;
	
	@Test
	public void testApplication(){
		runner.run();
	}
	
}
