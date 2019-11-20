package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Valoracion.class)
public class Valoracion_ { 

    public static volatile SingularAttribute<Valoracion, Taller> taller;
    public static volatile SingularAttribute<Valoracion, Date> fecha;
    public static volatile SingularAttribute<Valoracion, Boolean> estado;
    public static volatile SingularAttribute<Valoracion, Date> fechaIns;
    public static volatile SingularAttribute<Valoracion, BigDecimal> presupuesto;
    public static volatile SingularAttribute<Valoracion, Integer> estatusRow;
    public static volatile SingularAttribute<Valoracion, Integer> usuarioIns;
    public static volatile SingularAttribute<Valoracion, Vehiculo> vehiculo;
    public static volatile SingularAttribute<Valoracion, Integer> id;
    public static volatile SingularAttribute<Valoracion, Boolean> solicitarCredito;
    public static volatile ListAttribute<Valoracion, Conversion> conversionList;
    public static volatile SingularAttribute<Valoracion, String> observacion;

}