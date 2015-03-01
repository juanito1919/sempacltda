/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.TipoEstudio;
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
public class TipoEstudioFacade extends AbstractFacade<TipoEstudio> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstudioFacade() {
        super(TipoEstudio.class);
    }

    public TipoEstudio getTipoEstidoDescripcion(String descripcion) {
        Query query = this.em.createNamedQuery(TipoEstudio.findByDescripcion);
        query.setParameter("descripcion", descripcion);
        try {
            return (TipoEstudio) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
