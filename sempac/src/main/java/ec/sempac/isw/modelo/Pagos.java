/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findByIdPagos", query = "SELECT p FROM Pagos p WHERE p.idPagos = :idPagos"),
    @NamedQuery(name = "Pagos.findByFechaRegistro", query = "SELECT p FROM Pagos p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Pagos.findByFechaCaducidad", query = "SELECT p FROM Pagos p WHERE p.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "Pagos.findByNumeroDeposito", query = "SELECT p FROM Pagos p WHERE p.numeroDeposito = :numeroDeposito"),
    @NamedQuery(name = "Pagos.findByFechaDeposito", query = "SELECT p FROM Pagos p WHERE p.fechaDeposito = :fechaDeposito"),
    @NamedQuery(name = "Pagos.findByValor", query = "SELECT p FROM Pagos p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pagos.findByEliminado", query = "SELECT p FROM Pagos p WHERE p.eliminado = :eliminado"),
    //Usuario
    @NamedQuery(name = "Pagos.findByIdUsuario", query = "SELECT p FROM Pagos p WHERE p.idUsuario.idUsuario = :idUsuario AND p.eliminado = :eliminado")})
public class Pagos implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIdUsuario ="SistemaUsuario.findByIdUsuario";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAGOS", nullable = false)
    private Long idPagos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Size(max = 32)
    @Column(name = "NUMERO_DEPOSITO", length = 32)
    private String numeroDeposito;
    @Column(name = "FECHA_DEPOSITO")
    @Temporal(TemporalType.DATE)
    private Date fechaDeposito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 8, scale = 2)
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Pagos() {
    }

    public Pagos(Long idPagos) {
        this.idPagos = idPagos;
    }

    public Pagos(Long idPagos, Date fechaRegistro, boolean eliminado) {
        this.idPagos = idPagos;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(Long idPagos) {
        this.idPagos = idPagos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(String numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagos != null ? idPagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.idPagos == null && other.idPagos != null) || (this.idPagos != null && !this.idPagos.equals(other.idPagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Pagos[ idPagos=" + idPagos + " ]";
    }
    
}
