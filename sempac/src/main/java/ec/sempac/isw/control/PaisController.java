package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.negocio.PaisFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "paisController")
@SessionScoped
public class PaisController extends AbstractController<Pais> implements Serializable {

    @EJB
    private PaisFacade ejbFacade;

    public PaisController() {
        super(Pais.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

}
