package com.dgdii.ejb;

import com.dgdii.models.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

        System.out.println("************JPQL*******************");
        
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

    
    public List<Alumnos> findAlumnosByCriteria(
            Personas persona, Alumnos alumno, Materias materia,
            Paises pais, Estados estado, Municipios municipio, Colonias colonia) {

        System.out.println("*********QUERIES NATIVAS***********");
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT a.* ")
                .append("FROM ALUMNOS a ")
                .append("INNER JOIN PERSONAS per ")
                .append("ON a.ID_PERSONA = per.ID_PERSONA ")
                .append("INNER JOIN COLONIAS c ")
                .append("ON per.ID_COLONIA = c.ID_COLONIA ")
                .append("INNER JOIN MUNICIPIOS m ")
                .append("ON c.ID_MUNICIPIO = m.ID_MUNICIPIO ")
                .append("INNER JOIN ESTADOS e ")
                .append("ON m.ID_ESTADO = e.ID_ESTADO ")
                .append("INNER JOIN PAISES p ")
                .append("ON e.ID_PAIS = p.ID_PAIS ")
                .append("INNER JOIN ALUMNOS_MATERIAS am ")
                .append("ON a.ID_ALUMNO = am.ID_ALUMNO ")
                .append("INNER JOIN MATERIAS mat ")
                .append("ON am.ID_MATERIA = mat.ID_MATERIA ")
                .append("WHERE 1 = 1 ");

        //Direccion
        if (colonia != null) {
            sb.append("AND c.ID_COLONIA = ").append(colonia.getIdColonia()).append(" ");
        } else if (municipio != null) {
            sb.append("AND m.ID_MUNICIPIO = ").append(municipio.getIdMunicipio()).append(" ");
        } else if (estado != null) {
            sb.append("AND e.ID_ESTADO = ").append(estado.getIdEstado()).append(" ");
        } else if (pais != null) {
            sb.append("AND p.ID_PAIS = ").append(pais.getIdPais()).append(" ");
        }
        //Persona
        if (persona.getSexo() != null) {
            sb.append("AND per.SEXO = ").append(persona.getSexo()).append(" ");
        }
        if (persona.getNombre() != null) {
            sb.append("AND per.NOMBRE LIKE UPPER('%").append(persona.getNombre()).append("%')").append(" ");
        }
        if (persona.getApaterno() != null) {
            sb.append("AND per.APATERNO LIKE UPPER('%").append(persona.getApaterno()).append("%')").append(" ");
        }
        if (persona.getAmaterno() != null) {
            sb.append("AND per.AMATERNO LIKE UPPER('%").append(persona.getAmaterno()).append("%')").append(" ");
        }
        if (materia != null) {
            sb.append("AND mat.ID_MATERIA = ").append(materia.getIdMateria()).append(" ");
        }

        sb.append("ORDER BY a.ID_ALUMNO");
        String string = sb.toString();
        System.out.println(string);

        return em.createNativeQuery(string, Alumnos.class).getResultList();

    }

}
