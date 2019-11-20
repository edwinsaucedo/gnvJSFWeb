package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.TipoDocLegal;
import com.gnv.business.ejb.modelo.TipoLegalizacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Legalizacion.class)
public class Legalizacion_ { 

    public static volatile SingularAttribute<Legalizacion, String> numeroLegal;
    public static volatile SingularAttribute<Legalizacion, String> informacionRegulador;
    public static volatile SingularAttribute<Legalizacion, String> numeroMotor;
    public static volatile SingularAttribute<Legalizacion, TipoDocLegal> tipoDocumento;
    public static volatile SingularAttribute<Legalizacion, Date> fechaIns;
    public static volatile ListAttribute<Legalizacion, Cilindro> cilindroList;
    public static volatile SingularAttribute<Legalizacion, TipoLegalizacion> tipoLegalizacion;
    public static volatile SingularAttribute<Legalizacion, String> numeroDoc;
    public static volatile SingularAttribute<Legalizacion, Integer> estatusRow;
    public static volatile SingularAttribute<Legalizacion, Integer> usuarioIns;
    public static volatile ListAttribute<Legalizacion, Kit> kitList;
    public static volatile SingularAttribute<Legalizacion, Integer> id;
    public static volatile SingularAttribute<Legalizacion, Long> cncdiridn;

}