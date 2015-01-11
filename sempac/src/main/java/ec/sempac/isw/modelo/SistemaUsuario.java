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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "SISTEMA_USUARIO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaUsuario.findAll", query = "SELECT s FROM SistemaUsuario s"),
    @NamedQuery(name = "SistemaUsuario.findByIdUsuario", query = "SELECT s FROM SistemaUsuario s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SistemaUsuario.findByFechaAsignacion", query = "SELECT s FROM SistemaUsuario s WHERE s.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "SistemaUsuario.findByFechaCaducidad", query = "SELECT s FROM SistemaUsuario s WHERE s.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "SistemaUsuario.findByFechaCambioContrasena", query = "SELECT s FROM SistemaUsuario s WHERE s.fechaCambioContrasena = :fechaCambioContrasena"),
    @NamedQuery(name = "SistemaUsuario.findByFechaBloqueo", query = "SELECT s FROM SistemaUsuario s WHERE s.fechaBloqueo = :fechaBloqueo"),
    @NamedQuery(name = "SistemaUsuario.findByEstado", query = "SELECT s FROM SistemaUsuario s WHERE s.estado = :estado"),
    @NamedQuery(name = "SistemaUsuario.findByTiempoBloqueo", query = "SELECT s FROM SistemaUsuario s WHERE s.tiempoBloqueo = :tiempoBloqueo"),
    @NamedQuery(name = "SistemaUsuario.findByIntentosFallidos", query = "SELECT s FROM SistemaUsuario s WHERE s.intentosFallidos = :intentosFallidos"),
    @NamedQuery(name = "SistemaUsuario.findByFechaUltimoIntentoFallido", query = "SELECT s FROM SistemaUsuario s WHERE s.fechaUltimoIntentoFallido = :fechaUltimoIntentoFallido"),
    
    @NamedQuery(name = "SistemaUsuario.findByEstadoPago", query = "SELECT s.usuario FROM SistemaUsuario s WHERE s.idUsuario = :idUsuario AND s.estado = :estado")
    
})
public class SistemaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEstadoPago ="SistemaUsuario.findByEstadoPago";
    public static final String findByIdUsuario ="SistemaUsuario.findByIdUsuario";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ASIGNACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @Column(name = "FECHA_CAMBIO_CONTRASENA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioContrasena;
    @Column(name = "FECHA_BLOQUEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBloqueo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_BLOQUEO", nullable = false)
    private int tiempoBloqueo;
    @Column(name = "INTENTOS_FALLIDOS")
    private Short intentosFallidos;
    @Column(name = "FECHA_ULTIMO_INTENTO_FALLIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoIntentoFallido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<SistemaAcceso> sistemaAccesoList;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public SistemaUsuario() {
    }

    public SistemaUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SistemaUsuario(Long idUsuario, Date fechaAsignacion, Character estado, int tiempoBloqueo) {
        this.idUsuario = idUsuario;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = estado;
        this.tiempoBloqueo = tiempoBloqueo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaCambioContrasena() {
        return fechaCambioContrasena;
    }

    public void setFechaCambioContrasena(Date fechaCambioContrasena) {
        this.fechaCambioContrasena = fechaCambioContrasena;
    }

    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public int getTiempoBloqueo() {
        return tiempoBloqueo;
    }

    public void setTiempoBloqueo(int tiempoBloqueo) {
        this.tiempoBloqueo = tiempoBloqueo;
    }

    public Short getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Short intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public Date getFechaUltimoIntentoFallido() {
        return fechaUltimoIntentoFallido;
    }

    public void setFechaUltimoIntentoFallido(Date fechaUltimoIntentoFallido) {
        this.fechaUltimoIntentoFallido = fechaUltimoIntentoFallido;
    }

    @XmlTransient
    public List<SistemaAcceso> getSistemaAccesoList() {
        return sistemaAccesoList;
    }

    public void setSistemaAccesoList(List<SistemaAcceso> sistemaAccesoList) {
        this.sistemaAccesoList = sistemaAccesoList;
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
        if (!(object instanceof SistemaUsuario)) {
            return false;
        }
        SistemaUsuario other = (SistemaUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.ssw.modelo.SistemaUsuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
