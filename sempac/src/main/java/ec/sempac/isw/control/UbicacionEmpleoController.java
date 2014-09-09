package ec.sempac.isw.control;

import ec.sempac.isw.modelo.UbicacionEmpleo;
import ec.sempac.isw.negocio.UbicacionEmpleoFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ubicacionEmpleoController")
@SessionScoped
public class UbicacionEmpleoController extends AbstractController<UbicacionEmpleo> implements Serializable {

    @EJB
    private UbicacionEmpleoFacade ejbFacade;

    public UbicacionEmpleoController() {
        super(UbicacionEmpleo.class);
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
