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
@Table(catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_IDIOMA"}),
    @UniqueConstraint(columnNames = {"NOMBRE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
    @NamedQuery(name = "Idioma.findByIdIdioma", query = "SELECT i FROM Idioma i WHERE i.idIdioma = :idIdioma"),
    @NamedQuery(name = "Idioma.findByNombre", query = "SELECT i FROM Idioma i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Idioma.findByEliminado", query = "SELECT i FROM Idioma i WHERE i.eliminado = :eliminado")})
public class Idioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_IDIOMA", nullable = false)
    private Short idIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma")
    private List<IdiomaDominado> idiomaDominadoList;

    public Idioma() {
    }

    public Idioma(Short idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Idioma(Short idIdioma, String nombre, boolean eliminado) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Short getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Short idIdioma) {
        this.idIdioma = idIdioma;
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
    public List<IdiomaDominado> getIdiomaDominadoList() {
        return idiomaDominadoList;
    }

    public void setIdiomaDominadoList(List<IdiomaDominado> idiomaDominadoList) {
        this.idiomaDominadoList = idiomaDominadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Idioma[ idIdioma=" + idIdioma + " ]";
    }
    
}
