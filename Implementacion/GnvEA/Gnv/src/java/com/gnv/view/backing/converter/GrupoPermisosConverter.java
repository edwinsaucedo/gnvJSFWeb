/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;


import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.persistencia.GrupoPermisoFacadeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 *
 * @author Admin
 */
@RequestScoped
@ManagedBean
public class GrupoPermisosConverter implements Converter{

    @EJB
    private GrupoPermisoFacadeLocal grupoPermisosFacadeLocal;
    
    public GrupoPermisosConverter(){
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value){
        if(value == null || value.isEmpty() || value.equals("Seleccione")){
            return null;
        }
        try{
            return grupoPermisosFacadeLocal.find(Integer.valueOf(value));
        }catch (Exception e){
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value){
        if (!(value instanceof GrupoPermiso)){
            return null;
        }
        return String.valueOf(((GrupoPermiso)value).getId());
    }
}
