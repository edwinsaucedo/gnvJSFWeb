package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoMetaMes.class)
public class CreditoMetaMes_ { 

    public static volatile SingularAttribute<CreditoMetaMes, Date> fechaIns;
    public static volatile SingularAttribute<CreditoMetaMes, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoMetaMes, BigDecimal> valor;
    public static volatile SingularAttribute<CreditoMetaMes, Integer> usuariosIns;
    public static volatile SingularAttribute<CreditoMetaMes, Credito> credito;
    public static volatile SingularAttribute<CreditoMetaMes, Integer> mes;
    public static volatile SingularAttribute<CreditoMetaMes, Integer> id;

}