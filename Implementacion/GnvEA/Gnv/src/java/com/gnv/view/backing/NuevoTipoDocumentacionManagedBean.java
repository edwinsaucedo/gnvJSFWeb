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
public class NuevoTipoDocumentacionManagedBean implements Serializable {

    /**
     * Creates a new instance of NewDocumentationTypeManagedBean
     */
    public NuevoTipoDocumentacionManagedBean()
    {
        out.println("E/GVN NuevoTipoDocumentacion Constructor");
    }

    @EJB
    private TipoDocumentacionFacadeLocal tipoDocumentacionFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private TipoDocumentacion tipoDocumentacion;
    private String titulo;
    

    @PostConstruct
    public void init()
    {
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null)
            {
                tipoDocumentacion = tipoDocumentacionFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_DOCUMENTACION_EDITAR_TITULO;
            } else
            {
                tipoDocumentacion = new TipoDocumentacion();
                titulo = Constantes.TIPO_DOCUMENTACION_NUEVO_TITULO;
            }
        } catch (Exception e)
        {
            out.println("E/GVN NuevoTipoDocumentacion: " + e.getMessage());
        }
    }

    public void guardar()
    {
        String mensaje = "",actividad="";
        int id=0;
        try
        {
            tipoDocumentacion.setEstatusRow(1);
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_TIPO_DOCUMENTACION);
            if (tipoDocumentacion.getId() != null)
            {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCUMENTACION_NUEVO_EDITO);
                tipoDocumentacionFacade.edit(tipoDocumentacion);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;                
            } else
            {
                tipoDocumentacionFacade.create(tipoDocumentacion);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;   
                if (tipoDocumentacion.getId() != null)
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCUMENTACION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=tipoDocumentacion.getId();
                    tipoDocumentacion = new TipoDocumentacion();
                } else
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCUMENTACION_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,tipoDocumentacion.getId()==null?id:tipoDocumentacion.getId(), actividad);

        } catch (Exception e)
        {
            out.println("E/GVN NuevoTipoDocumentacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public TipoDocumentacion getTipoDocumentacion()
    {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion)
    {
        this.tipoDocumentacion = tipoDocumentacion;
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
