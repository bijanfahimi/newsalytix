package com.bijan.common.utils;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public final class StringUtils {
	
	private StringUtils() {
		
	}
	
	public static Charset utf8 = Charset.forName("utf-8");
	
	public static Pattern spaceRegEx = Pattern.compile("\\s+");
	
	public static double handleDouble(String x) {
		Double y;
		if (Pattern.matches("N/A", x)) {  
			y = 0.00;   
		} else { 
			y = Double.parseDouble(x);  
		}  
		return y;
	}
	
	public static int handleInt(String x) {
		int y;
		if (Pattern.matches("N/A", x)) {  
			y = 0;   
		} else { 
			y = Integer.parseInt(x);  
		} 
		return y;
	}

	
	/**
	 * 
	 * Replaces more than one whitespaces with one whitespace
	 * 
	 * @param text
	 * @return
	 */
	public static String removeWhitespace(String text) {
		return spaceRegEx.matcher(text).replaceAll(" ");
	}
}
