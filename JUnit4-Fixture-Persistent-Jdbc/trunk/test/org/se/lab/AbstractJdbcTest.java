package org.se.lab;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class AbstractJdbcTest 
{
	private SqlProcessor sqlProcessor = new SqlProcessor();
	
	
	/**
	 * Execute a SQL script file.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void executeSqlScript(String sqlFile) 
	{
		sqlProcessor.executeSqlScript(sqlFile);
	}
	
	protected Connection getConnection() throws ClassNotFoundException, SQLException
	{
	    return sqlProcessor.getConnection();
	}
}
