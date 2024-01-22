package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Pase;

public class PaseRepository implements Repository<Pase> {

    @Override
    public boolean insert(Pase t) {
        return DBManager.insertEntity(t);
    }

    @Override
    public boolean remove(Pase t) {
        return DBManager.removeById(t);
    }

    @Override
    public Pase getById(Pase t) {
        var o = getAll()
                    .stream()
                    .filter(Pase.class::isInstance)
                    .filter(pase -> pase.getId().equals(t.getId()))
                    .findAny();
        return o.orElse(null);
    }

    @Override
    public List<Pase> getAll() {
        return (List<Pase>) DBManager.getAll(Pase.class);
    }
    
}
