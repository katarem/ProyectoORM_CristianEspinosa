package aed.hibernate.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cine")
public class Cine {
    
    @Id
    @Column(name = "cine_nombre")
    private String nombre;
    @Column(name = "cine_calle")
    private String calle;
    @Column(name = "cine_numero")
    private int numero;
    @Column(name = "cine_telefono")
    private int telefono;
    @Column(name = "cine_tarifas")
    private List<Tarifa> tarifas;
    
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((calle == null) ? 0 : calle.hashCode());
        result = prime * result + numero;
        result = prime * result + telefono;
        result = prime * result + ((tarifas == null) ? 0 : tarifas.hashCode());
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
        Cine other = (Cine) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (calle == null) {
            if (other.calle != null)
                return false;
        } else if (!calle.equals(other.calle))
            return false;
        if (numero != other.numero)
            return false;
        if (telefono != other.telefono)
            return false;
        if (tarifas == null) {
            if (other.tarifas != null)
                return false;
        } else if (!tarifas.equals(other.tarifas))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Cine [nombre=" + nombre + ", calle=" + calle + ", numero=" + numero + ", telefono=" + telefono
                + ", tarifas=" + tarifas + "]";
    }

    
    
}
