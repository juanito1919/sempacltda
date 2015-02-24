/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control.util;

import ec.sempac.isw.seguridades.Sesion;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pablo
 */
@ManagedBean
@SessionScoped
public class ControlUrlControler implements Serializable {

    /**
     * Creates a new instance of ControlUrlControler
     */
    public ControlUrlControler() {

    }

    public void openPaginaPrincipalEmpleado() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("principal"));
    }

    public void openPaginaIdiomasDominados() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("idiomasDominados"));
    }

    public void openPaginadDatosPeronales() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("datosPeronales"));
    }

    public void openPaginaReferenciasLaborales() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("referenciasLaborales"));
    }

    public void openPaginaReferenciasPersonales() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("referenciasPersonales"));
    }

    public void openPaginaInstruccionFormal() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("instruccionFormal"));
    }

    public void openPaginaLogrosPersonales() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("logrosPersonales"));
    }

    public void openRegistroUsuario() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("registro"));
    }

    public void openLoginUsuario() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("loginUsuario"));
    }

    public void openPrincipalEmpresa() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("principalEmpresa"));
    }

    public void openPublicarEmpleo() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("publicarEmpleo"));
    }

    public void openbuscarEmpleados() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("buscarEmpleados"));
    }

    public void openbusquedaAvanzada() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("buscarAvanzada"));
    }

    //habilidadesEspectativas

    public void openHabilidadesEspectativas() throws IOException {
        Sesion.redireccionaPagina(ResourceBundle.getBundle("/BundleObjetosES").getString("habilidadesEspectativas"));
    }
}
