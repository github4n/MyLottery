package com.csy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csy.model.User;

@RestController
public class MQController {
	
	
	@RequestMapping(value = {"/"}, method=RequestMethod.GET)
    public String home(HttpServletRequest httpRequest){
    	return "index";
    }
	
	@PostMapping("/user")
	public String readJson(@RequestBody User user) {
		System.out.println("Post : "+ user);
		return "Read from JSON: " + user;
	}
	
//	GameType gameType;
//	
//	@PostMapping("/最新一期")
//	public String readJson(@RequestBody GameType gameType) {
//		System.out.println("{\"gameId\":\"9\"}");
//		
//		Object o =service.select("key_最新一期");
//		
//		return o;
//	}
	
	@PostMapping("/jsonuser")
	@ResponseBody
	public User readJsonUser(@RequestBody User user) {
		System.out.println("Post : "+ user);
		
		return user;
	}
	
	
}
