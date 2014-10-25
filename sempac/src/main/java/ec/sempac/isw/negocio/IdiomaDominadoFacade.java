/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.IdiomaDominado;
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
public class IdiomaDominadoFacade extends AbstractFacade<IdiomaDominado> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdiomaDominadoFacade() {
        super(IdiomaDominado.class);
    }
    
    public List<IdiomaDominado> getItemsIdiDomEliminadoUsuario(Long idUsuario, boolean eliminado) {
        Query query = this.em.createNamedQuery(IdiomaDominado.findByUsuarioEliminado);
        query.setParameter("eliminado", eliminado);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
}
