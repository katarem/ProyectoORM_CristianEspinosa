package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aed.hibernate.model.Tarifa;

public class TarifaRepository implements Repository<Tarifa>{
	
	Configuration configuration;
	SessionFactory sessionFactory;
	
	
	public TarifaRepository() {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}
	
	

    @Override
    public boolean insert(Tarifa t) {
        try(Session session  = sessionFactory.openSession()) {
        	session.beginTransaction();
        	session.persist(t);
        	session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
       try(Session session = sessionFactory.openSession()) {
    	   	Tarifa cine = session.get(Tarifa.class, id);
    	   	session.beginTransaction();
    	   	session.remove(cine);
    	   	session.getTransaction().commit();
            return true;
       } catch (Exception e) {
            e.printStackTrace();
            return false;
       }
    }

    @Override
    public boolean update(int id, Tarifa entity) {
        try(Session session = sessionFactory.openSession()) {
        	entity.setId(id);
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tarifa getById(int id) {
        try(Session session = sessionFactory.openSession()) {
        	session.beginTransaction();
        	var t =session.get(Tarifa.class, id);
        	session.getTransaction().commit();
        	return t;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Override
    public List<Tarifa> getAll() {
       try(Session session = sessionFactory.openSession()) {
    	   session.beginTransaction();
    	   var query = String.format("from %s",Tarifa.class.getName());
    	   var t = session.createQuery(query,Tarifa.class).list();
    	   session.getTransaction().commit();
    	   return t;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
       }
    }
    
}
