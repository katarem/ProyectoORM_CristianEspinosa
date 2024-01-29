package aed.hibernate.model;

import jakarta.persistence.CascadeType;
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
public class Pase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pase_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;
    @Column(name = "pase_fecha")
    private String fecha;

    public Pase(){}
    
    public Pase(int id) {this.id = id;}

    public Pase(Cine cine, Pelicula pelicula, String fecha) {
        this.cine = cine;
        this.pelicula = pelicula;
        this.fecha = fecha;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public String toString() {
        return "Pase [id=" + id + ", cine=" + cine.getNombre() + ", pelicula=" + pelicula.getTitulo() + ", fecha=" + fecha + "]";
    }

    
    
}
