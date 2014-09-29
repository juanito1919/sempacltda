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
    public Usuario getItemsPais(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsernameEmail);
        query.setParameter("username", username);
        query.setParameter("eliminado", false);
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
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
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public Usuario getItemsUserName(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsername);
        query.setParameter("username", username);
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
