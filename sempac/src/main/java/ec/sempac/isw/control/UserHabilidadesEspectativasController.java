package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Espectativas;
import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.modelo.UserHabilidadesEspectativasPK;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.EspectativasFacade;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.negocio.UserHabilidadesEspectativasFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "userHabilidadesEspectativasController")
@SessionScoped
public class UserHabilidadesEspectativasController extends AbstractController<UserHabilidadesEspectativas> implements Serializable {

    @EJB
    private UserHabilidadesEspectativasFacade ejbFacade;

    @EJB
    private HabilidadesFacade ejbFacadeHabilidades;

    @EJB
    private EspectativasFacade ejbFacadeEspectativas;

    private UserHabilidadesEspectativas seleccion;
    private List<UserHabilidadesEspectativas> itemsHabExp;
    private List<Habilidades> itemsHabilidades;
    private List<Espectativas> itemsEspectativas;

    public UserHabilidadesEspectativasController() {
        super(UserHabilidadesEspectativas.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsHabilidades(this.ejbFacadeHabilidades.getItemsHabilidadesEliminado(false));
        this.setItemsEspectativas(this.ejbFacadeEspectativas.getItemsEspectativasEliminado(false));
        if (ActivacionUsuario.getUsuario() != null) {
            this.setItemsHabExp(this.ejbFacade.getItemsHabilidadesUsuario(ActivacionUsuario.getUsuario().getIdUsuario()));
        }
    }

    public void iniciaSelected() {
        this.setSelected(new UserHabilidadesEspectativas());
        this.getSelected().setUserHabilidadesEspectativasPK(new UserHabilidadesEspectativasPK());
    }

    public void guardaNuevo(ActionEvent event) {

        if (this.getSelected().getEspectativas() == null || this.getSelected().getHabilidades() == null) {
            return;
        }
        for (int i = 0; i < this.itemsHabExp.size(); i++) {
            if (this.itemsHabExp.get(i).getEspectativas().getIdEspectativas() == this.getSelected().getEspectativas().getIdEspectativas() && this.itemsHabExp.get(i).getHabilidades().getIdHabilidades() == this.getSelected().getHabilidades().getIdHabilidades()) {
                return;
            }
        }
        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.save(event);
        this.setItemsHabExp(this.ejbFacade.getItemsHabilidadesUsuario(ActivacionUsuario.getUsuario().getIdUsuario()));
        this.setSelected(new UserHabilidadesEspectativas());
        this.getSelected().setUserHabilidadesEspectativasPK(new UserHabilidadesEspectativasPK());
    }

    public void mostrar() {

        this.setSelected(seleccion);
    }

    public void Deselecionar() {

        this.setSelected(null);
    }

    public void eliminar(ActionEvent event, UserHabilidadesEspectativas item) {
        setSelected(item);
        if (getSelected() != null) {
            this.ejbFacade.remove(getSelected());
            this.itemsHabExp.remove(getSelected());
            seleccion = null;
        }

    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getUserHabilidadesEspectativasPK().setIdEspectativas(getSelected().getEspectativas().getIdEspectativas());
        getSelected().getUserHabilidadesEspectativasPK().setIdHabilidades(getSelected().getHabilidades().getIdHabilidades());
        getSelected().getUserHabilidadesEspectativasPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setUserHabilidadesEspectativasPK(new ec.sempac.isw.modelo.UserHabilidadesEspectativasPK());
    }

    /**
     * @return the ejbFacadeHabilidades
     */
    public HabilidadesFacade getEjbFacadeHabilidades() {
        return ejbFacadeHabilidades;
    }

    /**
     * @param ejbFacadeHabilidades the ejbFacadeHabilidades to set
     */
    public void setEjbFacadeHabilidades(HabilidadesFacade ejbFacadeHabilidades) {
        this.ejbFacadeHabilidades = ejbFacadeHabilidades;
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
     * @return the ejbFacadeEspectativas
     */
    public EspectativasFacade getEjbFacadeEspectativas() {
        return ejbFacadeEspectativas;
    }

    /**
     * @param ejbFacadeEspectativas the ejbFacadeEspectativas to set
     */
    public void setEjbFacadeEspectativas(EspectativasFacade ejbFacadeEspectativas) {
        this.ejbFacadeEspectativas = ejbFacadeEspectativas;
    }

    /**
     * @return the itemsEspectativas
     */
    public List<Espectativas> getItemsEspectativas() {
        return itemsEspectativas;
    }

    /**
     * @param itemsEspectativas the itemsEspectativas to set
     */
    public void setItemsEspectativas(List<Espectativas> itemsEspectativas) {
        this.itemsEspectativas = itemsEspectativas;
    }

    /**
     * @return the itemsHabExp
     */
    public List<UserHabilidadesEspectativas> getItemsHabExp() {
        return itemsHabExp;
    }

    /**
     * @param itemsHabExp the itemsHabExp to set
     */
    public void setItemsHabExp(List<UserHabilidadesEspectativas> itemsHabExp) {
        this.itemsHabExp = itemsHabExp;
    }

    /**
     * @return the seleccion
     */
    public UserHabilidadesEspectativas getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(UserHabilidadesEspectativas seleccion) {
        this.seleccion = seleccion;
    }

}
