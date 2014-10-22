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
@Table(name = "SISTEMA_ACCESO_EMPRESA", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaAccesoEmpresa.findAll", query = "SELECT s FROM SistemaAccesoEmpresa s"),
    @NamedQuery(name = "SistemaAccesoEmpresa.findByIdSistemaAccesoEmpresa", query = "SELECT s FROM SistemaAccesoEmpresa s WHERE s.idSistemaAccesoEmpresa = :idSistemaAccesoEmpresa"),
    @NamedQuery(name = "SistemaAccesoEmpresa.findByFechaAcceso", query = "SELECT s FROM SistemaAccesoEmpresa s WHERE s.fechaAcceso = :fechaAcceso"),
    @NamedQuery(name = "SistemaAccesoEmpresa.findByDireccionIp", query = "SELECT s FROM SistemaAccesoEmpresa s WHERE s.direccionIp = :direccionIp")})
public class SistemaAccesoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SISTEMA_ACCESO_EMPRESA", nullable = false)
    private Long idSistemaAccesoEmpresa;
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
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false)
    @ManyToOne(optional = false)
    private SistemaEmpresa idEmpresa;

    public SistemaAccesoEmpresa() {
    }

    public SistemaAccesoEmpresa(Long idSistemaAccesoEmpresa) {
        this.idSistemaAccesoEmpresa = idSistemaAccesoEmpresa;
    }

    public SistemaAccesoEmpresa(Long idSistemaAccesoEmpresa, Date fechaAcceso, String direccionIp) {
        this.idSistemaAccesoEmpresa = idSistemaAccesoEmpresa;
        this.fechaAcceso = fechaAcceso;
        this.direccionIp = direccionIp;
    }

    public Long getIdSistemaAccesoEmpresa() {
        return idSistemaAccesoEmpresa;
    }

    public void setIdSistemaAccesoEmpresa(Long idSistemaAccesoEmpresa) {
        this.idSistemaAccesoEmpresa = idSistemaAccesoEmpresa;
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

    public SistemaEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SistemaEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSistemaAccesoEmpresa != null ? idSistemaAccesoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemaAccesoEmpresa)) {
            return false;
        }
        SistemaAccesoEmpresa other = (SistemaAccesoEmpresa) object;
        if ((this.idSistemaAccesoEmpresa == null && other.idSistemaAccesoEmpresa != null) || (this.idSistemaAccesoEmpresa != null && !this.idSistemaAccesoEmpresa.equals(other.idSistemaAccesoEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.SistemaAccesoEmpresa[ idSistemaAccesoEmpresa=" + idSistemaAccesoEmpresa + " ]";
    }

}
