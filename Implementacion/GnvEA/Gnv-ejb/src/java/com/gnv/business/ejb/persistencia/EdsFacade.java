/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Eds;
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
public class EdsFacade extends AbstractFacade<Eds> implements EdsFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EdsFacade() {
        super(Eds.class);
    }
       
     @Override
    public List <Eds> findByEstatusRow()
    {
        List<Eds> eds= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT eds FROM Eds eds where eds.estatusRow > 0", Eds.class);
            eds = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda ed, facade local: "+e.getMessage());
        }
        return eds;
    }
    
}
