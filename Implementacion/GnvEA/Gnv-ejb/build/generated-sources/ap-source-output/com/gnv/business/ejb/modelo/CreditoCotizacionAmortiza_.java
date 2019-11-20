package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoCotizacionAmortiza.class)
public class CreditoCotizacionAmortiza_ { 

    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Date> fecha;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Double> capital;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Double> ivaIntereses;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Integer> id;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, CreditoCotizacion> creditoCotizacion;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Double> intereses;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Double> pago;
    public static volatile SingularAttribute<CreditoCotizacionAmortiza, Integer> partida;

}