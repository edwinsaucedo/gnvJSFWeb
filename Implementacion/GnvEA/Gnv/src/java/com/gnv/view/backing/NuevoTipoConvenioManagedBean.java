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
public class NuevoTipoConvenioManagedBean implements Serializable {

    /**
     * Creates a new instance of NuevoTipoConvenioManagedBean
     */
    public NuevoTipoConvenioManagedBean() {
    }
    
    @EJB
    private TipoConvenioFacadeLocal tipoConvenioFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private TipoConvenio tipoConvenio;
    private String titulo;
    
    @PostConstruct
    public void init(){
        try
        {
            String id =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if(id!=null)
            {
                tipoConvenio = tipoConvenioFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_CONVENIO_EDITAR_TITULO;
            }else
            {
                tipoConvenio = new TipoConvenio();
                titulo = Constantes.TIPO_CONVENIO_NUEVO_TITULO;
            }
        }catch(Exception e)
        {
            out.println("E/GVN NuevoTipoConvenio: "+e.getMessage());
        }
    }
    
    public void guardar(){
        String mensaje = "",actividad="";
        int id=0;
        try
        {
            tipoConvenio.setEstatusRow(1);
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CONVENIO);
            if(tipoConvenio.getId()!=null)
            {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVENIO_NUEVO_EDITO);
                tipoConvenioFacade.edit(tipoConvenio);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_EXITO,mensaje));
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR; 
                
            }else
            {
                tipoConvenioFacade.create(tipoConvenio);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO; 
                if(tipoConvenio.getId()!=null)
                {
                    mensaje=Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVENIO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=tipoConvenio.getId();
                    tipoConvenio = new TipoConvenio();
                }else{
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVENIO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_ERROR,mensaje));     
                }

            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,tipoConvenio.getId()==null?id:tipoConvenio.getId(), actividad);
            
        }catch(Exception e)
        {
          out.println("E/GVN NuevoTipoConvenio: "+e.getMessage());
          //Registrar en bitacora de exepciones.
          mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public TipoConvenio getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenio tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
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
