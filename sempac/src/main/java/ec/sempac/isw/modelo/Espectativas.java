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
    @NamedQuery(name = "Espectativas.findAll", query = "SELECT e FROM Espectativas e"),
    @NamedQuery(name = "Espectativas.findByIdEspectativas", query = "SELECT e FROM Espectativas e WHERE e.idEspectativas = :idEspectativas"),
    @NamedQuery(name = "Espectativas.findByNombre", query = "SELECT e FROM Espectativas e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Espectativas.findByEliminado", query = "SELECT e FROM Espectativas e WHERE e.eliminado = :eliminado")})
public class Espectativas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESPECTATIVAS", nullable = false)
    private Long idEspectativas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "espectativas")
    private List<UserHabilidadesEspectativas> userHabilidadesEspectativasList;

    public Espectativas() {
    }

    public Espectativas(Long idEspectativas) {
        this.idEspectativas = idEspectativas;
    }

    public Espectativas(Long idEspectativas, String nombre, boolean eliminado) {
        this.idEspectativas = idEspectativas;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getIdEspectativas() {
        return idEspectativas;
    }

    public void setIdEspectativas(Long idEspectativas) {
        this.idEspectativas = idEspectativas;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspectativas != null ? idEspectativas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espectativas)) {
            return false;
        }
        Espectativas other = (Espectativas) object;
        if ((this.idEspectativas == null && other.idEspectativas != null) || (this.idEspectativas != null && !this.idEspectativas.equals(other.idEspectativas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Espectativas[ idEspectativas=" + idEspectativas + " ]";
    }
    
}
