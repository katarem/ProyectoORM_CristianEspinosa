package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aed.hibernate.model.Protagonista;

public class ProtagonistaRepository implements Repository<Protagonista>{
	
	Configuration configuration;
	SessionFactory sessionFactory;
	
	
	public ProtagonistaRepository() {
		 configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 sessionFactory = configuration.buildSessionFactory();
	}
	
	

    @Override
    public boolean insert(Protagonista t) {
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
    	   	Protagonista cine = session.get(Protagonista.class, id);
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
    public boolean update(int id, Protagonista entity) {
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
    public Protagonista getById(int id) {
        try(Session session = sessionFactory.openSession()) {
        	session.beginTransaction();
        	var t =session.get(Protagonista.class, id);
        	session.getTransaction().commit();
        	return t;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Override
    public List<Protagonista> getAll() {
       try(Session session = sessionFactory.openSession()) {
    	   session.beginTransaction();
    	   var query = String.format("from %s",Protagonista.class.getName());
    	   var t = session.createQuery(query,Protagonista.class).list();
    	   session.getTransaction().commit();
    	   return t;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
       }
    }
    
}
