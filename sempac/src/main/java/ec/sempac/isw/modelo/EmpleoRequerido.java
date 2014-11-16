/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "EMPLEO_REQUERIDO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleoRequerido.findAll", query = "SELECT e FROM EmpleoRequerido e"),
    @NamedQuery(name = "EmpleoRequerido.findByIdEmpleoRequerido", query = "SELECT e FROM EmpleoRequerido e WHERE e.idEmpleoRequerido = :idEmpleoRequerido"),
    @NamedQuery(name = "EmpleoRequerido.findByNombreEmpleoRequerido", query = "SELECT e FROM EmpleoRequerido e WHERE e.nombreEmpleoRequerido = :nombreEmpleoRequerido"),
    @NamedQuery(name = "EmpleoRequerido.findByActivo", query = "SELECT e FROM EmpleoRequerido e WHERE e.activo = :activo"),
    @NamedQuery(name = "EmpleoRequerido.findByFechaModificacion", query = "SELECT e FROM EmpleoRequerido e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "EmpleoRequerido.findByEliminado", query = "SELECT e FROM EmpleoRequerido e WHERE e.eliminado = :eliminado")})
public class EmpleoRequerido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMPLEO_REQUERIDO", nullable = false)
    private Long idEmpleoRequerido;
    @Size(max = 64)
    @Column(name = "NOMBRE_EMPLEO_REQUERIDO", length = 64)
    private String nombreEmpleoRequerido;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_PERSONAL_REQUERIDO", referencedColumnName = "ID_PERSONAL_REQUERIDO", nullable = false)
    @ManyToOne(optional = false)
    private PersonalRequerido idPersonalRequerido;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleoRequerido")
    private List<UbicacionEmpleo> ubicacionEmpleoList;

    public EmpleoRequerido() {
    }

    public EmpleoRequerido(Long idEmpleoRequerido) {
        this.idEmpleoRequerido = idEmpleoRequerido;
    }

    public EmpleoRequerido(Long idEmpleoRequerido, boolean activo, Date fechaModificacion, boolean eliminado) {
        this.idEmpleoRequerido = idEmpleoRequerido;
        this.activo = activo;
        this.fechaModificacion = fechaModificacion;
        this.eliminado = eliminado;
    }

    public Long getIdEmpleoRequerido() {
        return idEmpleoRequerido;
    }

    public void setIdEmpleoRequerido(Long idEmpleoRequerido) {
        this.idEmpleoRequerido = idEmpleoRequerido;
    }

    public String getNombreEmpleoRequerido() {
        return nombreEmpleoRequerido;
    }

    public void setNombreEmpleoRequerido(String nombreEmpleoRequerido) {
        this.nombreEmpleoRequerido = nombreEmpleoRequerido;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public PersonalRequerido getIdPersonalRequerido() {
        return idPersonalRequerido;
    }

    public void setIdPersonalRequerido(PersonalRequerido idPersonalRequerido) {
        this.idPersonalRequerido = idPersonalRequerido;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @XmlTransient
    public List<UbicacionEmpleo> getUbicacionEmpleoList() {
        return ubicacionEmpleoList;
    }

    public void setUbicacionEmpleoList(List<UbicacionEmpleo> ubicacionEmpleoList) {
        this.ubicacionEmpleoList = ubicacionEmpleoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleoRequerido != null ? idEmpleoRequerido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleoRequerido)) {
            return false;
        }
        EmpleoRequerido other = (EmpleoRequerido) object;
        if ((this.idEmpleoRequerido == null && other.idEmpleoRequerido != null) || (this.idEmpleoRequerido != null && !this.idEmpleoRequerido.equals(other.idEmpleoRequerido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EmpleoRequerido[ idEmpleoRequerido=" + idEmpleoRequerido + " ]";
    }
    
}
