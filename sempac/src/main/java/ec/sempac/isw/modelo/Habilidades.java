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
    @NamedQuery(name = "Habilidades.findAll", query = "SELECT h FROM Habilidades h"),
    @NamedQuery(name = "Habilidades.findByIdHabilidades", query = "SELECT h FROM Habilidades h WHERE h.idHabilidades = :idHabilidades"),
    @NamedQuery(name = "Habilidades.findByNombre", query = "SELECT h FROM Habilidades h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Habilidades.findByEliminado", query = "SELECT h FROM Habilidades h WHERE h.eliminado = :eliminado")})
public class Habilidades implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="Habilidades.findByEliminado";  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HABILIDADES", nullable = false)
    private Long idHabilidades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidades")
    private List<UserHabilidadesEspectativas> userHabilidadesEspectativasList;
    @OneToMany(mappedBy = "idHabilidades")
    private List<PersonalRequerido> personalRequeridoList;

    public Habilidades() {
    }

    public Habilidades(Long idHabilidades) {
        this.idHabilidades = idHabilidades;
    }

    public Habilidades(Long idHabilidades, String nombre, boolean eliminado) {
        this.idHabilidades = idHabilidades;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getIdHabilidades() {
        return idHabilidades;
    }

    public void setIdHabilidades(Long idHabilidades) {
        this.idHabilidades = idHabilidades;
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
    public List<UserHabilidadesEspectativas> getUserHabilidadesEspectativasList() {
        return userHabilidadesEspectativasList;
    }

    public void setUserHabilidadesEspectativasList(List<UserHabilidadesEspectativas> userHabilidadesEspectativasList) {
        this.userHabilidadesEspectativasList = userHabilidadesEspectativasList;
    }

    @XmlTransient
    public List<PersonalRequerido> getPersonalRequeridoList() {
        return personalRequeridoList;
    }

    public void setPersonalRequeridoList(List<PersonalRequerido> personalRequeridoList) {
        this.personalRequeridoList = personalRequeridoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabilidades != null ? idHabilidades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidades)) {
            return false;
        }
        Habilidades other = (Habilidades) object;
        if ((this.idHabilidades == null && other.idHabilidades != null) || (this.idHabilidades != null && !this.idHabilidades.equals(other.idHabilidades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Habilidades[ idHabilidades=" + idHabilidades + " ]";
    }
    
}
