package com.vimisky.crawler;

import java.util.Timer;
import java.util.TimerTask;

import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.filter.PendingUrlUniqFilter;
import com.vimisky.crawler.persistence.BDBVisitedUrlStore;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;

public class ProcessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BDBVisitedUrlStore bdbVisitedUrlStore = BDBVisitedUrlStore.getInstance();
		
		int crawlcount = QueueManager.getInstance().getPendingCrawlUrlQueue().size();
		int filtercount = QueueManager.getInstance().getPendingFilterUrlQueue().size();
		int parsecount = QueueManager.getInstance().getPendingParseArticleQueue().size();
		int readycount = QueueManager.getInstance().getReadyArticleQueue().size();
		System.out.println("visited URL count:"+bdbVisitedUrlStore.count());
		System.out.println("pending crawl count:"+crawlcount);
		System.out.println("pending filter count:"+filtercount);
		System.out.println("pending parse count:"+parsecount);
		System.out.println("ready article count:"+readycount);

	}

}
