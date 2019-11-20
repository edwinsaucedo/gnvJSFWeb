/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;


import com.gnv.business.ejb.modelo.Pais;
import com.gnv.business.ejb.persistencia.PaisFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Administrador
 */
@Named(value = "paisConverter")
@Dependent
public class PaisConverter implements Converter{

    @EJB
    private PaisFacadeLocal paisFacade;
    
    public PaisConverter()
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
            Pais p = paisFacade.find(i);
            return p;
        } catch (Exception e)
        {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Objeto no válido"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (!(value instanceof Pais))
        {
            return null;
        }
        return String.valueOf(((Pais) value).getId());
    }
}
