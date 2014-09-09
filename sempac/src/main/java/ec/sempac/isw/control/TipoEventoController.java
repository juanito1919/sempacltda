package ec.sempac.isw.control;

import ec.sempac.isw.modelo.TipoEvento;
import ec.sempac.isw.negocio.TipoEventoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoEventoController")
@SessionScoped
public class TipoEventoController extends AbstractController<TipoEvento> implements Serializable {

    @EJB
    private TipoEventoFacade ejbFacade;

    public TipoEventoController() {
        super(TipoEvento.class);
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
