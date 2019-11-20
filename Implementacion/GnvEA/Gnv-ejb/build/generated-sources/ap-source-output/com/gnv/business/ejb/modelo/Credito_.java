package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.CreditoEds;
import com.gnv.business.ejb.modelo.CreditoMetaMes;
import com.gnv.business.ejb.modelo.CreditoMetasGen;
import com.gnv.business.ejb.modelo.CreditoPersona;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.TipoCredito;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Credito.class)
public class Credito_ { 

    public static volatile SingularAttribute<Credito, String> numero;
    public static volatile ListAttribute<Credito, CreditoMetasGen> creditoMetasGenList;
    public static volatile ListAttribute<Credito, CreditoEds> creditoEdsList;
    public static volatile SingularAttribute<Credito, Integer> plazo;
    public static volatile ListAttribute<Credito, CreditoMetaMes> creditoMetaMesList;
    public static volatile SingularAttribute<Credito, Double> montoCredito;
    public static volatile SingularAttribute<Credito, Integer> usuariosIns;
    public static volatile SingularAttribute<Credito, Integer> estadoCredito;
    public static volatile SingularAttribute<Credito, String> digitoV;
    public static volatile SingularAttribute<Credito, Double> baseRecaudo;
    public static volatile SingularAttribute<Credito, Double> consumoMinimo;
    public static volatile SingularAttribute<Credito, Date> fechaIns;
    public static volatile SingularAttribute<Credito, Integer> estatusRow;
    public static volatile SingularAttribute<Credito, TipoCredito> tipoCredito;
    public static volatile SingularAttribute<Credito, Integer> id;
    public static volatile SingularAttribute<Credito, Financiera> financiera;
    public static volatile SingularAttribute<Credito, Date> fechaAut;
    public static volatile SingularAttribute<Credito, Double> anticipo;
    public static volatile SingularAttribute<Credito, Boolean> metas;
    public static volatile SingularAttribute<Credito, Taller> taller;
    public static volatile SingularAttribute<Credito, Integer> formaPagoRec;
    public static volatile SingularAttribute<Credito, Vehiculo> vehiculo;
    public static volatile ListAttribute<Credito, CreditoPersona> creditoPersonaList;
    public static volatile SingularAttribute<Credito, Date> fechaDes;
    public static volatile SingularAttribute<Credito, Integer> tipoRecaudo;
    public static volatile SingularAttribute<Credito, Double> montoSolicitado;
    public static volatile SingularAttribute<Credito, Double> interes;

}