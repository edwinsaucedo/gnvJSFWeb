/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehiculoInfoA;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class VehiculoInfoAFacade extends AbstractFacade<VehiculoInfoA> implements VehiculoInfoAFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public VehiculoInfoAFacade()
    {
        super(VehiculoInfoA.class);
    }
    
}
