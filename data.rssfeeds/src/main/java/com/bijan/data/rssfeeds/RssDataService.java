package com.bijan.data.rssfeeds;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bijan.data.rssfeeds.model.RssFeed;

@Service
public class RssDataService {

	@Resource
	private RssDataRepository rssDataRepo;
	
	@Transactional
	public RssFeed create(RssFeed feed){
		return rssDataRepo.save(feed);
	}
	
	
	public List<RssFeed> findAll(){
		return rssDataRepo.findAll();
	}
	
	public RssFeed findById(Long id){
		return rssDataRepo.findOne(id);
	}
}
