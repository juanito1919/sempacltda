package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Empresa;
import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.control.util.JsfUtil.PersistAction;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.SistemaEmpresa;
import ec.sempac.isw.negocio.EmpresaFacade;
import ec.sempac.isw.negocio.SistemaEmpresaFacade;
import ec.sempac.isw.seguridades.Sesion;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("empresaController")
@SessionScoped
public class EmpresaController implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpresaFacade ejbFacade;
    @EJB
    private SistemaEmpresaFacade ejbFacadeSistEmpresa;
    private List<Empresa> items = null;
    private Empresa selected;
    private String confirmaContrasena;
    private String contrasena;
    public EmpresaController() {
    }
    public void guardar(){
     if (this.getSelected() != null
                    && this.getSelected().getNombre() != null
                    && this.getSelected().getDireccion() != null
                    && this.getSelected().getUsername() != null
                    && this.getSelected().getCorreoElectronico() != null
                    && this.getSelected().getContrasena() != null
                    && this.getSelected().getRuc() != null) {
                this.create();
                this.crearSistema();
            }
         else {
            System.out.println("Debe ingresar todos los campos por favor");
            MuestraMensaje.addError("Debe ingresar todos los campos por favor");
        }
    }
    public void crearSistema(){
            SistemaEmpresa sisEmpresa = new SistemaEmpresa();
            Empresa e = this.ejbFacade.getItemsUserName(this.getSelected().getUsername());
            sisEmpresa.setIdEmpresa(e.getIdEmpresa());
            sisEmpresa.setFechaAsignacion(new Date());
            sisEmpresa.setEstado('G');
            sisEmpresa.setTiempoBloqueo(0);
            this.ejbFacadeSistEmpresa.create(sisEmpresa);
    }
    public void validarUsername() {
        if (selected.getUsername() != null) {
            if (ejbFacade.getItemsUserName(selected.getUsername()) != null) {
                MuestraMensaje.addError("Ya existe, Empresa con username: " + selected.getUsername() + "\' Ingrese Otro\'");
                System.out.println("Ya existe");
                selected.setUsername(null);
            } else {
                System.out.println(" User correcto");
                MuestraMensaje.addSatisfactorio("Correcto");
            }
        }
    }
    public void validarEmail() {
        if (selected.getCorreoElectronico() != null) {
            if (ejbFacade.getUserEmail(selected.getCorreoElectronico()) != null) {
                MuestraMensaje.addError("Ya existe, Empresa con email: " + selected.getCorreoElectronico() + "\' Ingrese Otro\'");
                System.out.println("Ya existe");
                selected.setCorreoElectronico(null);
            } else {
                System.out.println(" User correcto");
                MuestraMensaje.addSatisfactorio("Correcto");
            }
        }
    }
    public void validarContrasena() {
        if (contrasena != null) {
            if (contrasena.length() < 6 || contrasena.length() > 15) {
                MuestraMensaje.addError("La contrasena debe tener minimo 6 caracteres y maximo 16");
                System.out.println("La contrasena debe tener minimo 6 caracteres y maximo 16");
                selected.setContrasena(null);
                contrasena = null;
            } else {
                MuestraMensaje.addSatisfactorio("Correcto");
                System.out.println("Pass correcto");
            }
            confirmaContrasena = null;
        }
    }
    public void validarConfirmacionContrasena() {
        if (contrasena != null) {
            if (confirmaContrasena != null) {
                if (!contrasena.equals(confirmaContrasena)) {
                    MuestraMensaje.addError("No coinciden las contrasenas");
                    System.out.println("no coinciden las pass");
                    confirmaContrasena = null;
                } else {
                    cifrarContrasena();
                    MuestraMensaje.addSatisfactorio("Verificacion Correcta");
                    System.out.println("verificacion correcta");
                }
            } else {
                MuestraMensaje.addError("Ingrese la confirmacion de contrasena");
            }
        } else {
            confirmaContrasena = null;
        }
    }
    public void cifrarContrasena() {
        if (contrasena != null) {
            try {
                selected.setContrasena(Sesion.MD5(contrasena));
                System.out.println("contra:" + selected.getContrasena());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MuestraMensaje.addError("Ingrese una contrasena");
        }
    }
    public Empresa getSelected() {
        return selected;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmpresaFacade getFacade() {
        return ejbFacade;
    }

    public Empresa prepareCreate() {
        selected = new Empresa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Empresa Creada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }

    public void update() {
        persist(PersistAction.UPDATE, "Empresa Actualizada");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Empresa Eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Empresa> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Empresa getEmpresa(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Empresa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Empresa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the confirmaContrasena
     */
    public String getConfirmaContrasena() {
        return confirmaContrasena;
    }

    /**
     * @param confirmaContrasena the confirmaContrasena to set
     */
    public void setConfirmaContrasena(String confirmaContrasena) {
        this.confirmaContrasena = confirmaContrasena;
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

    @FacesConverter(forClass = Empresa.class)
    public static class EmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaController controller = (EmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaController");
            return controller.getEmpresa(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Empresa) {
                Empresa o = (Empresa) object;
                return getStringKey(o.getIdEmpresa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Empresa.class.getName()});
                return null;
            }
        }

    }

}
