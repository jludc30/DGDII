package com.dgdii.models;

import com.dgdii.models.Materias;
import com.dgdii.models.Personas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-08-22T15:06:45")
@StaticMetamodel(Profesores.class)
public class Profesores_ { 

    public static volatile SingularAttribute<Profesores, Integer> idProfesor;
    public static volatile SingularAttribute<Profesores, String> numeroControl;
    public static volatile ListAttribute<Profesores, Materias> materiasList;
    public static volatile SingularAttribute<Profesores, Personas> idPersona;

}