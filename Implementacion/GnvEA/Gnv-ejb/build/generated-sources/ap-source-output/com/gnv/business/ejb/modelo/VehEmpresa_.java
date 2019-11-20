package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(VehEmpresa.class)
public class VehEmpresa_ { 

    public static volatile SingularAttribute<VehEmpresa, Date> fechaIns;
    public static volatile SingularAttribute<VehEmpresa, Integer> estatusRow;
    public static volatile SingularAttribute<VehEmpresa, Integer> usuarioIns;
    public static volatile SingularAttribute<VehEmpresa, Integer> id;
    public static volatile SingularAttribute<VehEmpresa, Vehiculo> vehiculo;
    public static volatile SingularAttribute<VehEmpresa, Empresa> empresa;

}