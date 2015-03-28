package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Carrera;
import ec.sempac.isw.modelo.EstudiosUniversitarios;
import ec.sempac.isw.modelo.EstudiosUniversitariosPK;
import ec.sempac.isw.modelo.ExperienciaLaboral;
import ec.sempac.isw.modelo.Universidad;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.CarreraFacade;
import ec.sempac.isw.negocio.UniversidadFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "estudiosUniversitariosController")
@SessionScoped
public class EstudiosUniversitariosController extends AbstractController<EstudiosUniversitarios> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosUniversitariosFacade ejbFacade;

    @EJB
    private UniversidadFacade ejbFacadeUniversidad;

    @EJB
    private CarreraFacade ejbFacadeCarrera;

    private List<EstudiosUniversitarios> itemsEstudiosUniversitarios;
    private List<Universidad> itemsUniversidad;
    private List<Carrera> itemsCarrera;
    private EstudiosUniversitarios seleccion;
    private String titulo;
    RequestContext context = RequestContext.getCurrentInstance();

    public EstudiosUniversitariosController() {
        super(EstudiosUniversitarios.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        if (ActivacionUsuario.getUsuario() != null) {
            this.setItemsEstudiosUniversitarios(this.ejbFacade.getItemsEstUnivEliminadoUsuario(ActivacionUsuario.getCodigoUsuario(), false));
            this.setItemsCarrera(this.ejbFacadeCarrera.getItemsCarrera(false));
            this.setItemsUniversidad(this.ejbFacadeUniversidad.getItemsUniversidad(false));
        }
    }

    public void crearUniversidad() {
        System.out.println("universidad " + this.getSelected().getUniversidad().getNombre());
        if (this.getSelected().getUniversidad().getNombre().equalsIgnoreCase("OTRA")) {
            //  context.execute("PF('digCrearEmpresa').show();");
            RequestContext.getCurrentInstance().execute("digCrearEmpresa.show()");
        }
    }

    public void iniciarSelectPerfil(Usuario u) {

        if (u != null) {
            this.setItemsEstudiosUniversitarios(this.ejbFacade.getItemsEstUnivEliminadoUsuario(u.getIdUsuario(), false));
            this.setItemsCarrera(this.ejbFacadeCarrera.getItemsCarrera(false));
            this.setItemsUniversidad(this.ejbFacadeUniversidad.getItemsUniversidad(false));
            if (this.getItemsEstudiosUniversitarios() == null) {
                this.setItemsEstudiosUniversitarios(new ArrayList<EstudiosUniversitarios>());
            }
        }
    }

    public void iniciaSelected() {
        this.setSelected(new EstudiosUniversitarios(new EstudiosUniversitariosPK()));
    }

    public void guardaNuevo(ActionEvent event) {
        this.getSelected().setTitulo(titulo);
        if (this.getSelected().getUniversidad() == null || this.getSelected().getCarrera() == null || this.getSelected().getTitulo().equals("")) {
            return;
        }
        for (int i = 0; i < this.itemsEstudiosUniversitarios.size(); i++) {
            if (this.itemsEstudiosUniversitarios.get(i).getUniversidad().getIdUniversidad() == this.getSelected().getUniversidad().getIdUniversidad() && this.itemsEstudiosUniversitarios.get(i).getCarrera().getIdCarrera() == this.getSelected().getCarrera().getIdCarrera() && this.itemsEstudiosUniversitarios.get(i).getTitulo().endsWith(this.getSelected().getTitulo())) {
                return;
            }
        }

        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.save(event);
        this.getItemsEstudiosUniversitarios().add(this.getSelected());
        this.titulo = "";
        this.setSelected(new EstudiosUniversitarios(new EstudiosUniversitariosPK()));
    }

    public String detectarEnlace(String enlace) {
        System.out.println("hola " + enlace);
        if (enlace.length() == 0 || enlace == null || enlace.isEmpty()) {

            return "No hay Archivo";
        } else {
            return "Descargar";
        }

    }

    public void eliminar(ActionEvent event, EstudiosUniversitarios item) {
       
        setSelected(item);
        System.out.println("no entro elimiar");
        if (getSelected() != null) {
            System.out.println("entro eliminar");
            getSelected().setEliminado(true);
            this.ejbFacade.remove(getSelected());
            this.itemsEstudiosUniversitarios.remove(getSelected());
            setSelected(null);
            item = null;
            iniciaSelected();
        }
    }

    public void mostrar() {
        System.out.println(seleccion);
           this.setSelected(getSeleccion());
    }

    public void subirArchivos(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String filePath = ec.getRealPath(String.format(File.separator + "Documentos" + File.separator + ActivacionUsuario.getUsuario().getUsername() + "/%s", file.getFileName()));

        File file2 = new File(ec.getRealPath(File.separator + "Documentos" + File.separator + ActivacionUsuario.getUsuario().getUsername()));
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String rutaRelativa = "../../Documentos" + File.separator + ActivacionUsuario.getUsuario().getUsername() + File.separator + file.getFileName();
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
            Logger.getLogger(ReferenciaPersonalController.class
                    .getName()).log(Level.SEVERE, null, ex);

            return;
        }

        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Archivo Subido"));

        return;
    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getEstudiosUniversitariosPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getEstudiosUniversitariosPK().setIdUniversidad(getSelected().getUniversidad().getIdUniversidad());
        getSelected().getEstudiosUniversitariosPK().setIdCarrera(getSelected().getCarrera().getIdCarrera());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setEstudiosUniversitariosPK(new ec.sempac.isw.modelo.EstudiosUniversitariosPK());
    }

    /**
     * @return the ejbFacadeUniversidad
     */
    public UniversidadFacade getEjbFacadeUniversidad() {
        return ejbFacadeUniversidad;
    }

    /**
     * @param ejbFacadeUniversidad the ejbFacadeUniversidad to set
     */
    public void setEjbFacadeUniversidad(UniversidadFacade ejbFacadeUniversidad) {
        this.ejbFacadeUniversidad = ejbFacadeUniversidad;
    }

    /**
     * @return the ejbFacadeCarrera
     */
    public CarreraFacade getEjbFacadeCarrera() {
        return ejbFacadeCarrera;
    }

    /**
     * @param ejbFacadeCarrera the ejbFacadeCarrera to set
     */
    public void setEjbFacadeCarrera(CarreraFacade ejbFacadeCarrera) {
        this.ejbFacadeCarrera = ejbFacadeCarrera;
    }

    /**
     * @return the itemsUniversidad
     */
    public List<Universidad> getItemsUniversidad() {
        return itemsUniversidad;
    }

    /**
     * @param itemsUniversidad the itemsUniversidad to set
     */
    public void setItemsUniversidad(List<Universidad> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }

    /**
     * @return the itemsCarrera
     */
    public List<Carrera> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * @param itemsCarrera the itemsCarrera to set
     */
    public void setItemsCarrera(List<Carrera> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * @return the itemsEstudiosUniversitarios
     */
    public List<EstudiosUniversitarios> getItemsEstudiosUniversitarios() {
        return itemsEstudiosUniversitarios;
    }

    /**
     * @param itemsEstudiosUniversitarios the itemsEstudiosUniversitarios to set
     */
    public void setItemsEstudiosUniversitarios(List<EstudiosUniversitarios> itemsEstudiosUniversitarios) {
        this.itemsEstudiosUniversitarios = itemsEstudiosUniversitarios;
    }

    /**
     * @return the seleccion
     */
    public EstudiosUniversitarios getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(EstudiosUniversitarios seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
