package at.fhj.swd.data;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import at.fhj.swd.domain.IEntity;

public class DBContext<T> implements IDataContext<T> {
	
	protected static final String PERSISTENCE_UNIT_NAME = "ExecNet";
	protected static EntityManagerFactory _factory;
	protected static final Logger logger = Logger.getLogger(DBContext.class.getName());
		
	public DBContext(){
		_factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); 
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#create(T)
	 */
	@Override
	public boolean create  (T item){
		boolean result = false;
		
		EntityManager _em = _factory.createEntityManager();
		
		try {
			_em.getTransaction().begin();
			_em.persist(item);
			_em.getTransaction().commit();
			result = true;
		} catch (Exception ex) {
			_em.getTransaction().rollback();
			logger.error(ex.getMessage(), ex);
			result = false;
		} finally {
			_em.close();
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#readOneByQuery(java.lang.String, java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T readOneByQuery (String query, Class<T> c) throws Exception{
		EntityManager _em = _factory.createEntityManager();

		T _x = (T) c.newInstance();
		String _s =  ((IEntity)_x).getEntityName();
		
		Query _q = _em.createQuery("select i from " + _s + " i where " +  query);
			//u._username =?1").setParameter(1, user.getUsername());
		
		try {
			T _entry = (T) _q.getSingleResult();
			return _entry;
		} catch (NoResultException e) {
			logger.debug("No results found");
			return null;
		} finally {
			_em.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#readOne(long, java.lang.Class)
	 */
	@Override
	public T readOne (long id, Class<T> c) {
		EntityManager _em = _factory.createEntityManager();
		
		try {
			T _x = (T) c.newInstance();
			_x = _em.find(c, id);
			return _x;
		}  catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		} 
		finally {
			_em.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#readAll(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> readAll(Class<T> c){
		EntityManager _em = _factory.createEntityManager();
		 
		try {
			T _x = (T) c.newInstance();
			IEntity _i = (IEntity)_x;
			String _s =  _i.getEntityName();
			Query query = _em.createQuery("select i from " + _s + " i");
			Collection<T> result = query.getResultList();
			return result;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		}  finally {
			_em.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#update(T)
	 */
	@Override
	public T update (T item){
		EntityManager _em = _factory.createEntityManager();
		try {
			_em.getTransaction().begin();
			item = _em.merge(item);
			_em.getTransaction().commit();
			}catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
		} finally {
			_em.close();
		}
		return item;
	}
	
	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContext#delete(T)
	 */
	@Override
	public boolean delete (T item){
		EntityManager _em = _factory.createEntityManager();
		boolean result = false;
		try {
			_em.getTransaction().begin();
			T target = _em.merge(item);
			_em.remove(target);
			_em.getTransaction().commit();
			result = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			result = false;
		} finally {
			_em.close();
		}
					
		return result;
	}

}
