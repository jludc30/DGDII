package com.dgdii.models;

import com.dgdii.models.Alumnos;
import com.dgdii.models.Materias;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-09-01T14:30:03")
@StaticMetamodel(AlumnosMaterias.class)
public class AlumnosMaterias_ { 

    public static volatile SingularAttribute<AlumnosMaterias, Alumnos> idAlumno;
    public static volatile SingularAttribute<AlumnosMaterias, Integer> idAlumnoMateria;
    public static volatile SingularAttribute<AlumnosMaterias, Materias> idMateria;

}