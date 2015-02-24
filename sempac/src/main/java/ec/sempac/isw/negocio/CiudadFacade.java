/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Ciudad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author miguesaca
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    public List<Ciudad> getItemsReionesPais(boolean eliminado, Integer region) {
        Query query = this.em.createNamedQuery(Ciudad.findByRegionEliminado);
        query.setParameter("eliminado", eliminado);
        query.setParameter("region", region);
        return query.getResultList();
    }

    public List<Ciudad> listTatolCiudad(boolean eliminado) {
        Query query = this.em.createNamedQuery(Ciudad.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
