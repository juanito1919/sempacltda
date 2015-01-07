/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.EstudiosPrimaria;
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
public class EstudiosPrimariaFacade extends AbstractFacade<EstudiosPrimaria> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosPrimariaFacade() {
        super(EstudiosPrimaria.class);
    }
     public List<EstudiosPrimaria> getItemsByIdUsuario(long idUsuario, boolean eliminado) {
        Query query = this.em.createNamedQuery(EstudiosPrimaria.findByIdUsuarioEliminado);
        query.setParameter("idUsuario",idUsuario);
        query.setParameter("eliminado",eliminado);
        return query.getResultList();
    }
}
