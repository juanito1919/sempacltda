package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Ciudad;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ciudadController")
@SessionScoped
public class CiudadController extends AbstractController<Ciudad> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.CiudadFacade ejbFacade;

    public CiudadController() {
        super(Ciudad.class);
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
