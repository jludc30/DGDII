package com.dgdii.ejb;

import com.dgdii.models.Colonias;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ColoniasFacade extends AbstractFacade<Colonias> {

    @PersistenceContext(unitName = "com.dgdii_dgdii_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColoniasFacade() {
        super(Colonias.class);
    }

    public List<Colonias> findColoniaByMunicipioId(Integer idMunicipio) {
        String query = "SELECT c FROM Colonias c "
                      + "WHERE c.idMunicipio.idMunicipio = :idMunicipio";
        
        return em.createQuery(query, Colonias.class).setParameter("idMunicipio", idMunicipio).getResultList();
    }
}
