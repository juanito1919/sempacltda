package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.EspecialidadSecundaria;
import ec.sempac.isw.negocio.ColegioFacade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "especialidadSecundariaController")
@SessionScoped
public class EspecialidadSecundariaController extends AbstractController<EspecialidadSecundaria> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EspecialidadSecundariaFacade ejbFacade;
     @EJB
    private ColegioFacade ejbFacadeColegio;
    private List<Colegio> itemColegio;
    private Colegio colegio;
    private String nombre="";

    public EspecialidadSecundariaController() {
        super(EspecialidadSecundaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemColegio(this.ejbFacadeColegio.getItemsColegio(false));
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

    /**
     * @return the itemColegio
     */
    public List<Colegio> getItemColegio() {
        return itemColegio;
    }

    /**
     * @param itemColegio the itemColegio to set
     */
    public void setItemColegio(List<Colegio> itemColegio) {
        this.itemColegio = itemColegio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the colegio
     */
    public Colegio getColegio() {
        return colegio;
    }

    /**
     * @param colegio the colegio to set
     */
    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }
}
