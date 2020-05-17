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
@RequestMapping("/hello")
public class HelloWorldController
{
	@Value("${application.message}")
	private String message;
	
	@Autowired
	TestBusinessServiceInterface service;

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	/**
	 * Simple Hello World Controller that returns a String in the response body.
	 * Invoke using /test1 URI.
	 * 
	 * @return Hello World test string
	 */
	@RequestMapping(path="/test1", method=RequestMethod.GET)		// OR could use @GetMapping("/test1")
	@ResponseBody
	public String printHello() 
	{
		// Log the API call
		logger.info("==========> Entering HelloWorldController.printHello() at " + "/test1");
		
		// Simply return a String in the response body (must use @ResponseBody annontation)
		return message;
	}
	
	/**
	 * Simple Hello World Controller that returns a View Name along with a Model Attribute named message.
	 * Invoke using /test2 URI.
	 * @param model Model to bind to the View.
	 * 
	 * @return View name hello
	 */
	@RequestMapping(path="/test2", method=RequestMethod.GET)		// OR could use @GetMapping("/test2")
	public String printHello(ModelMap model) 
	{
		// Log the API call
		logger.info("==========> Entering HelloWorldController.printHello() at " + "/test2");

		// Simply return a Model with an attribute named message and return a View named hello using a passed in ModelMap
		model.addAttribute("message", "Hello World from my Play Spring Boot Application using the Spring MVC Framework!");
	    return "hello";
	}

	/**
	 * Simple Hello World Controller that returns a View Name along with a Model Attribute named message.
	 * Invoke using /test3 URI.
	 * 
	 * @return ModelAndView with both the Model and the View to render
	 */
	@RequestMapping(path="/test3", method=RequestMethod.GET)		// OR could use @GetMapping("/test3")
	public ModelAndView printHello1() 
	{
		// Log the API call
		logger.info("==========> Entering HelloWorldController.printHello1() at " + "/test3");
		
		// Create a ModelAndView and then set an attribute named message and with a View named hello
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", new String("Hello World from my Play Spring Boot Application using a Spring MVC ModelAndView!"));
		mv.addObject("message2", new String("I love the BSSD Program!"));
		mv.addObject("message3", new String("GCU Rocks!"));
		mv.setViewName("hello");
	    return mv;
	}

	/**
	 * Simple Hello World Controller that returns a View Name along with a Model Attribute named message.
	 * Invoke using /test4 URI.
	 * @param message HTTP Parameter named message to add to the Model for rendering.
	 * @param model Model to bind to the View.
	 * 
	 * @return View name hello
	 */
	@RequestMapping(path="/test4", method=RequestMethod.GET)		// OR could use @GetMapping("/test4")
	public String printHello(@RequestParam("message") String message, ModelMap model) 
	{
		// Log the API call
		logger.info("==========> Entering HelloWorldController.printHello() at " + "/test4");
		
		// Simply return a Model attribute named message and return a View named hello
		model.addAttribute("message", message);
	    return "hello";
	}
	
	/**
	 * Simple Hello World Controller that returns a String from an injected Spring Bean in the response body.
	 * Invoke using /test5 URI.
	 * 
	 * @return ModelAndView with both the Model and the View to render
	 */
	@RequestMapping(path="/test5", method=RequestMethod.GET)		// OR could use @GetMapping("/test5")
	public ModelAndView printHello2() 
	{
		// Log the API call
		logger.info("==========> Entering HelloWorldController.printHello2() at " + "/test5");
		
		// Create a ModelAndView and then set an attribute named message and with a View named hello
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", new String("Hello World from my Play Spring Boot Application using a Spring MVC ModelAndView!"));
		mv.addObject("message2", new String("I love the BSSD Program!"));
		mv.addObject("message3", service.getMessage() + " with an IoT Weater database row count of " + service.getCount());
		mv.setViewName("hello");
	    return mv;
	}
}
