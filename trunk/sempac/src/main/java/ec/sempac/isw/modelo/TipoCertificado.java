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
@Table(name = "TIPO_CERTIFICADO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCertificado.findAll", query = "SELECT t FROM TipoCertificado t"),
    @NamedQuery(name = "TipoCertificado.findByIdTipoCertificado", query = "SELECT t FROM TipoCertificado t WHERE t.idTipoCertificado = :idTipoCertificado"),
    @NamedQuery(name = "TipoCertificado.findByNombre", query = "SELECT t FROM TipoCertificado t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCertificado.findByEliminado", query = "SELECT t FROM TipoCertificado t WHERE t.eliminado = :eliminado")})
public class TipoCertificado implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="TipoCertificado.findByEliminado"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_CERTIFICADO", nullable = false)
    private Integer idTipoCertificado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idTipoCertificado")
    private List<Capacitaciones> capacitacionesList;
    @OneToMany(mappedBy = "idTipoCertificado")
    private List<Meritos> meritosList;

    public TipoCertificado() {
    }

    public TipoCertificado(Integer idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public TipoCertificado(Integer idTipoCertificado, String nombre, boolean eliminado) {
        this.idTipoCertificado = idTipoCertificado;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoCertificado() {
        return idTipoCertificado;
    }

    public void setIdTipoCertificado(Integer idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
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
        hash += (idTipoCertificado != null ? idTipoCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCertificado)) {
            return false;
        }
        TipoCertificado other = (TipoCertificado) object;
        if ((this.idTipoCertificado == null && other.idTipoCertificado != null) || (this.idTipoCertificado != null && !this.idTipoCertificado.equals(other.idTipoCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.TipoCertificado[ idTipoCertificado=" + idTipoCertificado + " ]";
    }
    
}
