/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "IDIOMA_DOMINADO", catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdiomaDominado.findAll", query = "SELECT i FROM IdiomaDominado i"),
    @NamedQuery(name = "IdiomaDominado.findByIdIdioma", query = "SELECT i FROM IdiomaDominado i WHERE i.idiomaDominadoPK.idIdioma = :idIdioma"),
    @NamedQuery(name = "IdiomaDominado.findByIdUsuario", query = "SELECT i FROM IdiomaDominado i WHERE i.idiomaDominadoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "IdiomaDominado.findByNivelHablado", query = "SELECT i FROM IdiomaDominado i WHERE i.nivelHablado = :nivelHablado"),
    @NamedQuery(name = "IdiomaDominado.findByNivelEscrito", query = "SELECT i FROM IdiomaDominado i WHERE i.nivelEscrito = :nivelEscrito"),
    @NamedQuery(name = "IdiomaDominado.findByEliminado", query = "SELECT i FROM IdiomaDominado i WHERE i.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "IdiomaDominado.findByUsuarioEliminado", query = "SELECT i FROM IdiomaDominado i WHERE i.usuario.idUsuario = :idUsuario AND i.eliminado = :eliminado")
})
public class IdiomaDominado implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioEliminado ="IdiomaDominado.findByUsuarioEliminado"; 
    @EmbeddedId
    protected IdiomaDominadoPK idiomaDominadoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NIVEL_HABLADO", nullable = false, length = 8)
    private String nivelHablado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NIVEL_ESCRITO", nullable = false, length = 8)
    private String nivelEscrito;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "ID_IDIOMA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Idioma idioma;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public IdiomaDominado() {
    }

    public IdiomaDominado(IdiomaDominadoPK idiomaDominadoPK) {
        this.idiomaDominadoPK = idiomaDominadoPK;
    }

    public IdiomaDominado(IdiomaDominadoPK idiomaDominadoPK, String nivelHablado, String nivelEscrito, boolean eliminado) {
        this.idiomaDominadoPK = idiomaDominadoPK;
        this.nivelHablado = nivelHablado;
        this.nivelEscrito = nivelEscrito;
        this.eliminado = eliminado;
    }

    public IdiomaDominado(short idIdioma, long idUsuario) {
        this.idiomaDominadoPK = new IdiomaDominadoPK(idIdioma, idUsuario);
    }

    public IdiomaDominadoPK getIdiomaDominadoPK() {
        return idiomaDominadoPK;
    }

    public void setIdiomaDominadoPK(IdiomaDominadoPK idiomaDominadoPK) {
        this.idiomaDominadoPK = idiomaDominadoPK;
    }

    public String getNivelHablado() {
        return nivelHablado;
    }

    public void setNivelHablado(String nivelHablado) {
        this.nivelHablado = nivelHablado;
    }

    public String getNivelEscrito() {
        return nivelEscrito;
    }

    public void setNivelEscrito(String nivelEscrito) {
        this.nivelEscrito = nivelEscrito;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idiomaDominadoPK != null ? idiomaDominadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdiomaDominado)) {
            return false;
        }
        IdiomaDominado other = (IdiomaDominado) object;
        if ((this.idiomaDominadoPK == null && other.idiomaDominadoPK != null) || (this.idiomaDominadoPK != null && !this.idiomaDominadoPK.equals(other.idiomaDominadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.IdiomaDominado[ idiomaDominadoPK=" + idiomaDominadoPK + " ]";
    }
    
}
