/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Eds;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Pais;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import com.gnv.business.ejb.persistencia.CiudadFacadeLocal;
import com.gnv.business.ejb.persistencia.EdsFacadeLocal;
import com.gnv.business.ejb.persistencia.EmpresaFacadeLocal;
import com.gnv.business.ejb.persistencia.EstadoFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PaisFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevaEstacionManagedBean implements Serializable {

    private MapModel draggableModel;

    private Estado estado;
    private Eds eds;
    private Pais pais;
    private Ciudad ciudad;
    private Marker marker;
    private Marker marker2;
    private String centerGeoMap = "19.4978, -99.1269";
    private Integer zoomMap;
    private String lat;
    private String lon;
    private Double latitud;
    private Double longitud;
    private Empresa empresa;
    private String titulo;

    @EJB
    private PaisFacadeLocal paisFacade;
    @EJB
    private CiudadFacadeLocal ciudadFacade;
    @EJB
    private EstadoFacadeLocal estadoFacade;
    @EJB
    private EdsFacadeLocal edsFacade;
    @EJB
    private EmpresaFacadeLocal empresaFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;
    private List<Estado> estadoList;
    private List<Pais> paisList;
    private List<Ciudad> ciudadList;
    private List<Empresa> listaEmpresa;
    private LatLng center;

    @PostConstruct
    public void init() {
        draggableModel = new DefaultMapModel();

        //Shared coordinates
        try {
          
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            paisList = paisFacade.findAll();
            listaEmpresa = empresaFacade.findByEstatusRow();

            if (id != null) {
                eds = edsFacade.find(Integer.valueOf(id));
                  zoomMap = 18;
                titulo = Constantes.EDS_EDITAR_TITULO;
                ciudadList = eds.getCiudad() != null ? ciudadFacade.findByEstado(eds.getCiudad().getEstado()) : new ArrayList<>();
                ciudad = eds.getCiudad() != null ? eds.getCiudad() : new Ciudad();
                estadoList = eds.getCiudad() != null ? estadoFacade.findByPais(eds.getCiudad().getEstado().getPais()) : new ArrayList<>();
                estado = ciudad.getEstado() != null ? ciudad.getEstado() : new Estado();
                pais = estado.getPais() != null ? estado.getPais() : new Pais();
                lat = eds.getLatitud();
                lon = eds.getLongitud();
                empresa = eds.getEmpresa();
                centerGeoMap = lat + ',' + lon;
                latitud = Double.parseDouble(lat);  
                longitud = Double.parseDouble(lon);
                LatLng coord1 = new LatLng(latitud, longitud);
                draggableModel.addOverlay(marker);
                if (marker != null) {
                    marker.setLatlng(coord1);

                } else {
                    draggableModel.addOverlay(new Marker(coord1));
                    marker = draggableModel.getMarkers().get(0);
                }

            } else {
                  zoomMap = 10;
                titulo = Constantes.EDS_NUEVO_TITULO;
                estadoList = new ArrayList<>();
                ciudadList = new ArrayList<>();
                estado = new Estado();
                pais = new Pais();
                ciudad = new Ciudad();
                eds = new Eds();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevaEstacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        int id=0;
        try {
            eds.setEstatusRow(1);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_ESTACION_SERVICIO);
            if (ciudad != null) {
                eds.setCiudad(ciudad);
            }
            if (lat == null && lon == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debes ubicar la estación"));
                return;
            } else {
                eds.setLatitud(lat);
                eds.setLongitud(lon);
            }
            eds.setEmpresa(empresa);

            if (eds.getId() != null) {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EDS_NUEVO_EDITO);
                edsFacade.edit(eds);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                edsFacade.create(eds);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                if (eds.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EDS_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=eds.getId();
                    estadoList = new ArrayList<>();
                    ciudadList = new ArrayList<>();
                    estado = new Estado();
                    pais = new Pais();
                    ciudad = new Ciudad();
                    eds = new Eds();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_EDS_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,eds.getId()==null?id:eds.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN NuevoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            lat = String.valueOf(center.getLat());
            lon = String.valueOf(center.getLng());
            zoomMap = 15;

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                if (marker != null) {
                    marker.setLatlng(result.getLatLng());
                    draggableModel.addOverlay(marker2);
                } else {
                    draggableModel.addOverlay(new Marker(result.getLatLng()));
                    marker = draggableModel.getMarkers().get(0);

                }

            }
            for (Marker premarker : draggableModel.getMarkers()) {
                premarker.setDraggable(true);
            }

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

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
        lat = String.valueOf(marker.getLatlng().getLat());
        lon = String.valueOf(marker.getLatlng().getLng());

        //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Eds getEds() {
        return eds;
    }

    public void setEds(Eds eds) {
        this.eds = eds;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
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

    public Integer getZoomMap() {
        return zoomMap;
    }

    public void setZoomMap(Integer zoomMap) {
        this.zoomMap = zoomMap;
    }
    
    

}
