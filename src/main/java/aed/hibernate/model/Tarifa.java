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
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tarifa_id",length = 10)
    private int idTarifa;
    @Column(name = "dia",length = 10)
    private String dia;
    @Column(name = "precio",length = 10)
    private Double precio;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cine_id")
    private Cine cine;
    
    public int getIdTarifa() {
        return idTarifa;
    }
    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idTarifa;
        result = prime * result + ((dia == null) ? 0 : dia.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + ((cine == null) ? 0 : cine.hashCode());
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
        Tarifa other = (Tarifa) obj;
        if (idTarifa != other.idTarifa)
            return false;
        if (dia == null) {
            if (other.dia != null)
                return false;
        } else if (!dia.equals(other.dia))
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (cine == null) {
            if (other.cine != null)
                return false;
        } else if (!cine.equals(other.cine))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Tarifa [dia=" + dia + ", precio=" + precio + "]";
    }
    
    
}
