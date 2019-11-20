/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.TipoMarca;
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
public class MarcaFacade extends AbstractFacade<Marca> implements MarcaFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public MarcaFacade()
    {
        super(Marca.class);
    }

    @Override
    public List<Marca> findByEstatusRow()
    {
        List<Marca> marca = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT m FROM Marca m where m.estatusRow>0", Marca.class);
            marca = query.getResultList();

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
        return marca;
    }

    @Override
    public List<Marca> findByTipoMarca(TipoMarca tipoMarca)
    {
        List<Marca> marca = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT m FROM Marca m where m.tipoMarca=:tipoMarca and m.estatusRow>0", Marca.class);
            query.setParameter("tipoMarca",tipoMarca);
            marca = query.getResultList();

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
        return marca;
    }

}
