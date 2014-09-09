package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EstudiosPrimaria;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "estudiosPrimariaController")
@SessionScoped
public class EstudiosPrimariaController extends AbstractController<EstudiosPrimaria> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosPrimariaFacade ejbFacade;

    public EstudiosPrimariaController() {
        super(EstudiosPrimaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getEstudiosPrimariaPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getEstudiosPrimariaPK().setIdEscuela(getSelected().getEscuela().getIdEscuela());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEstudiosPrimariaPK(new ec.sempac.isw.modelo.EstudiosPrimariaPK());
    }

}
