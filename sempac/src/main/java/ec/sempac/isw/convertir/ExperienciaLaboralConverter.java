/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.convertir;

import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.modelo.ExperienciaLaboral;
import ec.sempac.isw.negocio.ExperienciaLaboralFacade;
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
public class ExperienciaLaboralConverter implements Converter {

    @EJB
    private ExperienciaLaboralFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    ec.sempac.isw.modelo.ExperienciaLaboralPK getKey(String value) {
        ec.sempac.isw.modelo.ExperienciaLaboralPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new ec.sempac.isw.modelo.ExperienciaLaboralPK();
        key.setIdEmpresa(Integer.parseInt(values[0]));
        key.setIdUsuario(Long.parseLong(values[1]));
        return key;
    }

    String getStringKey(ec.sempac.isw.modelo.ExperienciaLaboralPK value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value.getIdEmpresa());
        sb.append(SEPARATOR);
        sb.append(value.getIdUsuario());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ExperienciaLaboral) {
            ExperienciaLaboral o = (ExperienciaLaboral) object;
            return getStringKey(o.getExperienciaLaboralPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ExperienciaLaboral.class.getName()});
            return null;
        }
    }
}
