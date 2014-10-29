package ec.sempac.isw.control;

import ec.sempac.isw.modelo.IdiomaDominado;
import ec.sempac.isw.modelo.IdiomaDominadoPK;
import ec.sempac.isw.modelo.Meritos;
import ec.sempac.isw.modelo.TipoCertificado;
import ec.sempac.isw.modelo.TipoEvento;
import ec.sempac.isw.modelo.TipoMerito;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.MeritosFacade;
import ec.sempac.isw.negocio.TipoCertificadoFacade;
import ec.sempac.isw.negocio.TipoEventoFacade;
import ec.sempac.isw.negocio.TipoMeritoFacade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "meritosController")
@SessionScoped
public class MeritosController extends AbstractController<Meritos> implements Serializable {

    @EJB
    private MeritosFacade ejbFacade;
    
    @EJB
    private TipoMeritoFacade ejbFacadeTipoMerito;
    
    @EJB
    private TipoCertificadoFacade ejbFacadeTipoCertificado;
    
    @EJB
    private TipoEventoFacade ejbFacadeTipoEvento;
    
    private List<Meritos> itemsMeritos;
    private List <TipoMerito> itemsTipoMerito;
    private List <TipoCertificado> itemsTipoCertificado;
    private List <TipoEvento> itemsTipoEvento;
    
    private Meritos seleccion;

    public MeritosController() {
        super(Meritos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsTipoCertificado(this.ejbFacadeTipoCertificado.getItemsTipoCertificadoEliminado(false));
        this.setItemsTipoEvento(this.ejbFacadeTipoEvento.getItemsTipoEventoEliminado(false));
        this.setItemsTipoMerito(this.ejbFacadeTipoMerito.getItemsTipoMeritoEliminado(false));
        
        //cambiar id
        this.setItemsMeritos(this.ejbFacade.getItemsMeritoEliminadoUsuario(Long.parseLong("9"), false));
    }

    public void iniciaSelected(){
        this.setSelected(new Meritos());
        this.getSelected().setNombre(" ");
    }
    
    public void iniciaNombre(){
        if (this.getSelected().getNombre().equals(""))
            this.getSelected().setNombre(" ");
    }
    
    public void guardaNuevo(ActionEvent event){
        System.out.println("guarda "+this.getSelected());
        if (this.getSelected().getNombre().trim().equals("") ||this.getSelected().getIdTipoCertificado()==null|| this.getSelected().getIdTipoEvento()==null|| this.getSelected().getIdTipoMerito()==null)
            return;
        //CAMBIAR estar liena por 
        //this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setIdUsuario(new Usuario(Long.parseLong("9")));
        this.getSelected().setEliminado(false);
        this.save(event);
        this.getItemsMeritos().add(this.getSelected());
        this.setSelected(new Meritos());        
        this.getSelected().setNombre(" ");

    }
    
    public void mostrar(){
        System.out.println("seleccion "+seleccion);
    }
    public void eliminar (ActionEvent event){
        System.out.println("seleccion "+seleccion);
        if (seleccion!=null){
            seleccion.setEliminado(true);
            this.ejbFacade.edit(seleccion);
            this.itemsMeritos.remove(seleccion);
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
     * @return the ejbFacadeTipoMerito
     */
    public TipoMeritoFacade getEjbFacadeTipoMerito() {
        return ejbFacadeTipoMerito;
    }

    /**
     * @param ejbFacadeTipoMerito the ejbFacadeTipoMerito to set
     */
    public void setEjbFacadeTipoMerito(TipoMeritoFacade ejbFacadeTipoMerito) {
        this.ejbFacadeTipoMerito = ejbFacadeTipoMerito;
    }

    /**
     * @return the ejbFacadeTipoCertificado
     */
    public TipoCertificadoFacade getEjbFacadeTipoCertificado() {
        return ejbFacadeTipoCertificado;
    }

    /**
     * @param ejbFacadeTipoCertificado the ejbFacadeTipoCertificado to set
     */
    public void setEjbFacadeTipoCertificado(TipoCertificadoFacade ejbFacadeTipoCertificado) {
        this.ejbFacadeTipoCertificado = ejbFacadeTipoCertificado;
    }

    /**
     * @return the ejbFacadeTipoEvento
     */
    public TipoEventoFacade getEjbFacadeTipoEvento() {
        return ejbFacadeTipoEvento;
    }

    /**
     * @param ejbFacadeTipoEvento the ejbFacadeTipoEvento to set
     */
    public void setEjbFacadeTipoEvento(TipoEventoFacade ejbFacadeTipoEvento) {
        this.ejbFacadeTipoEvento = ejbFacadeTipoEvento;
    }

    /**
     * @return the itemsTipoMerito
     */
    public List <TipoMerito> getItemsTipoMerito() {
        return itemsTipoMerito;
    }

    /**
     * @param itemsTipoMerito the itemsTipoMerito to set
     */
    public void setItemsTipoMerito(List <TipoMerito> itemsTipoMerito) {
        this.itemsTipoMerito = itemsTipoMerito;
    }

    /**
     * @return the itemsTipoCertificado
     */
    public List <TipoCertificado> getItemsTipoCertificado() {
        return itemsTipoCertificado;
    }

    /**
     * @param itemsTipoCertificado the itemsTipoCertificado to set
     */
    public void setItemsTipoCertificado(List <TipoCertificado> itemsTipoCertificado) {
        this.itemsTipoCertificado = itemsTipoCertificado;
    }

    /**
     * @return the itemsTipoEvento
     */
    public List <TipoEvento> getItemsTipoEvento() {
        return itemsTipoEvento;
    }

    /**
     * @param itemsTipoEvento the itemsTipoEvento to set
     */
    public void setItemsTipoEvento(List <TipoEvento> itemsTipoEvento) {
        this.itemsTipoEvento = itemsTipoEvento;
    }

    /**
     * @return the itemsMeritos
     */
    public List<Meritos> getItemsMeritos() {
        return itemsMeritos;
    }

    /**
     * @param itemsMeritos the itemsMeritos to set
     */
    public void setItemsMeritos(List<Meritos> itemsMeritos) {
        this.itemsMeritos = itemsMeritos;
    }

    /**
     * @return the seleccion
     */
    public Meritos getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(Meritos seleccion) {
        this.seleccion = seleccion;
    }

}
