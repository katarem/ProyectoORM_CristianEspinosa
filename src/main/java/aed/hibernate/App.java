package aed.hibernate;

import java.util.ArrayList;
import java.util.List;

import aed.hibernate.db.CineRepository;
//import aed.hibernate.db.PaseRepository;
//import aed.hibernate.db.PeliculaRepository;
import aed.hibernate.db.TarifaRepository;
import aed.hibernate.model.Cine;
import aed.hibernate.model.Tarifa;

public class App {

    static CineRepository cRepository = new CineRepository();
    //static PaseRepository pRepository = new PaseRepository();
    //static PeliculaRepository peRepository = new PeliculaRepository();
    static TarifaRepository tRepository = new TarifaRepository();

    public static void main(String[] args){
        
        testInsertar();
        delete();
        testLeer();
    }

    private static void testLeer(){
        System.out.println("EMPIEZA CINES");
        cRepository.getAll().forEach(System.out::println);
        System.out.println("ACABA CINES");
        System.out.println("EMPIEZA TARIFA");
        tRepository.getAll().forEach(System.out::println);
        System.out.println("ACABA TARIFA");
    }

    private static void delete(){
        var t = tRepository.getAll().get(0);
        tRepository.remove(t);
    }


    private static void testInsertar(){
        
        Cine c = new Cine();
        // creamos el cine
        c.setNombre("Yelmo Cines");
        c.setCalle("Avenida Trinidad");
        c.setNumero(29);
        c.setTelefono(21451523);
        cRepository.insert(c);
        
        // creamos las tarifas asociadas
        Tarifa t = new Tarifa();
        Tarifa t1 = new Tarifa();
        t.setCine(c);
        t1.setCine(c);
        t.setDia("24/01/2024");
        t1.setDia("24/01/2024");
        t.setPrecio(20.3);
        t1.setPrecio(10.2);

        // las asociamos al ítem de cine
        var tarifas = List.of(t,t1);
        c.setTarifas(tarifas);
        

        tarifas.forEach(tarifa -> tRepository.insert(tarifa));
    }


    private static List<Tarifa> createTarifas(int num){
        List<Tarifa> tarifas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            tarifas.add(createTarifa());
        }
        return tarifas;
    }

    private static Tarifa createTarifa(){
        var t = new Tarifa();
        var c = new Cine();
        c.setNombre("Yelmo Cines");
        t.setDia("23/01/2024");
        t.setPrecio(Math.random()*10+4);
        t.setCine(c);
        return t;
    } 

}
