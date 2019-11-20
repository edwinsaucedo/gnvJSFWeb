package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehTipoServicio.class)
public class VehTipoServicio_ { 

    public static volatile SingularAttribute<VehTipoServicio, Integer> estatusRow;
    public static volatile SingularAttribute<VehTipoServicio, Integer> id;
    public static volatile SingularAttribute<VehTipoServicio, String> nombre;
    public static volatile ListAttribute<VehTipoServicio, Vehiculo> vehiculoList;

}