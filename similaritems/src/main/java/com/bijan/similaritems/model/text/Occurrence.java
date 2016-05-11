package com.bijan.similaritems.model.text;

/**
 *  Representing an occurrence of a {@link Term}. 
 *  
 *  
 *
 */
public class Occurrence {

	private int startOffset;
	private int endOffset;

	public static Occurrence occurs(int startOffset, int endOffset){
		return new Occurrence(startOffset, endOffset);
	}
	
	public Occurrence(int startOffset, int endOffset) {
		super();
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endOffset;
		result = prime * result + startOffset;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Occurrence other = (Occurrence) obj;
		
		return other.endOffset == this.endOffset && other.startOffset == this.startOffset;
	}


	@Override
	public String toString() {
		return  new StringBuilder().append(startOffset).append(":").append(endOffset).toString();
	}
}
