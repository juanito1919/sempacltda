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
@Table(name = "TIPO_ESTUDIO", catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_TIPO_ESTUDIO"}),
    @UniqueConstraint(columnNames = {"DESCRIPCION"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstudio.findAll", query = "SELECT t FROM TipoEstudio t"),
    @NamedQuery(name = "TipoEstudio.findByIdTipoEstudio", query = "SELECT t FROM TipoEstudio t WHERE t.idTipoEstudio = :idTipoEstudio"),
    @NamedQuery(name = "TipoEstudio.findByDescripcion", query = "SELECT t FROM TipoEstudio t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoEstudio.findByEliminado", query = "SELECT t FROM TipoEstudio t WHERE t.eliminado = :eliminado")})
public class TipoEstudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ESTUDIO", nullable = false)
    private Integer idTipoEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEstudio")
    private List<EstudiosEspecializados> estudiosEspecializadosList;

    public TipoEstudio() {
    }

    public TipoEstudio(Integer idTipoEstudio) {
        this.idTipoEstudio = idTipoEstudio;
    }

    public TipoEstudio(Integer idTipoEstudio, String descripcion, boolean eliminado) {
        this.idTipoEstudio = idTipoEstudio;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Integer getIdTipoEstudio() {
        return idTipoEstudio;
    }

    public void setIdTipoEstudio(Integer idTipoEstudio) {
        this.idTipoEstudio = idTipoEstudio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<EstudiosEspecializados> getEstudiosEspecializadosList() {
        return estudiosEspecializadosList;
    }

    public void setEstudiosEspecializadosList(List<EstudiosEspecializados> estudiosEspecializadosList) {
        this.estudiosEspecializadosList = estudiosEspecializadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEstudio != null ? idTipoEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstudio)) {
            return false;
        }
        TipoEstudio other = (TipoEstudio) object;
        if ((this.idTipoEstudio == null && other.idTipoEstudio != null) || (this.idTipoEstudio != null && !this.idTipoEstudio.equals(other.idTipoEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.TipoEstudio[ idTipoEstudio=" + idTipoEstudio + " ]";
    }
    
}
