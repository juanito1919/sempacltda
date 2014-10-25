/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Habilidades;
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
public class HabilidadesFacade extends AbstractFacade<Habilidades> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabilidadesFacade() {
        super(Habilidades.class);
    }
    
    public List<Habilidades> getItemsHabilidadesEliminado(boolean eliminado) {
        Query query = this.em.createNamedQuery(Habilidades.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
