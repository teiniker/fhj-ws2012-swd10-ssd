package at.fhj.swd.business;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.User;
import at.fhj.swd.utils.TestDataFactory;

/**
 * @author christoph.seiltinger
 */

public class DocumentBOTest {

    private static DocumentBO _DocumentBO;

    private static CommunityBO _CommunityBO;

    private static TestDataFactory _testDataFactory;

    private static IDataContext<Document> _contextDocument;
    private static IDataContext<User> _contextUser;
    private static IDataContext<Community> _contextCommunity;


    static Document document1;
    static Community community;
    static User _user;

    @BeforeClass
    public static void setup() {

        _testDataFactory = new TestDataFactory();

        _CommunityBO = new CommunityBO();
        _DocumentBO = new DocumentBO();

        _contextDocument = new DBContext<Document>();
        _contextUser = new DBContext<User>();
        _contextCommunity = new DBContext<Community>();


        document1 = _testDataFactory.createDocument("document1");
        _user = _testDataFactory.createUser("Test User");
        community = _testDataFactory.createCommunity("Test community");

        _contextUser.create(_user);
        document1.setOwner(_user);
        _contextDocument.create(document1);


        community.setAdmin(_user);
        _contextCommunity.create(community);

        document1.addCommunity(community);
        // document1.setCommunities(community);

    }

    @Test
    public void testGetAll() {
        Assert.assertTrue(_DocumentBO.getAll().contains(document1));
        Assert.assertTrue(document1.getCommunities().contains(community));
    }

    @Test
    public void testUpload() throws IOException {
        Document _new = _DocumentBO.createDocument("test.jpg", (List<Community>)_CommunityBO.getAll(), _user,
            "d bla bla");
        Assert.assertTrue(_new.getCommunities().toString().contains("Test community"));
        Assert.assertTrue(_new.getUrl().contains(_new.getId().toString()));
        Assert.assertEquals("test.jpg", _new.getName());
        Assert.assertTrue(_new.isPublic());

        // _contextDocument.delete(document1);
        // Document _empty = _DocumentBO.createDocument("test.jpg", (List<Community>)_CommunityBO.getAll(), _user,
        // "d bla bla");
        // Assert.assertEquals("", _empty.getCommunities().toString());

        Assert.assertFalse(_DocumentBO.download(_new));
        Assert.assertFalse(_DocumentBO.remove(_new.getId()));
    }
}
