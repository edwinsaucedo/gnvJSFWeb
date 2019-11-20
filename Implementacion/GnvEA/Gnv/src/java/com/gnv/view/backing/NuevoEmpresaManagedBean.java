/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EmpresaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
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
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevoEmpresaManagedBean implements Serializable {

    @EJB
    private EmpresaFacadeLocal empresaFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Empresa empresa;
    private String titulo;
    private Objeto objeto;

    @PostConstruct
    public void init()
    {
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null)
            {
                empresa = empresaFacade.find(Integer.valueOf(id));
                titulo = Constantes.EMPRESA_EDITAR_TITULO;
            } else
            {
                empresa = new Empresa();
                titulo = Constantes.EMPRESA_NUEVO_TITULO;
            }
        } catch (Exception e)
        {
            out.println("E/GVN NuevoEmpresa: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar()
    {
        String mensaje = "", actividad = "";
        int id=0;
        try
        {
            empresa.setEstatusRow(1);
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_EMPRESA);
            if (empresa.getId() != null)
            {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EMPRESA_NUEVO_EDITO);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                empresaFacade.edit(empresa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else
            {
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                empresaFacade.create(empresa);
                if (empresa.getId() != null)
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EMPRESA_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=empresa.getId();
                    empresa = new Empresa();

                } else
                {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EMPRESA_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

                }
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,empresa.getId()==null?id:empresa.getId(), actividad);

        } catch (Exception e)
        {

            out.println("E/GVN NuevoEmpresa: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoEmpresaManagedBean()
    {
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
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
