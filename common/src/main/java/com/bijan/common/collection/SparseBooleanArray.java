package com.bijan.common.collection;

import java.io.Serializable;
import java.util.List;


/**
 * Puts key/value integer pairs in sparse arrays
 * 
 * The keys are ascending ordered. => key[0] < key[1]
 */
@SuppressWarnings("serial")
public class SparseBooleanArray implements Serializable {

	private int[] keys;
	private int size;

	/**
	 * Creates a new SparseIntArray containing no mappings.
	 */
	public SparseBooleanArray() {
		this(10);
	}

	/**
	 * Creates a sparse array with initial size.
	 * 
	 * @param initialCapacity
	 *            must be > 0
	 */
	public SparseBooleanArray(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Initial capacity may not be 0 or less, but was " + initialCapacity);
		}

		initialCapacity = ArrayUtils.idealArraySize(initialCapacity);
		keys = new int[initialCapacity];
		size = 0;
	}

	public SparseBooleanArray(List<Integer> sortedTokenList) {
		this(sortedTokenList.size());
		for(int i = 0; i < sortedTokenList.size(); i++){
			keys[i] = sortedTokenList.get(i);
		}
		size = sortedTokenList.size();
	}

	/**
	 * 
	 * @param key
	 *            index of the key in the array
	 * @return the corresponding value or <code>defaultValue</code> if it was
	 *         not found
	 */
	public int get(int key, int defaultValue) {

		int i = ArrayUtils.binarySearch(keys, size, key);

		if (i < 0) {
			return defaultValue;
		} else {
			return i;
		}
	}

	/**
	 * Removes the mapping from the specified key, if there was any.
	 */
	public void delete(int key) {
		int i = ArrayUtils.binarySearch(keys, size, key);

		if (i >= 0) {
			removeAt(i);
		}
	}

	/**
	 * Removes the mapping at the given index. Shifting the elements at one
	 * position higher the the index one to the left
	 */
	public void removeAt(int index) {
		System.arraycopy(keys, index + 1, keys, index, size - (index + 1));
		size--;
	}

	/**
	 * Adds a mapping from the specified key to the specified value, replacing
	 * the previous mapping from the specified key if there was one.
	 * 
	 * The keys are ascending ordered. => key[0] < key[1]
	 * 
	 */
	public boolean add(int key) {
		int i = ArrayUtils.binarySearch(keys, size, key);

		if (i >= 0) {
			return false; // already in array no need to add again
		} else {
			i = ~i;

			if (size >= keys.length) {
				int n = ArrayUtils.idealArraySize(size + 1);

				int[] nkeys = new int[n];

				System.arraycopy(keys, 0, nkeys, 0, keys.length);
				keys = nkeys;
			}

			if (size - i != 0) {
				System.arraycopy(keys, i, keys, i + 1, size - i);
			}

			keys[i] = key;
			size++;
			return true;
		}
		
	}

	/**
	 * The number of key/value mappings
	 */
	public int size() {
		return size;
	}

	/**
	 * The corresponding key at the index
	 * The keys are ascending ordered. => key[0] < key[1]
	 */
	public int keyAt(int index) {
		return keys[index];
	}

	/**
	 * Returns the index have the key
	 */
	public int indexOfKey(int key) {
		return ArrayUtils.binarySearch(keys, size, key);
	}


	/**
	 * Removes all key-value mappings from this SparseIntArray.
	 */
	public void clear() {
		size = 0;
	}

	/**
	 * Puts a key/value pair into the array, optimizing for the case where the
	 * key is greater than all existing keys in the array.
	 */
	public void append(int key) {
		if (size != 0 && key <= keys[size - 1]) {
			add(key);
			return;
		}

		int pos = size;
		if (pos >= keys.length) {
			int n = ArrayUtils.idealArraySize(pos + 1);

			int[] nkeys = new int[n];

			System.arraycopy(keys, 0, nkeys, 0, keys.length);

			keys = nkeys;
		}

		keys[pos] = key;
		size = pos + 1;
	}

	@Override
	public String toString() {
		if (size() <= 0) {
			return "{}";
		}

		StringBuilder buffer = new StringBuilder(size * 28);
		buffer.append('{');
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				buffer.append(", ");
			}
			int key = keyAt(i);
			buffer.append(key);
		}
		buffer.append('}');
		return buffer.toString();
	}

	

}