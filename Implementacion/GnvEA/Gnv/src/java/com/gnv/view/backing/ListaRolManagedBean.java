/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Rol;
import com.gnv.business.ejb.modelo.RolesPermissions;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.RolFacadeLocal;
import com.gnv.business.ejb.persistencia.RolesPermissionsFacadeLocal;
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
public class ListaRolManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private RolFacadeLocal rolFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    @EJB
    private RolesPermissionsFacadeLocal rolesPermissionFacade;

    private List<Rol> rolList;
    private Rol rol;

    private List<RolesPermissions> rolesPermissionList;

    public ListaRolManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            rolList = rolFacade.findAll();
        } catch (Exception e) {
            out.println("E/GVN ListaRoles: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            String actividad;
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_ROL);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                rol = rolFacade.find(Integer.valueOf(id));
                rolesPermissionList = rolesPermissionFacade.findByRol(rol.getNombre());
                if (!(rolesPermissionList.isEmpty()) && rolesPermissionList != null && rolesPermissionList.size() > 0) {
                    String mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_ERROR_ELIMINO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.MENSAJE_ERROR, mensaje));
                } else {
                    rolFacade.eliminaPermisoRol(rol.getNombre());
                           BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id),actividad);
                    init();
                    String mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_ELIMINO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                }
            }
        } catch (Exception e) {
            out.println("E/GVN ListaRoles: " + e.getMessage());
        }
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
