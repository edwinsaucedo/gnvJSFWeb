package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Legalizacion;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Kit.class)
public class Kit_ { 

    public static volatile SingularAttribute<Kit, Marca> marca;
    public static volatile SingularAttribute<Kit, Date> fechaIns;
    public static volatile SingularAttribute<Kit, Date> fechaFabricacion;
    public static volatile SingularAttribute<Kit, Estatus> estatus;
    public static volatile SingularAttribute<Kit, Legalizacion> legalizacion;
    public static volatile SingularAttribute<Kit, String> serial;
    public static volatile SingularAttribute<Kit, Integer> estatusRow;
    public static volatile SingularAttribute<Kit, Integer> usuarioIns;
    public static volatile SingularAttribute<Kit, Integer> id;
    public static volatile SingularAttribute<Kit, Fabricante> fabricante;
    public static volatile SingularAttribute<Kit, Linea> linea;

}