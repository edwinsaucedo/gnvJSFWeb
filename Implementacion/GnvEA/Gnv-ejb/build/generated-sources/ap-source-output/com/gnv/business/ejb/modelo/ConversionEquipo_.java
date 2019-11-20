package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Conversion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(ConversionEquipo.class)
public class ConversionEquipo_ { 

    public static volatile SingularAttribute<ConversionEquipo, Boolean> tipo;
    public static volatile SingularAttribute<ConversionEquipo, Date> fechaIns;
    public static volatile SingularAttribute<ConversionEquipo, Integer> estatusRow;
    public static volatile SingularAttribute<ConversionEquipo, Integer> usuarioIns;
    public static volatile SingularAttribute<ConversionEquipo, Integer> id;
    public static volatile SingularAttribute<ConversionEquipo, Integer> idRegistro;
    public static volatile SingularAttribute<ConversionEquipo, Conversion> conversion;

}