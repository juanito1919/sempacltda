package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;
    
    @EJB
    private PaisFacade ejbFacadePais;
    
    @EJB
    private RegionFacade ejbFacadeProvincia;
    
    @EJB
    private CiudadFacade ejbFacadeCiudad;
    
    private List <Pais> itemPaises;
    private Pais pais;
    private List <Region> itemProvincias;
    private Region provincia;
    private List <Ciudad> itemCiudades;
    private Ciudad ciudad;
    private String confirmaContrasena;
    private String contrasena;

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.setSelected(new Usuario());
    }
    
    public void cambiaPais(){
        if (pais!=null)
            this.setItemProvincias(this.ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais()));
        else
            this.setItemProvincias(null);            
        this.itemCiudades=null;
        if (this.getSelected().getIdCiudad()!=null)this.getSelected().setIdCiudad(null);
    }
    

    public void asignarUsuario() {
        System.out.println("uruario " + ActivacionUsuario.getUsuario());
        this.setSelected(ActivacionUsuario.getUsuario());
    }

    public void cambiaProvincia(){
        if (provincia!=null)
            this.setItemCiudades(this.ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
        else
            this.setItemCiudades(null);
        if (this.getSelected().getIdCiudad()!=null)this.getSelected().setIdCiudad(null);
    }
    
    public void revisaContasena(){
        System.out.println("Entroooo "+this.contrasena+" "+confirmaContrasena);
        this.getSelected().setContrasena(contrasena);
        if (!this.getSelected().getContrasena().equals(this.confirmaContrasena)){
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("ContrasenaNoConisiden"));
        }
    }
        
    public void revisaNombre(){
        System.out.println("Usuario "+this.getSelected().getUsername());
        Usuario user=this.ejbFacade.getItemsUserName(this.getSelected().getUsername());
        if (user!=null){
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }
    
    public void revisaMail(){
        System.out.println("Mail "+this.getSelected().getCorreoElectronico());
        Usuario user=this.ejbFacade.getItemsCorreo(this.getSelected().getCorreoElectronico());
        if (user!=null){
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }
    
    public void registraCuenta(ActionEvent event){
        System.out.println("Entro guardar");
        this.getSelected().setTipo('U');
        this.getSelected().setEliminado(false);
        this.saveNew(event);
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }
    
    
  
    /**
     * @return the itemPaises
     */
    public List <Pais> getItemPaises() {
        return itemPaises;
    }

    /**
     * @param itemPaises the itemPaises to set
     */
    public void setItemPaises(List <Pais> itemPaises) {
        this.itemPaises = itemPaises;
    }

    /**
     * @return the pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * @return the itemProvincias
     */
    public List <Region> getItemProvincias() {
        return itemProvincias;
    }

    /**
     * @param itemProvincias the itemProvincias to set
     */
    public void setItemProvincias(List <Region> itemProvincias) {
        this.itemProvincias = itemProvincias;
    }

    /**
     * @return the provincia
     */
    public Region getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(Region provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the itemCiudades
     */
    public List <Ciudad> getItemCiudades() {
        return itemCiudades;
    }

    /**
     * @param itemCiudades the itemCiudades to set
     */
    public void setItemCiudades(List <Ciudad> itemCiudades) {
        this.itemCiudades = itemCiudades;
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the confirmaContrasena
     */
    public String getConfirmaContrasena() {
        return confirmaContrasena;
    }

    /**
     * @param confirmaContrasena the confirmaContrasena to set
     */
    public void setConfirmaContrasena(String confirmaContrasena) {
        this.confirmaContrasena = confirmaContrasena;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
