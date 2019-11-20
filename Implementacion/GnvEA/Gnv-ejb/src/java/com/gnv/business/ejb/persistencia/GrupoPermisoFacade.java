/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.GrupoPermiso;
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
public class GrupoPermisoFacade extends AbstractFacade<GrupoPermiso> implements GrupoPermisoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public GrupoPermisoFacade()
    {
        super(GrupoPermiso.class);
    }

    @Override
    public List<GrupoPermiso> findPadres()
    {
        List<GrupoPermiso> grupoPermisos = new ArrayList();
        try
        {
            Query query = em.createQuery("SELECT gp FROM GrupoPermiso gp WHERE gp.grupoPadre IS NULL", GrupoPermiso.class);
            grupoPermisos = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return grupoPermisos;
    }

    @Override
    public List<GrupoPermiso> findHijos(GrupoPermiso grupoPermisoPadre)
    {
        List<GrupoPermiso> grupoPermisos = new ArrayList();
        try
        {
            Query query = em.createQuery("SELECT gp FROM GrupoPermiso gp WHERE gp.grupoPadre=:grupoPermisoPadre", GrupoPermiso.class);
            query.setParameter("grupoPermisoPadre", grupoPermisoPadre);
            grupoPermisos = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return grupoPermisos;
    }
    
}
