package com.vimisky.crawler;

import java.util.Queue;

import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.filter.PendingUrlUniqFilter;
import com.vimisky.crawler.pagespider.WebPageSpider;
import com.vimisky.crawler.parser.WebPageParser;
import com.vimisky.crawler.persistence.BDBVisitedUrlStore;
import com.vimisky.crawler.persistence.VisitedUrlStore;
import com.vimisky.crawler.queue.QueueManager;
import com.vimisky.dms.utils.VSThreadPool;

public class ReuterProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<CrawlURL> pendingCrawlURLs = QueueManager.getInstance().getPendingCrawlUrlQueue();
		Queue<CrawlURL> pendingFilterUrls = QueueManager.getInstance().getPendingFilterUrlQueue();
		Queue<CrawlArticle> pendingParseArticles = QueueManager.getInstance().getPendingParseArticleQueue();
		Queue<CrawlArticle> readyArticles = QueueManager.getInstance().getReadyArticleQueue();
		VisitedUrlStore visitedUrlStore = BDBVisitedUrlStore.getInstance();
		
		VSThreadPool filterPool = new VSThreadPool("filter", 200);
		VSThreadPool crawlPool = new VSThreadPool("crawl", 200);
		VSThreadPool parserPool = new VSThreadPool("parser", 200);
		
		int pendingCrawls = pendingCrawlURLs.size();
		while(--pendingCrawls>0){
			filterPool.execute(new PendingUrlUniqFilter(pendingCrawlURLs, visitedUrlStore, pendingFilterUrls));
		}
		int pendingFilters = pendingFilterUrls.size();
		
		while(--pendingFilters>0){
			crawlPool.execute(new WebPageSpider(pendingFilterUrls, visitedUrlStore, pendingParseArticles));
		}
		int pendingParsers = pendingParseArticles.size();

		while(--pendingParsers>0){
			parserPool.execute(new WebPageParser(pendingParseArticles, readyArticles));
		}
		filterPool.waitFinish();
		crawlPool.waitFinish();
		parserPool.waitFinish();
		filterPool.closePool();
		crawlPool.closePool();
		parserPool.closePool();
		
		int crawlcount = QueueManager.getInstance().getPendingCrawlUrlQueue().size();
		int filtercount = QueueManager.getInstance().getPendingFilterUrlQueue().size();
		int parsecount = QueueManager.getInstance().getPendingParseArticleQueue().size();
		int readycount = QueueManager.getInstance().getReadyArticleQueue().size();
		System.out.println("Ö´ÐÐÍê±Ï");
		System.out.println("visited URL count:"+visitedUrlStore.count());
		System.out.println("pending crawl count:"+crawlcount);
		System.out.println("pending filter count:"+filtercount);
		System.out.println("pending parse count:"+parsecount);
		System.out.println("ready article count:"+readycount);
	}

}
