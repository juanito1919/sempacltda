/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Idioma;
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
public class IdiomaFacade extends AbstractFacade<Idioma> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdiomaFacade() {
        super(Idioma.class);
    }
    
    public List<Idioma> getItemsIdiomasEliminado(boolean eliminado) {
        Query query = this.em.createNamedQuery(Idioma.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
