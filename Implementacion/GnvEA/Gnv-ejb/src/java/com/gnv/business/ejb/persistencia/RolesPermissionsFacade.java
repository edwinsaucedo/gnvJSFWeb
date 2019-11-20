/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.RolesPermissions;
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
public class RolesPermissionsFacade extends AbstractFacade<RolesPermissions> implements RolesPermissionsFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public RolesPermissionsFacade()
    {
        super(RolesPermissions.class);
    }

    @Override
    public List<RolesPermissions> findByRol(String nombreRol)
    {
        List<RolesPermissions> rolesPermissionses = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT rp FROM RolesPermissions rp WHERE rp.roleName = :roleName", RolesPermissions.class);
            query.setParameter("roleName", nombreRol);
            rolesPermissionses = query.getResultList();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda permisos por roles, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda de permisos por roles, facade local: " + e.getMessage());
        }
        return rolesPermissionses;
    }

    @Override
    public Boolean existePermisoRol(String nombreRol, String permiso)
    {
        Boolean existe = false;
        try
        {
            Query query = em.createQuery("SELECT rp FROM RolesPermissions rp WHERE rp.roleName = :nombreRol AND rp.permission = :permission", RolesPermissions.class);
            query.setParameter("nombreRol", nombreRol);
            query.setParameter("permission", permiso);
            RolesPermissions rp = (RolesPermissions) query.getSingleResult();
            if (rp != null)
            {
                existe = true;
            }
        } catch (NoResultException nre)
        {
            return existe = false;
        } catch (EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return existe;
    }

}
