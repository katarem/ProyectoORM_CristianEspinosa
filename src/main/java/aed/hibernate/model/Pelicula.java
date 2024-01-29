package aed.hibernate.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pelicula")
public class Pelicula{
    @Id
    @Column(name = "pelicula_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pelicula_titulo")
    private String titulo;
    @Column(name = "pelicula_director")
    private String director;
    @Column(name = "pelicula_clasificacion")
    private String clasificacion;
    @Column(name = "pelicula_genero")
    private String genero;
    @Column(name = "pelicula_caratula")
    private String caratula = "";
    @Column(name = "pelicula_protagonista")
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Protagonista> protagonistas;
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pase> pases;
    
    public Pelicula() {}

    public Pelicula(int id) {}

    public Pelicula(String titulo, String director, String clasificacion, String genero, String caratula) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.clasificacion = clasificacion;
		this.genero = genero;
		this.caratula = caratula;
	}

	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }  

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
    
    public List<Protagonista> getProtagonistas() {
		return protagonistas;
	}

	public void setProtagonistas(List<Protagonista> protagonistas) {
		this.protagonistas = protagonistas;
	}

	public List<Pase> getPases() {
        return pases;
    }
    public void setPases(List<Pase> pases) {
        this.pases = pases;
    }
    public void addPase(Pase p){
        this.pases.add(p);
    }
    public void removePase(Pase p){
        this.pases.remove(p);
    }
    
    public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	@Override
    public String toString() {
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", director=" + director + ", clasificacion="
                + clasificacion + ", genero=" + genero + "]";
    }
    
      

    
    
}
