/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.modelo.Permiso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;

/**
 *
 * @author Administrador
 */
@Stateless
public class PermisoFacade extends AbstractFacade<Permiso> implements PermisoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PermisoFacade()
    {
        super(Permiso.class);
    }

    @Override
    public List<Permiso> findByGrupoPermisoPadre(GrupoPermiso grupoPermisoPadre)
    {
        List<Permiso> permisos = new ArrayList();
        try
        {
            Query query = em.createQuery("SELECT p FROM Permiso p WHERE p.grupoPermiso=:grupoPermisoPadre", Permiso.class);
            query.setParameter("grupoPermisoPadre", grupoPermisoPadre);
            permisos = query.getResultList();
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
        
        return permisos;
    }
    
}
