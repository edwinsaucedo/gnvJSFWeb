package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Propietario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoDocumentacion.class)
public class TipoDocumentacion_ { 

    public static volatile ListAttribute<TipoDocumentacion, Propietario> propietarioList;
    public static volatile SingularAttribute<TipoDocumentacion, Integer> estatusRow;
    public static volatile ListAttribute<TipoDocumentacion, Fabricante> fabricanteList;
    public static volatile SingularAttribute<TipoDocumentacion, Integer> id;
    public static volatile SingularAttribute<TipoDocumentacion, String> nombre;

}