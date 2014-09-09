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
public class IdiomaDominadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_IDIOMA", nullable = false)
    private short idIdioma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private long idUsuario;

    public IdiomaDominadoPK() {
    }

    public IdiomaDominadoPK(short idIdioma, long idUsuario) {
        this.idIdioma = idIdioma;
        this.idUsuario = idUsuario;
    }

    public short getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(short idIdioma) {
        this.idIdioma = idIdioma;
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
        hash += (int) idIdioma;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdiomaDominadoPK)) {
            return false;
        }
        IdiomaDominadoPK other = (IdiomaDominadoPK) object;
        if (this.idIdioma != other.idIdioma) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.IdiomaDominadoPK[ idIdioma=" + idIdioma + ", idUsuario=" + idUsuario + " ]";
    }
    
}
