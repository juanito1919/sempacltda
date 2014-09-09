/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author miguesaca
 */
@Embeddable
public class UserHabilidadesEspectativasPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESPECTATIVAS", nullable = false)
    private long idEspectativas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HABILIDADES", nullable = false)
    private long idHabilidades;

    public UserHabilidadesEspectativasPK() {
    }

    public UserHabilidadesEspectativasPK(long idUsuario, long idEspectativas, long idHabilidades) {
        this.idUsuario = idUsuario;
        this.idEspectativas = idEspectativas;
        this.idHabilidades = idHabilidades;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdEspectativas() {
        return idEspectativas;
    }

    public void setIdEspectativas(long idEspectativas) {
        this.idEspectativas = idEspectativas;
    }

    public long getIdHabilidades() {
        return idHabilidades;
    }

    public void setIdHabilidades(long idHabilidades) {
        this.idHabilidades = idHabilidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idEspectativas;
        hash += (int) idHabilidades;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHabilidadesEspectativasPK)) {
            return false;
        }
        UserHabilidadesEspectativasPK other = (UserHabilidadesEspectativasPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idEspectativas != other.idEspectativas) {
            return false;
        }
        if (this.idHabilidades != other.idHabilidades) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.UserHabilidadesEspectativasPK[ idUsuario=" + idUsuario + ", idEspectativas=" + idEspectativas + ", idHabilidades=" + idHabilidades + " ]";
    }
    
}
