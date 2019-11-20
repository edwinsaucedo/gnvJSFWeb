/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.CreditoEds;
import com.gnv.business.ejb.modelo.CreditoMetaMes;
import com.gnv.business.ejb.modelo.CreditoMetasGen;
import com.gnv.business.ejb.modelo.CreditoPersona;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Eds;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.TipoCredito;
import com.gnv.business.ejb.modelo.TipoDocumentacion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.persistencia.CreditoEdsFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoMetaMesFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoMetasGenFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoPersonaFacadeLocal;
import com.gnv.business.ejb.persistencia.DocTipoArchivoFacadeLocal;
import com.gnv.business.ejb.persistencia.DocumentoFacadeLocal;
import com.gnv.business.ejb.persistencia.EdsFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.FinancieraFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.ServidorArchivosFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoCreditoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocumentacionFacadeLocal;
import com.gnv.business.ejb.persistencia.VehPropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.factories.FileUploadHelper;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.math.BigDecimal;
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
public class NuevoCreditoManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    //Facade
    @EJB
    private CreditoFacadeLocal creditoFacade;

    @EJB
    private DocumentoFacadeLocal documentoFacade;

    @EJB
    private TipoCreditoFacadeLocal tipoCreditoFacade;

    @EJB
    private CreditoEdsFacadeLocal creditoEdsFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private CreditoMetaMesFacadeLocal creditoMetaMesFacade;

    @EJB
    private CreditoMetasGenFacadeLocal creditoMetasGenFacade;

    @EJB
    private CreditoPersonaFacadeLocal creditoPersonaFacade;

    @EJB
    private FinancieraFacadeLocal financieraFacade;

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;

    @EJB
    private TipoDocumentacionFacadeLocal tipoDocumentacionFacade;

    @EJB
    private VehPropietarioFacadeLocal propietarioFacade;

    @EJB
    private TallerFacadeLocal tallerFacade;

    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private DocTipoArchivoFacadeLocal docTipoArchivoFacade;

    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;

    @EJB
    private EdsFacadeLocal edsFacade;

    private List<Vehiculo> vehiculoList;

    private List<Eds> edsList;
    private List<Eds> edsSelectionList;

    private List<Documento> documentoList;
    private List<Estatus> estatusList;
    private List<Taller> tallerList;
    private List<Financiera> financieraList;

    private List<CreditoEds> creditoEdsList;
    private List<TipoDocumentacion> tipoDocumentacionList;
    private List<VehPropietario> propietarioList;
    private List<TipoCredito> tipoCreditoList;
    private Vehiculo vehiculo, vehiculoModal;
    private Credito credito;
    private CreditoPersona creditoPersona;
    private CreditoMetaMes creditoMetaMes;
    private CreditoMetasGen creditoMetasGen;
    private CreditoEds creditoEds;
    private String titulo;
    private String vin;
    private String placa;
    private String vinPanel;
    private String placaPanel;
    private String marcaPanel;
    private String modeloPanel;
    private String nombrePropietario;
    private String apellidoPropietario;

    private String propietarioPanel;
    private String nombreP;
    private String apellidoP;
    private String nombreCompleto;
    private String tipoDocumentacion;
    private Integer tipoRecaudacion;
    private Integer formaPago;
    private boolean metas;
    private String digito;
    private Integer estatusI;

    private Double valorMeta;
    private Integer metaMes;
    private Double valorMensual;
    private Integer tipoMeta;
    private Integer tipoPago;
    private Integer diasAviso;
    private boolean proporcion;
    private boolean bloqueo;
    private boolean estatusActivado;

    private String numeroCredito;

    private VehPropietario vehPropietario;
    private Propietario propietario;
    private Taller taller;
    private Financiera financiera;
    private TipoCredito tipoCredito;
    private Estatus estatus;
    private Objeto objeto;

    private Documento documento;
    private UploadedFile file;
    private String extension;
    private DocTipoArchivo docTipoArchivo;
    private ServidorArchivos servidorArchivos;
    private StreamedContent streamedContent;

    private boolean creditoInfoDisabled = false;
    private boolean seleccion;

    @PostConstruct
    public void init() {
        credito = new Credito();
        creditoPersona = new CreditoPersona();
        creditoMetasGen = new CreditoMetasGen();
        creditoMetaMes = new CreditoMetaMes();
        creditoEds = new CreditoEds();
        vehiculo = new Vehiculo();
        vehPropietario = new VehPropietario();
        vehiculoList = new ArrayList<>();
        financieraList = new ArrayList<>();
        propietarioList = new ArrayList<>();
        tallerList = new ArrayList<>();
        tipoCreditoList = new ArrayList<>();
        estatusList = new ArrayList<>();
        edsList = new ArrayList<>();
        edsSelectionList = new ArrayList<>();
        creditoEdsList = new ArrayList<>();
        Eds edsAux;
        edsAux = new Eds();

        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_c");
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO);
            tipoDocumentacionList = tipoDocumentacionFacade.findByEstatusRow();
            tallerList = tallerFacade.findByEstatusRow();
            financieraList = financieraFacade.findByEstatusRow();
            tipoCreditoList = tipoCreditoFacade.findByEstatusRow();
            estatusList = estatusFacade.findByClaseList(Credito.class.getName());
            edsList = edsFacade.findByEstatusRow();
            vehiculoModal = new Vehiculo();
            if (id != null) {
                titulo = Constantes.CREDITO_EDITAR_TITULO;
                credito = creditoFacade.find(Integer.valueOf(id));
                tipoCredito = credito.getTipoCredito();
                estatusActivado = false;
                creditoPersona = creditoPersonaFacade.findByCredito(Integer.valueOf(id));

                propietario = creditoPersona.getPropietario();
                vehiculo = credito.getVehiculo();
                vin = vehiculo.getVin();
                taller = credito.getTaller();
                placa = vehiculo.getPlaca();
                vinPanel = vehiculo.getVin();
                placaPanel = vehiculo.getPlaca();
                numeroCredito = credito.getNumero();
                nombreCompleto = propietario.getNombreCompleto();
                propietarioPanel = propietario.getNombreCompleto();
                formaPago = credito.getFormaPagoRec();
                tipoRecaudacion = credito.getTipoRecaudo();

                estatusI = credito.getEstadoCredito();
                estatus = estatusFacade.find(estatusI);
                financiera = credito.getFinanciera();
                tipoCredito = credito.getTipoCredito();
                digito = credito.getDigitoV();
                tipoDocumentacion = propietario.getTipoDocumentacion().getNombre();
                marcaPanel = vehiculo.getMarca().getNombre();
                modeloPanel = vehiculo.getModelo().toString();
                nombrePropietario = propietario.getNombre();
                apellidoPropietario = propietario.getApellido();

                if (credito.isMetas()) {

                    creditoMetasGen = creditoMetasGenFacade.findByCredito(Integer.valueOf(id));
                    creditoMetaMes = creditoMetaMesFacade.findByCredito(Integer.valueOf(id));
                    metas = credito.isMetas();
                    if (creditoMetasGen.getId() != null) {
                        tipoMeta = creditoMetasGen.getTipoMeta();
                        tipoPago = creditoMetasGen.getTipoPagos();
                        diasAviso = creditoMetasGen.getDiasAviso();
                        proporcion = creditoMetasGen.getProporcionPrimerMes();
                        bloqueo = creditoMetasGen.getBloqueo();
                        valorMeta = creditoMetasGen.getValorMeta().doubleValue();

                    }
                    if (creditoMetasGen == null) {
                        creditoMetasGen = new CreditoMetasGen();
                    }
                    if (creditoMetaMes == null) {
                        creditoMetaMes = new CreditoMetaMes();
                    }
                    if (creditoMetaMes.getId() != null) {
                        valorMensual = creditoMetaMes.getValor().doubleValue();
                        metaMes = creditoMetaMes.getMes();

                    }
                } else {
                    creditoMetasGen = new CreditoMetasGen();
                    creditoMetaMes = new CreditoMetaMes();
                }

            } else {
                titulo = Constantes.CREDITO_NUEVO_TITULO;
                estatusActivado = true;
                estatus = estatusFacade.findByClase(Credito.class.getName(), Constantes.ESTATUS_ACTIVO);
                estatusI = estatus.getId();

            }

            if (credito.getId() != null && objeto != null) {
                documentoList = documentoFacade.findByObjeto(objeto, credito.getId());
            }

            creditoEdsList = creditoEdsFacade.findByCredito(credito);

            for (int i = 0; i < creditoEdsList.size(); i++) {
                if (creditoEdsList.size() > 0) {
                    edsAux = creditoEdsList.get(i).getEds();
                    edsSelectionList.add(edsAux);

                }
            }
        } catch (Exception e) {

        }
    }

    public void buscarVehiculo() {
        try {
            vehiculoList = vehiculoFacade.findByCredit(vin, placa);
            //vehiculo = new Vehiculo();
        } catch (Exception e) {

        }
    }

    public void onItPlacaKeyUp() {
        try {
            if (null != vehiculo) {
                vehiculoList = vehiculoFacade.findByCreditPlaca(placa);

                if (placa.isEmpty()) {
                    vehiculoList = vehiculoFacade.findByCreditVin(vin);
                }
            } else {
                vehiculoList = new ArrayList<>();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoVehiculo: " + e.getMessage());
        }
    }

    public void onItPropietarioKeyUp() {
        try {
            if (null != vehiculo) {
                propietarioList = propietarioFacade.findByPropietario(vehiculo.getId(), nombreCompleto);

            } else {
                propietarioList = new ArrayList<>();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoCredito: " + e.getMessage());
        }
    }

    public void onItPlacaChange() {
        try {

            vehiculo = vehiculoFacade.findByCreditoValoracionVinPlaca(vin, placa);

            if (vehiculo != null) {
                vinPanel = vehiculo.getVin();
                placaPanel = vehiculo.getPlaca();
                marcaPanel = vehiculo.getMarca().getNombre();
                vin = vehiculo.getVin();
                placa = vehiculo.getPlaca();
                modeloPanel = vehiculo.getModelo().toString();
                if (vehiculo != null) {
                    vin = "";
                    placa = "";
                }

            } else {

                vehiculo = new Vehiculo();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Placa de vehiculo no valida."));
            }

        } catch (Exception e) {

            vehiculo = new Vehiculo();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error."));
            out.println("E/GVN NuevaConversion: " + e.getMessage());
        }
    }

    public void buscarPropietario() {
        try {

            if (vehiculo.getId() != null) {

                vehPropietario = propietarioFacade.findByPropietarioVehiculo(vehiculo.getId(), nombrePropietario, apellidoPropietario);
                if (vehPropietario != null) {
                    propietarioPanel = vehPropietario.getPropietario().getNombreCompleto();
                    propietario = vehPropietario.getPropietario();
                    nombreCompleto = vehPropietario.getPropietario().getNombreCompleto();
                    tipoDocumentacion = vehPropietario.getPropietario().getTipoDocumentacion().getNombre();
                } else {
                    vehPropietario = new VehPropietario();

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Nombre de propietario no válido."));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe seleccionar un vehículo primero."));
            }
        } catch (Exception e) {

        }
    }

    public void abrirBusquedaPropietario() {
        try {

            if (vehiculo.getId() != null) {

                PrimeFaces.current().executeScript("PF('dlgConsultarPropietario').show()");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe seleccionar un vehículo primero."));
            }
        } catch (Exception e) {

        }
    }

    public void onDtVehiculosRowDobleClick(SelectEvent event) {

        vehiculo = (Vehiculo) event.getObject();
        vinPanel = vehiculo.getVin();
        placaPanel = vehiculo.getPlaca();
        marcaPanel = vehiculo.getMarca().getNombre();
        vin = vehiculo.getVin();
        placa = vehiculo.getPlaca();
        modeloPanel = vehiculo.getModelo().toString();

    }

    public void onDtPropietarioRowDobleClick(SelectEvent event) {

        vehPropietario = (VehPropietario) event.getObject();
        propietarioPanel = vehPropietario.getPropietario().getNombreCompleto();
        propietario = vehPropietario.getPropietario();
        nombreCompleto = vehPropietario.getPropietario().getNombreCompleto();
        tipoDocumentacion = vehPropietario.getPropietario().getTipoDocumentacion().getNombre();

    }

    public void guardarCredito() {
        String mensaje = "", actividad = "";
        try {
            if (vehiculo.getId() == null) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe asociar un vehículo"));
                return;
            }
            if (propietario == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe asociar un propietario"));
                return;
            }
            if (digito.length() < 10) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe especificar 10 dígitos para dígito verificador"));
                return;
            }
            if (numeroCredito.length() < 10) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe especificar 10 dígitos para numero de crédito"));
                return;
            }
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO);
            credito.setEstadoCredito(estatus.getId());
            credito.setFinanciera(financiera);
            credito.setTaller(taller);

            credito.setTipoCredito(tipoCredito);
            credito.setVehiculo(vehiculo);
            credito.setNumero(numeroCredito);
            credito.setDigitoV(digito);
            credito.setFormaPagoRec(formaPago);
            credito.setTipoRecaudo(tipoRecaudacion);

            if (credito.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                creditoFacade.edit(credito);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, credito.getId(), actividad);
                if (creditoPersona.getId() != null) {
                    creditoPersona.setPropietario(propietario);
                    creditoPersonaFacade.edit(creditoPersona);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Se editó correctamente"));
                }

            } else {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_GUARDO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                credito.setEstatusRow(1);
                credito.setFechaIns(new Date());
                credito.setUsuariosIns(usuarioLogueado.getId());
                creditoFacade.create(credito);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, credito.getId(), actividad);
                if (credito.getId() != null) {
                    creditoInfoDisabled = true;

                    creditoPersona.setCredito(credito);
                    creditoPersona.setPropietario(propietario);
                    creditoPersona.setEstatusRow(1);
                    creditoPersonaFacade.create(creditoPersona);

                    if (creditoPersona.getId() != null) {

                        creditoPersona = new CreditoPersona();
                        propietario = new Propietario();
                        vehiculo = new Vehiculo();

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Crédito asociado correctamente"));
                    }
                    //mensaje = Propiedades.getMensaje(Constantes.PROCESO_CREDITO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Se guardo correctamente"));

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.PROCESO_VALORACION_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoCredito: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Constantes.CATALOGO_ERROR_GENERAL));
        }

    }

    public void guardarMetas() {
        String mensaje = "";
        try {
            if (credito.getId() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe registrar un crédito antes"));
                return;
            }
            credito = creditoFacade.find(credito.getId());
            credito.setMetas(metas);
            creditoFacade.edit(credito);

            creditoMetasGen.setCredito(credito);
            creditoMetasGen.setTipoMeta(tipoMeta);
            creditoMetasGen.setTipoPagos(tipoPago);
            creditoMetasGen.setBloqueo(bloqueo);
            creditoMetasGen.setDiasAviso(diasAviso);
            creditoMetasGen.setProporcionPrimerMes(proporcion);
            creditoMetasGen.setValorMeta(BigDecimal.valueOf(valorMeta));

            if (creditoMetasGen.getId() != null) {
                creditoMetasGenFacade.edit(creditoMetasGen);
            } else {
                creditoMetasGen.setUsuariosIns(usuarioLogueado.getId());
                creditoMetasGen.setFechaIns(new Date());
                creditoMetasGen.setEstatusRow(1);
                creditoMetasGenFacade.create(creditoMetasGen);
                if (creditoMetasGen.getId() != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Meta guardada"));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoCredito: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Constantes.CATALOGO_ERROR_GENERAL));
        }
    }

    public void guardarMetaMes() {
        String mensaje = "";
        try {
            if (credito.getId() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe registrar un crédito antes"));
                return;
            }
            credito = creditoFacade.find(credito.getId());
            credito.setMetas(metas);
            creditoFacade.edit(credito);

            creditoMetaMes.setCredito(credito);
            creditoMetaMes.setMes(metaMes);
            creditoMetaMes.setValor(BigDecimal.valueOf(valorMensual));

            if (creditoMetaMes.getId() != null) {
                creditoMetaMesFacade.edit(creditoMetaMes);
            } else {
                creditoMetaMes.setUsuariosIns(usuarioLogueado.getId());
                creditoMetaMes.setFechaIns(new Date());
                creditoMetaMes.setEstatusRow(1);
                creditoMetaMesFacade.create(creditoMetaMes);
                if (creditoMetaMes.getId() != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Meta guardada"));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoCredito: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Constantes.CATALOGO_ERROR_GENERAL));
        }
    }

    public void guardarDocumentos(FileUploadEvent fileUploadEvent) {
        String tokens[], ruta;

        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            if (credito.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REQUIRED_CREDITO_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {
                documento = new Documento();
                file = fileUploadEvent.getFile();
                ruta = objeto.getNombre() + "/" + credito.getId() + "/";
                //servidorArchivos  = seervidorArchivosFacade.findAll.get(0);

                tokens = file.getFileName().split("\\.(?=[^\\.]+$)");

                //  documento.setTipoArchivo(tipoArchivo);
                extension = FilenameUtils.getExtension(file.getFileName());
                docTipoArchivo = docTipoArchivoFacade.findByExtension(extension);
                if (docTipoArchivo == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Tipo de archivo no valido."));
                    return;
                }
                servidorArchivos = servidorArchivosFacade.findAll().get(0);

                documento.setNombre(tokens[0]);
                documento.setCompania(usuarioLogueado.getCompania());
                documento.setTamanio(String.valueOf(file.getSize()) + " Kb");
                documento.setTipoArchivo(docTipoArchivo);
                documento.setEstatusRow(1);
                documento.setObjeto(objeto);
                documento.setFechaIns(new Date());
                documento.setUsuarioIns(usuarioLogueado.getId());
                documento.setRuta(ruta);

                documento.setIdRegistro(credito.getId());
                documentoFacade.create(documento);

            }
            if (documento.getId() != null) {
                if (FileUploadHelper.cargarArchivos(file, servidorArchivos, ruta)) {
                    documentoList = documentoFacade.findByObjeto(objeto, credito.getId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Documento cargado exitosamente."));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, documento.getId(), actividad);
                } else {
                    documentoFacade.remove(documento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al cargar el documento."));
                }
            }

        } catch (Exception e) {
            out.println("E/GVN guardarDocumentos: " + e.getMessage());
        }

    }

    public void descargarDocumento(String idDocumento) {
        String ruta, nombreArchivo;
        try {
            servidorArchivos = servidorArchivosFacade.findAll().get(0);
            StreamedContent documentoContent;
            Documento documentoD = documentoFacade.find(Integer.valueOf(idDocumento));
            if (documentoD != null && servidorArchivos != null) {
                ruta = documentoD.getRuta();
                nombreArchivo = documentoD.getNombre() + "." + documentoD.getTipoArchivo().getExtension();
                documentoContent = FileUploadHelper.descargarArchivo(nombreArchivo, documentoD.getTipoArchivo().getExtension(), servidorArchivos, ruta);
                if (documentoContent == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
                    return;
                }
                this.setStreamedContent(documentoContent);
            }
        } catch (Exception e) {
            out.println("E/GVN Nuevo Crédito: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
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
            if (credito.getId() != null) {
                documentoList = documentoFacade.findByObjeto(objeto, credito.getId());
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, documento.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaDocumento: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public void asociarEds() {
        try {
            if (credito.getId() != null) {
                Eds idE;
                CreditoEds creditoEdsAux;
                creditoEdsAux = new CreditoEds();
                CreditoEds creditoEdsAuxB;
                creditoEdsAuxB = new CreditoEds();
                //edsSelectionList = new ArrayList<>();

                if (edsSelectionList.isEmpty()) {
                    for (int o = 0; o < creditoEdsList.size(); o++) {
                        creditoEdsAuxB = creditoEdsFacade.find(creditoEdsList.get(o).getId());
                        creditoEdsAuxB.setEstatusRow(-1);
                        creditoEdsFacade.edit(creditoEdsAuxB);
                    }
                } else {
                    for (int i = 0; i < edsSelectionList.size(); i++) {
                        Integer idSeleccion, idBusqueda;
                        creditoEdsAux = creditoEdsFacade.findByCreditoEds(credito.getId(), edsSelectionList.get(i).getId());

                        if (creditoEdsAux == null) {

                            idE = edsSelectionList.get(i);
                            creditoEds.setCredito(credito);
                            creditoEds.setEstatusRow(1);
                            creditoEds.setEds(idE);
                            creditoEdsFacade.create(creditoEds);

                        } else {
                            for (int o = 0; o < creditoEdsList.size(); o++) {
                                idBusqueda = creditoEdsList.get(o).getEds().getId();
                                idSeleccion = edsSelectionList.get(i).getId();

                                if (!idSeleccion.equals(idBusqueda)) {
                                    creditoEdsAuxB = creditoEdsFacade.find(creditoEdsList.get(o).getId());
                                    creditoEdsAuxB.setEstatusRow(-1);
                                    creditoEdsFacade.edit(creditoEdsAuxB);
                                }

                            }
                        }
                    }
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Debe guardar un crédito antes."));
            }
            PrimeFaces.current().executeScript("PF('dlgEds').hide()");
        } catch (Exception e) {
            out.println("E/GVN NuevoCredito: " + e.getMessage());
        }
    }

    public NuevoCreditoManagedBean() {
    }

    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getVinPanel() {
        return vinPanel;
    }

    public void setVinPanel(String vinPanel) {
        this.vinPanel = vinPanel;
    }

    public String getPlacaPanel() {
        return placaPanel;
    }

    public void setPlacaPanel(String placaPanel) {
        this.placaPanel = placaPanel;
    }

    public String getMarcaPanel() {
        return marcaPanel;
    }

    public void setMarcaPanel(String marcaPanel) {
        this.marcaPanel = marcaPanel;
    }

    public List<TipoDocumentacion> getTipoDocumentacionList() {
        return tipoDocumentacionList;
    }

    public void setTipoDocumentacionList(List<TipoDocumentacion> tipoDocumentacionList) {
        this.tipoDocumentacionList = tipoDocumentacionList;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public VehPropietario getVehPropietario() {
        return vehPropietario;
    }

    public void setVehPropietario(VehPropietario vehPropietario) {
        this.vehPropietario = vehPropietario;
    }

    public String getPropietarioPanel() {
        return propietarioPanel;
    }

    public void setPropietarioPanel(String propietarioPanel) {
        this.propietarioPanel = propietarioPanel;
    }

    public List<VehPropietario> getPropietarioList() {
        return propietarioList;
    }

    public void setPropietarioList(List<VehPropietario> propietarioList) {
        this.propietarioList = propietarioList;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Taller> getTallerList() {
        return tallerList;
    }

    public void setTallerList(List<Taller> tallerList) {
        this.tallerList = tallerList;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public List<TipoCredito> getTipoCreditoList() {
        return tipoCreditoList;
    }

    public void setTipoCreditoList(List<TipoCredito> tipoCreditoList) {
        this.tipoCreditoList = tipoCreditoList;
    }

    public TipoCredito getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(TipoCredito tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public List<Estatus> getEstatusList() {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList) {
        this.estatusList = estatusList;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Integer getTipoRecaudacion() {
        return tipoRecaudacion;
    }

    public void setTipoRecaudacion(Integer tipoRecaudacion) {
        this.tipoRecaudacion = tipoRecaudacion;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public List<Financiera> getFinancieraList() {
        return financieraList;
    }

    public void setFinancieraList(List<Financiera> financieraList) {
        this.financieraList = financieraList;
    }

    public Financiera getFinanciera() {
        return financiera;
    }

    public void setFinanciera(Financiera financiera) {
        this.financiera = financiera;
    }

    public boolean isMetas() {
        return metas;
    }

    public void setMetas(boolean metas) {
        this.metas = metas;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public CreditoMetaMes getCreditoMetaMes() {
        return creditoMetaMes;
    }

    public void setCreditoMetaMes(CreditoMetaMes creditoMetaMes) {
        this.creditoMetaMes = creditoMetaMes;
    }

    public CreditoMetasGen getCreditoMetasGen() {
        return creditoMetasGen;
    }

    public void setCreditoMetasGen(CreditoMetasGen creditoMetasGen) {
        this.creditoMetasGen = creditoMetasGen;
    }

    public CreditoEds getCreditoEds() {
        return creditoEds;
    }

    public void setCreditoEds(CreditoEds creditoEds) {
        this.creditoEds = creditoEds;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Integer getMetaMes() {
        return metaMes;
    }

    public void setMetaMes(Integer metaMes) {
        this.metaMes = metaMes;
    }

    public Double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(Double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public Integer getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(Integer tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    public Integer getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Integer getDiasAviso() {
        return diasAviso;
    }

    public void setDiasAviso(Integer diasAviso) {
        this.diasAviso = diasAviso;
    }

    public boolean isProporcion() {
        return proporcion;
    }

    public void setProporcion(boolean proporcion) {
        this.proporcion = proporcion;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public boolean isCreditoInfoDisabled() {
        return creditoInfoDisabled;
    }

    public void setCreditoInfoDisabled(boolean creditoInfoDisabled) {
        this.creditoInfoDisabled = creditoInfoDisabled;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Eds> getEdsList() {
        return edsList;
    }

    public void setEdsList(List<Eds> edsList) {
        this.edsList = edsList;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public List<Eds> getEdsSelectionList() {
        return edsSelectionList;
    }

    public void setEdsSelectionList(List<Eds> edsSelectionList) {

        this.edsSelectionList = edsSelectionList;
    }

    public Vehiculo getVehiculoModal() {
        return vehiculoModal;
    }

    public void setVehiculoModal(Vehiculo vehiculoModal) {
        this.vehiculoModal = vehiculoModal;
    }

    public String getModeloPanel() {
        return modeloPanel;
    }

    public void setModeloPanel(String modeloPanel) {
        this.modeloPanel = modeloPanel;
    }

    public String getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(String tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public boolean isEstatusActivado() {
        return estatusActivado;
    }

    public void setEstatusActivado(boolean estatusActivado) {
        this.estatusActivado = estatusActivado;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getApellidoPropietario() {
        return apellidoPropietario;
    }

    public void setApellidoPropietario(String apellidoPropietario) {
        this.apellidoPropietario = apellidoPropietario;
    }

}
