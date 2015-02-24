/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguesaca
 */
@Entity
@Table(name = "USUARIO", catalog = "jmj", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"}),
    @UniqueConstraint(columnNames = {"CORREO_ELECTRONICO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByCorreoElectronico", query = "SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Usuario.findByTipoIdentidad", query = "SELECT u FROM Usuario u WHERE u.tipoIdentidad = :tipoIdentidad"),
    @NamedQuery(name = "Usuario.findByIdentidad", query = "SELECT u FROM Usuario u WHERE u.identidad = :identidad"),
    @NamedQuery(name = "Usuario.findByGenero", query = "SELECT u FROM Usuario u WHERE u.genero = :genero"),
    @NamedQuery(name = "Usuario.findByEstadoCivil", query = "SELECT u FROM Usuario u WHERE u.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Usuario.findByTelefonoFijo", query = "SELECT u FROM Usuario u WHERE u.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Usuario.findByTelefonoMovil", query = "SELECT u FROM Usuario u WHERE u.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "Usuario.findByEdad", query = "SELECT u FROM Usuario u WHERE u.edad = :edad"),
    @NamedQuery(name = "Usuario.findByUrl", query = "SELECT u FROM Usuario u WHERE u.url = :url"),
    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Usuario.findByDisponibilidad", query = "SELECT u FROM Usuario u WHERE u.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "Usuario.findByCoorLongitud", query = "SELECT u FROM Usuario u WHERE u.coorLongitud = :coorLongitud"),
    @NamedQuery(name = "Usuario.findByCoorLatitud", query = "SELECT u FROM Usuario u WHERE u.coorLatitud = :coorLatitud"),
    @NamedQuery(name = "Usuario.findByEliminado", query = "SELECT u FROM Usuario u WHERE u.eliminado = :eliminado"),
    //personalizadas
    @NamedQuery(name = "Usuario.findByUsernameEmail", query = "SELECT u FROM Usuario u WHERE (u.username = :username OR u.correoElectronico = :username) AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE  u.correoElectronico = :correoElectronico AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByPais", query = "SELECT u FROM Usuario u WHERE  u.idCiudad.idRegion.idPais.idPais=:pais and u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByPaisyRgion", query = "SELECT u FROM Usuario u WHERE  (u.idCiudad.idRegion.idPais.idPais =:pais AND u.idCiudad.idRegion.idRegion =:region) AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByPaisyRgionYciudad", query = "SELECT u FROM Usuario u WHERE  (u.idCiudad.idRegion.idPais.idPais =:pais AND u.idCiudad.idRegion.idRegion =:region AND u.idCiudad.idCiudad =:ciudad) AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByAvanzada", query = "SELECT u FROM Usuario u JOIN UserHabilidadesEspectativas he ON u.idUsuario = he.usuario.idUsuario WHERE u.idCiudad.idRegion.idPais.idPais like :pais AND u.idCiudad.idRegion.idRegion like :region AND u.idCiudad.idCiudad like :ciudad AND he.espectativas.idEspectativas IN :espectativa AND he.habilidades.idHabilidades IN :habilidad AND u.tipo=:tipo AND u.eliminado = :eliminado")

})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByUsernameEmail = "Usuario.findByUsernameEmail";
    public static final String findByEliminado = "Usuario.findByEliminado";
    public static final String findByUsername = "Usuario.findByUsername";
    public static final String findByCorreoElectronico = "Usuario.findByCorreoElectronico";
    public static final String findByEmail = "Usuario.findByEmail";
    public static final String findByPais = "Usuario.findByPais";
    public static final String findByPaisYregion = "Usuario.findByPaisyRgion";
    public static final String findByPaisyRgionYciudad = "Usuario.findByPaisyRgionYciudad";
    public static final String findByAvanzada = "Usuario.findByAvanzada";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombres;
    @Size(max = 64)
    @Column(length = 64)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "CORREO_ELECTRONICO", nullable = false, length = 64)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Correo inválido")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_IDENTIDAD", nullable = false)
    private Character tipoIdentidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String identidad;
    private Character genero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_CIVIL", nullable = false)
    private Character estadoCivil;
    @Size(max = 16)
    @Column(name = "TELEFONO_FIJO", length = 16)
    private String telefonoFijo;
    @Size(max = 16)
    @Column(name = "TELEFONO_MOVIL", length = 16)
    private String telefonoMovil;
    private Integer edad;
    @Size(max = 128)
    @Column(length = 128)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character tipo;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String disponibilidad;
    @Column(name = "COOR_LONGITUD")
    private Integer coorLongitud;
    @Column(name = "COOR_LATITUD")
    private Integer coorLatitud;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<EstudiosEspecializados> estudiosEspecializadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<EstudiosPrimaria> estudiosPrimariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<UserHabilidadesEspectativas> userHabilidadesEspectativasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<ReferenciaPersonal> referenciaPersonalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<EspecialidadSecundaria> especialidadSecundariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Pagos> pagosList;
    @OneToMany(mappedBy = "idAdministrador")
    private List<PersonalRequerido> personalRequeridoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Capacitaciones> capacitacionesList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private SistemaUsuario sistemaUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<IdiomaDominado> idiomaDominadoList;
    @OneToMany(mappedBy = "idUsuario")
    private List<Meritos> meritosList;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD", nullable = false)
    @ManyToOne(optional = false)
    private Ciudad idCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<EmpleoRequerido> empleoRequeridoList;
    @OneToMany(mappedBy = "idAdministrador")
    private List<EmpleoRequerido> empleoRequeridoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ExperienciaLaboral> experienciaLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<EstudiosUniversitarios> estudiosUniversitariosList;

    public Usuario() {
    }

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Long idUsuario, String username, String nombres, String direccion, String correoElectronico, String contrasena, Date fechaNacimiento, Character tipoIdentidad, String identidad, Character estadoCivil, Character tipo, String disponibilidad, boolean eliminado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombres = nombres;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoIdentidad = tipoIdentidad;
        this.identidad = identidad;
        this.estadoCivil = estadoCivil;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.eliminado = eliminado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombres() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getTipoIdentidad() {
        return tipoIdentidad;
    }

    public void setTipoIdentidad(Character tipoIdentidad) {
        this.tipoIdentidad = tipoIdentidad;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Integer getCoorLongitud() {
        return coorLongitud;
    }

    public void setCoorLongitud(Integer coorLongitud) {
        this.coorLongitud = coorLongitud;
    }

    public Integer getCoorLatitud() {
        return coorLatitud;
    }

    public void setCoorLatitud(Integer coorLatitud) {
        this.coorLatitud = coorLatitud;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<EstudiosEspecializados> getEstudiosEspecializadosList() {
        return estudiosEspecializadosList;
    }

    public void setEstudiosEspecializadosList(List<EstudiosEspecializados> estudiosEspecializadosList) {
        this.estudiosEspecializadosList = estudiosEspecializadosList;
    }

    @XmlTransient
    public List<EstudiosPrimaria> getEstudiosPrimariaList() {
        return estudiosPrimariaList;
    }

    public void setEstudiosPrimariaList(List<EstudiosPrimaria> estudiosPrimariaList) {
        this.estudiosPrimariaList = estudiosPrimariaList;
    }

    @XmlTransient
    public List<UserHabilidadesEspectativas> getUserHabilidadesEspectativasList() {
        return userHabilidadesEspectativasList;
    }

    public void setUserHabilidadesEspectativasList(List<UserHabilidadesEspectativas> userHabilidadesEspectativasList) {
        this.userHabilidadesEspectativasList = userHabilidadesEspectativasList;
    }

    @XmlTransient
    public List<ReferenciaPersonal> getReferenciaPersonalList() {
        return referenciaPersonalList;
    }

    public void setReferenciaPersonalList(List<ReferenciaPersonal> referenciaPersonalList) {
        this.referenciaPersonalList = referenciaPersonalList;
    }

    @XmlTransient
    public List<EspecialidadSecundaria> getEspecialidadSecundariaList() {
        return especialidadSecundariaList;
    }

    public void setEspecialidadSecundariaList(List<EspecialidadSecundaria> especialidadSecundariaList) {
        this.especialidadSecundariaList = especialidadSecundariaList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @XmlTransient
    public List<PersonalRequerido> getPersonalRequeridoList() {
        return personalRequeridoList;
    }

    public void setPersonalRequeridoList(List<PersonalRequerido> personalRequeridoList) {
        this.personalRequeridoList = personalRequeridoList;
    }

    @XmlTransient
    public List<Capacitaciones> getCapacitacionesList() {
        return capacitacionesList;
    }

    public void setCapacitacionesList(List<Capacitaciones> capacitacionesList) {
        this.capacitacionesList = capacitacionesList;
    }

    public SistemaUsuario getSistemaUsuario() {
        return sistemaUsuario;
    }

    public void setSistemaUsuario(SistemaUsuario sistemaUsuario) {
        this.sistemaUsuario = sistemaUsuario;
    }

    @XmlTransient
    public List<IdiomaDominado> getIdiomaDominadoList() {
        return idiomaDominadoList;
    }

    public void setIdiomaDominadoList(List<IdiomaDominado> idiomaDominadoList) {
        this.idiomaDominadoList = idiomaDominadoList;
    }

    @XmlTransient
    public List<Meritos> getMeritosList() {
        return meritosList;
    }

    public void setMeritosList(List<Meritos> meritosList) {
        this.meritosList = meritosList;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    @XmlTransient
    public List<EmpleoRequerido> getEmpleoRequeridoList() {
        return empleoRequeridoList;
    }

    public void setEmpleoRequeridoList(List<EmpleoRequerido> empleoRequeridoList) {
        this.empleoRequeridoList = empleoRequeridoList;
    }

    @XmlTransient
    public List<EmpleoRequerido> getEmpleoRequeridoList1() {
        return empleoRequeridoList1;
    }

    public void setEmpleoRequeridoList1(List<EmpleoRequerido> empleoRequeridoList1) {
        this.empleoRequeridoList1 = empleoRequeridoList1;
    }

    @XmlTransient
    public List<ExperienciaLaboral> getExperienciaLaboralList() {
        return experienciaLaboralList;
    }

    public void setExperienciaLaboralList(List<ExperienciaLaboral> experienciaLaboralList) {
        this.experienciaLaboralList = experienciaLaboralList;
    }

    @XmlTransient
    public List<EstudiosUniversitarios> getEstudiosUniversitariosList() {
        return estudiosUniversitariosList;
    }

    public void setEstudiosUniversitariosList(List<EstudiosUniversitarios> estudiosUniversitariosList) {
        this.estudiosUniversitariosList = estudiosUniversitariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sempac.isw.modelo.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
