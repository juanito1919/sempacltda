package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ExperienciaLaboral;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "experienciaLaboralController")
@SessionScoped
public class ExperienciaLaboralController extends AbstractController<ExperienciaLaboral> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.ExperienciaLaboralFacade ejbFacade;

    public ExperienciaLaboralController() {
        super(ExperienciaLaboral.class);
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
