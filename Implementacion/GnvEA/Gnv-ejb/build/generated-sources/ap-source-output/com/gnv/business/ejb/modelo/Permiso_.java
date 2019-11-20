package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Permiso.class)
public class Permiso_ { 

    public static volatile SingularAttribute<Permiso, String> descripcion;
    public static volatile SingularAttribute<Permiso, String> etiqueta;
    public static volatile SingularAttribute<Permiso, GrupoPermiso> grupoPermiso;
    public static volatile SingularAttribute<Permiso, String> permiso;
    public static volatile SingularAttribute<Permiso, Integer> id;
    public static volatile SingularAttribute<Permiso, Integer> orden;

}