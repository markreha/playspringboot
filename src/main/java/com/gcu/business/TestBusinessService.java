package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.TestDataServiceInterface;

public class TestBusinessService implements TestBusinessServiceInterface
{
	@Autowired
	TestDataServiceInterface service;

	@Override
	public String getMessage()
	{
		return "Hello, I am a Business SpringBean";
	}
	
	@Override
	public int getCount()
	{
		return service.getCount();
	}
}
