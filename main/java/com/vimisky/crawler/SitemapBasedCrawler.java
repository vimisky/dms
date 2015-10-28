package com.vimisky.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.vimisky.dms.entity.ContentWeb;
import com.vimisky.dms.utils.VSThreadPool;

public abstract class SitemapBasedCrawler{
	
	private String sitemapUrl;
	private List<ContentWeb> contentWebs = new ArrayList<ContentWeb>();
	
	/**
	 * @return the sitemapUrl
	 */
	public String getSitemapUrl() {
		return sitemapUrl;
	}

	/**
	 * @param sitemapUrl the sitemapUrl to set
	 */
	public void setSitemapUrl(String sitemapUrl) {
		this.sitemapUrl = sitemapUrl;
	}

	/**
	 * @return the contentWebs
	 */
	public List<ContentWeb> getContentWebs() {
		return contentWebs;
	}

	/**
	 * @param contentWebs the contentWebs to set
	 */
	public void setContentWebs(List<ContentWeb> contentWebs) {
		this.contentWebs = contentWebs;
	}

	public void fetchSitemap(URL url){
		
		SAXReader saxReader = new SAXReader();
		Document document = null;
		ContentWeb contentWeb = null;
		try {
			document = saxReader.read(url);
			Element root = document.getRootElement();
			
			for( Iterator<Element> iterator = root.elementIterator("url"); iterator.hasNext();){
				Element eurl = (Element)iterator.next();
				contentWeb = new ContentWeb();
				for(Iterator<Element> iterator2 = eurl.elementIterator(); iterator2.hasNext();){
					Element element = (Element) iterator2.next();					
					if (element.getName().equals("loc")) {
//						设置来源URL
						contentWeb.setSourceURL(element.getTextTrim());
					}else if (element.getName().equals("news")) {
						for(Iterator<Element> iterator3 = element.elementIterator();iterator3.hasNext();){
							Element meta = iterator3.next();
//							设置发布属性
							if (meta.getName().equals("publication")) {
								for(Iterator<Element> iterator4 = meta.elementIterator();iterator4.hasNext();){
									Element publicationMeta = iterator4.next();
									if (publicationMeta.getName().equals("name")) {
										contentWeb.setSourceName(publicationMeta.getTextTrim());
									}else if (publicationMeta.getName().equals("language")) {
										contentWeb.setcLanguage(publicationMeta.getTextTrim());
									}
								}
							}else if (meta.getName().equals("publication_date")) {
//								设置发布时间
//								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
								contentWeb.setPublishTime(DateFormat.getDateInstance().parse(meta.getTextTrim()));
								
							}else if (meta.getName().equals("title")) {
//								设置标题
								contentWeb.setTitle(meta.getTextTrim());
							}else if (meta.getName().equals("keywords")) {
								
							}else if (meta.getName().equals("stock_tikers")) {
//								忽略此项属性
							}
						}
					}else if (element.getName().equals("image")) {
						
					}//end if url.child.name
				}//end url.child
				contentWebs.add(contentWeb);
			}//end url iterator
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public abstract String fetchPage(String url) ;
	
	public void crawl(String url) {
		URL urlObject = null;
		try {
			urlObject = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fetchSitemap(urlObject);
		VSThreadPool pageCrawlerThreadPool = new VSThreadPool(url, 300);

		for (ContentWeb contentWeb : this.getContentWebs()) {
			
			WebPageCrawler webPageCrawler = new WebPageCrawler();
			webPageCrawler.setContentWeb(contentWeb);
			webPageCrawler.setUrl(contentWeb.getSourceURL());
			webPageCrawler.setSitemapBasedCrawler(this);
			
			pageCrawlerThreadPool.execute(webPageCrawler);
		}
		pageCrawlerThreadPool.waitFinish();
		pageCrawlerThreadPool.closePool();
		
	}

}
