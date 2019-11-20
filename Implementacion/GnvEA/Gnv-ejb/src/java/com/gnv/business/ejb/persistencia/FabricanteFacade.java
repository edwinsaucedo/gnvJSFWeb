/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
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
public class FabricanteFacade extends AbstractFacade<Fabricante> implements FabricanteFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FabricanteFacade() {
        super(Fabricante.class);
    }
    
       @Override
    public List<Fabricante> findByEstatusRow(Estatus estatus)
    {
        List<Fabricante> fabricante = new ArrayList();
        
        try{
            Query query = em.createQuery("SELECT f FROM Fabricante f where f.estatus = :estatus ", Fabricante.class);
            query.setParameter("estatus", estatus);
            fabricante = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda fabricante, facade local: "+e.getMessage());
        }
        return fabricante;
    }
    
}
