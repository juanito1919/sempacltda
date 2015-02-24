package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ReferenciaPersonal;
import ec.sempac.isw.modelo.ReferenciaPersonalPK;
import ec.sempac.isw.negocio.ReferenciaPersonalFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.File;
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

    private List<ReferenciaPersonal> itemsReferenciaPersonal;
    private ReferenciaPersonal seleccion;
    private StreamedContent file;

    public ReferenciaPersonalController() {
        super(ReferenciaPersonal.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsReferenciaPersonal(ejbFacade.getItemsReferenciasEliminadoUsuario(ActivacionUsuario.getCodigoUsuario(), false));
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
        this.setItemsReferenciaPersonal(ejbFacade.getItemsReferenciasEliminadoUsuario(ActivacionUsuario.getCodigoUsuario(), false));

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

    /**
     * @return the seleccion
     */
    public ReferenciaPersonal getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(ReferenciaPersonal seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * @return the itemsReferenciaPersonal
     */
    public List<ReferenciaPersonal> getItemsReferenciaPersonal() {
        return itemsReferenciaPersonal;
    }

    /**
     * @param itemsReferenciaPersonal the itemsReferenciaPersonal to set
     */
    public void setItemsReferenciaPersonal(List<ReferenciaPersonal> itemsReferenciaPersonal) {
        this.itemsReferenciaPersonal = itemsReferenciaPersonal;
    }

}
