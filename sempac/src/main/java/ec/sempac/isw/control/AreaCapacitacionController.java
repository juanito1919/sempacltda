package ec.sempac.isw.control;

import ec.sempac.isw.modelo.AreaCapacitacion;
import ec.sempac.isw.negocio.AreaCapacitacionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "areaCapacitacionController")
@SessionScoped
public class AreaCapacitacionController extends AbstractController<AreaCapacitacion> implements Serializable {

    @EJB
    private AreaCapacitacionFacade ejbFacade;

    public AreaCapacitacionController() {
        super(AreaCapacitacion.class);
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
