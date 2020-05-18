package com.gcu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.gcu.business.TestBusinessServiceInterface;
import com.gcu.data.TestDataService;
import com.gcu.data.TestDataServiceInterface;
import com.gcu.business.TestBusinessService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig
{
	@Value("${datasource.url}")
    private String url;
	@Value("${datasource.username}")
    private String username;
	@Value("${datasource.password}")
    private String password;
	@Value("${datasource.driver}")
    private String driver;
	

	@Bean
	public UrlBasedViewResolver viewResolver()
	{
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() 
	{
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layouts/layouts.xml", "/WEB-INF/layouts/views.xml" });
		return tiles;

	}
	
/*	
	@Bean(name="dataSource")
	public DataSource getDataSource() 
	{
        return DataSourceBuilder
                .create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
*/
	
	@Bean(name="testBusinessService")
	public TestBusinessServiceInterface getOrdersBusiness()
	{
		return new TestBusinessService();
	}
	
/*	
	@Bean(name="testDataService")
	public TestDataServiceInterface getCount()
	{
		return new TestDataService();
	}
*/	
}
