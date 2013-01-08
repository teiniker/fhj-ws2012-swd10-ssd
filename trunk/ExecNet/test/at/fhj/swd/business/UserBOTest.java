package at.fhj.swd.business;

import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.User;
import at.fhj.swd.testhelper.TestHelper;

/**
 * @author david.tomes, elke.mattheiss
 */

public class UserBOTest {
    
    private static UserBO userBO;
    private static IDataContext<User> _context;
    
    @BeforeClass
    public static void setup() {
        
        userBO = new UserBO();
        _context = new DBContext<User>();
        
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        
        user1.setEmail("user1@email.com");
        user1.setPassword("p@ssword");
        user1.setAdmin(false);
        user1.setUsername("user1");
        
        user2.setEmail("user2@email.com");
        user2.setPassword("p@ssword");
        user2.setAdmin(false);
        user2.setUsername("user2");
        
        user3.setEmail("user3@email.com");
        user3.setPassword("p@ssword");
        user3.setAdmin(false);
        user3.setUsername("user3");
        
        user1.setFirstname("Max");
        user1.setLastname("Mustermann");
        user2.setFirstname("Maximilian");
        user2.setLastname("Muster");
        user3.setFirstname("Maike");
        user3.setLastname("Maler");
        
        _context.create(user1);
        _context.create(user2);
        _context.create(user3);
    }
    
    @AfterClass
	 public static void tearDown() {
   	//TODO tear down at setup created data
		TestHelper.ShutDownDerby();
	 } 

    @Test
    public void searchUser_ShouldReturnTwoUsers() throws Exception {
        
//        System.out.println(userBO.getAll());
        String searchResult = userBO.searchUser("Max Muster").toString();
        Assert.assertEquals("[ID = 1, username = user1, isAdmin = false, " +
        		"isPortalAdmin = false, ID = 2, username = user2, isAdmin = false, " +
        		"isPortalAdmin = false]", searchResult);
    }
    
    @Test
    public void searchUser_ShouldReturnOneUsers() throws Exception {
        
        String searchResult = userBO.searchUser("Maike Mur").toString();
        Assert.assertEquals("[ID = 3, username = user3, isAdmin = false, " +
        		"isPortalAdmin = false]", searchResult);
    }
    
    @Test
    public void searchUser_ShouldReturnNoUsers() throws Exception {
        
        String searchResult = userBO.searchUser("Heinrich Mur").toString();
        Assert.assertEquals("[]", searchResult);
    }

}
