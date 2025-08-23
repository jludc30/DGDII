package com.dgdii.models;

import com.dgdii.models.AlumnosMaterias;
import com.dgdii.models.Personas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-08-22T15:06:45")
@StaticMetamodel(Alumnos.class)
public class Alumnos_ { 

    public static volatile SingularAttribute<Alumnos, Integer> idAlumno;
    public static volatile SingularAttribute<Alumnos, Date> fechaIngreso;
    public static volatile SingularAttribute<Alumnos, String> matricula;
    public static volatile ListAttribute<Alumnos, AlumnosMaterias> alumnosMateriasList;
    public static volatile SingularAttribute<Alumnos, Personas> idPersona;

}