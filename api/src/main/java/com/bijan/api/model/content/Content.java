package com.bijan.api.model.content;

import java.util.ArrayList;
import java.util.List;

public class Content {

	private long id;
	private List<Value<?>> values;

	private String fulltext;

	public Content(long id) {
		values = new ArrayList<>();
		this.id = id;
	}

	
	public Content withId(long id){
		this.id = id;
		return this;
	}
	
	public Content withFulltext(String fulltext){
		this.fulltext = fulltext;
		return this;
	}
	
	public <T> Content addValue(T value, ValueType type) {
		this.values.add(new Value<T>(value, type));
		return this;
	}

	public long getId() {
		return id;
	}

	public List<Value<?>> getValues() {
		return values;
	}

	public String getFulltext() {
		return fulltext;
	}

	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", values=" + values + ", fulltext=" + fulltext + "]";
	}
}
