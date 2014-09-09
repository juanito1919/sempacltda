package ec.sempac.isw.control;

import ec.sempac.isw.modelo.CampoEstudio;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "campoEstudioController")
@SessionScoped
public class CampoEstudioController extends AbstractController<CampoEstudio> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.CampoEstudioFacade ejbFacade;

    public CampoEstudioController() {
        super(CampoEstudio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

}
