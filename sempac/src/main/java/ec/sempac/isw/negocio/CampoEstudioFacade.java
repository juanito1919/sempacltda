/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.CampoEstudio;
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
public class CampoEstudioFacade extends AbstractFacade<CampoEstudio> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampoEstudioFacade() {
        super(CampoEstudio.class);
    }

    public CampoEstudio getCampoNombre(String nombre) {
        Query query = this.em.createNamedQuery(CampoEstudio.findByNombre);
        query.setParameter("nombre", nombre);
        try {
            return (CampoEstudio) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
