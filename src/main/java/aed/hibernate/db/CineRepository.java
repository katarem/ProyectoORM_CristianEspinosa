package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aed.hibernate.model.Cine;

public class CineRepository implements Repository<Cine>{
	
	Configuration configuration;
	SessionFactory sessionFactory;
	
	
	public CineRepository() {
		 configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 sessionFactory = configuration.buildSessionFactory();
	}
	
	

    @Override
    public boolean insert(Cine t) {
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
    	   	Cine cine = session.get(Cine.class, id);
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
    public boolean update(int id, Cine entity) {
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
    public Cine getById(int id) {
        try(Session session = sessionFactory.openSession()) {
        	session.beginTransaction();
        	var t =session.get(Cine.class, id);
        	session.getTransaction().commit();
        	return t;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Override
    public List<Cine> getAll() {
       try(Session session = sessionFactory.openSession()) {
    	   session.beginTransaction();
    	   var query = String.format("from %s",Cine.class.getName());
    	   var t = session.createQuery(query,Cine.class).list();
    	   session.getTransaction().commit();
    	   return t;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
       }
    }
    
}
