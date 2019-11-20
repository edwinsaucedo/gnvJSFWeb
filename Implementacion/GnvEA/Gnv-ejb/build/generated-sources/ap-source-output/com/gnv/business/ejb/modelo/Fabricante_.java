package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.FabricanteInfoA;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Fabricante.class)
public class Fabricante_ { 

    public static volatile SingularAttribute<Fabricante, String> codigo;
    public static volatile SingularAttribute<Fabricante, String> contacto;
    public static volatile SingularAttribute<Fabricante, String> direccion;
    public static volatile SingularAttribute<Fabricante, String> nombre;
    public static volatile SingularAttribute<Fabricante, FabricanteInfoA> fabricanteInfoA;
    public static volatile SingularAttribute<Fabricante, TipoDocumentacion> tipoDocumento;
    public static volatile ListAttribute<Fabricante, Cilindro> cilindroList;
    public static volatile SingularAttribute<Fabricante, Estatus> estatus;
    public static volatile SingularAttribute<Fabricante, Ciudad> ciudad;
    public static volatile ListAttribute<Fabricante, Kit> kitList;
    public static volatile SingularAttribute<Fabricante, Integer> id;
    public static volatile SingularAttribute<Fabricante, String> numeroDocumento;
    public static volatile SingularAttribute<Fabricante, String> telefono;
    public static volatile SingularAttribute<Fabricante, Empresa> empresa;
    public static volatile SingularAttribute<Fabricante, String> email;

}