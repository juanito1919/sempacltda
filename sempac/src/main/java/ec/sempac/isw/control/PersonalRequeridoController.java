package ec.sempac.isw.control;

import ec.sempac.isw.modelo.PersonalRequerido;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "personalRequeridoController")
@SessionScoped
public class PersonalRequeridoController extends AbstractController<PersonalRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.PersonalRequeridoFacade ejbFacade;
    
    private List<PersonalRequerido> itemPersonalRequerido;
    
    public PersonalRequeridoController() {
        super(PersonalRequerido.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setItemPersonalRequerido(ejbFacade.getItemsPersonalRequerido(false));// todas las notifiaciones q no esten eliminadas
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    /**
     * @return the itemPersonalRequerido
     */
    public List<PersonalRequerido> getItemPersonalRequerido() {
        return itemPersonalRequerido;
    }

    /**
     * @param itemPersonalRequerido the itemPersonalRequerido to set
     */
    public void setItemPersonalRequerido(List<PersonalRequerido> itemPersonalRequerido) {
        this.itemPersonalRequerido = itemPersonalRequerido;
    }
}
