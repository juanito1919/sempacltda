/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "AREA_CAPACITACION", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaCapacitacion.findAll", query = "SELECT a FROM AreaCapacitacion a"),
    @NamedQuery(name = "AreaCapacitacion.findByIdAreaCapacitacion", query = "SELECT a FROM AreaCapacitacion a WHERE a.idAreaCapacitacion = :idAreaCapacitacion"),
    @NamedQuery(name = "AreaCapacitacion.findByNombre", query = "SELECT a FROM AreaCapacitacion a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AreaCapacitacion.findByEliminado", query = "SELECT a FROM AreaCapacitacion a WHERE a.eliminado = :eliminado")})
public class AreaCapacitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_AREA_CAPACITACION", nullable = false)
    private Integer idAreaCapacitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idAreaCapacitacion")
    private List<Capacitaciones> capacitacionesList;

    public AreaCapacitacion() {
    }

    public AreaCapacitacion(Integer idAreaCapacitacion) {
        this.idAreaCapacitacion = idAreaCapacitacion;
    }

    public AreaCapacitacion(Integer idAreaCapacitacion, String nombre, boolean eliminado) {
        this.idAreaCapacitacion = idAreaCapacitacion;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdAreaCapacitacion() {
        return idAreaCapacitacion;
    }

    public void setIdAreaCapacitacion(Integer idAreaCapacitacion) {
        this.idAreaCapacitacion = idAreaCapacitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Capacitaciones> getCapacitacionesList() {
        return capacitacionesList;
    }

    public void setCapacitacionesList(List<Capacitaciones> capacitacionesList) {
        this.capacitacionesList = capacitacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaCapacitacion != null ? idAreaCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaCapacitacion)) {
            return false;
        }
        AreaCapacitacion other = (AreaCapacitacion) object;
        if ((this.idAreaCapacitacion == null && other.idAreaCapacitacion != null) || (this.idAreaCapacitacion != null && !this.idAreaCapacitacion.equals(other.idAreaCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.AreaCapacitacion[ idAreaCapacitacion=" + idAreaCapacitacion + " ]";
    }
    
}
