package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile SingularAttribute<Marca, String> descripcion;
    public static volatile SingularAttribute<Marca, Date> fechaIns;
    public static volatile ListAttribute<Marca, Cilindro> cilindroList;
    public static volatile SingularAttribute<Marca, TipoMarca> tipoMarca;
    public static volatile SingularAttribute<Marca, Integer> estatusRow;
    public static volatile ListAttribute<Marca, Linea> lineaList;
    public static volatile SingularAttribute<Marca, Integer> usuarioIns;
    public static volatile ListAttribute<Marca, Kit> kitList;
    public static volatile SingularAttribute<Marca, Integer> id;
    public static volatile SingularAttribute<Marca, String> nombre;
    public static volatile ListAttribute<Marca, Vehiculo> vehiculoList;

}