/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.convertir;

import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.negocio.UserHabilidadesEspectativasFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author miguesaca
 */
@ManagedBean
public class UserHabilidadesEspectativasConverter implements Converter {

    @EJB
    private UserHabilidadesEspectativasFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.sempac.isw.modelo.UserHabilidadesEspectativasPK getKey(String value) {
        ec.sempac.isw.modelo.UserHabilidadesEspectativasPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.sempac.isw.modelo.UserHabilidadesEspectativasPK();
        key.setIdUsuario(Long.parseLong(values[0]));
        key.setIdEspectativas(Long.parseLong(values[1]));
        key.setIdHabilidades(Long.parseLong(values[2]));
        return key;
    }

    String getStringKey(ec.sempac.isw.modelo.UserHabilidadesEspectativasPK value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value.getIdUsuario());
        sb.append(SEPARATOR);
        sb.append(value.getIdEspectativas());
        sb.append(SEPARATOR);
        sb.append(value.getIdHabilidades());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof UserHabilidadesEspectativas) {
            UserHabilidadesEspectativas o = (UserHabilidadesEspectativas) object;
            return getStringKey(o.getUserHabilidadesEspectativasPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserHabilidadesEspectativas.class.getName()});
            return null;
        }
    }
}
