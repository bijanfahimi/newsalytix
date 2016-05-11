package com.bijan.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.bijan.api.model.content.Content;

/**
 * {@link Content}s may come in various types. This interface allows the
 * conversion of the different types into the basic data model
 *
 * @param <T>
 */
public interface ContentConverter<T> {

	Content convert(T entry);

	default List<Content> convert(List<T> entries) {
		return entries.stream().map(this::convert).collect(Collectors.toList());
	}

}