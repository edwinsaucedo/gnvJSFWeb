package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.ConversionEquipo;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.TipoConvenio;
import com.gnv.business.ejb.modelo.TipoConversion;
import com.gnv.business.ejb.modelo.Valoracion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Conversion.class)
public class Conversion_ { 

    public static volatile SingularAttribute<Conversion, TipoConversion> tipoConversion;
    public static volatile SingularAttribute<Conversion, Taller> taller;
    public static volatile SingularAttribute<Conversion, Date> fechaIns;
    public static volatile SingularAttribute<Conversion, Estatus> estatus;
    public static volatile SingularAttribute<Conversion, Date> fechaConversion;
    public static volatile ListAttribute<Conversion, ConversionEquipo> conversionEquipoList;
    public static volatile SingularAttribute<Conversion, Integer> estatusRow;
    public static volatile SingularAttribute<Conversion, TipoConvenio> tipoConvenio;
    public static volatile SingularAttribute<Conversion, Integer> usuarioIns;
    public static volatile SingularAttribute<Conversion, Valoracion> valoracion;
    public static volatile SingularAttribute<Conversion, Integer> id;
    public static volatile SingularAttribute<Conversion, String> observacion;

}