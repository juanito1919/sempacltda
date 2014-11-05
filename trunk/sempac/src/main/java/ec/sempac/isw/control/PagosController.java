package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Pagos;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.PagosFacade;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.Sesion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "pagosController")
@SessionScoped
public class PagosController extends AbstractController<Pagos> implements Serializable {

    @EJB
    private PagosFacade ejbFacade;
    @EJB
    private UsuarioFacade ejbFacadeUsuario;
    @EJB
    private SistemaUsuarioFacade ejbFacadeSistemaUsuario;

    private SistemaUsuario sistemaUsuario;
    private Usuario usuario;
    private String correoElectronico;

    public PagosController() {
        super(Pagos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setUsuario(null);
        setSistemaUsuario(null);
    }

    public void buscarUsuario() {
        System.out.println("pasoo");
        setUsuario(ejbFacadeUsuario.getUsuario(correoElectronico));
        if (getUsuario() == null) {
            System.out.println("si encontro");
            MuestraMensaje.addError("Usuario no registrado");
        } else {
            setSistemaUsuario(ejbFacadeSistemaUsuario.find(getUsuario().getIdUsuario()));
        }
    }

    public void modificarSistemaUsuario(ActionEvent event) {
        if (getSistemaUsuario() != null && getUsuario()!=null) {
            System.out.println("si va a guardar");
            this.create(event);
            getSistemaUsuario().setEstado('P');
            getSistemaUsuario().setFechaCaducidad(this.getSelected().getFechaCaducidad());
            ejbFacadeSistemaUsuario.edit(getSistemaUsuario());
        } else {
            System.out.println("no va a guardar");
            MuestraMensaje.addError("Seleccione primero un usuario");
        }
        setUsuario(null);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().setIdUsuario(usuario);
        //System.out.println("Usuario:  "+ this.getSelected().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.setUsuario(new Usuario());
        this.setCorreoElectronico("");
        this.getSelected().setIdUsuario(getUsuario());
        this.getSelected().setFechaRegistro(new Date());
        this.getSelected().setFechaCaducidad(Sesion.obtieneFechaCaducidad(new Date(), 3, 0));
        this.getSelected().setValor(new BigDecimal(0.0));
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the sistemaUsuario
     */
    public SistemaUsuario getSistemaUsuario() {
        return sistemaUsuario;
    }

    /**
     * @param sistemaUsuario the sistemaUsuario to set
     */
    public void setSistemaUsuario(SistemaUsuario sistemaUsuario) {
        this.sistemaUsuario = sistemaUsuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
