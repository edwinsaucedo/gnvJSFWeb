/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Eds;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EdsFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
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
public class ListaEstacionManagedBean {

    @EJB
    private EdsFacadeLocal edsFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private List<Eds> edsList;
    private Eds eds;

    @PostConstruct
    public void init()
    {
        try
        {
            edsList = edsFacade.findByEstatusRow();

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
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EDS_LISTA_ELIMINO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            eds = edsFacade.find(Integer.valueOf(id));
            eds.setEstatusRow(-1);
            edsFacade.edit(eds);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_ESTACION_SERVICIO);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            init();

        } catch (Exception e)
        {
            out.println("E/GVN ListaTaller: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public ListaEstacionManagedBean()
    {
    }

    public List<Eds> getEdsList()
    {
        return edsList;
    }

    public void setEdsList(List<Eds> edsList)
    {
        this.edsList = edsList;
    }

    public Eds getEds()
    {
        return eds;
    }

    public void setEds(Eds eds)
    {
        this.eds = eds;
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
