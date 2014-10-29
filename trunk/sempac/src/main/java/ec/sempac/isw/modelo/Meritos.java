/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(catalog = "jmj", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meritos.findAll", query = "SELECT m FROM Meritos m"),
    @NamedQuery(name = "Meritos.findByIdMeritos", query = "SELECT m FROM Meritos m WHERE m.idMeritos = :idMeritos"),
    @NamedQuery(name = "Meritos.findByNombre", query = "SELECT m FROM Meritos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Meritos.findByUrl", query = "SELECT m FROM Meritos m WHERE m.url = :url"),
    @NamedQuery(name = "Meritos.findByEliminado", query = "SELECT m FROM Meritos m WHERE m.eliminado = :eliminado"),
///Personalizados
    @NamedQuery(name = "Meritos.findByUsuarioEliminado", query = "SELECT m FROM Meritos m WHERE m.idUsuario.idUsuario = :idUsuario AND m.eliminado = :eliminado")

})
public class Meritos implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioEliminado ="Meritos.findByUsuarioEliminado"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MERITOS", nullable = false)
    private Long idMeritos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "ID_TIPO_CERTIFICADO", referencedColumnName = "ID_TIPO_CERTIFICADO")
    @ManyToOne
    private TipoCertificado idTipoCertificado;
    @JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID_TIPO_EVENTO")
    @ManyToOne
    private TipoEvento idTipoEvento;
    @JoinColumn(name = "ID_TIPO_MERITO", referencedColumnName = "ID_TIPO_MERITO")
    @ManyToOne
    private TipoMerito idTipoMerito;

    public Meritos() {
    }

    public Meritos(Long idMeritos) {
        this.idMeritos = idMeritos;
    }

    public Meritos(Long idMeritos, String nombre, boolean eliminado) {
        this.idMeritos = idMeritos;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getIdMeritos() {
        return idMeritos;
    }

    public void setIdMeritos(Long idMeritos) {
        this.idMeritos = idMeritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoCertificado getIdTipoCertificado() {
        return idTipoCertificado;
    }

    public void setIdTipoCertificado(TipoCertificado idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public TipoEvento getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(TipoEvento idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public TipoMerito getIdTipoMerito() {
        return idTipoMerito;
    }

    public void setIdTipoMerito(TipoMerito idTipoMerito) {
        this.idTipoMerito = idTipoMerito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeritos != null ? idMeritos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meritos)) {
            return false;
        }
        Meritos other = (Meritos) object;
        if ((this.idMeritos == null && other.idMeritos != null) || (this.idMeritos != null && !this.idMeritos.equals(other.idMeritos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Meritos[ idMeritos=" + idMeritos + " ]";
    }
    
}
