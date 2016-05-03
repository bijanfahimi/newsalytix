package com.bijan.similaritems.service.tokenizer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import com.bijan.similaritems.model.text.Term;

public class FulltextTokenizer {

	private SimpleTextAnalyzer analyzer;

	
	
	public FulltextTokenizer() {
		this.analyzer = new SimpleTextAnalyzer();
	}



	public Collection<Term> textify(String text) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("fulltext", text);
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

		tokenStream.reset();

		Map<String, Term> terms = new HashMap<>();

		while (tokenStream.incrementToken()) {
			String term = charTermAttribute.toString();

			Term termObj = terms.get(term);
			if (termObj == null) {
				termObj = new Term(term);
				terms.put(term, termObj);
			}

			termObj.addOccurrence(offsetAttribute.startOffset(), offsetAttribute.endOffset());
		}

		return terms.values();
	}

}
