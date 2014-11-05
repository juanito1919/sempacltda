/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.Pais;
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
public class ColegioFacade extends AbstractFacade<Colegio> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColegioFacade() {
        super(Colegio.class);
    }

    public List<Colegio> getItemsColegio(String nombre) {
        Query query = this.em.createNamedQuery(Colegio.findByAutoCompletado);
        query.setParameter("nombre", "'" + nombre + "%'");
        query.setParameter("eliminado", false);
        return query.getResultList();
    }

    public List<Colegio> getItemsColegio(boolean  estado) {
        Query query = this.em.createNamedQuery(Colegio.findByEliminado);
        query.setParameter("eliminado",estado);
        return query.getResultList();
    }
}
