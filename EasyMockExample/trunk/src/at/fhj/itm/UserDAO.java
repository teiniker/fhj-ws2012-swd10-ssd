package at.fhj.itm;

/**
 * This interface defines the PersonDAO methods which can be implemented
 * in different ways and technologies (JDBC, Hibernate, XML, etc.)  
 */
public interface UserDAO
{
    void insert(User p) throws DAOException;
    void update(User p) throws DAOException;
    void delete(int id) throws DAOException;
    
    User findById(int id) throws DAOException;    
}
