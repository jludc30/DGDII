package com.dgdii.ejb;

import com.dgdii.models.Estados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class EstadosFacade extends AbstractFacade<Estados> {

    @PersistenceContext(unitName = "com.dgdii_dgdii_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosFacade() {
        super(Estados.class);
    }
    
    public List<Estados> findByPaisId(Integer idPais){
        String query = "SELECT e "
                + "FROM Estados e "
                + "WHERE e.idPais.idPais=:idPais";
        System.out.println(query+"idPais: "+idPais);
        return em.createQuery(query, Estados.class).setParameter("idPais", idPais).getResultList();
    }
    
}
