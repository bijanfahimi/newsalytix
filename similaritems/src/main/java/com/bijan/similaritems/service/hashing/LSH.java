package com.bijan.similaritems.service.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hashes the signature into {@link #numBands} buckets. Each bucket summerizes
 * {@link #numRows} hash functions
 * 
 */
public class LSH {

	private int numBands;
	private int numRows;

	public LSH(int numBand, int numRows) {
		this.numBands = numBand;
		this.numRows = numRows;
	}

	public List<Integer> lsh(int[] hashes) {
		List<Integer> buckets = new ArrayList<>(numBands);
		int bandArr[] = new int[numRows];
		for (int band = 0; band < numBands; band++) {

			for (int rows = 0; rows < numRows; rows++) {
				bandArr[rows] = hashes[band * numRows + rows];
			}
			buckets.add(Arrays.hashCode(bandArr));

		}

		return buckets;
	}
}
