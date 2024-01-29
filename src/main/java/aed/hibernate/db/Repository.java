package aed.hibernate.db;

import java.util.List;

public interface Repository<T> {
    public boolean insert(T t);
    public boolean remove(int id);
    public boolean update(int id, T entity);
    public T getById(int id);
    public List<T> getAll();
}
