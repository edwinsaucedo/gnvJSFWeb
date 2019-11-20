package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(CreditoMetasGen.class)
public class CreditoMetasGen_ { 

    public static volatile SingularAttribute<CreditoMetasGen, Integer> tipoPagos;
    public static volatile SingularAttribute<CreditoMetasGen, Boolean> proporcionPrimerMes;
    public static volatile SingularAttribute<CreditoMetasGen, Integer> diasAviso;
    public static volatile SingularAttribute<CreditoMetasGen, Date> fechaIns;
    public static volatile SingularAttribute<CreditoMetasGen, Integer> estatusRow;
    public static volatile SingularAttribute<CreditoMetasGen, Integer> usuariosIns;
    public static volatile SingularAttribute<CreditoMetasGen, Credito> credito;
    public static volatile SingularAttribute<CreditoMetasGen, BigDecimal> valorMeta;
    public static volatile SingularAttribute<CreditoMetasGen, Integer> id;
    public static volatile SingularAttribute<CreditoMetasGen, Boolean> bloqueo;
    public static volatile SingularAttribute<CreditoMetasGen, Integer> tipoMeta;

}