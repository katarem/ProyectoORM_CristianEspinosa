package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aed.hibernate.model.Pelicula;

public class PeliculaRepository implements Repository<Pelicula>{
	
	Configuration configuration;
	SessionFactory sessionFactory;
	
	
	public PeliculaRepository() {
		 configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 sessionFactory = configuration.buildSessionFactory();
	}
	
	

    @Override
    public boolean insert(Pelicula t) {
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
    	   	Pelicula cine = session.get(Pelicula.class, id);
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
    public boolean update(int id, Pelicula entity) {
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
    public Pelicula getById(int id) {
        try(Session session = sessionFactory.openSession()) {
        	session.beginTransaction();
        	var t =session.get(Pelicula.class, id);
        	session.getTransaction().commit();
        	return t;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Override
    public List<Pelicula> getAll() {
       try(Session session = sessionFactory.openSession()) {
    	   session.beginTransaction();
    	   var query = String.format("from %s",Pelicula.class.getName());
    	   var t = session.createQuery(query,Pelicula.class).list();
    	   session.getTransaction().commit();
    	   return t;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
       }
    }
    
}
