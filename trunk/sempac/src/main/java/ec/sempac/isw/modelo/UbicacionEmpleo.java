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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "UBICACION_EMPLEO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionEmpleo.findAll", query = "SELECT u FROM UbicacionEmpleo u"),
    @NamedQuery(name = "UbicacionEmpleo.findByIdUbicacionEmpleo", query = "SELECT u FROM UbicacionEmpleo u WHERE u.idUbicacionEmpleo = :idUbicacionEmpleo"),
    @NamedQuery(name = "UbicacionEmpleo.findByZonaUbiacion", query = "SELECT u FROM UbicacionEmpleo u WHERE u.zonaUbiacion = :zonaUbiacion"),
    @NamedQuery(name = "UbicacionEmpleo.findByEliminado", query = "SELECT u FROM UbicacionEmpleo u WHERE u.eliminado = :eliminado")})
public class UbicacionEmpleo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UBICACION_EMPLEO", nullable = false)
    private Long idUbicacionEmpleo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZONA_UBIACION", nullable = false)
    private int zonaUbiacion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_EMPLEO_REQUERIDO", referencedColumnName = "ID_EMPLEO_REQUERIDO", nullable = false)
    @ManyToOne(optional = false)
    private EmpleoRequerido idEmpleoRequerido;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD", nullable = false)
    @ManyToOne(optional = false)
    private Ciudad idCiudad;

    public UbicacionEmpleo() {
    }

    public UbicacionEmpleo(Long idUbicacionEmpleo) {
        this.idUbicacionEmpleo = idUbicacionEmpleo;
    }

    public UbicacionEmpleo(Long idUbicacionEmpleo, int zonaUbiacion, boolean eliminado) {
        this.idUbicacionEmpleo = idUbicacionEmpleo;
        this.zonaUbiacion = zonaUbiacion;
        this.eliminado = eliminado;
    }

    public Long getIdUbicacionEmpleo() {
        return idUbicacionEmpleo;
    }

    public void setIdUbicacionEmpleo(Long idUbicacionEmpleo) {
        this.idUbicacionEmpleo = idUbicacionEmpleo;
    }

    public int getZonaUbiacion() {
        return zonaUbiacion;
    }

    public void setZonaUbiacion(int zonaUbiacion) {
        this.zonaUbiacion = zonaUbiacion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbicacionEmpleo != null ? idUbicacionEmpleo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionEmpleo)) {
            return false;
        }
        UbicacionEmpleo other = (UbicacionEmpleo) object;
        if ((this.idUbicacionEmpleo == null && other.idUbicacionEmpleo != null) || (this.idUbicacionEmpleo != null && !this.idUbicacionEmpleo.equals(other.idUbicacionEmpleo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.UbicacionEmpleo[ idUbicacionEmpleo=" + idUbicacionEmpleo + " ]";
    }
    
}
