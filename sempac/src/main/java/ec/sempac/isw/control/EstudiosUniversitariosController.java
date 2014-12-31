package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Carrera;
import ec.sempac.isw.modelo.EstudiosUniversitarios;
import ec.sempac.isw.modelo.EstudiosUniversitariosPK;
import ec.sempac.isw.modelo.Universidad;
import ec.sempac.isw.negocio.CarreraFacade;
import ec.sempac.isw.negocio.UniversidadFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "estudiosUniversitariosController")
@SessionScoped
public class EstudiosUniversitariosController extends AbstractController<EstudiosUniversitarios> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosUniversitariosFacade ejbFacade;

    @EJB
    private UniversidadFacade ejbFacadeUniversidad;
    
    @EJB
    private CarreraFacade ejbFacadeCarrera;
    
    
    private List <EstudiosUniversitarios> itemsEstudiosUniversitarios;
    private List<Universidad> itemsUniversidad;    
    private List<Carrera> itemsCarrera;
    private EstudiosUniversitarios seleccion;
    private String titulo;
    
    public EstudiosUniversitariosController() {
        super(EstudiosUniversitarios.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsEstudiosUniversitarios(this.ejbFacade.getItemsEstUnivEliminadoUsuario(ActivacionUsuario.getCodigoUsuario(), false));
        this.setItemsCarrera(this.ejbFacadeCarrera.getItemsCarrera(false));
        this.setItemsUniversidad(this.ejbFacadeUniversidad.getItemsUniversidad(false));
    }

    public void iniciaSelected(){
        this.setSelected(new EstudiosUniversitarios(new EstudiosUniversitariosPK()));
    }
    
    public void guardaNuevo(ActionEvent event){
        this.getSelected().setTitulo(titulo);
        if (this.getSelected().getUniversidad()==null ||this.getSelected().getCarrera()==null || this.getSelected().getTitulo().equals(""))
            return;
        for (int i=0;i<this.itemsEstudiosUniversitarios.size();i++){
            if (this.itemsEstudiosUniversitarios.get(i).getUniversidad().getIdUniversidad()==this.getSelected().getUniversidad().getIdUniversidad() && this.itemsEstudiosUniversitarios.get(i).getCarrera().getIdCarrera()==this.getSelected().getCarrera().getIdCarrera() && this.itemsEstudiosUniversitarios.get(i).getTitulo().endsWith(this.getSelected().getTitulo()))
                return;
        }
        //CAMBIAR estar liena por 
        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.save(event);
        this.getItemsEstudiosUniversitarios().add(this.getSelected());
        this.titulo="";
        this.setSelected(new EstudiosUniversitarios(new EstudiosUniversitariosPK()));
    }
    
    public void eliminar (ActionEvent event){
        if (getSeleccion()!=null){
            getSeleccion().setEliminado(true);
            this.ejbFacade.edit(getSeleccion());
            this.itemsEstudiosUniversitarios.remove(getSeleccion());
            setSeleccion(null);
        }
        
    }
    
    public void mostrar(){
        System.out.println("seleccion "+seleccion);
    }
    
    @Override
    protected void setEmbeddableKeys() {
        getSelected().getEstudiosUniversitariosPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getEstudiosUniversitariosPK().setIdUniversidad(getSelected().getUniversidad().getIdUniversidad());
        getSelected().getEstudiosUniversitariosPK().setIdCarrera(getSelected().getCarrera().getIdCarrera());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEstudiosUniversitariosPK(new ec.sempac.isw.modelo.EstudiosUniversitariosPK());
    }

    /**
     * @return the ejbFacadeUniversidad
     */
    public UniversidadFacade getEjbFacadeUniversidad() {
        return ejbFacadeUniversidad;
    }

    /**
     * @param ejbFacadeUniversidad the ejbFacadeUniversidad to set
     */
    public void setEjbFacadeUniversidad(UniversidadFacade ejbFacadeUniversidad) {
        this.ejbFacadeUniversidad = ejbFacadeUniversidad;
    }

    /**
     * @return the ejbFacadeCarrera
     */
    public CarreraFacade getEjbFacadeCarrera() {
        return ejbFacadeCarrera;
    }

    /**
     * @param ejbFacadeCarrera the ejbFacadeCarrera to set
     */
    public void setEjbFacadeCarrera(CarreraFacade ejbFacadeCarrera) {
        this.ejbFacadeCarrera = ejbFacadeCarrera;
    }

    /**
     * @return the itemsUniversidad
     */
    public List<Universidad> getItemsUniversidad() {
        return itemsUniversidad;
    }

    /**
     * @param itemsUniversidad the itemsUniversidad to set
     */
    public void setItemsUniversidad(List<Universidad> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }

    /**
     * @return the itemsCarrera
     */
    public List<Carrera> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * @param itemsCarrera the itemsCarrera to set
     */
    public void setItemsCarrera(List<Carrera> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * @return the itemsEstudiosUniversitarios
     */
    public List <EstudiosUniversitarios> getItemsEstudiosUniversitarios() {
        return itemsEstudiosUniversitarios;
    }

    /**
     * @param itemsEstudiosUniversitarios the itemsEstudiosUniversitarios to set
     */
    public void setItemsEstudiosUniversitarios(List <EstudiosUniversitarios> itemsEstudiosUniversitarios) {
        this.itemsEstudiosUniversitarios = itemsEstudiosUniversitarios;
    }

    /**
     * @return the seleccion
     */
    public EstudiosUniversitarios getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(EstudiosUniversitarios seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
