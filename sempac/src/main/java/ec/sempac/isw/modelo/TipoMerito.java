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
@Table(name = "TIPO_MERITO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMerito.findAll", query = "SELECT t FROM TipoMerito t"),
    @NamedQuery(name = "TipoMerito.findByIdTipoMerito", query = "SELECT t FROM TipoMerito t WHERE t.idTipoMerito = :idTipoMerito"),
    @NamedQuery(name = "TipoMerito.findByNombre", query = "SELECT t FROM TipoMerito t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMerito.findByEliminado", query = "SELECT t FROM TipoMerito t WHERE t.eliminado = :eliminado")})
public class TipoMerito implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="TipoMerito.findByEliminado"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_MERITO", nullable = false)
    private Integer idTipoMerito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idTipoMerito")
    private List<Meritos> meritosList;

    public TipoMerito() {
    }

    public TipoMerito(Integer idTipoMerito) {
        this.idTipoMerito = idTipoMerito;
    }

    public TipoMerito(Integer idTipoMerito, String nombre, boolean eliminado) {
        this.idTipoMerito = idTipoMerito;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoMerito() {
        return idTipoMerito;
    }

    public void setIdTipoMerito(Integer idTipoMerito) {
        this.idTipoMerito = idTipoMerito;
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
    public List<Meritos> getMeritosList() {
        return meritosList;
    }

    public void setMeritosList(List<Meritos> meritosList) {
        this.meritosList = meritosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMerito != null ? idTipoMerito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMerito)) {
            return false;
        }
        TipoMerito other = (TipoMerito) object;
        if ((this.idTipoMerito == null && other.idTipoMerito != null) || (this.idTipoMerito != null && !this.idTipoMerito.equals(other.idTipoMerito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.TipoMerito[ idTipoMerito=" + idTipoMerito + " ]";
    }
    
}
