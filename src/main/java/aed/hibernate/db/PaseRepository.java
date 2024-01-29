package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aed.hibernate.model.Cine;
import aed.hibernate.model.Pase;
import aed.hibernate.model.Pelicula;

public class PaseRepository implements Repository<Pase>{
	
	Configuration configuration;
	SessionFactory sessionFactory;
	
	
	public PaseRepository() {
		 configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 sessionFactory = configuration.buildSessionFactory();
	}
	
	

    @Override
    public boolean insert(Pase t) {
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
    	   	Pase pase = session.get(Pase.class, id);
    	   	//desacoplamos el pase de sus padres
    	   	Cine cine = session.get(Cine.class, pase.getCine().getId());
    	   	Pelicula pelicula = session.get(Pelicula.class, pase.getPelicula().getId());
    	   	
    	   	cine.removePase(pase);
    	   	pelicula.removePase(pase);
    	   	
    	   	session.merge(cine);
    	   	session.merge(pelicula);
    	   	
    	   	session.beginTransaction();
    	   	session.remove(pase);
    	   	session.getTransaction().commit();
            return true;
       } catch (Exception e) {
            e.printStackTrace();
            return false;
       }
    }

    @Override
    public boolean update(int id, Pase entity) {
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
    public Pase getById(int id) {
        try(Session session = sessionFactory.openSession()) {
        	session.beginTransaction();
        	var t =session.get(Pase.class, id);
        	session.getTransaction().commit();
        	return t;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Override
    public List<Pase> getAll() {
       try(Session session = sessionFactory.openSession()) {
    	   session.beginTransaction();
    	   var query = String.format("from %s",Pase.class.getName());
    	   var t = session.createQuery(query,Pase.class).list();
    	   session.getTransaction().commit();
    	   return t;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
       }
    }
    
}
