/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoLegalizacion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoLegalizacionFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
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
 * @author US5
 */
@ManagedBean
@ViewScoped
public class ListaTipoLegalizacionManagedBean implements Serializable {

    /**
     * Creates a new instance of ListaTipoLegalizacionManagedBean
     */
    public ListaTipoLegalizacionManagedBean()
    {
    }

    @EJB
    private TipoLegalizacionFacadeLocal tipoLegalizacionFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private List<TipoLegalizacion> tipoLegalizacionList;
    private TipoLegalizacion tipoLegalizacion;

    @PostConstruct
    public void init()
    {
        out.println("init ListaTipoLegalizacionManagedBean");
        try
        {
            tipoLegalizacionList = tipoLegalizacionFacade.findByEstatusRow();
        } catch (Exception e)
        {
            out.println("E/GVN ListaTipoLegalizacionManagedBean.init: " + e.getMessage());
        }
    }

    public void eliminar()
    {
        //String ;
        String mensaje,actividad;
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_LEGALIZACION_LISTA_ELIMINO);
            tipoLegalizacion = tipoLegalizacionFacade.find(Integer.valueOf(id));
            tipoLegalizacion.setEstatusRow(-1);
            tipoLegalizacionFacade.edit(tipoLegalizacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_LEGALIZACION);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            init();
        } catch (Exception e)
        {
            out.println("E/GVN ListaTipoLegalizacionManagedBean.eliminar: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<TipoLegalizacion> getTipoLegalizacionList()
    {
        return tipoLegalizacionList;
    }

    public void setTipoLegalizacionList(List<TipoLegalizacion> tipoLegalizacionList)
    {
        this.tipoLegalizacionList = tipoLegalizacionList;
    }

    public TipoLegalizacion getTipoLegalizacion()
    {
        return tipoLegalizacion;
    }

    public void setTipoLegalizacion(TipoLegalizacion tipoLegalizacion)
    {
        this.tipoLegalizacion = tipoLegalizacion;
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
