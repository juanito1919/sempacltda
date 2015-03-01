package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Pagos;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.PagosFacade;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import ec.sempac.isw.seguridades.Sesion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    private List<Pagos> itemsEspera;
    private List<Pagos> itemsPagoUsuario;
    private String identidad;

    public PagosController() {
        super(Pagos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setSelected(new Pagos());
        //if (ActivacionUsuario.getUsuario() != null) {
        //setUsuario(ActivacionUsuario.getUsuario());
        // setSistemaUsuario(ejbFacadeSistemaUsuario.getUsuarioActivacion(getUsuario().getIdUsuario()));
        itemsEspera = ejbFacade.getItemsEspera();
        itemsPagoUsuario = new ArrayList<Pagos>();
        //}
    }

    @Override
    public void update() {
        if (this.getSelected() != null) {
            this.getSelected().getIdUsuario().getSistemaUsuario().setFechaCaducidad(this.getSelected().getFechaCaducidad());
            super.update(); //To change body of generated methods, choose Tools | Templates.
            ejbFacadeSistemaUsuario.edit(this.getSelected().getIdUsuario().getSistemaUsuario());
            itemsEspera = ejbFacade.getItemsEspera();
            buscarUsuario();
        }
    }

    @Override
    public Pagos prepareCreate(ActionEvent event) {
        return super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null && usuario!=null && usuario.getSistemaUsuario()!=null) {
            usuario.getSistemaUsuario().setFechaCaducidad(this.getSelected().getFechaCaducidad());
            this.getSelected().setIdUsuario(usuario);
            super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
            ejbFacadeSistemaUsuario.edit(usuario.getSistemaUsuario());
            itemsEspera = ejbFacade.getItemsEspera();
            buscarUsuario();
        }
    }

    public void buscarUsuario() {
        System.out.println("pasoo " + identidad);
        if (identidad != null) {
            setUsuario(ejbFacadeUsuario.getUserIdentificador(identidad));
            if (getUsuario() == null) {
                MuestraMensaje.addError("Usuario no registrado");
                itemsPagoUsuario = new ArrayList<Pagos>();
            } else {
                itemsPagoUsuario = ejbFacade.getItemsUsuario(usuario.getIdUsuario());
            }
        } else {
            this.setUsuario(null);
        }
    }

//    public void modificarSistemaUsuario(ActionEvent event) {
//        if (getSistemaUsuario() != null && getUsuario() != null) {
//            System.out.println("si va a guardar");
//            this.create(event);
//            getSistemaUsuario().setEstado('P');
//            getSistemaUsuario().setFechaCaducidad(new Date());/// esto mas 12
//            ejbFacadeSistemaUsuario.edit(getSistemaUsuario());
//            this.getSelected().setIdUsuario(getUsuario());
//            this.getSelected().setFechaRegistro(new Date());
//            this.getSelected().setValor(new BigDecimal(0.0));
//            ejbFacade.create(getSelected());
//        } else {
//            System.out.println("no va a guardar");
//            MuestraMensaje.addError("Seleccione primero un usuario");
//        }
//        setUsuario(null);
//    }

    @Override
    protected void setEmbeddableKeys() {
//        this.getSelected().setIdUsuario(getUsuario());
//        this.getSelected().setFechaRegistro(new Date());
//        this.getSelected().setValor(new BigDecimal(0.0));
        //System.out.println("Usuario:  "+ this.getSelected().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
//        this.setUsuario(new Usuario());
//        this.setCorreoElectronico("");
//        this.getSelected().setIdUsuario(getUsuario());
//        this.getSelected().setFechaRegistro(new Date());
//        this.getSelected().setFechaCaducidad(Sesion.obtieneFechaCaducidad(new Date(), 3, 0));
//        this.getSelected().setValor(new BigDecimal(0.0));
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

    /**
     * @return the itemsEspera
     */
    public List<Pagos> getItemsEspera() {
        for (Pagos espera : itemsEspera) {

        }
        return itemsEspera;
    }

    /**
     * @param itemsEspera the itemsEspera to set
     */
    public void setItemsEspera(List<Pagos> itemsEspera) {
        this.itemsEspera = itemsEspera;
    }

    /**
     * @return the identidad
     */
    public String getIdentidad() {
        return identidad;
    }

    /**
     * @param identidad the identidad to set
     */
    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    /**
     * @return the itemsPagoUsuario
     */
    public List<Pagos> getItemsPagoUsuario() {
        return itemsPagoUsuario;
    }

    /**
     * @param itemsPagoUsuario the itemsPagoUsuario to set
     */
    public void setItemsPagoUsuario(List<Pagos> itemsPagoUsuario) {
        this.itemsPagoUsuario = itemsPagoUsuario;
    }

}
