/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u"),
    @NamedQuery(name = "Universidad.findByIdUniversidad", query = "SELECT u FROM Universidad u WHERE u.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Universidad.findByNombre", query = "SELECT u FROM Universidad u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Universidad.findByEliminado", query = "SELECT u FROM Universidad u WHERE u.eliminado = :eliminado"),
    @NamedQuery(name = "Universidad.findByPaisEliminado", query = "SELECT u FROM Universidad u WHERE u.idPais.idPais = :idPais AND u.eliminado = :eliminado")})
public class Universidad implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="Universidad.findByEliminado";
    
    public static final String findByPaisEliminado ="Universidad.findByPaisEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UNIVERSIDAD", nullable = false)
    private Integer idUniversidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @ManyToMany(mappedBy = "universidadList")
    private List<Carrera> carreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUniversidad")
    private List<EstudiosEspecializados> estudiosEspecializadosList;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS", nullable = false)
    @ManyToOne(optional = false)
    private Pais idPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidad")
    private List<EstudiosUniversitarios> estudiosUniversitariosList;

    public Universidad() {
    }

    public Universidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Universidad(Integer idUniversidad, String nombre, boolean eliminado) {
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @XmlTransient
    public List<EstudiosEspecializados> getEstudiosEspecializadosList() {
        return estudiosEspecializadosList;
    }

    public void setEstudiosEspecializadosList(List<EstudiosEspecializados> estudiosEspecializadosList) {
        this.estudiosEspecializadosList = estudiosEspecializadosList;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public List<EstudiosUniversitarios> getEstudiosUniversitariosList() {
        return estudiosUniversitariosList;
    }

    public void setEstudiosUniversitariosList(List<EstudiosUniversitarios> estudiosUniversitariosList) {
        this.estudiosUniversitariosList = estudiosUniversitariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidad != null ? idUniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.idUniversidad == null && other.idUniversidad != null) || (this.idUniversidad != null && !this.idUniversidad.equals(other.idUniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Universidad[ idUniversidad=" + idUniversidad + " ]";
    }
    
}
