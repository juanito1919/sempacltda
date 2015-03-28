/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.seguridades;

import ec.sempac.isw.control.AbstractController;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.SendEmail;
import ec.sempac.isw.modelo.Empresa;
import ec.sempac.isw.modelo.SistemaEmpresa;
import ec.sempac.isw.negocio.EmpresaFacade;
import ec.sempac.isw.negocio.SistemaEmpresaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.validation.constraints.Pattern;

/**
 *
 * @author miguesaca
 */
@ManagedBean(name = "contrasenaControllerEmpresa")
public class ContrasenaControllerEmpresa extends AbstractController<Empresa> implements Serializable {

    @EJB
    private EmpresaFacade ejbFacade;
    @EJB
    private SistemaEmpresaFacade ejbFacadeUsuarioSistema;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Usado para el ingreso del correoElectronico de la venta de login
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Correo inválido")
    private String correoElectronico;
    // Usado para el ingreso de la contraseña de la venta de login
    private String contrasena;
    // Almancena el la Entidad del Usuario con los datos de empresa quien accedio al sistema
    private Empresa empresa;
    // Fecha de Acceso al Sistema.
    private Date fecha;
    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
    String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

    public ContrasenaControllerEmpresa() {
        super(Empresa.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setCorreoElectronico(null);
        this.setContrasena(null);
        ActivacionUsuario.setCambiarContrasena(false);
    }

    public String generarClave() {

        Random r = new Random();
        char[] caracter
                = {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u',
                    'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
                    'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9'
                };
        String key = "";
        for (int i = 0; i < 12; i++) {
            key += caracter[r.nextInt(59)];
        }
        return key;
    }

    public void contrasenaOlvidada(ActionEvent event) {
        try {
            Empresa firm = this.ejbFacade.getItemsCorreo(correoElectronico);
            if (firm == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/BundleMensajesES").getString("CorreoNoRegistrado"));
                return;
            }
            String clave = generarClave();
            firm.setContrasena(Sesion.MD5(clave));
            this.setSelected(firm);
            this.save(event);
            SendEmail sendMail = new SendEmail();
            SistemaEmpresa empresaSistema;
            // Colocando la Entidad del Usuario

            String contenido = "<div><span style=\"color:blue;\">Estimado Usuari@: </span><span>" + firm.getNombre() + "</span> </div>\n"
                    + "\n"
                    + "<div style=\"margin-top:30px;\">Se ha aprobado su cuenta en semptac, sus credenciales de acceso son:</div>\n"
                    + "\n"
                    + "<div style=\"margin-top:30px;\">\n"
                    + "<span style=\"color:blue;\">Email:</span> <span>" + firm.getCorreoElectronico() + "</span>\n"
                    + "</div>\n"
                    + "<span style=\"color:blue;\">Contraseña:</span> <span>" + clave + "</span>";
            sendMail.enviar("Recuperacion de Contraseña", contenido, getCorreoElectronico());

            this.setEmpresa(firm);
            try {
                ctx.redirect(ctxPath + "/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ContrasenaControllerEmpresa.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
