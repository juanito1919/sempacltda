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
@Table(name = "SISTEMA_EMPRESA", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaEmpresa.findAll", query = "SELECT s FROM SistemaEmpresa s"),
    @NamedQuery(name = "SistemaEmpresa.findByIdEmpresa", query = "SELECT s FROM SistemaEmpresa s WHERE s.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "SistemaEmpresa.findByFechaAsignacion", query = "SELECT s FROM SistemaEmpresa s WHERE s.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "SistemaEmpresa.findByFechaCaducidad", query = "SELECT s FROM SistemaEmpresa s WHERE s.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "SistemaEmpresa.findByFechaCambioContrasena", query = "SELECT s FROM SistemaEmpresa s WHERE s.fechaCambioContrasena = :fechaCambioContrasena"),
    @NamedQuery(name = "SistemaEmpresa.findByFechaBloqueo", query = "SELECT s FROM SistemaEmpresa s WHERE s.fechaBloqueo = :fechaBloqueo"),
    @NamedQuery(name = "SistemaEmpresa.findByEstado", query = "SELECT s FROM SistemaEmpresa s WHERE s.estado = :estado"),
    @NamedQuery(name = "SistemaEmpresa.findByTiempoBloqueo", query = "SELECT s FROM SistemaEmpresa s WHERE s.tiempoBloqueo = :tiempoBloqueo"),
    @NamedQuery(name = "SistemaEmpresa.findByIntentosFallidos", query = "SELECT s FROM SistemaEmpresa s WHERE s.intentosFallidos = :intentosFallidos"),
    @NamedQuery(name = "SistemaEmpresa.findByFechaUltimoIntentoFallido", query = "SELECT s FROM SistemaEmpresa s WHERE s.fechaUltimoIntentoFallido = :fechaUltimoIntentoFallido")})
public class SistemaEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPRESA", nullable = false)
    private Integer idEmpresa;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<SistemaAccesoEmpresa> sistemaAccesoEmpresaList;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empresa empresa;

    public SistemaEmpresa() {
    }

    public SistemaEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SistemaEmpresa(Integer idEmpresa, Date fechaAsignacion, Character estado, int tiempoBloqueo) {
        this.idEmpresa = idEmpresa;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = estado;
        this.tiempoBloqueo = tiempoBloqueo;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
    public List<SistemaAccesoEmpresa> getSistemaAccesoEmpresaList() {
        return sistemaAccesoEmpresaList;
    }

    public void setSistemaAccesoEmpresaList(List<SistemaAccesoEmpresa> sistemaAccesoEmpresaList) {
        this.sistemaAccesoEmpresaList = sistemaAccesoEmpresaList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemaEmpresa)) {
            return false;
        }
        SistemaEmpresa other = (SistemaEmpresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.SistemaEmpresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
