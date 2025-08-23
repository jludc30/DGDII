package com.dgdii.models;

import com.dgdii.models.Alumnos;
import com.dgdii.models.Colonias;
import com.dgdii.models.Profesores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-08-22T15:06:45")
@StaticMetamodel(Personas.class)
public class Personas_ { 

    public static volatile SingularAttribute<Personas, String> apaterno;
    public static volatile SingularAttribute<Personas, Colonias> idColonia;
    public static volatile SingularAttribute<Personas, Date> fechaNacimiento;
    public static volatile ListAttribute<Personas, Alumnos> alumnosList;
    public static volatile SingularAttribute<Personas, String> amaterno;
    public static volatile SingularAttribute<Personas, Integer> sexo;
    public static volatile SingularAttribute<Personas, String> nombre;
    public static volatile SingularAttribute<Personas, Integer> idPersona;
    public static volatile ListAttribute<Personas, Profesores> profesoresList;

}