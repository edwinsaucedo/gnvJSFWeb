package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.FlotaVehiculo;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Revision;
import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.modelo.VehEmpresa;
import com.gnv.business.ejb.modelo.VehEquipo;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile ListAttribute<Vehiculo, VehPropietario> vehPropietarioList;
    public static volatile SingularAttribute<Vehiculo, VehTipo> tipo;
    public static volatile SingularAttribute<Vehiculo, VehTipoServicio> tipoServicio;
    public static volatile SingularAttribute<Vehiculo, VehiculoInfoA> vehiculoInfoA;
    public static volatile ListAttribute<Vehiculo, Revision> revisionList;
    public static volatile ListAttribute<Vehiculo, VehEmpresa> vehEmpresaList;
    public static volatile SingularAttribute<Vehiculo, Date> fechaProxRev;
    public static volatile SingularAttribute<Vehiculo, Integer> modelo;
    public static volatile ListAttribute<Vehiculo, VehEquipo> vehEquipoList;
    public static volatile SingularAttribute<Vehiculo, Linea> linea;
    public static volatile SingularAttribute<Vehiculo, String> engomadoPatrimonial;
    public static volatile SingularAttribute<Vehiculo, VehClase> clase;
    public static volatile ListAttribute<Vehiculo, Credito> creditoList;
    public static volatile SingularAttribute<Vehiculo, Marca> marca;
    public static volatile ListAttribute<Vehiculo, FlotaVehiculo> flotaVehiculoList;
    public static volatile SingularAttribute<Vehiculo, Date> fechaIns;
    public static volatile SingularAttribute<Vehiculo, Estatus> estatus;
    public static volatile SingularAttribute<Vehiculo, Integer> estatusRow;
    public static volatile SingularAttribute<Vehiculo, Integer> usuarioIns;
    public static volatile SingularAttribute<Vehiculo, String> vin;
    public static volatile SingularAttribute<Vehiculo, Integer> id;
    public static volatile ListAttribute<Vehiculo, Valoracion> valoracionList;
    public static volatile SingularAttribute<Vehiculo, String> placa;

}