/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Pais;
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
 * @author Administrador
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> implements EstadoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstadoFacade()
    {
        super(Estado.class);
    }

    @Override
    public List<Estado> findByPais(Pais pais)
    {
        List<Estado> estado= new ArrayList<>();
        
        try
        {
            Query query = em.createQuery("SELECT e FROM Estado e where e.pais=:pais", Estado.class);
            query.setParameter("pais",pais);
            estado = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Estados, facade local: "+ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda Estados, facade local: "+e.getMessage());
        }
        return estado;
    }
    
}
