package at.fhj.swd.business;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.AfterClass;

import java.util.Calendar;
import java.util.Date;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd.controller.CommentBean;
import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;
import at.fhj.swd.testhelper.TestHelper;
import at.fhj.swd.utils.TestDataFactory;
import at.fhj.swd.utils.TestRuntimeContext;


public class CommentBOTest {

    private static CommentBO commentBO;
    private static CommunityBO communityBO;
    private static CommentBean commentB;

    private static IDataContext<Comment> _cc;
    private static IDataContext<User> _uc;
    private static IDataContext<Post> _pc;
    private static IDataContext<Community> _cyc;

    private static TestRuntimeContext _context;
    private static TestDataFactory _factory;

    private static User user;
    private static Comment comment;
    private static Post post;
    private static Community community;


    @BeforeClass
    public static void setup() {
        _factory = new TestDataFactory();

        _context = new TestRuntimeContext();
        user = _factory.createUser("testUser");
        community = _factory.createCommunity("testCommunity");
        post = _factory.createPost("testPost");

        _context.setCurrentUser(user);
        _context.setAuthenticated(user);
        _context.setCurrentCommunity(community);

        Date dtNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtNow);
        cal.add(Calendar.DATE, 1);
        Date dtNowPlus1Day = cal.getTime();

        commentBO = new CommentBO();
        commentB = new CommentBean();
        communityBO = new CommunityBO();

        _cc = new DBContext<Comment>();
        _pc = new DBContext<Post>();
        _cyc = new DBContext<Community>();
        _uc = new DBContext<User>();

        // _uc.create(user);
        post.setAuthor(user);
        // _pc.create(post);

        post = _factory.createPost("TestPostText");

    }

    @AfterClass
	 public static void tearDown() {
   	//TODO tear down at setup created data
		TestHelper.ShutDownDerby();
	 } 
    
    @Test
    public void testAddPost() {
        Assert.assertTrue(commentBO.add(post, "This is a test"));
        // commentBO.add(post, "This is a test");
        // System.out.println("pansitest 01:" + commentB.getcomments(post));
    }
}
