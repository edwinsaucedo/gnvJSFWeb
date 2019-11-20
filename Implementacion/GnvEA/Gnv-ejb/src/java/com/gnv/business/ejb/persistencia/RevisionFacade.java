/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Revision;
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
public class RevisionFacade extends AbstractFacade<Revision> implements RevisionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    public List<Revision> findByRevision()
    {
        List<Revision> revision = new ArrayList();
        String nQuery = "select r.* from  Revision r inner join Vehiculo vh on r.Vehiculo = vh.ID where vh.estatus_Row>0 and GETDATE() <= DATEADD(month,-1, vh.FECHA_PROX_REV) and r.estatus_Row>0";

        try
        {
            Query query = em.createNativeQuery(nQuery, Revision.class);
            revision = query.getResultList();
        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda revision, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda revision, facade local: " + e.getMessage());
        }
        return revision;
    }

    @Override
    public Revision findByVehiculo(Vehiculo vehiculo)
    {
        Revision revision = new Revision();

        try
        {
            Query query = em.createQuery("Select r FROM Revision r where r.estatusRow>-1 and r.vehiculo = :vehiculo", Revision.class);
            query.setParameter("vehiculo", vehiculo);
            revision = (Revision) query.getSingleResult();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda revision, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda revision, facade local: " + e.getMessage());
        }
        return revision;
    }

    public RevisionFacade()
    {
        super(Revision.class);
    }

    @Override
    public HashMap<String, Integer> findRevisionesMes()
    {
        HashMap<String, Integer> revisiones = new HashMap<>();
        String key = "";
        Integer value = 0;
        Object[] response;
        List<Object[]> auxRevisiones;
        try
        {

            String nQuery = "select coalesce(datename(month,r.FECHA_REVISION),'Desconocido') MES,COUNT(r.ID) REVISIONES \n"
                    + "from REVISION r where r.ESTATUS_ROW >-1\n"
                    + "group by datename(month,r.FECHA_REVISION)\n"
                    + "order by datename(month,r.FECHA_REVISION) desc";
            Query query = em.createNativeQuery(nQuery);
            auxRevisiones = query.getResultList();
            for (int i = auxRevisiones.size() - 1; i >= 0; i--)
            {
                response = auxRevisiones.get(i);
                key = (String) response[0];
                value = (Integer) response[1];
                revisiones.put(key, value);
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
        return revisiones;
    }

}
