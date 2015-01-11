/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Pagos;
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
public class PagosFacade extends AbstractFacade<Pagos> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosFacade() {
        super(Pagos.class);
    }
    
    public Pagos getPago(long idUsuario) {
        Query query = this.em.createNamedQuery(Pagos.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("eliminado", false);
        try {
            //if(query.getResultList().size()>1){
                
           // }else{
            return (Pagos) query.getSingleResult();
           // }
        } catch (NoResultException e) {
            return null;
        }
    }
}
