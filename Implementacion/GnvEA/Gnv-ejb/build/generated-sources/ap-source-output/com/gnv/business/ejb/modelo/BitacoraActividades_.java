package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(BitacoraActividades.class)
public class BitacoraActividades_ { 

    public static volatile SingularAttribute<BitacoraActividades, String> accion;
    public static volatile SingularAttribute<BitacoraActividades, Date> fechaActividad;
    public static volatile SingularAttribute<BitacoraActividades, Objeto> objeto;
    public static volatile SingularAttribute<BitacoraActividades, Usuario> usuario;
    public static volatile SingularAttribute<BitacoraActividades, Integer> id;
    public static volatile SingularAttribute<BitacoraActividades, Integer> registro;

}