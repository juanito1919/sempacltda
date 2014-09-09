package ec.sempac.isw.control;

import ec.sempac.isw.modelo.TipoCertificado;
import ec.sempac.isw.negocio.TipoCertificadoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoCertificadoController")
@SessionScoped
public class TipoCertificadoController extends AbstractController<TipoCertificado> implements Serializable {

    @EJB
    private TipoCertificadoFacade ejbFacade;

    public TipoCertificadoController() {
        super(TipoCertificado.class);
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
