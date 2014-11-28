package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ExperienciaLaboral;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "experienciaLaboralController")
@SessionScoped
public class ExperienciaLaboralController extends AbstractController<ExperienciaLaboral> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.ExperienciaLaboralFacade ejbFacade;
    
    private List<ExperienciaLaboral> itemsExperienciaLaboral;
    
    private ExperienciaLaboral seleccion;

    public ExperienciaLaboralController() {
        super(ExperienciaLaboral.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //borrar esta parte
        ActivacionUsuario.setUsuario(new Usuario(Long.parseLong("1")));
        this.setItemsExperienciaLaboral(this.ejbFacade.getItemsMeritoEliminadoUsuario(ActivacionUsuario.getUsuario().getIdUsuario(), false));
    }
    
    public void iniciaSelected(){
        this.setSelected(new ExperienciaLaboral());
    }
    
    public void guardaNuevo(ActionEvent event){
        if (this.getSelected().getEmpresa().trim().equals("") ||this.getSelected().getCargo().trim().equals("")||this.getSelected().getActividades().trim().equals("")|| this.getSelected().getFechaInicio() ==null)
            return;
        //CAMBIAR estar liena por 
        this.getSelected().setIdUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.save(event);
        this.getItemsExperienciaLaboral().add(this.getSelected());
        this.setSelected(new ExperienciaLaboral());  

    }

    public void eliminar (ActionEvent event){
        System.out.println("seleccion "+seleccion);
        if (seleccion!=null){
            seleccion.setEliminado(true);
            this.ejbFacade.edit(seleccion);
            this.itemsExperienciaLaboral.remove(seleccion);
            seleccion=null;
        }
    }
    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    /**
     * @return the itemsExperienciaLaboral
     */
    public List<ExperienciaLaboral> getItemsExperienciaLaboral() {
        return itemsExperienciaLaboral;
    }

    /**
     * @param itemsExperienciaLaboral the itemsExperienciaLaboral to set
     */
    public void setItemsExperienciaLaboral(List<ExperienciaLaboral> itemsExperienciaLaboral) {
        this.itemsExperienciaLaboral = itemsExperienciaLaboral;
    }

    /**
     * @return the seleccion
     */
    public ExperienciaLaboral getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(ExperienciaLaboral seleccion) {
        this.seleccion = seleccion;
    }

}
