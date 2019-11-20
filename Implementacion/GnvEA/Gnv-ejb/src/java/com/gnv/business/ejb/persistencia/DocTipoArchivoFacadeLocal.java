/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.DocTipoArchivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface DocTipoArchivoFacadeLocal {

    void create(DocTipoArchivo docTipoArchivo);

    void edit(DocTipoArchivo docTipoArchivo);

    void remove(DocTipoArchivo docTipoArchivo);

    DocTipoArchivo find(Object id);

    List<DocTipoArchivo> findAll();

    List<DocTipoArchivo> findRange(int[] range);

    int count();
    
    public DocTipoArchivo findByExtension (String extension);
    
}
