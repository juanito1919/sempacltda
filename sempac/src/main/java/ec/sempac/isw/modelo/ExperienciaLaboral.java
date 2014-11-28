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
@Table(name = "EXPERIENCIA_LABORAL", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e"),
    @NamedQuery(name = "ExperienciaLaboral.findByIdExperienciaLaboral", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idExperienciaLaboral = :idExperienciaLaboral"),
    @NamedQuery(name = "ExperienciaLaboral.findByEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.empresa = :empresa"),
    @NamedQuery(name = "ExperienciaLaboral.findByCargo", query = "SELECT e FROM ExperienciaLaboral e WHERE e.cargo = :cargo"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaInicio", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaFin", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaFin = :fechaFin"),
    @NamedQuery(name = "ExperienciaLaboral.findByActividades", query = "SELECT e FROM ExperienciaLaboral e WHERE e.actividades = :actividades"),
    @NamedQuery(name = "ExperienciaLaboral.findByUrl", query = "SELECT e FROM ExperienciaLaboral e WHERE e.url = :url"),
    @NamedQuery(name = "ExperienciaLaboral.findByTiempo", query = "SELECT e FROM ExperienciaLaboral e WHERE e.tiempo = :tiempo"),
    @NamedQuery(name = "ExperienciaLaboral.findByEliminado", query = "SELECT e FROM ExperienciaLaboral e WHERE e.eliminado = :eliminado"),
//personalizadas
    @NamedQuery(name = "ExperienciaLaboral.findByUsuarioEliminado", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idUsuario.idUsuario = :idUsuario AND e.eliminado = :eliminado")
})
public class ExperienciaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioEliminado ="ExperienciaLaboral.findByUsuarioEliminado"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EXPERIENCIA_LABORAL", nullable = false)
    private Long idExperienciaLaboral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String empresa;
    @Size(max = 64)
    @Column(length = 64)
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 128)
    @Column(length = 128)
    private String actividades;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Long idExperienciaLaboral) {
        this.idExperienciaLaboral = idExperienciaLaboral;
    }

    public ExperienciaLaboral(Long idExperienciaLaboral, String empresa, Date fechaInicio, int tiempo, boolean eliminado) {
        this.idExperienciaLaboral = idExperienciaLaboral;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.tiempo = tiempo;
        this.eliminado = eliminado;
    }

    public Long getIdExperienciaLaboral() {
        return idExperienciaLaboral;
    }

    public void setIdExperienciaLaboral(Long idExperienciaLaboral) {
        this.idExperienciaLaboral = idExperienciaLaboral;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperienciaLaboral != null ? idExperienciaLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.idExperienciaLaboral == null && other.idExperienciaLaboral != null) || (this.idExperienciaLaboral != null && !this.idExperienciaLaboral.equals(other.idExperienciaLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.ExperienciaLaboral[ idExperienciaLaboral=" + idExperienciaLaboral + " ]";
    }
    
}
