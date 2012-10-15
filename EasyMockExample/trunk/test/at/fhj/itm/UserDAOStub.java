package at.fhj.itm;

import at.fhj.itm.DAOException;
import at.fhj.itm.User;
import at.fhj.itm.UserDAOInterface;

/**
 * This is a dummy class that simulates UserDAO for unit tests.
 *
 */
public class UserDAOStub
	implements UserDAOInterface
{

	public void delete(int id) throws DAOException
	{
		System.out.println("UserDAOStub> delete: " + id);	
	}

	public User findById(int id) throws DAOException
	{
		System.out.println("UserDAOStub> findById: " + id);
		return new User(7,"Egon");
	}

	public void insert(User p) throws DAOException
	{
		System.out.println("UserDAOStub> insert: " + p);
	}

	public void update(User p) throws DAOException
	{
		System.out.println("UserDAOStub> update: " + p);
	}
}
