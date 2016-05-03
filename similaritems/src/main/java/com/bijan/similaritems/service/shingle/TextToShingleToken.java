package com.bijan.similaritems.service.shingle;

import java.util.HashSet;
import java.util.Set;

import com.bijan.common.utils.StringUtils;

/**
 * Creating n-gram from a text. This method follows the definition of Leskovec,
 * Rajaraman & Ullman (2014), "Mining of Massive Datasets", Cambridge University
 * Press
 * 
 * In general this class slides through the given string with a window of
 * {@link #kLength} and adds the sub strings into a set. Therefore, this method
 * does not return duplicate tokens.
 *
 */
public class TextToShingleToken {

	private int kLength;

	public TextToShingleToken() {
		this(8);
	}

	public TextToShingleToken(int kLength) {
		if (kLength <= 0) {
			throw new IllegalArgumentException("K must be larger then 0, but was " + kLength);
		}
		this.kLength = kLength;
	}

	public Set<Integer> shingle(String text) {
		String clearText = StringUtils.removeWhitespace(text);
		Set<Integer> shingles = new HashSet<>(text.length() / 3);

		String shingle;
		for (int i = 0; i < (clearText.length() - kLength + 1); i++) {
			shingle = text.substring(i, i + kLength);
			shingles.add(shingle.hashCode());
		}

		return shingles;
	}
}