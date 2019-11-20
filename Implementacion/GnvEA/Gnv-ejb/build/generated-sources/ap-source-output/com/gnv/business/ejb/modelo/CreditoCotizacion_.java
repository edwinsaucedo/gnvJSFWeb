package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza;
import com.gnv.business.ejb.modelo.Financiera;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoCotizacion.class)
public class CreditoCotizacion_ { 

    public static volatile SingularAttribute<CreditoCotizacion, Double> anticipo;
    public static volatile SingularAttribute<CreditoCotizacion, Character> frecuenciaTipoPlazo;
    public static volatile SingularAttribute<CreditoCotizacion, Character> frecuenciaPago;
    public static volatile SingularAttribute<CreditoCotizacion, Double> montoAFinanciar;
    public static volatile SingularAttribute<CreditoCotizacion, Integer> plazoCalculo;
    public static volatile SingularAttribute<CreditoCotizacion, Integer> usuariosIns;
    public static volatile SingularAttribute<CreditoCotizacion, String> nombre;
    public static volatile SingularAttribute<CreditoCotizacion, Double> montoInteres;
    public static volatile SingularAttribute<CreditoCotizacion, Date> fecha;
    public static volatile SingularAttribute<CreditoCotizacion, Date> fechaIns;
    public static volatile SingularAttribute<CreditoCotizacion, Double> monto;
    public static volatile ListAttribute<CreditoCotizacion, CreditoCotizacionAmortiza> creditoCotizacionAmortizaList;
    public static volatile SingularAttribute<CreditoCotizacion, Double> montoAPagar;
    public static volatile SingularAttribute<CreditoCotizacion, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoCotizacion, Double> tasaOCuota;
    public static volatile SingularAttribute<CreditoCotizacion, Integer> id;
    public static volatile SingularAttribute<CreditoCotizacion, Financiera> financiera;

}