/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.seguridades;

import ec.sempac.isw.control.AbstractController;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.SendEmail;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.validation.constraints.Pattern;

/**
 *
 * @author miguesaca
 */
@ManagedBean(name = "contrasenaController")
public class ContrasenaController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;
    @EJB
    private SistemaUsuarioFacade ejbFacadeUsuarioSistema;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Usado para el ingreso del correoElectronico de la venta de login
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Correo inválido")
    private String correoElectronico;
    // Usado para el ingreso de la contraseña de la venta de login
    private String contrasena;
    // Almancena el la Entidad del Usuario con los datos de usuario quien accedio al sistema
    private Usuario usuario;
    // Fecha de Acceso al Sistema.
    private Date fecha;
    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
    String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

    public ContrasenaController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setCorreoElectronico(null);
        this.setContrasena(null);
        ActivacionUsuario.setCambiarContrasena(false);
    }

    public String generarClave() {

        return "127";
    }

    public void contrasenaOlvidada(ActionEvent event) {
        Usuario user = this.ejbFacade.getItemsCorreo(correoElectronico);
        if (user == null) {
            
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/BundleMensajesES").getString("CorreoNoRegistrado"));
            return;
        }

        user.setContrasena(generarClave());
        this.setSelected(user);
        this.save(event);
        SendEmail sendMail = new SendEmail();
        SistemaUsuario usuarioSistema;
        // Colocando la Entidad del Usuario
        sendMail.enviarMail(getCorreoElectronico(), "empresaSempac@info.com", "Recuperacion Clave",
                "La clave es: " + user.getContrasena());
        this.setUsuario(user);
        try {
            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
}
