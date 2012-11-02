package at.fhj.swd.utils;

import java.util.Date;

public class NearCache<T> {

    private Date _updated;
    private T _item;
    private int _ttl;

    public NearCache(int ttl) {
        _ttl = ttl;
    }

    public void put(T item) {
        _item = item;
        _updated = new Date();
    }

    public T get() {
        Date now = new Date();
        if (_updated.getTime() + _ttl * 1000 < now.getTime()) {
            _item = null;
        }
        return _item;
    }

}
