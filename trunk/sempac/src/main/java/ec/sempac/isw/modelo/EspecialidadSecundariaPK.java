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
public class EspecialidadSecundariaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLEGIO", nullable = false)
    private int idColegio;

    public EspecialidadSecundariaPK() {
    }

    public EspecialidadSecundariaPK(long idUsuario, int idColegio) {
        this.idUsuario = idUsuario;
        this.idColegio = idColegio;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(int idColegio) {
        this.idColegio = idColegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idColegio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadSecundariaPK)) {
            return false;
        }
        EspecialidadSecundariaPK other = (EspecialidadSecundariaPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idColegio != other.idColegio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EspecialidadSecundariaPK[ idUsuario=" + idUsuario + ", idColegio=" + idColegio + " ]";
    }
    
}
