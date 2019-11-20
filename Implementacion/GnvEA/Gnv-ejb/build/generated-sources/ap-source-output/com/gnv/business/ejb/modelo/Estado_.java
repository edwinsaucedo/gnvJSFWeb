package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile ListAttribute<Estado, Ciudad> ciudadList;
    public static volatile SingularAttribute<Estado, Integer> id;
    public static volatile SingularAttribute<Estado, String> nombre;
    public static volatile SingularAttribute<Estado, Pais> pais;

}