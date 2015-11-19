package com.feng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/helloworld")
	public String helloworld() {
		return "test";
	}


}
