package com.vimisky.crawler;

import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;
import com.vimisky.crawler.urlcollection.SitemapBasedUrlCollector;


public class CrawlerBase {

	final private static Logger logger = Logger.getLogger(CrawlerBase.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sitemapUrlString = "http://www.reuters.com/sitemap_news_index.xml";
//		String sitemapUrlString = "http://www.usatoday.com/news_sitemap_news_nation-now.xml";
//		String sitemapUrlString = "http://www.usatoday.com/news_sitemap_news_nation.xml";

		Queue<CrawlURL> pendingCrawlURLs = QueueManager.getInstance().getPendingCrawlUrlQueue();
		SitemapBasedUrlCollector collector = new SitemapBasedUrlCollector(sitemapUrlString, pendingCrawlURLs);
		collector.crawl();
		BDBWorkQueue<BDBDataModelInterface> qQueue = (BDBWorkQueue)pendingCrawlURLs;
		QueueManager.getInstance().makePersistence(qQueue);
		int i = 0;
//		for (CrawlURL crawlURL : pendingCrawlURLs) {
//			logger.info("seq:"+(i++)+"url:"+crawlURL.getUrl().toString());
//		}
		BDBWorkQueue<CrawlURL> pendingCrawlURLQueue = (BDBWorkQueue<CrawlURL>)pendingCrawlURLs;
		List<CrawlURL> urls = pendingCrawlURLQueue.getAll();
		
		for (CrawlURL crawlURL : urls) {
			logger.info("seq:"+(i++)+"url:"+crawlURL.getUrl().toString());
		}
	}

}
