/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresa.findByRuc", query = "SELECT e FROM Empresa e WHERE e.ruc = :ruc"),
    @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresa.findByEliminado", query = "SELECT e FROM Empresa e WHERE e.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "Empresa.findByCorreoElectronico", query = "SELECT e FROM Empresa e WHERE e.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Empresa.findByUsername", query = "SELECT e FROM Empresa e WHERE e.username = :username"),    
    @NamedQuery(name = "Empresa.findByUsernameEmail", query = "SELECT u FROM Empresa e WHERE (e.username = :username OR e.correoElectronico = :username) AND u.eliminado = :eliminado")
})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="Empresa.findByEliminado";
    public static final String findByUsername ="Empresa.findByUsername";
    public static final String findByEmail ="Empresa.findByUsername";
    public static final String findByUsernameEmail ="Empresa.findByUsernameEmail";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA", nullable = false)
    private Integer idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "CORREO_ELECTRONICO", nullable = false, length = 64)
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Correo inválido")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String contrasena;
    @Size(max = 16)
    @Column(length = 16)
    private String ruc;
    @Size(max = 128)
    @Column(length = 128)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<PersonalRequerido> personalRequeridoCollection;
    @JoinColumn(name = "ID_CLASE_EMPRESA", referencedColumnName = "ID_CLASE_EMPRESA")
    @ManyToOne
    private ClaseEmpresa idClaseEmpresa;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD", nullable = false)
    @ManyToOne(optional = false)
    private Ciudad idCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Collection<ExperienciaLaboral> experienciaLaboralCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empresa")
    private SistemaEmpresa sistemaEmpresa;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(Integer idEmpresa, String nombre, String username, String correoElectronico, String contrasena, boolean eliminado) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.username = username;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.eliminado = eliminado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PersonalRequerido> getPersonalRequeridoCollection() {
        return personalRequeridoCollection;
    }

    public void setPersonalRequeridoCollection(Collection<PersonalRequerido> personalRequeridoCollection) {
        this.personalRequeridoCollection = personalRequeridoCollection;
    }

    public ClaseEmpresa getIdClaseEmpresa() {
        return idClaseEmpresa;
    }

    public void setIdClaseEmpresa(ClaseEmpresa idClaseEmpresa) {
        this.idClaseEmpresa = idClaseEmpresa;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    @XmlTransient
    public Collection<ExperienciaLaboral> getExperienciaLaboralCollection() {
        return experienciaLaboralCollection;
    }

    public void setExperienciaLaboralCollection(Collection<ExperienciaLaboral> experienciaLaboralCollection) {
        this.experienciaLaboralCollection = experienciaLaboralCollection;
    }

    public SistemaEmpresa getSistemaEmpresa() {
        return sistemaEmpresa;
    }

    public void setSistemaEmpresa(SistemaEmpresa sistemaEmpresa) {
        this.sistemaEmpresa = sistemaEmpresa;
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
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
