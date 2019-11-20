package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Fabricante;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(FabricanteInfoA.class)
public class FabricanteInfoA_ { 

    public static volatile SingularAttribute<FabricanteInfoA, String> numeroAutorizacion;
    public static volatile SingularAttribute<FabricanteInfoA, Date> fechaVencimiento;
    public static volatile ListAttribute<FabricanteInfoA, Fabricante> fabricanteList;
    public static volatile SingularAttribute<FabricanteInfoA, BigDecimal> comisionControlCarga;
    public static volatile SingularAttribute<FabricanteInfoA, Integer> id;
    public static volatile SingularAttribute<FabricanteInfoA, Boolean> tipoValorComision;
    public static volatile SingularAttribute<FabricanteInfoA, Date> fechaAutorizacion;
    public static volatile SingularAttribute<FabricanteInfoA, String> observacion;

}