package com.bijan.similaritems;

import static com.bijan.similaritems.model.text.Term.newTerm;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import com.bijan.similaritems.model.text.Term;
import com.bijan.similaritems.service.tokenizer.FulltextTokenizer;

public class TokenizerTest {

	@Test
	public void testTokenize() throws IOException {
		String text = "Noch bevor die AfD sich Ende April ihr erstes Grundsatzprogramm in Stuttgart gibt";

		FulltextTokenizer tokenizer = new FulltextTokenizer();

		Collection<Term> textify = tokenizer.textify(text);

		assertThat(textify,
				containsInAnyOrder(newTerm("noch"), newTerm("bevor"), newTerm("die"), newTerm("afd"), newTerm("sich"),
						newTerm("ende"), newTerm("april"), newTerm("ihr"), newTerm("erstes"),
						newTerm("grundsatzprogramm"), newTerm("in"), newTerm("stuttgart"), newTerm("gibt")));
	}
}