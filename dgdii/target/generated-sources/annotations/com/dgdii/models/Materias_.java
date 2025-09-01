package com.dgdii.models;

import com.dgdii.models.AlumnosMaterias;
import com.dgdii.models.Profesores;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-09-01T14:30:03")
@StaticMetamodel(Materias.class)
public class Materias_ { 

    public static volatile SingularAttribute<Materias, Profesores> idProfesor;
    public static volatile SingularAttribute<Materias, Integer> idMateria;
    public static volatile SingularAttribute<Materias, String> materia;
    public static volatile ListAttribute<Materias, AlumnosMaterias> alumnosMateriasList;

}