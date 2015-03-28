/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Habilidades;
import ec.sempac.isw.modelo.UserHabilidadesEspectativas;
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
public class UserHabilidadesEspectativasFacade extends AbstractFacade<UserHabilidadesEspectativas> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserHabilidadesEspectativasFacade() {
        super(UserHabilidadesEspectativas.class);
    }

    public List<UserHabilidadesEspectativas> getSoloHabilidades(Long idUsuario) {
        Query query = this.em.createNamedQuery(UserHabilidadesEspectativas.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    public List<UserHabilidadesEspectativas> getSoloEspectativas(Long idUsuario) {
        Query query = this.em.createNamedQuery(UserHabilidadesEspectativas.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    public List<UserHabilidadesEspectativas> getSoloEspectativasYHabilidades(Long idUsuario) {
        Query query = this.em.createNamedQuery(UserHabilidadesEspectativas.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    public List<UserHabilidadesEspectativas> getItemsHabilidadesUsuario(Long idUsuario) {
        Query query = this.em.createNamedQuery(UserHabilidadesEspectativas.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
         try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        
       
    }
}
