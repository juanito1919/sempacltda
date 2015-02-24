package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.PersonalRequerido;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.seguridades.Empleos;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "personalRequeridoController")
@SessionScoped
public class PersonalRequeridoController extends AbstractController<PersonalRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.PersonalRequeridoFacade ejbFacade;
    @EJB
    private HabilidadesFacade ejbFacadeHabilidades;
    @EJB
    private CiudadFacade ejbFacadeCiudad;
    
    private List<PersonalRequerido> itemsPersonalRequerido;
    private List<Habilidades> itemsHabilidades;
    private Habilidades habilidadBusqueda;
    private String fechaActual;
    private List<Ciudad> itemCiudades;
    private Ciudad ciudad;
    
    public PersonalRequeridoController() {
        super(PersonalRequerido.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setItemsPersonalRequerido(ejbFacade.getItemsPersonalRequerido(false));// todas las notifiaciones q no esten eliminadas
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    /**
     * @return the itemPersonalRequerido
     */
    public List<PersonalRequerido> getItemsPersonalRequerido() {
        return itemsPersonalRequerido;
    }
    public void empleo(){
        Empleos.setPersonalRequerido(getSelected());/////
        System.out.println("engreso empleo req");
    }
    public void iniciarBusqueda() {

        this.setItemsHabilidades(this.ejbFacadeHabilidades.getItemsHabilidadesEliminado(false));
        Date data = new Date();
        this.setFechaActual(data.toString());
        this.setItemCiudades(this.ejbFacadeCiudad.listTatolCiudad(false));
        prepareNuevo();
    }

    public String fechaActual() {
        Date data = new Date();
        String fecha = data.toString();
        return fecha;
    }

    public void guardarNuevo(ActionEvent event) {
        if (ActivacionUsuario.getEmpresa() != null) {
            this.getSelected().setIdEmpresa(ActivacionUsuario.getEmpresa());
            this.getSelected().setActivo(true);
            this.getSelected().setIdHabilidades(habilidadBusqueda);
            this.getSelected().setFechaModificacion(new Date());
            this.getSelected().setEliminado(false);
            this.getSelected().setIdCiudad(ciudad);
            this.saveNew(event);
        }
    }

    public void prepareNuevo() {
        System.err.println("entro nuevo");
        this.setSelected(new PersonalRequerido());

    }

    /**
     * @param itemsPersonalRequerido the itemPersonalRequerido to set
     */
    public void setItemsPersonalRequerido(List<PersonalRequerido> itemsPersonalRequerido) {
        this.itemsPersonalRequerido = itemsPersonalRequerido;
    }

    /**
     * @return the itemsHabilidades
     */
    public List<Habilidades> getItemsHabilidades() {
        return itemsHabilidades;
    }

    /**
     * @param itemsHabilidades the itemsHabilidades to set
     */
    public void setItemsHabilidades(List<Habilidades> itemsHabilidades) {
        this.itemsHabilidades = itemsHabilidades;
    }

    /**
     * @return the habilidadBusqueda
     */
    public Habilidades getHabilidadBusqueda() {
        return habilidadBusqueda;
    }

    /**
     * @param habilidadBusqueda the habilidadBusqueda to set
     */
    public void setHabilidadBusqueda(Habilidades habilidadBusqueda) {
        this.habilidadBusqueda = habilidadBusqueda;
    }

    /**
     * @return the fechaActual
     */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the itemCiudades
     */
    public List<Ciudad> getItemCiudades() {
        return itemCiudades;
    }

    /**
     * @param itemCiudades the itemCiudades to set
     */
    public void setItemCiudades(List<Ciudad> itemCiudades) {
        this.itemCiudades = itemCiudades;
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
