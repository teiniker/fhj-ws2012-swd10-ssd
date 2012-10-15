package org.se.lab;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTableTest 
	extends AbstractJdbcTest
{	
	
	@Before
	public void setUp()  
	{
		executeSqlScript("sql/createUserTable.sql");
		executeSqlScript("sql/insertUserTable.sql");
	}
	
	@After
	public void tearDown()
	{		        
        executeSqlScript("sql/dropUserTable.sql");
	}

	
	@Test
    public void testLogin() throws SQLException, ClassNotFoundException
    {
        final String username = "homer";
        final String password = "*******";
        
        Service service = new Service();
        boolean result = service.login(getConnection(), username, password);
        Assert.assertTrue(result);
    }

    @Test
    public void testSqlInjectionAttack() throws SQLException, ClassNotFoundException
    {
        final String username = "homer' OR 1 --'"; // SQL injection
        final String password = "wurscht";
        
        Service service = new Service();
        boolean result = service.login(getConnection(), username, password);
        Assert.assertTrue(result);
    }

    
    @Test
    public void testSecureLogin() throws SQLException, ClassNotFoundException
    {
        final String username = "homer";
        final String password = "*******";
        
        Service service = new Service();
        boolean result = service.secureLogin(getConnection(), username, password);
        Assert.assertTrue(result);
    }

    @Test
    public void testSecureLoginSqlInjectionAttack() throws SQLException, ClassNotFoundException
    {
        final String username = "homer' OR 1 --'"; // SQL injection
        final String password = "wurscht";
        
        Service service = new Service();
        boolean result = service.secureLogin(getConnection(), username, password);
        Assert.assertFalse(result);
    } 
}
