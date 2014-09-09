/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.validador;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.Validaciones;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author miguesaca
 */
@FacesValidator("correoElectronicoValidador")
public class CorreoElectronicoValidador implements Validator {

    private String msg;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String correoElectronico = (value != null) ?  value.toString() : "";
        
        if (!Validaciones.validaCorreoElectronico(correoElectronico) && !correoElectronico.trim().equals("")) {
            String  msg = ResourceBundle.getBundle("/BundleMensajesES").getString("CorreoElectronicoInvalido");
                    throw new ValidatorException(MuestraMensaje.getFaceError(msg));
        }
       
    }

}
