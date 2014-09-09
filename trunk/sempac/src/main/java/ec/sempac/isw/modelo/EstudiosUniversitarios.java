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
@Table(name = "ESTUDIOS_UNIVERSITARIOS", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudiosUniversitarios.findAll", query = "SELECT e FROM EstudiosUniversitarios e"),
    @NamedQuery(name = "EstudiosUniversitarios.findByIdUsuario", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.estudiosUniversitariosPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "EstudiosUniversitarios.findByIdUniversidad", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.estudiosUniversitariosPK.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "EstudiosUniversitarios.findByIdCarrera", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.estudiosUniversitariosPK.idCarrera = :idCarrera"),
    @NamedQuery(name = "EstudiosUniversitarios.findByTitulo", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "EstudiosUniversitarios.findByTiempoEstudios", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.tiempoEstudios = :tiempoEstudios"),
    @NamedQuery(name = "EstudiosUniversitarios.findByAnioEgresado", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.anioEgresado = :anioEgresado"),
    @NamedQuery(name = "EstudiosUniversitarios.findByUrl", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.url = :url"),
    @NamedQuery(name = "EstudiosUniversitarios.findByEliminado", query = "SELECT e FROM EstudiosUniversitarios e WHERE e.eliminado = :eliminado")})
public class EstudiosUniversitarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudiosUniversitariosPK estudiosUniversitariosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String titulo;
    @Column(name = "TIEMPO_ESTUDIOS")
    private Integer tiempoEstudios;
    @Column(name = "ANIO_EGRESADO")
    private Integer anioEgresado;
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
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Universidad universidad;
    @JoinColumn(name = "ID_CARRERA", referencedColumnName = "ID_CARRERA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carrera carrera;

    public EstudiosUniversitarios() {
    }

    public EstudiosUniversitarios(EstudiosUniversitariosPK estudiosUniversitariosPK) {
        this.estudiosUniversitariosPK = estudiosUniversitariosPK;
    }

    public EstudiosUniversitarios(EstudiosUniversitariosPK estudiosUniversitariosPK, String titulo, boolean eliminado) {
        this.estudiosUniversitariosPK = estudiosUniversitariosPK;
        this.titulo = titulo;
        this.eliminado = eliminado;
    }

    public EstudiosUniversitarios(long idUsuario, int idUniversidad, int idCarrera) {
        this.estudiosUniversitariosPK = new EstudiosUniversitariosPK(idUsuario, idUniversidad, idCarrera);
    }

    public EstudiosUniversitariosPK getEstudiosUniversitariosPK() {
        return estudiosUniversitariosPK;
    }

    public void setEstudiosUniversitariosPK(EstudiosUniversitariosPK estudiosUniversitariosPK) {
        this.estudiosUniversitariosPK = estudiosUniversitariosPK;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTiempoEstudios() {
        return tiempoEstudios;
    }

    public void setTiempoEstudios(Integer tiempoEstudios) {
        this.tiempoEstudios = tiempoEstudios;
    }

    public Integer getAnioEgresado() {
        return anioEgresado;
    }

    public void setAnioEgresado(Integer anioEgresado) {
        this.anioEgresado = anioEgresado;
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

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiosUniversitariosPK != null ? estudiosUniversitariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosUniversitarios)) {
            return false;
        }
        EstudiosUniversitarios other = (EstudiosUniversitarios) object;
        if ((this.estudiosUniversitariosPK == null && other.estudiosUniversitariosPK != null) || (this.estudiosUniversitariosPK != null && !this.estudiosUniversitariosPK.equals(other.estudiosUniversitariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EstudiosUniversitarios[ estudiosUniversitariosPK=" + estudiosUniversitariosPK + " ]";
    }
    
}
