package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Revision;
import com.gnv.business.ejb.modelo.Valoracion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Taller.class)
public class Taller_ { 

    public static volatile SingularAttribute<Taller, Date> fechaIns;
    public static volatile SingularAttribute<Taller, Compania> compania;
    public static volatile SingularAttribute<Taller, Integer> estatusRow;
    public static volatile SingularAttribute<Taller, Integer> usuarioIns;
    public static volatile ListAttribute<Taller, Revision> revisionList;
    public static volatile SingularAttribute<Taller, Integer> id;
    public static volatile SingularAttribute<Taller, Empresa> empresa;
    public static volatile ListAttribute<Taller, Conversion> conversionList;
    public static volatile ListAttribute<Taller, Valoracion> valoracionList;
    public static volatile SingularAttribute<Taller, String> nombre;
    public static volatile ListAttribute<Taller, Credito> creditoList;

}