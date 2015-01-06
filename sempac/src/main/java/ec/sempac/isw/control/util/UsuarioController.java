package ec.sempac.isw.control.util;

import ec.sempac.isw.control.AbstractController;
import ec.sempac.isw.modelo.Usuario;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private ec.sempac.isw.negocio.UsuarioFacade ejbFacade;

    public UsuarioController() {
    }



    @Override
    protected void setEmbeddableKeys() {//cuando mandamos a guardar
    }

    @Override
    protected void initializeEmbeddableKey() {/// cuando abrimos al pagina o el dialogo
    }


//    public Usuario prepareCreate() {
//        selected = new Usuario();
//        initializeEmbeddableKey();
//        return selected;
//    }

}
