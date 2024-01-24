package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Tarifa;

public class TarifaRepository implements Repository<Tarifa>{

    @Override
    public boolean insert(Tarifa t) {
        try {
            DBManager.insertEntity(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Tarifa t) {
       try {
            Tarifa c = (Tarifa) DBManager.getSessionObjectById(Tarifa.class, t.getIdTarifa());
            c.setCine(null);
            DBManager.removeById(c);
            return true;
       } catch (Exception e) {
            e.printStackTrace();
            return false;
       }
    }

    @Override
    public boolean update(Tarifa t) {
        try {
            Tarifa c = (Tarifa) DBManager.getSessionObjectById(Tarifa.class, t.getIdTarifa());
            c.setCine(t.getCine());
            c.setDia(t.getDia());
            c.setPrecio(t.getPrecio());
            DBManager.updateById(c);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tarifa getById(Tarifa t) {
        return (Tarifa) DBManager.getSessionObjectById(Tarifa.class, t.getIdTarifa());
    }

    @Override
    public List<Tarifa> getAll() {
       return (List<Tarifa>) DBManager.getAll(Tarifa.class);
    }
    
}
