package com.vimisky.crawler;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.vimisky.dms.utils.HibernateInsert;

public class ReuterCrawler extends SitemapBasedCrawler {

	private static Logger logger = Logger.getLogger(ReuterCrawler.class);

	@Override
	public String fetchPage(String url) {
		// TODO Auto-generated method stub
		org.jsoup.nodes.Document document = null;
		String content = "";
		try {
			document = Jsoup.connect(url).timeout(10000).get();
			org.jsoup.select.Elements articles = document.select("span#articleText p");
			for(org.jsoup.nodes.Element article : articles)
			if (article != null) {
				content += article.text();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateInsert.initFactory() ;
		ReuterCrawler reuterCrawler = new ReuterCrawler();
		reuterCrawler.crawl("http://www.reuters.com/sitemap_news_index.xml");
	}



	
}
