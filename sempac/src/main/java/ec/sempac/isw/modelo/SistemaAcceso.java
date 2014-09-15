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
@Table(name = "SISTEMA_ACCESO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaAcceso.findAll", query = "SELECT s FROM SistemaAcceso s"),
    @NamedQuery(name = "SistemaAcceso.findByIdSistemaAcceso", query = "SELECT s FROM SistemaAcceso s WHERE s.idSistemaAcceso = :idSistemaAcceso"),
    @NamedQuery(name = "SistemaAcceso.findByFechaAcceso", query = "SELECT s FROM SistemaAcceso s WHERE s.fechaAcceso = :fechaAcceso"),
    @NamedQuery(name = "SistemaAcceso.findByDireccionIp", query = "SELECT s FROM SistemaAcceso s WHERE s.direccionIp = :direccionIp")})
public class SistemaAcceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SISTEMA_ACCESO", nullable = false)
    private Long idSistemaAcceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACCESO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "DIRECCION_IP", nullable = false, length = 32)
    private String direccionIp;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private SistemaUsuario idUsuario;

    public SistemaAcceso() {
    }

    public SistemaAcceso(Long idSistemaAcceso) {
        this.idSistemaAcceso = idSistemaAcceso;
    }

    public SistemaAcceso(Long idSistemaAcceso, Date fechaAcceso, String direccionIp) {
        this.idSistemaAcceso = idSistemaAcceso;
        this.fechaAcceso = fechaAcceso;
        this.direccionIp = direccionIp;
    }

    public Long getIdSistemaAcceso() {
        return idSistemaAcceso;
    }

    public void setIdSistemaAcceso(Long idSistemaAcceso) {
        this.idSistemaAcceso = idSistemaAcceso;
    }

    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public SistemaUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SistemaUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSistemaAcceso != null ? idSistemaAcceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemaAcceso)) {
            return false;
        }
        SistemaAcceso other = (SistemaAcceso) object;
        if ((this.idSistemaAcceso == null && other.idSistemaAcceso != null) || (this.idSistemaAcceso != null && !this.idSistemaAcceso.equals(other.idSistemaAcceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.ssw.modelo.SistemaAcceso[ idSistemaAcceso=" + idSistemaAcceso + " ]";
    }
    
}
