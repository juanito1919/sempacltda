/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "USER_HABILIDADES_ESPECTATIVAS", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHabilidadesEspectativas.findAll", query = "SELECT u FROM UserHabilidadesEspectativas u"),
    @NamedQuery(name = "UserHabilidadesEspectativas.findByIdUsuario", query = "SELECT u FROM UserHabilidadesEspectativas u WHERE u.userHabilidadesEspectativasPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "UserHabilidadesEspectativas.findByIdEspectativas", query = "SELECT u FROM UserHabilidadesEspectativas u WHERE u.userHabilidadesEspectativasPK.idEspectativas = :idEspectativas"),
    @NamedQuery(name = "UserHabilidadesEspectativas.findByIdHabilidades", query = "SELECT u FROM UserHabilidadesEspectativas u WHERE u.userHabilidadesEspectativasPK.idHabilidades = :idHabilidades")})
public class UserHabilidadesEspectativas implements Serializable {
    private static final long serialVersionUID = 1L;    
    public static final String findByIdUsuario ="UserHabilidadesEspectativas.findByIdUsuario";  
    @EmbeddedId
    protected UserHabilidadesEspectativasPK userHabilidadesEspectativasPK;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ID_ESPECTATIVAS", referencedColumnName = "ID_ESPECTATIVAS", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Espectativas espectativas;
    @JoinColumn(name = "ID_HABILIDADES", referencedColumnName = "ID_HABILIDADES", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidades habilidades;

    public UserHabilidadesEspectativas() {
    }

    public UserHabilidadesEspectativas(UserHabilidadesEspectativasPK userHabilidadesEspectativasPK) {
        this.userHabilidadesEspectativasPK = userHabilidadesEspectativasPK;
    }

    public UserHabilidadesEspectativas(long idUsuario, long idEspectativas, long idHabilidades) {
        this.userHabilidadesEspectativasPK = new UserHabilidadesEspectativasPK(idUsuario, idEspectativas, idHabilidades);
    }

    public UserHabilidadesEspectativasPK getUserHabilidadesEspectativasPK() {
        return userHabilidadesEspectativasPK;
    }

    public void setUserHabilidadesEspectativasPK(UserHabilidadesEspectativasPK userHabilidadesEspectativasPK) {
        this.userHabilidadesEspectativasPK = userHabilidadesEspectativasPK;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espectativas getEspectativas() {
        return espectativas;
    }

    public void setEspectativas(Espectativas espectativas) {
        this.espectativas = espectativas;
    }

    public Habilidades getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidades habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHabilidadesEspectativasPK != null ? userHabilidadesEspectativasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHabilidadesEspectativas)) {
            return false;
        }
        UserHabilidadesEspectativas other = (UserHabilidadesEspectativas) object;
        if ((this.userHabilidadesEspectativasPK == null && other.userHabilidadesEspectativasPK != null) || (this.userHabilidadesEspectativasPK != null && !this.userHabilidadesEspectativasPK.equals(other.userHabilidadesEspectativasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.UserHabilidadesEspectativas[ userHabilidadesEspectativasPK=" + userHabilidadesEspectativasPK + " ]";
    }
    
}
