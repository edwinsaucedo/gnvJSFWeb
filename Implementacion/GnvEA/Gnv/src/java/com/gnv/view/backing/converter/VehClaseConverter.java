/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;

import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.persistencia.VehClaseFacadeLocal;
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
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class VehClaseConverter implements Converter{

    /**
     * Creates a new instance of VehClaseConverter
     */
    public VehClaseConverter()
    {
    }
    
    @EJB
    private VehClaseFacadeLocal vClaseFacade;
    
    

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
            VehClase vT = vClaseFacade.find(i);
            return vT;
        } catch (Exception e)
        {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Objeto no válido"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (!(value instanceof VehClase))
        {
            return null;
        }
        return String.valueOf(((VehClase) value).getId());
    }
    
}
