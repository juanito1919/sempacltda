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
import ec.sempac.isw.modelo.SistemaAcceso;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.SistemaAccesoFacade;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import org.primefaces.context.RequestContext;

/**
 *
 * @author miguesaca
 */
@ManagedBean(name = "usuarioLoginController")
@SessionScoped
public class UsuarioLoginController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;
    @EJB
    private SistemaAccesoFacade ejbFacadeUsuAcc;
    @EJB
    private SistemaUsuarioFacade ejbFacadeUsuarioSistema;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Usado para el ingreso del username de la venta de login
    private String username;
    // Usado para el ingreso de la contraseña de la venta de login
    private String contrasena;
    // Almancena el la Entidad del Usuario con los datos de usuario quien accedio al sistema
    private Usuario usuario;
    // Guarda la Entidad del Acceso al Sistema del Usuario
   // private SistemaAcceso usuarioAccesoSistema;
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
    
    @Pattern(regexp = "((?=.*\\d).{8,20})", message = "Debe tener almenos 8 caracteres y un Numero")
    private String contrasenaNueva;
    private String confirmacionContrasena;
    private boolean repiteContrasena;
    private String msg;
    private String mensajeComplejidad;

    public UsuarioLoginController() {
        super(Usuario.class);
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
        this.setConfirmacionContrasena(null);
        this.setContrasenaNueva(null);
        this.repiteContrasena = false;
    }

    public void prepareNuevo() {
        System.err.println("prepara login");
    }
    public void validaUsuario() {
        System.out.println("Login");
        try {
            ActivacionUsuario.setCambiarContrasena(false);
            // Validando credenciales del Usuario 
            Usuario listUsuario = this.ejbFacade.getUsuario(username);
            if (listUsuario == null) {

                MuestraMensaje.addAdvertencia("ERROR USUARIO");
                return;
            }
            SistemaUsuario usuarioSistema;
            // Colocando la Entidad del Usuario
            this.setUsuario(listUsuario);

            // Validando si el usuario esta asignado al SISTEMA MINKASOFTWARE
            //List<UsuarioSistema> listUsuarioSistema = ejbFacade.getUsuarioSistema(getUsuario(), this.contrasena);
            usuarioSistema = ejbFacadeUsuarioSistema.find(listUsuario.getIdUsuario());

            if (usuarioSistema == null) {

                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));

                return;
            }
            //Verificando el Estado del Usuario
            String estadoUsuario = String.valueOf(usuarioSistema.getEstado());

            // Colocando el tiempo de inactividad que tiene el sistema
            Sesion.tiempoInactividad(10000);

            if (usuarioSistema.getUsuario().getContrasena().equals(Sesion.MD5(this.contrasena))) {
            //if (usuarioSistema.getUsuario().getContrasena().equals(this.contrasena)) {

                // if (estadoUsuario.equals("V") && !Validaciones.validaFechaIgualHoy(usuarioSistema.getFechaCaducidad())) {
                if (estadoUsuario.equals("V")||estadoUsuario.equals("P")) {
                    System.out.println("entrossss");
                    // Iniciando la variable de session con los datos del usuario mediante la entidad.                      
                    ActivacionUsuario.setUsuario(this.getUsuario());
                    // ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
                    ActivacionUsuario.setCodigoUsuario(this.getUsuario().getIdUsuario());
                    ActivacionUsuario.setCodigoPeriodo(String.valueOf(new Date().getYear() + 1900));

                    // Inserta  el Acceso al Sistema
                    //this.usuarioAccesoSistema = new SistemaAcceso();
                    //this.usuarioAccesoSistema.setIdUsuario(usuarioSistema);
                    //this.usuarioAccesoSistema.setDireccionIp(ObtieneInformacionCliente.obtenerDireccionIP());
                    //this.usuarioAccesoSistema.setFechaAcceso(new Date());

                    // Registrando el acceso al sistema
                   // ejbFacadeUsuAcc.create(getUsuarioAccesoSistema());

                    // coloca la fecha de Acceso al Sistema
                    //this.setFecha(this.getUsuarioAccesoSistema().getFechaAcceso());

                    //Colocando el codigo del acceso al sistema
                   // ActivacionUsuario.setCodigoAccesoSistema(this.getUsuarioAccesoSistema().getIdSistemaAcceso());
                    //Accediendo al Menu
                    String url = ResourceBundle.getBundle("/BundleObjetosES").getString("principal");
                    Sesion.redireccionaPagina(url);
                    // Si la contraseña ha caducado
                } else if (estadoUsuario.equals("V") && Validaciones.validaFechaIgualHoy(usuarioSistema.getFechaCaducidad())) {
                    // Actualizando el Estado de la Contraseña a Caducada
                    usuarioSistema.setEstado('C');
                    this.ejbFacadeUsuarioSistema.edit(usuarioSistema);
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
                    usuarioSistema.setEstado('B');
                    usuarioSistema.setFechaBloqueo(new Date());
                    this.ejbFacadeUsuarioSistema.edit(usuarioSistema);
                    //Abriendo el dilog del bloqueo
                    context.execute("BloqueoCuentaDialogo.show()");
                } else if (this.numeroIntentos == 4) {
                    this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProximoIntentoFallidoBloqueaCuenta");
                    context.execute("InformacionDialogo.show()");
                }
                usuarioSistema.setIntentosFallidos((short) numeroIntentos);
                usuarioSistema.setFechaUltimoIntentoFallido(new Date());
                this.ejbFacadeUsuarioSistema.edit(usuarioSistema);
            }
        } catch (Exception ex) {
            System.err.println(ex);
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
        ActivacionUsuario.setUsuario(this.getUsuario());
        //ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
        ActivacionUsuario.setCodigoUsuario(this.getUsuario().getIdUsuario());
        ActivacionUsuario.setCambiarContrasena(true);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("CambioContrasenaDialogo.hide()");
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlCambiaContrasena");
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    public void cambiaContrasenaUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario usuarioSistema = ActivacionUsuario.getUsuario();
        SistemaUsuario sistemaUsuario = ejbFacadeUsuarioSistema.find(usuarioSistema.getIdUsuario());
        msg = null;
        try {
            if (!Sesion.MD5(this.getContrasenaActual()).equals(usuarioSistema.getContrasena())) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaActual");
                System.out.println(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaActual"));
            } else if (!contrasenaNueva.equals(confirmacionContrasena)) {

                //System.out.println("actual :"+contrasenaActual);
                // System.out.println("cofirmacion :"+confirmacionContrasena);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConfirmaContrasena");
                // System.out.println(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConfirmaContrasena"));
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
                ejbFacadeUsuarioSistema.edit(sistemaUsuario);
                MuestraMensaje.addSatisfactorio("Contrasena Actualizada");
            } else {
                MuestraMensaje.addError(msg);
            }
        } else {
            MuestraMensaje.addError(msg);
        }
    }

    public boolean validaComplejidadContrasena() {
        boolean complejidadContrasena = false;
        mensajeComplejidad = null;
        if (this.contrasenaNueva.length() >= 8) {
            complejidadContrasena = true;
        } else {
            this.mensajeComplejidad = "La Contrasena debe tener al menos 8 caracteres";
        }
//        if (Validaciones.contrasenaComplejidadBaja(this.getContrasenaNueva())) {
//            complejidadContrasena = false;
//            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadBaja");
//        } else if (Validaciones.contrasenaComplejidadMedia(this.getContrasenaNueva())) {
//            complejidadContrasena = true;
//            
//            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadMedia");;
//        } else if (Validaciones.contrasenaComplejidadAlta(this.getContrasenaNueva())) {
//            complejidadContrasena = true;
//            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadAlta");;
//        } else if (this.mensajeComplejidad == null) {
//            this.mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaInsegura");;
//        }

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
            Logger.getLogger(Usuario.class
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
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
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

//    /**
//     * @return the usuarioAccesoSistema
//     */
//    public SistemaAcceso getUsuarioAccesoSistema() {
//        return usuarioAccesoSistema;
//    }
//
//    /**
//     * @param usuarioAccesoSistema the usuarioAccesoSistema to set
//     */
//    public void setUsuarioAccesoSistema(SistemaAcceso usuarioAccesoSistema) {
//        this.usuarioAccesoSistema = usuarioAccesoSistema;
//    }

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

    /**
     * @return the confirmacionContrasena
     */
    public String getConfirmacionContrasena() {
        return confirmacionContrasena;
    }

    /**
     * @param confirmacionContrasena the confirmacionContrasena to set
     */
    public void setConfirmacionContrasena(String confirmacionContrasena) {
        this.confirmacionContrasena = confirmacionContrasena;
    }

    /**
     * @return the repiteContrasena
     */
}
