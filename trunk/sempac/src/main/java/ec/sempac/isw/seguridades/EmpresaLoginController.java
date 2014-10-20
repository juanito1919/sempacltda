/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.seguridades;

import ec.sempac.isw.control.AbstractController;
import ec.sempac.isw.control.util.ErrorException;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.ObtieneInformacionCliente;
import ec.sempac.isw.control.util.Validaciones;
import ec.sempac.isw.modelo.Empresa;
import ec.sempac.isw.modelo.SistemaAccesoEmpresa;
import ec.sempac.isw.modelo.SistemaEmpresa;
import ec.sempac.isw.negocio.EmpresaFacade;
import ec.sempac.isw.negocio.SistemaAccesoEmpresaFacade;
import ec.sempac.isw.negocio.SistemaEmpresaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author miguesaca
 */
@ManagedBean(name = "empresaLoginController")
@SessionScoped
public class EmpresaLoginController extends AbstractController<Empresa> implements Serializable{
    @EJB
    private EmpresaFacade ejbFacade;
    @EJB
    private SistemaAccesoEmpresaFacade ejbFacadeEmpAcc;
    @EJB
    private SistemaEmpresaFacade ejbFacadeEmpresaSistema;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Usado para el ingreso del username de la venta de login
    private String username;
    // Usado para el ingreso de la contraseña de la venta de login
    private String contrasena;
    // Almancena el la Entidad del Usuario con los datos de usuario quien accedio al sistema
    private Empresa usuario;
    // Guarda la Entidad del Acceso al Sistema del Usuario
    private SistemaAccesoEmpresa sistemaAccesoEmpresa;
    // Fecha de Acceso al Sistema.
    private Date fecha;
    //Mensaje en el Dialogo de Cambio de Contraseña
    private String mensajeDialogoCambioContrasena;
    //Numero de Intentos de Acceso
    private int numeroIntentos;
    private String mensajeCorreoValido;
    private boolean habilitaPanelUsuario;
    // FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    private String contrasenaActual;
    private String contrasenaNueva;
    private String confirmacionContrasena;
    private boolean repiteContrasena;
    private String msg;
    private String mensajeComplejidad;

