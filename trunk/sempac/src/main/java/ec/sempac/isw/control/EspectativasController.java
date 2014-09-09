package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Espectativas;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "espectativasController")
@SessionScoped
public class EspectativasController extends AbstractController<Espectativas> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EspectativasFacade ejbFacade;

    public EspectativasController() {
        super(Espectativas.class);
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
