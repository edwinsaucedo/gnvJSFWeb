/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.FabricanteFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
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
public class ListaFabricanteManagedBean {

    private List<Fabricante> listaFabricante;
    private Fabricante fabricante;
    private Estatus estatusEliminar;
    private Estatus estatusActivos;
    private Objeto objeto;

    @EJB
    private FabricanteFacadeLocal fabricanteFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    public ListaFabricanteManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            estatusEliminar = estatusFacade.findByClase(Fabricante.class.getName(), Constantes.ESTATUS_ELIMINADO);
            estatusActivos = estatusFacade.findByClase(Fabricante.class.getName(), Constantes.ESTATUS_ACTIVO);
            listaFabricante = fabricanteFacade.findByEstatusRow(estatusActivos);

        } catch (Exception e)
        {
            out.println("E/GVN ListaFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar()
    {
        String mensaje, actividad;
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_del");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_LISTA_ELIMINO);
            fabricante = fabricanteFacade.find(Integer.valueOf(id));
            fabricante.setEstatus(estatusEliminar);
            fabricanteFacade.edit(fabricante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_FABRICANTE_INFO_A);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);

            init();

        } catch (Exception e)
        {
            out.println("E/GVN ListaFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public List<Fabricante> getListaFabricante()
    {
        return listaFabricante;
    }

    public void setListaFabricante(List<Fabricante> listaFabricante)
    {
        this.listaFabricante = listaFabricante;
    }

    public Fabricante getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante)
    {
        this.fabricante = fabricante;
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
