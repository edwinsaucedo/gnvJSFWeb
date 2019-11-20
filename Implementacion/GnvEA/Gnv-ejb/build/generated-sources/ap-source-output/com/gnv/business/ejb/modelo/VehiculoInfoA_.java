package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehiculoInfoA.class)
public class VehiculoInfoA_ { 

    public static volatile SingularAttribute<VehiculoInfoA, Date> fechaExpedicion;
    public static volatile SingularAttribute<VehiculoInfoA, Date> fechaIns;
    public static volatile SingularAttribute<VehiculoInfoA, String> organismo;
    public static volatile SingularAttribute<VehiculoInfoA, Compania> compania;
    public static volatile SingularAttribute<VehiculoInfoA, Ciudad> ciudad;
    public static volatile SingularAttribute<VehiculoInfoA, Integer> estatusRow;
    public static volatile SingularAttribute<VehiculoInfoA, Integer> usuarioIns;
    public static volatile SingularAttribute<VehiculoInfoA, Integer> id;
    public static volatile SingularAttribute<VehiculoInfoA, String> tarjetaCirculacion;
    public static volatile ListAttribute<VehiculoInfoA, Vehiculo> vehiculoList;

}