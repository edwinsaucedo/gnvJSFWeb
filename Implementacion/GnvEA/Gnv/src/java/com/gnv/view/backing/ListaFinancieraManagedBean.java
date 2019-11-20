/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CompaniaFacadeLocal;
import com.gnv.business.ejb.persistencia.FinancieraFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaFinancieraManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<Financiera> financieraList;
    private Financiera financiera;
    private Objeto objeto;
  
    
    @EJB
    private FinancieraFacadeLocal financieraFacade;
    
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
 

    public ListaFinancieraManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        try{
            financieraList = financieraFacade.findByEstatusRow();
        }catch(Exception e){
              out.println("E/GVN ListaFinanciera: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }
    public void eliminar(){
          String mensaje,actividad;
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_del");
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_FINANCIERA);
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FINANCIERA_LISTA_ELIMINO);
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            financiera = financieraFacade.find(Integer.valueOf(id));
            financiera.setEstatusRow(-1);
            financieraFacade.edit(financiera);
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,financiera.getId(), actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
            init();
        } catch (Exception e)
        {
            out.println("E/GVN ListaFinanciera: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<Financiera> getFinancieraList() {
        return financieraList;
    }

    public void setFinancieraList(List<Financiera> financieraList) {
        this.financieraList = financieraList;
    }

    public Financiera getFinanciera() {
        return financiera;
    }

    public void setFinanciera(Financiera financiera) {
        this.financiera = financiera;
    }

}
