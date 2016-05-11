package com.bijan.similaritems.service.processing;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bijan.api.model.content.Content;
import com.bijan.common.collection.ContentProcessed;
import com.bijan.common.collection.SparseBooleanArray;
import com.bijan.similaritems.service.shingle.TextToShingleToken;

/**
 * Processes a stream of contents by creating a {@link SparseBooleanArray} from the full text
 */
public class ShingleContents {

	private TextToShingleToken textToShingle;

	public ShingleContents(int shingleSize) {
		textToShingle = new TextToShingleToken(shingleSize);
	}

	/**
	 * 
	 * Shingles the contents and creates a sparse array from the content.
	 * 
	 * @param contents
	 * @return Stream of {@link ContentProcessed}
	 */
	public Stream<ContentProcessed> shingleContents(Stream<Content> contents) {
		return contents.map(content -> {

			SparseBooleanArray booleanArray = new SparseBooleanArray(
					textToShingle.shingle(content.getFulltext()).stream().sorted().collect(Collectors.toList()));

			return new ContentProcessed().setContent(content).setSparseTokenArray(booleanArray);
		});
	}
}
