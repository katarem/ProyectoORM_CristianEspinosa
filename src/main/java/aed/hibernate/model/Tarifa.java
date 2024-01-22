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
@Table(name = "Tarifa")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tarifa_id",length = 10)
    private int idTarifa;
    @Column(name = "tarifa_dia",length = 10)
    private String dia;
    @Column(name = "tarifa_precio",length = 10)
    private Double precio;
    @ManyToOne
    @JoinColumn(name = "cine_nombre")
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idTarifa;
        result = prime * result + ((dia == null) ? 0 : dia.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
        return true;
    }

    @Override
    public String toString() {
        return "Tarifa [idTarifa=" + idTarifa + ", dia=" + dia + ", precio=" + precio + "]";
    }
    
}
