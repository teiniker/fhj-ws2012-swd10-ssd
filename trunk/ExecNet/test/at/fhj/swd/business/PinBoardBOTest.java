package at.fhj.swd.business;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;
import at.fhj.swd.testhelper.TestHelper;
import at.fhj.swd.utils.TestDataFactory;
import at.fhj.swd.utils.TestRuntimeContext;


public class PinBoardBOTest {

    private static PinBoardBO pinBoardBO;

    private static IDataContext<User> _uc;

    private static TestRuntimeContext _context;
    private static TestRuntimeContext _context2;
    private static TestDataFactory _factory;
    
    private static User user;
    private static User user2;
    private static Post post;

    @BeforeClass
    public static void setup() {

        _factory = new TestDataFactory();
        _context = new TestRuntimeContext();
        _context2 = new TestRuntimeContext();
        
        user = _factory.createUser("testUser" + new Random().nextLong() );
        user2 = _factory.createUser("testUser" + new Random().nextLong() );
        
        _context.setAuthenticated(user);
        _context.setCurrentUser(user);
        
        pinBoardBO = new PinBoardBO();
        pinBoardBO.setRuntimeContext(_context);
        PinBoardBO pinBoardBO2 = new PinBoardBO();
        
        _uc = new DBContext<User>();

        post = _factory.createPinBoardPost("TestPostText PinBoardTest");
        _uc.create(user);
        
       pinBoardBO.add(post);
        
        System.out.println(user.getUsername());
        System.out.println("Post:"+post.getAuthor().getUsername());
        
        _context2.setAuthenticated(user2);
        _context2.setCurrentUser(user2);
        pinBoardBO2.setRuntimeContext(_context2);
        _uc.create(user2);
        
        
    
        Post post2 = _factory.createPinBoardPost("TestPostText2");
        pinBoardBO2.add(post2);
        _context.setCurrentUser(user);
        System.out.println ("Author post2:" + post2.getAuthor().getId());
        
    }

    @AfterClass
	 public static void tearDown() {
   	//TODO tear down at setup created data
		TestHelper.ShutDownDerby();
	 } 

    
    
   // @Test
   // public void testShowAllPosts() throws Eso. xception {
   //     Assert.assertEquals( 2, pinBoardBO.getAll().size());
   // }

    @Test
    public void testMyPosts() throws Exception {
        Assert.assertEquals( 1, pinBoardBO.getMy().size());
    }

    @Test
    public void testgetOthers() throws Exception {
    	System.out.println("id:" +user2.getId());
        Assert.assertEquals(1,pinBoardBO.getOthers(user2.getId()).size());
    }

}
