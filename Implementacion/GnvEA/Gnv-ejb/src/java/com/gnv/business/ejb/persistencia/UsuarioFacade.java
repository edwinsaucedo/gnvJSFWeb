/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UsuarioFacade()
    {
        super(Usuario.class);
    }

    @Override
    public Usuario findByClave(String clave,Estatus estatus)
    {
        Usuario usuario = null;
        try
        {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.claveAcceso = :clave AND u.estatus=:estatus", Usuario.class);
            query.setParameter("clave", clave);
            query.setParameter("estatus", estatus);
            usuario = (Usuario) query.getSingleResult();
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

        return usuario;
    }

    @Override
    public List<Usuario> findByEstatus(Estatus estatus,Estatus estatus2)
    {
        List<Usuario> usuario = new ArrayList<>();
        try
        {
            String jpqlQuery="";
            if(estatus.getId()!=null & estatus2.getId()==null)
                jpqlQuery="select u from Usuario u where u.estatus=:estatus";
            else
                jpqlQuery="select u from Usuario u where u.estatus=:estatus or u.estatus=:estatus2";
            Query query = em.createQuery(jpqlQuery, Usuario.class);
            if(estatus.getId()!=null & estatus2.getId()==null)
              query.setParameter("estatus", estatus);
            else{
                query.setParameter("estatus", estatus);
                query.setParameter("estatus2", estatus2);
            }
            usuario = query.getResultList();
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

        return usuario;
    }
    
    
}
