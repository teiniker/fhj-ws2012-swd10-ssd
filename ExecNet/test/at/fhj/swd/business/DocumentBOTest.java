package at.fhj.swd.business;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.User;
import at.fhj.swd.utils.TestDataFactory;
import at.fhj.swd.utils.TestRuntimeContext;

/**
 * @author christoph.seiltinger
 */

public class DocumentBOTest {

    private DocumentBO _DocumentBO;
    private CommunityBO _CommunityBO;

    private TestDataFactory _testDataFactory;
    private TestRuntimeContext _testRuntimeContext;

    private IDataContext<Document> _contextDocument;
    private IDataContext<User> _contextUser;
    private IDataContext<Community> _contextCommunity;

    private File _file;

    Document document1;
    Community community;

    @Before
    public void setup() {

        _testDataFactory = new TestDataFactory();
        _testRuntimeContext = new TestRuntimeContext();

        _CommunityBO = new CommunityBO();
        _DocumentBO = new DocumentBO();

        _contextDocument = new DBContext<Document>();
        _contextUser = new DBContext<User>();
        _contextCommunity = new DBContext<Community>();


        document1 = _testDataFactory.createDocument("document1");
        User user = _testDataFactory.createUser("Test User");
        community = _testDataFactory.createCommunity("Test community");

        _testRuntimeContext.setAuthenticated(user);


        _contextUser.create(user);
        document1.setOwner(user);
        _contextDocument.create(document1);


        community.setAdmin(user);
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
        System.out.println("war da");

        _file.createTempFile("test", "test");

        System.out.println(_file.getName());

        // _DocumentBO.upload((UploadedFile)_file, (List<Community>)document1.getCommunities());
    }

}
