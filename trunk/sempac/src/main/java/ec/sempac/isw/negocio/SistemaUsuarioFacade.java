/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
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
public class SistemaUsuarioFacade extends AbstractFacade<SistemaUsuario> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemaUsuarioFacade() {
        super(SistemaUsuario.class);
    }

    public Usuario getUsuario(long idUsuario, char estado) {
        Query query = this.em.createNamedQuery(SistemaUsuario.findByEstadoPago);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("estado", estado);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
