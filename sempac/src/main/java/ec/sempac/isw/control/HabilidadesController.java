package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.negocio.HabilidadesFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "habilidadesController")
@SessionScoped
public class HabilidadesController extends AbstractController<Habilidades> implements Serializable {

    @EJB
    private HabilidadesFacade ejbFacade;

    public HabilidadesController() {
        super(Habilidades.class);
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
