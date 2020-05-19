package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController
{
	@RequestMapping(path="/", method=RequestMethod.GET)		// OR could use @GetMapping("/")
	@ResponseBody
	public String sayHello() 
	{
		// Simply return a String in the response body (must use @ResponseBody annotation)
		return "Hello World from TestController.sayHello(). For something MORE interesting go to /hello/test2";
	}
}
