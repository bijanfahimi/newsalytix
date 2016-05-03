package com.bijan.similaritems.model.text;

import static com.bijan.similaritems.model.text.Occurrence.occurs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Term {

	private List<Occurrence> offset;
	private String term;

	public static Term newTerm(String term){
		return new Term(term);
	}
	
	public Term(String term) {
		this.term = term;
		offset = new ArrayList<>();
	}
	
	public void addOccurrence(Integer startOffset, Integer endOffset) {
		offset.add(occurs(startOffset, endOffset));
	}

	public String getTerm() {
		return term;
	}

	@Override
	public int hashCode() {
		return TermHashUtils.termHashInt(term);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Term other = (Term) obj;
		return other.term.equals(this.term);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(term).append(":").append(StringUtils.join(offset, ','))
				.append("]").toString();
	}

}
