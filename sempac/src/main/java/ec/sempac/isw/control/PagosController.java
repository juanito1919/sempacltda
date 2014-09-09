package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Pagos;
import ec.sempac.isw.negocio.PagosFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pagosController")
@SessionScoped
public class PagosController extends AbstractController<Pagos> implements Serializable {

    @EJB
    private PagosFacade ejbFacade;

    public PagosController() {
        super(Pagos.class);
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
