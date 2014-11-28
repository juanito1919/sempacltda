/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.ExperienciaLaboral;
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
public class ExperienciaLaboralFacade extends AbstractFacade<ExperienciaLaboral> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienciaLaboralFacade() {
        super(ExperienciaLaboral.class);
    }
    
    public List<ExperienciaLaboral> getItemsMeritoEliminadoUsuario(Long idUsuario, boolean eliminado) {
        Query query = this.em.createNamedQuery(ExperienciaLaboral.findByUsuarioEliminado);
        query.setParameter("eliminado", eliminado);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    
}
