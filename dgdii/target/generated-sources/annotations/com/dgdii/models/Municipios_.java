package com.dgdii.models;

import com.dgdii.models.Colonias;
import com.dgdii.models.Estados;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-08-22T15:06:45")
@StaticMetamodel(Municipios.class)
public class Municipios_ { 

    public static volatile SingularAttribute<Municipios, Estados> idEstado;
    public static volatile SingularAttribute<Municipios, String> municipio;
    public static volatile ListAttribute<Municipios, Colonias> coloniasList;
    public static volatile SingularAttribute<Municipios, Integer> idMunicipio;

}