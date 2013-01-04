package at.fhj.swd.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;
import at.fhj.swd.utils.TestDataFactory;
import at.fhj.swd.utils.TestRuntimeContext;


public class PinBoardBOTest {

    private static PinBoardBO pinBoardBO;

    private static IDataContext<Post> _pc;
    private static IDataContext<User> _uc;

    private static TestRuntimeContext _context;
    private static TestDataFactory _factory;
    
    private static User user;
    private static User user2;
    private static Post post;

    @BeforeClass
    public static void setup() {

        _factory = new TestDataFactory();
        _context = new TestRuntimeContext();
        
        user = _factory.createUser("testUser");
        user2 = _factory.createUser("testUser2");
        
        _context.setAuthenticated(user);
        _context.setCurrentUser(user);
        
        pinBoardBO = new PinBoardBO();
        
        _pc = new DBContext<Post>();
        _uc = new DBContext<User>();

        post = _factory.createPinBoardPost("TestPostText");
        post.setAuthor(user);
        post.setPinboard(user);
        _uc.create(user);
        _pc.create(post);
        
        post = _factory.createPinBoardPost("TestPostText2");
        post.setAuthor(user2);
        post.setPinboard(user2);
        _uc.create(user2);
        _pc.create(post);
        
        
    }

    @Test
    public void testShowAllPosts() throws Exception {
        Assert.assertEquals( 2, pinBoardBO.getAll());
    }

    @Test
    public void testMyPosts() throws Exception {
        Assert.assertEquals( 1, pinBoardBO.getMy());
    }

    @Test
    public void testIsAuthor_nullExpected() throws Exception {
        Assert.assertEquals(1,pinBoardBO.getOthers(user2.getId()));
    }

}
