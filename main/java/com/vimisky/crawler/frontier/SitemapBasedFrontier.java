package com.vimisky.crawler.frontier;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.vimisky.crawler.datamodel.CrawlURL;

public class SitemapBasedFrontier {
	
	private static Logger logger = Logger.getLogger(SitemapBasedFrontier.class);
	private URL sitemapUrl = null;
	private Queue<CrawlURL> outputQueue = null;
	/**
	 * @return the sitemapUrl
	 */
	public URL getSitemapUrl() {
		return sitemapUrl;
	}

	/**
	 * @param sitemapUrl the sitemapUrl to set
	 */
	public void setSitemapUrl(URL sitemapUrl) {
		this.sitemapUrl = sitemapUrl;
	}


	public SitemapBasedFrontier(){
		super();
	}
	
	public SitemapBasedFrontier(String sitemapUrl, Queue<CrawlURL> outputQueue){
		super();
		try {
			this.sitemapUrl = new URL(sitemapUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.outputQueue = outputQueue;
	}
	
	private void fetchSitemap(URL url,Queue<CrawlURL> queue){
		
		SAXReader saxReader = new SAXReader();
		Document document = null;
		CrawlURL crawlURL = null;
		try {
			document = saxReader.read(url);
			Element root = document.getRootElement();
			
			for( Iterator<Element> iterator = root.elementIterator("url"); iterator.hasNext();){
				Element eurl = (Element)iterator.next();
				crawlURL = new CrawlURL();
				Map<String, Object> articleMap = new HashMap<String, Object>();
				for(Iterator<Element> iterator2 = eurl.elementIterator(); iterator2.hasNext();){
					Element element = (Element) iterator2.next();					
					if (element.getName().equals("loc")) {
//						设置来源URL
						crawlURL.setUrl(new URL(element.getTextTrim()));
					}else if (element.getName().equals("news")) {
						for(Iterator<Element> iterator3 = element.elementIterator();iterator3.hasNext();){
							Element meta = iterator3.next();
//							设置发布属性
							if (meta.getName().equals("publication")) {
								for(Iterator<Element> iterator4 = meta.elementIterator();iterator4.hasNext();){
									Element publicationMeta = iterator4.next();
									if (publicationMeta.getName().equals("name")) {
										articleMap.put("news:publication:name", publicationMeta.getTextTrim());
									}else if (publicationMeta.getName().equals("language")) {
										articleMap.put("news:publication:language", publicationMeta.getTextTrim());
									}
								}
							}else if (meta.getName().equals("publication_date")) {
//								设置发布时间
//								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
								articleMap.put("news:publication_date", DateFormat.getDateInstance().parse(meta.getTextTrim()));
								
							}else if (meta.getName().equals("title")) {
//								设置标题
								articleMap.put("news:title", meta.getTextTrim());
							}else if (meta.getName().equals("keywords")) {
								articleMap.put("news:keywords", meta.getTextTrim());
							}else if (meta.getName().equals("stock_tikers")) {
//								忽略此项属性
							}
						}
					}else if (element.getName().equals("image")) {
						
					}//end if url.child.name
				}//end url.child
				outputQueue.add(crawlURL);
			}//end url iterator
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public void crawl() {
		if (this.sitemapUrl == null || this.outputQueue == null) {
			logger.error("No sitemapUrl or outputQueue");
			return;
		}
		this.fetchSitemap(this.sitemapUrl, this.outputQueue);
	}
	public void printCollection() {
		
	}
}
