package com.bijan.similaritems.service.processing;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bijan.api.model.content.Content;
import com.bijan.common.collection.ContentProcessed;
import com.bijan.common.collection.SparseBooleanArray;
import com.bijan.similaritems.service.shingle.TextToShingleToken;

public class ShingleContents {

	private TextToShingleToken textToShingle;

	public ShingleContents(int shingleSize) {
		textToShingle = new TextToShingleToken(shingleSize);
	}

	public Stream<ContentProcessed> shingleContents(Stream<Content> contents) {
		return contents.map(content -> {

			SparseBooleanArray booleanArray = new SparseBooleanArray(
					textToShingle.shingle(content.getFulltext()).stream().sorted().collect(Collectors.toList()));

			return new ContentProcessed().setContent(content).setSparseTokenArray(booleanArray);
		});
	}
}
