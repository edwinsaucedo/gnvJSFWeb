package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.Eds;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoEds.class)
public class CreditoEds_ { 

    public static volatile SingularAttribute<CreditoEds, Date> fechaIns;
    public static volatile SingularAttribute<CreditoEds, Eds> eds;
    public static volatile SingularAttribute<CreditoEds, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoEds, Integer> usuarioIns;
    public static volatile SingularAttribute<CreditoEds, Credito> credito;
    public static volatile SingularAttribute<CreditoEds, Long> id;

}