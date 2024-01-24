package aed.hibernate.db;

import java.util.List;

public interface Repository<T> {
    public boolean insert(T t);
    public boolean remove(T t);
    public boolean update(T t);
    public T getById(T t);
    public List<T> getAll();
}
