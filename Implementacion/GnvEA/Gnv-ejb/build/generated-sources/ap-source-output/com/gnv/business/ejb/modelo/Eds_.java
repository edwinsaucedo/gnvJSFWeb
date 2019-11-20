package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.CreditoEds;
import com.gnv.business.ejb.modelo.Empresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Eds.class)
public class Eds_ { 

    public static volatile SingularAttribute<Eds, String> latitud;
    public static volatile SingularAttribute<Eds, String> longitud;
    public static volatile SingularAttribute<Eds, String> codigoPostal;
    public static volatile ListAttribute<Eds, CreditoEds> creditoEdsList;
    public static volatile SingularAttribute<Eds, Ciudad> ciudad;
    public static volatile SingularAttribute<Eds, Integer> estatusRow;
    public static volatile SingularAttribute<Eds, String> direccion;
    public static volatile SingularAttribute<Eds, Integer> id;
    public static volatile SingularAttribute<Eds, Empresa> empresa;
    public static volatile SingularAttribute<Eds, String> nombre;

}