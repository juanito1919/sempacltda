/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author miguesaca
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario getUsuario(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsernameEmail);
        query.setParameter("username", username);
        query.setParameter("eliminado", false);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioPorPais(short pais) {
        Query query = this.em.createNamedQuery(Usuario.findByPais);
        query.setParameter("pais", pais);
        query.setParameter("eliminado", false);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioBusquedaAvanzadaNingunaUbicacion(List<Long> espectativa, List<Long> habilidad, Character pasante, String barrio) {
        Query query;

        query = this.em.createNamedQuery(Usuario.findByAvanzadaNinguna);

     
        query.setParameter("espectativa", espectativa);
        query.setParameter("habilidad", habilidad);
        query.setParameter("tipo", pasante);
        query.setParameter("eliminado", false);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioBusquedaAvanzadaPais(short pais, List<Long> espectativa, List<Long> habilidad, Character pasante, String barrio) {
        Query query;

        query = this.em.createNamedQuery(Usuario.findByAvanzadaPais);

        query.setParameter("pais", pais);
        query.setParameter("espectativa", espectativa);
        query.setParameter("habilidad", habilidad);
        query.setParameter("tipo", pasante);
        query.setParameter("eliminado", false);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioBusquedaAvanzadaPaisRegion(short pais, int region, List<Long> espectativa, List<Long> habilidad, Character pasante, String barrio) {
        Query query;

        query = this.em.createNamedQuery(Usuario.findByAvanzadaPaisyRegion);

        query.setParameter("pais", pais);
        query.setParameter("espectativa", espectativa);
        query.setParameter("region", region);
        query.setParameter("habilidad", habilidad);
        query.setParameter("tipo", pasante);
        query.setParameter("eliminado", false);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioBusquedaAvanzada(short pais, int region, int ciudad, List<Long> espectativa, List<Long> habilidad, Character pasante, String barrio) {

        Query query;

        query = this.em.createNamedQuery(Usuario.findByAvanzada);

        query.setParameter("pais", pais);
        query.setParameter("region", region);
        query.setParameter("ciudad", ciudad);
        query.setParameter("espectativa", espectativa);
        query.setParameter("habilidad", habilidad);
        query.setParameter("tipo", pasante);
        query.setParameter("eliminado", false);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuario(short pais, int region) {
        Query query = this.em.createNamedQuery(Usuario.findByPaisYregion);
        query.setParameter("pais", pais);
        query.setParameter("region", region);
        query.setParameter("eliminado", false);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioPorPaisyRegionYciudad(short pais, int region, int ciudad) {
        Query query = this.em.createNamedQuery(Usuario.findByPaisyRgionYciudad);
        query.setParameter("pais", pais);
        query.setParameter("region", region);
        query.setParameter("ciudad", ciudad);
        query.setParameter("eliminado", false);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getUsuarioPorPaisyRegion(short pais, int region) {
        Query query = this.em.createNamedQuery(Usuario.findByPaisYregion);
        query.setParameter("pais", pais);
        query.setParameter("region", region);

        query.setParameter("eliminado", false);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> getItemsUsuarioEliminado(boolean eliminado) {
        Query query = this.em.createNamedQuery(Usuario.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    public Usuario getItemsCorreo(String correoElectronico) {
        Query query = this.em.createNamedQuery(Usuario.findByCorreoElectronico);
        query.setParameter("correoElectronico", correoElectronico);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario getItemsUserName(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsername);
        query.setParameter("username", username);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario getUserEmail(String email) {
        Query query = this.em.createNamedQuery(Usuario.findByEmail);
        query.setParameter("correoElectronico", email);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario getUserIdentificador(String identidad) {
        Query query = this.em.createNamedQuery(Usuario.findByIdentidad);
        query.setParameter("identidad", identidad);
        query.setParameter("eliminado", false);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Es nulo");
            return null;
        }
    }
}
