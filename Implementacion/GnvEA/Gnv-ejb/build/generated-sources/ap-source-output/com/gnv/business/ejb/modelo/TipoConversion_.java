package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Conversion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoConversion.class)
public class TipoConversion_ { 

    public static volatile SingularAttribute<TipoConversion, Integer> estatusRow;
    public static volatile SingularAttribute<TipoConversion, Integer> id;
    public static volatile ListAttribute<TipoConversion, Conversion> conversionList;
    public static volatile SingularAttribute<TipoConversion, String> nombre;

}