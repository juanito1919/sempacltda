package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EstudiosEspecializados;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "estudiosEspecializadosController")
@SessionScoped
public class EstudiosEspecializadosController extends AbstractController<EstudiosEspecializados> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosEspecializadosFacade ejbFacade;

    public EstudiosEspecializadosController() {
        super(EstudiosEspecializados.class);
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
