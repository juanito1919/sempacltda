package ec.sempac.isw.control;

import ec.sempac.isw.modelo.ExperienciaLaboral;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.seguridades.ActivacionUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "experienciaLaboralController")
@SessionScoped
public class ExperienciaLaboralController extends AbstractController<ExperienciaLaboral> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.ExperienciaLaboralFacade ejbFacade;

    private List<ExperienciaLaboral> itemsExperienciaLaboral;

    private ExperienciaLaboral seleccion;

    public ExperienciaLaboralController() {
        super(ExperienciaLaboral.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //borrar esta parte

        this.setItemsExperienciaLaboral(this.ejbFacade.getItemsMeritoEliminadoUsuario(ActivacionUsuario.getUsuario().getIdUsuario(), false));
    }

    public void iniciaSelected() {
        this.setSelected(new ExperienciaLaboral());
    }

    public String cambiarFormatoFecha(Date date) {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(date);
    }

    public void guardaNuevo(ActionEvent event) {
        if (getSelected() != null) //CAMBIAR estar liena por 
        {
            this.getSelected().setIdUsuario(ActivacionUsuario.getUsuario());

            this.getSelected().setEliminado(false);
            this.save(event);
            this.setItemsExperienciaLaboral(this.ejbFacade.getItemsMeritoEliminadoUsuario(ActivacionUsuario.getUsuario().getIdUsuario(), false));
            this.setSelected(new ExperienciaLaboral());
        }
    }

    public void selecionar() {
        this.setSelected(seleccion);

    }

    public void eliminar(ActionEvent event, ExperienciaLaboral item) {
        setSelected(item);
        if (getSelected() != null) {
            getSelected().setEliminado(true);
            this.ejbFacade.remove(getSelected());
            this.itemsExperienciaLaboral.remove(getSelected());
            setSelected(null);
            item = null;
            iniciaSelected();
        }
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
            System.out.println("direcion antes: "+this.getSelected().getUrl());
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
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    /**
     * @return the itemsExperienciaLaboral
     */
    public List<ExperienciaLaboral> getItemsExperienciaLaboral() {
        return itemsExperienciaLaboral;
    }

    /**
     * @param itemsExperienciaLaboral the itemsExperienciaLaboral to set
     */
    public void setItemsExperienciaLaboral(List<ExperienciaLaboral> itemsExperienciaLaboral) {
        this.itemsExperienciaLaboral = itemsExperienciaLaboral;
    }

    /**
     * @return the seleccion
     */
    public ExperienciaLaboral getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(ExperienciaLaboral seleccion) {
        this.seleccion = seleccion;
    }

}
