package aed.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pase")
public class Pase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pase_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cine_nombre")
    private Cine cine;
    @ManyToOne
    @JoinColumn(name = "pelicula_titulo")
    private Pelicula pelicula;
    @Column(name = "pase_fecha")
    private String fecha;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cine getCine() {
        return cine;
    }
    public void setCine(Cine cine) {
        this.cine = cine;
    }
    public Pelicula getPelicula() {
        return pelicula;
    }
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cine == null) ? 0 : cine.hashCode());
        result = prime * result + ((pelicula == null) ? 0 : pelicula.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
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
        Pase other = (Pase) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (cine == null) {
            if (other.cine != null)
                return false;
        } else if (!cine.equals(other.cine))
            return false;
        if (pelicula == null) {
            if (other.pelicula != null)
                return false;
        } else if (!pelicula.equals(other.pelicula))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Pase [id=" + id + ", cine=" + cine + ", pelicula=" + pelicula + ", fecha=" + fecha + "]";
    }

    
    
}
