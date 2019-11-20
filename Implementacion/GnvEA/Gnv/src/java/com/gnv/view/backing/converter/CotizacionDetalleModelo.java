    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.converter;

/**
 *
 * @author PC2
 */
public class CotizacionDetalleModelo {
    
    private int numeroPlazo;
    private double capital;
    private double intereses;
    private double iva;
    private double pagoTotal;

    public CotizacionDetalleModelo(int numeroPlazo, double capital, double intereses, double iva, double pagoTotal) {
        this.numeroPlazo = numeroPlazo;
        this.capital = capital;
        this.intereses = intereses;
        this.iva = iva;
        this.pagoTotal = pagoTotal;
    }

    public int getNumeroPlazo() {
        return numeroPlazo;
    }

    public void setNumeroPlazo(int numeroPlazo) {
        this.numeroPlazo = numeroPlazo;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }
    
    
    
}
