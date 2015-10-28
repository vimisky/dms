package com.vimisky.crawler.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.vimisky.dms.entity.ContentWeb;

public class WebPageWriter {

	private Queue<ContentWeb> inputQueue;
	
	public void persist(){
		ContentWeb contentWeb = null;
		List<ContentWeb> contentWebs = new ArrayList<ContentWeb>();
		
		for (int i = 0; i < 300; i++) {
			contentWeb = inputQueue.remove();
			if (contentWeb != null) {
				contentWebs.add(contentWeb);
			}
		}
	}

}
