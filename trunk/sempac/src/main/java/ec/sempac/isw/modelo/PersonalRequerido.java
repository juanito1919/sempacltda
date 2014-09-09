/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "PERSONAL_REQUERIDO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalRequerido.findAll", query = "SELECT p FROM PersonalRequerido p"),
    @NamedQuery(name = "PersonalRequerido.findByIdPersonalRequerido", query = "SELECT p FROM PersonalRequerido p WHERE p.idPersonalRequerido = :idPersonalRequerido"),
    @NamedQuery(name = "PersonalRequerido.findByDescripcion", query = "SELECT p FROM PersonalRequerido p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PersonalRequerido.findByActivo", query = "SELECT p FROM PersonalRequerido p WHERE p.activo = :activo"),
    @NamedQuery(name = "PersonalRequerido.findByFechaModificacion", query = "SELECT p FROM PersonalRequerido p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "PersonalRequerido.findByCoorLongitud", query = "SELECT p FROM PersonalRequerido p WHERE p.coorLongitud = :coorLongitud"),
    @NamedQuery(name = "PersonalRequerido.findByCoorLatitud", query = "SELECT p FROM PersonalRequerido p WHERE p.coorLatitud = :coorLatitud"),
    @NamedQuery(name = "PersonalRequerido.findByEliminado", query = "SELECT p FROM PersonalRequerido p WHERE p.eliminado = :eliminado")})
public class PersonalRequerido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSONAL_REQUERIDO", nullable = false)
    private Long idPersonalRequerido;
    @Size(max = 64)
    @Column(length = 64)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "COOR_LONGITUD")
    private Integer coorLongitud;
    @Column(name = "COOR_LATITUD")
    private Integer coorLatitud;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false)
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
    @JoinColumn(name = "ID_EMPLEO_REQUERIDO", referencedColumnName = "ID_EMPLEO_REQUERIDO")
    @ManyToOne
    private EmpleoRequerido idEmpleoRequerido;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD")
    @ManyToOne
    private Ciudad idCiudad;
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idAdministrador;
    @JoinColumn(name = "ID_HABILIDADES", referencedColumnName = "ID_HABILIDADES")
    @ManyToOne
    private Habilidades idHabilidades;

    public PersonalRequerido() {
    }

    public PersonalRequerido(Long idPersonalRequerido) {
        this.idPersonalRequerido = idPersonalRequerido;
    }

    public PersonalRequerido(Long idPersonalRequerido, boolean activo, Date fechaModificacion, boolean eliminado) {
        this.idPersonalRequerido = idPersonalRequerido;
        this.activo = activo;
        this.fechaModificacion = fechaModificacion;
        this.eliminado = eliminado;
    }

    public Long getIdPersonalRequerido() {
        return idPersonalRequerido;
    }

    public void setIdPersonalRequerido(Long idPersonalRequerido) {
        this.idPersonalRequerido = idPersonalRequerido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getCoorLongitud() {
        return coorLongitud;
    }

    public void setCoorLongitud(Integer coorLongitud) {
        this.coorLongitud = coorLongitud;
    }

    public Integer getCoorLatitud() {
        return coorLatitud;
    }

    public void setCoorLatitud(Integer coorLatitud) {
        this.coorLatitud = coorLatitud;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public EmpleoRequerido getIdEmpleoRequerido() {
        return idEmpleoRequerido;
    }

    public void setIdEmpleoRequerido(EmpleoRequerido idEmpleoRequerido) {
        this.idEmpleoRequerido = idEmpleoRequerido;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Habilidades getIdHabilidades() {
        return idHabilidades;
    }

    public void setIdHabilidades(Habilidades idHabilidades) {
        this.idHabilidades = idHabilidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonalRequerido != null ? idPersonalRequerido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalRequerido)) {
            return false;
        }
        PersonalRequerido other = (PersonalRequerido) object;
        if ((this.idPersonalRequerido == null && other.idPersonalRequerido != null) || (this.idPersonalRequerido != null && !this.idPersonalRequerido.equals(other.idPersonalRequerido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.PersonalRequerido[ idPersonalRequerido=" + idPersonalRequerido + " ]";
    }
    
}
