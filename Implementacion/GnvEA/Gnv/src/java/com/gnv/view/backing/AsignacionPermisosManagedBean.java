/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Permiso;
import com.gnv.business.ejb.modelo.Rol;
import com.gnv.business.ejb.modelo.RolesPermissions;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.GrupoPermisoFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PermisoFacadeLocal;
import com.gnv.business.ejb.persistencia.RolFacadeLocal;
import com.gnv.business.ejb.persistencia.RolesPermissionsFacadeLocal;
import com.gnv.view.backing.comparator.GrupoPermisosComparator;
import com.gnv.view.backing.comparator.PermisoComparator;
import com.gnv.view.backing.wraper.PermisosWrapper;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class AsignacionPermisosManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    @EJB
    private RolesPermissionsFacadeLocal rolesPermissionFacade;
    @EJB
    private GrupoPermisoFacadeLocal grupoPermisoFacade;
    @EJB
    private RolFacadeLocal rolFacade;
    @EJB
    private PermisoFacadeLocal permisoFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private Rol rolSeleccionado;
    private Permiso permisoSelecionado;
    private Objeto objeto;

    private TreeNode root;

    private TreeNode[] selectedNodes2;
    private List<Rol> listaRoles;

    public AsignacionPermisosManagedBean() {
    }

    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        rolSeleccionado = rolFacade.find(Integer.valueOf(id));
        listaRoles = rolFacade.findAll();
        crearTreeNode();

    }

    public void crearTreeNode() {
        root = new CheckboxTreeNode();
        root.setSelected(true);
        List<GrupoPermiso> listaGrupoPermisoPadre = grupoPermisoFacade.findPadres();
        Collections.sort(listaGrupoPermisoPadre, new GrupoPermisosComparator());

        for (GrupoPermiso gp : listaGrupoPermisoPadre) {
            agregarPermisosWrape(gp, root);
        }

        int j = 0;
    }

    private Integer agregarPermisosWrape(GrupoPermiso gp, TreeNode tnpadre) {
        try {
            PermisosWrapper pw = new PermisosWrapper(gp.getId(), gp.getNombre(), false, null, "GRUPO_PERMISO", gp.getDescripcion());
            TreeNode tn = new CheckboxTreeNode(pw, tnpadre);

            List<Permiso> permisos = permisoFacade.findByGrupoPermisoPadre(gp);
            Integer r1 = 1;
            Integer r2 = 1;

            PermisosWrapper pwAux = (PermisosWrapper) tn.getData();
            if (pwAux.getNombre().equals("Administracion")) {
                pwAux.toString();
            }

            int i = 0;
            Collections.sort(permisos, new PermisoComparator());
            for (Permiso p : permisos) {
                if (rolesPermissionFacade.existePermisoRol(rolSeleccionado.getNombre(), p.getPermiso())) {
                    PermisosWrapper pew = new PermisosWrapper(p.getId(), p.getEtiqueta(), true, true, "PERMISO", p.getDescripcion());
                    TreeNode ten = new CheckboxTreeNode(pew, tn);
                    ten.setSelected(true);
                    //cada ves que agrego un permiso incrementar un contador en 1
                    i++;

                } else {
                    PermisosWrapper pew = new PermisosWrapper(p.getId(), p.getEtiqueta(), true, false, "PERMISO", p.getDescripcion());
                    TreeNode ten = new CheckboxTreeNode(pew, tn);
                    ten.setSelected(false);
                }

            }

            if (i == 0) {
                r1 = 3;
            } else if (permisos.size() == i) {
                r1 = 1;
            } else if (i < permisos.size()) {
                r1 = 2;
            }

            List<GrupoPermiso> hijos = grupoPermisoFacade.findHijos(gp);
            int j = 0;
            int k = 0;
            for (GrupoPermiso g : hijos) {
                int rAux = agregarPermisosWrape(g, tn);
                if (rAux == 1) {
                    j = j + 1;
                }
                if (rAux == 2) {
                    k = k + 1;
                }
            }

            if (j == 0) {
                r2 = 3;
            } else if (hijos.size() == j) {
                r2 = 1;
            } else if (j < hijos.size()) {
                r2 = 2;
            }

            if (k > 0) {
                r2 = 2;
            }
            //GrupoPermiso gp

            //Si tiene hijos y permisos
            if (!permisos.isEmpty() && !hijos.isEmpty()) {
                if (r1 == 1 && r2 == 1) {
                    tn.setSelected(true);
                    return 1;
                } else if (r1 == 1 && r2 == 3) {
                    tn.setPartialSelected(true);
                    return 2;
                } else if (r1 == 3 && r2 == 1) {
                    tn.setPartialSelected(true);
                    return 2;
                } else if (r1 == 3 && r2 == 3) {
                    tn.setSelected(false);
                    return 3;
                } else if (r1 == 2 || r2 == 2) {
                    tn.setPartialSelected(true);
                    return 2;
                }
            }

            //Si solo tiene permisos
            if (!permisos.isEmpty() && hijos.isEmpty()) {
                if (r1 == 1) {
                    tn.setSelected(true);
                    return 1;
                } else if (r1 == 2) {
                    tn.setPartialSelected(true);
                    return 2;
                } else if (r1 == 3) {
                    tn.setSelected(false);
                    return 3;
                }
            }

            //Si solo tiene hijos
            if (permisos.isEmpty() && !hijos.isEmpty()) {
                if (r2 == 1) {
                    tn.setSelected(true);
                    return 1;
                } else if (r2 == 2) {
                    tn.setPartialSelected(true);
                    return 2;
                } else if (r2 == 3) {
                    tn.setSelected(false);
                    return 3;
                }
            }

            //Si no tiene ni hijos ni permisos
            if (permisos.isEmpty() && hijos.isEmpty()) {
                tn.setSelected(false);
                return 3;
            }

            return 3;
        } catch (Exception e) {
            out.println("E/GVN ListaRoles: " + e.getMessage());
        }
        return 3;
    }

    public void recursiva(TreeNode n, StringBuilder tmp) {
        try {
            List<TreeNode> tmo = n.getChildren();
            for (TreeNode nTmp : tmo) {
                PermisosWrapper p = (PermisosWrapper) n.getData();
                tmp.append(p.getNombre()).append("-").append(p.getTipo());
                tmp.append("<br />");
                if (!tmo.isEmpty()) {
                    recursiva(nTmp, tmp);
                }
            }
        } catch (Exception e) {
            out.println("E/GVN ListaRoles: " + e.getMessage());
        }

    }

    public void seleccionarCheckbox(TreeNode[] nodes) {// metodo para la mostra los permisos seleccionados
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_ROLES_PERMISSIONS);
            List<PermisosWrapper> rolesGuardados = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            if (nodes != null && nodes.length > 0) {

                for (TreeNode nodo : nodes) {
                    PermisosWrapper p = (PermisosWrapper) nodo.getData();
                    if (p.getTipo().equals("PERMISO")) {
                        Permiso per = permisoFacade.find(p.getId());

                        RolesPermissions rop = new RolesPermissions();
                        rop.setRoleName(rolSeleccionado.getNombre());
                        rop.setPermission(per.getPermiso());
                        actividad = actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                        rolesPermissionFacade.create(rop);
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, rop.getRolespermissionsid(), actividad);
                        p.setRolPermiso(rop);
                        rolesGuardados.add(p);
                    }
                    builder.append(p.getNombre()).append("-").append(p.getTipo());
                    builder.append("<br />");
                    if (nodo.getChildCount() > 0) {
                        recursiva(nodo, builder);
                    }
                }
            }
            List<RolesPermissions> rolesEliminar = new ArrayList<>();
            List<RolesPermissions> rolesPerfil = rolesPermissionFacade.findByRol(rolSeleccionado.getNombre());

            if (rolesPerfil != null && !rolesPerfil.isEmpty()) {
                for (RolesPermissions rolPerfil : rolesPerfil) {
                    Boolean existeRol = false;
                    Permiso permiso = null;
                    for (PermisosWrapper rolGuardado : rolesGuardados) {
                        if (rolGuardado.getRolPermiso() != null && rolPerfil.equals(rolGuardado.getRolPermiso())) {
                            existeRol = true;
                            break;
                        }
                    }

                    if (!existeRol) {
                        rolesEliminar.add(rolPerfil);
                    }
                }
            }

            if (rolesEliminar != null && !rolesEliminar.isEmpty()) {
                for (RolesPermissions rolEliminar : rolesEliminar) {
                    actividad = actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
                    rolesPermissionFacade.remove(rolEliminar);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, rolEliminar.getRolespermissionsid(), actividad);

                }
            }
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_ASIGNO_PERMISO);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            out.println("E/GVN ListaRoles: " + e.getMessage());
        }
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Permiso getPermisoSelecionado() {
        return permisoSelecionado;
    }

    public void setPermisoSelecionado(Permiso permisoSelecionado) {
        this.permisoSelecionado = permisoSelecionado;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }

    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
