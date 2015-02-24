package ec.sempac.isw.control;

import ec.sempac.isw.modelo.EmpleoRequerido;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import ec.sempac.isw.seguridades.Empleos;

import java.io.Serializable;
import java.util.Date;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "empleoRequeridoController")
@SessionScoped
public class EmpleoRequeridoController extends AbstractController<EmpleoRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpleoRequeridoFacade ejbFacade;

    public EmpleoRequeridoController() {
        super(EmpleoRequerido.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
 
    }

    protected void setEmbeddableKeys() {
        
    }
    
    public void guardaNuevo(javax.faces.event.ActionEvent event){
       System.out.println("accion valida: ");

        getSelected().setActivo(true);
        getSelected().setFechaModificacion(new Date());
        getSelected().setIdUsuario(ActivacionUsuario.getUsuario());
        getSelected().setIdPersonalRequerido(Empleos.getPersonalRequerido());
        saveNew(event);
        
        System.out.println("dgfdfgfdgfhg: ");
        
    
    }
    
    
    protected void initializeEmbeddableKey() {

    }
}
