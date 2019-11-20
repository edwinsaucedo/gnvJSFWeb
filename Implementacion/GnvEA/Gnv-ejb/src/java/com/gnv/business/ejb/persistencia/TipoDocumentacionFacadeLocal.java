/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoDocumentacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author US5
 */
@Local
public interface TipoDocumentacionFacadeLocal {

    void create(TipoDocumentacion tipoDocumentacion);

    void edit(TipoDocumentacion tipoDocumentacion);

    void remove(TipoDocumentacion tipoDocumentacion);

    TipoDocumentacion find(Object id);

    List<TipoDocumentacion> findAll();

    public List<TipoDocumentacion> findByEstatusRow();
    
    List<TipoDocumentacion> findRange(int[] range);

    int count();
    
}
