package ec.sempac.isw.control;

import ec.sempac.isw.modelo.TipoMerito;
import ec.sempac.isw.negocio.TipoMeritoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoMeritoController")
@SessionScoped
public class TipoMeritoController extends AbstractController<TipoMerito> implements Serializable {

    @EJB
    private TipoMeritoFacade ejbFacade;

    public TipoMeritoController() {
        super(TipoMerito.class);
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
