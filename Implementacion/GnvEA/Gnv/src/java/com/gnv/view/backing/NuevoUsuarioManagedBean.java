/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Rol;
import com.gnv.business.ejb.modelo.UserRoles;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CompaniaFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.RolFacadeLocal;
import com.gnv.business.ejb.persistencia.UserRolesFacadeLocal;
import com.gnv.business.ejb.persistencia.UsuarioFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class NuevoUsuarioManagedBean implements Serializable{

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private RolFacadeLocal rolFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;
    @EJB
    private UserRolesFacadeLocal usuarioRolFacade;
    @EJB
    private CompaniaFacadeLocal companiaFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private String titulo;
    private boolean editable;
    
    private Usuario usuario;
    private Rol rol;
    private Estatus estatus;
    private UserRoles usuarioRol;
    private Compania compania;
    private Objeto objeto;
    

    private List<Rol> rolList;
    private List<Estatus> estatusList;
    private List<Compania>companiaList;

    public NuevoUsuarioManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            rolList = rolFacade.findAll();
            estatusList = estatusFacade.findByClaseList(Usuario.class.getName());
            companiaList= companiaFacade.findAll();
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_USUARIO);
            for (int i = estatusList.size()-1; i>=0; i--)
            {
                if(estatusList.get(i).getNombre().equals(Constantes.ESTATUS_ELIMINADO)){
                      estatusList.remove(i);
                      break;
                }
            }
            if (id != null)
            {
                usuario = usuarioFacade.find(Integer.valueOf(id));
                titulo = Constantes.USUARIO_EDITAR_TITULO;
                rol=usuario.getRol();
                estatus=usuario.getEstatus();
                compania=usuario.getCompania();
                editable=false;
            } else
            {
                usuario = new Usuario();
                rol=new Rol();
                estatus=new Estatus();
                compania=new Compania();
                titulo = Constantes.USUARIO_NUEVO_TITULO;
                editable=true;
            }
        }
        catch(Exception e)
        {
           out.println("E/GVN NuevoUsuario: " + e.getMessage());
        }
    }

    public void guardar()
    {
        String hashPassword="",claveAcceso="",mensaje = "", actividad = "";
        int idUsuario=0;
        usuarioRol=new UserRoles();
        usuario.setRol(rol);
        usuario.setEstatus(estatus);
        usuario.setCompania(compania);
        if(usuario.getId()!=null)
        {
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
            mensaje=Propiedades.getMensaje(Constantes.CATALOGO_USUARIO_NUEVO_EDITO);
            usuario.setFechaModificacion(new Date());
            usuarioFacade.edit(usuario);
            usuarioRol = usuarioRolFacade.findByUserName(usuario.getClaveAcceso());
            if(usuarioRol!=null){
                usuarioRol.setRoleName(usuario.getRol().getNombre());
                usuarioRolFacade.edit(usuarioRol);
            }
            else{
                usuarioRol = new UserRoles();
                usuarioRol.setRoleName(usuario.getRol().getNombre());
                usuarioRol.setUsername(usuario.getClaveAcceso());
                usuarioRolFacade.create(usuarioRol);
            }
        }
        else{
           actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
           mensaje=Propiedades.getMensaje(Constantes.CATALOGO_USUARIO_NUEVO_GUARDO);
           hashPassword=new Md5Hash(usuario.getHash()).toHex();
           usuario.setHash(hashPassword);
           usuario.setFechaRegistro(new Date());
           usuario.setActualizarHash(true);
           claveAcceso=generaClaveAcceso(usuario.getNombre(),usuario.getApellidoPaterno());
           usuario.setClaveAcceso(claveAcceso);
           usuarioFacade.create(usuario);
           usuarioRol.setRoleName(usuario.getRol().getNombre());
           usuarioRol.setUsername(usuario.getClaveAcceso());
           usuarioRolFacade.create(usuarioRol);
           idUsuario=usuario.getId();
           usuario = new Usuario();
           rol=new Rol();
           estatus=new Estatus();
           compania=new Compania();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,usuario.getId()==null?idUsuario:usuario.getId(), actividad);
    }
    private String generaClaveAcceso(String nombre, String apellido)
    {
        String clave = null;
        Random random = new Random();
        int numero = 01 + random.nextInt(90);
        try
        {
            if (null != nombre && null != apellido)
            {
                clave = nombre.substring(0, 1);
                clave += apellido.concat(String.valueOf(numero));
            }
        } catch (Exception e)
        {
           out.println("E/GVN NuevoUsuario: " + e.getMessage());
        }
        return clave;
    }
    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public Rol getRol()
    {
        return rol;
    }

    public void setRol(Rol rol)
    {
        this.rol = rol;
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public List<Rol> getRolList()
    {
        return rolList;
    }

    public List<Estatus> getEstatusList()
    {
        return estatusList;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    public Compania getCompania()
    {
        return compania;
    }

    public void setCompania(Compania compania)
    {
        this.compania = compania;
    }

    public List<Compania> getCompaniaList()
    {
        return companiaList;
    }
    
    
    
    

}
