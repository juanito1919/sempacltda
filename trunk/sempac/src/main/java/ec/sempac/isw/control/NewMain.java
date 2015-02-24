/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Usuario;

/**
 *
 * @author pablito
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioController userControler = new UsuarioController();
        userControler.busquedaAvanzada();
    }
    
}
