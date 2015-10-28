package com.vimisky.functional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;


public class NewsFetcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

	public void exeSelfFetcher() {
		HttpClient client = new HttpClient();
		HttpMethod getMethod = new GetMethod("http://edition.cnn.com/?refresh=1");
		int status = HttpStatus.SC_OK;
		String response = "";
		try {
			status = client.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String regex = "<article.*?>(.*?)</article>";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(response);
//		System.out.println("response:"+response);
//		while(matcher.find()){
		if (matcher.find()) {
			System.out.println("xx:"+matcher.group(1));

		}
//		}
		String article = matcher.group(1);
		
		regex = "<.*?itemprop\\s*=\\s*\"?name\"?.*?>(.*?)</.*?>";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(article);
		
		if (matcher.find()) {
			System.out.println("name:"+matcher.group(1));
		}
		regex = "<a\\s*href\\s*=\\s*\"*([^>|\"]*)\"*.*?itemprop\\s*=\\s*\"*url\"*[^>]*?>";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(article);
		
		if (matcher.find()) {
			System.out.println("url:"+matcher.group(1));
		}
		regex = "<.*?itemprop\\s*=\\s*\"?description\"?.*?>(.*?)</.*?>";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(article);
		
		if (matcher.find()) {
			System.out.println("description:"+matcher.group(1));
		}
	}
	
	
}
