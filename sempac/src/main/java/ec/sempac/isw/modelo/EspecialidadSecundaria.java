/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ESPECIALIDAD_SECUNDARIA", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadSecundaria.findAll", query = "SELECT e FROM EspecialidadSecundaria e"),
    @NamedQuery(name = "EspecialidadSecundaria.findByIdUsuario", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.especialidadSecundariaPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "EspecialidadSecundaria.findByIdColegio", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.especialidadSecundariaPK.idColegio = :idColegio"),
    @NamedQuery(name = "EspecialidadSecundaria.findByEspecialidad", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.especialidad = :especialidad"),
    @NamedQuery(name = "EspecialidadSecundaria.findByAniosEstudios", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.aniosEstudios = :aniosEstudios"),
    @NamedQuery(name = "EspecialidadSecundaria.findByUrl", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.url = :url"),
    @NamedQuery(name = "EspecialidadSecundaria.findByEliminado", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.eliminado = :eliminado")})
public class EspecialidadSecundaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EspecialidadSecundariaPK especialidadSecundariaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String especialidad;
    @Column(name = "ANIOS_ESTUDIOS")
    private Integer aniosEstudios;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Colegio colegio;

    public EspecialidadSecundaria() {
    }

    public EspecialidadSecundaria(EspecialidadSecundariaPK especialidadSecundariaPK) {
        this.especialidadSecundariaPK = especialidadSecundariaPK;
    }

    public EspecialidadSecundaria(EspecialidadSecundariaPK especialidadSecundariaPK, String especialidad, boolean eliminado) {
        this.especialidadSecundariaPK = especialidadSecundariaPK;
        this.especialidad = especialidad;
        this.eliminado = eliminado;
    }

    public EspecialidadSecundaria(long idUsuario, int idColegio) {
        this.especialidadSecundariaPK = new EspecialidadSecundariaPK(idUsuario, idColegio);
    }

    public EspecialidadSecundariaPK getEspecialidadSecundariaPK() {
        return especialidadSecundariaPK;
    }

    public void setEspecialidadSecundariaPK(EspecialidadSecundariaPK especialidadSecundariaPK) {
        this.especialidadSecundariaPK = especialidadSecundariaPK;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getAniosEstudios() {
        return aniosEstudios;
    }

    public void setAniosEstudios(Integer aniosEstudios) {
        this.aniosEstudios = aniosEstudios;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (especialidadSecundariaPK != null ? especialidadSecundariaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadSecundaria)) {
            return false;
        }
        EspecialidadSecundaria other = (EspecialidadSecundaria) object;
        if ((this.especialidadSecundariaPK == null && other.especialidadSecundariaPK != null) || (this.especialidadSecundariaPK != null && !this.especialidadSecundariaPK.equals(other.especialidadSecundariaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EspecialidadSecundaria[ especialidadSecundariaPK=" + especialidadSecundariaPK + " ]";
    }
    
}
