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
@FacesValidator("rucNaturalValidador")
public class RucNaturalValidador implements Validator{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = null;
        String identificacion = value.toString();

        String siglasTipoIdentificacion = (component.getAttributes().get("siglasIdentificacion") != null) ? component.getAttributes().get("siglasIdentificacion").toString() : "C";

        if (siglasTipoIdentificacion.trim().toUpperCase().equals("C")) {
            try {
                Integer.parseInt(identificacion);
            } catch (Exception e) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
            }
            if (!Validaciones.validaCedula(identificacion)) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }
        }
        //System.out.println("Siglas "+siglasTipoIdentificacion);
        if (siglasTipoIdentificacion.trim().toUpperCase().equals("R")) {
            if (identificacion.length() != 13) {
                try {
                    Integer.parseInt(identificacion);
                } catch (Exception e) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
                }
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }
        }

        if (msg != null) {
            throw new ValidatorException(MuestraMensaje.getFaceError(msg));
        }

    }
}
