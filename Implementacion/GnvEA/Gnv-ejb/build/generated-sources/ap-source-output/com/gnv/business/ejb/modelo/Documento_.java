package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Objeto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(Documento.class)
public class Documento_ { 

    public static volatile SingularAttribute<Documento, Date> fechaIns;
    public static volatile SingularAttribute<Documento, Compania> compania;
    public static volatile SingularAttribute<Documento, String> ruta;
    public static volatile SingularAttribute<Documento, String> tamanio;
    public static volatile SingularAttribute<Documento, Integer> estatusRow;
    public static volatile SingularAttribute<Documento, Integer> usuarioIns;
    public static volatile SingularAttribute<Documento, Objeto> objeto;
    public static volatile SingularAttribute<Documento, Integer> id;
    public static volatile SingularAttribute<Documento, DocTipoArchivo> tipoArchivo;
    public static volatile SingularAttribute<Documento, String> nombre;
    public static volatile SingularAttribute<Documento, Boolean> publico;
    public static volatile SingularAttribute<Documento, Integer> idRegistro;

}