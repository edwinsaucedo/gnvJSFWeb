package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.CreditoCotizacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Financiera.class)
public class Financiera_ { 

    public static volatile SingularAttribute<Financiera, Date> fechaIns;
    public static volatile SingularAttribute<Financiera, Compania> compania;
    public static volatile SingularAttribute<Financiera, Integer> estatusRow;
    public static volatile SingularAttribute<Financiera, String> logotipo;
    public static volatile ListAttribute<Financiera, CreditoCotizacion> creditoCotizacionList;
    public static volatile SingularAttribute<Financiera, Integer> usuarioIns;
    public static volatile SingularAttribute<Financiera, Integer> id;
    public static volatile SingularAttribute<Financiera, String> nombre;
    public static volatile SingularAttribute<Financiera, String> rfc;
    public static volatile ListAttribute<Financiera, Credito> creditoList;

}