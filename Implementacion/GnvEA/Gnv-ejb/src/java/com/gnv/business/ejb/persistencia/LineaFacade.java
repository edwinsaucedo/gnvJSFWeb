/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
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
public class LineaFacade extends AbstractFacade<Linea> implements LineaFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public LineaFacade()
    {
        super(Linea.class);
    }

    @Override
    public List<Linea> findLineasByMarca(Marca marca)
    {
        List<Linea> linea = new ArrayList<>();
        try
        {
            Query query = em.createQuery("SELECT l FROM Linea l where l.marca=:marca", Linea.class);
            query.setParameter("marca",marca);
            linea = query.getResultList();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda tipo marca, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda tipo marca, facade local: " + e.getMessage());
        }
        return linea;
    }
    
    
    
}
