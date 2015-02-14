package ec.sempac.isw.control;

import ec.sempac.isw.modelo.AreaCapacitacion;
import ec.sempac.isw.negocio.AreaCapacitacionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "areaCapacitacionController")
@SessionScoped
public class AreaCapacitacionController extends AbstractController<AreaCapacitacion> implements Serializable {

    @EJB
    private AreaCapacitacionFacade ejbFacade;

    public AreaCapacitacionController() {
        super(AreaCapacitacion.class);
    }

    @Override
    public void create(ActionEvent event) {
        super.create(event); //To change body of generated methods, choose Tools | Templates.
        this.setItems(this.ejbFacade.findAll());
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
