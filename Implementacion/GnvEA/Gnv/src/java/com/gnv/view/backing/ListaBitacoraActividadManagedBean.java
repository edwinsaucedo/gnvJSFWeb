/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.BitacoraActividades;
import com.gnv.business.ejb.persistencia.BitacoraActividadesFacadeLocal;
import com.gnv.views.utils.Constantes;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class ListaBitacoraActividadManagedBean implements Serializable {

    /**
     * Creates a new instance of ListaBitacoraActividadManagedBean
     */
    @EJB
    private BitacoraActividadesFacadeLocal bitacoraActividadesFacade;

    private Date fechaInicio, fechaFin;

    private List<BitacoraActividades> bitacoraActividadList;
   
    private  final SimpleDateFormat  dateFormat = new SimpleDateFormat("dd/MM/yy");

    public ListaBitacoraActividadManagedBean()
    {

    }

    @PostConstruct
    public void init()
    {
        try
        {
            fechaInicio = new Date();
            fechaFin = new Date();
            bitacoraActividadList = bitacoraActividadesFacade.findByFecha(dateFormat.format(fechaInicio), dateFormat.format(fechaFin));
        } catch (Exception e)
        {
            out.println("E/GVN ListaBitacoraActividades: " + e.getMessage());
        }
    }

    public void onDateSelect()
    {
        if (fechaFin.compareTo(fechaInicio) < 0)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "La fecha final no puede ser menor a la inicial."));
            return;
        }
        bitacoraActividadList = bitacoraActividadesFacade.findByFecha(dateFormat.format(fechaInicio), dateFormat.format(fechaFin));

    }

    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public List<BitacoraActividades> getBitacoraActividadList()
    {
        return bitacoraActividadList;
    }

    public void setBitacoraActividadList(List<BitacoraActividades> bitacoraActividadList)
    {
        this.bitacoraActividadList = bitacoraActividadList;
    }

}
