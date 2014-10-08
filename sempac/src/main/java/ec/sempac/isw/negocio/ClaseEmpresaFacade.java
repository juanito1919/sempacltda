/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.ClaseEmpresa;
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
public class ClaseEmpresaFacade extends AbstractFacade<ClaseEmpresa> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClaseEmpresaFacade() {
        super(ClaseEmpresa.class);
    }

    public ClaseEmpresa getClaseEmpresa(String nombre) {
        Query query = this.em.createNamedQuery(ClaseEmpresa.findByNombre);
        query.setParameter("nombre", nombre);
        try {
            ClaseEmpresa claseEmpresa = (ClaseEmpresa) query.getSingleResult();
            return claseEmpresa;
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public List<ClaseEmpresa> getItemsClaseEmpresa(boolean eliminado) {
        Query query = this.em.createNamedQuery(ClaseEmpresa.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
