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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "especialidad_secundaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadSecundaria.findAll", query = "SELECT e FROM EspecialidadSecundaria e"),
    @NamedQuery(name = "EspecialidadSecundaria.findByIdUsuario", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.idUsuario = :idUsuario"),
    @NamedQuery(name = "EspecialidadSecundaria.findByEspecialidad", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.especialidad = :especialidad"),
    @NamedQuery(name = "EspecialidadSecundaria.findByAniosEstudios", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.aniosEstudios = :aniosEstudios"),
    @NamedQuery(name = "EspecialidadSecundaria.findByUrl", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.url = :url"),
    @NamedQuery(name = "EspecialidadSecundaria.findByEliminado", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.eliminado = :eliminado"),
//Personalizados
    @NamedQuery(name = "EspecialidadSecundaria.findByIdUsuarioEliminado", query = "SELECT e FROM EspecialidadSecundaria e WHERE e.idUsuario = :idUsuario AND e.eliminado = :eliminado")
})
public class EspecialidadSecundaria implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIdUsuarioEliminado="EspecialidadSecundaria.findByIdUsuarioEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "ESPECIALIDAD")
    private String especialidad;
    @Column(name = "ANIOS_ESTUDIOS")
    private Integer aniosEstudios;
    @Size(max = 128)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private boolean eliminado;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio colegio;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public EspecialidadSecundaria() {
    }

    public EspecialidadSecundaria(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EspecialidadSecundaria(Long idUsuario, String especialidad, boolean eliminado) {
        this.idUsuario = idUsuario;
        this.especialidad = especialidad;
        this.eliminado = eliminado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio idColegio) {
        this.colegio = idColegio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

        @Override
        public boolean equals(Object object) {
            // TODO: Warning - this method won't work in the case the id fields are not set
            if (!(object instanceof EspecialidadSecundaria)) {
                return false;
            }
            EspecialidadSecundaria other = (EspecialidadSecundaria) object;
            if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
                return false;
            }
            return true;
        }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.prueba.EspecialidadSecundaria[ idUsuario=" + idUsuario + " ]";
    }
    
}
