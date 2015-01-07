package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ReferenciaPersonal;
import ec.sempac.isw.modelo.ReferenciaPersonalPK;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.ReferenciaPersonalFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
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
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "referenciaPersonalController")
@SessionScoped
public class ReferenciaPersonalController extends AbstractController<ReferenciaPersonal> implements Serializable {

    @EJB
    private ReferenciaPersonalFacade ejbFacade;
    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    private List<Usuario> listaUsuario;
    private StreamedContent file;
    public ReferenciaPersonalController() {
        super(ReferenciaPersonal.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaUsuario(ejbFacadeUsuario.getItemsUsuarioEliminado(false));

    }

    public void iniciaSelected() {
        this.setSelected(new ReferenciaPersonal());
        this.getSelected().setReferenciaPersonalPK(new ReferenciaPersonalPK());
    }

    public void guardaNuevo(ActionEvent event) {

        //CAMBIAR estar liena por 
        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(false);
        this.saveNew(event);

    }

    @Override
    protected void setEmbeddableKeys() {
        getSelected().getReferenciaPersonalPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    @Override
    protected void initializeEmbeddableKey() {
        getSelected().setReferenciaPersonalPK(new ec.sempac.isw.modelo.ReferenciaPersonalPK());
    }

    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public void subirArchivos(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String filePath = ec.getRealPath(String.format("/Documentos/%s", file.getFileName()));
        //TODO
        //AGRAGAR LA CARPETA DEL USUARIO
        String rutaRelativa = "../../Documentos/" + file.getFileName();
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

    public void descargarArchivo(ActionEvent eve) {
        
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
        setFile(new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg"));
       
    }

    /**
     * @return the file
     */
    public StreamedContent getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }

}