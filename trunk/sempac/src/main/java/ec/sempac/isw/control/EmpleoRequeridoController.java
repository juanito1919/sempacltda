package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EmpleoRequerido;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "empleoRequeridoController")
@SessionScoped
public class EmpleoRequeridoController extends AbstractController<EmpleoRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpleoRequeridoFacade ejbFacade;

    public EmpleoRequeridoController() {
        super(EmpleoRequerido.class);
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
