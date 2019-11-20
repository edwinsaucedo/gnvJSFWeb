/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza;
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
public class CreditoCotizacionAmortizaFacade extends AbstractFacade<CreditoCotizacionAmortiza> implements CreditoCotizacionAmortizaFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoCotizacionAmortizaFacade() {
        super(CreditoCotizacionAmortiza.class);
    }
    
    
     @Override
    public List<CreditoCotizacionAmortiza> findByEstatusRow(CreditoCotizacion creditoCotizacion) {
        List<CreditoCotizacionAmortiza> creditoCotizacionAmortiza = new ArrayList();

        try {
            Query query = em.createQuery("SELECT cca FROM CreditoCotizacionAmortiza cca where cca.estatusRow>0 and cca.creditoCotizacion = :creditoCotizacion", CreditoCotizacionAmortiza.class);
            query.setParameter("creditoCotizacion",creditoCotizacion);
            creditoCotizacionAmortiza = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda CreditoCotizacionAmortiza, facade local: " + e.getMessage());
        }
        return creditoCotizacionAmortiza;
    }
    
}
