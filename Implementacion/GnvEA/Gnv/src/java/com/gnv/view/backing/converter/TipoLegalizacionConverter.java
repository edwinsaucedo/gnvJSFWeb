/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;

import com.gnv.business.ejb.modelo.TipoLegalizacion;
import com.gnv.business.ejb.persistencia.TipoLegalizacionFacadeLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author PC2
 */
@ManagedBean
@RequestScoped
public class TipoLegalizacionConverter implements Converter {

    @EJB
    private TipoLegalizacionFacadeLocal tipoLegalizacionFacade;

    public TipoLegalizacionConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty() || value.equals("Seleccione")) {
            return null;
        }
        try {
            int i = Integer.valueOf(value);
            TipoLegalizacion tl = tipoLegalizacionFacade.find(i);
            return tl;
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Objeto no válido"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof TipoLegalizacion)) {
            return null;
        }
        return String.valueOf(((TipoLegalizacion) value).getId());
    }

}
