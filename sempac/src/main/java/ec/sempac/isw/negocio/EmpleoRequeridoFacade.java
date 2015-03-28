/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.EmpleoRequerido;

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
public class EmpleoRequeridoFacade extends AbstractFacade<EmpleoRequerido> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleoRequeridoFacade() {
        super(EmpleoRequerido.class);
    }
    public EmpleoRequerido getEmpleoRequerido(Long idPersonalRequerido,Long IdUsuario) {
        Query query = this.em.createNamedQuery(EmpleoRequerido.findByUsuarioYpersonalRequerido);
        query.setParameter("idPersonalRequerido", idPersonalRequerido);
        query.setParameter("idUsuario", IdUsuario);
        query.setParameter("eliminado", false);
        try {
            return (EmpleoRequerido) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<EmpleoRequerido> getListEmpleoRequerido(Integer idEmpresa) {
        Query query = this.em.createNamedQuery(EmpleoRequerido.findByEmpresa);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("eliminado", false);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
