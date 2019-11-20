/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Permiso;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.GrupoPermisoFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PermisoFacadeLocal;
import com.gnv.business.utils.StringUtil;
import com.gnv.view.backing.comparator.GrupoPermisosComparator;
import com.gnv.view.backing.comparator.PermisoComparator;
import com.gnv.view.backing.wraper.GrupoPermisoWraper;
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
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.*;

/**
 *
 * @author Administrador
 */
@ViewScoped
@ManagedBean
public class GrupoPermisosManagedBean implements Serializable {

    private TreeNode rootNode;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuario;

    @EJB
    private PermisoFacadeLocal permisoFacade;
    @EJB
    private GrupoPermisoFacadeLocal grupoPermisosFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private Objeto objeto;

    private List<GrupoPermiso> listaGrupoPermiso;
    private List<GrupoPermiso> listaGrupoPermisoPadres;
    private GrupoPermiso grupoPermiso, grupoPermisosPadre;
    private Permiso permiso;
    private String dialogGrupoPermisosHeader,dialogPermisosHeader;

    public GrupoPermisosManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {

        rootNode = new DefaultTreeNode();
        try
        {
            grupoPermiso = new GrupoPermiso();
            List<GrupoPermiso> listaGrupoPermisoHijos = new ArrayList<>();
            List<Permiso> listaPermisoHijos = new ArrayList<>();
            listaGrupoPermiso = grupoPermisosFacade.findAll();
            listaGrupoPermisoPadres = grupoPermisosFacade.findPadres();
            for (GrupoPermiso g : listaGrupoPermisoPadres)
            {
                TreeNode treeNode = new DefaultTreeNode("grupo", new GrupoPermisoWraper(g, new Permiso()), rootNode);
                treeNode.setExpanded(g.getExpandido());
                listaGrupoPermisoHijos = grupoPermisosFacade.findHijos(g);
                listaPermisoHijos=permisoFacade.findByGrupoPermisoPadre(g);
                if (listaPermisoHijos != null && !listaPermisoHijos.isEmpty())
                {
                    agregarHijosPermisos(listaPermisoHijos,g, treeNode);
                }
                if (listaGrupoPermisoHijos != null && !listaGrupoPermisoHijos.isEmpty())
                {
                    agregarHijosGrupoPermiso(listaGrupoPermisoHijos, treeNode);
                }
            }

        } catch (Exception e)
        {

            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void seleccionarGrupoPermisoNuevo()
    {
        try
        {
            dialogGrupoPermisosHeader="Nuevo grupo de permisos.";
            String idPadre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGrupoPermiso");
            if (idPadre != null && !idPadre.equals("null"))
            {
                grupoPermisosPadre = grupoPermisosFacade.find(Integer.valueOf(idPadre));
            }
            listaGrupoPermisoPadres = grupoPermisosFacade.findAll();
            grupoPermiso = new GrupoPermiso();
            grupoPermiso.setGrupoPadre(grupoPermisosPadre);
            permiso = new Permiso();
            permiso.setGrupoPermiso(grupoPermiso);
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }
    
     public void seleccionarGrupoPermiso() 
    {
        try 
        {
            dialogGrupoPermisosHeader="Editar grupo de permisos.";
            String idGrupoPermiso = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGrupoPermiso");
            if (idGrupoPermiso != null && !idGrupoPermiso.equals("null"))
            {
                grupoPermiso = grupoPermisosFacade.find(Integer.valueOf(idGrupoPermiso));
            }
            listaGrupoPermisoPadres = grupoPermisosFacade.findAll();
        } catch (Exception e) 
        {
           out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void seleccionarPermisoNuevo()
    {
        try
        {
            dialogPermisosHeader="Nuevo permiso.";
            String idPadre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGrupoPermiso");
            if (idPadre != null && !idPadre.equals("null"))
            {
                grupoPermisosPadre = grupoPermisosFacade.find(Integer.valueOf(idPadre));
            }

            String srt_permiso = "";
            boolean tiene_padre = true;
            GrupoPermiso g = new GrupoPermiso();
            g = grupoPermisosPadre;

            while (tiene_padre)
            {
                srt_permiso = StringUtil.reemplazarCaracteresRaros(g.getNombre()).replace(" ", "_").toLowerCase().trim() + ":" + srt_permiso;
                if (g.getGrupoPadre() != null)
                {
                    g = (GrupoPermiso) g.getGrupoPadre();
                } else
                {
                    tiene_padre = false;
                }
            }
            permiso = new Permiso();
            permiso.setPermiso(srt_permiso);
            permiso.setGrupoPermiso(grupoPermisosPadre);
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }
    
     public void seleccionarPermiso() 
    {
        try 
        {
            dialogPermisosHeader="Editar permiso.";
            String idPermiso = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPermiso");
            if (idPermiso != null && !idPermiso.equals("null")) 
            {
                permiso = permisoFacade.find(Integer.valueOf(idPermiso));
            }
        } catch (Exception e) 
        {
               out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }


    public void guardarGrupoPermiso()
    {
        String mensaje = "", actividad = "";
        try
        {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_GRUPO_PERMISO);
            if (grupoPermiso.getId() != null)
            {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                grupoPermisosFacade.edit(grupoPermiso);
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_GRUPO_NUEVO_EDITO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            } else
            {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                grupoPermiso.setExpandido(Boolean.FALSE);
                grupoPermisosFacade.create(grupoPermiso);
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_GRUPO_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            }
            BitacoraActividadesFactory.ckeckIn(usuario, objeto,grupoPermiso.getId(), actividad);
            init();
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void guardarPermiso()
    {
        String mensaje = "", actividad = "";
        try
        {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_PERMISO);
            if (permiso.getId() == null)
            {
                permisoFacade.create(permiso);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            } else
            {
                permisoFacade.edit(permiso);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_NUEVO_EDITO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            }
             BitacoraActividadesFactory.ckeckIn(usuario, objeto,permiso.getId(), actividad);
             init();
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }
    
     public void eliminarGrupoPermiso() 
    {
        String mensaje = "", actividad = "";
        try 
        {
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_GRUPO_PERMISO);
            grupoPermisosFacade.remove(grupoPermiso);
            init();
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_GRUPO_NUEVO_ELIMINO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuario, objeto,grupoPermiso.getId(), actividad);

        } catch (Exception e) 
        {
             out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void eliminarPermiso() 
    {
        String mensaje = "", actividad = "";
        try 
        {
            permisoFacade.remove(permiso);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_PERMISO);
            init();
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_PERMISO_NUEVO_ELIMINO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuario, objeto,permiso.getId(), actividad);
        } catch (Exception e) {
             out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    private void agregarHijosPermisos(List<Permiso> listaHijos,GrupoPermiso grupoP, TreeNode treeNode)
    {
        try
        {
            Collections.sort(listaHijos, new PermisoComparator());
            for (Permiso p : listaHijos)
            {
                TreeNode treeN = new DefaultTreeNode("permiso", new GrupoPermisoWraper(null, p), treeNode);
                treeNode.setExpanded(grupoP.getExpandido());

            }
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    private void agregarHijosGrupoPermiso(List<GrupoPermiso> listaHijos, TreeNode treeNode)
    {
        try
        {
            Collections.sort(listaHijos, new GrupoPermisosComparator());
            for (GrupoPermiso g : listaHijos)
            {
                TreeNode treeN = new DefaultTreeNode("grupo", new GrupoPermisoWraper(g, null), treeNode);
                treeN.setExpanded(g.getExpandido());
                List<GrupoPermiso> listaSubhijos = grupoPermisosFacade.findHijos(g);
                List<Permiso> listaPermisoHijos = permisoFacade.findByGrupoPermisoPadre(g);
                if (listaSubhijos != null && !listaSubhijos.isEmpty())
                {
                    agregarHijosGrupoPermiso(listaSubhijos, treeN);
                }
                if (listaPermisoHijos != null && !listaPermisoHijos.isEmpty())
                {
                    agregarHijosPermisos(listaPermisoHijos,g, treeN);
                }
            }
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void onExpand(NodeExpandEvent nee)
    {
        try
        {
            Object o = nee.getTreeNode().getData();

            GrupoPermisoWraper gpw = (GrupoPermisoWraper) o;
            if (gpw.getGp() != null)
            {
                gpw.getGp().setExpandido(true);
                grupoPermisosFacade.edit(gpw.getGp());
            }
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public void onCollapse(NodeCollapseEvent nee)
    {
        try
        {
            Object o = nee.getTreeNode().getData();

            GrupoPermisoWraper gpw = (GrupoPermisoWraper) o;
            if (gpw.getGp() != null)
            {
                gpw.getGp().setExpandido(false);
                grupoPermisosFacade.edit(gpw.getGp());
            }
        } catch (Exception e)
        {
            out.println("E/GNV: ERROR LISTA PERMISOS EN: " + e.getMessage());
        }
    }

    public TreeNode getRootNode()
    {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode)
    {
        this.rootNode = rootNode;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public List<GrupoPermiso> getListaGrupoPermiso()
    {
        return listaGrupoPermiso;
    }

    public void setListaGrupoPermiso(List<GrupoPermiso> listaGrupoPermiso)
    {
        this.listaGrupoPermiso = listaGrupoPermiso;
    }

    public GrupoPermiso getGrupoPermiso()
    {
        return grupoPermiso;
    }

    public void setGrupoPermiso(GrupoPermiso grupoPermiso)
    {
        this.grupoPermiso = grupoPermiso;
    }

    public GrupoPermiso getGrupoPermisosPadre()
    {
        return grupoPermisosPadre;
    }

    public void setGrupoPermisosPadre(GrupoPermiso grupoPermisosPadre)
    {
        this.grupoPermisosPadre = grupoPermisosPadre;
    }

    public Permiso getPermiso()
    {
        return permiso;
    }

    public void setPermiso(Permiso permiso)
    {
        this.permiso = permiso;
    }

    public String getDialogGrupoPermisosHeader()
    {
        return dialogGrupoPermisosHeader;
    }

    public void setDialogGrupoPermisosHeader(String dialogGrupoPermisosHeader)
    {
        this.dialogGrupoPermisosHeader = dialogGrupoPermisosHeader;
    }

    public String getDialogPermisosHeader()
    {
        return dialogPermisosHeader;
    }

    public void setDialogPermisosHeader(String dialogPermisosHeader)
    {
        this.dialogPermisosHeader = dialogPermisosHeader;
    }
   
}
