/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Colegio;
import ec.sempac.isw.modelo.Escuela;
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
public class EscuelaFacade extends AbstractFacade<Escuela> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EscuelaFacade() {
        super(Escuela.class);
    }

    public Escuela getIEscuela(String nombre) {
        Query query = this.em.createNamedQuery(Escuela.findByAutoCompletado);
        query.setParameter("nombre", nombre + "%");
        query.setParameter("eliminado", false);
        return (query.getResultList()!=null && !query.getResultList().isEmpty())?(Escuela)query.getResultList().get(0):null;
    }

    public List<Escuela> getItemsEscuela(boolean estado) {
        Query query = this.em.createNamedQuery(Escuela.findByEliminado);
        query.setParameter("eliminado", estado);
        return query.getResultList();
    }
}
