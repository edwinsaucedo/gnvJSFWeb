package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.CreditoPersona;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.VehPropietario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Propietario.class)
public class Propietario_ { 

    public static volatile ListAttribute<Propietario, VehPropietario> vehPropietarioList;
    public static volatile SingularAttribute<Propietario, String> numeroIdentificacion;
    public static volatile SingularAttribute<Propietario, Integer> cnciasidn;
    public static volatile SingularAttribute<Propietario, String> nombre;
    public static volatile ListAttribute<Propietario, CreditoPersona> creditoPersonaList;
    public static volatile SingularAttribute<Propietario, Date> fechaIns;
    public static volatile SingularAttribute<Propietario, Integer> estatusRow;
    public static volatile SingularAttribute<Propietario, String> apellido;
    public static volatile SingularAttribute<Propietario, Integer> usuarioIns;
    public static volatile SingularAttribute<Propietario, Integer> id;
    public static volatile SingularAttribute<Propietario, Integer> cncdiridn;
    public static volatile SingularAttribute<Propietario, String> telefono;
    public static volatile SingularAttribute<Propietario, TipoDocumentacion> tipoDocumentacion;

}