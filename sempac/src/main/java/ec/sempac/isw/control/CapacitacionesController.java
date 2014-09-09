package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Capacitaciones;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "capacitacionesController")
@SessionScoped
public class CapacitacionesController extends AbstractController<Capacitaciones> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.CapacitacionesFacade ejbFacade;

    public CapacitacionesController() {
        super(Capacitaciones.class);
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
