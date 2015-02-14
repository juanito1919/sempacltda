package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.negocio.PaisFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "paisController")
@SessionScoped
public class PaisController extends AbstractController<Pais> implements Serializable {

    @EJB
    private PaisFacade ejbFacade;

    public PaisController() {
        super(Pais.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItems(ejbFacade.findAll());
    }

    @Override
    public void create(ActionEvent event) {
        super.create(event); //To change body of generated methods, choose Tools | Templates.
        //ejbFacade.create(this.getSelected());
        this.setItems(ejbFacade.findAll());
        //System.out.println("tamano:" + this.getItems().size());
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }
}
