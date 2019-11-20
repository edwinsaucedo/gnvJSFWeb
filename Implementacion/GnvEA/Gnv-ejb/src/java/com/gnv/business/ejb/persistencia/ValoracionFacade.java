/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.Vehiculo;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ValoracionFacade extends AbstractFacade<Valoracion> implements ValoracionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }

    @Override
    public List<Valoracion> findValoracion() {
        List<Valoracion> valoracion = new ArrayList();

        try {
            Query query = em.createQuery("Select v FROM Valoracion v where v.estatusRow>0", Valoracion.class);
            valoracion = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda valoracion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda valoracion, facade local: " + e.getMessage());
        }
        return valoracion;
    }

    @Override
    public Valoracion findByVehiculo(Vehiculo vehiculo) {
        Valoracion valoracion = new Valoracion();

        try {
            Query query = em.createQuery("Select v FROM Valoracion v where v.estatusRow>-1 and v.vehiculo = :vehiculo", Valoracion.class);
            query.setParameter("vehiculo", vehiculo);
            valoracion = (Valoracion) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda valoracion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda valoracion, facade local: " + e.getMessage());
        }
        return valoracion;
    }

    @Override
    public List<Valoracion> findByPlacaVehiculo(Vehiculo vehiculo) {
        List<Valoracion> valoracion = new ArrayList();
        String nQuery = "select v.* from  VALORACION v inner join VEHICULO vh on v.VEHICULO=vh.ID\n"
                + " where  v.ESTADO=1  and v.ESTATUS_ROW>-1 and vh.PLACA like ?1 and not exists(SELECT null from CONVERSION c where c.VALORACION=v.ID and ESTATUS_ROW>0) ";
        try {
            Query query = em.createNativeQuery(nQuery, Valoracion.class);
            query.setParameter(1, "%" + vehiculo.getPlaca() + "%");
            valoracion = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda valoracion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda valoracion, facade local: " + e.getMessage());
        }
        return valoracion;
    }

    @Override
    public Valoracion findByPlacaVehiculo(String placa) {

        Valoracion valoracion = new Valoracion();
        String nQuery = "select v.* from  VALORACION v inner join VEHICULO vh on v.VEHICULO=vh.ID\n"
                + " where  v.ESTADO=1  and v.ESTATUS_ROW>-1 and vh.PLACA = ? and not exists(SELECT null from CONVERSION c where c.VALORACION=v.ID and ESTATUS_ROW>0) ";
        try {
            Query query = em.createNativeQuery(nQuery, Valoracion.class);
            query.setParameter(1, placa);
            valoracion = (Valoracion) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda valoracion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda valoracion, facade local: " + e.getMessage());
        }
        return valoracion;
    }
    
     @Override
    public HashMap<String, Integer> findValoracionesMes()
    {
        HashMap<String, Integer> valoraciones = new HashMap<>();
        String key = "";
        Integer value = 0;
        Object[] response;
        List<Object[]> auxValoraciones;
        try
        {

            String nQuery = "select coalesce(datename(month,v.fecha),'Desconocido') MES,COUNT(v.ID) VALORACIONES \n"
                    + "from valoracion v where v.ESTATUS_ROW >-1\n"
                    + "group by datename(month,v.fecha) \n"
                    + "order by datename(month,v.fecha) desc";
            Query query = em.createNativeQuery(nQuery);
            auxValoraciones = query.getResultList();
            for (int i = auxValoraciones.size() - 1; i >= 0; i--)
            {
                response = auxValoraciones.get(i);
                key = (String) response[0];
                value = (Integer) response[1];
                valoraciones.put(key, value);
            }
        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return valoraciones;
    }

}
