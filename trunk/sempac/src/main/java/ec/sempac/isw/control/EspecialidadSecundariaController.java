package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EspecialidadSecundaria;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "especialidadSecundariaController")
@SessionScoped
public class EspecialidadSecundariaController extends AbstractController<EspecialidadSecundaria> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EspecialidadSecundariaFacade ejbFacade;

    public EspecialidadSecundariaController() {
        super(EspecialidadSecundaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {

        getSelected().getEspecialidadSecundariaPK().setIdColegio(getSelected().getColegio().getIdColegio());
        getSelected().getEspecialidadSecundariaPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEspecialidadSecundariaPK(new ec.sempac.isw.modelo.EspecialidadSecundariaPK());
    }
}
