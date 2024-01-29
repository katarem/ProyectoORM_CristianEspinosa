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
@Table(name = "protagonista")
public class Protagonista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "protagonista_id")
	private int id;
	@Column(name = "protagonista_nombre")
	private String nombre;
	@Column(name = "protagonista_foto", length = 1000)
	private String foto;
	@ManyToOne
	@JoinColumn(name = "pelicula_id")
	private Pelicula pelicula;
	
	public Protagonista() {}
	
	public Protagonista(int id) {this.id = id;}
	
	public Protagonista(String nombre, String foto) {
		super();
		this.nombre = nombre;
		this.foto = foto;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Pelicula getPelicula() {
		return this.pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
