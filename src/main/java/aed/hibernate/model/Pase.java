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
	private int id;
	@ManyToOne
	@JoinColumn(name = "cine_id")
	private Cine cine;
	@ManyToOne
	@JoinColumn(name = "pelicula_id")
	private Pelicula pelicula;
	@Column(name = "pase_fecha")
	private String fecha;
	@Column(name = "pase_hora")
	private String hora;

	public Pase() {
	}

	public Pase(int id) {
		this.id = id;
	}

	public Pase(Cine cine, Pelicula pelicula, String fecha, String hora) {
		this.cine = cine;
		this.pelicula = pelicula;
		this.fecha = fecha;
		this.hora = hora;
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pase))
			return false;
		if (this != obj)
			return false;
		Pase other = (Pase) obj;
		if (other.getId() != this.getId())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return fecha + " - " + hora;
	}

}
