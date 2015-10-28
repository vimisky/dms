package com.vimisky.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ctr")
public class NewsController {

	@RequestMapping("/hello")
	String helloWorld(){
		
		return "helloWorld";
	}
}
