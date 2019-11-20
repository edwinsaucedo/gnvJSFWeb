package com.gnv.business.ejb.persistencia;

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
 * @author PC2
 */
@Stateless
public class TipoMarcaFacade extends AbstractFacade<TipoMarca> implements TipoMarcaFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TipoMarcaFacade()
    {
        super(TipoMarca.class);
    }

    @Override
    public TipoMarca findByTipoMarcaNombre(String nombre)
    {
        
        TipoMarca tipoMarca = new TipoMarca();
        try
        {
            Query query = em.createQuery("SELECT tm FROM TipoMarca tm where tm.nombre=:nombre", TipoMarca.class);
            query.setParameter("nombre", nombre);
            tipoMarca = (TipoMarca)query.getSingleResult();

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
        return tipoMarca;
    }

}
