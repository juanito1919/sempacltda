/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.seguridades;

import ec.sempac.isw.control.AbstractController;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author miguesaca
 */
public class ContrasenaController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;
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
    // Fecha de Acceso al Sistema.
    private Date fecha;

    public ContrasenaController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setUsername(null);
        this.setContrasena(null);
        ActivacionUsuario.setCambiarContrasena(false);
    }

    public void contrasenaOlvidada() {
        Usuario user = this.ejbFacade.getItemsCorreo(username);
        if (user == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/BundleMensajesES").getString("UsuarioNoExiste"));
            return;
        }
        SistemaUsuario usuarioSistema;
        // Colocando la Entidad del Usuario
        this.setUsuario(user);

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
}
