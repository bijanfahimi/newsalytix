package com.bijan.similaritems.service.tokenizer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import com.bijan.similaritems.model.text.Term;

/**
 * Lucence tokenizer for parsing the full text into {@link Term}s
 */
public class FulltextTokenizer {

	private SimpleTextAnalyzer analyzer;

	public FulltextTokenizer() {
		this.analyzer = new SimpleTextAnalyzer();
	}

	/**
	 * Using the {@link SimpleTextAnalyzer} to tokenize the text into
	 * {@link Term}s.
	 * 
	 * @param text
	 *            the full text
	 * @return Collection of {@link Term}s
	 * @throws IOException
	 */
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
