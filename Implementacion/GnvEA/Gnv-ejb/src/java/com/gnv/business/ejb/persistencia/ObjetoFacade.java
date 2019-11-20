/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Objeto;
import static java.lang.System.out;
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
public class ObjetoFacade extends AbstractFacade<Objeto> implements ObjetoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public ObjetoFacade()
    {
        super(Objeto.class);
    }

    @Override
    public Objeto findByNombre(String nombre)
    {
        Objeto objeto=new Objeto();
         try{
            Query query = em.createQuery("SELECT o FROM Objeto o where o.nombre=:nombre", Objeto.class);
            query.setParameter("nombre",nombre);
            objeto = (Objeto)query.getSingleResult();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda objeto, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda objeto, facade local: "+e.getMessage());
        }
         return objeto;
    }
    
}
