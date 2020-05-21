package com.gcu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller public class HomeController
{
	@Value("${application.title}")
	private String title;

	/**
	 * Simple Welcome World Controller that returns a View Name.
	 * Invoke using / URI.
	 * @param model Model to bind to the View.
	 * 
	 * @return View name welcome
	 */
	@GetMapping("/") 
	public String welcome(Model model) 
	{
		// Simply return a Model with an attribute named message and return a View named welcome using a passed in Mode
		model.addAttribute("title", title);
	    return "welcome";
	}
}
