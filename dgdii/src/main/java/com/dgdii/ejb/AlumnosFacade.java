package com.dgdii.ejb;

import com.dgdii.models.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<Alumnos> findAlumnosByCriteria(Personas persona, Alumnos alumno, Materias materia, Paises pais, Estados estado, Municipios municipio, Colonias colonia, Integer edad) {

        Integer edadMinima = edad;
        Integer edadMaxima = 100;
        LocalDate hoy = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();

        // DOB más reciente permitido (fin inclusivo):
        LocalDate endDob = hoy.minusYears(edadMinima);
        // DOB más antiguo permitido (inicio inclusivo):
        LocalDate startDob = hoy.minusYears(edadMaxima + 1).plusDays(1);

        // Convertimos a java.util.Date y hacemos fin exclusivo (+1 día):
        Date start = Date.from(startDob.atStartOfDay(zone).toInstant());
        Date endExclusive = Date.from(endDob.plusDays(1).atStartOfDay(zone).toInstant());

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT a ");
        sb.append("FROM Alumnos a ");
        sb.append("INNER JOIN a.idPersona per ");
        sb.append("INNER JOIN per.idColonia c ");
        sb.append("INNER JOIN c.idMunicipio m ");
        sb.append("INNER JOIN m.idEstado e ");
        sb.append("INNER JOIN e.idPais p ");
        sb.append("INNER JOIN a.alumnosMateriasList am ");
        sb.append("INNER JOIN am.idMateria mat ");
        sb.append("WHERE 1=1 ");

        if (colonia != null) {
            sb.append("AND c.idColonia = :idColonia ");

        } else if (municipio != null) {
            sb.append("AND m.idMunicipio = idMunicipio ");
        } else if (estado != null) {
            sb.append("AND e.idEstado = :idEstado ");
        } else if (pais != null) {
            sb.append("AND p.idPais = :idPais ");
        }

        if (edad != null) {
            sb.append("AND per.fechaNacimiento >= :startDob ")
                    .append("AND per.fechaNacimiento < :endDobExclusive ");
        }

        if (persona != null) {
            sb.append(persona.getSexo() != null ? "AND per.sexo = :sexo " : "");
            sb.append(persona.getNombre() != null ? "AND per.nombre = :nombre " : "");
            sb.append(persona.getApaterno() != null ? "AND per.apaterno = :apaterno " : "");
            sb.append(persona.getAmaterno() != null ? "AND per.amaterno = :amaterno " : "");
        }
        // sb.append(alumno != null ? "AND a.matricula = "+alumno.getMatricula()+" " : "");              
        sb.append(materia != null ? "AND mat.idMateria = :idMateria " : "");

        sb.append("ORDER BY a.idAlumno");

        String string = sb.toString();
        System.out.println(string);

        Query query = em.createQuery(string);

        if (colonia != null) {
            query.setParameter("idColonia", colonia.getIdColonia());
        } else if (municipio != null) {
            query.setParameter("idMunicipio", municipio.getIdMunicipio());
        } else if (estado != null) {
            query.setParameter("idEstado", estado.getIdEstado());
        } else if (pais != null) {
            query.setParameter("idPais", pais.getIdPais());
        }

        if (edad != null) {
            query.setParameter("startDob", start);
            query.setParameter("endDobExclusive", endExclusive);
        }
        if (persona.getSexo() != null) {
            query.setParameter("sexo", persona.getSexo());
        }
        if (persona.getNombre() != null) {
            query.setParameter("nombre", persona.getNombre());
        }
        if (persona.getApaterno() != null) {
            query.setParameter("apaterno", persona.getApaterno());
        }
        if (persona.getAmaterno() != null) {
            query.setParameter("amaterno", persona.getAmaterno());
        }

        if (materia != null) {
            query.setParameter("idMateria", materia.getIdMateria());
        }

        return query.getResultList();
    }
}
