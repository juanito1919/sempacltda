/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control;

import ec.sempac.isw.modelo.Ciudad;
import ec.sempac.isw.modelo.Espectativas;
import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.Pais;
import ec.sempac.isw.modelo.Region;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
import ec.sempac.isw.modelo.Usuario;
import ec.sempac.isw.negocio.CiudadFacade;
import ec.sempac.isw.negocio.EspectativasFacade;
import ec.sempac.isw.negocio.HabilidadesFacade;
import ec.sempac.isw.negocio.PaisFacade;
import ec.sempac.isw.negocio.RegionFacade;
import ec.sempac.isw.negocio.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pablito
 */
@ManagedBean(name = "usuarioBusquedaController")
@SessionScoped
public class UsuarioBusquedaController {

    @EJB
    private UsuarioFacade ejbUsuarioFacade;
    @EJB
    private HabilidadesFacade ejbFacadeHabilidades;

    @EJB
    private EspectativasFacade ejbFacadeEspectativas;
     @EJB
    private PaisFacade ejbFacadePais;

    @EJB
    private RegionFacade ejbFacadeProvincia;

    @EJB
    private CiudadFacade ejbFacadeCiudad;

    private Usuario selecionado;
    private List<Usuario> itemsUsuariosSeleccionados;
    private List<Pais> itemPaises;
    private Pais pais;
    private List<Region> itemProvincias;
    private Region provincia;
    private List<Ciudad> itemCiudades;
    private Ciudad ciudad;
    private char tipoUsuario;
    private List<UserHabilidadesEspectativas> itemsHabilidadesEspectativas;
    private UserHabilidadesEspectativas HabilidadesEspectativaSeleccionado;
    private List<Habilidades> itemsHabilidades;
    private List<Espectativas> itemsEspectativas;

    public UsuarioBusquedaController() {
    }

    /**
     * @return the selecionado
     */
    public Usuario getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(Usuario selecionado) {
        this.selecionado = selecionado;
    }

    public void buscarUsuarioBasico() {

        this.setItemsUsuariosSeleccionados(null);
        if (getPais() != null && getProvincia() == null && getCiudad() == null) {
            this.setItemsUsuariosSeleccionados(ejbUsuarioFacade.getUsuarioPorPais(getPais().getIdPais()));
        } else if (getPais() != null && getProvincia() != null && getCiudad() == null) {

            this.setItemsUsuariosSeleccionados(ejbUsuarioFacade.getUsuarioPorPaisyRegion(getPais().getIdPais(), getProvincia().getIdRegion()));
        } else if (getPais() != null && getProvincia() != null && getCiudad() != null) {

            this.setItemsUsuariosSeleccionados(ejbUsuarioFacade.getUsuarioPorPaisyRegionYciudad(getPais().getIdPais(), getProvincia().getIdRegion(), getCiudad().getIdCiudad()));
        } else {
            this.setItemsUsuariosSeleccionados(ejbUsuarioFacade.findAll());
        }

    }

    public void busquedaAvanzada() {

        List<Long> listHabilidades = new ArrayList<Long>();
        List<Long> listEspectaitivas = new ArrayList<Long>();
        listEspectaitivas.clear();
        listHabilidades.clear();
        tieneHabilidades(listHabilidades);
        tieneespectitivas(listEspectaitivas);

        this.itemsUsuariosSeleccionados = null;
        if (pais == null && provincia == null && ciudad == null) {
            this.itemsUsuariosSeleccionados = ejbUsuarioFacade.getUsuarioBusquedaAvanzadaNingunaUbicacion(listEspectaitivas, listHabilidades, getTipoUsuario(), "");
        }
        if (pais != null && provincia == null && ciudad == null) {
            this.itemsUsuariosSeleccionados = ejbUsuarioFacade.getUsuarioBusquedaAvanzadaPais(pais.getIdPais(), listEspectaitivas, listHabilidades, getTipoUsuario(), "");
        }
        if (pais != null && provincia != null && ciudad == null) {
            this.itemsUsuariosSeleccionados = ejbUsuarioFacade.getUsuarioBusquedaAvanzadaPaisRegion(pais.getIdPais(), provincia.getIdRegion(), listEspectaitivas, listHabilidades, getTipoUsuario(), "");
        }
        if (pais != null && provincia != null && ciudad != null) {
            this.itemsUsuariosSeleccionados = ejbUsuarioFacade.getUsuarioBusquedaAvanzada(pais.getIdPais(), provincia.getIdRegion(), ciudad.getIdCiudad(), listEspectaitivas, listHabilidades, getTipoUsuario(), "");
        }
    }

    private void tieneHabilidades(List<Long> listHabilidades) {
        if (getItemsHabilidades() == null || getItemsHabilidades().isEmpty()) {
            setItemsHabilidades(ejbFacadeHabilidades.findAll());
        }
        for (Habilidades habilidade : getItemsHabilidades()) {
            listHabilidades.add(habilidade.getIdHabilidades());
        }

    }

    private void tieneespectitivas(List<Long> listEspectativas) {
        if (getItemsEspectativas() == null || getItemsEspectativas().isEmpty()) {
            setItemsEspectativas(ejbFacadeEspectativas.findAll());
        }
        for (Espectativas espectativas : getItemsEspectativas()) {
            listEspectativas.add(espectativas.getIdEspectativas());
        }
    }

    public void cambiaPaisBusqueda() {
        cambiaPais();
        //cargarUsuariosBusqueda();
    }

    public void cambiaProvinciaBusqueda() {
        cambiaProvincia();
        //  cargarUsuariosBusqueda();
    }

    public void cambiaCiudadBusqueda() {
        // cargarUsuariosBusqueda();
    }

    public void cambiaPais() {
        if (pais != null) {
            this.setItemProvincias(this.ejbFacadeProvincia.getItemsReionesPais(false, pais.getIdPais()));
        } else {
            this.setItemProvincias(null);
        }
        this.itemCiudades = null;
        if (this.getSelecionado().getIdCiudad() != null) {
            this.getSelecionado().setIdCiudad(null);
        }
    }

    public void cambiaProvincia() {
        if (provincia != null) {
            this.setItemCiudades(this.ejbFacadeCiudad.getItemsReionesPais(false, provincia.getIdRegion()));
        } else {
            this.setItemCiudades(null);
        }
        if (this.getSelecionado().getIdCiudad() != null) {
            this.getSelecionado().setIdCiudad(null);
        }
    }

    public String obtenerDisponibilidad(String valor) {
        if (valor.equalsIgnoreCase("C")) {
            return "Tiempo Completo";
        } else if (valor.equalsIgnoreCase("C")) {
            return "Medio Tiempo";
        } else {
            return "Otro";
        }
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
     * @return the tipoUsuario
     */
    public char getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(char tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
}