    public EmpresaLoginController() {
        super(Empresa.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setUsername(null);
        this.setContrasena(null);
        this.setUsuario(null);
        this.habilitaPanelUsuario = false;
        this.numeroIntentos = 0;
        ActivacionUsuario.setCambiarContrasena(false);
        this.setContrasenaActual(null);
        this.confirmacionContrasena = null;
        this.setContrasenaNueva(null);
        this.repiteContrasena = false;
    }

    public void validaUsuario() {
        try {
            ActivacionUsuario.setCambiarContrasena(false);
            // Validando credenciales del Usuario 
            Empresa listEmpresa = this.ejbFacade.getEmpresa(username);
            if (listEmpresa == null) {

                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/BundleMensajesES").getString("UsuarioNoExiste"));
                return;
            }
            SistemaEmpresa empresaSistema;
            // Colocando la Entidad del Usuario
            this.setUsuario(listEmpresa);

            // Validando si el usuario esta asignado al SISTEMA MINKASOFTWARE
            //List<UsuarioSistema> listUsuarioSistema = ejbFacade.getUsuarioSistema(getUsuario(), this.contrasena);
            empresaSistema = ejbFacadeEmpresaSistema.find(listEmpresa.getIdEmpresa());

            if (empresaSistema == null) {

                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));

                return;
            }
            //Verificando el Estado del Usuario
            String estadoUsuario = String.valueOf(empresaSistema.getEstado());

            // Colocando el tiempo de inactividad que tiene el sistema
            Sesion.tiempoInactividad(100);
            //if (empresaSistema.getUsuario().getContrasena().equals(Sesion.MD5(this.contrasena))) {
            if (empresaSistema.getEmpresa().getContrasena().equals(this.contrasena)) {

                // if (estadoUsuario.equals("V") && !Validaciones.validaFechaIgualHoy(empresaSistema.getFechaCaducidad())) {
                if (estadoUsuario.equals("V")) {
                    System.out.println("entrossss");
                    // Iniciando la variable de session con los datos del usuario mediante la entidad.                      
                    ActivacionUsuario.setEmpresa(this.getUsuario());
                    // ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
                    ActivacionUsuario.setCodigoEmpresa(this.getUsuario().getIdEmpresa());
                    ActivacionUsuario.setCodigoPeriodo(String.valueOf(new Date().getYear() + 1900));

                    // Inserta  el Acceso al Sistema
                    this.sistemaAccesoEmpresa = new SistemaAccesoEmpresa();
                    this.sistemaAccesoEmpresa.setIdEmpresa(empresaSistema);
                    this.sistemaAccesoEmpresa.setDireccionIp(ObtieneInformacionCliente.obtenerDireccionIP());
                    this.sistemaAccesoEmpresa.setFechaAcceso(new Date());

                    // Registrando el acceso al sistema
                    ejbFacadeEmpAcc.create(getSistemaAccesoEmpresa());

                    // coloca la fecha de Acceso al Sistema
                    this.setFecha(this.getSistemaAccesoEmpresa().getFechaAcceso());

                    //Colocando el codigo del acceso al sistema
                    ActivacionUsuario.setCodigoAccesoSistema(this.getSistemaAccesoEmpresa().getIdSistemaAccesoEmpresa());
                    //Accediendo al Menu
                    String url = ResourceBundle.getBundle("/BundleObjetosES").getString("principal");
                    Sesion.redireccionaPagina(url);
                    // Si la contraseña ha caducado
                } else if (estadoUsuario.equals("V") && Validaciones.validaFechaIgualHoy(empresaSistema.getFechaCaducidad())) {
                    // Actualizando el Estado de la Contraseña a Caducada
                    empresaSistema.setEstado('C');
                    this.ejbFacadeEmpresaSistema.edit(empresaSistema);
                    this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaCaducada");
                    this.muestraDialogoCambioContrasena();
                    // Si el usuario está Eliminado en el Sistema    
                } else if (estadoUsuario.equals("E")) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));
                    // Si el Usuario tiene la Cuenta Bloqueada
                } else if (estadoUsuario.equals("B")) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCuentaBloqueada"));
                    // Si la Contraseña del Usuario esta Expirada o Caducada
                } else if (estadoUsuario.equals("X") || estadoUsuario.equals("C")) {
                    this.mensajeDialogoCambioContrasena = ((estadoUsuario.equals("X"))
                            ? ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaExpirada")
                            : ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaCaducada"));
                    //System.out.println("Muestra Cambio Contraseña "+this.mensajeDialogoCambioContrasena);
                    this.muestraDialogoCambioContrasena();
                }
            } else if (estadoUsuario.equals("B")) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCuentaBloqueada"));
            } else {
                // Muestra el Mensaje de Advertencia
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioIncorrecto"));
                this.numeroIntentos++;
                RequestContext context = RequestContext.getCurrentInstance();
                if (this.numeroIntentos > 4) {
                    this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioBloqueoCuenta");
                    //Ingreso de parametros de bloqueo temporal de usuario
                    empresaSistema.setEstado('B');
                    empresaSistema.setFechaBloqueo(new Date());
                    this.ejbFacadeEmpresaSistema.edit(empresaSistema);
                    //Abriendo el dilog del bloqueo
                    context.execute("BloqueoCuentaDialogo.show()");
                } else if (this.numeroIntentos == 4) {
                    this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProximoIntentoFallidoBloqueaCuenta");
                    context.execute("InformacionDialogo.show()");
                }
                empresaSistema.setIntentosFallidos((short) numeroIntentos);
                empresaSistema.setFechaUltimoIntentoFallido(new Date());
                this.ejbFacadeEmpresaSistema.edit(empresaSistema);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ErrorException.errorNoCapturadoCapaDeControl, ex.getMessage());
        }
    }

    public void muestraDialogoCambioContrasena() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("CambioContrasenaDialogo.show()");

    }

    public void cambiaContrasena() throws IOException {
        // Iniciando la variable de session con los datos del usuario mediante la entidad.                      
        ActivacionUsuario.setEmpresa(this.getUsuario());
        //ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
        ActivacionUsuario.setCodigoEmpresa(this.getUsuario().getIdEmpresa());
        ActivacionUsuario.setCambiarContrasena(true);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("CambioContrasenaDialogo.hide()");

        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlCambiaContrasena");
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    public void cambiaContrasenaUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Empresa usuarioSistema = ActivacionUsuario.getEmpresa();
        SistemaEmpresa sistemaUsuario = ejbFacadeEmpresaSistema.find(usuarioSistema.getIdEmpresa());
        msg = null;
        try {
            if (!Sesion.MD5(this.getContrasenaActual()).equals(usuarioSistema.getContrasena())) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaActual");

            }
        } catch (Exception ex) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValidarContrasena");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ErrorException.errorNoCapturadoCapaDeControl,
                    new Object[]{"contrasenaUsuarioController", "cambiaContrasenaUsuario", ErrorException.getErrorException(ex)});
        }
        if (msg == null) {
            if (validaComplejidadContrasena()) {

                //Actualiza la tabla de Usuario sistema con la nueva contrasenia
                usuarioSistema.setContrasena(Sesion.MD5(this.getContrasenaNueva()));
                this.ejbFacade.edit(usuarioSistema);
                sistemaUsuario.setFechaCambioContrasena(new Date());
                sistemaUsuario.setEstado('V');
                sistemaUsuario.setFechaCaducidad(obtieneFechaCaducidad());
                ejbFacadeEmpresaSistema.edit(sistemaUsuario);
            }

        }
    }

    public boolean validaComplejidadContrasena() {
        boolean complejidadContrasena = false;
        mensajeComplejidad = null;
        if (Validaciones.contrasenaComplejidadBaja(this.getContrasenaNueva())) {
            complejidadContrasena = false;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadBaja");
        } else if (Validaciones.contrasenaComplejidadMedia(this.getContrasenaNueva())) {
            complejidadContrasena = true;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadMedia");;
        } else if (Validaciones.contrasenaComplejidadAlta(this.getContrasenaNueva())) {
            complejidadContrasena = true;
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadAlta");;
        } else if (this.mensajeComplejidad == null) {
            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaInsegura");;
        }

        msg = (mensajeComplejidad != null && !complejidadContrasena) ? "Contraseña no Cumple con Complejidad Aceptable. " + this.mensajeComplejidad : null;

        return complejidadContrasena;
    }

    public Date obtieneFechaCaducidad() {

        GregorianCalendar calendario = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        calendario.add(Calendar.MONTH, 12);//meses
        calendario.add(Calendar.DATE, 15);//dias
        String fecha = formato.format(calendario.getTime());
        Date envioFechaExpiracion = null;
        try {
            envioFechaExpiracion = formato.parse(fecha);

        } catch (ParseException ex) {
            Logger.getLogger(Empresa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return envioFechaExpiracion;

    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the usuario
     */
    public Empresa getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Empresa usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the sistemaAccesoEmpresa
     */
    public SistemaAccesoEmpresa getSistemaAccesoEmpresa() {
        return sistemaAccesoEmpresa;
    }

    /**
     * @param sistemaAccesoEmpresa the sistemaAccesoEmpresa to set
     */
    public void setSistemaAccesoEmpresa(SistemaAccesoEmpresa sistemaAccesoEmpresa) {
        this.sistemaAccesoEmpresa = sistemaAccesoEmpresa;
    }

    /**
     * @return the contrasenaActual
     */
    public String getContrasenaActual() {
        return contrasenaActual;
    }

    /**
     * @param contrasenaActual the contrasenaActual to set
     */
    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    /**
     * @return the contrasenaNueva
     */
    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    /**
     * @param contrasenaNueva the contrasenaNueva to set
     */
    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }
}
