/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoConvenio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author US5
 */
@Local
public interface TipoConvenioFacadeLocal {

    void create(TipoConvenio tipoConvenio);

    void edit(TipoConvenio tipoConvenio);

    void remove(TipoConvenio tipoConvenio);

    TipoConvenio find(Object id);

    List<TipoConvenio> findAll();
    
    public List<TipoConvenio> findByEstatusRow();
    
    List<TipoConvenio> findRange(int[] range);

    int count();
    
}
