package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehEquipo.class)
public class VehEquipo_ { 

    public static volatile SingularAttribute<VehEquipo, String> accion;
    public static volatile SingularAttribute<VehEquipo, Boolean> tipo;
    public static volatile SingularAttribute<VehEquipo, Date> fechaAct;
    public static volatile SingularAttribute<VehEquipo, Integer> id;
    public static volatile SingularAttribute<VehEquipo, Vehiculo> vehiculo;
    public static volatile SingularAttribute<VehEquipo, Integer> idRegistro;

}