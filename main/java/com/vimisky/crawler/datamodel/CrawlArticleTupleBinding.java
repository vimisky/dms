package com.vimisky.crawler.datamodel;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class CrawlArticleTupleBinding extends TupleBinding<CrawlArticle>{

	@Override
	public CrawlArticle entryToObject(TupleInput arg0) {
		// TODO Auto-generated method stub
		CrawlArticle crawlArticle = null;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = arg0.readString();
		try {
			crawlArticle = mapper.readValue(jsonString, CrawlArticle.class);
			return crawlArticle;
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public void objectToEntry(CrawlArticle arg0, TupleOutput arg1) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = mapper.writeValueAsString(arg0);
			arg1.writeString(jsonString);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
