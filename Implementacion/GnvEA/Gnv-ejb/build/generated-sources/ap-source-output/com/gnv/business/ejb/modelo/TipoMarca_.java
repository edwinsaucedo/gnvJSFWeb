package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Marca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(TipoMarca.class)
public class TipoMarca_ { 

    public static volatile SingularAttribute<TipoMarca, String> descripcion;
    public static volatile SingularAttribute<TipoMarca, Integer> id;
    public static volatile ListAttribute<TipoMarca, Marca> marcaList;
    public static volatile SingularAttribute<TipoMarca, String> nombre;

}