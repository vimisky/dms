package com.vimisky.crawler.persistence;

import java.util.List;

import com.vimisky.crawler.datamodel.CrawlURL;

public interface VisitedUrlStore {

	public boolean exists(CrawlURL crawlURL);
	public void add(CrawlURL crawlURL);
	public void delete(String key);
	public void update(String key, CrawlURL crawlURL);
	public CrawlURL find(String key);
	public void deleteAll();
	public List<CrawlURL> findAll();
	public void flush();
	public long count();
}
