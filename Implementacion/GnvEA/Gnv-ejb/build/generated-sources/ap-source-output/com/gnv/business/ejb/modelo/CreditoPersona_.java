package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.Propietario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoPersona.class)
public class CreditoPersona_ { 

    public static volatile SingularAttribute<CreditoPersona, Date> fechaIns;
    public static volatile SingularAttribute<CreditoPersona, Propietario> propietario;
    public static volatile SingularAttribute<CreditoPersona, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoPersona, Integer> usuariosIns;
    public static volatile SingularAttribute<CreditoPersona, Credito> credito;
    public static volatile SingularAttribute<CreditoPersona, Integer> id;

}