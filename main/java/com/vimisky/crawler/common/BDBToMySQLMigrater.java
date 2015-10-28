package com.vimisky.crawler.common;

import java.util.Queue;

import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.queue.BDBCrawlArticleQueue;
import com.vimisky.crawler.queue.BDBWorkQueue;
import com.vimisky.crawler.queue.QueueManager;
import com.vimisky.dms.entity.ContentWeb;
import com.vimisky.dms.utils.HibernateInsert;

public class BDBToMySQLMigrater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<CrawlArticle> crawlArticleQueue =  QueueManager.getInstance().getReadyArticleQueue();
		CrawlArticle crawArticle = null;
		while (null != (crawArticle = crawlArticleQueue.poll())) {
			ContentWeb contentWeb = new ContentWeb();
			contentWeb.setTitle(crawArticle.getTitle());
			contentWeb.setAuthor(crawArticle.getAuthors());
			contentWeb.setPublishTime(crawArticle.getPubtime());
			contentWeb.setLandTime(crawArticle.getCrawltime());
			contentWeb.setSourceName(crawArticle.getSourceSiteName());
			contentWeb.setSourceURL(crawArticle.getSourceArticleUrl());
			contentWeb.setContent(crawArticle.getContent());
			contentWeb.setOriginContent(crawArticle.getOriginWebPageContent());
			
			HibernateInsert.insertContentWeb(contentWeb);
		}
	}

}
