package com.dgdii.models;

import com.dgdii.models.Municipios;
import com.dgdii.models.Personas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-08-22T15:06:45")
@StaticMetamodel(Colonias.class)
public class Colonias_ { 

    public static volatile SingularAttribute<Colonias, Integer> idColonia;
    public static volatile ListAttribute<Colonias, Personas> personasList;
    public static volatile SingularAttribute<Colonias, Municipios> idMunicipio;
    public static volatile SingularAttribute<Colonias, String> cp;
    public static volatile SingularAttribute<Colonias, String> colonia;

}