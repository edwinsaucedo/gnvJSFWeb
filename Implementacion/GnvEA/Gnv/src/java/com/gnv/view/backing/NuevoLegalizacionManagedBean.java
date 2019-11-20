/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Documento;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Legalizacion;
import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.business.ejb.modelo.TipoCilindro;
import com.gnv.business.ejb.modelo.TipoDocLegal;
import com.gnv.business.ejb.modelo.TipoLegalizacion;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;

import com.gnv.business.ejb.persistencia.CilindroFacadeLocal;
import com.gnv.business.ejb.persistencia.DocTipoArchivoFacadeLocal;
import com.gnv.business.ejb.persistencia.DocumentoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.FabricanteFacadeLocal;
import com.gnv.business.ejb.persistencia.KitFacadeLocal;
import com.gnv.business.ejb.persistencia.LegalizacionFacadeLocal;
import com.gnv.business.ejb.persistencia.LineaFacadeLocal;
import com.gnv.business.ejb.persistencia.MarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.ServidorArchivosFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoCilindroFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoDocLegalFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoLegalizacionFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.factories.FileUploadHelper;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.text.Format;
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
import javax.swing.text.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevoLegalizacionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private DocTipoArchivo docTipoArchivo;

    @EJB
    private DocumentoFacadeLocal documentoFacade;

    @EJB
    private LegalizacionFacadeLocal legalizacionFacade;

    @EJB
    private TipoDocLegalFacadeLocal tipoDocLegalFacade;

    @EJB
    private TipoLegalizacionFacadeLocal tipoLegalizacionFacade;

    @EJB
    private TipoCilindroFacadeLocal tipoCilindroFacade;
    @EJB
    private MarcaFacadeLocal marcaFacade;
    @EJB
    private LineaFacadeLocal lineaFacade;
    @EJB
    private FabricanteFacadeLocal fabricanteFacade;
    @EJB
    private CilindroFacadeLocal cilindroFacade;
    @EJB
    private KitFacadeLocal kitFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private DocTipoArchivoFacadeLocal docTipoArchivoFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;

    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    private String publicoString = "No";
    private TipoMarca tipoMarcaKits;
    private TipoMarca tipoMarcaCilindros;
    private ServidorArchivos servidorArchivo;
    private String legalizacionString = "Numero de legalización: ";
    private Boolean legalizacionInfoDisabled = false;
    private Objeto objeto;
    private List<Documento> listaDocumento;
    private List<Cilindro> listaCilindros;
    private List<Kit> listaKit;

    private StreamedContent streamedContent;

    private Legalizacion legalizacion;
    private TipoDocLegal tipoDocLegal;
    private TipoLegalizacion tipoLegalizacion;
    private List<TipoDocLegal> listaTipoDocLegal;
    private List<TipoLegalizacion> listaTipoLeg;
    private List<TipoCilindro> tipoCilindroList;
    private List<Marca> marcaListCilindros;
    private List<Marca> marcaListKits;
    private List<Linea> lineaList;
    private List<Fabricante> fabricanteList;
    private TipoCilindro tipoCilindro;
    private Marca marca;
    private Linea linea;
    private Fabricante fabricante;
    private Cilindro cilindro;
    private Kit kit;
    private Kit kitAux;
    private Cilindro cilindroAux;
    private Estatus estatusFabricanteActivos;
    private Estatus estatusNoAsociadoKit;
    private Estatus estatusNoAsociadoCil;

    private String titulo;
    private boolean tipo;
    private int año, mes, dia;
   
   private UploadedFile file;
    private Documento documento;
    private String extension;
    private boolean publico;
    private String serialKit;
    private String serialCilindro;
    String serialCilindroAux;
    String serialKitAux;

    @PostConstruct
    public void init() {
        publico = false;
        estatusFabricanteActivos = estatusFacade.findByClase(Fabricante.class.getName(), Constantes.ESTATUS_ACTIVO);
        estatusNoAsociadoKit = estatusFacade.findByClase(Kit.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
        estatusNoAsociadoCil = estatusFacade.findByClase(Cilindro.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
        String id="";
        try {

            tipo = false;
            id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            listaTipoDocLegal = tipoDocLegalFacade.findByEstatusRow();
            listaTipoLeg = tipoLegalizacionFacade.findByEstatusRow();
            tipoMarcaCilindros = tipoMarcaFacade.findByTipoMarcaNombre("Cilindros");
            tipoMarcaKits = tipoMarcaFacade.findByTipoMarcaNombre("Kits");
            tipoCilindroList = tipoCilindroFacade.findByEstatusRow();
            marcaListCilindros = marcaFacade.findByTipoMarca(tipoMarcaCilindros);
            marcaListKits = marcaFacade.findByTipoMarca(tipoMarcaKits);
            lineaList = lineaFacade.findLineasByMarca(marca);
            fabricanteList = fabricanteFacade.findByEstatusRow(estatusFabricanteActivos);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            cilindro = new Cilindro();
            kit = new Kit();
            tipoCilindro = new TipoCilindro();

            linea = new Linea();
            fabricante = new Fabricante();

            if (id != null) {
                legalizacion = legalizacionFacade.find(Integer.valueOf(id));
                titulo = Constantes.LEGALIZACION_EDITAR_TITULO;
                tipoDocLegal = legalizacion.getTipoDocumento();

                tipoLegalizacion = legalizacion.getTipoLegalizacion();
            } else {
                legalizacion = new Legalizacion();
                tipoDocLegal = new TipoDocLegal();
                tipoLegalizacion = new TipoLegalizacion();
                titulo = Constantes.LEGALIZACION_NUEVO_TITULO;
                marca = new Marca();
                lineaList = new ArrayList<>();
            }
            listaKit = kitFacade.findByEstatusRow(legalizacion);
            listaCilindros = cilindroFacade.findByEstatusRow(legalizacion);

            if (legalizacion.getId() != null && objeto != null) {
                listaDocumento = documentoFacade.findByObjeto(objeto, legalizacion.getId());
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoLegalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public void clearObjects() {

        if (legalizacion.getId() != null) {
            listaDocumento = documentoFacade.findByObjeto(objeto, legalizacion.getId());
        }

        listaTipoDocLegal = tipoDocLegalFacade.findByEstatusRow();
        listaTipoLeg = tipoLegalizacionFacade.findByEstatusRow();
        listaCilindros = cilindroFacade.findByEstatusRow(legalizacion);
        listaKit = kitFacade.findByEstatusRow(legalizacion);
        tipoCilindroList = tipoCilindroFacade.findByEstatusRow();
        marcaListCilindros = marcaFacade.findByTipoMarca(tipoMarcaCilindros);
        marcaListKits = marcaFacade.findByTipoMarca(tipoMarcaKits);
        lineaList = lineaFacade.findLineasByMarca(marca);
        fabricanteList = fabricanteFacade.findByEstatusRow(estatusFabricanteActivos);
        cilindro = new Cilindro();
        kit = new Kit();
        tipoCilindro = new TipoCilindro();
        marca = new Marca();
        linea = new Linea();
        fabricante = new Fabricante();
        estatusNoAsociadoKit = estatusFacade.findByClase(Kit.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
        estatusNoAsociadoCil = estatusFacade.findByClase(Cilindro.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
    }

    public void guardar() {
          String mensaje = "", actividad = "";
        try {
            int countLegal = legalizacionFacade.count();
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            legalizacion.setEstatusRow(1);
            legalizacion.setTipoDocumento(tipoDocLegal);
            legalizacion.setTipoLegalizacion(tipoLegalizacion);
            if (legalizacion.getId() != null) {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LEGALIZACION_NUEVO_EDITO);
                legalizacionFacade.edit(legalizacion);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,legalizacion.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                legalizacion.setUsuarioIns(usuarioLogueado.getId());
                legalizacion.setFechaIns(getDateTime());
                legalizacion.setUsuarioIns(1);
                legalizacion.setCncdiridn((long) 0);
                legalizacion.setNumeroLegal(String.valueOf(getDateString() + countLegal));
                legalizacionFacade.create(legalizacion);
                 BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,legalizacion.getId(), actividad);
                //  init();
                if (legalizacion.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LEGALIZACION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    //legalizacion = new Legalizacion();
                    legalizacionInfoDisabled = true;

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LEGALIZACION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                }
               

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoLegalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
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

    public String getDateString() {
        java.util.Date utilDate = new java.util.Date();
        Date date;
        String dateFinally;
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(utilDate);
        SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyHHmm");
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long time = cal.getTimeInMillis();
        java.sql.Timestamp sqlTimestamp = new Timestamp(time);
        date = sqlTimestamp;
        dateFinally = formateador.format(date);
        return dateFinally;

    }

    public String setFechaActual() {//obtener fecha actual
        final Calendar c = Calendar.getInstance();

        año = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(c.getTime());
        return s;
    }

    public boolean onTipoLegalizacionOnChange() {
        try {
            String tipoLegal = tipoLegalizacion.getNombre();
            if (tipoLegal.equals("Vehículo")) {
                tipo = true;

            } else if (tipoLegal == null || tipoLegal.isEmpty()) {
                tipo = false;
            } else {
                tipo = false;
                legalizacion.setNumeroMotor("");
                legalizacion.setInformacionRegulador("");
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoLegalizacion: " + e.getMessage());
            tipo = false;
        }
        return tipo;

    }

    public void onMarcaChage() {
        try {
            if (marca != null) {
                lineaList = lineaFacade.findLineasByMarca(marca);
            } else {
                lineaList = new ArrayList<>();
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoLegalizacion: " + e.getMessage());
        }
    }

    //Metodo guarda unicamente en Cilindro
    public void guardarCilindro() {
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CILINDRO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            if (legalizacion.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_REQUIRED_LEG_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {

                if (estatusNoAsociadoCil != null) {
                    cilindro.setEstatus(estatusNoAsociadoCil);
                }
                cilindro.setFechaIns(new Date());
                //cilindro.setUsuarioIns();
                cilindro.setEstatusRow(1);
                cilindro.setLegalizacion(legalizacion);
                cilindro.setTipoCilindro(tipoCilindro);
                cilindro.setUsuarioIns(usuarioLogueado.getId());
                cilindro.setMarca(marca);
                cilindro.setLinea(linea);
                cilindro.setSerial(serialCilindro);
                cilindroAux = cilindroFacade.findCilindroBySerial(cilindro.getSerial());
                if (cilindroAux != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El de serie ya se encuentra registrado."));
                    return;
                }

                cilindroFacade.create(cilindro);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,cilindro.getId(), actividad);
                cilindro.setFabricante(fabricante);
            }

            clearObjects();
            if (cilindro.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CILINDRO_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                cilindro = new Cilindro();
                marca = new Marca();
                tipoCilindro = new TipoCilindro();
                linea = new Linea();
                fabricante = new Fabricante();
            } else {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CILINDRO_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoCilindro: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void editarCilindro() {
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CILINDRO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
            if (cilindro.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CILINDRO_NUEVO_EDITO);
                cilindro.setMarca(marca);
                cilindro.setLinea(linea);
                cilindro.setSerial(serialCilindro);
                cilindroAux = cilindroFacade.findCilindroBySerial(cilindro.getSerial());

                if (cilindroAux != null & (cilindro.getId() == null || !cilindro.getSerial().equals(serialCilindroAux))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El número de serie ya se encuentra registrado.."));
                    return;
                }
                cilindroFacade.edit(cilindro);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,cilindro.getId(), actividad);
                clearObjects();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                PrimeFaces.current().executeScript("PF('dlgCil').hide()");
            }
        } catch (Exception e) {
            {
                out.println("E/GVN NuevoLegalizacion: " + e.getMessage());
            }

        }
    }

    //Carga los datos de cilindro en el modal
    public void showDataCilindro() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCil");
            linea = new Linea();

            if (id != null) {
                cilindro = cilindroFacade.find(Integer.valueOf(id));
                serialCilindroAux = cilindro.getSerial();
                marca = cilindro.getMarca();
                serialCilindro = cilindro.getSerial();
                fabricante = cilindro.getFabricante();
                linea = cilindro.getLinea();
                tipoCilindro = cilindro.getTipoCilindro();

            }

            if (marca != null) {
                lineaList = lineaFacade.findLineasByMarca(marca);
            } else {
                lineaList = new ArrayList<>();
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
        PrimeFaces.current().executeScript("PF('dlgCil').show()");
    }

    public void eliminarCilindro() {
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CILINDRO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCil");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CILINDRO_LISTA_ELIMINO);
            cilindro = cilindroFacade.find(Integer.valueOf(id));
            cilindro.setEstatusRow(-1);
            cilindroFacade.edit(cilindro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            clearObjects();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaKit: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void guardarKit() {
        String mensaje = "", actividad = "";

        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_KIT);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            if (legalizacion.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_REQUIRED_LEG_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {
                if (estatusNoAsociadoKit != null) {
                    kit.setEstatus(estatusNoAsociadoKit);
                }

                kit.setLegalizacion(legalizacion);
                kit.setEstatusRow(1);
                kit.setMarca(marca);
                kit.setLinea(linea);
                kit.setSerial(serialKit);
                kit.setUsuarioIns(usuarioLogueado.getId());
                kitAux = kitFacade.findKitBySerial(kit.getSerial());
                if (kitAux != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El número de serie ya se encuentra registrado."));
                    return;
                }

                kitFacade.create(kit);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,kit.getId(), actividad);
                kit.setFabricante(fabricante);

                //Cabiar por id del usuario logueado
            }
            clearObjects();
            if (kit.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_KIT_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                kit = new Kit();
                marca = new Marca();
                linea = new Linea();
                fabricante = new Fabricante();
            } else {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_KIT_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            }

        } catch (Exception e) {

            out.println("E/GVN NuevoFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public void editarKit() {
        if (kit.getId() != null) {
            //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
            String mensaje = "", actividad = "";
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_KIT_NUEVO_EDITO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_KIT);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
            // kit.setLegalizacion(legalizacion);
            kit.setMarca(marca);
            kit.setLinea(linea);
            kit.setSerial(serialKit);
            kitAux = kitFacade.findKitBySerial(kit.getSerial());
            
            if (kitAux != null & (kit.getId() == null || !kit.getSerial().equals(serialKitAux))) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "El número de serie ya se encuentra registrado."));
                return;
            }
            kitFacade.edit(kit);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,kit.getId(), actividad);
            clearObjects();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            PrimeFaces.current().executeScript("PF('dlgKit').hide()");
        }
    }

    //Muestra datos de Kit en el modal para editar
    public void showDataKit() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idKit");
            linea = new Linea();

            if (id != null) {
                kit = kitFacade.find(Integer.valueOf(id));
                marca = kit.getMarca();
                serialKit = kit.getSerial();
                linea = kit.getLinea();
                serialKitAux = kit.getSerial();
                fabricante = kit.getFabricante();
            }

            if (marca != null) {
                lineaList = lineaFacade.findLineasByMarca(marca);
            } else {
                lineaList = new ArrayList<>();
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoFabricante: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
        PrimeFaces.current().executeScript("PF('dlgKit').show()");
    }

    public void eliminarKit() {
        String mensaje = "", actividad = "";
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_del");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_KIT_LISTA_ELIMINO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_KIT);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            kit = kitFacade.find(Integer.valueOf(id));
            kit.setEstatusRow(-1);
            kitFacade.edit(kit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            clearObjects();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaKit: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void guardarDocumentos(FileUploadEvent fileUploadEvent) {
        String tokens[], ruta;

        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            if (legalizacion.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_REQUIRED_LEG_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {
                documento = new Documento();
                file = fileUploadEvent.getFile();
                ruta = objeto.getNombre() + "/" + legalizacion.getId() + "/";
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
                documento.setIdRegistro(legalizacion.getId());
                documentoFacade.create(documento);
                clearObjects();
            }
            if (documento.getId() != null) {
                if (FileUploadHelper.cargarArchivos(file, servidorArchivo, ruta)) {
                    listaDocumento = documentoFacade.findByObjeto(objeto, legalizacion.getId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Documento cargado exitosamente."));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,documento.getId(), actividad);
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
        String mensaje = "", actividad = "";
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDoc");
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_DOCUMENTO);
            documento = documentoFacade.find(Integer.valueOf(id));
            documento.setEstatusRow(-1);
            documentoFacade.edit(documento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            clearObjects();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
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
            out.println("E/GVN Nuevo Legalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
        }

    }

    public void showDataDocument() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDoc");

            if (id != null) {
                documento = documentoFacade.find(Integer.valueOf(id));
                docTipoArchivo = documento.getTipoArchivo();
            }

        } catch (Exception e) {
            out.println("E/GVN showDataDocuments: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
        PrimeFaces.current().executeScript("PF('dlgDoc').show()");
    }

    public void closeModalDoc() {

        PrimeFaces.current().executeScript("PF('dlgDoc').hide");
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

    public boolean isTipo() {
        return onTipoLegalizacionOnChange();

    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public NuevoLegalizacionManagedBean() {
    }

    public Legalizacion getLegalizacion() {
        return legalizacion;
    }

    public void setLegalizacion(Legalizacion legalizacion) {
        this.legalizacion = legalizacion;
    }

    public TipoDocLegal getTipoDocLegal() {
        return tipoDocLegal;
    }

    public void setTipoDocLegal(TipoDocLegal tipoDocLegal) {
        this.tipoDocLegal = tipoDocLegal;
    }

    public TipoLegalizacion getTipoLegalizacion() {
        return tipoLegalizacion;
    }

    public void setTipoLegalizacion(TipoLegalizacion tipoLegalizacion) {
        this.tipoLegalizacion = tipoLegalizacion;
    }

    public List<TipoDocLegal> getListaTipoDocLegal() {
        return listaTipoDocLegal;
    }

    public void setListaTipoDocLegal(List<TipoDocLegal> listaTipoDocLegal) {
        this.listaTipoDocLegal = listaTipoDocLegal;
    }

    public List<TipoLegalizacion> getListaTipoLeg() {
        return listaTipoLeg;
    }

    public void setListaTipoLeg(List<TipoLegalizacion> listaTipoLeg) {
        this.listaTipoLeg = listaTipoLeg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<TipoCilindro> getTipoCilindroList() {
        return tipoCilindroList;
    }

    public void setTipoCilindroList(List<TipoCilindro> tipoCilindroList) {
        this.tipoCilindroList = tipoCilindroList;
    }

    public List<Marca> getMarcaListCilindros() {
        return marcaListCilindros;
    }

    public void setMarcaListCilindros(List<Marca> marcaListCilindros) {
        this.marcaListCilindros = marcaListCilindros;
    }

    public List<Marca> getMarcaListKits() {
        return marcaListKits;
    }

    public void setMarcaListKits(List<Marca> marcaListKits) {
        this.marcaListKits = marcaListKits;
    }

    public List<Linea> getLineaList() {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList) {
        this.lineaList = lineaList;
    }

    public List<Fabricante> getFabricanteList() {
        return fabricanteList;
    }

    public void setFabricanteList(List<Fabricante> fabricanteList) {
        this.fabricanteList = fabricanteList;
    }

    public TipoCilindro getTipoCilindro() {
        return tipoCilindro;
    }

    public void setTipoCilindro(TipoCilindro tipoCilindro) {
        this.tipoCilindro = tipoCilindro;
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

    public Estatus getEstatusFabricanteActivos() {
        return estatusFabricanteActivos;
    }

    public void setEstatusFabricanteActivos(Estatus estatusFabricanteActivos) {
        this.estatusFabricanteActivos = estatusFabricanteActivos;
    }

    public List<Cilindro> getListaCilindros() {
        return listaCilindros;
    }

    public void setListaCilindros(List<Cilindro> listaCilindros) {
        this.listaCilindros = listaCilindros;
    }

    public List<Kit> getListaKit() {
        return listaKit;
    }

    public void setListaKit(List<Kit> listaKit) {
        this.listaKit = listaKit;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
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

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public boolean isLegalizacionInfoDisabled() {
        return legalizacionInfoDisabled;
    }

    public void setLegalizacionInfoDisabled(Boolean legalizacionInfoDisabled) {
        this.legalizacionInfoDisabled = legalizacionInfoDisabled;
    }

    public String getLegalizacionString() {

        if (legalizacion.getNumeroLegal() == null) {
            legalizacionString = "";
            return legalizacionString;
        } else {
            legalizacionString = " Numero de legalización: ";
            return legalizacionString + legalizacion.getNumeroLegal();
        }
    }

    public void setLegalizacionString(String legalizacionString) {
        this.legalizacionString = legalizacionString;
    }

    public String getPublicoString() {

        if (documento != null) {
            if (documento.isPublico()) {

                publicoString = "Si";
            }
        } else {
            publicoString = "No";
        }

        return publicoString;
    }

    public void setPublicoString(String publicoString) {
        this.publicoString = publicoString;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public String getSerialKit() {
        return serialKit;
    }

    public void setSerialKit(String serialKit) {
        this.serialKit = serialKit;
    }

    public String getSerialCilindro() {
        return serialCilindro;
    }

    public void setSerialCilindro(String serialCilindro) {
        this.serialCilindro = serialCilindro;
    }

}
