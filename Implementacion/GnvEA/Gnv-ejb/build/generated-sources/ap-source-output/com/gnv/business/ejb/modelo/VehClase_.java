package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehClase.class)
public class VehClase_ { 

    public static volatile SingularAttribute<VehClase, Integer> estatusRow;
    public static volatile SingularAttribute<VehClase, Integer> id;
    public static volatile SingularAttribute<VehClase, String> nombre;
    public static volatile ListAttribute<VehClase, Vehiculo> vehiculoList;

}