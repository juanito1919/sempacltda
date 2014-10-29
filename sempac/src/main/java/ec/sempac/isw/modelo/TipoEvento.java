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
@Table(name = "TIPO_EVENTO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEvento.findAll", query = "SELECT t FROM TipoEvento t"),
    @NamedQuery(name = "TipoEvento.findByIdTipoEvento", query = "SELECT t FROM TipoEvento t WHERE t.idTipoEvento = :idTipoEvento"),
    @NamedQuery(name = "TipoEvento.findByNombre", query = "SELECT t FROM TipoEvento t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoEvento.findByEliminado", query = "SELECT t FROM TipoEvento t WHERE t.eliminado = :eliminado")})
public class TipoEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="TipoEvento.findByEliminado"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_EVENTO", nullable = false)
    private Integer idTipoEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idTipoEvento")
    private List<Capacitaciones> capacitacionesList;
    @OneToMany(mappedBy = "idTipoEvento")
    private List<Meritos> meritosList;

    public TipoEvento() {
    }

    public TipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public TipoEvento(Integer idTipoEvento, String nombre, boolean eliminado) {
        this.idTipoEvento = idTipoEvento;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
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

    @XmlTransient
    public List<Meritos> getMeritosList() {
        return meritosList;
    }

    public void setMeritosList(List<Meritos> meritosList) {
        this.meritosList = meritosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEvento != null ? idTipoEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvento)) {
            return false;
        }
        TipoEvento other = (TipoEvento) object;
        if ((this.idTipoEvento == null && other.idTipoEvento != null) || (this.idTipoEvento != null && !this.idTipoEvento.equals(other.idTipoEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.TipoEvento[ idTipoEvento=" + idTipoEvento + " ]";
    }
    
}
