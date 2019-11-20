package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Conversion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoConvenio.class)
public class TipoConvenio_ { 

    public static volatile SingularAttribute<TipoConvenio, Integer> estatusRow;
    public static volatile SingularAttribute<TipoConvenio, Integer> id;
    public static volatile ListAttribute<TipoConvenio, Conversion> conversionList;
    public static volatile SingularAttribute<TipoConvenio, String> nombre;

}