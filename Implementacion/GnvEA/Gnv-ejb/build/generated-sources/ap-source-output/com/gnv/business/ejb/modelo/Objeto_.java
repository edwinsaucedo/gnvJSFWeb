package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.BitacoraActividades;
import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Estatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Objeto.class)
public class Objeto_ { 

    public static volatile SingularAttribute<Objeto, String> descripcion;
    public static volatile ListAttribute<Objeto, Documento> documentoList;
    public static volatile ListAttribute<Objeto, Estatus> estatusList;
    public static volatile SingularAttribute<Objeto, Integer> id;
    public static volatile SingularAttribute<Objeto, String> nombre;
    public static volatile ListAttribute<Objeto, BitacoraActividades> bitacoraActividadesList;

}