/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocumentacionFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
 import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author US5
 */
@ManagedBean
@ViewScoped
public class ListaTipoDocumentacionManagedBean implements Serializable{

    @EJB
    private TipoDocumentacionFacadeLocal tipoDocumentacionFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    private Objeto objeto;
    private List<TipoDocumentacion> tipoDocumentacionList;
    private TipoDocumentacion tipoDocumentacion;
    
 
        /**
     * Creates a new instance of ListaTipoDocumentacionManagedBean
     */
    public ListaTipoDocumentacionManagedBean() {
        out.println("constructor ListaTipoDocumentacionManagedBean");
    }
    
    @PostConstruct
    public void init(){
        out.println("init ListaTipoDocumentacionManagedBean");
        try
        {
            tipoDocumentacionList = tipoDocumentacionFacade.findByEstatusRow();
        }catch(Exception e)
        {
             out.println("E/GVN ListaTipoDocumentacionManagedBean.init: "+e.getMessage());
        }
    }
      
      public void eliminar(){
        //String actividad;
        String mensaje,actividad;
        try{
             String id= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
             mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCUMENTACION_LISTA_ELIMINO);
             tipoDocumentacion=tipoDocumentacionFacade.find(Integer.valueOf(id));
             tipoDocumentacion.setEstatusRow(-1);
             tipoDocumentacionFacade.edit(tipoDocumentacion);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
             actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
             objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_DOCUMENTACION);
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
             init();
        }catch(Exception e){
           out.println("E/GVN ListaTipoDocumentacionManagedBean.eliminar: "+e.getMessage());
           //Registrar en bitacora de exepciones.
           mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        
        }
    }  
    

    public List<TipoDocumentacion> getTipoDocumentacionList() {
        return tipoDocumentacionList;
    }

    public void setTipoDocumentacionList(List<TipoDocumentacion> tipoDocumentacionList) {
        this.tipoDocumentacionList = tipoDocumentacionList;
    }

    public TipoDocumentacion getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
    
}
