package at.fhj.itm;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest
{
    @Test
    public void testProperties()
    {
    	User user = new User(7,"Christian","xxx"); 
        assertEquals(7, user.getId());
        assertEquals("Christian", user.getName());
        assertEquals("xxx", user.getPassword());
    }
    
    
    @Test
    public void testToString()
    {
    	User user = new User(7,"Christian","xxx");
        assertEquals("7,Christian,xxx", user.toString());
    }

  
   @Test(expected=IllegalArgumentException.class)
    public void testUsernameNull()
    {
    	new User(7,null,"xxx");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testUsernameEmpty()
    {
    	new User(7,"","xxx");
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testPasswordNull()
    {
    	new User(7,"Christian",null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPasswordEmpty()
    {
    	new User(7,"Christian","");
    }
   
}
