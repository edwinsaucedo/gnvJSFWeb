package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Legalizacion;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.TipoCilindro;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Cilindro.class)
public class Cilindro_ { 

    public static volatile SingularAttribute<Cilindro, String> lote;
    public static volatile SingularAttribute<Cilindro, Linea> linea;
    public static volatile SingularAttribute<Cilindro, String> clase;
    public static volatile SingularAttribute<Cilindro, Marca> marca;
    public static volatile SingularAttribute<Cilindro, Date> fechaIns;
    public static volatile SingularAttribute<Cilindro, Date> fechaFabricacion;
    public static volatile SingularAttribute<Cilindro, Estatus> estatus;
    public static volatile SingularAttribute<Cilindro, Legalizacion> legalizacion;
    public static volatile SingularAttribute<Cilindro, String> serial;
    public static volatile SingularAttribute<Cilindro, Integer> estatusRow;
    public static volatile SingularAttribute<Cilindro, TipoCilindro> tipoCilindro;
    public static volatile SingularAttribute<Cilindro, Integer> usuarioIns;
    public static volatile SingularAttribute<Cilindro, Integer> id;
    public static volatile SingularAttribute<Cilindro, Fabricante> fabricante;
    public static volatile SingularAttribute<Cilindro, BigDecimal> capacidad;

}