/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_COLEGIO"}),
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c"),
    @NamedQuery(name = "Colegio.findByIdColegio", query = "SELECT c FROM Colegio c WHERE c.idColegio = :idColegio"),
    @NamedQuery(name = "Colegio.findByNombre", query = "SELECT c FROM Colegio c WHERE c.nombre = :nombre AND c.eliminado = :eliminado"),
    @NamedQuery(name = "Colegio.findByEliminado", query = "SELECT c FROM Colegio c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "Colegio.findByAutoCompletado", query = "SELECT c FROM Colegio c WHERE c.nombre like :nombre and c.eliminado = :eliminado ORDER BY c.nombre")
   

})
    
public class Colegio implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="Colegio.findByEliminado";
    public static final String findByNombre ="Colegio.findByNombre";
    public static final String findByAutoCompletado ="Colegio.findByAutoCompletado";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COLEGIO", nullable = false)
    private Integer idColegio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;

    public Colegio() {
    }

    public Colegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public Colegio(Integer idColegio, String nombre, boolean eliminado) {
        this.idColegio = idColegio;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Integer idColegio) {
        this.idColegio = idColegio;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColegio != null ? idColegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idColegio == null && other.idColegio != null) || (this.idColegio != null && !this.idColegio.equals(other.idColegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Colegio[ idColegio=" + idColegio + " ]";
    }
    
}
