package com.vimisky.crawler;

import com.vimisky.dms.entity.ContentWeb;
import com.vimisky.dms.utils.HibernateInsert;

public class WebPageCrawler implements Runnable{

	private SitemapBasedCrawler sitemapBasedCrawler;
	private String url;
	private ContentWeb contentWeb;
	
	
	
	/**
	 * @return the sitemapBasedCrawler
	 */
	public SitemapBasedCrawler getSitemapBasedCrawler() {
		return sitemapBasedCrawler;
	}

	/**
	 * @param sitemapBasedCrawler the sitemapBasedCrawler to set
	 */
	public void setSitemapBasedCrawler(SitemapBasedCrawler sitemapBasedCrawler) {
		this.sitemapBasedCrawler = sitemapBasedCrawler;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
	/**
	 * @return the contentWeb
	 */
	public ContentWeb getContentWeb() {
		return contentWeb;
	}

	/**
	 * @param contentWeb the contentWeb to set
	 */
	public void setContentWeb(ContentWeb contentWeb) {
		this.contentWeb = contentWeb;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String pageUrl = getUrl();
		String pageContent = sitemapBasedCrawler.fetchPage(pageUrl);
		contentWeb.setContent(pageContent);
		HibernateInsert.insertContentWeb(contentWeb);
	}

}
