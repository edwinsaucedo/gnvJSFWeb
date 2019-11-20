/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.FabricanteInfoA;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Pais;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CiudadFacadeLocal;
import com.gnv.business.ejb.persistencia.EmpresaFacadeLocal;
import com.gnv.business.ejb.persistencia.EstadoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.FabricanteFacadeLocal;
import com.gnv.business.ejb.persistencia.FabricanteInfoAFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PaisFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocumentacionFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
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
public class NuevoFabricanteManagedBean implements Serializable {

    @EJB
    private FabricanteFacadeLocal fabricanteFacade;

    @EJB
    private EmpresaFacadeLocal empresaFacade;
  
    @EJB
    private TipoDocumentacionFacadeLocal tipoDocFacade;

    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private PaisFacadeLocal paisFacade;

    @EJB
    private CiudadFacadeLocal ciudadFacade;

    @EJB
    private EstadoFacadeLocal estadoFacade;

    @EJB
    private FabricanteInfoAFacadeLocal fabricanteInfoAFacade;
    
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private FabricanteInfoA fabricanteInfoA;
    private Pais pais;
    private Estado estado;
    private Ciudad ciudad;
    private Fabricante fabricante;
    private Empresa empresa;
    private Objeto objeto;
   
    private TipoDocumentacion tipoDocumentacion;
    private List<Empresa> listaEmpresa;
    private List<Fabricante> listaFabricante;
    private List<TipoDocumentacion> listaTipoDoc;
   
    private List<Estado> estadoList;
    private List<Pais> paisList;
    private List<Ciudad> ciudadList;
    private String titulo;
    private Estatus estatus;
    private boolean fabricanteInfoEnabled;
    private boolean tipoValorComision;

    public NuevoFabricanteManagedBean() {
    }

