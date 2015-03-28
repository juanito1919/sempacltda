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
@Table(name = "ESTUDIOS_PRIMARIA", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudiosPrimaria.findAll", query = "SELECT e FROM EstudiosPrimaria e"),
    @NamedQuery(name = "EstudiosPrimaria.findByIdUsuario", query = "SELECT e FROM EstudiosPrimaria e WHERE e.idUsuario = :idUsuario"),
    @NamedQuery(name = "EstudiosPrimaria.findByIdEscuela", query = "SELECT e FROM EstudiosPrimaria e WHERE e.escuela.idEscuela = :idEscuela"),
    @NamedQuery(name = "EstudiosPrimaria.findByDuracion", query = "SELECT e FROM EstudiosPrimaria e WHERE e.duracion = :duracion"),
    @NamedQuery(name = "EstudiosPrimaria.findByEliminado", query = "SELECT e FROM EstudiosPrimaria e WHERE e.eliminado = :eliminado"),
    @NamedQuery(name = "EstudiosPrimaria.findByIdUsuarioEliminado", query = "SELECT e FROM EstudiosPrimaria e WHERE e.idUsuario = :idUsuario AND e.eliminado = :eliminado")
})
public class EstudiosPrimaria implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIdUsuarioEliminado = "EstudiosPrimaria.findByIdUsuarioEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Size(max = 32)
    @Column(length = 32)
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_ESCUELA", referencedColumnName = "ID_ESCUELA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Escuela escuela;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public EstudiosPrimaria() {
    }

    public EstudiosPrimaria(Long idUsuario) {
        this.idUsuario=idUsuario;
    }

    public EstudiosPrimaria(Long idUsuario,String duracion, boolean eliminado) {
        this.duracion=duracion;
        this.idUsuario=idUsuario;
        this.eliminado = eliminado;
    }

 

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
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
        hash += (getIdUsuario() != null ? getIdUsuario().hashCode() : 0);
        return hash;
    }

       @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof  EstudiosPrimaria)) {
            return false;
        }
        EstudiosPrimaria other = (EstudiosPrimaria) object;
        if ((this.getIdUsuario() == null && other.getIdUsuario() != null) || (this.getIdUsuario() != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EstudiosPrimaria[ estudiosPrimariaPK=" + getIdUsuario()+ " ]";
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}
