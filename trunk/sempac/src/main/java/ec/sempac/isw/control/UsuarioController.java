package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.Validaciones;
import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Espectativas;
import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.modelo.UserHabilidadesEspectativasPK;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.EspectativasFacade;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import ec.sempac.isw.negocio.SistemaAccesoFacade;
import ec.sempac.isw.negocio.SistemaUsuarioFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import ec.sempac.isw.seguridades.Sesion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

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

    @EJB
    private SistemaUsuarioFacade ejbFacadeSistemaUsuario;

    @EJB
    private SistemaAccesoFacade ejbFacadeSistemaAcceso;

    @EJB
    private HabilidadesFacade ejbFacadeHabilidades;

    @EJB
    private EspectativasFacade ejbFacadeEspectativas;

    private String DIRECTORIO_DOCUMENTOS = "c:\\documentos";
    private String DIRECTORIO_TEMP = "c://temp";
    private List<Pais> itemPaises;
    private Pais pais;
    private List<Region> itemProvincias;
    private Region provincia;
    private List<Ciudad> itemCiudades;
    private Ciudad ciudad;
    private String confirmaContrasena;
    private String contrasena;
    private String msj;
    private Usuario seleccionado;
    private Habilidades habilidadBusqueda;
    private Espectativas espectativaBusqueda;
    private String direccion; 
    private CroppedImage croppeFoto;
    private String imageTemp;

    private List<UserHabilidadesEspectativas> itemsHabilidadesEspectativas;
    private UserHabilidadesEspectativas HabilidadesEspectativaSeleccionado;

    private List<Usuario> listaUsuariosBusqueda;
    private List<Usuario> itemsUsuariosSeleccionados;
    private List<Habilidades> itemsHabilidades;
    private List<Espectativas> itemsEspectativas;

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.setSelected(new Usuario());
    }
    
    public void actualizarUsuario(ActionEvent evet){
        this.save(evet);
    }

    public void iniciarBusqueda() {
        this.listaUsuariosBusqueda = this.ejbFacade.getItemsUsuarioEliminado(false);
        this.setItemsHabilidades(this.ejbFacadeHabilidades.getItemsHabilidadesEliminado(false));
        this.setItemsEspectativas(this.ejbFacadeEspectativas.getItemsEspectativasEliminado(false));
        this.itemsHabilidadesEspectativas = new ArrayList<UserHabilidadesEspectativas>();
        prepareNuevo();
    }

    public void cargarUsuariosBusqueda() {
        this.itemsUsuariosSeleccionados = new ArrayList<Usuario>();
        boolean agregar = false;
        if (!(itemsHabilidadesEspectativas.isEmpty() && pais == null && provincia == null && ciudad == null)) {
            for (int i = 0; i < listaUsuariosBusqueda.size(); i++) {
                Usuario us = listaUsuariosBusqueda.get(i);
                for (int j = 0; j < itemsHabilidadesEspectativas.size(); j++) {
                    List<UserHabilidadesEspectativas> habEsp = us.getUserHabilidadesEspectativasList();
                    for (int k = 0; k < habEsp.size(); k++) {
                        if (itemsHabilidadesEspectativas.get(j).getHabilidades().equals(habEsp.get(k).getHabilidades())
                                && itemsHabilidadesEspectativas.get(j).getEspectativas().equals(habEsp.get(k).getEspectativas())) {
                            agregar = true;
                            break;
                        }
                    }
                    if (agregar) {
                        break;
                    }
                }
                /// Ubicacion
                if (pais != null && agregar) {
                    if (!us.getIdCiudad().getIdRegion().getIdPais().equals(pais)) {
                        agregar = false;
                    }
                }
                if (provincia != null && agregar) {
                    if (!us.getIdCiudad().getIdRegion().equals(provincia)) {
                        agregar = false;
                    }
                }
                if (ciudad != null && agregar) {
                    if (!us.getIdCiudad().equals(ciudad)) {
                        agregar = false;
                    }
                }
                if (!direccion.equals("") && agregar) {
                    if (!us.getDireccion().contains(direccion)) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    itemsUsuariosSeleccionados.add(us);
                    agregar = false;
                }
            }
        }
    }

    public void AgragarHabEspBusqueda(ActionEvent event) {

        System.out.println(habilidadBusqueda);
        System.out.println(espectativaBusqueda);
        UserHabilidadesEspectativas nuevo = new UserHabilidadesEspectativas();
        nuevo.setEspectativas(espectativaBusqueda);
        nuevo.setHabilidades(habilidadBusqueda);
        nuevo.setUserHabilidadesEspectativasPK(new UserHabilidadesEspectativasPK(0, espectativaBusqueda.getIdEspectativas(), habilidadBusqueda.getIdHabilidades()));
        this.itemsHabilidadesEspectativas.add(nuevo);
        cargarUsuariosBusqueda();
//        this.HabilidadesEspectativaSeleccionado.setUserHabilidadesEspectativasPK(new UserHabilidadesEspectativasPK(0, HabilidadesEspectativaSeleccionado.getEspectativas().getIdEspectativas(), HabilidadesEspectativaSeleccionado.getHabilidades().getIdHabilidades()));
//        this.HabilidadesEspectativaSeleccionado.setUsuario(new Usuario());
//        
//        this.itemsHabilidadesEspectativas.add(HabilidadesEspectativaSeleccionado);
        //this.HabilidadesEspectativaSeleccionado=new UserHabilidadesEspectativas();
    }

    public void QuitarHabEspBusqueda(ActionEvent event) {
        this.itemsHabilidadesEspectativas.remove(HabilidadesEspectativaSeleccionado);
        cargarUsuariosBusqueda();
    }

    public void cambiaPaisBusqueda() {
        cambiaPais();
        cargarUsuariosBusqueda();
    }

    public void cambiaProvinciaBusqueda() {
        cambiaProvincia();
        cargarUsuariosBusqueda();
    }

    public void cambiaCiudadBusqueda() {
        cargarUsuariosBusqueda();
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

    public void asignarUsuario() {
        System.out.println("uruario " + ActivacionUsuario.getUsuario());
        this.setSelected(ActivacionUsuario.getUsuario());
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

    public void close() throws ServletException {
        Sesion.cerrarSesion();
        // ejbFacadeSistemaAcceso.remove(null);
        this.init();

    }

    public void validaSession() throws IOException {
        Sesion.validaSesion();
    }

    public void revisaContasena() {
        System.out.println("Entroooo " + this.contrasena + " " + confirmaContrasena);
        this.getSelected().setContrasena(contrasena);
        if (!this.getSelected().getContrasena().equals(this.confirmaContrasena)) {
            this.setMsj(ResourceBundle.getBundle("/BundleMensajesES").getString("ContrasenaNoConisiden"));
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("ContrasenaNoConisiden"));
        }
    }

    public void revisaNombre() {
        System.out.println("Usuario " + this.getSelected().getUsername());
        Usuario user = this.ejbFacade.getItemsUserName(this.getSelected().getUsername());
        if (user != null) {
            this.setMsj(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }

    public void revisaMail() {
        System.out.println("Mail " + this.getSelected().getCorreoElectronico());
        Usuario user = this.ejbFacade.getItemsCorreo(this.getSelected().getCorreoElectronico());
        if (user != null) {
            this.setMsj(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }

    public void prepareNuevo() {
        System.err.println("entro nuevo");
        this.setSelected(new Usuario());
        this.setCiudad(null);
        this.setProvincia(null);
        this.setPais(null);
        this.setContrasena("");
        this.setConfirmaContrasena("");
        this.direccion="";
    }

    public void registraCuenta(ActionEvent event) {
        if (this.getSelected().getTipoIdentidad() == 'C') {
            if (!Validaciones.validaCedula(this.getSelected().getIdentidad())) {
                System.out.println("cedula error");
                MuestraMensaje.addError("Cedula incorrecta");
                return;
            }
        }
        System.out.println("Entro guardar");
        this.setMsj("");
        boolean ok = true;
        revisaContasena();
        if (getMsj() != "") {
            ok = false;
        }
        revisaMail();
        if (getMsj() != "") {
            ok = false;
        }
        revisaNombre();
        if (getMsj() != "") {
            ok = false;
        }
        if (ok) {
            try {
                this.getSelected().setContrasena(Sesion.MD5(contrasena));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.getSelected().setTipo('U');
            this.getSelected().setEliminado(false);
            this.saveNew(event);

            SistemaUsuario sisUser = new SistemaUsuario();
            this.setSelected(this.ejbFacade.getItemsUserName(this.getSelected().getUsername()));
            sisUser.setIdUsuario(this.getSelected().getIdUsuario());
            sisUser.setFechaAsignacion(new Date());
            sisUser.setEstado('V');
            sisUser.setTiempoBloqueo(0);
            this.ejbFacadeSistemaUsuario.create(sisUser);
            File miDir = new File(".");
            File folder;
            try {
                folder = new File(miDir.getCanonicalPath() + File.separator + "Documentos" + File.separator + getSelected().getUsername());
                folder.mkdirs();
            } catch (IOException ex) {

            }
        } else {
            MuestraMensaje.addError(msj);
        }
    }

    public void actionFoto() {
        this.setCroppeFoto(null);
        this.setImageTemp(null);
    }
    public void hola(ActionEvent evet){
        System.out.println("holaaAA");
    }
    public void guardarFoto() {
        System.out.println("como estassssss");
    }

    public void actionGuardarFoto() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        DIRECTORIO_DOCUMENTOS = servletContext.getRealPath("")+ File.separatorChar + "Documntos" + File.separatorChar;

        String archivo = DIRECTORIO_DOCUMENTOS + this.getSelected().getUsername() + ".jpg";
        System.out.println("comoooooooo: " + archivo);
       // getSelected().setUrl(archivo);
        try {

            if (getCroppeFoto() != null) {
                FileImageOutputStream imageOutput = new FileImageOutputStream(new File(archivo));
                imageOutput.write(getCroppeFoto().getBytes(), 0, getCroppeFoto().getBytes().length);
                imageOutput.close();
            } else {
                OutputStream outStream = new FileOutputStream(new File(archivo));
                InputStream inputStream = new FileInputStream(DIRECTORIO_DOCUMENTOS + "temp/" + this.getImageTemp());
                byte[] buffer = new byte[6124];
                int bulk;
                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    outStream.write(buffer, 0, bulk);
                    outStream.flush();
                }
                outStream.close();
                inputStream.close();
            }

            actionFoto();

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void uploadFile(FileUploadEvent event) {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            DIRECTORIO_DOCUMENTOS = servletContext.getRealPath("")
                    + File.separatorChar + "Documentos" + File.separatorChar;

           
            String archivo = DIRECTORIO_DOCUMENTOS + "temp" + File.separatorChar + event.getFile().getFileName();
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            byte[] buffer = new byte[6124];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();

            this.setImageTemp(event.getFile().getFileName());

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo"));
        }
    }
    
    public void subirFoto(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String filePath = ec.getRealPath(String.format("/Documentos/%s", file.getFileName()));
       //TODO
        //AGRAGAR LA CARPETA DEL USUARIO
        String rutaRelativa="../../Documentos/"+file.getFileName();
        try {
            this.getSelected().setUrl(rutaRelativa);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[6124];
            int bulk;
            InputStream inputStream = file.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaPersonalController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Archivo Subido"));
        
       
        return;
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

    /**
     * @return the ejbFacadeSistemaUsuario
     */
    public SistemaUsuarioFacade getEjbFacadeSistemaUsuario() {
        return ejbFacadeSistemaUsuario;
    }

    /**
     * @param ejbFacadeSistemaUsuario the ejbFacadeSistemaUsuario to set
     */
    public void setEjbFacadeSistemaUsuario(SistemaUsuarioFacade ejbFacadeSistemaUsuario) {
        this.ejbFacadeSistemaUsuario = ejbFacadeSistemaUsuario;
    }

    /**
     * @return the seleccionado
     */
    public Usuario getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the itemsUsuariosSeleccionados
     */
    public List<Usuario> getItemsUsuariosSeleccionados() {
        return itemsUsuariosSeleccionados;
    }

    /**
     * @param itemsUsuariosSeleccionados the itemsUsuariosSeleccionados to set
     */
    public void setItemsUsuariosSeleccionados(List<Usuario> itemsUsuariosSeleccionados) {
        this.itemsUsuariosSeleccionados = itemsUsuariosSeleccionados;
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
     * @return the itemsHabilidadesEspectativas
     */
    /**
     * @return the HabilidadesEspectativaSeleccionado
     */
    public UserHabilidadesEspectativas getHabilidadesEspectativaSeleccionado() {
        return HabilidadesEspectativaSeleccionado;
    }

    /**
     * @param HabilidadesEspectativaSeleccionado the
     * HabilidadesEspectativaSeleccionado to set
     */
    public void setHabilidadesEspectativaSeleccionado(UserHabilidadesEspectativas HabilidadesEspectativaSeleccionado) {
        this.HabilidadesEspectativaSeleccionado = HabilidadesEspectativaSeleccionado;
    }

    /**
     * @return the itemsHabilidadesEspectativas
     */
    public List<UserHabilidadesEspectativas> getItemsHabilidadesEspectativas() {
        return itemsHabilidadesEspectativas;
    }

    /**
     * @param itemsHabilidadesEspectativas the itemsHabilidadesEspectativas to
     * set
     */
    public void setItemsHabilidadesEspectativas(List<UserHabilidadesEspectativas> itemsHabilidadesEspectativas) {
        this.itemsHabilidadesEspectativas = itemsHabilidadesEspectativas;
    }

    /**
     * @return the habilidadBusqueda
     */
    public Habilidades getHabilidadBusqueda() {
        return habilidadBusqueda;
    }

    /**
     * @param habilidadBusqueda the habilidadBusqueda to set
     */
    public void setHabilidadBusqueda(Habilidades habilidadBusqueda) {
        this.habilidadBusqueda = habilidadBusqueda;
    }

    /**
     * @return the espectativaBusqueda
     */
    public Espectativas getEspectativaBusqueda() {
        return espectativaBusqueda;
    }

    /**
     * @param espectativaBusqueda the espectativaBusqueda to set
     */
    public void setEspectativaBusqueda(Espectativas espectativaBusqueda) {
        this.espectativaBusqueda = espectativaBusqueda;
    }

    /**
     * @return the croppeFoto
     */
    public CroppedImage getCroppeFoto() {
        return croppeFoto;
    }

    /**
     * @param croppeFoto the croppeFoto to set
     */
    public void setCroppeFoto(CroppedImage croppeFoto) {
        this.croppeFoto = croppeFoto;
    }

    /**
     * @return the imageTemp
     */
    public String getImageTemp() {
        return imageTemp;
    }

    /**
     * @param imageTemp the imageTemp to set
     */
    public void setImageTemp(String imageTemp) {
        this.imageTemp = imageTemp;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
