package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Universidad;
import ec.sempac.isw.negocio.UniversidadFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "universidadController")
@SessionScoped
public class UniversidadController extends AbstractController<Universidad> implements Serializable {

    @EJB
    private UniversidadFacade ejbFacade;

    public UniversidadController() {
        super(Universidad.class);
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
