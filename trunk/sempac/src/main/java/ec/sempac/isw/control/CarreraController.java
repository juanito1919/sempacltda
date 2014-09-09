package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Carrera;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carreraController")
@SessionScoped
public class CarreraController extends AbstractController<Carrera> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.CarreraFacade ejbFacade;

    public CarreraController() {
        super(Carrera.class);
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
