package at.fhj.itm;


/**
 * This is the implementation of a simple service object which is used as an 
 * Object Under Test (OUT).
 * 
 * Note that the used PersonDAO is a faked implementation based on the
 * EasyMock framework - there is no real code out there...
 */
public class UserService
{
	/*
	 * Constructor
	 */
	public UserService(UserDAO userDAO)
	{
		if(userDAO == null)
			throw new IllegalArgumentException("UserDAO is null!");
		
		setUserDAO(userDAO);
	}

	
	/*
	 * Dependency: userDao:UserDAO
	 */
	private UserDAO userDAO;
    public void setUserDAO(UserDAO userDAO) 
    {
		this.userDAO = userDAO;
	}

    
    /*
     * Business Methods
     */
    
	public String toCSV(int id) throws ServiceException
    {
        StringBuilder csv = new StringBuilder();
        try 
        {
            User p = userDAO.findById(id);
            csv.append(p.toString());
        }
        catch(DAOException e)
        {
        	//Note CKrenn: Antipattern: Deletes stacktrace!
        	throw new ServiceException(e.getMessage());
        }
        return csv.toString();
    }
    
    public void addUser(User p) throws ServiceException
    {
        try 
        {
            userDAO.insert(p);
        }
        catch(DAOException e)
        {
        	//Note CKrenn: Antipattern: Deletes stacktrace!
        	throw new ServiceException(e.getMessage());
        }
    }
    
    
    //...
}
