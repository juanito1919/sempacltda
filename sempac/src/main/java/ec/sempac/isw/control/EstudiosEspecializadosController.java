package ec.sempac.isw.control;

import ec.sempac.isw.modelo.CampoEstudio;
import ec.sempac.isw.modelo.EstudiosEspecializados;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.TipoEstudio;
import ec.sempac.isw.modelo.Universidad;
import ec.sempac.isw.negocio.CampoEstudioFacade;
import ec.sempac.isw.negocio.TipoEstudioFacade;
import ec.sempac.isw.negocio.UniversidadFacade;
import ec.sempac.isw.seguridades.ActivacionUsuario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "estudiosEspecializadosController")
@SessionScoped
public class EstudiosEspecializadosController extends AbstractController<EstudiosEspecializados> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.EstudiosEspecializadosFacade ejbFacade;
    @EJB
    private UniversidadFacade ejbFacadeUniversidad;
    @EJB
    private CampoEstudioFacade ejbFacadeCampoEstudio;
    @EJB
    private TipoEstudioFacade ejbFacadeTipoEstudio;

    private List<EstudiosEspecializados> itemsEstudiosEspecializados;
    private List<Universidad> itemsUniversidad;

    private Pais pais;
    private TipoEstudio tipoEstudio;
    private CampoEstudio campoEstudio;
    private Universidad universidad;
    private String titulo;

    public EstudiosEspecializadosController() {
        super(EstudiosEspecializados.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new EstudiosEspecializados());
        tipoEstudio = new TipoEstudio();
        campoEstudio = new CampoEstudio();
        universidad = new Universidad();
        this.getSelected().setIdCampoEstudio(campoEstudio);
        this.getSelected().setIdTipoEstudio(tipoEstudio);
        this.getSelected().setIdUniversidad(universidad);
        if (ActivacionUsuario.getUsuario() != null && ActivacionUsuario.getUsuario().getIdUsuario() != null) {
            itemsEstudiosEspecializados = ejbFacade.getItemsEspecializacionUser(ActivacionUsuario.getUsuario().getIdUsuario());

        } else {
            itemsEstudiosEspecializados = null;
        }
        pais = null;
        itemsUniversidad = null;
    }

    public void selectUniversidad() {
        if (pais != null) {
            itemsUniversidad = this.ejbFacadeUniversidad.getItemsUniversidadPais(pais.getIdPais());
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        System.out.println("Si ingresa...");
        this.setSelected(new EstudiosEspecializados());
        this.getSelected().setIdCampoEstudio(campoEstudio);
        this.getSelected().setIdTipoEstudio(tipoEstudio);
        this.getSelected().setIdUniversidad(universidad);
        this.getSelected().setTitulo(titulo);
        
        TipoEstudio auxTipoEstudio;
        CampoEstudio auxCampoEstudio;
//        String nulo = (this.getSelected() == null) ? "selected"
//                : (this.getSelected().getIdTipoEstudio() == null) ? "selected tipo estudio"
//                        : (ActivacionUsuario.getUsuario() == null) ? "activacion usuario" : "Universidad";
//        System.out.println("el nulo es: " + nulo);
        if (this.getSelected() != null
                && this.getSelected().getIdTipoEstudio() != null
                && ActivacionUsuario.getUsuario() != null
                && this.getSelected().getIdUniversidad() != null) {
            this.getSelected().setIdUsuario(ActivacionUsuario.getUsuario());

            //Creando tipo estudio
            auxTipoEstudio = ejbFacadeTipoEstudio.getTipoEstidoDescripcion(getSelected().getIdTipoEstudio().getDescripcion());
            if (auxTipoEstudio == null) {
                ejbFacadeTipoEstudio.create(this.getSelected().getIdTipoEstudio());
                auxTipoEstudio = ejbFacadeTipoEstudio.getTipoEstidoDescripcion(getSelected().getIdTipoEstudio().getDescripcion());//salir si sigue null
                if (auxTipoEstudio == null) {
                    System.out.println("No se pude crear tipo de estudio");
                    return;
                }
            }
            getSelected().setIdTipoEstudio(auxTipoEstudio);

            //creando campo estudio
            if (this.getSelected().getIdCampoEstudio() != null) {
                auxCampoEstudio = ejbFacadeCampoEstudio.getCampoNombre(getSelected().getIdCampoEstudio().getNombre());
                if (auxCampoEstudio == null) {
                    ejbFacadeCampoEstudio.create(this.getSelected().getIdCampoEstudio());
                    auxCampoEstudio = ejbFacadeCampoEstudio.getCampoNombre(getSelected().getIdCampoEstudio().getNombre());
                    if (auxCampoEstudio == null) {
                        System.out.println("No se pude crear campo de estudio");
                        return;
                    }
                }
                getSelected().setIdCampoEstudio(auxCampoEstudio);
            }
            //creando especialalizacion
            super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        }

    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    /**
     * @return the itemsEstudiosEspecializados
     */
    public List<EstudiosEspecializados> getItemsEstudiosEspecializados() {
        return itemsEstudiosEspecializados;
    }

    /**
     * @param itemsEstudiosEspecializados the itemsEstudiosEspecializados to set
     */
    public void setItemsEstudiosEspecializados(List<EstudiosEspecializados> itemsEstudiosEspecializados) {
        this.itemsEstudiosEspecializados = itemsEstudiosEspecializados;
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
     * @return the tipoEstudio
     */
    public TipoEstudio getTipoEstudio() {
        return tipoEstudio;
    }

    /**
     * @param TipoEstudio the tipoEstudio to set
     */
    public void setTipoEstudio(TipoEstudio TipoEstudio) {
        this.tipoEstudio = TipoEstudio;
    }

    /**
     * @return the campoEstudio
     */
    public CampoEstudio getCampoEstudio() {
        return campoEstudio;
    }

    /**
     * @param CampoEstudio the campoEstudio to set
     */
    public void setCampoEstudio(CampoEstudio CampoEstudio) {
        this.campoEstudio = CampoEstudio;
    }

    /**
     * @return the universidad
     */
    public Universidad getUniversidad() {
        return universidad;
    }

    /**
     * @param universidad the universidad to set
     */
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
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
