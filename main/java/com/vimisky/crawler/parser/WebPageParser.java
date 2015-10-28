package com.vimisky.crawler.parser;

import java.util.Queue;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.pagespider.WebPageSpider;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;

public class WebPageParser implements Runnable {
	private static Logger logger = Logger.getLogger(WebPageParser.class);

	private Queue<CrawlArticle> inputQueue;
	private Queue<CrawlArticle> outputQueue;

	public WebPageParser(Queue<CrawlArticle> inputQueue,
			Queue<CrawlArticle> outputQueue) {
		super();
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		CrawlArticle crawlArticle = inputQueue.poll();
		if (crawlArticle == null) {
			return;
		}
		//Parse
		org.jsoup.nodes.Document document = Jsoup.parse(crawlArticle.getOriginWebPageContent());
		String content = "";
		org.jsoup.select.Elements articles = document.select("span#articleText p");
		for(org.jsoup.nodes.Element article : articles)
		if (article != null) {
			content += article.text();
		}
		if (!content.equals("")) {
			outputQueue.offer(crawlArticle);
			BDBWorkQueue<BDBDataModelInterface> bOutputQueue = (BDBWorkQueue)outputQueue;
			bOutputQueue.getDatabase().sync();
		}
		
		BDBWorkQueue<BDBDataModelInterface> bInputQueue = (BDBWorkQueue)inputQueue;
		bInputQueue.getDatabase().sync();

		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<CrawlArticle> inputQueue = QueueManager.getInstance().getPendingParseArticleQueue();
		Queue<CrawlArticle> outputQueue = QueueManager.getInstance().getReadyArticleQueue();
		WebPageParser webPageParser = new WebPageParser(inputQueue, outputQueue);
		int i = 0;
		while (true) {
			logger.info("µÚ"+(i++)+"´ÎÖ´ÐÐ");
			webPageParser.run();

		}
	}

}
