package com.gcu.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.TestBusinessServiceInterface;
import org.slf4j.Logger;

@Controller
public class HomeController
{
    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	/**
	 * Simple Home Controller that returns a String in the response body.
	 * Invoke using / URI.
	 * 
	 * @return Home test string
	 */
	@RequestMapping(path="/", method=RequestMethod.GET)		// OR could use @GetMapping("/")
	@ResponseBody
	public String printHello() 
	{
		// Log the API call
		logger.info("==========> Entering HomeController.printHello() at " + "/");
		
		// Simply return a String in the response body (must use @ResponseBody annontation)
		return "You have reached the Home. Try /hello/test2";
	}
}
