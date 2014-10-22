package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.ClaseEmpresa;
import ec.sempac.isw.modelo.Empresa;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.ClaseEmpresaFacade;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import ec.sempac.isw.seguridades.Sesion;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "empresaController")
@SessionScoped
public class EmpresaController extends AbstractController<Empresa> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EmpresaFacade ejbFacade;
    
    @EJB
    private PaisFacade ejbFacadePais;

    @EJB
    private RegionFacade ejbFacadeProvincia;

    @EJB
    private CiudadFacade ejbFacadeCiudad;
    
    @EJB
    private ClaseEmpresaFacade ejbFacadeClaseEmpresa;

    private List<Empresa> itemsEmpresa;
    private List<Pais> itemPaises;
    private Pais pais;
    private List<Region> itemProvincias;
    private Region provincia;
    private List<Ciudad> itemCiudades;
    private Ciudad ciudad;
    private List<ClaseEmpresa> itemClaseEmpresa;
    
    private String contrasena;
    private String confirmaContrasena;
    private String msj;
    
    public EmpresaController() {
        super(Empresa.class);
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsEmpresa(this.ejbFacade.getItemsEmpresa(false));
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.setItemClaseEmpresa(this.ejbFacadeClaseEmpresa.getItemsClaseEmpresa(false));
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public void cambiaPais() {
        if (pais != null) {
            this.setItemProvincias(this.ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais()));
        } else {
            this.setItemProvincias(null);
        }
        this.itemCiudades = null;
        if (this.getSelected().getIdCiudad() != null) {
            this.getSelected().setIdCiudad(null);
        }
    }

    public void cambiaProvincia() {
        if (provincia != null) {
            this.setItemCiudades(this.ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
        } else {
            this.setItemCiudades(null);
        }
        if (this.getSelected().getIdCiudad() != null) {
            this.getSelected().setIdCiudad(null);
        }
    }
    
    public void preparaEditar(){        
        this.pais=this.getSelected().getIdCiudad().getIdRegion().getIdPais();
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.provincia=this.getSelected().getIdCiudad().getIdRegion();
        this.setItemProvincias(this.ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais()));
        this.setItemCiudades(this.ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
    }
    
    public void preparaCrear(){
        this.setSelected(new Empresa());
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.setItemProvincias(null);
        this.setItemCiudades(null);
        this.pais=null;
        this.provincia=null;
        this.ciudad=null;
    }

    public void revisaNombre() {
        setMsj("");
        Empresa user = this.ejbFacade.getItemsUserName(this.getSelected().getUsername());
        if (user != null) {
            System.out.println(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
            setMsj(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
            MuestraMensaje.addError(getMsj());
        }
    }
    
    public void revisaMail() {
        msj = "";
        Empresa user = this.ejbFacade.getItemsUserName(this.getSelected().getCorreoElectronico());
        if (user != null) {
            msj = ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste");
            MuestraMensaje.addError(msj);
        }
    }
    
    public void revisaContasena() {
        msj = "";
        if (!this.contrasena.equals(this.confirmaContrasena)) {
            msj = ResourceBundle.getBundle("/BundleMensajesES").getString("ContrasenaNoConisiden");
            MuestraMensaje.addError(msj);
        }
    }
    
    public void guardar(ActionEvent event){
        boolean ok = true;
        revisaContasena();
        if (msj != "") {
            ok = false;
        }
        revisaMail();
        if (msj != "") {
            ok = false;
        }
        revisaNombre();
        if (msj != "") {
            ok = false;
        }
        if (ok) {
            try {
                this.getSelected().setContrasena(Sesion.MD5(contrasena));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.getSelected().setEliminado(false);
            this.save(event);
            this.setItemsEmpresa(this.ejbFacade.getItemsEmpresa(false));
            this.setSelected(null);
        }    
    }
    
    public void editar(ActionEvent event){
        boolean ok = true;
        revisaMail();
        if (msj != "") {
            ok = false;
        }
        revisaNombre();
        if (msj != "") {
            ok = false;
        }
        if (ok) {
            this.getSelected().setEliminado(false);
            this.save(event);
            this.setItemsEmpresa(this.ejbFacade.getItemsEmpresa(false));
            this.setSelected(null);
        }    
    }
    
    public void eliminar(ActionEvent event){
        this.getSelected().setEliminado(true);
        this.save(event);
        this.setItemsEmpresa(this.ejbFacade.getItemsEmpresa(false));
        this.setSelected(null);
    }
    
    /**
     * @return the ejbFacadePais
     */
    public PaisFacade getEjbFacadePais() {
        return ejbFacadePais;
    }

    /**
     * @param ejbFacadePais the ejbFacadePais to set
     */
    public void setEjbFacadePais(PaisFacade ejbFacadePais) {
        this.ejbFacadePais = ejbFacadePais;
    }

    /**
     * @return the ejbFacadeProvincia
     */
    public RegionFacade getEjbFacadeProvincia() {
        return ejbFacadeProvincia;
    }

    /**
     * @param ejbFacadeProvincia the ejbFacadeProvincia to set
     */
    public void setEjbFacadeProvincia(RegionFacade ejbFacadeProvincia) {
        this.ejbFacadeProvincia = ejbFacadeProvincia;
    }

    /**
     * @return the ejbFacadeCiudad
     */
    public CiudadFacade getEjbFacadeCiudad() {
        return ejbFacadeCiudad;
    }

    /**
     * @param ejbFacadeCiudad the ejbFacadeCiudad to set
     */
    public void setEjbFacadeCiudad(CiudadFacade ejbFacadeCiudad) {
        this.ejbFacadeCiudad = ejbFacadeCiudad;
    }

    /**
     * @return the itemPaises
     */
    public List<Pais> getItemPaises() {
        return itemPaises;
    }

    /**
     * @param itemPaises the itemPaises to set
     */
    public void setItemPaises(List<Pais> itemPaises) {
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
    public List<Region> getItemProvincias() {
        return itemProvincias;
    }

    /**
     * @param itemProvincias the itemProvincias to set
     */
    public void setItemProvincias(List<Region> itemProvincias) {
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
    public List<Ciudad> getItemCiudades() {
        return itemCiudades;
    }

    /**
     * @param itemCiudades the itemCiudades to set
     */
    public void setItemCiudades(List<Ciudad> itemCiudades) {
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
     * @return the ejbFacadeClaseEmpresa
     */
    public ClaseEmpresaFacade getEjbFacadeClaseEmpresa() {
        return ejbFacadeClaseEmpresa;
    }

    /**
     * @param ejbFacadeClaseEmpresa the ejbFacadeClaseEmpresa to set
     */
    public void setEjbFacadeClaseEmpresa(ClaseEmpresaFacade ejbFacadeClaseEmpresa) {
        this.ejbFacadeClaseEmpresa = ejbFacadeClaseEmpresa;
    }

    /**
     * @return the itemClaseEmpresa
     */
    public List<ClaseEmpresa> getItemClaseEmpresa() {
        return itemClaseEmpresa;
    }

    /**
     * @param itemClaseEmpresa the itemClaseEmpresa to set
     */
    public void setItemClaseEmpresa(List<ClaseEmpresa> itemClaseEmpresa) {
        this.itemClaseEmpresa = itemClaseEmpresa;
    }

    /**
     * @return the itemsEmpresa
     */
    public List<Empresa> getItemsEmpresa() {
        return itemsEmpresa;
    }

    /**
     * @param itemsEmpresa the itemsEmpresa to set
     */
    public void setItemsEmpresa(List<Empresa> itemsEmpresa) {
        this.itemsEmpresa = itemsEmpresa;
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
     * @return the msj
     */
    public String getMsj() {
        return msj;
    }

    /**
     * @param msj the msj to set
     */
    public void setMsj(String msj) {
        this.msj = msj;
    }


}
