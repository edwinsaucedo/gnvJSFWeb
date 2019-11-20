package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoCredito.class)
public class TipoCredito_ { 

    public static volatile SingularAttribute<TipoCredito, Date> fechaIns;
    public static volatile SingularAttribute<TipoCredito, Integer> estatusRow;
    public static volatile SingularAttribute<TipoCredito, Integer> usuariosIns;
    public static volatile SingularAttribute<TipoCredito, Integer> id;
    public static volatile SingularAttribute<TipoCredito, String> nombre;
    public static volatile ListAttribute<TipoCredito, Credito> creditoList;

}