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
@Table(name = "tarifa")
public class Tarifa{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tarifa_id",length = 10)
    private int id;
    @Column(name = "tarifa_dia",length = 10)
    private String dia;
    @Column(name = "tarifa_precio",length = 10)
    private Double precio;
    @ManyToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;
    
    public Tarifa() {}

    public Tarifa(int id) {this.id = id;}
    
    public Tarifa(String dia, Double precio, Cine cine) {
		super();
		this.dia = dia;
		this.precio = precio;
		this.cine = cine;
	}

	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Cine getCine() {
        return cine;
    }
    public void setCine(Cine cine) {
        this.cine = cine;
    }   
    
    @Override
    public boolean equals(Object o) {
    	if(!(o instanceof Tarifa)) return false;
    	Tarifa other = (Tarifa) o;
    	if(!(other.getId() == this.getId())) return false;
    	return true;
    }
    
}
