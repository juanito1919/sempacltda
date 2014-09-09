/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "ESTUDIOS_ESPECIALIZADOS", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudiosEspecializados.findAll", query = "SELECT e FROM EstudiosEspecializados e"),
    @NamedQuery(name = "EstudiosEspecializados.findByIdEstudiosEspecializados", query = "SELECT e FROM EstudiosEspecializados e WHERE e.idEstudiosEspecializados = :idEstudiosEspecializados"),
    @NamedQuery(name = "EstudiosEspecializados.findByTitulo", query = "SELECT e FROM EstudiosEspecializados e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "EstudiosEspecializados.findByUrl", query = "SELECT e FROM EstudiosEspecializados e WHERE e.url = :url"),
    @NamedQuery(name = "EstudiosEspecializados.findByEliminado", query = "SELECT e FROM EstudiosEspecializados e WHERE e.eliminado = :eliminado")})
public class EstudiosEspecializados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ ESTUDIOS_ESPECIALIZADOS", nullable = false)
    private Long idEstudiosEspecializados;
    @Size(max = 64)
    @Column(length = 64)
    private String titulo;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_TIPO_ESTUDIO", referencedColumnName = "ID_TIPO_ESTUDIO", nullable = false)
    @ManyToOne(optional = false)
    private TipoEstudio idTipoEstudio;
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD", nullable = false)
    @ManyToOne(optional = false)
    private Universidad idUniversidad;
    @JoinColumn(name = "ID_CAMPO_ESTUDIO", referencedColumnName = "ID_CAMPO_ESTUDIO")
    @ManyToOne
    private CampoEstudio idCampoEstudio;

    public EstudiosEspecializados() {
    }

    public EstudiosEspecializados(Long idEstudiosEspecializados) {
        this.idEstudiosEspecializados = idEstudiosEspecializados;
    }

    public EstudiosEspecializados(Long idEstudiosEspecializados, boolean eliminado) {
        this.idEstudiosEspecializados = idEstudiosEspecializados;
        this.eliminado = eliminado;
    }

    public Long getIdEstudiosEspecializados() {
        return idEstudiosEspecializados;
    }

    public void setIdEstudiosEspecializados(Long idEstudiosEspecializados) {
        this.idEstudiosEspecializados = idEstudiosEspecializados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoEstudio getIdTipoEstudio() {
        return idTipoEstudio;
    }

    public void setIdTipoEstudio(TipoEstudio idTipoEstudio) {
        this.idTipoEstudio = idTipoEstudio;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public CampoEstudio getIdCampoEstudio() {
        return idCampoEstudio;
    }

    public void setIdCampoEstudio(CampoEstudio idCampoEstudio) {
        this.idCampoEstudio = idCampoEstudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiosEspecializados != null ? idEstudiosEspecializados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosEspecializados)) {
            return false;
        }
        EstudiosEspecializados other = (EstudiosEspecializados) object;
        if ((this.idEstudiosEspecializados == null && other.idEstudiosEspecializados != null) || (this.idEstudiosEspecializados != null && !this.idEstudiosEspecializados.equals(other.idEstudiosEspecializados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EstudiosEspecializados[ idEstudiosEspecializados=" + idEstudiosEspecializados + " ]";
    }
    
}
