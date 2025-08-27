package com.dgdii.ejb;

import com.dgdii.models.Profesores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfesoresFacade extends AbstractFacade<Profesores> {

    @PersistenceContext(unitName = "com.dgdii_dgdii_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesoresFacade() {
        super(Profesores.class);
    }
    
    public Profesores findProfesoreByMateriaId(Integer idMateria){
       String query = "SELECT m.idProfesor "
                    + "FROM Materias m "
                    + "WHERE m.idMateria = :idMateria";
       return em.createQuery(query, Profesores.class).setParameter("idMateria", idMateria).getSingleResult();
   }

}
