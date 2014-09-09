package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ReferenciaPersonal;
import ec.sempac.isw.negocio.ReferenciaPersonalFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "referenciaPersonalController")
@SessionScoped
public class ReferenciaPersonalController  extends AbstractController<ReferenciaPersonal>implements Serializable {

    @EJB
    private ReferenciaPersonalFacade ejbFacade;

    public ReferenciaPersonalController() {
        super(ReferenciaPersonal.class);
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getReferenciaPersonalPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }
    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setReferenciaPersonalPK(new ec.sempac.isw.modelo.ReferenciaPersonalPK());
    }

}
