/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Rol;
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
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public RolFacade()
    {
        super(Rol.class);
    }
    
    @Override
    public Boolean existeRol(String nombre)
    {
        Boolean existe = false;
        try
        {
            Query query = em.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre", Rol.class);
            query.setParameter("nombre", nombre);
            Rol rol = (Rol) query.getSingleResult();
            if (null != rol)
            {
                existe = true;
            }
        } catch (NoResultException nre)
        {
            return false;
        } catch (EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return existe;
    }
    
    @Override
    public Boolean eliminaPermisoRol(String nombre)
    {
       Boolean flag = false;
        try
        {
            Query query = em.createQuery("DELETE FROM Rol r WHERE r.nombre = :nombre ", Rol.class);
            query.setParameter("nombre", nombre);                        
            if (query.executeUpdate()>0)
            {
                flag = true;
            }
        } catch(NoResultException nre)
        {
            return flag = false;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return flag;
    }
    
}
