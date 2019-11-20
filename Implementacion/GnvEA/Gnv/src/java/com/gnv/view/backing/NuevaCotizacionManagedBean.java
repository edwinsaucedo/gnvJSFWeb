/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CreditoCotizacionAmortizaFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoCotizacionFacadeLocal;
import com.gnv.business.ejb.persistencia.EmpresaFacadeLocal;
import com.gnv.business.ejb.persistencia.FinancieraFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.views.factories.CotizacionCreditoFactory;
import com.gnv.view.backing.converter.CotizacionDetalleModelo;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.DecimalFormat;
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

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevaCotizacionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private double montoCredito;
    private double tasaInteres;
    private int plazo;
    private int mensualAnual;
    private double iva;
    private double montoIvaTotal;
    private double montoInteresTotal;
    private double montoPagoTotal;
    private ArrayList<CotizacionDetalleModelo> detalleList;

    //Detalle
    private double capital;
    private double montoPagoMensual;
    private double montoInteresMensual;
    //
    private double montoSolicitar;
    private double anticipo;
    private String nombre;
    private Date fechaCotizacion;
    private CreditoCotizacion creditoCotizacion;
    private CreditoCotizacionAmortiza creditoCotizacionAmortiza;
    private Financiera financiera;
    private List<Financiera> financieraList;
    private List<CreditoCotizacionAmortiza> creditoCotizacionAmortizaList;
    //
    private boolean infoDisabled;
    private Objeto objeto;

    @EJB
    private FinancieraFacadeLocal financieraFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private CreditoCotizacionFacadeLocal creditoCotizacionFacade;

    @EJB
    private CreditoCotizacionAmortizaFacadeLocal creditoCotizacionAmortizaFacade;

    public NuevaCotizacionManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            detalleList = new ArrayList<>();
            creditoCotizacion = new CreditoCotizacion();
            creditoCotizacionAmortiza = new CreditoCotizacionAmortiza();
            financiera = new Financiera();

            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            financieraList = financieraFacade.findByEstatusRow();
            if (id != null) {
                DecimalFormat df = new DecimalFormat("#.00");
                double interesSinIva;
                infoDisabled = true;
                creditoCotizacion = creditoCotizacionFacade.find(Integer.valueOf(id));

                montoInteresTotal = creditoCotizacion.getMontoInteres();
                montoPagoTotal = creditoCotizacion.getMontoAPagar();
                plazo = creditoCotizacion.getPlazoCalculo();
                montoCredito = creditoCotizacion.getMontoAFinanciar();
                nombre = creditoCotizacion.getNombre();
                fechaCotizacion = creditoCotizacion.getFecha();
                anticipo = creditoCotizacion.getAnticipo();
                montoSolicitar = creditoCotizacion.getMonto();
                financiera = financieraFacade.find(creditoCotizacion.getFinanciera().getId());

                creditoCotizacionAmortizaList = creditoCotizacionAmortizaFacade.findByEstatusRow(creditoCotizacion);
                iva = Double.parseDouble(df.format((creditoCotizacionAmortizaList.get(0).getIvaIntereses() / creditoCotizacionAmortizaList.get(0).getIntereses()) * 100));
                interesSinIva = creditoCotizacionAmortizaList.get(0).getIntereses() * ((iva / 100) + 1);
                tasaInteres = Double.parseDouble(df.format(interesSinIva / creditoCotizacionAmortizaList.get(0).getCapital() * 100));

                for (int i = 0; i < creditoCotizacionAmortizaList.size(); i++) {
                    detalleList.add(new CotizacionDetalleModelo(creditoCotizacionAmortizaList.get(i).getPartida(), creditoCotizacionAmortizaList.get(i).getCapital(), creditoCotizacionAmortizaList.get(i).getIntereses(), creditoCotizacionAmortizaList.get(i).getIvaIntereses(), creditoCotizacionAmortizaList.get(i).getPago()));
                }

            } else {

                infoDisabled = false;
                detalleList = new ArrayList<>();
                creditoCotizacion = new CreditoCotizacion();
                financiera = new Financiera();

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoEmpresa: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void calcularCotizacion() {
        if (montoCredito < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, "Monto de crédito no válido"));
            return;
        }
        CotizacionCreditoFactory cotizacion = new CotizacionCreditoFactory();
        cotizacion = cotizacion.calcularCredito(montoCredito, tasaInteres, plazo, iva);

        mensualAnual = cotizacion.getMensualAnual();
       // iva = cotizacion.getIva();
        montoInteresTotal = cotizacion.getMontoInteresTotal();
        montoPagoTotal = cotizacion.getMontoPagoTotal();
        detalleList = (ArrayList<CotizacionDetalleModelo>) cotizacion.getDetalleList();

        capital = cotizacion.getCapital();
        montoPagoMensual = cotizacion.getMontoPagoMensual();
        montoInteresMensual = cotizacion.getMontoInteresMensual();
        montoIvaTotal = cotizacion.getIvaTotal();

    }

    public void calculoMontoFinanciar() {
        montoCredito = montoSolicitar - anticipo;
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            if (detalleList.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, Propiedades.getMensaje(Constantes.PROCESO_REQUIRED_COTIZACION_ERROR)));
                return;
            }
             mensaje = Propiedades.getMensaje(Constantes.PROCESO_COTIZACION_NUEVO_EDITO);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO_COTIZACION);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
            creditoCotizacion.setEstatusRow(1);
            creditoCotizacion.setFechaIns(new Date());
            creditoCotizacion.setAnticipo(anticipo);
            creditoCotizacion.setFinanciera(financiera);    
            creditoCotizacion.setNombre(nombre);
            creditoCotizacion.setFecha(fechaCotizacion);
            creditoCotizacion.setMonto(montoSolicitar);
            creditoCotizacion.setMontoInteres(montoInteresTotal);
            creditoCotizacion.setMontoAFinanciar(montoCredito);
            creditoCotizacion.setMontoAPagar(montoPagoTotal);
            creditoCotizacion.setPlazoCalculo(plazo);
            creditoCotizacion.setFrecuenciaPago('M');
            creditoCotizacion.setFrecuenciaTipoPlazo('M');
            creditoCotizacion.setUsuariosIns(usuarioLogueado.getId());
            creditoCotizacionFacade.create(creditoCotizacion);
             BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, creditoCotizacion.getId(), actividad);
            if (creditoCotizacion.getId() != null) {
               
                objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO_COTIZACION_AMORTIZA);

                for (int i = 0; i < detalleList.size(); i++) {
                    creditoCotizacionAmortiza.setCreditoCotizacion(creditoCotizacion);
                    creditoCotizacionAmortiza.setEstatusRow(1);
                    creditoCotizacionAmortiza.setFecha(fechaCotizacion);
                    creditoCotizacionAmortiza.setCapital(detalleList.get(i).getCapital());

                    creditoCotizacionAmortiza.setIntereses(detalleList.get(i).getIntereses());
                    creditoCotizacionAmortiza.setIvaIntereses(detalleList.get(i).getIva());
                    creditoCotizacionAmortiza.setPartida(detalleList.get(i).getNumeroPlazo());
                    creditoCotizacionAmortiza.setPago(detalleList.get(i).getPagoTotal());
                    creditoCotizacionAmortizaFacade.create(creditoCotizacionAmortiza);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, creditoCotizacionAmortiza.getId(), actividad);

                }

            }
            infoDisabled = true;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

        } catch (Exception e) {
            out.println("E/GVN NuevaCotizacion: " + e.getMessage());
        }

    }

    public double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getMensualAnual() {
        return mensualAnual;
    }

    public void setMensualAnual(int mensualAnual) {
        this.mensualAnual = mensualAnual;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getMontoInteresTotal() {
        return montoInteresTotal;
    }

    public void setMontoInteresTotal(double montoInteresTotal) {
        this.montoInteresTotal = montoInteresTotal;
    }

    public double getMontoPagoTotal() {
        return montoPagoTotal;
    }

    public void setMontoPagoTotal(double montoPagoTotal) {
        this.montoPagoTotal = montoPagoTotal;
    }

    public ArrayList<CotizacionDetalleModelo> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(ArrayList<CotizacionDetalleModelo> detalleList) {
        this.detalleList = detalleList;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getMontoPagoMensual() {
        return montoPagoMensual;
    }

    public void setMontoPagoMensual(double montoPagoMensual) {
        this.montoPagoMensual = montoPagoMensual;
    }

    public double getMontoInteresMensual() {
        return montoInteresMensual;
    }

    public void setMontoInteresMensual(double montoInteresMensual) {
        this.montoInteresMensual = montoInteresMensual;
    }

    public double getMontoIvaTotal() {
        return montoIvaTotal;
    }

    public void setMontoIvaTotal(double montoIvaTotal) {
        this.montoIvaTotal = montoIvaTotal;
    }

    public double getMontoSolicitar() {
        return montoSolicitar;
    }

    public void setMontoSolicitar(double montoSolicitar) {
        this.montoSolicitar = montoSolicitar;
    }

    public double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(double anticipo) {
        this.anticipo = anticipo;
    }

    public CreditoCotizacion getCreditoCotizacion() {
        return creditoCotizacion;
    }

    public void setCreditoCotizacion(CreditoCotizacion creditoCotizacion) {
        this.creditoCotizacion = creditoCotizacion;
    }

    public Financiera getFinanciera() {
        return financiera;
    }

    public void setFinanciera(Financiera financiera) {
        this.financiera = financiera;
    }

    public List<Financiera> getFinancieraList() {
        return financieraList;
    }

    public void setFinancieraList(List<Financiera> financieraList) {
        this.financieraList = financieraList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Date fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public CreditoCotizacionAmortiza getCreditoCotizacionAmortiza() {
        return creditoCotizacionAmortiza;
    }

    public void setCreditoCotizacionAmortiza(CreditoCotizacionAmortiza creditoCotizacionAmortiza) {
        this.creditoCotizacionAmortiza = creditoCotizacionAmortiza;
    }

    public boolean isInfoDisabled() {
        return infoDisabled;
    }

    public void setInfoDisabled(boolean infoDisabled) {
        this.infoDisabled = infoDisabled;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

}
