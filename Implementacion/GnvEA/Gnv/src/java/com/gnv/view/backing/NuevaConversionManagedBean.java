/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.ConversionEquipo;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.TipoConvenio;
import com.gnv.business.ejb.modelo.TipoConversion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.persistencia.CilindroFacadeLocal;
import com.gnv.business.ejb.persistencia.ConversionEquipoFacadeLocal;
import com.gnv.business.ejb.persistencia.ConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.DocTipoArchivoFacadeLocal;
import com.gnv.business.ejb.persistencia.DocumentoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.KitFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.ServidorArchivosFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoConvenioFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.ValoracionFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.factories.FileUploadHelper;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
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

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class NuevaConversionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private ConversionFacadeLocal conversionFacade;
    @EJB
    private ValoracionFacadeLocal valoracionFacade;
    @EJB
    private TallerFacadeLocal tallerFacade;
    @EJB
    private TipoConversionFacadeLocal tipoConversionFacade;
    @EJB
    private TipoConvenioFacadeLocal tipoConvenioFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;
    @EJB
    private ConversionEquipoFacadeLocal conversionEquipoFacade;
    @EJB
    private KitFacadeLocal kitFacade;
    @EJB
    private CilindroFacadeLocal cilindroFacade;
    @EJB
    private DocumentoFacadeLocal documentoFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;
    @EJB
    private DocTipoArchivoFacadeLocal tipoArchivoFacade;
    @EJB
    private VehiculoFacadeLocal vehiculoFacade;

    private Conversion conversion;
    private Taller taller;
    private Valoracion valoracion;
    private TipoConversion tipoConversion;
    private TipoConvenio tipoConvenio;
    private Estatus estatus;
    private Vehiculo vehiculoForm, vehiculoModal;
    private Cilindro cilindro;
    private Kit kit;
    private ConversionEquipo conversionEquipo;
    private Documento documento, documentoEquipo;
    private Objeto objeto;
    private ServidorArchivos servidorArchivos;

    private List<Taller> tallerList;
    private List<Valoracion> valoracionList;
    private List<TipoConversion> tipoConversionList;
    private List<TipoConvenio> tipoConvenioList;
    private List<Estatus> estatusList;
    private List<Kit> kitConversionList;
    private List<Cilindro> cilindroConversionList;
    private List<Documento> documentoList, documentoEquipoList;
    private List<Integer> idLegalizacionList;

    private String titulo;
    private boolean editable, infoVehEditable, infoConversionEditable, infoVehVisible, kitEditable, cilindroEditable;
    private StreamedContent streamedContent;
    private UploadedFile archivo;

    public NuevaConversionManagedBean()
    {

    }

    @PostConstruct
    public void init()
    {
        try
        {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            kit = new Kit();
            cilindro = new Cilindro();
            documento = new Documento();
            documentoEquipo = new Documento();
            vehiculoModal = new Vehiculo();
            conversionEquipo = new ConversionEquipo();
            tallerList = tallerFacade.findByCompania(usuarioLogueado.getCompania());
            tipoConversionList = tipoConversionFacade.findByEstatusRow();
            tipoConvenioList = tipoConvenioFacade.findByEstatusRow();
            estatusList = estatusFacade.findByClaseList(Conversion.class.getName());
            valoracionList = new ArrayList<>();
            idLegalizacionList = new ArrayList<>();
            infoConversionEditable = true;
            if (null != id)
            {
                titulo = Constantes.CONVERSION_EDITAR_TITULO;
                infoVehVisible = true;
                infoVehEditable = false;
                frmEdit(id);
            } else
            {
                titulo = Constantes.CONVERSION_NUEVO_TITULO;
                infoVehVisible = false;
                infoVehEditable = true;
                kitEditable = true;
                cilindroEditable = true;
                frmNew();
            }

        } catch (Exception e)
        {   
            out.println("E/GVN NuevaConversion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    private void frmEdit(String id)
    {
        try
        {
            conversion = conversionFacade.find(Integer.valueOf(id));
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_VALORACION);
            taller = conversion.getTaller();
            valoracion = conversion.getValoracion();
            vehiculoForm = valoracion.getVehiculo();
            tipoConversion = conversion.getTipoConversion();
            tipoConvenio = conversion.getTipoConvenio();
            estatus = conversion.getEstatus();
            kitConversionList = kitFacade.findByConversion(conversion);
            cilindroConversionList = cilindroFacade.findByConversion(conversion);
            kitEditable = kitConversionList.isEmpty();
            cilindroEditable = cilindroConversionList.isEmpty();
            if (!kitConversionList.isEmpty())
            {
                idLegalizacionList.add(kitConversionList.get(0).getLegalizacion().getId());
            }
            if (!cilindroConversionList.isEmpty())
            {
                idLegalizacionList.add(cilindroConversionList.get(0).getLegalizacion().getId());
            }
            if (!idLegalizacionList.isEmpty())
            {
                documentoEquipoList = documentoFacade.findByRegistros(objeto, idLegalizacionList);
            } else
            {
                documentoEquipoList = new ArrayList<>();
            }
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION);
            documentoList = documentoFacade.findByObjeto(objeto, conversion.getId());
            editable = estatus.getNombre().equals(Constantes.ESTATUS_EN_PROCESO);
        } catch (Exception e)
        {
            out.println("E/GVN NuevaConversion: " + e.getMessage());
        }
    }

    private void frmNew()
    {
        conversion = new Conversion();
        taller = new Taller();
        valoracion = new Valoracion();
        vehiculoForm = new Vehiculo();
        tipoConversion = new TipoConversion();
        tipoConvenio = new TipoConvenio();
        kitConversionList = new ArrayList<>();
        documentoEquipoList = new ArrayList<>();
        documentoList = new ArrayList<>();
        cilindroConversionList = new ArrayList<>();
        estatus = estatusFacade.findByClase(Conversion.class.getName(), Constantes.ESTATUS_EN_PROCESO);
        editable = true;
    }

    public void guardar()
    {
        String mensaje = "",actividad="";
        if (null == vehiculoForm.getId())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Información del vehiculo requerido."));
            return;
        }
        try
        {
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION);
            conversion.setEstatus(estatus);
            conversion.setTaller(taller);
            conversion.setTipoConversion(tipoConversion);
            conversion.setTipoConvenio(tipoConvenio);
            Vehiculo vehiculo=valoracion.getVehiculo();
            if (conversion.getId() != null)
            {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EDITO);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                conversionFacade.edit(conversion);
                editable = estatus.getNombre().equals(Constantes.ESTATUS_EN_PROCESO);

            } else
            {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_GUARDO);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                conversion.setValoracion(valoracion);
                conversion.setEstatusRow(1);
                conversion.setFechaIns(new Date());
                conversion.setUsuarioIns(usuarioLogueado.getId());
                conversionFacade.create(conversion);
                infoConversionEditable = false;
                infoVehEditable = false;
            }
            if (estatus.getNombre().equals(Constantes.ESTATUS_CONVERTIDO))
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(conversion.getFechaConversion());
                calendar.add(Calendar.YEAR, 1);
                vehiculo.setFechaProxRev(calendar.getTime());
                vehiculoFacade.edit(vehiculo);
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,conversion.getId(), actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Constantes.CATALOGO_ERROR_GENERAL));
        }

    }

    public void guardarKit()
    {
        String serial = "", mensaje = "", estatusSt = "",actividad="";
        try
        {
            if (null == conversion.getId())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Es necesario guardar la informacion de la conversion."));
                return;
            }

            Kit kitAuxiliar;
            Estatus estatusKit;
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EQUIPO_GUARDO);
            serial = kit.getSerial();
            kitAuxiliar = kitFacade.findKitBySerial(serial);
            estatusKit = estatusFacade.findByClase(Kit.class.getName(), Constantes.ESTATUS_ASOCIADO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            if (null != kitAuxiliar && null != estatusKit)
            {
                estatusSt = kitAuxiliar.getEstatus() == null ? "No asociado" : kitAuxiliar.getEstatus().getNombre();
                if (estatusSt.equals("No asociado"))
                {
                    actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    conversionEquipo.setIdRegistro(kitAuxiliar.getId());
                    conversionEquipo.setTipo(true);
                    conversionEquipo.setConversion(conversion);
                    conversionEquipo.setFechaIns(new Date());
                    conversionEquipo.setUsuarioIns(usuarioLogueado.getId());
                    conversionEquipo.setEstatusRow(1);
                    conversionEquipoFacade.create(conversionEquipo);
                    kitAuxiliar.setEstatus(estatusKit);
                    kitFacade.edit(kitAuxiliar);
                    kitConversionList.add(kitAuxiliar);
                    idLegalizacionList.add(kitAuxiliar.getLegalizacion().getId());
                    documentoEquipoList = documentoFacade.findByRegistros(objeto, idLegalizacionList);
                    kit = new Kit();
                    kitEditable = false;
                    objeto = objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION_EQUIPO);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,conversionEquipo.getId(), actividad);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                     
                } else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Serial ya asociado, favor de verificarlo."));
                }
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Serial incorrecto, favor de verificarlo."));
            }

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
        }

    }

    public void guardarCilindro()
    {
        String serial = "", mensaje = "", estatusSt = "",actividad="";
        try
        {
            if (null == conversion.getId())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Es necesario guardar la informacion de la conversion."));
                return;
            }

            Cilindro cilindroAuxiliar;
            Estatus estatusCilindro;
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EQUIPO_GUARDO);
            serial = cilindro.getSerial();
            cilindroAuxiliar = cilindroFacade.findCilindroBySerial(serial);
            estatusCilindro = estatusFacade.findByClase(Cilindro.class.getName(), Constantes.ESTATUS_ASOCIADO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            if (null != cilindroAuxiliar && null != estatusCilindro)
            {
                estatusSt = cilindroAuxiliar.getEstatus() == null ? "No asociado" : cilindroAuxiliar.getEstatus().getNombre();
                if (estatusSt.equals("No asociado"))
                {
                    actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    conversionEquipo.setIdRegistro(cilindroAuxiliar.getId());
                    conversionEquipo.setTipo(false);
                    conversionEquipo.setConversion(conversion);
                    conversionEquipo.setFechaIns(new Date());
                    conversionEquipo.setUsuarioIns(usuarioLogueado.getId());
                    conversionEquipo.setEstatusRow(1);
                    conversionEquipoFacade.create(conversionEquipo);
                    cilindroAuxiliar.setEstatus(estatusCilindro);
                    cilindroFacade.edit(cilindroAuxiliar);
                    cilindroConversionList.add(cilindroAuxiliar);
                    idLegalizacionList.add(cilindroAuxiliar.getLegalizacion().getId());
                    documentoEquipoList = documentoFacade.findByRegistros(objeto, idLegalizacionList);
                    cilindro = new Cilindro();
                    cilindroEditable = false;
                    objeto = objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION_EQUIPO);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,conversionEquipo.getId(), actividad);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                } else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Serial ya asociado, favor de verificarlo."));
                }
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Serial incorrecto o, favor de verificarlo."));
            }

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
        }

    }

    public void deleteKitConversion()
    {
        String mensaje = "";
        int response = 0;
        Estatus estatusKit;
        try
        {
            String idKit = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Kit kitAuxiliar;
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EQUIPO_ELIMINO);
            estatusKit = estatusFacade.findByClase(Kit.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            if (null != estatusKit)
            {
                kitAuxiliar = kitFacade.find(Integer.valueOf(idKit));
                response = conversionEquipoFacade.deleteByKit(kitAuxiliar);
                if (response > 0)
                {
                    kitAuxiliar.setEstatus(estatusKit);
                    kitFacade.edit(kitAuxiliar);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    kitConversionList = kitFacade.findByConversion(conversion);
                    for (int i = 0; i < idLegalizacionList.size(); i++)
                    {
                        int idLegalizacion = idLegalizacionList.get(i);
                        if (idLegalizacion == kitAuxiliar.getLegalizacion().getId())
                        {
                            idLegalizacionList.remove(i);
                            break;
                        }
                    }
                    documentoEquipoList = documentoFacade.findByRegistros(objeto, idLegalizacionList);
                    kitEditable = true;
                } else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
                }
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
            }

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
        }

    }

    public void deleteCilindroConversion()
    {
        String mensaje = "";
        int response = 0;
        Estatus estatusCilindro;
        try
        {
            String idCilindro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Cilindro cilindroAuxiliar;
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_EQUIPO_ELIMINO);
            estatusCilindro = estatusFacade.findByClase(Cilindro.class.getName(), Constantes.ESTATUS_NO_ASOCIADO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            if (null != estatusCilindro)
            {
                cilindroAuxiliar = cilindroFacade.find(Integer.valueOf(idCilindro));
                response = conversionEquipoFacade.deleteByCilindro(cilindroAuxiliar);
                if (response > 0)
                {
                    cilindroAuxiliar.setEstatus(estatusCilindro);
                    cilindroFacade.edit(cilindroAuxiliar);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    cilindroConversionList = cilindroFacade.findByConversion(conversion);
                    for (int i = 0; i < idLegalizacionList.size(); i++)
                    {
                        int idLegalizacion = idLegalizacionList.get(i);
                        if (idLegalizacion == cilindroAuxiliar.getLegalizacion().getId())
                        {
                            idLegalizacionList.remove(i);
                            break;
                        }
                    }
                    documentoEquipoList = documentoFacade.findByRegistros(objeto, idLegalizacionList);
                    kitEditable = true;
                    cilindroEditable = true;
                } else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
                }
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
            }

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
        }

    }

    public void guardarDocumento(FileUploadEvent fileUploadEvent)
    {
        String tokens[], ruta, extension,actividad;
        DocTipoArchivo tipoArchivo;
        if (null == conversion.getId())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Es necesario guardar la informacion de la conversion."));
            return;
        }

        try
        {
            ruta = objeto.getNombre() + "/" + conversion.getId();
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            archivo = fileUploadEvent.getFile();
            tokens = archivo.getFileName().split("\\.(?=[^\\.]+$)");
            extension = tokens[1];
            tipoArchivo = tipoArchivoFacade.findByExtension(extension);
            if (tipoArchivo == null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Tipo de archivo no valido."));
                return;
            }
            servidorArchivos = servidorArchivosFacade.findAll().get(0);
            documento.setNombre(tokens[0]);
            if (!documento.isPublico())
            {
                documento.setCompania(usuarioLogueado.getCompania());
            }
            documento.setTamanio(String.valueOf(archivo.getSize()));
            documento.setTipoArchivo(tipoArchivo);
            documento.setIdRegistro(conversion.getId());
            documento.setObjeto(objeto);
            documento.setRuta(ruta);
            documento.setFechaIns(new Date());
            documento.setUsuarioIns(usuarioLogueado.getId());
            documento.setEstatusRow(1);
            documentoFacade.create(documento);
            if (documento.getId() != null)
            {
                if (FileUploadHelper.cargarArchivos(archivo, servidorArchivos, ruta))
                {
                    documentoList = documentoFacade.findByObjeto(objeto, conversion.getId());
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,documento.getId(), actividad);
                    documento = new Documento();
                    documento.setPublico(false);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Documento cargado exitosamente."));
                    objeto=objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
                } else
                {
                    documentoFacade.remove(documento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al cargar el documento."));
                }

            }
        } catch (Exception e)
        {
            out.println("E/GVN Nuevo vehiculo: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL)));
        }
    }

    public void descargarDocumentos(String idDocumento)
    {
        String ruta, nombreArchivo;
        try
        {
            servidorArchivos = servidorArchivosFacade.findAll().get(0);
            StreamedContent documentoContent;
            Documento documentoD = documentoFacade.find(Integer.valueOf(idDocumento));
            if (documentoD != null && servidorArchivos != null)
            {
                ruta = documentoD.getRuta();
                nombreArchivo = documentoD.getNombre() + "." + documentoD.getTipoArchivo().getExtension();
                documentoContent = FileUploadHelper.descargarArchivo(nombreArchivo, documentoD.getTipoArchivo().getExtension(), servidorArchivos, ruta);
                if (documentoContent == null)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
                    return;
                }
                this.setStreamedContent(documentoContent);
            }
        } catch (Exception e)
        {
            out.println("E/GVN Nueva conversion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
        }
    }

    public void eliminarDocumento()
    {
        String idDocumento = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
        String mensaje,actividad;
        try
        {
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_CONVERSION_NUEVO_DOCUMENTO_ELIMINO);
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION);
            Documento documentoD = documentoFacade.find(Integer.valueOf(idDocumento));
            documentoD.setEstatusRow(-1);
            documentoFacade.edit(documentoD);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            documentoList = documentoFacade.findByObjeto(objeto, conversion.getId());
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,documentoD.getId(), actividad);
        } catch (Exception e)
        {
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public void onItPlacaKeyUp()
    {
        try
        {
            if (null != vehiculoModal && !vehiculoModal.getPlaca().equals(""))
            {
                valoracionList = valoracionFacade.findByPlacaVehiculo(vehiculoModal);
            } else
            {
                valoracionList = new ArrayList<>();
            }
        } catch (Exception e)
        {
            out.println("E/GVN NuevaConversion: " + e.getMessage());
        }
    }

    public void onItPlacaChange()
    {
        try
        {
            if (null != vehiculoForm && !vehiculoForm.getPlaca().equals(""))
            {
                valoracion = valoracionFacade.findByPlacaVehiculo(vehiculoForm.getPlaca());
                if (valoracion != null)
                {
                    vehiculoForm = valoracion.getVehiculo();
                    infoVehVisible = true;
                } else
                {
                    valoracion = new Valoracion();
                    vehiculoForm = new Vehiculo();
                    infoVehVisible = false;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Placa de vehiculo no valida."));
                }
            } else
            {
                valoracion = new Valoracion();
                vehiculoForm = new Vehiculo();
                infoVehVisible = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Placa de vehiculo no valida."));
            }
        } catch (Exception e)
        {
            valoracion = new Valoracion();
            vehiculoForm = new Vehiculo();
            infoVehVisible = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error."));
            out.println("E/GVN NuevaConversion: " + e.getMessage());
        }
    }

    public void onDtVehiculosRowDobleClick(SelectEvent event)
    {
        valoracion = (Valoracion) event.getObject();
        vehiculoModal = new Vehiculo();
        valoracionList = new ArrayList<>();
        vehiculoForm = valoracion.getVehiculo();
        infoVehVisible = true;
        PrimeFaces.current().ajax().update("frmConsultarVehiculo");
        PrimeFaces.current().ajax().update("frmNuevoConversion:idPanelGridVehiculo");

    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public Conversion getConversion()
    {
        return conversion;
    }

    public void setConversion(Conversion conversion)
    {
        this.conversion = conversion;
    }

    public Taller getTaller()
    {
        return taller;
    }

    public void setTaller(Taller taller)
    {
        this.taller = taller;
    }

    public Valoracion getValoracion()
    {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion)
    {
        this.valoracion = valoracion;
    }

    public TipoConversion getTipoConversion()
    {
        return tipoConversion;
    }

    public void setTipoConversion(TipoConversion tipoConversion)
    {
        this.tipoConversion = tipoConversion;
    }

    public TipoConvenio getTipoConvenio()
    {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenio tipoConvenio)
    {
        this.tipoConvenio = tipoConvenio;
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public List<Taller> getTallerList()
    {
        return tallerList;
    }

    public void setTallerList(List<Taller> tallerList)
    {
        this.tallerList = tallerList;
    }

    public List<Valoracion> getValoracionList()
    {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList)
    {
        this.valoracionList = valoracionList;
    }

    public List<TipoConversion> getTipoConversionList()
    {
        return tipoConversionList;
    }

    public void setTipoConversionList(List<TipoConversion> tipoConversionList)
    {
        this.tipoConversionList = tipoConversionList;
    }

    public List<TipoConvenio> getTipoConvenioList()
    {
        return tipoConvenioList;
    }

    public void setTipoConvenioList(List<TipoConvenio> tipoConvenioList)
    {
        this.tipoConvenioList = tipoConvenioList;
    }

    public List<Estatus> getEstatusList()
    {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList)
    {
        this.estatusList = estatusList;
    }

    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    public boolean isInfoVehVisible()
    {
        return infoVehVisible;
    }

    public void setInfoVehVisible(boolean infoVehVisible)
    {
        this.infoVehVisible = infoVehVisible;
    }

    public Vehiculo getVehiculoForm()
    {
        return vehiculoForm;
    }

    public void setVehiculoForm(Vehiculo vehiculoForm)
    {
        this.vehiculoForm = vehiculoForm;
    }

    public Vehiculo getVehiculoModal()
    {
        return vehiculoModal;
    }

    public void setVehiculoModal(Vehiculo vehiculoModal)
    {
        this.vehiculoModal = vehiculoModal;
    }

    public boolean isInfoVehEditable()
    {
        return infoVehEditable;
    }

    public void setInfoVehEditable(boolean infoVehEditable)
    {
        this.infoVehEditable = infoVehEditable;
    }

    public Cilindro getCilindro()
    {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro)
    {
        this.cilindro = cilindro;
    }

    public Kit getKit()
    {
        return kit;
    }

    public void setKit(Kit kit)
    {
        this.kit = kit;
    }

    public List<Kit> getKitConversionList()
    {
        return kitConversionList;
    }

    public void setKitConversionList(List<Kit> kitConversionList)
    {
        this.kitConversionList = kitConversionList;
    }

    public List<Cilindro> getCilindroConversionList()
    {
        return cilindroConversionList;
    }

    public void setCilindroConversionList(List<Cilindro> cilindroConversionList)
    {
        this.cilindroConversionList = cilindroConversionList;
    }

    public boolean isKitEditable()
    {
        return kitEditable;
    }

    public void setKitEditable(boolean kitEditable)
    {
        this.kitEditable = kitEditable;
    }

    public boolean isCilindroEditable()
    {
        return cilindroEditable;
    }

    public void setCilindroEditable(boolean cilindroEditable)
    {
        this.cilindroEditable = cilindroEditable;
    }

    public Documento getDocumento()
    {
        return documento;
    }

    public void setDocumento(Documento documento)
    {
        this.documento = documento;
    }

    public Documento getDocumentoEquipo()
    {
        return documentoEquipo;
    }

    public void setDocumentoEquipo(Documento documentoEquipo)
    {
        this.documentoEquipo = documentoEquipo;
    }

    public List<Documento> getDocumentoList()
    {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList)
    {
        this.documentoList = documentoList;
    }

    public List<Documento> getDocumentoEquipoList()
    {
        return documentoEquipoList;
    }

    public void setDocumentoEquipoList(List<Documento> documentoEquipoList)
    {
        this.documentoEquipoList = documentoEquipoList;
    }

    public StreamedContent getStreamedContent()
    {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent)
    {
        this.streamedContent = streamedContent;
    }

    public boolean isInfoConversionEditable()
    {
        return infoConversionEditable;
    }

    public void setInfoConversionEditable(boolean infoConversionEditable)
    {
        this.infoConversionEditable = infoConversionEditable;
    }

}
