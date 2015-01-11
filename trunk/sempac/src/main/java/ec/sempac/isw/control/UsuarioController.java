package ec.sempac.isw.control;

import ec.sempac.isw.control.util.JsfUtil;
import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.control.util.Validaciones;
import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Espectativas;
import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.Pagos;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.modelo.UserHabilidadesEspectativasPK;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.EspectativasFacade;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.negocio.PagosFacade;
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
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

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
    private boolean estadoPago;

    private List<UserHabilidadesEspectativas> itemsHabilidadesEspectativas;
    private UserHabilidadesEspectativas HabilidadesEspectativaSeleccionado;

    private List<Usuario> listaUsuariosBusqueda;
    private List<Usuario> itemsUsuariosSeleccionados;
    private List<Habilidades> itemsHabilidades;
    private List<Espectativas> itemsEspectativas;
////MIO
    private List<Usuario> items = null;
    private Usuario selected;

    private Date fMaxima;
    private Pagos pagos;
    @EJB
    private PagosFacade ejbFacadePagos;
    ///

    public UsuarioController() {
    }

    @PostConstruct
    public void init() {
        this.setItemPaises(this.ejbFacadePais.getItemsPais(false));
        this.setSelected(new Usuario());
        pagos = new Pagos();
        //mio
        itemPaises = ejbFacadePais.getItemsPais(false);
        selected = new Usuario();
        fMaxima = new Date(new Date().getYear() - 12, new Date().getMonth(), new Date().getDate());//fecha actual menos 12 anios
    }

    public void actualizarUsuario(ActionEvent evet) {
        this.save(evet);
    }

    public void verificarEstadoPago() {

        Usuario user = this.ejbFacadeSistemaUsuario.getUsuario(ActivacionUsuario.getCodigoUsuario(), 'P');
        if (user != null) {
            estadoPago=true;
        } else {
            estadoPago=false;
        }

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

    public void revisaisMail() {
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
        this.setDireccion("");
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
        revisaisMail();
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

    public void hola(ActionEvent evet) {
        System.out.println("holaaAA");
    }

    public void guardarFoto() {
        System.out.println("como estassssss");
    }

    public void actionGuardarFoto() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        DIRECTORIO_DOCUMENTOS = servletContext.getRealPath("") + File.separatorChar + "Documntos" + File.separatorChar;

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
        String filePath = ec.getRealPath(String.format("/Documentos/" + this.getSelected().getUsername() + "/%s", file.getFileName()));
        //TODO
        //AGRAGAR LA CARPETA DEL USUARIO
        String rutaRelativa = "../../Documentos/" + File.separator + this.getSelected().getUsername() + File.separator + file.getFileName();
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
///MIO

    public void selectPais() {
        selected.setIdCiudad(null);
        setItemCiudades(null);
        provincia = null;
        itemProvincias = null;
        if (pais != null) {
            itemProvincias = ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais());

        } else {
            itemProvincias = null;
        }
    }

    public void selectProvincia() {
        selected.setIdCiudad(null);
        if (pais != null && provincia != null) {
            setItemCiudades(ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
        } else {
            setItemCiudades(null);
        }
    }

    public void validarUsername() {
        if (selected.getUsername() != null) {
            if (ejbFacade.getItemsUserName(selected.getUsername()) != null) {
                MuestraMensaje.addError("Ya existe, usuario con username: " + selected.getUsername() + "\' Ingrese Otro\'");
                System.out.println("Ya existe");
                selected.setUsername(null);
            } else {
                System.out.println(" User correcto");
                MuestraMensaje.addSatisfactorio("Correcto");
            }
        }
    }

    public void validarContrasena() {
        if (contrasena != null) {
            if (contrasena.length() < 6 || contrasena.length() > 15) {
                MuestraMensaje.addError("La contrasena debe tener minimo 6 caracteres y maximo 16");
                System.out.println("La contrasena debe tener minimo 6 caracteres y maximo 16");
                selected.setContrasena(null);
                contrasena = null;
            } else {
                MuestraMensaje.addSatisfactorio("Correcto");
                System.out.println("Pass correcto");
            }
            confirmaContrasena = null;
        }
    }

    public void validarConfirmacionContrasena() {
        if (contrasena != null) {
            if (confirmaContrasena != null) {
                if (!contrasena.equals(confirmaContrasena)) {
                    MuestraMensaje.addError("No coinciden las contrasenas");
                    System.out.println("no coinciden las pass");
                    confirmaContrasena = null;
                } else {
                    cifrarContrasena();
                    MuestraMensaje.addSatisfactorio("Verificacion Correcta");
                    System.out.println("verificacion correcta");
                }
            } else {
                MuestraMensaje.addError("Ingrese la confirmacion de contrasena");
            }
        } else {
            confirmaContrasena = null;
        }
    }

    public void cifrarContrasena() {
        if (contrasena != null) {
            try {
                selected.setContrasena(Sesion.MD5(contrasena));
                System.out.println("contra:" + selected.getContrasena());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MuestraMensaje.addError("Ingrese una contrasena");
        }
    }

    public void validadIdentidad() {
        if (selected.getTipoIdentidad() != null) {
            if (selected.getTipoIdentidad() == 'C') {

                if (!Validaciones.validaCedula(selected.getIdentidad())) {
                    MuestraMensaje.addError("Cedula incorrecta");
                    System.out.println("tipo incorrecto");
                } else {
                    MuestraMensaje.addSatisfactorio("Correcta");
                    System.out.println("tipo correcto");
                }
            }
        }
    }

    public void validaTipoIdentidad() {
        System.out.println("esto es TI: " + selected.getTipoIdentidad());
        selected.setIdentidad(null);
        if (selected.getTipoIdentidad() == null) {
            return;
        }
        if (selected.getTipoIdentidad() == '-') {
            selected.setTipoIdentidad(null);
            return;
        }
        return;
    }

    private void crearSistemaUsuario() {
        SistemaUsuario sistemaUsuario = new SistemaUsuario();
        sistemaUsuario.setIdUsuario((ejbFacade.getItemsUserName(selected.getUsername())).getIdUsuario());
        sistemaUsuario.setFechaAsignacion(new Date());
        sistemaUsuario.setEstado('V');//V = valido, P = Pago
        sistemaUsuario.setTiempoBloqueo(0);
        ejbFacadeSistemaUsuario.create(sistemaUsuario);
        File miDir = new File(".");
        File folder;
        try {
            folder = new File(miDir.getCanonicalPath() + File.separator + "Documentos" + File.separator + getSelected().getUsername());
            folder.mkdirs();
        } catch (IOException ex) {
            MuestraMensaje.addError("No se pudo crear el directorio de archivos");
        }
        Usuario user = ejbFacade.getItemsUserName(selected.getUsername());
        ActivacionUsuario.setUsuario(user);
        ActivacionUsuario.setCodigoUsuario(user.getIdUsuario());
        ActivacionUsuario.setCodigoPeriodo(String.valueOf(new Date().getYear() + 1900));
        ActivacionUsuario.setCambiarContrasena(false);

        try {
            System.out.println("p0ndras la direccion");
            Sesion.redireccionaPagina("http://localhost:8080/sempac/faces/configuraciones/inicioEmpleado/inicioEmpleado.xhtml");
        } catch (Exception ex) {
            MuestraMensaje.addError("No se pudo iniciar la session");
        }
    }

    public void revisaMail() {
        System.out.println("Mail " + this.getSelected().getCorreoElectronico());
        Usuario user = this.ejbFacade.getItemsCorreo(this.getSelected().getCorreoElectronico());
        if (user != null) {
            selected.setCorreoElectronico(null);
            MuestraMensaje.addError(ResourceBundle.getBundle("/BundleMensajesES").getString("UserNameExiste"));
        }
    }

    //
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

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected boolean setEmbeddableKeys() {

        if (selected != null) {
            if (selected.getTipoIdentidad() != null && selected.getTipoIdentidad() != '-') {
                if (selected.getIdentidad() != null) {
                    if (selected.getNombres() != null) {
                        if (selected.getFechaNacimiento() != null) {
                            if (selected.getContrasena() != null) {
                                if (selected.getIdCiudad() != null) {
                                    if (selected.getDireccion() != null) {
                                        if (selected.getEstadoCivil() != null) {
                                            if (selected.getCorreoElectronico() != null) {
                                                if (selected.getDisponibilidad() != null) {
                                                    selected.setTipo('U');

                                                    System.out.println("11: " + selected.getTipo());
                                                    return true;
                                                } else {
                                                    System.out.println("10: " + selected.getDisponibilidad());
                                                    MuestraMensaje.addError("Seleccione su disponibilidad");
                                                    return false;
                                                }
                                            } else {
                                                System.out.println("9: " + selected.getCorreoElectronico());
                                                MuestraMensaje.addError("Ingrese su correo electronico");
                                                return false;
                                            }
                                        } else {
                                            System.out.println("8: " + selected.getEstadoCivil());
                                            MuestraMensaje.addError("Ingrese su estado civil");
                                            return false;
                                        }
                                    } else {
                                        System.out.println("7: " + selected.getDireccion());
                                        MuestraMensaje.addError("Ingrese la direccion");
                                        return false;
                                    }
                                } else {
                                    System.out.println("6: " + selected.getIdCiudad().getNombre());
                                    MuestraMensaje.addError("Ingrese el Pais, Provincia/region, ciudad correctamente");
                                    return false;
                                }
                            } else {
                                System.out.println("5: " + selected.getContrasena());
                                MuestraMensaje.addError("Ingrese la contrasena");
                                return false;
                            }
                        } else {
                            System.out.println("4: " + selected.getFechaNacimiento());
                            MuestraMensaje.addError("Ingrese la fecha de su nacimiento");
                            return false;
                        }
                    } else {
                        System.out.println("3: " + selected.getNombres());
                        MuestraMensaje.addError("Ingrese los nombres");
                        return false;
                    }
                } else {
                    System.out.println("2: " + selected.getIdentidad());
                    MuestraMensaje.addError("Ingrese la identidad identidad");
                    return false;
                }
            } else {
                System.out.println("1: " + selected.getTipoIdentidad());
                MuestraMensaje.addError("Ingrese tipo de identidad");
                return false;
            }
        } else {
            MuestraMensaje.addError("Ingrese datos");
            return false;
        }
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, "Usuario Creado correctamente");
        crearSistemaUsuario();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundles").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundles").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void saveNew(ActionEvent event) {

        String msg = "";
        persist(JsfUtil.PersistAction.CREATE, msg);
        if (!JsfUtil.isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void save(ActionEvent event) {
        String msg = "";// = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(JsfUtil.PersistAction.UPDATE, msg);
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            System.out.println("entroo...");
            if (setEmbeddableKeys()) {
                System.out.println("saliooo.;;");
                try {
                    if (persistAction != JsfUtil.PersistAction.DELETE) {
                        getFacade().edit(selected);
                    } else {
                        getFacade().remove(selected);
                    }
                    JsfUtil.addSuccessMessage(successMessage);

                } catch (EJBException ex) {
                    String msg = "";
                    Throwable cause = ex.getCause();
                    if (cause != null) {
                        msg = cause.getLocalizedMessage();
                    }
                    if (msg.length() > 0) {
                        JsfUtil.addErrorMessage(msg);
                    } else {
                        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundles").getString("PersistenceErrorOccured"));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundles").getString("PersistenceErrorOccured"));
                }
            } else {
                JsfUtil.addErrorMessage("No se pudo registar al usuario... intentelo de nuevo");
            }
        }
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public Date getFMaxima() {
        return fMaxima;
    }

    public void setFMaxima(Date fMaxima) {
        this.fMaxima = fMaxima;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public void pagosDeposito(ActionEvent event) {

        Usuario user = ActivacionUsuario.getUsuario();
        System.out.println("codigo user.." + user.getIdUsuario());
        SistemaUsuario su = ejbFacadeSistemaUsuario.getUsuarioActivacion(user.getIdUsuario());
        int mes = 0;
        int anio = new Date().getYear();
        if (new Date().getMonth() == 10) {
            mes = 1;
            anio += 1;
        } else if (new Date().getMonth() == 11) {
            mes = 2;
            anio += 1;
        } else if (new Date().getMonth() == 12) {
            mes = 3;
            anio += 1;
        } else {
            mes += 3;
        }
        Date caducidad = new Date(anio, mes, new Date().getDate());//fecha actual mas 3 meses

        Pagos aux = ejbFacadePagos.getPago(user.getIdUsuario());
        if (aux != null) {
            aux.setEliminado(true);
            ejbFacadePagos.edit(aux);
        }
        pagos.setIdUsuario(user);
        pagos.setFechaRegistro(new Date());
        pagos.setFechaCaducidad(caducidad); ////  fecha de registro mas 3 meses
        pagos.setValor(new BigDecimal(0.0));

        ejbFacadePagos.create(pagos);
        su.setFechaCaducidad(caducidad);////  fecha de registro mas 3 meses
        su.setEstado('P');
        ejbFacadeSistemaUsuario.edit(su);

        System.out.println("Stisfactorio....");
    }
}
