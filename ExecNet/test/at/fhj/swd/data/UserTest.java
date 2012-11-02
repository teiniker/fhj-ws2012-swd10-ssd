package at.fhj.swd.data;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.domain.*;
import at.fhj.swd.utils.TestDataFactory;

public class UserTest {

	TestDataFactory _factory = new TestDataFactory();
		
	@Before
	public void setUp() throws Exception {
	}


	
	@Test
	public void registerUserTest(){
		User u1 = _factory.createUser("u1");
		u1.registerNow();
		_factory.getRuntime().setCurrentUser(u1);
		
		Community c1 = _factory.createCommunity("community1");
		c1.setAdmin(u1); 
		c1.joinNow();
		
		Assert.assertTrue(c1.getUsers().contains(u1));
		Assert.assertTrue(u1.getCommunities().contains(c1));
		
	}

}
