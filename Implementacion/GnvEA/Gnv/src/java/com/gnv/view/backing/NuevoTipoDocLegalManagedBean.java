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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class NuevoTipoDocLegalManagedBean implements Serializable {

    /**
     * Creates a new instance of NuevoTipoDocLegalManagedBean
     */
    public NuevoTipoDocLegalManagedBean()
    {
    }

    @EJB
    private TipoDocLegalFacadeLocal tipoDocLegalFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private TipoDocLegal tipoDocLegal;
    private String titulo;

    @PostConstruct
    public void init()
    {
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null)
            {
                tipoDocLegal = tipoDocLegalFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_DOCLEGAL_EDITAR_TITULO;
            } else
            {
                tipoDocLegal = new TipoDocLegal();
                titulo = Constantes.TIPO_DOCLEGAL_NUEVO_TITULO;
            }
        } catch (Exception e)
        {
            out.println("E/GVN NuevoTipoDocLegal: " + e.getMessage());
        }
    }

    public void guardar()
    {
        String mensaje = "", actividad = "";
        int id=0;
        try
        {
            tipoDocLegal.setEstatusRow(1);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_DOCUMENTACION_LEGAL);
            if (tipoDocLegal.getId() != null)
            {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCLEGAL_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                tipoDocLegalFacade.edit(tipoDocLegal);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else
            {
                tipoDocLegalFacade.create(tipoDocLegal);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO; 
                if (tipoDocLegal.getId() != null)
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCLEGAL_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=tipoDocLegal.getId();
                    tipoDocLegal = new TipoDocLegal();
                } else
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCLEGAL_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,tipoDocLegal.getId()==null?id:tipoDocLegal.getId(), actividad);

        } catch (Exception e)
        {
            out.println("E/GVN NuevoTipoDocLegal: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public TipoDocLegal getTipoDocLegal()
    {
        return tipoDocLegal;
    }

    public void setTipoDocLegal(TipoDocLegal tipoDocLegal)
    {
        this.tipoDocLegal = tipoDocLegal;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
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
