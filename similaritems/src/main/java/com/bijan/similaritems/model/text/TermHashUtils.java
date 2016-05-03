package com.bijan.similaritems.model.text;

import java.nio.charset.Charset;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class TermHashUtils {

	private static HashFunction termHash = Hashing.sipHash24();
	
	private static Charset utf8 = Charset.forName("utf-8");
	
	public static int termHashInt(String term){
		return termHash.hashString(term, utf8).asInt();
	}
	public static long termHashLong(String term){
		return termHash.hashString(term, utf8).asLong();
	}
}
