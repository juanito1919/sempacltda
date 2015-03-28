/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.PersonalRequerido;
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
public class PersonalRequeridoFacade extends AbstractFacade<PersonalRequerido> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalRequeridoFacade() {
        super(PersonalRequerido.class);
    }

    public List<PersonalRequerido> getItemsPersonalRequerido(boolean eliminado) {
        
        Query query = this.em.createNamedQuery(PersonalRequerido.findByEliminado);
        query.setParameter("eliminado", eliminado);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    public List<PersonalRequerido> getItemsUsuariosInteresados(Integer idEmpresa, boolean activo, boolean eliminado) {
        Query query = this.em.createNamedQuery(PersonalRequerido.findByEliminado);
        query.setParameter("idEmpresa", idEmpresa);
        query.setParameter("activo", activo);
        query.setParameter("eliminado", eliminado);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
