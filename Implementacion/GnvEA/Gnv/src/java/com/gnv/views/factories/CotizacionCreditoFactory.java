/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.views.factories;

import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.view.backing.converter.CotizacionDetalleModelo;
import com.gnv.views.utils.Constantes;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class CotizacionCreditoFactory implements Serializable {

    /**
     * Creates a new instance of CotizacionCreditoGenerico
     */
    private double montoCredito;
    private double tasaInteres;
    private double plazo;
    private int mensualAnual;
    private double iva;
    private double ivaTotal;
    private double montoInteresTotal;
    private double montoPagoTotal;
    private ArrayList<CotizacionDetalleModelo> detalleList;

    //Detalle
    private double capital;
    private double montoPagoMensual;
    private double montoInteresMensual;
    private double montoInteresMensualSinIva;

    public CotizacionCreditoFactory() {
    }

    public CotizacionCreditoFactory(double montoCredito, double tasaInteres, double plazo, int mensualAnual, double iva, double montoInteresTotal, double montoPagoTotal, ArrayList<CotizacionDetalleModelo> detalleList, double capital, double montoPagoMensual, double montoInteresMensual, double ivaTotal) {
        this.montoCredito = montoCredito;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.mensualAnual = mensualAnual;
        this.iva = iva;
        this.montoInteresTotal = montoInteresTotal;
        this.montoPagoTotal = montoPagoTotal;
        this.detalleList = detalleList;
        this.capital = capital;
        this.montoPagoMensual = montoPagoMensual;
        this.montoInteresMensual = montoInteresMensual;
        this.ivaTotal = ivaTotal;

    }

    public CotizacionCreditoFactory calcularCredito(double montoCredito, double tasaInteres, double plazo, double iva) {
        //Datos cabecera
        DecimalFormat df = new DecimalFormat("#.00");
        detalleList = new ArrayList<>();
        montoInteresTotal = (montoCredito * (tasaInteres / 100));
        montoPagoTotal = montoInteresTotal + montoCredito;

        //Datos de detalle-
        capital = montoCredito / plazo;
        montoPagoMensual = montoPagoTotal / plazo;
        montoInteresMensualSinIva = (capital * tasaInteres / 100);
        montoInteresMensual = montoInteresMensualSinIva / ((iva / 100) + 1) ;
// capital = montoCredito - montoInteresMensual;

        ivaTotal = montoInteresMensual * (iva / 100);

        for (int i = 1; i <= plazo; i++) {
            detalleList.add(new CotizacionDetalleModelo(i, Double.parseDouble(df.format(capital)), Double.parseDouble(df.format(montoInteresMensual)), Double.parseDouble(df.format(ivaTotal)), Double.parseDouble(df.format(montoPagoMensual))));

        }
        return new CotizacionCreditoFactory(montoCredito, tasaInteres, plazo, mensualAnual, Double.parseDouble(df.format(ivaTotal)), Double.parseDouble(df.format(montoInteresTotal)), Double.parseDouble(df.format(montoPagoTotal)), detalleList, Double.parseDouble(df.format(capital)), Double.parseDouble(df.format(montoPagoMensual)), Double.parseDouble(df.format(montoInteresMensual)), Double.parseDouble(df.format(ivaTotal)));

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

    public double getPlazo() {
        return plazo;
    }

    public void setPlazo(double plazo) {
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

    public List<CotizacionDetalleModelo> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(ArrayList<CotizacionDetalleModelo> detalleList) {
        this.detalleList = detalleList;
    }

    public double getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(double ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

}
