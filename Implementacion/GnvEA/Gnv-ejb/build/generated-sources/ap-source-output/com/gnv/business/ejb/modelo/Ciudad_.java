package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Eds;
import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile ListAttribute<Ciudad, Eds> edsList;
    public static volatile SingularAttribute<Ciudad, Estado> estado;
    public static volatile ListAttribute<Ciudad, Fabricante> fabricanteList;
    public static volatile ListAttribute<Ciudad, VehiculoInfoA> vehiculoInfoAList;
    public static volatile SingularAttribute<Ciudad, Integer> id;
    public static volatile SingularAttribute<Ciudad, String> nombre;

}