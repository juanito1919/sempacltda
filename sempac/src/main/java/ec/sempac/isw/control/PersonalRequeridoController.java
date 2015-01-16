package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.PersonalRequerido;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.seguridades.Empleos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "personalRequeridoController")
@SessionScoped
public class PersonalRequeridoController extends AbstractController<PersonalRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.PersonalRequeridoFacade ejbFacade;
    @EJB
    private HabilidadesFacade ejbFacadeHabilidades;
    
    private List<PersonalRequerido> itemsPersonalRequerido;
     private List<Habilidades> itemsHabilidades;
     private Habilidades habilidadBusqueda;
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
        Empleos.setPersonalRequerido(null);/////
    }
    public void iniciarBusqueda() {
      
        this.setItemsHabilidades(this.ejbFacadeHabilidades.getItemsHabilidadesEliminado(false));
        prepareNuevo();
    }
    public String fechaActual() {
        Date data = new Date();
        String fecha = data.toString();
        return fecha;
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
    
    
}
