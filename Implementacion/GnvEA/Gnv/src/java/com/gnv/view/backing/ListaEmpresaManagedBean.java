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
public class ListaEmpresaManagedBean implements Serializable {

    private List<Empresa> empresaList;
    private Empresa empresa;
    private Objeto objeto;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private EmpresaFacadeLocal empresaFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init()
    {
        try
        {
            empresaList = empresaFacade.findByEstatusRow();
        } catch (Exception e)
        {
            out.println("E/GVN ListaEmpresa: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public ListaEmpresaManagedBean()
    {
    }

    public void eliminar()
    {
        String mensaje,actividad;
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_del");
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_EMPRESA);
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EMPRESA_LISTA_ELIMINO);
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            empresa = empresaFacade.find(Integer.valueOf(id));
            empresa.setEstatusRow(-1);
            empresaFacade.edit(empresa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
            init();
        } catch (Exception e)
        {
            out.println("E/GVN ListaEmpresa: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public List<Empresa> getEmpresaList()
    {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList)
    {
        this.empresaList = empresaList;
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
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
