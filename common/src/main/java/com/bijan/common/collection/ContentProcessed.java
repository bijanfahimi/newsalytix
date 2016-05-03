package com.bijan.common.collection;

import java.util.Arrays;
import java.util.List;

import com.bijan.api.model.content.Content;

public class ContentProcessed {

	private Content content;
	private SparseBooleanArray sparseTokenArray;
	private int[] minHash;
	private List<Integer> lsh;

	public List<Integer> getLsh() {
		return lsh;
	}

	public ContentProcessed setLsh(List<Integer> lsh) {
		this.lsh = lsh;
		return this;
	}

	public int[] getMinHash() {
		return minHash;
	}

	public ContentProcessed setMinHash(int[] minHash) {
		this.minHash = minHash;
		return this;
	}

	public Content getContent() {
		return content;
	}

	public ContentProcessed setContent(Content content) {
		this.content = content;
		return this;
	}

	public SparseBooleanArray getSparseTokenArray() {
		return sparseTokenArray;
	}

	public ContentProcessed setSparseTokenArray(SparseBooleanArray sparseTokenArray) {
		this.sparseTokenArray = sparseTokenArray;
		return this;
	}

	@Override
	public String toString() {
		return "ContentProcessed [content=" + content + ", sparseTokenArray=" + sparseTokenArray + ", minHash="
				+ Arrays.toString(minHash) + ", lsh=" + lsh + "]";
	}

}
