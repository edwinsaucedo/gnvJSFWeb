/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class ListaConversionManagedBean implements Serializable{

    private List<Conversion> conversionList;
    private Conversion conversion;
    private List<Estatus> estatusList;
    private Estatus estatus;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    @EJB
    private EstatusFacadeLocal estatusFacade;
    
    @EJB
    private ConversionFacadeLocal conversionFacade; 
    
    @EJB
    private ObjetoFacadeLocal objetoFacade;
   
    public ListaConversionManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
       try{
          estatusList=estatusFacade.findByClaseList(Conversion.class.getName());
          estatus=estatusFacade.findByClase(Conversion.class.getName(),Constantes.ESTATUS_EN_PROCESO);
          conversionList=conversionFacade.findByEstatus(estatus);
       }
       catch(Exception e){
           out.println("E/GVN ListaConversiones: "+e.getMessage());
       }
    }
    
    public void eliminar()
    {
        String mensaje,actividad;
        try{
             String id= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
             Objeto objeto=objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION);
             actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
             Estatus estatusDel=estatusFacade.findByClase(Conversion.class.getName(),Constantes.ESTATUS_ELIMINADO);
             //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
             mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_LISTA_ELIMINO);
             conversion=conversionFacade.find(Integer.valueOf(id));
             conversion.setEstatusRow(-1);
             conversion.setEstatus(estatusDel);
             conversionFacade.edit(conversion);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
             conversionList=conversionFacade.findByEstatus(estatus);
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id),actividad);
        }catch(Exception e){
           out.println("E/GVN ListaLinea: "+e.getMessage());
           //Registrar en bitacora de exepciones.
           mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        
        }
    }
    public void onEstatusChange()
    {
       if(estatus!=null){

           conversionList=conversionFacade.findByEstatus(estatus);
       }
       else{
         conversionList=new ArrayList<>();
       }
    }
    
    public List<Conversion> getConversionList()
    {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList)
    {
        this.conversionList = conversionList;
    }

    public Conversion getConversion()
    {
        return conversion;
    }

    public void setConversion(Conversion conversion)
    {
        this.conversion = conversion;
    }

    public List<Estatus> getEstatusList()
    {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList)
    {
        this.estatusList = estatusList;
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
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
