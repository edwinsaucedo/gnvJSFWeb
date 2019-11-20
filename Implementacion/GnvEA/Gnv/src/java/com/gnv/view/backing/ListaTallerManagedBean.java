/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import static java.lang.System.out;
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
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaTallerManagedBean {

    private List<Taller> tallerList;
    private Taller taller;
    private Objeto objeto;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private TallerFacadeLocal tallerFacade;

    public ListaTallerManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            tallerList = tallerFacade.findByEstatusRow();

        } catch (Exception e)
        {
            out.println("E/GVN ListaTaller: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar()
    {
        String mensaje,actividad;
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_TALLER);
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TALLER_LISTA_ELIMINO);
            taller = tallerFacade.find(Integer.valueOf(id));
            taller.setEstatusRow(-1);
            tallerFacade.edit(taller);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
            init();

        } catch (Exception e)
        {
            out.println("E/GVN ListaTaller: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public List<Taller> getTallerList()
    {
        return tallerList;
    }

    public void setTallerList(List<Taller> tallerList)
    {
        this.tallerList = tallerList;
    }

    public Taller getTaller()
    {
        return taller;
    }

    public void setTaller(Taller taller)
    {
        this.taller = taller;
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
