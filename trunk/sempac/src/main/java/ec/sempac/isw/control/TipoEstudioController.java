package ec.sempac.isw.control;

import ec.sempac.isw.modelo.TipoEstudio;
import ec.sempac.isw.negocio.TipoEstudioFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "tipoEstudioController")
@SessionScoped
public class TipoEstudioController extends AbstractController<TipoEstudio> implements Serializable {

    @EJB
    private TipoEstudioFacade ejbFacade;
    private String tipoNivel;
    public TipoEstudioController() {
        super(TipoEstudio.class);
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

    public String getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }
    
    public void escojerVentana(){
       RequestContext context = RequestContext.getCurrentInstance();
        if(getTipoNivel().equals("S")){
          context.execute("EspecialidadSecundariaCreateDialog.show()");
        }
    }
   
}
