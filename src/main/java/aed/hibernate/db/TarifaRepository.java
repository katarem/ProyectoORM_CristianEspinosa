package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Tarifa;

public class TarifaRepository implements Repository<Tarifa> {

    @Override
    public boolean insert(Tarifa t) {
        return DBManager.insertEntity(t);
    }

    @Override
    public boolean remove(Tarifa t) {
        return DBManager.removeById(t);
    }

    @Override
    public Tarifa getById(Tarifa t) {
        var o = getAll()
            .stream()
            .filter(Tarifa.class::isInstance)
            .filter(tarifa -> tarifa.getIdTarifa() == t.getIdTarifa())
            .findAny();
        return o.orElse(null);
    }

    @Override
    public List<Tarifa> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
