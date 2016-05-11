package com.bijan.api.model.content;

/**
 *  Representing the additional information that is added to the content. 
 *  Like attributes or meta information like creation dates. 
 *
 * @param <T>
 */
public class Value<T> {

	private T value;
	private ValueType type;

	public Value(T value, ValueType type) {
		super();
		this.value = value;
		this.type = type;
	}

	public T getValue() {
		return value;
	}

	public ValueType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("[Value: ").append(type.name()).append(", ").append(String.valueOf(value)).append(']').toString();
	}

	
}
