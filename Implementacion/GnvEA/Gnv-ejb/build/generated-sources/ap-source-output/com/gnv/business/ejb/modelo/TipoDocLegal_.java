package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Legalizacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoDocLegal.class)
public class TipoDocLegal_ { 

    public static volatile SingularAttribute<TipoDocLegal, Integer> estatusRow;
    public static volatile SingularAttribute<TipoDocLegal, Integer> id;
    public static volatile SingularAttribute<TipoDocLegal, String> nombre;
    public static volatile ListAttribute<TipoDocLegal, Legalizacion> legalizacionList;

}