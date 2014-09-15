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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "REFERENCIA_PERSONAL", catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_REFERENCIA_PERSONAL"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenciaPersonal.findAll", query = "SELECT r FROM ReferenciaPersonal r"),
    @NamedQuery(name = "ReferenciaPersonal.findByIdReferenciaPersonal", query = "SELECT r FROM ReferenciaPersonal r WHERE r.referenciaPersonalPK.idReferenciaPersonal = :idReferenciaPersonal"),
    @NamedQuery(name = "ReferenciaPersonal.findByIdUsuario", query = "SELECT r FROM ReferenciaPersonal r WHERE r.referenciaPersonalPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "ReferenciaPersonal.findByNombres", query = "SELECT r FROM ReferenciaPersonal r WHERE r.nombres = :nombres"),
    @NamedQuery(name = "ReferenciaPersonal.findByApellidos", query = "SELECT r FROM ReferenciaPersonal r WHERE r.apellidos = :apellidos"),
    @NamedQuery(name = "ReferenciaPersonal.findByTelefono", query = "SELECT r FROM ReferenciaPersonal r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReferenciaPersonal.findByCorreo", query = "SELECT r FROM ReferenciaPersonal r WHERE r.correo = :correo"),
    @NamedQuery(name = "ReferenciaPersonal.findByUrl", query = "SELECT r FROM ReferenciaPersonal r WHERE r.url = :url"),
    @NamedQuery(name = "ReferenciaPersonal.findByEliminado", query = "SELECT r FROM ReferenciaPersonal r WHERE r.eliminado = :eliminado")})
public class ReferenciaPersonal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReferenciaPersonalPK referenciaPersonalPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String telefono;
    @Size(max = 64)
    @Column(length = 64)
    private String correo;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public ReferenciaPersonal() {
    }

    public ReferenciaPersonal(ReferenciaPersonalPK referenciaPersonalPK) {
        this.referenciaPersonalPK = referenciaPersonalPK;
    }

    public ReferenciaPersonal(ReferenciaPersonalPK referenciaPersonalPK, String nombres, String apellidos, String telefono, boolean eliminado) {
        this.referenciaPersonalPK = referenciaPersonalPK;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }

    public ReferenciaPersonal(long idReferenciaPersonal, long idUsuario) {
        this.referenciaPersonalPK = new ReferenciaPersonalPK(idReferenciaPersonal, idUsuario);
    }

    public ReferenciaPersonalPK getReferenciaPersonalPK() {
        return referenciaPersonalPK;
    }

    public void setReferenciaPersonalPK(ReferenciaPersonalPK referenciaPersonalPK) {
        this.referenciaPersonalPK = referenciaPersonalPK;
    }

    public String getNombres() {
        //System.out.println("holaaaaaaa"+getNombres());
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referenciaPersonalPK != null ? referenciaPersonalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenciaPersonal)) {
            return false;
        }
        ReferenciaPersonal other = (ReferenciaPersonal) object;
        if ((this.referenciaPersonalPK == null && other.referenciaPersonalPK != null) || (this.referenciaPersonalPK != null && !this.referenciaPersonalPK.equals(other.referenciaPersonalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.ReferenciaPersonal[ referenciaPersonalPK=" + referenciaPersonalPK + " ]";
    }
    
}
