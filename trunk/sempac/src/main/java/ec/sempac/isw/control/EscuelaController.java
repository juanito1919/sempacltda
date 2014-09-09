package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Escuela;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "escuelaController")
@SessionScoped
public class EscuelaController extends AbstractController<Escuela> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EscuelaFacade ejbFacade;

    public EscuelaController() {
        super(Escuela.class);
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
