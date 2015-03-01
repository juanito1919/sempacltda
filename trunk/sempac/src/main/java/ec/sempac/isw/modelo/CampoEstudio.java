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
@Table(name = "CAMPO_ESTUDIO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampoEstudio.findAll", query = "SELECT c FROM CampoEstudio c"),
    @NamedQuery(name = "CampoEstudio.findByIdCampoEstudio", query = "SELECT c FROM CampoEstudio c WHERE c.idCampoEstudio = :idCampoEstudio"),
    @NamedQuery(name = "CampoEstudio.findByNombre", query = "SELECT c FROM CampoEstudio c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CampoEstudio.findByEliminado", query = "SELECT c FROM CampoEstudio c WHERE c.eliminado = :eliminado")})
public class CampoEstudio implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNombre ="CampoEstudio.findByNombre";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CAMPO_ESTUDIO", nullable = false)
    private Integer idCampoEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(mappedBy = "idCampoEstudio")
    private List<EstudiosEspecializados> estudiosEspecializadosList;

    public CampoEstudio() {
    }

    public CampoEstudio(Integer idCampoEstudio) {
        this.idCampoEstudio = idCampoEstudio;
    }

    public CampoEstudio(Integer idCampoEstudio, String nombre, boolean eliminado) {
        this.idCampoEstudio = idCampoEstudio;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdCampoEstudio() {
        return idCampoEstudio;
    }

    public void setIdCampoEstudio(Integer idCampoEstudio) {
        this.idCampoEstudio = idCampoEstudio;
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
    public List<EstudiosEspecializados> getEstudiosEspecializadosList() {
        return estudiosEspecializadosList;
    }

    public void setEstudiosEspecializadosList(List<EstudiosEspecializados> estudiosEspecializadosList) {
        this.estudiosEspecializadosList = estudiosEspecializadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampoEstudio != null ? idCampoEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampoEstudio)) {
            return false;
        }
        CampoEstudio other = (CampoEstudio) object;
        if ((this.idCampoEstudio == null && other.idCampoEstudio != null) || (this.idCampoEstudio != null && !this.idCampoEstudio.equals(other.idCampoEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.CampoEstudio[ idCampoEstudio=" + idCampoEstudio + " ]";
    }
    
}
