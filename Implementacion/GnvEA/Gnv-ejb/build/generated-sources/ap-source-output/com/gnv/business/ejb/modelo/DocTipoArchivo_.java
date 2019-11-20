package com.gnv.business.ejb.modelo;

import com.gnv.business.ejb.modelo.Documento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20170522-rNA", date="2019-10-09T10:20:48")
@StaticMetamodel(DocTipoArchivo.class)
public class DocTipoArchivo_ { 

    public static volatile SingularAttribute<DocTipoArchivo, String> descripcion;
    public static volatile SingularAttribute<DocTipoArchivo, String> extension;
    public static volatile ListAttribute<DocTipoArchivo, Documento> documentoList;
    public static volatile SingularAttribute<DocTipoArchivo, Integer> estatusRow;
    public static volatile SingularAttribute<DocTipoArchivo, Integer> id;
    public static volatile SingularAttribute<DocTipoArchivo, String> mimeType;
    public static volatile SingularAttribute<DocTipoArchivo, String> nombre;

}