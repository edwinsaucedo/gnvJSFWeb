/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Compania;
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
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.modelo.VehiculoInfoA;
import com.gnv.business.ejb.persistencia.CiudadFacadeLocal;
import com.gnv.business.ejb.persistencia.CompaniaFacadeLocal;
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
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocumentacionFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.ValoracionFacadeLocal;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevoValoracionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    //Facade
    @EJB
    private CompaniaFacadeLocal companiaFacade;

    @EJB
    private TallerFacadeLocal tallerFacade;

    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;

    @EJB
    private VehiculoInfoAFacadeLocal vehiculoInfoAFacade;

    @EJB
    private ValoracionFacadeLocal valoracionFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private DocumentoFacadeLocal documentoFacade;
    @EJB
    private DocTipoArchivoFacadeLocal docTipoArchivoFacade;

    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;
    ///Vehiculo Facades
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

    ///Objetos
    private Compania compania;
    private Taller taller;
    private Valoracion valoracion;
    private Vehiculo vehiculo;
    private Valoracion valoracionAux;
    private Objeto objeto;
    private Vehiculo vehiculoBusqueda;
    private Objeto objetoV;
    private VehiculoInfoA veInfoA;
    private StreamedContent streamedContent;
    private DocTipoArchivo docTipoArchivo;
    private UploadedFile file;
    private Documento documento;
    private ServidorArchivos servidorArchivo;
    private Estatus estatusV;
    private VehTipoServicio veTipoServicio;
    private VehTipo veTipo;
    private VehClase veClase;
    private Marca marca;
    private Linea linea;
    private TipoMarca tipoMarca;
    private TipoDocumentacion tipoDocumentacion;
    private Propietario propietario;
    private VehPropietario vePropietario;
    private VehPropietario vePropietarioBusqueda;
    private Estado estado;
    private Pais pais;
    private Ciudad ciudad;

    //Listas
    private List<Taller> listaTaller;
    private List<Estatus> listaEstatus;
    private List<Vehiculo> listaVehiculo;
    private List<VehTipoServicio> veTipoServicioList;
    private List<VehTipo> veTipoList;
    private List<VehClase> veClaseList;
    private List<Marca> marcaList;
    private List<Linea> lineaList;
    private List<Propietario> propietarioListC;
    private List<Propietario> propietarioListI;
    private List<VehPropietario> vePropietarioList;
    private List<VehPropietario> vePropietarioListBusqueda;
    private List<TipoDocumentacion> tipoDocumentacionList;
    private List<Estado> estadoList;
    private List<Pais> paisList;
    private List<Ciudad> ciudadList;
    private List<Documento> listaDocumento;

    //Vars
    private String titulo;
    private Date fecha;
    private String observacion;
    private String nombremarca;
    private boolean solicitarCredito;
    private boolean vehiculoInfoEnabled, propietarioInfoVisible;
    private String placaAux, vinAux;
    private String placaPanel, vinPanel, marcaPanel;
    private boolean valoracionInfoDisabled = false;
    private boolean publico;
    private String extension;
    private String placa;
    private String vin;
    private boolean estatus;
    private Objeto objetoPropietario;

    public NuevoValoracionManagedBean() {
    }

    @PostConstruct
    public void init() {
        vehiculoInfoEnabled = true;
        propietarioListI = new ArrayList<>();
        publico = false;
        solicitarCredito = false;
        fecha = getDateTime();
        try {
            //Instancias de Vehiculo
            paisList = paisFacade.findAll();
            objetoPropietario = objetoFacade.findByNombre(Constantes.OBJETO_PROPIETARIO);
            veTipoServicioList = vehTipoServicioFacade.findVehTipoServicio();
            veTipoList = vehTipoFacade.findVehTipo();
            veClaseList = vehClaseFacade.findVehClase();
            tipoMarca = tipoMarcaFacade.findByTipoMarcaNombre("Vehículo");
            objetoV = objetoFacade.findByNombre(Constantes.OBJETO_VEHICULO);
            marcaList = marcaFacade.findByTipoMarca(tipoMarca);
            valoracionAux = valoracionFacade.findByVehiculo(vehiculo);
            propietarioListC = propietarioFacade.findByEstatusRow();
            tipoDocumentacion = new TipoDocumentacion();
            propietario = new Propietario();

            tipoDocumentacionList = tipoDocumentacionFacade.findByEstatusRow();
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_VALORACION);

            estadoList = new ArrayList<>();
            vePropietarioListBusqueda = new ArrayList<>();
            ciudadList = new ArrayList<>();
            estado = new Estado();
            pais = new Pais();
            ciudad = new Ciudad();
            veInfoA = new VehiculoInfoA();

            marca = new Marca();
            vehiculo = new Vehiculo();
            vehiculoBusqueda = new Vehiculo();
            linea = new Linea();
            veClase = new VehClase();
            veTipo = new VehTipo();

            veTipoServicio = new VehTipoServicio();
            veInfoA = new VehiculoInfoA();
            lineaList = new ArrayList<>();
            propietarioInfoVisible = false;
            estadoList = new ArrayList<>();
            ciudadList = new ArrayList<>();
            estado = new Estado();
            pais = new Pais();
            ciudad = new Ciudad();
            ///----

            //Instancias de valoración
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (usuarioLogueado != null) {
                compania = usuarioLogueado.getCompania();
                if (compania != null) {
                    listaTaller = tallerFacade.findByCompania(compania);
                }
            }
            if (id != null) {
                valoracion = valoracionFacade.find(Integer.valueOf(id));
                titulo = Constantes.VALORACION_EDITAR_TITULO;
                taller = valoracion.getTaller();
                fecha = valoracion.getFecha();
                vin = valoracion.getVehiculo().getVin();
                placa = valoracion.getVehiculo().getPlaca();
                vinPanel = valoracion.getVehiculo().getVin();
                placaPanel = valoracion.getVehiculo().getPlaca();
                marcaPanel = valoracion.getVehiculo().getMarca().getNombre();
                vehiculo = valoracion.getVehiculo();
                observacion = valoracion.getObservacion();
                solicitarCredito = valoracion.getSolicitarCredito();
                estatus = valoracion.getEstado();
                vePropietarioListBusqueda = vehPropietarioFacade.findByVehiculo(vehiculo);
                valoracionAux = valoracionFacade.findByVehiculo(vehiculo);

            } else {
                valoracion = new Valoracion();
                vehiculo = new Vehiculo();
                taller = new Taller();
                listaTaller = tallerFacade.findByCompania(compania);

                vin = "";
                placa = "";
                observacion = "";
                nombremarca = "";
                titulo = Constantes.VALORACION_NUEVO_TITULO;
                listaVehiculo = new ArrayList<>();

            }

            if (valoracion.getId() != null && objeto != null) {
                listaDocumento = documentoFacade.findByObjeto(objeto, valoracion.getId());
            }
            ///---

        } catch (Exception e) {
            out.println("E/GVN NuevoValoracion: " + e.getMessage());
        }

    }

    public Date getDateTime() {
        java.util.Date utilDate = new java.util.Date();
        Date date;
        String dateFinally;
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(utilDate);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        java.sql.Timestamp sqlTimestamp = new Timestamp(time);
        date = sqlTimestamp;
        dateFinally = formateador.format(date);
        return date;

    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            if (vehiculo.getId() == null) {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REQUIRED_VEH_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;

            }

            valoracion.setSolicitarCredito(solicitarCredito);

            valoracion.setEstatusRow(1);
            valoracion.setFecha(fecha);
            valoracion.setEstado(estatus);
            valoracion.setTaller(taller);
            valoracion.setVehiculo(vehiculo);

            if (valoracion.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_VALORACION_NUEVO_EDITO);
                actividad = actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                valoracionFacade.edit(valoracion);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else {
                valoracion.setFechaIns(new Date());
                valoracion.setUsuarioIns(usuarioLogueado.getId());
                actividad = actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                valoracionFacade.create(valoracion);
                if (valoracion.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.PROCESO_VALORACION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

                    valoracionInfoDisabled = true;

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.PROCESO_VALORACION_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, valoracion.getId(), actividad);
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoValoracion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void buscar() {
        String mensaje = "";
        try {
            if (vin.isEmpty() && placa.isEmpty()) {
                listaVehiculo.clear();
            } else if (placa.isEmpty()) {

                listaVehiculo = vehiculoFacade.findByVinList(vin);

            } else if (vin.isEmpty()) {

                listaVehiculo = vehiculoFacade.findByPlacaList(placa);
            } else if (!(vin.isEmpty() && placa.isEmpty())) {

                listaVehiculo = vehiculoFacade.findByVinPlacaList(vin, placa);
            }

            //Comaparacion si obtiene solo un resultado
            if (listaVehiculo.size() == 1) {

                vehiculoBusqueda = listaVehiculo.get(0);
                valoracionAux = valoracionFacade.findByVehiculo(vehiculoBusqueda);
                if (valoracionAux != null) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ya existe una valoracion para el vehículo"));
                    return;
                } else {
                    vehiculo = listaVehiculo.get(0);
                }
                vePropietarioListBusqueda = vehPropietarioFacade.findByVehiculo(vehiculo);
                PrimeFaces.current().executeScript("PF('dlgBuscarVeh').hide()");
                if (vePropietarioListBusqueda.isEmpty()) {

                    listaVehiculo = vehiculoFacade.findByVehiculo(vehiculo.getId());
                    vinPanel = listaVehiculo.get(0).getVin();
                    placaPanel = listaVehiculo.get(0).getPlaca();
                    marcaPanel = listaVehiculo.get(0).getMarca().getNombre();
                    vin = listaVehiculo.get(0).getVin();
                    placa = listaVehiculo.get(0).getPlaca();

                } else {

                    vinPanel = vePropietarioListBusqueda.get(0).getVehiculo().getVin();
                    placaPanel = vePropietarioListBusqueda.get(0).getVehiculo().getPlaca();
                    marcaPanel = vePropietarioListBusqueda.get(0).getVehiculo().getMarca().getNombre();
                    vin = vePropietarioListBusqueda.get(0).getVehiculo().getVin();
                    placa = vePropietarioListBusqueda.get(0).getVehiculo().getPlaca();
                    PrimeFaces.current().executeScript("PF('dlgBuscarVeh').hide()");
                }

            } else if (listaVehiculo.isEmpty()) {

                vin = "";
                placa = "";
                nombremarca = "";
                vinPanel = "";
                marcaPanel = "";
                placaPanel = "";
                listaVehiculo = new ArrayList<>();
                vePropietarioListBusqueda = new ArrayList<>();
            }

            //
            if (listaVehiculo.size() > 1) {
                PrimeFaces.current().executeScript("PF('dlgBuscarVeh').show()");
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoValoracion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void buscarDialogo() {
        String mensaje = "";
        try {
            // PrimeFaces.current().executeScript("PF('dlgBuscarVeh').hide()");
            if (vin.isEmpty() && placa.isEmpty()) {
                listaVehiculo.clear();
            } else if (placa.isEmpty()) {

                listaVehiculo = vehiculoFacade.findByVinList(vin);

            } else if (vin.isEmpty()) {

                listaVehiculo = vehiculoFacade.findByPlacaList(placa);
            } else if (!(vin.isEmpty() && placa.isEmpty())) {

                listaVehiculo = vehiculoFacade.findByVinPlacaList(vin, placa);
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoValoracion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void abrirBusqueda() {

        PrimeFaces.current().executeScript("PF('dlgBuscarVeh').show()");
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void guardarDocumento(FileUploadEvent fileUploadEvent) {
        String tokens[], ruta;

        String mensaje = "", actividad = "";
        try {
            Objeto objetoDoc = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);

            if (valoracion.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REQUIRED_VAL_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {
                documento = new Documento();
                file = fileUploadEvent.getFile();
                ruta = objeto.getNombre() + "/" + valoracion.getId() + "/";
                //servidorArchivos  = seervidorArchivosFacade.findAll.get(0);

                tokens = file.getFileName().split("\\.(?=[^\\.]+$)");

                //  documento.setTipoArchivo(tipoArchivo);
                extension = FilenameUtils.getExtension(file.getFileName());
                docTipoArchivo = docTipoArchivoFacade.findByExtension(extension);
                if (docTipoArchivo == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Tipo de archivo no valido."));
                    return;
                }
                servidorArchivo = servidorArchivosFacade.findAll().get(0);

                documento.setNombre(tokens[0]);
                documento.setCompania(usuarioLogueado.getCompania());
                documento.setTamanio(String.valueOf(file.getSize()) + " Kb");
                documento.setTipoArchivo(docTipoArchivo);
                documento.setEstatusRow(1);
                documento.setObjeto(objeto);
                documento.setFechaIns(new Date());
                documento.setUsuarioIns(usuarioLogueado.getId());
                documento.setRuta(ruta);
                documento.setPublico(publico);
                documento.setIdRegistro(valoracion.getId());
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                documentoFacade.create(documento);
               
            }
            if (documento.getId() != null) {
                if (FileUploadHelper.cargarArchivos(file, servidorArchivo, ruta)) {
                    listaDocumento = documentoFacade.findByObjeto(objeto, valoracion.getId());
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoDoc, documento.getId(), actividad);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Documento cargado exitosamente."));
                } else {
                    documentoFacade.remove(documento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al cargar el documento."));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN guardarDocumentos: " + e.getMessage());
        }

    }

    public void eliminarDocumento() {
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDoc");
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_DOCUMENTO);
            documento = documentoFacade.find(Integer.valueOf(id));
            documento.setEstatusRow(-1);
            documentoFacade.edit(documento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            if (valoracion.getId() != null) {
                listaDocumento = documentoFacade.findByObjeto(objeto, valoracion.getId());
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, documento.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaDocumento: " + e.getMessage());
            //Registrar en bitacora de exepciones.
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

    public void nuevoVehiculo() {

        PrimeFaces.current().executeScript("PF('dlgVeh').show()");
        vehiculo = new Vehiculo();
        vin = "";
        placa = "";
        nombremarca = "";
        vinPanel = "";
        marcaPanel = "";
        placaPanel = "";
        listaVehiculo = new ArrayList<>();
        vePropietarioListBusqueda = new ArrayList<>();
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void guardarVehiculo() {
        String mensaje = "", actividad = "";
        Objeto objetoVeInfoA = objetoFacade.findByNombre(Constantes.OBJETO_VEHICULO_INFO_A);
        Vehiculo vAuxiliar = new Vehiculo();
        //vehiculo = new Vehiculo();
        try {
            estatusV = estatusFacade.findByClase(Vehiculo.class.getName(), Constantes.ESTATUS_REGISTRADO);

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
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                vehiculoFacade.edit(vehiculo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, vehiculo.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                if (veInfoA.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_EDITO_INF_ADICIONAL);
                    actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                    vehInfoAFacade.edit(veInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVeInfoA, veInfoA.getId(), actividad);
                } else {
                    veInfoA.setEstatusRow(1);
                    veInfoA.setUsuarioIns(usuarioLogueado.getId());
                    veInfoA.setFechaIns(new Date());
                    vehInfoAFacade.create(veInfoA);
                    if (null != veInfoA.getId()) {
                        actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                        vehiculo.setVehiculoInfoA(veInfoA);
                        vehiculoFacade.edit(vehiculo);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO_INF_ADICIONAL);
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVeInfoA, veInfoA.getId(), actividad);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else {
                vehiculo.setEstatusRow(1);
                vehiculo.setEstatus(estatusV);
                vehiculo.setFechaIns(new Date());
                vehiculo.setUsuarioIns(usuarioLogueado.getId());
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehiculoFacade.create(vehiculo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, vehiculo.getId(), actividad);
                if (vehiculo.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vin = vehiculo.getVin();
                    placa = vehiculo.getPlaca();
                    vinPanel = vehiculo.getVin();
                    placaPanel = vehiculo.getPlaca();
                    marcaPanel = vehiculo.getMarca().getNombre();
                    listaVehiculo = vehiculoFacade.findByVinPlacaList(vin, placa);
                    vePropietarioListBusqueda = vehPropietarioFacade.findByVehiculo(vehiculo);
                    veInfoA.setEstatusRow(1);
                    veInfoA.setUsuarioIns(usuarioLogueado.getId());
                    veInfoA.setFechaIns(new Date());
                    vehInfoAFacade.create(veInfoA);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVeInfoA, veInfoA.getId(), actividad);
                    if (null != veInfoA.getId()) {
                        vehiculo.setVehiculoInfoA(veInfoA);
                        actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                        vehiculoFacade.edit(vehiculo);
                        mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_GUARDO_INF_ADICIONAL);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoV, vehiculo.getId(), actividad);
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
            Objeto objetoVehPropietario = objetoFacade.findByNombre(Constantes.OBJETO_VEH_PROPIETARIO);
            int i = 0;
            for (Propietario p : propietarioListI) {
                vePropietario = vehPropietarioFacade.findByPropietarioVehiculo(p, vehiculo);
                if (vePropietario != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ASIGNAR_PROPIETARIO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                    break;
                }
                vePropietario = new VehPropietario();
                vePropietario.setPropietario(p);
                vePropietario.setVehiculo(vehiculo);
                vePropietario.setFechaIns(new Date());
                vePropietario.setUsuarioIns(usuarioLogueado.getId());
                vePropietario.setEstatusRow(1);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehPropietarioFacade.create(vePropietario);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoVehPropietario, vePropietario.getId(), actividad);
                if (i == propietarioListI.size() - 1) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ASIGNAR_PROPIETARIO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vePropietarioList = vehPropietarioFacade.findByVehiculo(vehiculo);
                    vePropietarioListBusqueda = vehPropietarioFacade.findByVehiculo(vehiculo);
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

            if (!Comun.isNumeric(propietario.getTelefono())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El telefono solo debe contener valores numericos."));
                return;
            }
            propietario.setCncdiridn(0);
            propietario.setCnciasidn(0);
            propietario.setEstatusRow(1);
            propietario.setFechaIns(new Date());
            propietario.setTipoDocumentacion(tipoDocumentacion);
            propietario.setUsuarioIns(usuarioLogueado.getId());
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
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
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_PROPIETARIO_VEH);
            vePropietario = vehPropietarioFacade.find(Integer.valueOf(id));
            vePropietario.setEstatusRow(-1);
            vehPropietarioFacade.edit(vePropietario);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objetoPropietario, propietario.getId(), actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            vePropietarioList = vehPropietarioFacade.findByVehiculo(vehiculo);
        } catch (Exception e) {
            out.println("E/GVN Nuevo vehiculo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
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

    public void onDtVehiculosRowDobleClick(SelectEvent event) {
        if (valoracionAux != null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ya existe una valoracion para el vehículo"));

        } else {

            vehiculo = (Vehiculo) event.getObject();
            vinPanel = vehiculo.getVin();
            placaPanel = vehiculo.getPlaca();
            marcaPanel = vehiculo.getMarca().getNombre();
            vin = vehiculo.getVin();
            placa = vehiculo.getPlaca();
            vePropietarioListBusqueda = vehPropietarioFacade.findByVehiculo(vehiculo);
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

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<Taller> getListaTaller() {
        return listaTaller;
    }

    public void setListaTaller(List<Taller> listaTaller) {
        this.listaTaller = listaTaller;
    }

    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public List<Estatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<Estatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public boolean isSolicitarCredito() {
        return solicitarCredito;
    }

    public void setSolicitarCredito(boolean solicitarCredito) {
        this.solicitarCredito = solicitarCredito;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public DocTipoArchivo getDocTipoArchivo() {
        return docTipoArchivo;
    }

    public void setDocTipoArchivo(DocTipoArchivo docTipoArchivo) {
        this.docTipoArchivo = docTipoArchivo;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public ServidorArchivos getServidorArchivo() {
        return servidorArchivo;
    }

    public void setServidorArchivo(ServidorArchivos servidorArchivo) {
        this.servidorArchivo = servidorArchivo;
    }

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public boolean isValoracionInfoDisabled() {
        return valoracionInfoDisabled;
    }

    public void setValoracionInfoDisabled(boolean valoracionInfoDisabled) {
        this.valoracionInfoDisabled = valoracionInfoDisabled;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }
    //region getters and setters Vehiculo

    //endregion
    public boolean isVehiculoInfoEnabled() {
        return vehiculoInfoEnabled;
    }

    public void setVehiculoInfoEnabled(boolean vehiculoInfoEnabled) {
        this.vehiculoInfoEnabled = vehiculoInfoEnabled;
    }

    public boolean isPropietarioInfoVisible() {
        return propietarioInfoVisible;
    }

    public void setPropietarioInfoVisible(boolean propietarioInfoVisible) {
        this.propietarioInfoVisible = propietarioInfoVisible;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public Objeto getObjetoV() {
        return objetoV;
    }

    public void setObjetoV(Objeto objetoV) {
        this.objetoV = objetoV;
    }

    public VehiculoInfoA getVeInfoA() {
        return veInfoA;
    }

    public void setVeInfoA(VehiculoInfoA veInfoA) {
        this.veInfoA = veInfoA;
    }

    public Estatus getEstatusV() {
        return estatusV;
    }

    public void setEstatusV(Estatus estatusV) {
        this.estatusV = estatusV;
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

    public TipoMarca getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(TipoMarca tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public VehPropietario getVePropietario() {
        return vePropietario;
    }

    public void setVePropietario(VehPropietario vePropietario) {
        this.vePropietario = vePropietario;
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

    public List<Linea> getLineaList() {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList) {
        this.lineaList = lineaList;
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

    public List<VehPropietario> getVePropietarioList() {
        return vePropietarioList;
    }

    public void setVePropietarioList(List<VehPropietario> vePropietarioList) {
        this.vePropietarioList = vePropietarioList;
    }

    public List<TipoDocumentacion> getTipoDocumentacionList() {
        return tipoDocumentacionList;
    }

    public void setTipoDocumentacionList(List<TipoDocumentacion> tipoDocumentacionList) {
        this.tipoDocumentacionList = tipoDocumentacionList;
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

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    public TipoDocumentacion getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public List<VehPropietario> getVePropietarioListBusqueda() {
        return vePropietarioListBusqueda;
    }

    public void setVePropietarioListBusqueda(List<VehPropietario> vePropietarioListBusqueda) {
        this.vePropietarioListBusqueda = vePropietarioListBusqueda;
    }

    public String getNombremarca() {
        return nombremarca;
    }

    public void setNombremarca(String nombremarca) {
        this.nombremarca = nombremarca;
    }

    public String getPlacaPanel() {
        return placaPanel;
    }

    public void setPlacaPanel(String placaPanel) {
        this.placaPanel = placaPanel;
    }

    public String getVinPanel() {
        return vinPanel;
    }

    public void setVinPanel(String vinPanel) {
        this.vinPanel = vinPanel;
    }

    public String getMarcaPanel() {
        return marcaPanel;
    }

    public void setMarcaPanel(String marcaPanel) {
        this.marcaPanel = marcaPanel;
    }

}
