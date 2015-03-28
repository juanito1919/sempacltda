package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.Escuela;
import ec.sempac.isw.modelo.EstudiosPrimaria;
import ec.sempac.isw.modelo.Usuario;

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

    private String nombre = "";

    public EstudiosPrimariaController() {
        super(EstudiosPrimaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

    }

    public void limpiarNombreEscuala() {
        this.setNombre(null);

    }

    public void limpiarEscuala() {
        this.getSelected().setEscuela(null);
    }

    public void iniciaSelected() {
        if (ActivacionUsuario.getUsuario() != null) {
            List<EstudiosPrimaria> estudios = ejbFacade.getItemsByIdUsuario(ActivacionUsuario.getUsuario().getIdUsuario(), false);
            if (!estudios.isEmpty() && estudios != null) {
                this.setSelected(estudios.get(0));
            } else {
                this.setSelected(new EstudiosPrimaria());
            }

        }

    }

    public void iniciaSelectedPerfil(Usuario user) {

    }

    public void guardarEscuela(ActionEvent event) {
        if ((getNombre() != null && getNombre().length() > 0) || getSelected().getEscuela() != null) {
            if (getNombre() != null && getNombre().length() > 0) {
                System.out.println("entro1");
                Escuela escuela = new Escuela();
                escuela.setNombre(getNombre());
                escuela.setEliminado(false);
                ejbFacadeEscuela.create(escuela);
                escuela = ejbFacadeEscuela.getIEscuela(getNombre());
                this.getSelected().setEscuela(escuela);
            }

            if (this.getSelected().getEscuela() != null) {
                System.out.println("entro2: " + ActivacionUsuario.getUsuario());
                this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
                this.getSelected().setEliminado(false);
                this.save(event);
                nombre = null;
            }
        } else {
            MuestraMensaje.addAdvertencia("Hay Campos Vacios");
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
