package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Compania.class)
public class Compania_ { 

    public static volatile ListAttribute<Compania, Taller> tallerList;
    public static volatile ListAttribute<Compania, Documento> documentoList;
    public static volatile SingularAttribute<Compania, String> razonSocial;
    public static volatile ListAttribute<Compania, Usuario> usuarioList;
    public static volatile ListAttribute<Compania, Financiera> financieraList;
    public static volatile ListAttribute<Compania, VehiculoInfoA> vehiculoInfoAList;
    public static volatile SingularAttribute<Compania, Long> cnciasidn;
    public static volatile SingularAttribute<Compania, Integer> id;
    public static volatile SingularAttribute<Compania, String> nombre;

}