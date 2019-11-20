/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Estatus;
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
 * @author Administrador
 */
@Stateless
public class ConversionFacade extends AbstractFacade<Conversion> implements ConversionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public ConversionFacade()
    {
        super(Conversion.class);
    }

    @Override
    public List<Conversion> findByEstatus(Estatus estatus)
    {
        List<Conversion> conversion = new ArrayList<>();
        try
        {
            Query query = em.createQuery("SELECT c FROM Conversion c WHERE c.estatus = :estatus", Conversion.class);
            query.setParameter("estatus", estatus);
            conversion = query.getResultList();
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
        return conversion;
    }

    @Override
    public List<Conversion> findByDate()
    {
        List<Conversion> conversion = new ArrayList();

        try
        {
            Query query = em.createNativeQuery("select c.* from  Conversion c inner join Valoracion v on c.Valoracion =v.ID  inner join Vehiculo vh on v.Vehiculo = vh.ID where vh.estatus_Row>-1 and GETDATE() >= DATEADD(month,-1, vh.FECHA_PROX_REV) and c.estatus_Row>-1 and vh.FECHA_PROX_REV IS NOT NULL", Conversion.class);
            //query.setParameter("proximaRevision", proximaRevision);
            conversion = query.getResultList();

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
        return conversion;
    }

    @Override
    public Conversion findByVehiculo(Integer vehiculo)
    {
        Conversion conversion = new Conversion();

        try
        {
            Query query = em.createNativeQuery("select c.* from  Conversion c inner join Valoracion v on c.Valoracion =v.ID  inner join Vehiculo vh on v.Vehiculo = vh.ID where vh.estatus_Row>-1 and vh.ID = ?1 and c.estatus_Row>-1 and vh.FECHA_PROX_REV IS NOT NULL", Conversion.class);
            query.setParameter(1, vehiculo);
            conversion = (Conversion) query.getSingleResult();

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
        return conversion;
    }

    @Override
    public HashMap<String, Integer> findCoversionesMes()
    {
        HashMap<String, Integer> conversiones = new HashMap<>();
        String key = "";
        Integer value = 0;
        Object[] response;
        List<Object[]> auxConversiones;
        try
        {

            String nQuery = "select coalesce(datename(month,c.FECHA_CONVERSION),'Desconocido') MES,COUNT(c.ID) CONVERSIONES \n"
                    + "from CONVERSION c where c.ESTATUS_ROW >-1\n"
                    + "group by datename(month,c.FECHA_CONVERSION)\n"
                    + "order by datename(month,c.FECHA_CONVERSION) desc";
            Query query = em.createNativeQuery(nQuery);
            auxConversiones = query.getResultList();
            for (int i =auxConversiones.size()-1; i>=0; i--)
            {
                response=auxConversiones.get(i);
                key=(String)response[0];
                value=(Integer)response[1];
                conversiones.put(key, value);
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
        return conversiones;
    }

    @Override
    public int findKitInstaladosAnio(Estatus estatus)
    {
       int kitInstalados=0;
       try
        {

            String nQuery = "select COUNT (c.ID) KITS_INSTALADOS  from CONVERSION c where c.ESTATUS_ROW>-1 and ESTATUS=? AND YEAR(FECHA_CONVERSION)=YEAR(GETDATE())";
            Query query = em.createNativeQuery(nQuery);
            query.setParameter(1,estatus.getId());
            kitInstalados=((Number)query.getSingleResult()).intValue();
        } catch (NoResultException nre)
        {
            return 0;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return kitInstalados;
    }

}
