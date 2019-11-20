/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PC2
 */
@Stateless
public class CreditoCotizacionFacade extends AbstractFacade<CreditoCotizacion> implements CreditoCotizacionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoCotizacionFacade() {
        super(CreditoCotizacion.class);
    }
    
     @Override
    public List<CreditoCotizacion> findByEstatusRow() {
        List<CreditoCotizacion> creditoCotiza = new ArrayList();

        try {
            Query query = em.createQuery("SELECT cc FROM CreditoCotizacion cc where cc.estatusRow > 0", CreditoCotizacion.class);
           
            creditoCotiza = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda creditoeds, facade local: " + e.getMessage());
        }
        return creditoCotiza;
    }
    
    
    
}

    

