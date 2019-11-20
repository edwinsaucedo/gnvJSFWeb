/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.UserRoles;
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
public class UserRolesFacade extends AbstractFacade<UserRoles> implements UserRolesFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UserRolesFacade()
    {
        super(UserRoles.class);
    }

    @Override
    public List<String> findRolesNamesByUser(String nickName)
    {
          List<String> nombreRoles = null;
        try
        {
            String sConsulta = "SELECT UR.ROLE_NAME FROM USER_ROLES UR WHERE UR.USERNAME = ?";
            Query query = em.createNativeQuery(sConsulta);
            query.setParameter(1, nickName);
            nombreRoles = query.getResultList();
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
        return nombreRoles;
    }
    
    @Override
    public UserRoles findByUserName(String nickName)
    {
        UserRoles userRoles = null;
        try
        {
            String sConsulta = "SELECT UR FROM UserRoles UR WHERE UR.username = :username";
            Query query = em.createQuery(sConsulta, UserRoles.class);
            query.setParameter("username", nickName);
            userRoles = (UserRoles) query.getSingleResult();
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
        return userRoles;
    }
    
}
