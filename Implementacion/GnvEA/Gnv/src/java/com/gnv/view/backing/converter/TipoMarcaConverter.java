/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;

import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
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
 * @author ReneVl
 */
@ManagedBean
@RequestScoped
public class TipoMarcaConverter implements Converter {

    /**
     * Creates a new instance of TipoMarcaConverter
     */
    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    public TipoMarcaConverter()
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
            TipoMarca tm = tipoMarcaFacade.find(i);
            return tm;
        } catch (Exception e)
        {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Objeto no válido"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (!(value instanceof TipoMarca))
        {
            return null;
        }
        return String.valueOf(((TipoMarca) value).getId());
    }

}
