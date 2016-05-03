package com.bijan.client.similaritems;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bijan.api.model.content.Content;
import com.bijan.client.similaritems.services.RssFeedsToContentCenverter;
import com.bijan.common.collection.ContentProcessed;
import com.bijan.data.rssfeeds.RssDataService;
import com.bijan.data.rssfeeds.service.FeedReader;
import com.bijan.similaritems.service.hashing.LSH;
import com.bijan.similaritems.service.hashing.MinHash;
import com.bijan.similaritems.service.processing.ShingleContents;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

@Service
public class ApplicationRunner implements Runnable {

	private static final int NUMBER_OF_SHINGLES = 4;
	private static final int NUMBER_OF_BANDS = 30;
	private static final int NUMBER_OF_ROWS = 5;
	private static final int NUMBER_OF_HASHES = NUMBER_OF_BANDS * NUMBER_OF_ROWS;

	private RssDataService rssData;

	private RssFeedsToContentCenverter converter;
	
	private ShingleContents shingleContents;

	private MinHash minHash = new MinHash(NUMBER_OF_HASHES);

	private LSH lsh = new LSH(NUMBER_OF_BANDS, NUMBER_OF_ROWS);

	
	@Autowired
	public ApplicationRunner(RssDataService rssData, FeedReader feedReader, RssFeedsToContentCenverter converter) {
		this.rssData = rssData;
		this.converter = converter;
		
		shingleContents = new ShingleContents(NUMBER_OF_SHINGLES);
	}

	@Override
	public void run() {

		SetMultimap<Integer, ContentProcessed> buckets = HashMultimap.create();

		
		Stream<Content> contents = converter.convertToStream(rssData.findAll());
		
		Stream<ContentProcessed> contentsProcessed = shingleContents.shingleContents(contents);
		
				
		contentsProcessed.map(sparseHash -> {
			int[] hash = minHash.minHash(sparseHash.getSparseTokenArray());
			return sparseHash.setMinHash(hash);
		}).map(minHash -> {
			List<Integer> lshHash = lsh.lsh(minHash.getMinHash());
			return minHash.setLsh(lshHash);
		}).forEach(processContent -> {
			List<Integer> lshBucket = processContent.getLsh();
			for (Integer candidateBucket : lshBucket) {
				buckets.put(candidateBucket, processContent);
			}
		});

		Set<Integer> keySet = buckets.keySet();
		for (Integer candidateBucket : keySet) {
			Set<ContentProcessed> set = buckets.get(candidateBucket);

			if (set.size() > 1) {
				System.out.println("Bucket: " + candidateBucket);
				for (ContentProcessed contentProcessed : set) {
					System.out.println(contentProcessed.getContent().getFulltext());
					System.out.println("-----------------");
				}
			}
		}
	}

}
