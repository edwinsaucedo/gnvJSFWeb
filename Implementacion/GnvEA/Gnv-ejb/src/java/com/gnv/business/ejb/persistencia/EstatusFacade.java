/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;

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
public class EstatusFacade extends AbstractFacade<Estatus> implements EstatusFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstatusFacade()
    {
        super(Estatus.class);
    }

    public Estatus findByClase(String nombreClase, String nombreStatus)
    {

        Estatus s = new Estatus();
        try
        {
            if (null != nombreClase && !nombreClase.isEmpty())
            {
                if (null != nombreStatus && !nombreStatus.isEmpty())
                {
                    Query query = em.createQuery("SELECT s FROM Estatus s WHERE s.nombreClase = :nombreClase AND s.nombre = :nombreStatus", Estatus.class);
                    query.setParameter("nombreClase", nombreClase);
                    query.setParameter("nombreStatus", nombreStatus);
                    s = (Estatus) query.getSingleResult();
                }
            }
        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Estatus> findByClaseList(String nombreClase)
    {
        List<Estatus> estatus = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT s FROM Estatus s WHERE s.nombreClase = :nombreClase", Estatus.class);
            query.setParameter("nombreClase", nombreClase);
            estatus = query.getResultList();
        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda Clase lista, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda clase lista, facade local: " + e.getMessage());
        }
        return estatus;
    }

}
