/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Region;
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
public class RegionFacade extends AbstractFacade<Region> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }
    public Region getRegionUnico(String nombre,Short idPais) {
        Query query = this.em.createNamedQuery(Region.findByUniquePaisRegion);
        query.setParameter("nombre", nombre);
        try {
            Region region = (Region) query.getSingleResult();
            return region;
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public List<Region> getItemsReionesPais(boolean eliminado, Short pais) {
        Query query = this.em.createNamedQuery(Region.findByPaisEliminado);
        query.setParameter("eliminado", eliminado);
        query.setParameter("pais", pais);
        return query.getResultList();
    }
}
