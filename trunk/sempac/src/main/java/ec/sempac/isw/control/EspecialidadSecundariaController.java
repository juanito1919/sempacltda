package ec.sempac.isw.control;

import ec.sempac.isw.control.util.MuestraMensaje;
import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.EspecialidadSecundaria;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.ColegioFacade;
import ec.sempac.isw.negocio.EspecialidadSecundariaFacade;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "especialidadSecundariaController")
@SessionScoped
public class EspecialidadSecundariaController extends AbstractController<EspecialidadSecundaria> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EspecialidadSecundariaFacade ejbFacade;
    @EJB
    private ColegioFacade ejbFacadeColegio;
    @EJB
    private EspecialidadSecundariaFacade ejbFacadeEspecialidaSec;

    private String nombre = null;

    public EspecialidadSecundariaController() {
        super(EspecialidadSecundaria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

    }

    public void iniciaSelected() {
        if (ActivacionUsuario.getUsuario() != null) {
            List<EspecialidadSecundaria> espSec = ejbFacadeEspecialidaSec.getItemsByIdUsuario(ActivacionUsuario.getUsuario().getIdUsuario(), false);
            if (!espSec.isEmpty()) {
                this.setSelected(espSec.get(0));
            } else {
                this.setSelected(new EspecialidadSecundaria());
            }
        }
    }

    public void limpiarColegio() {
        this.getSelected().setColegio(null);

    }

    public void limpiarNombreColegio() {

        this.setNombre(null);

    }

    public void iniciaSelectedPerfil(Usuario u) {
        this.setSelected(null);
        if (u != null) {
            List<EspecialidadSecundaria> espSec = ejbFacadeEspecialidaSec.getItemsByIdUsuario(u.getIdUsuario(), false);
            if (!espSec.isEmpty()) {
                this.setSelected(espSec.get(0));
            }
        }
    }

    public void guardarColegio(ActionEvent event) {

        if ((getNombre() != null && getNombre().length() > 0) || getSelected().getColegio() != null) {
            if (getNombre() != null && getNombre().length() > 0) {

                Colegio colegio = new Colegio();
                colegio.setNombre(getNombre());
                colegio.setEliminado(false);
                ejbFacadeColegio.create(colegio);
                colegio = ejbFacadeColegio.getColegio(getNombre());
                this.getSelected().setColegio(colegio);
            }

            if (this.getSelected().getColegio() != null) {

                this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
                this.getSelected().setEliminado(false);
                this.save(event);
                nombre = null;
            }
        } else {
            MuestraMensaje.addAdvertencia("Hay Campos Vacios");
        }

    }

    public void eliminarColegio(ActionEvent event) {
        this.getSelected().setUsuario(ActivacionUsuario.getUsuario());
        this.getSelected().setEliminado(true);
        this.ejbFacade.remove(this.getSelected());
        this.setSelected(new EspecialidadSecundaria());
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

        getSelected().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    /**
     * @return the itemColegio
     */
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
