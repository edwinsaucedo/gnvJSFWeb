package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.modelo.Permiso;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(GrupoPermiso.class)
public class GrupoPermiso_ { 

    public static volatile SingularAttribute<GrupoPermiso, String> descripcion;
    public static volatile SingularAttribute<GrupoPermiso, Boolean> expandido;
    public static volatile SingularAttribute<GrupoPermiso, GrupoPermiso> grupoPadre;
    public static volatile ListAttribute<GrupoPermiso, GrupoPermiso> grupoPermisoList;
    public static volatile SingularAttribute<GrupoPermiso, Integer> id;
    public static volatile SingularAttribute<GrupoPermiso, Integer> orden;
    public static volatile SingularAttribute<GrupoPermiso, String> nombre;
    public static volatile ListAttribute<GrupoPermiso, Permiso> permisoList;

}