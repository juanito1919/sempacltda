/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.seguridades;

import ec.sempac.isw.modelo.Usuario;

/**
 *
 * @author miguesaca
 */
public class ActivacionUsuario {

    // -------------------------------------------------------------------------
    // PROPIEDAD SET
    public static void setCodigoOpcion(Long codigoOpcion) {
        Sesion.setVariable("codigoOpcion", codigoOpcion);
    }

    public static void setCodigoRol(String codigoRol) {
        Sesion.setVariable("codigoRol", codigoRol);
    }

    public static void setCodigoIfip(Long codigoIfip) {
        Sesion.setVariable("codigoIfip", codigoIfip);
    }

    public static void setControlador(String controlador) {
        Sesion.setVariable("controlador", controlador);
    }

    public static void setCodigoUsuario(Long codigoUsuario) {
        Sesion.setVariable("codigoUsuario", codigoUsuario);
    }

    public static void setUsuario(Usuario usuario) {
        Sesion.setVariable("usuario", usuario);
    }

    public static void setCodigoAccesoSistema(Long codigoAccesoSistema) {
        Sesion.setVariable("codigoAccesoSistemaa", codigoAccesoSistema);
    }

    public static void setCodigoIfipAgencia(Long codigoIfipAgencia) {
        Sesion.setVariable("codigoIfipAgencia", codigoIfipAgencia);
    }

    public static void setSinPermisosOpcion(String sinPermisosOpcion) {
        Sesion.setVariable("sinPermisosOpcion", sinPermisosOpcion);
    }

    public static void setCodigoComputador(Long codigoComputador) {
        Sesion.setVariable("codigoComputador", codigoComputador);
    }

    public static void setCambiarContrasena(boolean cambiarContrasena) {
        Sesion.setVariable("cambiarContrasena", cambiarContrasena);
    }

    public static void setCodigoPeriodo(String codigoPeriodo) {
        Sesion.setVariable("codigoPeriodo", codigoPeriodo);
    }

    public static void setPersonaCoyuge(String personaConyuge) {
        // personaConyuge C=Crea E=Edita N=Ninguna Accion
        Sesion.setVariable("personaConyuge", personaConyuge);
    }
    
     public static void setCodigoPersonaConyuge(Long codigoPersonaConyuge) {
        Sesion.setVariable("codigoPersonaConyuge", codigoPersonaConyuge);
    }
    

    // -------------------------------------------------------------------------
    // PROPIEDAD GET
    public static Usuario getUsuario() {
        return (Usuario) Sesion.getVariable("usuario");
    }

    /**
     * @return the codigoUsuario
     */
    public static Long getCodigoUsuario() {
        return Long.parseLong((Sesion.getVariable("codigoUsuario") != null) ? Sesion.getVariable("codigoUsuario").toString() : null);
    }

    public static Long getCodigoIfip() {
        return Long.parseLong((Sesion.getVariable("codigoIfip") != null) ? Sesion.getVariable("codigoIfip").toString() : null);
    }

    public static Long getCodigoOpcion() {
        return Long.parseLong((Sesion.getVariable("codigoOpcion") != null) ? Sesion.getVariable("codigoOpcion").toString() : null);
    }

    public static String getCodigoRol() {
        return (Sesion.getVariable("codigoRol") != null) ? Sesion.getVariable("codigoRol").toString() : null;
    }

    public static String getControlador() {
        return (Sesion.getVariable("controlador") != null) ? Sesion.getVariable("controlador").toString() : null;
    }

    public static Long getCodigoAccesoSistema() {
        return Long.parseLong((Sesion.getVariable("codigoAccesoSistemaa") != null) ? Sesion.getVariable("codigoAccesoSistemaa").toString() : null);
    }

    public static Long getCodigoIfipAgencia() {
        return Long.parseLong((Sesion.getVariable("codigoIfipAgencia") != null) ? Sesion.getVariable("codigoIfipAgencia").toString() : null);
    }

    public static String getSinPermisosOpcion() {
        return (Sesion.getVariable("sinPermisosOpcion") != null) ? Sesion.getVariable("sinPermisosOpcion").toString() : null;
    }

    public static Long getCodigoComputador() {
        return Long.parseLong((Sesion.getVariable("codigoComputador") != null) ? Sesion.getVariable("codigoComputador").toString() : null);
    }

    public static boolean isCambiarContrasena() {
        return (Sesion.getVariable("cambiarContrasena") != null) ? Boolean.parseBoolean(Sesion.getVariable("cambiarContrasena").toString()) : false;
    }

    public static String getCodigoPeriodo() {
        return (Sesion.getVariable("codigoPeriodo") != null) ? Sesion.getVariable("codigoPeriodo").toString() : null;
    }

    public static String getPersonaConyuge() {
        return (Sesion.getVariable("personaConyuge") != null) ? Sesion.getVariable("personaConyuge").toString() : null;
    }
    
    public static Long getCodigoPersonaConyuge() {
        return Long.parseLong((Sesion.getVariable("codigoPersonaConyuge") != null) ? Sesion.getVariable("codigoPersonaConyuge").toString() : null);
    }

}
