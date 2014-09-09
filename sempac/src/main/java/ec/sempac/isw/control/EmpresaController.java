package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Empresa;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "empresaController")
@SessionScoped
public class EmpresaController extends AbstractController<Empresa> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpresaFacade ejbFacade;


    public EmpresaController() {
        super(Empresa.class);
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
