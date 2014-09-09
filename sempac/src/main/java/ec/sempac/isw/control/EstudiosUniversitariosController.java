package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EstudiosUniversitarios;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "estudiosUniversitariosController")
@SessionScoped
public class EstudiosUniversitariosController extends AbstractController<EstudiosUniversitarios> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosUniversitariosFacade ejbFacade;

    public EstudiosUniversitariosController() {
        super(EstudiosUniversitarios.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getEstudiosUniversitariosPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getEstudiosUniversitariosPK().setIdUniversidad(getSelected().getUniversidad().getIdUniversidad());
        getSelected().getEstudiosUniversitariosPK().setIdCarrera(getSelected().getCarrera().getIdCarrera());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEstudiosUniversitariosPK(new ec.sempac.isw.modelo.EstudiosUniversitariosPK());
    }

}
