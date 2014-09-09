package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "regionController")
@SessionScoped
public class RegionController extends AbstractController<Region> implements Serializable {

    @EJB
    private RegionFacade ejbFacade;
    @EJB
    private PaisFacade ejbFacadePais;

    private List<Pais> listaPais;
    private String auxNombre, auxNombre1;
    private Pais auxPais, auxPais1;

    public RegionController() {
        super(Region.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaPais(ejbFacadePais.getItemsPais(false));
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().setNombre((this.getSelected().getNombre()).trim());
    }

    @Override
    protected void initializeEmbeddableKey() {
        auxNombre = "";
        auxPais = null;
    }

    @Override
    public void prepareEdit(ActionEvent event) {
        if (getSelected() != null) {
            auxNombre = auxNombre1 = getSelected().getNombre();
            auxPais = auxPais1 = getSelected().getIdPais();
        }
    }

    public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (arg2 instanceof String) {
            auxNombre = (arg2.toString()).trim();
        } else if (arg2 instanceof Pais) {
            auxPais = (Pais) arg2;
        }
        if (!(auxNombre1.equals(auxNombre) && auxPais1.equals(auxPais))) {
            if (!"".equals(auxNombre) && auxPais != null && ejbFacade.getRegionUnico(auxNombre, auxPais.getIdPais()) != null) {
                throw new ValidatorException(new FacesMessage(ResourceBundle.getBundle("/BundleMensajesES").getString("ExisteTalValor")));
            }
        }
    }

    /**
     * @return the listaPais
     */
    public List<Pais> getListaPais() {
        return listaPais;
    }

    /**
     * @param listaPais the listaPais to set
     */
    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

}
