package com.csy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {
	
	@CrossOrigin
	@RequestMapping("/hello")
	@ResponseBody
	public String gameTime() {
		return "Hello";
	}
	
}
