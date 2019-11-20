package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Estatus.class)
public class Estatus_ { 

    public static volatile ListAttribute<Estatus, Cilindro> cilindroList;
    public static volatile ListAttribute<Estatus, Usuario> usuarioList;
    public static volatile SingularAttribute<Estatus, String> nombreClase;
    public static volatile ListAttribute<Estatus, Fabricante> fabricanteList;
    public static volatile SingularAttribute<Estatus, Objeto> objeto;
    public static volatile ListAttribute<Estatus, Kit> kitList;
    public static volatile SingularAttribute<Estatus, Integer> id;
    public static volatile ListAttribute<Estatus, Conversion> conversionList;
    public static volatile SingularAttribute<Estatus, String> nombre;
    public static volatile ListAttribute<Estatus, Vehiculo> vehiculoList;

}