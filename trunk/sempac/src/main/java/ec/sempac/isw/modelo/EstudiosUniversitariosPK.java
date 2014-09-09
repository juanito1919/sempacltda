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
public class EstudiosUniversitariosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNIVERSIDAD", nullable = false)
    private int idUniversidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CARRERA", nullable = false)
    private int idCarrera;

    public EstudiosUniversitariosPK() {
    }

    public EstudiosUniversitariosPK(long idUsuario, int idUniversidad, int idCarrera) {
        this.idUsuario = idUsuario;
        this.idUniversidad = idUniversidad;
        this.idCarrera = idCarrera;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idUniversidad;
        hash += (int) idCarrera;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosUniversitariosPK)) {
            return false;
        }
        EstudiosUniversitariosPK other = (EstudiosUniversitariosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idUniversidad != other.idUniversidad) {
            return false;
        }
        if (this.idCarrera != other.idCarrera) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.EstudiosUniversitariosPK[ idUsuario=" + idUsuario + ", idUniversidad=" + idUniversidad + ", idCarrera=" + idCarrera + " ]";
    }
    
}
