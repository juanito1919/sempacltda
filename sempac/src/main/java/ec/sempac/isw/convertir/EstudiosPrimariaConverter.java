/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.convertir;

import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.modelo.EstudiosPrimaria;
import ec.sempac.isw.negocio.EstudiosPrimariaFacade;
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
public class EstudiosPrimariaConverter implements Converter {

    @EJB
    private EstudiosPrimariaFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.sempac.isw.modelo.EstudiosPrimariaPK getKey(String value) {
        ec.sempac.isw.modelo.EstudiosPrimariaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.sempac.isw.modelo.EstudiosPrimariaPK();
        key.setIdUsuario(Long.parseLong(values[0]));
        key.setIdEscuela(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(ec.sempac.isw.modelo.EstudiosPrimariaPK value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value.getIdUsuario());
        sb.append(SEPARATOR);
        sb.append(value.getIdEscuela());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EstudiosPrimaria) {
            EstudiosPrimaria o = (EstudiosPrimaria) object;
            return getStringKey(o.getEstudiosPrimariaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstudiosPrimaria.class.getName()});
            return null;
        }
    }
}
