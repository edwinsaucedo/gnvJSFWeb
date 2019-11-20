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
public class NuevoTipoLegalizacionManagedBean implements Serializable {

    /**
     * Creates a new instance of NuevoTipoLegalizacionManagedBean
     */
    public NuevoTipoLegalizacionManagedBean() {
    }
    @EJB
    private TipoLegalizacionFacadeLocal tipoLegalizacionFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private TipoLegalizacion tipoLegalizacion;
    private String titulo;

    @PostConstruct
    public void init(){
        try
        {
            String id =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if(id!=null)
            {
                tipoLegalizacion = tipoLegalizacionFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_LEGALIZACION_EDITAR_TITULO;
            }else
            {
                tipoLegalizacion = new TipoLegalizacion();
                titulo = Constantes.TIPO_LEGALIZACION_NUEVO_TITULO;
            }
        }catch(Exception e)
        {
            out.println("E/GVN NuevoTipoLegalizacion: "+e.getMessage());
        }
    }
    
    public void guardar(){
        String mensaje = "", actividad = "";
        int id=0;
        try
        {
            tipoLegalizacion.setEstatusRow(1);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_LEGALIZACION);
            if(tipoLegalizacion.getId()!=null)
            {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_LEGALIZACION_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                tipoLegalizacionFacade.edit(tipoLegalizacion);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_EXITO,mensaje));
                
            }else
            {
                tipoLegalizacionFacade.create(tipoLegalizacion);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                if(tipoLegalizacion.getId()!=null)
                {
                    mensaje=Propiedades.getMensaje(Constantes.CATALOGO_TIPO_LEGALIZACION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=tipoLegalizacion.getId();
                    tipoLegalizacion = new TipoLegalizacion();
                }else{
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_DOCLEGAL_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_ERROR,mensaje));     
                }

            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,tipoLegalizacion.getId()==null?id:tipoLegalizacion.getId(), actividad);
            
        }catch(Exception e)
        {
          out.println("E/GVN NuevoTipoLegalizacion: "+e.getMessage());
          //Registrar en bitacora de exepciones.
          mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }    
    
    public TipoLegalizacion getTipoLegalizacion() {
        return tipoLegalizacion;
    }

    public void setTipoLegalizacion(TipoLegalizacion tipoLegalizacion) {
        this.tipoLegalizacion = tipoLegalizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
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
