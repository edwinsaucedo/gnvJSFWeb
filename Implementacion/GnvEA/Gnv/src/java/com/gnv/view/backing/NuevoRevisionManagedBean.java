/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.Revision;
import com.gnv.business.ejb.modelo.ServidorArchivos;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.persistencia.CilindroFacadeLocal;
import com.gnv.business.ejb.persistencia.ConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.DocTipoArchivoFacadeLocal;
import com.gnv.business.ejb.persistencia.DocumentoFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.KitFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.PropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.RevisionFacadeLocal;
import com.gnv.business.ejb.persistencia.ServidorArchivosFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
import com.gnv.business.ejb.persistencia.VehPropietarioFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.factories.FileUploadHelper;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
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
public class NuevoRevisionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    //Facade
    @EJB
    private TallerFacadeLocal tallerFacade;

    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private ConversionFacadeLocal conversionFacade;

    @EJB
    private VehPropietarioFacadeLocal propietarioFacade;

    @EJB
    private KitFacadeLocal kitFacade;

    @EJB
    private CilindroFacadeLocal cilindroFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;

    @EJB
    private DocumentoFacadeLocal documentoFacade;

    @EJB
    private ServidorArchivosFacadeLocal servidorArchivosFacade;

    @EJB
    private DocTipoArchivoFacadeLocal docTipoArchivoFacade;

    @EJB
    private RevisionFacadeLocal revisionFacade;

    //Objetos
    private Documento documento;
    private Revision revision;
    private Vehiculo vehiculo;
    private Cilindro cilindro;
    private Compania compania;
    private Estatus estatus;
    private Kit kit;
    private Objeto objeto;
    private String titulo;
    private Taller taller;
    private Revision revisionVehiculo;
    private Conversion conversion;

    //Vars
    private StreamedContent streamedContent;
    private ServidorArchivos servidorArchivo;
    private DocTipoArchivo docTipoArchivo;
    private UploadedFile file;
    private boolean publico;
    private boolean estatusRevision;
    private String extension;
    private Date fechaRevision;
    private String observacion;
    private String id;
    private String idRevision;
    private boolean revisionInfoDisabled;

    //Listas
    private List<Documento> listaDocumento;
    private List<Taller> listaTaller;
    private List<Kit> listaKit;
    private List<Cilindro> listaCilindro;
    private List<VehPropietario> listaPropietario;

    @PostConstruct
    public void init() {

        try {

            titulo = Constantes.REVISION_NUEVO_TITULO;
            compania = usuarioLogueado.getCompania();
            listaTaller = tallerFacade.findByCompania(compania);
            objeto = objetoFacade.findByNombre("Revision");//Declarar constante
            revision = new Revision();

            cargarDatosConversion();
            cargarDatosRevision();

        } catch (Exception e) {
            out.println("E/GVN NuevoValoracion: " + e.getMessage());

        }
    }

    public void guardarRevision() {

        String mensaje = "", actividad = "";

        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_REVISION);
            if (vehiculo.getId() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, "Debe seleccionar un vehículo"));
                return;

            } else {

                revision = revisionFacade.findByVehiculo(vehiculo);
            }
            if (revision == null) {
                revision = new Revision();
            }

            revision.setFechaRevision(fechaRevision);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaRevision);
            calendar.add(Calendar.YEAR, 1);
            vehiculo.setFechaProxRev(calendar.getTime());
            revision.setFechaProxRevision(calendar.getTime());
            revision.setTaller(taller);

            vehiculoFacade.edit(vehiculo);
            revision.setVehiculo(vehiculo);
            revision.setObservacion(observacion);
            revision.setEstatusRow(1);
            revision.setEstatus(estatusRevision);

            if (revision.getId() != null) {
                revisionFacade.edit(revision);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
            } else {
                revisionFacade.create(revision);
                 actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            }

            if (revision.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REVISION_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

                revisionInfoDisabled = true;

            } else {
                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REVISION_NUEVO_GUARDO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                revisionInfoDisabled = true;
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,revision.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN guardarRevision: " + e.getMessage());
        }

    }

    public void cargarDatosConversion() {
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (id != null) {
            conversion = conversionFacade.find(Integer.valueOf(id));
            vehiculo = conversion.getValoracion().getVehiculo();
            listaPropietario = propietarioFacade.findByVehiculo(vehiculo);
            listaKit = kitFacade.findByConversion(conversion);
            listaCilindro = cilindroFacade.findByConversion(conversion);

        }
    }

    public void cargarDatosRevision() {
        try {
            idRevision = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idR");
            if (idRevision != null) {
                revision = revisionFacade.find(Integer.valueOf(idRevision));
                listaDocumento = documentoFacade.findByObjeto(objeto, revision.getId());
                if (revision.getEstatus()) {
                    revisionInfoDisabled = true;
                }

                vehiculo = revision.getVehiculo();
                observacion = revision.getObservacion();
                fechaRevision = revision.getFechaRevision();
                estatusRevision = revision.getEstatus();
                taller = revision.getTaller();
                listaPropietario = propietarioFacade.findByVehiculo(vehiculo);
                conversion = conversionFacade.findByVehiculo(vehiculo.getId());
                listaKit = kitFacade.findByConversion(conversion);
                listaCilindro = cilindroFacade.findByConversion(conversion);
            }
        } catch (Exception e) {
            out.println("E/GVN ListaDocumento: " + e.getMessage());
        }

    }

    public void guardarDocumento(FileUploadEvent fileUploadEvent) {
        String tokens[], ruta;

        String mensaje = "", actividad = "";
        try {
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            if (revision.getId() == null) {

                mensaje = Propiedades.getMensaje(Constantes.PROCESO_REQUIRED_REVISION_ERROR);//cambiar
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                return;
            } else {
                documento = new Documento();
                file = fileUploadEvent.getFile();
                ruta = objeto.getNombre() + "/" + revision.getId() + "/";
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
                documento.setIdRegistro(revision.getId());
                documentoFacade.create(documento);

            }
            if (documento.getId() != null) {
                if (FileUploadHelper.cargarArchivos(file, servidorArchivo, ruta)) {
                    listaDocumento = documentoFacade.findByObjeto(objeto, revision.getId());
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
        String mensaje,actividad;
        try {
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_DOCUMENTO);
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDoc");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEHICULO_NUEVO_ELIMINAR_DOCUMENTO);
            documento = documentoFacade.find(Integer.valueOf(id));
            documento.setEstatusRow(-1);
            documentoFacade.edit(documento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            if (revision.getId() != null) {
                listaDocumento = documentoFacade.findByObjeto(objeto, revision.getId());
            }
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
            out.println("E/GVN Nuevo revision: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Ocurrio un error al descargar el documento."));
        }

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

    public NuevoRevisionManagedBean() {
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Taller> getListaTaller() {
        return listaTaller;
    }

    public void setListaTaller(List<Taller> listaTaller) {
        this.listaTaller = listaTaller;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public DocTipoArchivo getDocTipoArchivo() {
        return docTipoArchivo;
    }

    public void setDocTipoArchivo(DocTipoArchivo docTipoArchivo) {
        this.docTipoArchivo = docTipoArchivo;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public List<Kit> getListaKit() {
        return listaKit;
    }

    public void setListaKit(List<Kit> listaKit) {
        this.listaKit = listaKit;
    }

    public List<Cilindro> getListaCilindro() {
        return listaCilindro;
    }

    public void setListaCilindro(List<Cilindro> listaCilindro) {
        this.listaCilindro = listaCilindro;
    }

    public List<VehPropietario> getListaPropietario() {
        return listaPropietario;
    }

    public void setListaPropietario(List<VehPropietario> listaPropietario) {
        this.listaPropietario = listaPropietario;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public boolean isEstatusRevision() {
        return estatusRevision;
    }

    public void setEstatusRevision(boolean estatusRevision) {
        this.estatusRevision = estatusRevision;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isRevisionInfoDisabled() {
        return revisionInfoDisabled;
    }

    public void setRevisionInfoDisabled(boolean revisionInfoDisabled) {
        this.revisionInfoDisabled = revisionInfoDisabled;
    }

}
