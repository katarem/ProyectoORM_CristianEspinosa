package aed.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @Column(name = "pelicula_titulo")
    private String titulo;
    @Column(name = "pelicula_director")
    private String director;
    @Column(name = "pelicula_clasificacion")
    private String clasificacion;
    @Column(name = "pelicula_genero")
    private String genero;
    @Column(name = "pelicula_protagonista")
    private String protagonista;
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getProtagonista() {
        return protagonista;
    }
    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        result = prime * result + ((clasificacion == null) ? 0 : clasificacion.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((protagonista == null) ? 0 : protagonista.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pelicula other = (Pelicula) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (director == null) {
            if (other.director != null)
                return false;
        } else if (!director.equals(other.director))
            return false;
        if (clasificacion == null) {
            if (other.clasificacion != null)
                return false;
        } else if (!clasificacion.equals(other.clasificacion))
            return false;
        if (genero == null) {
            if (other.genero != null)
                return false;
        } else if (!genero.equals(other.genero))
            return false;
        if (protagonista == null) {
            if (other.protagonista != null)
                return false;
        } else if (!protagonista.equals(other.protagonista))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Pelicula [titulo=" + titulo + ", director=" + director + ", clasificacion=" + clasificacion
                + ", genero=" + genero + ", protagonista=" + protagonista + "]";
    }    
    
}
