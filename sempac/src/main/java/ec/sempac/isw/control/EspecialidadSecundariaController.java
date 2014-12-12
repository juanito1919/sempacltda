package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.EspecialidadSecundaria;
import ec.sempac.isw.modelo.EspecialidadSecundariaPK;
import ec.sempac.isw.negocio.ColegioFacade;
import ec.sempac.isw.negocio.EspecialidadSecundariaFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "especialidadSecundariaController")
@SessionScoped
public class EspecialidadSecundariaController extends AbstractController<EspecialidadSecundaria> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EspecialidadSecundariaFacade ejbFacade;
    @EJB
    private ColegioFacade ejbFacadeColegio;
    @EJB
    private EspecialidadSecundariaFacade ejbFacadeEspecialidaSec;
    private List<Colegio> itemColegio;
    private String nombre = "";

    public EspecialidadSecundariaController() {
        super(EspecialidadSecundaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemColegio(this.ejbFacadeColegio.getItemsColegio(false));

    }

    public void iniciaSelected() {
        if (ActivacionUsuario.getUsuario() != null) {
            List<EspecialidadSecundaria> espSec = ejbFacadeEspecialidaSec.getItemsByIdUsuario(ActivacionUsuario.getUsuario().getIdUsuario());
            if (!espSec.isEmpty()) {
                this.setSelected(espSec.get(0));
            } else {
                this.setSelected(new EspecialidadSecundaria());
                this.getSelected().setEspecialidadSecundariaPK(new EspecialidadSecundariaPK());
            }
        }
    }

    public void guardarColegio(ActionEvent event) {

        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.saveNew(event);

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

}
