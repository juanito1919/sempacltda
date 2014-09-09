package ec.sempac.isw.control;

import ec.sempac.isw.control.util.ErrorException;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.ClaseEmpresa;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "claseEmpresaController")
@ViewScoped
public class ClaseEmpresaController extends AbstractController<ClaseEmpresa> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.ClaseEmpresaFacade ejbFacade;

    public ClaseEmpresaController() {
        super(ClaseEmpresa.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().setNombre((this.getSelected().getNombre()).trim());
    }

    @Override
    protected void initializeEmbeddableKey() {
    }
    public void elimina(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado(true);
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/BundleMensajesES").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ErrorException.errorNoCapturadoCapaDeControl,
                        new Object[]{"ClaseEmpresaController", "eliminaClaseEmpresa", ErrorException.getErrorException(ex)});
            }
        }

    }
    public void validateNombreUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (ejbFacade.getClaseEmpresa((arg2.toString()).trim()) != null) {
            throw new ValidatorException(new FacesMessage(ResourceBundle.getBundle("/BundleMensajesES").getString("ExisteTalValor")));
        }
    }
    @Override
    public void prepareEdit(ActionEvent event){}
}
