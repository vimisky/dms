package com.vimisky.crawler.pagespider;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

import com.vimisky.crawler.common.BDBhelper;
import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.filter.PendingUrlUniqFilter;
import com.vimisky.crawler.persistence.BDBVisitedUrlStore;
import com.vimisky.crawler.persistence.VisitedUrlStore;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;
import com.vimisky.dms.entity.ContentWeb;

public class WebPageSpider implements Runnable{

	private static Logger logger = Logger.getLogger(WebPageSpider.class);

	private Queue<CrawlURL> inputQueue;
	private VisitedUrlStore visitedUrlStore = null;
	private Queue<CrawlArticle> outputQueue;
	
	public WebPageSpider(Queue<CrawlURL> inputQueue, VisitedUrlStore visitedUrlStore, Queue<CrawlArticle> outputQueue) {
		super();
		this.inputQueue = inputQueue;
		this.visitedUrlStore = visitedUrlStore;
		this.outputQueue = outputQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		HttpMethod getMethod ;
		int httpStatus = HttpStatus.SC_NOT_FOUND;
	
		CrawlURL crawlURL = inputQueue.poll();
		if (crawlURL == null) {
			return;
		}
		if (crawlURL!=null&&crawlURL.getUrl()!=null&&crawlURL.getUrl().toString()!=null && ! crawlURL.getUrl().toString().equals("")) {
			getMethod = new GetMethod(crawlURL.getUrl().toString());
			try {
				httpStatus = httpClient.executeMethod(getMethod);
				if (httpStatus == HttpStatus.SC_OK) {
					crawlURL.setFetchStatus(1);
					visitedUrlStore.add(crawlURL);
					
					CrawlArticle crawlArticle = new CrawlArticle();
					crawlArticle.setSourceArticleUrl(crawlURL.toString());
					
					crawlArticle.setOriginWebPageContent(getMethod.getResponseBodyAsString());
					Map<String, Object> articleMap = crawlURL.getData();
					Set<String> keys = articleMap.keySet();
					for (String key : keys) {
						if (key.equals("news:publication:name")) {
							crawlArticle.setSourceSiteName((String)articleMap.get(key));
						}else if(key.equals("news:publication:language")) {
							
						}else if(key.equals("news:title")) {
							crawlArticle.setTitle((String)articleMap.get(key));
						}else if (key.equals("news:keywords")) {
							crawlArticle.setTags((String)articleMap.get(key));
						}else if (key.equals("news:publication_date")) {
							crawlArticle.setPubtime(new Date((Long)articleMap.get(key)));
							crawlArticle.setCrawltime(new Date());
						}
					}
					outputQueue.offer(crawlArticle);
					logger.info("crawl article:"+crawlArticle.getTitle());
				}else {
					crawlURL.setFetchAttempts(crawlURL.getFetchAttempts()+1);
					inputQueue.offer(crawlURL);
				}
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//if end
		BDBWorkQueue<BDBDataModelInterface> bInputQueue = (BDBWorkQueue)inputQueue;
		bInputQueue.getDatabase().sync();
		visitedUrlStore.flush();
		BDBWorkQueue<BDBDataModelInterface> bOutputQueue = (BDBWorkQueue)outputQueue;
		bOutputQueue.getDatabase().sync();
	}//run end

	public static void main(String[] arg0) {
		Queue<CrawlURL> inputQueue = QueueManager.getInstance().getPendingFilterUrlQueue();
		Queue<CrawlArticle> outputQueue = QueueManager.getInstance().getPendingParseArticleQueue();
		VisitedUrlStore visitedUrlStore = BDBVisitedUrlStore.getInstance();
		WebPageSpider webPageSpider = new WebPageSpider(inputQueue, visitedUrlStore, outputQueue);
		
		while (true) {
			webPageSpider.run();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
