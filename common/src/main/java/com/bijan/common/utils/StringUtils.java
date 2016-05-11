package com.bijan.common.utils;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public final class StringUtils {
	
	private StringUtils() {
		
	}
	
	public static Charset utf8 = Charset.forName("utf-8");
	
	public static Pattern spaceRegEx = Pattern.compile("\\s+");
	
	
	/**
	 * 
	 * Replaces more than one white spaces with one whitespace
	 * 
	 * @param text 
	 * @return the text without multiple white spaces
	 */
	public static String removeWhitespace(String text) {
		return spaceRegEx.matcher(text).replaceAll(" ");
	}
}
