/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Objeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface DocumentoFacadeLocal {

    void create(Documento documento);

    void edit(Documento documento);

    void remove(Documento documento);

    Documento find(Object id);

    List<Documento> findAll();

    public List<Documento> findByObjeto(Objeto objeto,Integer idRegistro);
    
    public List<Documento> findByRegistros(Objeto objeto,List<Integer> idRegistros);

    List<Documento> findByLegalizacion(Integer legalizacion);

    List<Documento> findRange(int[] range);

    int count();

}
