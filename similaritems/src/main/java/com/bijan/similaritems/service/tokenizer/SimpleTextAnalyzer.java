package com.bijan.similaritems.service.tokenizer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
/**
 * Lucene analyzer pipeline definition
 */
public class SimpleTextAnalyzer extends Analyzer {

	
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		StandardTokenizer tokenizer = new StandardTokenizer();
		StandardFilter filter = new StandardFilter(tokenizer);
		LowerCaseFilter lowerCaseFilter = new LowerCaseFilter(filter);
		
		return new TokenStreamComponents(tokenizer, lowerCaseFilter);

	}
	
}
