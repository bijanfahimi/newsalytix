package com.bijan.data.rssfeeds.converter;

import org.springframework.stereotype.Service;

import com.bijan.api.converter.ContentConverter;
import com.bijan.api.model.content.Content;
import com.bijan.api.model.content.ValueType;
import com.bijan.common.utils.StringUtils;
import com.bijan.data.rssfeeds.model.RssFeedEntry;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 *  Converts {@link RssFeedEntry}s into a system wide data model {@link Content}.
 *
 */
@Service
public class RssToContentConvert implements ContentConverter<RssFeedEntry> {

	private static HashFunction hash = Hashing.sipHash24();
	
	
	@Override
	public Content convert(RssFeedEntry entry){
		
		long id = hash.newHasher().putString(entry.getUrl(), StringUtils.utf8).putString(entry.getTitle(), StringUtils.utf8).hash().asLong();
		
		return new Content(id).withFulltext(entry.getDescription()).addValue(entry.getTitle(), ValueType.TEXT);
		
	}
	
	
}
