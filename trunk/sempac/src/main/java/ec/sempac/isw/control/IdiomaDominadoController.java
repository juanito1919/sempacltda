package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Idioma;
import ec.sempac.isw.modelo.IdiomaDominado;
import ec.sempac.isw.modelo.IdiomaDominadoPK;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.IdiomaDominadoFacade;
import ec.sempac.isw.negocio.IdiomaFacade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "idiomaDominadoController")
@SessionScoped
public class IdiomaDominadoController extends AbstractController<IdiomaDominado> implements Serializable {

    @EJB
    private IdiomaDominadoFacade ejbFacade;
    
    @EJB
    private IdiomaFacade ejbFacadeIdioma;
    
    private List <IdiomaDominado> itemsIdiomasDominados;
    private List <Idioma> itemIdiomas;
    
    private IdiomaDominado seleccion;

    public IdiomaDominadoController() {
        super(IdiomaDominado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemIdiomas(this.ejbFacadeIdioma.getItemsIdiomasEliminado(false));
        //cambiar id
        this.setItemsIdiomasDominados(this.ejbFacade.getItemsIdiDomEliminadoUsuario(Long.parseLong("9"), false));
    }

    public void iniciaSelected(){
        this.setSelected(new IdiomaDominado());
        this.getSelected().setIdiomaDominadoPK(new IdiomaDominadoPK());
    }
    
    public void guardaNuevo(ActionEvent event){
        System.out.println("guarda "+this.getSelected());
        if (this.getSelected().getIdioma()==null ||this.getSelected().getNivelEscrito()=="-" || this.getSelected().getNivelHablado()=="-")
            return;
        for (int i=0;i<this.itemsIdiomasDominados.size();i++){
            if (this.itemsIdiomasDominados.get(i).getIdioma().getIdIdioma()==this.getSelected().getIdioma().getIdIdioma())
                return;
        }
        //CAMBIAR estar liena por 
        //this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setUsuario(new Usuario(Long.parseLong("9")));
        this.getSelected().setEliminado(false);
        this.save(event);
        this.getItemsIdiomasDominados().add(this.getSelected());
        this.setSelected(new IdiomaDominado());
        this.getSelected().setIdiomaDominadoPK(new IdiomaDominadoPK());
    }
    
    public void mostrar(){
        System.out.println("seleccion "+seleccion);
    }
    public void eliminar (ActionEvent event){
        System.out.println("seleccion "+seleccion);
        if (seleccion!=null){
            seleccion.setEliminado(true);
            this.ejbFacade.edit(seleccion);
            this.itemsIdiomasDominados.remove(seleccion);
            seleccion=null;
        }
        
        
    }
    
    @Override
    protected void setEmbeddableKeys() {
        getSelected().getIdiomaDominadoPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getIdiomaDominadoPK().setIdIdioma(getSelected().getIdioma().getIdIdioma());
    }
    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setIdiomaDominadoPK(new ec.sempac.isw.modelo.IdiomaDominadoPK());
    }

    /**
     * @return the ejbFacadeIdioma
     */
    public IdiomaFacade getEjbFacadeIdioma() {
        return ejbFacadeIdioma;
    }

    /**
     * @param ejbFacadeIdioma the ejbFacadeIdioma to set
     */
    public void setEjbFacadeIdioma(IdiomaFacade ejbFacadeIdioma) {
        this.ejbFacadeIdioma = ejbFacadeIdioma;
    }

    /**
     * @return the itemIdiomas
     */
    public List <Idioma> getItemIdiomas() {
        return itemIdiomas;
    }

    /**
     * @param itemIdiomas the itemIdiomas to set
     */
    public void setItemIdiomas(List <Idioma> itemIdiomas) {
        this.itemIdiomas = itemIdiomas;
    }

    /**
     * @return the itemsIdiomasDominados
     */
    public List <IdiomaDominado> getItemsIdiomasDominados() {
        return itemsIdiomasDominados;
    }

    /**
     * @param itemsIdiomasDominados the itemsIdiomasDominados to set
     */
    public void setItemsIdiomasDominados(List <IdiomaDominado> itemsIdiomasDominados) {
        this.itemsIdiomasDominados = itemsIdiomasDominados;
    }

    /**
     * @return the seleccion
     */
    public IdiomaDominado getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(IdiomaDominado seleccion) {
        this.seleccion = seleccion;
    }

}
