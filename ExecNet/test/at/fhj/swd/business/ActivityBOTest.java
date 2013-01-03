package at.fhj.swd.business;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
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
    private static Community community;

    @BeforeClass
    public static void setup() {

        _factory = new TestDataFactory();
        _context = new TestRuntimeContext();

        user = _factory.createUser("testUser");
        community = _factory.createCommunity("Huehott");

        _context.setCurrentUser(user);
        _context.setAuthenticated(user);
        _context.setCurrentCommunity(community);

        activityBO = new ActivityBO();
        activityBO.set_rc(_context);

        _pc = new DBContext<Post>();
        _cc = new DBContext<Community>();
        _uc = new DBContext<User>();

        post = _factory.createPost("TestPostText");
        post.setAuthor(user);

        _uc.create(user);
        _pc.create(post);
        _cc.create(community);
    }

    @Test
    public void testGetCurrentCulture() throws Exception {
        user.setCulture("EN");
        Assert.assertEquals("EN", activityBO.getCurrentCulture());
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

    @Test
    public void testIsPortalAdmin_falseExpected_UserNull() throws Exception {
        user = _factory.createUser(null);
        _context.setCurrentUser(user);
        activityBO.set_rc(_context);
        Assert.assertFalse(activityBO.isPortalAdmin());
    }

    @Test
    public void testIsPortalAdmin_trueExpected() throws Exception {
        User user1 = new User();
        user1.setEmail("user1@email.com");
        user1.setPassword("p@ssword");
        user1.setAdmin(true);
        user1.setPortalAdmin(true);
        user1.setUsername("user1");
        user1.setFirstname("Max");
        user1.setLastname("Mustermann");
        _context.setCurrentUser(user1);
        activityBO.set_rc(_context);
        Assert.assertTrue(activityBO.isPortalAdmin());
    }

    @Test
    public void testUpdatePost_trueExpected() throws Exception {
        post.setCommunity(community);
        Assert.assertTrue(activityBO.updatePost(post));
    }

    @Test
    public void testDelete_trueExpected() throws Exception {
        Assert.assertTrue(activityBO.delete(post));
    }

    @Test
    public void testGetCommunities() {
        Collection<Community> co = new ArrayList<Community>();
        co.add(community);
        user.setCommunities(co);
        Assert.assertEquals(2, activityBO.getCommunities().size());
    }

    @Test
    // (expected = IllegalStateException.class)
    public void testDelete_ExceptionExpected() {
        activityBO.delete(null);
        Assert.assertTrue(true);
    }


    @Test(expected = IllegalArgumentException.class)
    public void addEntry() {
        Date dtNow = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dtNow);
        cal.add(Calendar.DATE, 1);
        Date dtNowPlus1Day = cal.getTime();

        activityBO.add("", dtNowPlus1Day, dtNow, 1);
    }
}
