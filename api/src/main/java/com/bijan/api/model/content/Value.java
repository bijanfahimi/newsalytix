package com.bijan.api.model.content;

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
