package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Legalizacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoLegalizacion.class)
public class TipoLegalizacion_ { 

    public static volatile SingularAttribute<TipoLegalizacion, Integer> estatusRow;
    public static volatile SingularAttribute<TipoLegalizacion, Integer> id;
    public static volatile SingularAttribute<TipoLegalizacion, String> nombre;
    public static volatile ListAttribute<TipoLegalizacion, Legalizacion> legalizacionList;

}