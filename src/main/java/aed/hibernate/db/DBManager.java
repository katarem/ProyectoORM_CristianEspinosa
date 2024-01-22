package aed.hibernate.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBManager {
    
    private static StandardServiceRegistry registro;
    private static SessionFactory sessionFactory;
    private static Session sesion;

    static{
        registro = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registro)
            .buildMetadata().buildSessionFactory();
        sesion = sessionFactory.openSession();
    }

    private DBManager(){}

    public static List<?> getAll(Class<?> clase){
        var query = String.format("from %s",clase.getName());
        return sesion.createQuery(query,clase).list();
    }

    public static boolean insertEntity(Object o){
        try{
            sesion.beginTransaction();
            sesion.save(o);
            sesion.getTransaction().commit();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean removeById(Object o){
        try {
            sesion.beginTransaction();
            sesion.delete(o);
            sesion.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateById(Object o){
       try{
           sesion.beginTransaction();
           sesion.merge(o);
           sesion.getTransaction().commit();
           return true;
       } catch(Exception e){
           e.printStackTrace();
           return false;
       }
    }

}
