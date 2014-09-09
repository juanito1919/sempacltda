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
@Table(catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c"),
    @NamedQuery(name = "Capacitaciones.findByIdCapacitaciones", query = "SELECT c FROM Capacitaciones c WHERE c.idCapacitaciones = :idCapacitaciones"),
    @NamedQuery(name = "Capacitaciones.findByInstitucion", query = "SELECT c FROM Capacitaciones c WHERE c.institucion = :institucion"),
    @NamedQuery(name = "Capacitaciones.findByNombre", query = "SELECT c FROM Capacitaciones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Capacitaciones.findByFechaInico", query = "SELECT c FROM Capacitaciones c WHERE c.fechaInico = :fechaInico"),
    @NamedQuery(name = "Capacitaciones.findByFechaFin", query = "SELECT c FROM Capacitaciones c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Capacitaciones.findByUrl", query = "SELECT c FROM Capacitaciones c WHERE c.url = :url"),
    @NamedQuery(name = "Capacitaciones.findByEliminado", query = "SELECT c FROM Capacitaciones c WHERE c.eliminado = :eliminado")})
public class Capacitaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CAPACITACIONES", nullable = false)
    private Long idCapacitaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String institucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Column(name = "FECHA_INICO")
    @Temporal(TemporalType.DATE)
    private Date fechaInico;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
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
    @JoinColumn(name = "ID_TIPO_CERTIFICADO", referencedColumnName = "ID_TIPO_CERTIFICADO")
    @ManyToOne
    private TipoCertificado idTipoCertificado;
    @JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID_TIPO_EVENTO")
    @ManyToOne
    private TipoEvento idTipoEvento;
    @JoinColumn(name = "ID_AREA_CAPACITACION", referencedColumnName = "ID_AREA_CAPACITACION")
    @ManyToOne
    private AreaCapacitacion idAreaCapacitacion;

    public Capacitaciones() {
    }

    public Capacitaciones(Long idCapacitaciones) {
        this.idCapacitaciones = idCapacitaciones;
    }

    public Capacitaciones(Long idCapacitaciones, String institucion, String nombre, boolean eliminado) {
        this.idCapacitaciones = idCapacitaciones;
        this.institucion = institucion;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getIdCapacitaciones() {
        return idCapacitaciones;
    }

    public void setIdCapacitaciones(Long idCapacitaciones) {
        this.idCapacitaciones = idCapacitaciones;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInico() {
        return fechaInico;
    }

    public void setFechaInico(Date fechaInico) {
        this.fechaInico = fechaInico;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public TipoCertificado getIdTipoCertificado() {
        return idTipoCertificado;
    }

    public void setIdTipoCertificado(TipoCertificado idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public TipoEvento getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(TipoEvento idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public AreaCapacitacion getIdAreaCapacitacion() {
        return idAreaCapacitacion;
    }

    public void setIdAreaCapacitacion(AreaCapacitacion idAreaCapacitacion) {
        this.idAreaCapacitacion = idAreaCapacitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitaciones != null ? idCapacitaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitaciones)) {
            return false;
        }
        Capacitaciones other = (Capacitaciones) object;
        if ((this.idCapacitaciones == null && other.idCapacitaciones != null) || (this.idCapacitaciones != null && !this.idCapacitaciones.equals(other.idCapacitaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Capacitaciones[ idCapacitaciones=" + idCapacitaciones + " ]";
    }
    
}
