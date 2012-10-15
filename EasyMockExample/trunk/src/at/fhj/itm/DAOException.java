/**
 * A DAOException is used to encapsulate all kinds of SQL related exceptions.
 */

package at.fhj.itm;

public class DAOException
	extends Exception
{
	private static final long serialVersionUID = 1L;

	public DAOException()
	{
		super("DAOException");
	}
	
	public DAOException(String msg)
	{
		super("DAOException: " + msg);
	}
}
