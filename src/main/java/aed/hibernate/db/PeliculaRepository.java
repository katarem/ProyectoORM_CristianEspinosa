package aed.hibernate.db;

import java.util.List;

import aed.hibernate.model.Pelicula;

public class PeliculaRepository implements Repository<Pelicula> {

    @Override
    public boolean insert(Pelicula t) {
        return DBManager.insertEntity(t);
    }

    @Override
    public boolean remove(Pelicula p){
        return DBManager.removeById(p);
    }

    @Override
    public Pelicula getById(Pelicula p) {
        var o = DBManager.getAll(Pelicula.class)
                            .stream()
                            .map(Pelicula.class::cast)
                            .filter(pelicula -> pelicula.getTitulo().equals(p.getTitulo()))
                            .findAny();
        return o.orElseGet(null);
    }

    @Override
    public List<Pelicula> getAll() {
        return (List<Pelicula>)DBManager.getAll(Pelicula.class);
    }
    
}
