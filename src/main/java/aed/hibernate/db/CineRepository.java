package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Cine;

public class CineRepository implements Repository<Cine> {

    @Override
    public boolean insert(Cine t) {
        return DBManager.insertEntity(t);
    }

    @Override
    public boolean remove(Cine t) {
        return DBManager.removeById(t);
    }

    @Override
    public Cine getById(Cine t) {
        var o = getAll()
                .stream()
                .filter(Cine.class::isInstance)
                .filter(cine -> cine.getNombre().equals(t.getNombre()))
                .findAny();
        return o.orElseGet(null);
    }

    @Override
    public List<Cine> getAll() {
        return (List<Cine>) DBManager.getAll(Cine.class);
    }
    
}
