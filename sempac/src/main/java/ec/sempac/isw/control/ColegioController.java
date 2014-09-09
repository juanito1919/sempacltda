package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Colegio;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "colegioController")
@SessionScoped
public class ColegioController extends AbstractController<Colegio> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.ColegioFacade ejbFacade;

    public ColegioController() {
        super(Colegio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

}
