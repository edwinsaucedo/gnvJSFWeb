/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Pais;
import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import com.gnv.business.ejb.persistencia.CiudadFacadeLocal;
import com.gnv.business.ejb.persistencia.DocTipoArchivoFacadeLocal;
import com.gnv.business.ejb.persistencia.DocumentoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstadoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.LineaFacadeLocal;
import com.gnv.business.ejb.persistencia.MarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PaisFacadeLocal;
import com.gnv.business.ejb.persistencia.PropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.ServidorArchivosFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocumentacionFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.VehClaseFacadeLocal;
import com.gnv.business.ejb.persistencia.VehPropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoServicioFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoInfoAFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.factories.FileUploadHelper;
import com.gnv.views.utils.Comun;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Administrador
 */@ManagedBean
@ViewScoped
public class NuevoVehiculoManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;
    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;
    @EJB
    private MarcaFacadeLocal marcaFacade;
    @EJB
    private VehClaseFacadeLocal vehClaseFacade;
    @EJB
    private VehTipoFacadeLocal vehTipoFacade;
    @EJB
    private VehPropietarioFacadeLocal vehPropietarioFacade;
    @EJB
    private VehTipoServicioFacadeLocal vehTipoServicioFacade;
    @EJB
    private LineaFacadeLocal lineaFacade;
    @EJB
    private PropietarioFacadeLocal propietarioFacade;
    @EJB
    private TipoDocumentacionFacadeLocal tipoDocumentacionFacade;
    @EJB
    private VehiculoInfoAFacadeLocal vehInfoAFacade;
    @EJB
    private PaisFacadeLocal paisFacade;
    @EJB
    private CiudadFacadeLocal ciudadFacade;
    @EJB
    private EstadoFacadeLocal estadoFacade;
    @EJB
    private DocumentoFacadeLocal documentoFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;
    @EJB
    private DocTipoArchivoFacadeLocal tipoArchivoFacade;

    private Estatus estatus;

    private VehTipoServicio veTipoServicio;
    private VehTipo veTipo;
    private VehClase veClase;
    private Marca marca;
    private Linea linea;
    private Vehiculo vehiculo;
    private TipoMarca tipoMarca;
    private TipoDocumentacion tipoDocumentacion;
    private Propietario propietario;
    private VehPropietario vePropietario;
    private VehiculoInfoA veInfoA;
    private Estado estado;
    private Pais pais;
    private Ciudad ciudad;
    private Documento documento;
    private Objeto objeto;
    private Objeto objetoV; 
    private Objeto objetoVehPropietario;
    private Objeto objetoPropietario;
    private Objeto objetoDocumento;
    private ServidorArchivos servidorArchivo;

    private List<VehTipoServicio> veTipoServicioList;
    private List<VehTipo> veTipoList;
    private List<VehClase> veClaseList;
    private List<Marca> marcaList;
    private List<Linea> lineaList;
    private List<Propietario> propietarioListC;
    private List<Propietario> propietarioListI;
    private List<VehPropietario> vePropietarioList;
    private List<TipoDocumentacion> tipoDocumentacionList;
    private List<Estado> estadoList;
    private List<Pais> paisList;
    private List<Ciudad> ciudadList;
    private List<Documento> documentoList;

    private String titulo, placaAux, vinAux;
    private boolean vehiculoInfoEnabled, propietarioInfoVisible;
    private UploadedFile archivo;
    private StreamedContent streamedContent;

    public NuevoVehiculoManagedBean() {

    }

    @PostConstruct
    public void init() {
        vehiculoInfoEnabled = true;
        propietarioListI = new ArrayList<>();
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            paisList = paisFacade.findAll();
            documento = new Documento();
            documento.setPublico(false);
            veTipoServicioList = vehTipoServicioFacade.findVehTipoServicio();
            veTipoList = vehTipoFacade.findVehTipo();
            veClaseList = vehClaseFacade.findVehClase();
            tipoMarca = tipoMarcaFacade.findByTipoMarcaNombre("Vehículo");
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_VEHICULO);
            marcaList = marcaFacade.findByTipoMarca(tipoMarca);
            propietarioListC = propietarioFacade.findByEstatusRow();
            tipoDocumentacion = new TipoDocumentacion();
            propietario = new Propietario();
            tipoDocumentacionList = tipoDocumentacionFacade.findByEstatusRow();
            if (id != null) {
                vehiculo = vehiculoFacade.find(Integer.valueOf(id));
                documentoList = documentoFacade.findByObjeto(objeto, vehiculo.getId());
                placaAux = vehiculo.getPlaca();
                vinAux = vehiculo.getVin();
                marca = vehiculo.getMarca();
                lineaList = lineaFacade.findLineasByMarca(marca);
                linea = vehiculo.getLinea();
                veClase = vehiculo.getClase();
                veTipo = vehiculo.getTipo();
                veTipoServicio = vehiculo.getTipoServicio();
                titulo = Constantes.VEHICULO_EDITAR_TITULO;
                vePropietarioList = vehPropietarioFacade.findByVehiculo(vehiculo);
                propietarioInfoVisible = true;
                veInfoA = vehiculo.getVehiculoInfoA();
                if (veInfoA != null) {
                    ciudadList = veInfoA.getCiudad() != null ? ciudadFacade.findByEstado(veInfoA.getCiudad().getEstado()) : new ArrayList<>();
                    ciudad = veInfoA.getCiudad() != null ? veInfoA.getCiudad() : new Ciudad();
                    estadoList = veInfoA.getCiudad() != null ? estadoFacade.findByPais(veInfoA.getCiudad().getEstado().getPais()) : new ArrayList<>();
                    estado = ciudad.getEstado() != null ? ciudad.getEstado() : new Estado();
                    pais = estado.getPais() != null ? estado.getPais() : new Pais();
                } else {
                    estadoList = new ArrayList<>();
                    ciudadList = new ArrayList<>();
                    estado = new Estado();
                    pais = new Pais();
                    ciudad = new Ciudad();
                    veInfoA = new VehiculoInfoA();
                }
            } else {
                marca = new Marca();
                vehiculo = new Vehiculo();
                linea = new Linea();
                veClase = new VehClase();
                veTipo = new VehTipo();
                documentoList = new ArrayList<>();
                veTipoServicio = new VehTipoServicio();
                veInfoA = new VehiculoInfoA();
                lineaList = new ArrayList<>();
                titulo = Constantes.VEHICULO_NUEVO_TITULO;
                propietarioInfoVisible = false;
                estadoList = new ArrayList<>();
                ciudadList = new ArrayList<>();
                estado = new Estado();
                pais = new Pais();
                ciudad = new Ciudad();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        Vehiculo vAuxiliar = new Vehiculo();
        try {
            objetoV = objetoFacade.findByNombre(Constantes.OBJETO_VEHICULO_INFO_A);
            estatus = estatusFacade.findByClase(Vehiculo.class.getName(), Constantes.ESTATUS_REGISTRADO);
            vehiculo.setClase(veClase);
            vehiculo.setMarca(marca);
            vehiculo.setLinea(linea);
            vehiculo.setTipoServicio(veTipoServicio);
            vehiculo.setTipo(veTipo);
            vAuxiliar = vehiculoFacade.findbyPlaca(vehiculo.getPlaca());
            if (vAuxiliar != null & (vehiculo.getId() == null || !vehiculo.getPlaca().equals(placaAux))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El número de placa ya se encuentra registrado."));
                return;
            }
            vAuxiliar = vehiculoFacade.findbyVin(vehiculo.getVin());
            if (vAuxiliar != null & (vehiculo.getId() == null || !vehiculo.getVin().equals(vinAux))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El número de VIN ya se encuentra registrado."));
                return;
            }
            if (ciudad != null) {
                veInfoA.setCiudad(ciudad);
            }
            veInfoA.setCompania(usuarioLogueado.getCompania());
            if (vehiculo.getId() != null) {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_EDITO);
                vehiculoFacade.edit(vehiculo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehiculo.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                if (veInfoA.getId() != null) {

                    actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_EDITO_INF_ADICIONAL);

                    vehInfoAFacade.edit(veInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, veInfoA.getId(), actividad);
                } else {
                    veInfoA.setEstatusRow(1);
                    veInfoA.setUsuarioIns(usuarioLogueado.getId());
                    veInfoA.setFechaIns(new Date());
                    actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    vehInfoAFacade.create(veInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, veInfoA.getId(), actividad);
                    if (null != veInfoA.getId()) {
                        vehiculo.setVehiculoInfoA(veInfoA);
                        actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                        vehiculoFacade.edit(vehiculo);
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehiculo.getId(), actividad);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO_INF_ADICIONAL);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehiculo.setEstatusRow(1);
                vehiculo.setEstatus(estatus);
                vehiculo.setFechaIns(new Date());
                vehiculo.setUsuarioIns(usuarioLogueado.getId());
                vehiculoFacade.create(vehiculo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehiculo.getId(), actividad);
                if (vehiculo.getId() != null) {
                    actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    veInfoA.setEstatusRow(1);
                    veInfoA.setUsuarioIns(usuarioLogueado.getId());
                    veInfoA.setFechaIns(new Date());
                    vehInfoAFacade.create(veInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, veInfoA.getId(), actividad);
                    if (null != veInfoA.getId()) {
                        vehiculo.setVehiculoInfoA(veInfoA);
                        actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                        vehiculoFacade.edit(vehiculo);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO_INF_ADICIONAL);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehiculo.getId(), actividad);

                    }
                    vehiculoInfoEnabled = false;
                    propietarioInfoVisible = true;
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public void guardarPropietarioVeh() {
        String mensaje = "", actividad = "";
        try {
            objetoVehPropietario = objetoFacade.findByNombre(Constantes.OBJETO_VEH_PROPIETARIO);
            int i = 0;
            for (Propietario p : propietarioListI) {
                vePropietario = vehPropietarioFacade.findByPropietarioVehiculo(p, vehiculo);
                if (vePropietario != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ASIGNAR_PROPIETARIO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                    break;
                }
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vePropietario = new VehPropietario();
                vePropietario.setPropietario(p);
                vePropietario.setVehiculo(vehiculo);
                vePropietario.setFechaIns(new Date());
                vePropietario.setUsuarioIns(usuarioLogueado.getId());
                vePropietario.setEstatusRow(1);
                vehPropietarioFacade.create(vePropietario);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVehPropietario, vePropietario.getId(), actividad);
                if (i == propietarioListI.size() - 1) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ASIGNAR_PROPIETARIO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vePropietarioList = vehPropietarioFacade.findByVehiculo(vehiculo);
                    propietarioListI = new ArrayList<>();
                }
                i++;
            }

        } catch (Exception e) {

            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void guardarPropietario() {
        String mensaje = "", actividad = "";
        try {
            objetoPropietario = objetoFacade.findByNombre(Constantes.OBJETO_PROPIETARIO);
            if (!Comun.isNumeric(propietario.getTelefono())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El telefono solo debe contener valores numericos."));
                return;
            }
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            propietario.setCncdiridn(0);
            propietario.setCnciasidn(0);
            propietario.setEstatusRow(1);
            propietario.setFechaIns(new Date());
            propietario.setTipoDocumentacion(tipoDocumentacion);
            propietario.setUsuarioIns(usuarioLogueado.getId());
            propietarioFacade.create(propietario);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoPropietario, propietario.getId(), actividad);

            if (propietario.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_AGREGAR_PROPIETARIO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                propietarioListC.add(propietario);
                propietario = new Propietario();
                tipoDocumentacion = new TipoDocumentacion();
                PrimeFaces.current().executeScript("PF('dlgNuevoPropietario').hide()");
            } else {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_AGREGAR_PROPIETARIO_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public void eliminarVehPropietario() {
        String mensaje, actividad = "";
        try {
            objetoPropietario = objetoFacade.findByNombre(Constantes.OBJETO_PROPIETARIO);
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_PROPIETARIO_VEH);
            vePropietario = vehPropietarioFacade.find(Integer.valueOf(id));
            vePropietario.setEstatusRow(-1);
            vehPropietarioFacade.edit(vePropietario);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVehPropietario, vePropietario.getId(), actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            vePropietarioList = vehPropietarioFacade.findByVehiculo(vehiculo);
        } catch (Exception e) {
            out.println("E/GVN Nuevo vehiculo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }

    }

    public void eliminarDocumento() {
        String idDocumento = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
        String mensaje, actividad;
        try {
            objetoDocumento = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_DOCUMENTO);
            Documento documentoD = documentoFacade.find(Integer.valueOf(idDocumento));
            documentoD.setEstatusRow(-1);
            documentoFacade.edit(documentoD);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoDocumento, documento.getId(), actividad);
            documentoList = documentoFacade.findByObjeto(objeto, vehiculo.getId());

        } catch (Exception e) {
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void descargarDocumento(String idDocumento) {
        String ruta, nombreArchivo;
        try {
            servidorArchivo = servidorArchivosFacade.findAll().get(0);
            StreamedContent documentoContent;
            Documento documentoD = documentoFacade.find(Integer.valueOf(idDocumento));
            if (documentoD != null && servidorArchivo != null) {
                ruta = documentoD.getRuta();
                nombreArchivo = documentoD.getNombre() + "." + documentoD.getTipoArchivo().getExtension();
                documentoContent = FileUploadHelper.descargarArchivo(nombreArchivo, documentoD.getTipoArchivo().getExtension(), servidorArchivo, ruta);
                if (documentoContent == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
                    return;
                }
                this.setStreamedContent(documentoContent);
            }
        } catch (Exception e) {
            out.println("E/GVN Nuevo vehiculo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
        }

    }

    public void guardarDocumento(FileUploadEvent fileUploadEvent) {
        String tokens[], ruta, extension, actividad;
        DocTipoArchivo tipoArchivo;
        if (vehiculo.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Es necesario guardar la informacion del vehiculo."));
            return;
        }

        try {
            objetoDocumento = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            ruta = objeto.getNombre() + "/" + vehiculo.getId();

            archivo = fileUploadEvent.getFile();
            tokens = archivo.getFileName().split("\\.(?=[^\\.]+$)");
            extension = tokens[1];
            tipoArchivo = tipoArchivoFacade.findByExtension(extension);
            if (tipoArchivo == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Tipo de archivo no valido."));
                return;
            }
            servidorArchivo = servidorArchivosFacade.findAll().get(0);
            documento.setNombre(tokens[0]);
            if (!documento.isPublico()) {
                documento.setCompania(usuarioLogueado.getCompania());
            }
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            documento.setTamanio(String.valueOf(archivo.getSize()));
            documento.setTipoArchivo(tipoArchivo);
            documento.setIdRegistro(vehiculo.getId());
            documento.setObjeto(objeto);
            documento.setRuta(ruta);
            documento.setFechaIns(new Date());
            documento.setUsuarioIns(usuarioLogueado.getId());
            documento.setEstatusRow(1);
            documentoFacade.create(documento);

            if (documento.getId() != null) {
                if (FileUploadHelper.cargarArchivos(archivo, servidorArchivo, ruta)) {
                    documentoList = documentoFacade.findByObjeto(objeto, vehiculo.getId());
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoDocumento, documento.getId(), actividad);
                    documento = new Documento();
                    documento.setPublico(false);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Documento cargado exitosamente."));
                } else {
                    documentoFacade.remove(documento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al cargar el documento."));
                }

            }
        } catch (Exception e) {
            out.println("E/GVN Nuevo vehiculo: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
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

    public void onMarcaChage() {
        try {
            if (marca != null) {
                lineaList = lineaFacade.findLineasByMarca(marca);
            } else {
                lineaList = new ArrayList<>();
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
        }
    }

    public VehTipoServicio getVeTipoServicio() {
        return veTipoServicio;
    }

    public void setVeTipoServicio(VehTipoServicio veTipoServicio) {
        this.veTipoServicio = veTipoServicio;
    }

    public VehTipo getVeTipo() {
        return veTipo;
    }

    public void setVeTipo(VehTipo veTipo) {
        this.veTipo = veTipo;
    }

    public VehClase getVeClase() {
        return veClase;
    }

    public void setVeClase(VehClase veClase) {
        this.veClase = veClase;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<VehTipoServicio> getVeTipoServicioList() {
        return veTipoServicioList;
    }

    public void setVeTipoServicioList(List<VehTipoServicio> veTipoServicioList) {
        this.veTipoServicioList = veTipoServicioList;
    }

    public List<VehTipo> getVeTipoList() {
        return veTipoList;
    }

    public void setVeTipoList(List<VehTipo> veTipoList) {
        this.veTipoList = veTipoList;
    }

    public List<VehClase> getVeClaseList() {
        return veClaseList;
    }

    public void setVeClaseList(List<VehClase> veClaseList) {
        this.veClaseList = veClaseList;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    public List<Linea> getLineaList() {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList) {
        this.lineaList = lineaList;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Propietario> getPropietarioListC() {
        return propietarioListC;
    }

    public void setPropietarioListC(List<Propietario> propietarioListC) {
        this.propietarioListC = propietarioListC;
    }

    public List<Propietario> getPropietarioListI() {
        return propietarioListI;
    }

    public void setPropietarioListI(List<Propietario> propietarioListI) {
        this.propietarioListI = propietarioListI;
    }

    public TipoDocumentacion getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public List<TipoDocumentacion> getTipoDocumentacionList() {
        return tipoDocumentacionList;
    }

    public void setTipoDocumentacionList(List<TipoDocumentacion> tipoDocumentacionList) {
        this.tipoDocumentacionList = tipoDocumentacionList;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public boolean isVehiculoInfoEnabled() {
        return vehiculoInfoEnabled;
    }

    public boolean isPropietarioInfoVisible() {
        return propietarioInfoVisible;
    }

    public List<VehPropietario> getVePropietarioList() {
        return vePropietarioList;
    }

    public void setVePropietarioList(List<VehPropietario> vhPropietarioListS) {
        this.vePropietarioList = vhPropietarioListS;
    }

    public VehiculoInfoA getVeInfoA() {
        return veInfoA;
    }

    public void setVehInfoA(VehiculoInfoA vehInfoA) {
        this.veInfoA = vehInfoA;
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

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }
    
    

}
