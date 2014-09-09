package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Idioma;
import ec.sempac.isw.negocio.IdiomaFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "idiomaController")
@SessionScoped
public class IdiomaController extends AbstractController<Idioma> implements Serializable {

    @EJB
    private IdiomaFacade ejbFacade;

    public IdiomaController() {
        super(Idioma.class);
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
