/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoConvenio;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoConvenioFacadeLocal;
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
public class ListaTipoConvenioManagedBean implements Serializable {

    /**
     * Creates a new instance of ListaTipoConvenioManagedBean
     */
    public ListaTipoConvenioManagedBean() {
    }
    
   
    
    @EJB
    private TipoConvenioFacadeLocal tipoConvenioFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    private Objeto objeto;
    private List<TipoConvenio> tipoConvenioList;
    private TipoConvenio tipoConvenio;

    
      @PostConstruct
    public void init(){
        out.println("init ListaTipoConvenioManagedBean");
        try
        {
            tipoConvenioList = tipoConvenioFacade.findByEstatusRow();
        }catch(Exception e)
        {
             out.println("E/GVN ListaTipoConvenioManagedBean.init: "+e.getMessage());
        }
    }
      
      public void eliminar(){
        //String ;
        String mensaje,actividad;
        try{
             String id= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
             actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
             mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVENIO_LISTA_ELIMINO);
             tipoConvenio=tipoConvenioFacade.find(Integer.valueOf(id));
             tipoConvenio.setEstatusRow(-1);
             tipoConvenioFacade.edit(tipoConvenio);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
             objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CONVENIO);
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
             init();
        }catch(Exception e){
           out.println("E/GVN ListaTipoConvenioManagedBean.eliminar: "+e.getMessage());
           //Registrar en bitacora de exepciones.
           mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        
        }
    }  
    
      
    public List<TipoConvenio> getTipoConvenioList() {
        return tipoConvenioList;
    }

    public void setTipoConvenioList(List<TipoConvenio> tipoConvenioList) {
        this.tipoConvenioList = tipoConvenioList;
    }

    public TipoConvenio getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenio tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }
}
