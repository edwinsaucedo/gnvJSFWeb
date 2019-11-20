/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoDocLegal;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocLegalFacadeLocal;
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
public class ListaTipoDocLegalManagedBean implements Serializable {

    @EJB
    private TipoDocLegalFacadeLocal tipoDocLegalFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private List<TipoDocLegal> tipoDocLegalList;
    private TipoDocLegal tipoDocLegal;

    /**
     * Creates a new instance of ListaTipoDocLegalManagedBean
     */
    public ListaTipoDocLegalManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        out.println("init ListaTipoDocLegalManagedBean");
        try
        {
            tipoDocLegalList = tipoDocLegalFacade.findByEstatusRow();
        } catch (Exception e)
        {
            out.println("E/GVN ListaTipoDocLegalManagedBean.init: " + e.getMessage());
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
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCLEGAL_LISTA_ELIMINO);
            tipoDocLegal = tipoDocLegalFacade.find(Integer.valueOf(id));
            tipoDocLegal.setEstatusRow(-1);
            tipoDocLegalFacade.edit(tipoDocLegal);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_DOCUMENTACION_LEGAL);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            init();
        } catch (Exception e)
        {
            out.println("E/GVN ListaTipoDocLegalManagedBean.eliminar: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<TipoDocLegal> getTipoDocLegalList()
    {
        return tipoDocLegalList;
    }

    public void setTipoDocLegalList(List<TipoDocLegal> tipoDocLegalList)
    {
        this.tipoDocLegalList = tipoDocLegalList;
    }

    public TipoDocLegal getTipoDocLegal()
    {
        return tipoDocLegal;
    }

    public void setTipoDocLegal(TipoDocLegal tipoDocLegal)
    {
        this.tipoDocLegal = tipoDocLegal;
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
