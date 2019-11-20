package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Linea.class)
public class Linea_ { 

    public static volatile SingularAttribute<Linea, String> descripcion;
    public static volatile SingularAttribute<Linea, Marca> marca;
    public static volatile ListAttribute<Linea, Cilindro> cilindroList;
    public static volatile ListAttribute<Linea, Kit> kitList;
    public static volatile SingularAttribute<Linea, Integer> id;
    public static volatile SingularAttribute<Linea, String> nombre;
    public static volatile ListAttribute<Linea, Vehiculo> vehiculoList;

}