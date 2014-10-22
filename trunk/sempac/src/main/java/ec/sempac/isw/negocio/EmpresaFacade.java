/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Empresa;
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
public class EmpresaFacade extends AbstractFacade<Empresa> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    public List<Empresa> getItemsEmpresa(boolean eliminado) {
        Query query = this.em.createNamedQuery(Empresa.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public Empresa getItemsUserName(String username) {
        Query query = this.em.createNamedQuery(Empresa.findByUsername);
        query.setParameter("username", username);
        try{
            return (Empresa)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public Empresa getUserEmail(String email) {
        Query query = this.em.createNamedQuery(Empresa.findByEmail);
        query.setParameter("correoElectronico", email);
        try{
            return (Empresa)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public Empresa getEmpresa(String username) {
        Query query = this.em.createNamedQuery(Empresa.findByUsernameEmail);
        query.setParameter("username", username);
        query.setParameter("eliminado", false);
        try{
            return (Empresa)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
