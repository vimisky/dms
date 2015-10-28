package com.vimisky.crawler.datamodel;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class CrawlURLTupleBinding extends TupleBinding<CrawlURL>{

	@Override
	public CrawlURL entryToObject(TupleInput arg0) {
		// TODO Auto-generated method stub
		
		CrawlURL crawlURL = null;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = arg0.readString();
		try {
			crawlURL = mapper.readValue(jsonString, CrawlURL.class);
			return crawlURL;
			
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
	public void objectToEntry(CrawlURL arg0, TupleOutput arg1) {
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
