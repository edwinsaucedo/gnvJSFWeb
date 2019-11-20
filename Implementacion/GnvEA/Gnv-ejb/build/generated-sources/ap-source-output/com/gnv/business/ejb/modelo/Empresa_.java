package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Eds;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Taller;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile ListAttribute<Empresa, Eds> edsList;
    public static volatile ListAttribute<Empresa, Taller> tallerList;
    public static volatile SingularAttribute<Empresa, Integer> estatusRow;
    public static volatile ListAttribute<Empresa, Fabricante> fabricanteList;
    public static volatile SingularAttribute<Empresa, Integer> id;
    public static volatile SingularAttribute<Empresa, String> nombre;
    public static volatile SingularAttribute<Empresa, String> rfc;

}