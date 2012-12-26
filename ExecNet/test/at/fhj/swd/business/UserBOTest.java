package at.fhj.swd.business;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.User;

/**
 * @author david.tomes, elke.mattheiss
 */

public class UserBOTest {
    
    private UserBO userBO;
    private IDataContext<User> _context;
    
    @Before
    public void setup() {
        
        userBO = new UserBO();
        _context = new DBContext<User>();
        
        User user1 = new User();
        User user2 = new User();
        
        user1.setEmail("user1@email.com");
        user1.setPassword("p@ssword");
        user1.setAdmin(false);
        user1.setUsername("user1");
        
        user2.setEmail("user2@email.com");
        user2.setPassword("p@ssword");
        user2.setAdmin(false);
        user2.setUsername("user2");
        
        user1.setFirstname("Max");
        user1.setLastname("Mustermann");
        user2.setFirstname("Maximilian");
        user2.setLastname("Muster");
        
        _context.create(user1);
        _context.create(user2);
    }
    
    @Test
    public void searchUser_ShouldReturnUsers() throws Exception {
        
        System.out.println(userBO.getAll());
        System.out.println(userBO.searchUser("Max Muster").toString());
        
    }

}
