/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Legalizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CilindroFacadeLocal {

    void create(Cilindro cilindro);

    void edit(Cilindro cilindro);

    void remove(Cilindro cilindro);

    Cilindro find(Object id);
    
    public Cilindro findCilindroBySerial(String serieKit);
    
    public List<Cilindro> findByConversion(Conversion conversion);

    List<Cilindro> findAll();

    List<Cilindro> findByEstatusRow(Legalizacion legalizacion);

    List<Cilindro> findRange(int[] range);

    int count();

}
