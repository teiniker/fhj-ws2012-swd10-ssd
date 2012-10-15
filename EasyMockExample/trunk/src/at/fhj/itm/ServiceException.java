package at.fhj.itm;

/**
 * This exception is used th encapsulate all integration layer exceptions
 * (e.g. DAOException, etc.)
 */
public class ServiceException
	extends Exception
{
	private static final long serialVersionUID = 1L;

	public ServiceException()
	{
		super("ServiceException");
	}
	
	public ServiceException(String msg)
	{
		super("ServiceException: " + msg);
	}
}
