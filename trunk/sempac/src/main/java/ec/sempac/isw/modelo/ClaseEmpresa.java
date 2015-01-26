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
@Table(name = "CLASE_EMPRESA", catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaseEmpresa.findAll", query = "SELECT c FROM ClaseEmpresa c"),
    @NamedQuery(name = "ClaseEmpresa.findByIdClaseEmpresa", query = "SELECT c FROM ClaseEmpresa c WHERE c.idClaseEmpresa = :idClaseEmpresa"),
    @NamedQuery(name = "ClaseEmpresa.findByNombre", query = "SELECT c FROM ClaseEmpresa c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClaseEmpresa.findByEliminado", query = "SELECT c FROM ClaseEmpresa c WHERE c.eliminado = :eliminado")})
public class ClaseEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNombre ="ClaseEmpresa.findByNombre";
    public static final String findByEliminado ="ClaseEmpresa.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CLASE_EMPRESA", nullable = false)
    private Integer idClaseEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idClaseEmpresa")
    private List<Empresa> empresaList;

    public ClaseEmpresa() {
    }

    public ClaseEmpresa(Integer idClaseEmpresa) {
        this.idClaseEmpresa = idClaseEmpresa;
    }

    public ClaseEmpresa(Integer idClaseEmpresa, String nombre, boolean eliminado) {
        this.idClaseEmpresa = idClaseEmpresa;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdClaseEmpresa() {
        return idClaseEmpresa;
    }

    public void setIdClaseEmpresa(Integer idClaseEmpresa) {
        this.idClaseEmpresa = idClaseEmpresa;
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
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClaseEmpresa != null ? idClaseEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseEmpresa)) {
            return false;
        }
        ClaseEmpresa other = (ClaseEmpresa) object;
        if ((this.idClaseEmpresa == null && other.idClaseEmpresa != null) || (this.idClaseEmpresa != null && !this.idClaseEmpresa.equals(other.idClaseEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.ClaseEmpresa[ idClaseEmpresa=" + idClaseEmpresa + " ]";
    }
    
}
