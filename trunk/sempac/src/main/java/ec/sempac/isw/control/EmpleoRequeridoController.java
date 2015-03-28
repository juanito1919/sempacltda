package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.EmpleoRequerido;
import ec.sempac.isw.modelo.PersonalRequerido;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import ec.sempac.isw.seguridades.Empleos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "empleoRequeridoController")
@SessionScoped
public class EmpleoRequeridoController extends AbstractController<EmpleoRequerido> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpleoRequeridoFacade ejbFacade;
    private List<EmpleoRequerido> listEmpleoRequerigo;

    public EmpleoRequeridoController() {
        super(EmpleoRequerido.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        if (ActivacionUsuario.getEmpresa() != null) {
            setListEmpleoRequerigo(ejbFacade.getListEmpleoRequerido(ActivacionUsuario.getEmpresa().getIdEmpresa()));
        }
    }

    protected void setEmbeddableKeys() {

    }
    public void contratarEmpleado(javax.faces.event.ActionEvent event,EmpleoRequerido empleoRequ){
      if(empleoRequ!=null){
          this.setSelected(empleoRequ);
         this.getSelected().setEliminado(true);
         this.save(event);
         setListEmpleoRequerigo(ejbFacade.getListEmpleoRequerido(ActivacionUsuario.getEmpresa().getIdEmpresa()));
      }
    }

    public void guardaNuevo(javax.faces.event.ActionEvent event, PersonalRequerido personal) {
        System.out.println("accion valida: ");

        if (personal != null) {
            EmpleoRequerido empleoRequerido = ejbFacade.getEmpleoRequerido(personal.getIdPersonalRequerido(), ActivacionUsuario.getUsuario().getIdUsuario());
            if (empleoRequerido == null) {
                getSelected().setActivo(true);
                getSelected().setFechaModificacion(new Date());
                getSelected().setIdUsuario(ActivacionUsuario.getUsuario());
                getSelected().setIdPersonalRequerido(personal);
                save(event);
                personal = null;
                RequestContext.getCurrentInstance().execute("dlg2.show()");
            } else {
                MuestraMensaje.addError("El Empleo Selecionado ya se encuentra registrado");
                personal = null;
            }
        } else {
            MuestraMensaje.addError("Selecione el trabajo que desea aplicar");
        }

    }

    protected void initializeEmbeddableKey() {

    }

    /**
     * @return the listEmpleoRequerigo
     */
    public List<EmpleoRequerido> getListEmpleoRequerigo() {
        return listEmpleoRequerigo;
    }

    /**
     * @param listEmpleoRequerigo the listEmpleoRequerigo to set
     */
    public void setListEmpleoRequerigo(List<EmpleoRequerido> listEmpleoRequerigo) {
        this.listEmpleoRequerigo = listEmpleoRequerigo;
    }
}
