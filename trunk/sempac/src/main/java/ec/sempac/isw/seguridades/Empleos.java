/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.seguridades;

import ec.sempac.isw.modelo.PersonalRequerido;

/**
 *
 * @author mivkys
 */
public class Empleos {
    public static void setPersonalRequerido(PersonalRequerido personalRequerido) {
        Sesion.setVariable("personalRequerido", personalRequerido);
    }
    public static PersonalRequerido getPersonalRequerido() {
        return (PersonalRequerido) Sesion.getVariable("personalRequerido");
    }
}
