package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Revision.class)
public class Revision_ { 

    public static volatile SingularAttribute<Revision, Taller> taller;
    public static volatile SingularAttribute<Revision, Boolean> estatus;
    public static volatile SingularAttribute<Revision, Integer> estatusRow;
    public static volatile SingularAttribute<Revision, Date> fechaRevision;
    public static volatile SingularAttribute<Revision, Date> fechaProxRevision;
    public static volatile SingularAttribute<Revision, Integer> id;
    public static volatile SingularAttribute<Revision, Vehiculo> vehiculo;
    public static volatile SingularAttribute<Revision, String> observacion;

}