package at.fhj.swd.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;
import at.fhj.swd.utils.TestDataFactory;
import at.fhj.swd.utils.TestRuntimeContext;


public class ActivityBOTest {

    private ActivityBO activityBO;

    private IDataContext<Post> _pc;
    private IDataContext<Community> _cc;
    private IDataContext<User> _uc;

    private TestRuntimeContext _context;
    private TestDataFactory _factory;

    private Post post;

    @Before
    public void setup() {

        activityBO = new ActivityBO();

        _pc = new DBContext<Post>();
        _cc = new DBContext<Community>();
        _uc = new DBContext<User>();

        User user = new User();
        post = new Post();

        _factory = new TestDataFactory();
        _context = new TestRuntimeContext();

        user = _factory.createUser("testUser");
        post = _factory.createPost("TestPostText");
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
