package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.FlotaVehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Flota.class)
public class Flota_ { 

    public static volatile ListAttribute<Flota, FlotaVehiculo> flotaVehiculoList;
    public static volatile SingularAttribute<Flota, Date> fechaIns;
    public static volatile SingularAttribute<Flota, Integer> estatusRow;
    public static volatile SingularAttribute<Flota, Integer> usuarioIns;
    public static volatile SingularAttribute<Flota, Long> cnciasidn;
    public static volatile SingularAttribute<Flota, Integer> id;
    public static volatile SingularAttribute<Flota, Long> cncdiridn;
    public static volatile SingularAttribute<Flota, String> nombre;

}