/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Financiera;
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
public class FinancieraFacade extends AbstractFacade<Financiera> implements FinancieraFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     @Override
    public List<Financiera> findByEstatusRow()
    {
        List<Financiera> financiera= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT f FROM Financiera f where f.estatusRow > 0", Financiera.class);
            financiera = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda financiera, facade local: "+e.getMessage());
        }
        return financiera;
    }
    public FinancieraFacade() {
        super(Financiera.class);
    }
    
}
