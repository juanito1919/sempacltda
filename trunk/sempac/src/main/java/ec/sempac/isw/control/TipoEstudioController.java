package ec.sempac.isw.control;

import ec.sempac.isw.modelo.TipoEstudio;
import ec.sempac.isw.negocio.TipoEstudioFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoEstudioController")
@SessionScoped
public class TipoEstudioController extends AbstractController<TipoEstudio> implements Serializable {

    @EJB
    private TipoEstudioFacade ejbFacade;

    public TipoEstudioController() {
        super(TipoEstudio.class);
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
