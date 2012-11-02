package at.fhj.swd.data;

import java.util.Collection;

public interface IDataContext<T> {

    public abstract boolean create(T item);

    public abstract T readOneByQuery(String query, Class<T> c) throws Exception;

    public abstract T readOne(long id, Class<T> c);

    public abstract Collection<T> readAll(Class<T> c);

    public abstract T update(T item);

    public abstract boolean delete(T item);

}
