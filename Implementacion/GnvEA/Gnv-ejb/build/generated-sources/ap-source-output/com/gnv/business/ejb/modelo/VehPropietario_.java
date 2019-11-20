package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehPropietario.class)
public class VehPropietario_ { 

    public static volatile SingularAttribute<VehPropietario, Date> fechaIns;
    public static volatile SingularAttribute<VehPropietario, Propietario> propietario;
    public static volatile SingularAttribute<VehPropietario, Integer> estatusRow;
    public static volatile SingularAttribute<VehPropietario, Integer> usuarioIns;
    public static volatile SingularAttribute<VehPropietario, Integer> id;
    public static volatile SingularAttribute<VehPropietario, Vehiculo> vehiculo;

}