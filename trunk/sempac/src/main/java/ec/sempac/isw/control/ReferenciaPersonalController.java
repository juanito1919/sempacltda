package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ReferenciaPersonal;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.ReferenciaPersonalFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "referenciaPersonalController")
@SessionScoped
public class ReferenciaPersonalController  extends AbstractController<ReferenciaPersonal>implements Serializable {

    @EJB
    private ReferenciaPersonalFacade ejbFacade;
    @EJB
    private UsuarioFacade ejbFacadeUsuario;
    
    private List<Usuario> listaUsuario;
    
    public ReferenciaPersonalController() {
        super(ReferenciaPersonal.class);
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaUsuario(ejbFacadeUsuario.getItemsUsuarioEliminado(false));
        
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getReferenciaPersonalPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }
    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setReferenciaPersonalPK(new ec.sempac.isw.modelo.ReferenciaPersonalPK());
    }

    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public void iniciaSelected(){
        this.setSelected(new ReferenciaPersonal());
    }
    
   

}
