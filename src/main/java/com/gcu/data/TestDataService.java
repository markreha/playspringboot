package com.gcu.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TestDataService implements TestDataServiceInterface
{
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public int getCount()
	{
		int count = -1;
		String sql = "SELECT count(*) FROM weather";
		try 
		{
			// Execute SQL Query to get row count
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			srs.next();
			count = srs.getInt(1);		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return count;
	}

    // ***** Dependencies and Helper Functions *****
    
    /**
     * IoC helper function.
     * 
     * @param dataSource Spring Data Source to inject into this DAO
     */
	@Autowired
     public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
}
