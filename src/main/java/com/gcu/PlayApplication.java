package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "com.gcu" })
@SpringBootConfiguration
public class PlayApplication extends SpringBootServletInitializer
{
    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) 
    {
        return application.sources(PlayApplication.class);
    }

	public static void main(String[] args)
	{
		SpringApplication.run(PlayApplication.class, args);
	}
}

