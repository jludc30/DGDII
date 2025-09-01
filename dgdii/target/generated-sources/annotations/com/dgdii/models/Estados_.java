package com.dgdii.models;

import com.dgdii.models.Municipios;
import com.dgdii.models.Paises;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-09-01T14:30:03")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Integer> idEstado;
    public static volatile SingularAttribute<Estados, String> estado;
    public static volatile SingularAttribute<Estados, String> clave;
    public static volatile ListAttribute<Estados, Municipios> municipiosList;
    public static volatile SingularAttribute<Estados, Paises> idPais;

}