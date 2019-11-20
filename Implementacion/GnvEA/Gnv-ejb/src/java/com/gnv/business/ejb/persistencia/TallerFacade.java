/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Taller;
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
public class TallerFacade extends AbstractFacade<Taller> implements TallerFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TallerFacade() {
        super(Taller.class);
    }
   
    @Override
    public List<Taller> findByEstatusRow()
    {
        List<Taller> taller = new ArrayList();
        
        try{
            Query query = em.createQuery("SELECT t FROM Taller t where t.estatusRow>0", Taller.class);
            taller = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Tipo Taller, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda taller, facade local: "+e.getMessage());
        }
        return taller;
    }
    
       @Override
    public List<Taller> findByCompania(Compania compania)
    {
        List<Taller> taller = new ArrayList();
        
        try{
            Query query = em.createQuery("SELECT t FROM Taller t where t.estatusRow>0 and t.compania = :compania", Taller.class);
            query.setParameter("compania",compania);
            taller = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Tipo Taller, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda taller, facade local: "+e.getMessage());
        }
        return taller;
    }
    
}
