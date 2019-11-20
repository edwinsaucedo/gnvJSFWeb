package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Flota;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(FlotaVehiculo.class)
public class FlotaVehiculo_ { 

    public static volatile SingularAttribute<FlotaVehiculo, Date> fechaIns;
    public static volatile SingularAttribute<FlotaVehiculo, Flota> flota;
    public static volatile SingularAttribute<FlotaVehiculo, Integer> estatusRow;
    public static volatile SingularAttribute<FlotaVehiculo, Integer> usuarioIns;
    public static volatile SingularAttribute<FlotaVehiculo, Long> cnciasidn;
    public static volatile SingularAttribute<FlotaVehiculo, Integer> id;
    public static volatile SingularAttribute<FlotaVehiculo, Vehiculo> vehiculo;

}