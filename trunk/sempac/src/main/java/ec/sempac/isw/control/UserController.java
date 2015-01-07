package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.control.util.JsfUtil.PersistAction;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.Validaciones;
import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.Sesion;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;

    ///PERSONALIZADOS
    @EJB
    private PaisFacade ejbFacadePais;

    @EJB
    private RegionFacade ejbFacadeProvincia;

    @EJB
    private CiudadFacade ejbFacadeCiudad;

    @EJB
    private SistemaUsuarioFacade ejbFacadeSistemaUsuario;

    private List<Pais> itemPaises;
    private Pais pais;
    private List<Region> itemProvincias;
    private Region provincia;
    private List<Ciudad> itemCiudades;
    private Ciudad ciudad;

    private String confirmaContrasena;
    private String contrasena;
    private Usuario seleccionado;
    private Date fMaxima;
    public UserController() {//solo al inicio del ciclo del bean

    }

    @PostConstruct
    public void init() { // cada vez q se abra la pagina
        itemPaises = ejbFacadePais.getItemsPais(false);
        selected = new Usuario();
        fMaxima = new Date(new Date().getYear()-12,new Date().getMonth(),new Date().getDate());//fecha actual menos 12 anios
    }

    public void selectPais() {
        selected.setIdCiudad(null);
        setItemCiudades(null);
        provincia = null;
        itemProvincias = null;
        if (pais != null) {
            itemProvincias = ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais());

        } else {
            itemProvincias = null;
        }
    }

    public void selectProvincia() {
        selected.setIdCiudad(null);
        if (pais != null && provincia != null) {
            setItemCiudades(ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
        } else {
            setItemCiudades(null);
        }
    }

    public void validarUsername() {
        if (selected.getUsername() != null) {
            if (ejbFacade.getItemsUserName(selected.getUsername()) != null) {
                MuestraMensaje.addError("Ya existe, usuario con username: " + selected.getUsername() + "\' Ingrese Otro\'");
                System.out.println("Ya existe");
                selected.setUsername(null);
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
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MuestraMensaje.addError("Ingrese una contrasena");
        }
    }

    public void validadIdentidad() {
        if (selected.getTipoIdentidad() != null) {
            if (selected.getTipoIdentidad() == 'C') {
                
                if (!Validaciones.validaCedula(selected.getIdentidad())) {
                    MuestraMensaje.addError("Cedula incorrecta");
                    System.out.println("tipo incorrecto");
                } else {
                    MuestraMensaje.addSatisfactorio("Correcta");
                    System.out.println("tipo correcto");
                }
            }
        }
    }

    public void validaTipoIdentidad() {
        System.out.println("esto es TI: " + selected.getTipoIdentidad());
        selected.setIdentidad(null);
        if (selected.getTipoIdentidad() == null) {
            return ;
        }
        if (selected.getTipoIdentidad() == '-') {
            selected.setTipoIdentidad(null);
            return ;
        }
        return ;
    }
    private void crearSistemaUsuario() {
        SistemaUsuario sistemaUsuario = new SistemaUsuario();
        sistemaUsuario.setIdUsuario((ejbFacade.getItemsUserName(selected.getUsername())).getIdUsuario());
        sistemaUsuario.setFechaAsignacion(new Date());
        sistemaUsuario.setEstado('V');//V = valido, P = Pago
        sistemaUsuario.setTiempoBloqueo(0);
        ejbFacadeSistemaUsuario.create(sistemaUsuario);
        File miDir = new File(".");
        File folder;
        try {
            folder = new File(miDir.getCanonicalPath() + File.separator + "Documentos" + File.separator + getSelected().getUsername());
            folder.mkdirs();
        } catch (IOException ex) {
            MuestraMensaje.addError("No se pudo crear el directorio de archivos");
        }

    }
    public void revisaMail() {
        System.out.println("Mail " + this.getSelected().getCorreoElectronico());
        Usuario user = this.ejbFacade.getItemsCorreo(this.getSelected().getCorreoElectronico());
        if (user != null) {
            selected.setCorreoElectronico(null);
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmaContrasena() {
        return confirmaContrasena;
    }

    public void setConfirmaContrasena(String confirmaContrasena) {
        this.confirmaContrasena = confirmaContrasena;
    }

    public Date getFMaxima() {
        return fMaxima;
    }

    public void setFMaxima(Date fMaxima) {
        this.fMaxima = fMaxima;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Region getProvincia() {
        return provincia;
    }

    public void setProvincia(Region provincia) {
        this.provincia = provincia;
    }



   
    //////////////Metodos Auto-generados
    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.setTipo('U');
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "UsuarioCreated");
        crearSistemaUsuario();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundles").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundles").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundles").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundles").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemPaises
     */
    public List<Pais> getItemPaises() {
        return itemPaises;
    }

    /**
     * @param itemPaises the itemPaises to set
     */
    public void setItemPaises(List<Pais> itemPaises) {
        this.itemPaises = itemPaises;
    }

    /**
     * @return the itemProvincias
     */
    public List<Region> getItemProvincias() {
        return itemProvincias;
    }

    /**
     * @param itemProvincias the itemProvincias to set
     */
    public void setItemProvincias(List<Region> itemProvincias) {
        this.itemProvincias = itemProvincias;
    }

    /**
     * @return the itemCiudades
     */
    public List<Ciudad> getItemCiudades() {
        return itemCiudades;
    }

    /**
     * @param itemCiudades the itemCiudades to set
     */
    public void setItemCiudades(List<Ciudad> itemCiudades) {
        this.itemCiudades = itemCiudades;
    }

 

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}
