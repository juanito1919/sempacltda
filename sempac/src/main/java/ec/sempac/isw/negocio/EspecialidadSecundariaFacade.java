/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.EspecialidadSecundaria;
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
public class EspecialidadSecundariaFacade extends AbstractFacade<EspecialidadSecundaria> {
    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadSecundariaFacade() {
        super(EspecialidadSecundaria.class);
    }
    
    public List<EspecialidadSecundaria> getItemsByIdUsuario(long idUsuario) {
        Query query = this.em.createNamedQuery(EspecialidadSecundaria.findByIdUsuario);
        query.setParameter("idUsuario",idUsuario);
        return query.getResultList();
    }
}
