package com.pepe.jpa.entities;

import com.pepe.jpa.entities.LineaTecnologica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-02-16T13:59:22")
@StaticMetamodel(RedTecnologica.class)
public class RedTecnologica_ { 

    public static volatile SingularAttribute<RedTecnologica, Integer> idRedTecnologica;
    public static volatile SingularAttribute<RedTecnologica, String> nombreRedTecnologica;
    public static volatile ListAttribute<RedTecnologica, LineaTecnologica> lineaTecnologicaList;

}