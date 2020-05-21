package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.TestBusinessServiceInterface;
import com.gcu.data.TestDataService;
import com.gcu.data.TestDataServiceInterface;


import com.gcu.business.TestBusinessService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig
{
	@Bean(name="testBusinessService")
	public TestBusinessServiceInterface getOrdersBusiness()
	{
		return new TestBusinessService();
	}
	
	@Bean(name="testDataService")
	public TestDataServiceInterface getCount()
	{
		return new TestDataService();
	}
}
