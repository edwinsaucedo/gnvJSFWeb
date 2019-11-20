/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.Vehiculo;
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
public class PropietarioFacade extends AbstractFacade<Propietario> implements PropietarioFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PropietarioFacade()
    {
        super(Propietario.class);
    }

    @Override
    public List<Propietario> findByEstatusRow()
    {
       List<Propietario> propietario = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT p FROM Propietario p where p.estatusRow>0", Propietario.class);
            propietario = query.getResultList();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda propietario, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda  propietario, facade local: " + e.getMessage());
        }
        return propietario;
    }

    
}
