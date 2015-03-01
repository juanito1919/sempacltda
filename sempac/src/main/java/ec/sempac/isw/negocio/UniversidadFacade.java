/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Universidad;
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
public class UniversidadFacade extends AbstractFacade<Universidad> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UniversidadFacade() {
        super(Universidad.class);
    }
    
    public List<Universidad> getItemsUniversidad(boolean  eliminado) {
        Query query = this.em.createNamedQuery(Universidad.findByEliminado);
        query.setParameter("eliminado",eliminado);
        return query.getResultList();
    }
    public List<Universidad> getItemsUniversidadPais(short idPais) {
        Query query = this.em.createNamedQuery(Universidad.findByPaisEliminado);
        query.setParameter("eliminado",false);
        query.setParameter("idPais",idPais);
        return query.getResultList();
    }
}
