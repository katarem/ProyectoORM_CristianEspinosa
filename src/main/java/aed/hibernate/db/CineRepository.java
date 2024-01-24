package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Cine;

public class CineRepository implements Repository<Cine>{

    @Override
    public boolean insert(Cine t) {
        try {
            DBManager.insertEntity(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Cine t) {
       try {
            Cine c = (Cine) DBManager.getSessionObjectById(Cine.class, t.getId());
            DBManager.removeById(c);
            return true;
       } catch (Exception e) {
            e.printStackTrace();
            return false;
       }
    }

    @Override
    public boolean update(Cine t) {
        try {
            Cine c = (Cine) DBManager.getSessionObjectById(Cine.class, t.getId());
            c.setCalle(t.getCalle());
            c.setNombre(t.getNombre());
            c.setNumero(t.getNumero());
            c.setTarifas(t.getTarifas());
            DBManager.updateById(c);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cine getById(Cine t) {
        return (Cine) DBManager.getSessionObjectById(Cine.class, t.getId());
    }

    @Override
    public List<Cine> getAll() {
       return (List<Cine>) DBManager.getAll(Cine.class);
    }
    
}