    @PostConstruct
    public void init() {

        fabricanteInfoEnabled = true;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            // 
            paisList = paisFacade.findAll();
            listaEmpresa = empresaFacade.findByEstatusRow();
            listaTipoDoc = tipoDocFacade.findByEstatusRow();
           
            estatus = estatusFacade.findByClase(Fabricante.class.getName(), Constantes.ESTATUS_ACTIVO);
            if (id != null) {
                fabricante = fabricanteFacade.find(Integer.valueOf(id));
                titulo = Constantes.FABRICANTE_EDITAR_TITULO;
                empresa = fabricante.getEmpresa();
                tipoDocumentacion = fabricante.getTipoDocumento();
                titulo = Constantes.FABRICANTE_EDITAR_TITULO;
                fabricanteInfoA = fabricante.getFabricanteInfoA();
                ciudadList = fabricante.getCiudad() != null ? ciudadFacade.findByEstado(fabricante.getCiudad().getEstado()) : new ArrayList<>();
                ciudad = fabricante.getCiudad() != null ? fabricante.getCiudad() : new Ciudad();
                estadoList = fabricante.getCiudad() != null ? estadoFacade.findByPais(fabricante.getCiudad().getEstado().getPais()) : new ArrayList<>();
                estado = ciudad.getEstado() != null ? ciudad.getEstado() : new Estado();
                pais = estado.getPais() != null ? estado.getPais() : new Pais();
                if (fabricanteInfoA != null) {
                    
                    tipoValorComision = fabricanteInfoA.getTipoValorComision();
                } else {
                    fabricanteInfoA = new FabricanteInfoA();

                }
            } else {
                fabricante = new Fabricante();
                empresa = new Empresa();
                estadoList = new ArrayList<>();
                ciudadList = new ArrayList<>();
                estado = new Estado();
                pais = new Pais();
                ciudad = new Ciudad();
                tipoDocumentacion = new TipoDocumentacion();
                titulo = Constantes.FABRICANTE_NUEVO_TITULO;

                estadoList = new ArrayList<>();
                ciudadList = new ArrayList<>();
                estado = new Estado();
                //pais = new Pais();
                ciudad = new Ciudad();
                fabricanteInfoA = new FabricanteInfoA();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "",actividad="";
        Fabricante fAuxiliar = new Fabricante();
        try {
            estatus = estatusFacade.findByClase(Fabricante.class.getName(), Constantes.ESTATUS_ACTIVO);
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_FABRICANTE);
            fabricante.setEstatus(estatus);
            fabricante.setEmpresa(empresa);
            fabricante.setTipoDocumento(tipoDocumentacion);

            if (ciudad != null) {
                fabricante.setCiudad(ciudad);
            }
         
            if (fabricante.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_EDITO);
                fabricanteFacade.edit(fabricante);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,fabricante.getId(), actividad);
                objeto=objetoFacade.findByNombre(Constantes.OBJETO_FABRICANTE_INFO_A);
                if (fabricanteInfoA.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_EDITO_INF_ADICIONAL);
                    fabricanteInfoA.setTipoValorComision(tipoValorComision);
                    fabricanteInfoAFacade.edit(fabricanteInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,fabricanteInfoA.getId(), actividad);
                } else {
                    fabricanteInfoA.setTipoValorComision(tipoValorComision);
                    fabricanteInfoAFacade.create(fabricanteInfoA);
                    actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,fabricanteInfoA.getId(), actividad);
                    if (null != fabricanteInfoA.getId()) {
                        fabricante.setFabricanteInfoA(fabricanteInfoA);
                        fabricanteFacade.edit(fabricante);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_GUARDO_INF_ADICIONAL);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else {

                fabricante.setEstatus(estatus);
                fabricanteFacade.create(fabricante);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,fabricanteInfoA.getId(), actividad);
                objeto=objetoFacade.findByNombre(Constantes.OBJETO_FABRICANTE_INFO_A);
                if (fabricante.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    fabricanteInfoA.setTipoValorComision(tipoValorComision);
                    fabricanteInfoAFacade.create(fabricanteInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,fabricanteInfoA.getId(), actividad); 
                    if (null != fabricanteInfoA.getId()) {
                        fabricante.setFabricanteInfoA(fabricanteInfoA);
                        fabricanteFacade.edit(fabricante);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_GUARDO_INF_ADICIONAL);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

                    }
                    fabricanteInfoEnabled = false;

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FABRICANTE_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoFabricante: " + e.getMessage());
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public void onPaisChange() {
        if (pais != null) {
            estadoList = estadoFacade.findByPais(pais);
        } else {
            estadoList = new ArrayList<>();
        }
    }

    public void onEstadoChange() {
        if (estado != null) {
            ciudadList = ciudadFacade.findByEstado(estado);
        } else {
            ciudadList = new ArrayList<>();
        }
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoDocumentacion getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public List<Fabricante> getListaFabricante() {
        return listaFabricante;
    }

    public void setListaFabricante(List<Fabricante> listaFabricante) {
        this.listaFabricante = listaFabricante;
    }

    public List<TipoDocumentacion> getListaTipoDoc() {
        return listaTipoDoc;
    }

    public void setListaTipoDoc(List<TipoDocumentacion> listaTipoDoc) {
        this.listaTipoDoc = listaTipoDoc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public FabricanteInfoA getFabricanteInfoA() {
        return fabricanteInfoA;
    }

    public void setFabricanteInfoA(FabricanteInfoA fabricanteInfoA) {
        this.fabricanteInfoA = fabricanteInfoA;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public boolean isFabricanteInfoEnabled() {
        return fabricanteInfoEnabled;
    }

    public void setFabricanteInfoEnabled(boolean fabricanteInfoEnabled) {
        this.fabricanteInfoEnabled = fabricanteInfoEnabled;
    }

    public boolean isTipoValorComision() {
        return tipoValorComision;
    }

    public void setTipoValorComision(boolean tipoValorComision) {
        this.tipoValorComision = tipoValorComision;
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
