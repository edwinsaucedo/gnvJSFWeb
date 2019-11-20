/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.UsuarioFacadeLocal;
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
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class ListaUsuarioManagedBean implements Serializable{

    /**
     * Creates a new instance of ListaUsuarioManagedBean
     */    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    @EJB
    private EstatusFacadeLocal estatusFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
    private Estatus estatus,estatusActivo,estatusInactivo;
    
    private List<Estatus> estatusList;
    private List<Usuario> usuarioList;

    public ListaUsuarioManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        try
        {
            estatusList = estatusFacade.findByClaseList(Usuario.class.getName());
            estatusActivo=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_ACTIVO);
            estatusInactivo=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_INACTIVO);
            estatus=new Estatus();
            for (int i = estatusList.size()-1; i>=0; i--)
            {
                if(estatusList.get(i).getNombre().equals(Constantes.ESTATUS_ELIMINADO)){
                      estatusList.remove(i);
                      break;
                }
            }
            usuarioList = usuarioFacade.findByEstatus(estatusActivo, estatusInactivo);
        } catch (Exception e)
        {
            out.println("E/GVN ListaUsuario: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }
    public void estatusOnChange()
    {
      try
        {
           if(null==estatus){
               usuarioList = usuarioFacade.findByEstatus(estatusActivo, estatusInactivo);
           }
           else{
               usuarioList = usuarioFacade.findByEstatus(estatus,new Estatus());
           }
        } catch (Exception e)
        {
            out.println("E/GVN ListaTipoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }
    public void eliminar()
    {
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Usuario usuario=usuarioFacade.find(Integer.valueOf(id));
            Estatus estatus3=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_ELIMINADO);
            usuario.setEstatus(estatus3);
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_USUARIO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_USUARIO_LISTA_ELIMINO);
            usuarioFacade.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            init();
        } catch (Exception e) {
            out.println("E/GVN ListaUsuarios: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
        
    }
    
    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }
    
    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    public List<Usuario> getUsuarioList()
    {
        return usuarioList;
    }
    
    public void setUsuarioList(List<Usuario> usuarioList)
    {
        this.usuarioList = usuarioList;
    }
    
    public Estatus getEstatus()
    {
        return estatus;
    }
    
    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }
    
    public List<Estatus> getEstatusList()
    {
        return estatusList;
    }
    
    public void setEstatusList(List<Estatus> estatusList)
    {
        this.estatusList = estatusList;
    }
    
}
