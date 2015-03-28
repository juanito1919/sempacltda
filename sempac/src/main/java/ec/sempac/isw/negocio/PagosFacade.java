/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.negocio;

import ec.sempac.isw.modelo.Pagos;
import ec.sempac.isw.modelo.SistemaUsuario;
import ec.sempac.isw.modelo.Usuario;
import java.util.ArrayList;
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
public class PagosFacade extends AbstractFacade<Pagos> {

    @PersistenceContext(unitName = "ec.sempac_sempac_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosFacade() {
        super(Pagos.class);
    }

    public Pagos getPago(long idUsuario) {
        Query query = this.em.createNamedQuery(Pagos.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("eliminado", false);
        try {
            //if(query.getResultList().size()>1){

            // }else{
            return  (query.getResultList()!=null && !query.getResultList().isEmpty())?(Pagos)query.getResultList().get(0):null;
            // }
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Pagos> getItemsEspera() {
        List<Pagos> pagos = null;
        List<Usuario> usuarios;
        Query query = this.em.createNamedQuery(SistemaUsuario.findByEspera);
        query.setParameter("estado", 'E');
        query.setParameter("eliminado", false);
        usuarios = query.getResultList();
        if (usuarios != null) {
            pagos = new ArrayList<Pagos>();
            Pagos aux;
            for (Usuario usuario : usuarios) {
                query = this.em.createNamedQuery(Pagos.findByIdUsuario);
                query.setParameter("idUsuario", usuario.getIdUsuario());
                query.setParameter("eliminado", false);
                try {
                    aux = (Pagos) query.getResultList().get(0);
                } catch (NoResultException e) {
                    aux = null;
                }
                if (aux != null) {
                    pagos.add(aux);
                }
            }
        }
        return pagos;
    }

    public List<Pagos> getItemsUsuario(Long idUsuario) {
        Query query = this.em.createNamedQuery(Pagos.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("eliminado", false);
        return query.getResultList();
    }
}
