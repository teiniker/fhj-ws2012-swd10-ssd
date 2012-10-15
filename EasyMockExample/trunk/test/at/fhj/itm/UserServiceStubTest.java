package at.fhj.itm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fhj.itm.DAOException;
import at.fhj.itm.ServiceException;
import at.fhj.itm.User;
import at.fhj.itm.UserDAOInterface;
import at.fhj.itm.UserService;


/**
 * These test cases use the UserService object as an Object Under Test.
 * 
 * Note that the UserService implementation uses the faked UserDAO
 * implementation, thus, only the PersonService code is tested.
 */
public class UserServiceStubTest 
{   
    @Test
    public void testAddPerson() throws ServiceException, DAOException
    {
    	UserDAOInterface stub = new UserDAOStub();
        UserService service = new UserService(stub);     	
    	
    	User user = new User(7, "Egon");
        service.addUser(user);        
    }

    @Test
    public void testToCsv() throws ServiceException, DAOException
    {
    	UserDAOInterface stub = new UserDAOStub();
    	UserService service = new UserService(stub);     	
        
        String csv = service.toCSV(7);
        assertEquals("7,Egon", csv);
    }
    
}
