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
public class EstudiosPrimariaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESCUELA", nullable = false)
    private int idEscuela;

    public EstudiosPrimariaPK() {
    }

    public EstudiosPrimariaPK(long idUsuario, int idEscuela) {
        this.idUsuario = idUsuario;
        this.idEscuela = idEscuela;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idEscuela;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosPrimariaPK)) {
            return false;
        }
        EstudiosPrimariaPK other = (EstudiosPrimariaPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idEscuela != other.idEscuela) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EstudiosPrimariaPK[ idUsuario=" + idUsuario + ", idEscuela=" + idEscuela + " ]";
    }
    
}
