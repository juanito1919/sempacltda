package ec.sempac.isw.control;

import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.negocio.UserHabilidadesEspectativasFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userHabilidadesEspectativasController")
@SessionScoped
public class UserHabilidadesEspectativasController extends AbstractController<UserHabilidadesEspectativas> implements Serializable {

    @EJB
    private UserHabilidadesEspectativasFacade ejbFacade;

    public UserHabilidadesEspectativasController() {
        super(UserHabilidadesEspectativas.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getUserHabilidadesEspectativasPK().setIdEspectativas(getSelected().getEspectativas().getIdEspectativas());
        getSelected().getUserHabilidadesEspectativasPK().setIdHabilidades(getSelected().getHabilidades().getIdHabilidades());
        getSelected().getUserHabilidadesEspectativasPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setUserHabilidadesEspectativasPK(new ec.sempac.isw.modelo.UserHabilidadesEspectativasPK());
    }

}
