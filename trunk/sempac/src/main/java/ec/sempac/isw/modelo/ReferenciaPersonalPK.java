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
public class ReferenciaPersonalPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_REFERENCIA_PERSONAL", nullable = false)
    private long idReferenciaPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;

    public ReferenciaPersonalPK() {
    }

    public ReferenciaPersonalPK(long idReferenciaPersonal, long idUsuario) {
        this.idReferenciaPersonal = idReferenciaPersonal;
        this.idUsuario = idUsuario;
    }

    public long getIdReferenciaPersonal() {
        return idReferenciaPersonal;
    }

    public void setIdReferenciaPersonal(long idReferenciaPersonal) {
        this.idReferenciaPersonal = idReferenciaPersonal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReferenciaPersonal;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenciaPersonalPK)) {
            return false;
        }
        ReferenciaPersonalPK other = (ReferenciaPersonalPK) object;
        if (this.idReferenciaPersonal != other.idReferenciaPersonal) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.ReferenciaPersonalPK[ idReferenciaPersonal=" + idReferenciaPersonal + ", idUsuario=" + idUsuario + " ]";
    }
    
}
