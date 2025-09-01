package com.dgdii.models;

import com.dgdii.models.Estados;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-09-01T14:30:03")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile SingularAttribute<Paises, String> clave;
    public static volatile SingularAttribute<Paises, Integer> idPais;
    public static volatile ListAttribute<Paises, Estados> estadosList;
    public static volatile SingularAttribute<Paises, String> pais;

}