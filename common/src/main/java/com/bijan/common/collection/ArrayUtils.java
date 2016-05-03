package com.bijan.common.collection;

/**
 * Some utility functions for arrays. A bit faster than java implementations,
 * but also without range checks, and so on....
 */
public class ArrayUtils {
	
	/**
     * The maximum size of array with a header word for some VMs.
     * 
     */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


	private ArrayUtils() { 
		// only helper
	}

	/**
	 * 
	 * Binary search. Dividing the array in half to find the index, which has
	 * the value.
	 * 
	 * Sometimes we know exactly, that the index we are look for is not above a
	 * certain index. Thus, we can set the highestIndex to the max position we
	 * think the value is. If you have no clue, set it to array.length
	 * 
	 * The array must be sorted.
	 * 
	 * @param array
	 *            the actual array. Must be sorted
	 * @param highestIndex
	 *            the highestIndex to look
	 * @param value
	 *            the value to look for
	 * @return the index containing the value
	 */
	public static int binarySearch(int[] array, int highestIndex, int value) {
		int lo = 0;
		int hi = highestIndex - 1;

		while (lo <= hi) {
			// at each iteration cut the pivot in half
			final int mid = (lo + hi) >>> 1;
			final int midVal = array[mid];

			if (midVal < value) {
				lo = mid + 1; // the value looking for is right of the pivot
			} else if (midVal > value) {
				hi = mid - 1; // nope it is left
			} else {
				return mid; // yap found it ;-)
			}
		}
		return ~lo; // value not in array
	}

	
	/**
	 * 
	 * according to https://github.com/facebook/folly/blob/master/folly/docs/FBVector.md
	 * 
	 * @param need
	 * @return 
	 */
	public static int idealArraySize(int need) {
		for (int i = 4; i < 32; i++) {
			if (need <= 1 << i){
				int k = (3 << (i-1)) ;
				return k > MAX_ARRAY_SIZE ? MAX_ARRAY_SIZE: k;
			}
		}
	
		return need;
		
	}
	public static int dot(SparseBooleanArray one, SparseBooleanArray other) {

		if (one.size() == 0 || other.size() == 0) {
			return 0;
		}

		int dot = 0;

		for (int i = 0; i < other.size(); i++) {
			int otherVal = other.keyAt(i);
			int oneVal = one.get(otherVal, 0);

			dot += oneVal * otherVal;
		}

		return dot;
	}
	
	public static int dot(SparseIntArray one, SparseIntArray other) {

		if (one.size() == 0 || other.size() == 0) {
			return 0;
		}

		int dot = 0;

		for (int i = 0; i < other.size(); i++) {
			int keyAt = other.keyAt(i);
			int otherVal = other.get(keyAt,0);
			int oneVal = one.get(keyAt, 0);

			dot += oneVal * otherVal;
		}

		return dot;
	}

	public static double euclideanSize(SparseIntArray one) {
		int dot = dot(one, one);
		return Math.sqrt(dot);
	}

}