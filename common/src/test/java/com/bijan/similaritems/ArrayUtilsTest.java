package com.bijan.similaritems;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.bijan.common.collection.ArrayUtils;

public class ArrayUtilsTest {

	private int offset;
	private int size;
	private int[] array;
	private int[] arrayUnordereds;

	@Before
	public void setup() {
		offset = 5;
		size = 5;

		array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + offset;
		}
		
		arrayUnordereds = new int[size];
		for (int i = 0; i < arrayUnordereds.length; i++) {
			arrayUnordereds[i] = (size -i) + offset;
		}
	} 

	@Test
	public void testBinarySearch() {

		for (int i : array) {
			assertThat(ArrayUtils.binarySearch(array, array.length, i), equalTo(i - offset));
		}
	}

	@Test
	public void testBinarySearchReturnsNegativeIfNothingIsFound() {
		int notInArray = size + offset;
		assertThat(ArrayUtils.binarySearch(array, array.length, notInArray), is(lessThan(0)));
	}
	
	@Test
	public void testBinarySearchNeedsOrdering() {
		int inArray = arrayUnordereds[size-1];
		
		assertThat(ArrayUtils.binarySearch(arrayUnordereds, arrayUnordereds.length, inArray), is(lessThan(0)));
		
		Arrays.sort(arrayUnordereds);
		inArray = arrayUnordereds[size-1];
		assertThat(ArrayUtils.binarySearch(arrayUnordereds, arrayUnordereds.length, inArray), is(size-1));
		
	}
}

