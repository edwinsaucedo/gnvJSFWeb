/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Credito;

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
public class CreditoFacade extends AbstractFacade<Credito> implements CreditoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

      @Override
    public List <Credito> findByEstatusRow()
    {
        List<Credito> credito= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT c FROM Credito c where c.estatusRow > 0", Credito.class);
            credito = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda credito, facade local: "+e.getMessage());
        }
        return credito;
    }
    
    public CreditoFacade() {
        super(Credito.class);
    }
    
}
