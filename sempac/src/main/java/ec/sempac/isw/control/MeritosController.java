package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Meritos;
import ec.sempac.isw.negocio.MeritosFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "meritosController")
@SessionScoped
public class MeritosController extends AbstractController<Meritos> implements Serializable {

    @EJB
    private MeritosFacade ejbFacade;

    public MeritosController() {
        super(Meritos.class);
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
