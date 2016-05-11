package com.bijan.similaritems.service.hashing;

import java.util.Random;

import com.bijan.common.collection.SparseBooleanArray;

/**
 * Creating the min hash signature from a {@link SparseBooleanArray}. Acccording to Leskovec,
 * Rajaraman & Ullman (2014), "Mining of Massive Datasets", Cambridge University
 * Press.
 * 
 *  
 */
public class MinHash {

	private int numOfHash;

	private int[] randomNumbersA;
	private int[] randomNumbersB;

	private int dictSize = Integer.MAX_VALUE;

	public MinHash() {
		this(100);
	}

	public MinHash(int numOfHash) {
		this.numOfHash = numOfHash;
		randomNumbersA = new int[numOfHash];
		randomNumbersB = new int[numOfHash];

		Random r = new Random();

		for (int i = 0; i < numOfHash; i++) {
			randomNumbersA[i] = r.nextInt(dictSize);
			randomNumbersB[i] = r.nextInt(dictSize);
		}
	}


	private int hash(int i, int x) {
		return (randomNumbersA[i]*x+randomNumbersB[i]) % dictSize;
	}

	public int[] minHash(SparseBooleanArray tokens) {
		int[] singature = new int[numOfHash];

		for (int i = 0; i < numOfHash; i++) {
			int min = Integer.MAX_VALUE;

			// calculating the minimal hash value for each row (token)
			for (int j = 0; j < tokens.size(); j++) {
				int hashVal = hash(i, tokens.keyAt(j));
				if (hashVal < min) {
					min = hashVal;
				}
			}
			singature[i] = min;
		}

		return singature;
	}

}
