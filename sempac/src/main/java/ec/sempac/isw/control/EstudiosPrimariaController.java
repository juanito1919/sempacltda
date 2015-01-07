package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Escuela;
import ec.sempac.isw.modelo.EstudiosPrimaria;
import ec.sempac.isw.modelo.EstudiosPrimariaPK;
import ec.sempac.isw.negocio.EscuelaFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "estudiosPrimariaController")
@SessionScoped
public class EstudiosPrimariaController extends AbstractController<EstudiosPrimaria> implements Serializable {
    
    @EJB
    private ec.sempac.isw.negocio.EstudiosPrimariaFacade ejbFacade;
    @EJB
    private EscuelaFacade ejbFacadeEscuela;
    private List<Escuela> itemEscuela;
    private String nombre = "";
    
    public EstudiosPrimariaController() {
        super(EstudiosPrimaria.class);
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
         this.setItemEscuela(this.ejbFacadeEscuela.getItemsEscuela(false));
    }

    public void iniciaSelected() {
        this.setSelected(new EstudiosPrimaria());
        this.getSelected().setEstudiosPrimariaPK(new EstudiosPrimariaPK());
    }
    public void guardarEscuela(ActionEvent event) {
        System.out.println("Guardar Escuela");
        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.save(event);
        
    }
    @Override
    protected void setEmbeddableKeys() {
        getSelected().getEstudiosPrimariaPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getEstudiosPrimariaPK().setIdEscuela(getSelected().getEscuela().getIdEscuela());
    }
    
    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEstudiosPrimariaPK(new ec.sempac.isw.modelo.EstudiosPrimariaPK());
    }

    /**
     * @return the itemEscuela
     */
    public List<Escuela> getItemEscuela() {
        return itemEscuela;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param itemEscuela the itemEscuela to set
     */
    public void setItemEscuela(List<Escuela> itemEscuela) {
        this.itemEscuela = itemEscuela;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
