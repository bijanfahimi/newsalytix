package com.bijan.data.rssfeeds;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bijan.data.rssfeeds.config.RssFeedDataConfig;
import com.bijan.data.rssfeeds.model.RssFeed;
import com.bijan.data.rssfeeds.model.RssFeedEntry;
import com.bijan.data.rssfeeds.service.FeedReader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RssFeedDataConfig.class })
public class RssFeedRepoTest {

	private EmbeddedDatabase db;

	@Autowired
	private RssDataRepository dataRepo;

	@Autowired
	private FeedReader feedReader;

	@Before
	public void setup() {
		db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Test
	public void testConnection() {

		List<RssFeed> findAll = dataRepo.findAll();

		assertThat(dataRepo.count(), equalTo(Long.valueOf(findAll.size())));

		RssFeed findOne = dataRepo.findOne(findAll.get(0).getId());
		assertThat(findOne.getId(), equalTo(findAll.get(0).getId()));

		dataRepo.delete(findOne.getId());

		assertThat(dataRepo.count(), equalTo(Long.valueOf(findAll.size() - 1)));

		dataRepo.save(findOne);
		assertThat(dataRepo.count(), equalTo(Long.valueOf(findAll.size())));

	}

	@Test
	public void testRssFeeds() {
		List<RssFeed> findAll = dataRepo.findAll();

		List<List<RssFeedEntry>> readRssFeeds = feedReader.readRssFeeds(findAll);

		assertThat(readRssFeeds.size(), equalTo(findAll.size()));

		
		readRssFeeds.stream().forEach(mapper -> System.out.println(mapper.toString()));

	}

	@After
	public void after() {
		db.shutdown();
	}
}
