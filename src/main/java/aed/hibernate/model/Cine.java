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
@Table(name = "cine")
public class Cine{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cine_id")
    private int id;
    @Column(name = "cine_nombre")
    private String nombre;
    @Column(name = "cine_calle")
    private String calle;
    @Column(name = "cine_numero")
    private int numero;
    @Column(name = "cine_telefono")
    private int telefono;
    
    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL)
    private List<Tarifa> tarifas;
    
    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pase> pases;
    
    public Cine() {}
    
    public Cine(int id) {this.id = id;}
    
    public Cine(String nombre, String calle, int numero, int telefono) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.telefono = telefono;
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
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public List<Tarifa> getTarifas() {
        return tarifas;
    }
    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    
    public List<Pase> getPases() {
        return pases;
    }
    public void setPases(List<Pase> pases) {
        this.pases = pases;
    }
    public void addTarifa(Tarifa t){
        this.tarifas.add(t);
    }
    public void removeTarifa(Tarifa t){
        this.tarifas.remove(t);
    }
    public void addPase(Pase p){
        this.pases.add(p);
    }
    public void removePase(Pase p){
        this.pases.remove(p);
    }
    @Override
    public String toString() {
        return "Cine [id=" + id + ", nombre=" + nombre + ", calle=" + calle + ", numero=" + numero + ", telefono="
                + telefono + ", tarifas=" + tarifas + ", pases=" + pases + "]";
    }

    
    
}
