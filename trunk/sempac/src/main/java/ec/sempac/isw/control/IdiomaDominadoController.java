package ec.sempac.isw.control;

import ec.sempac.isw.modelo.IdiomaDominado;
import ec.sempac.isw.negocio.IdiomaDominadoFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "idiomaDominadoController")
@SessionScoped
public class IdiomaDominadoController extends AbstractController<IdiomaDominado> implements Serializable {

    @EJB
    private IdiomaDominadoFacade ejbFacade;

    public IdiomaDominadoController() {
        super(IdiomaDominado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getIdiomaDominadoPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getIdiomaDominadoPK().setIdIdioma(getSelected().getIdioma().getIdIdioma());
    }
    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setIdiomaDominadoPK(new ec.sempac.isw.modelo.IdiomaDominadoPK());
    }

}
