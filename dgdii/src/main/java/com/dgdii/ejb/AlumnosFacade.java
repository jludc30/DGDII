package com.dgdii.ejb;

import com.dgdii.models.Alumnos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AlumnosFacade extends AbstractFacade<Alumnos> {

    @PersistenceContext(unitName = "com.dgdii_dgdii_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnosFacade() {
        super(Alumnos.class);
    }
    
}
