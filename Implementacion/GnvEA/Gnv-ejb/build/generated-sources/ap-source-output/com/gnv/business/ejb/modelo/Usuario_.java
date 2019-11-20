package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.BitacoraActividades;
import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> claveAcceso;
    public static volatile SingularAttribute<Usuario, String> apellidoPaterno;
    public static volatile SingularAttribute<Usuario, Date> fechaModificacion;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistro;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Rol> rol;
    public static volatile ListAttribute<Usuario, BitacoraActividades> bitacoraActividadesList;
    public static volatile SingularAttribute<Usuario, String> apellidoMaterno;
    public static volatile SingularAttribute<Usuario, Boolean> actualizarHash;
    public static volatile SingularAttribute<Usuario, Compania> compania;
    public static volatile SingularAttribute<Usuario, Estatus> estatus;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> hash;

}