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


public class ActivityBOTest {

    private static ActivityBO activityBO;

    private static IDataContext<Post> _pc;
    private static IDataContext<Community> _cc;
    private static IDataContext<User> _uc;

    private static TestRuntimeContext _context;
    private static TestDataFactory _factory;
    
    
    private static User user;
    private static Post post;

    @BeforeClass
    public static void setup() {

        _factory = new TestDataFactory();
        _context = new TestRuntimeContext();
        
        user = _factory.createUser("testUser");
        
        _context.setAuthenticated(user);
        _context.setCurrentUser(user);
        
        activityBO = new ActivityBO();
        activityBO.set_rc(_context);
        
        _pc = new DBContext<Post>();
        _cc = new DBContext<Community>();
        _uc = new DBContext<User>();

        post = _factory.createPost("TestPostText");
        post.setAuthor(user);
        
        _uc.create(user);
        _pc.create(post);
    }

    @Test
    public void testIsPortalAdmin_falseExpected() throws Exception {
        Assert.assertFalse(activityBO.isPortalAdmin());
    }

    @Test
    public void testIsAuthor_trueExpected() throws Exception {
        Assert.assertTrue(activityBO.isAuthor(post));
    }

    @Test
    public void testIsAuthor_nullExpected() throws Exception {
        Assert.assertFalse(activityBO.isAuthor(null));
    }

}
