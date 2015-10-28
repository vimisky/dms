package com.vimisky.crawler.filter;

import java.sql.Time;
import java.util.Queue;

import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.persistence.BDBVisitedUrlStore;
import com.vimisky.crawler.persistence.VisitedUrlStore;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;

public class PendingUrlUniqFilter implements Runnable{

	private Queue<CrawlURL> inputQueue = null;
	private VisitedUrlStore visitedUrlStore = null;
	private Queue<CrawlURL> outputQueue = null;
	
	
	
	public PendingUrlUniqFilter(Queue<CrawlURL> inputQueue,
			VisitedUrlStore visitedUrlStore, Queue<CrawlURL> outputQueue) {
		super();
		this.inputQueue = inputQueue;
		this.visitedUrlStore = visitedUrlStore;
		this.outputQueue = outputQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		CrawlURL crawlURL = inputQueue.poll();
		if (crawlURL == null) {
			return;
		}
		boolean visited = visitedUrlStore.exists(crawlURL);
		if (!visited) {
			outputQueue.offer(crawlURL);
			BDBWorkQueue<BDBDataModelInterface> bOutputQueue = (BDBWorkQueue)outputQueue;
			bOutputQueue.getDatabase().sync();
		}
		
		BDBWorkQueue<BDBDataModelInterface> bInputQueue = (BDBWorkQueue)inputQueue;
		bInputQueue.getDatabase().sync();
		
	}

	public static void main(String[] arg0) {
		Queue<CrawlURL> inputQueue = QueueManager.getInstance().getPendingCrawlUrlQueue();
		Queue<CrawlURL> outputQueue = QueueManager.getInstance().getPendingFilterUrlQueue();
		VisitedUrlStore visitedUrlStore = BDBVisitedUrlStore.getInstance();
		PendingUrlUniqFilter pendingUrlUniqFilter = new PendingUrlUniqFilter(inputQueue, visitedUrlStore, outputQueue);
		
		while (true) {
			pendingUrlUniqFilter.run();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
