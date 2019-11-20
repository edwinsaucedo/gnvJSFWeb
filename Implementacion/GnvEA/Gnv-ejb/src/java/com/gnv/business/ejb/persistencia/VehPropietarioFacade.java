/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.Vehiculo;
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
public class VehPropietarioFacade extends AbstractFacade<VehPropietario> implements VehPropietarioFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehPropietarioFacade() {
        super(VehPropietario.class);
    }

    @Override
    public VehPropietario findByPropietarioVehiculo(Propietario propietario, Vehiculo vehiculo) {
        VehPropietario vehPropietario = new VehPropietario();
        try {
            Query query = em.createQuery("SELECT vp FROM VehPropietario vp where vp.propietario=:propietario and vp.vehiculo=:vehiculo and vp.estatusRow>0", VehPropietario.class);
            query.setParameter("propietario", propietario);
            query.setParameter("vehiculo", vehiculo);
            vehPropietario = (VehPropietario) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda propietario del vehiculo, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda  propietario del vehiculo facade local: " + e.getMessage());
        }

        return vehPropietario;
    }

    @Override
    public List<VehPropietario> findByVehiculo(Vehiculo vehiculo) {
        List<VehPropietario> vhPropietario = new ArrayList();
        try {
            Query query = em.createQuery("SELECT vp FROM VehPropietario vp inner join vp.propietario p where vp.vehiculo=:vehiculo and vp.estatusRow>0", VehPropietario.class);
            query.setParameter("vehiculo", vehiculo);
            vhPropietario = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda propietario, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda  propietario, facade local: " + e.getMessage());
        }

        return vhPropietario;
    }

    @Override
    public List<VehPropietario> findByPropietario(Integer vehiculo, String nombreCompleto) {
        List<VehPropietario> vhPropietario = new ArrayList();
        String nQuery = "SELECT vp.* FROM Veh_Propietario vp inner join Propietario p on vp.propietario = p.ID  where vp.estatus_Row>0 and vp.vehiculo = ? and  p.nombre+p.apellido  like ?";
        try {
            Query query = em.createNativeQuery(nQuery, VehPropietario.class);
            query.setParameter(1, vehiculo);
            query.setParameter(2, "%" + nombreCompleto + "%");
            //query.setParameter(3, apellido + "%");
            vhPropietario = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda propietario, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda  propietario, facade local: " + e.getMessage());
        }

        return vhPropietario;
    }



    @Override
    public VehPropietario findByPropietarioVehiculo(Integer vehiculo, String nombre,String apellido) {
        VehPropietario vhPropietario = new VehPropietario();
        String nQuery = "SELECT vp.* FROM Veh_Propietario vp inner join Propietario p on vp.propietario = p.ID  where vp.estatus_Row>0 and vp.vehiculo = ? and  p.Nombre = ? and p.Apellido = ? ";
        try {
            Query query = em.createNativeQuery(nQuery, VehPropietario.class);
            query.setParameter(1, vehiculo);
            query.setParameter(2, nombre);
            query.setParameter(3, apellido);
            //query.setParameter(3, apellido + "%");
            vhPropietario = (VehPropietario) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda propietario, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda  propietario, facade local: " + e.getMessage());
        }

        return vhPropietario;
    }

}
