/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * 
 *
 *
 * Author Administrador
 */
@ManagedBean
@RequestScoped
public class EstatusConverter implements Converter {

    @EJB
    EstatusFacadeLocal estatusFacade;

    public EstatusConverter()
    {
    }

    @Override

    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if (value == null || value.isEmpty() || value.equals("Seleccione"))
        {
            return null;
        }
        try
        {
            int i = Integer.valueOf(value);

            Estatus e = estatusFacade.find(i);
            return e;

        } catch (Exception e)
        {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Objeto no válido"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (!(value instanceof Estatus))
        {
            return null;
        }
        return String.valueOf(((Estatus) value).getId());
    }

}
