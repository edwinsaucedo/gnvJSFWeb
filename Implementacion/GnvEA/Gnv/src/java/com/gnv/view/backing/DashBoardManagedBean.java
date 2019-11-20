/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.persistencia.ConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.RevisionFacadeLocal;
import com.gnv.business.ejb.persistencia.ValoracionFacadeLocal;
import com.gnv.views.utils.Constantes;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.*;

/**
 *
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class DashBoardManagedBean implements Serializable{

    private BarChartModel cvBarModel;
    private LineChartModel revisionLineModel;
    private HashMap<String, Integer> conversionesMes;
    private HashMap<String, Integer> valoracionesMes;
    private HashMap<String, Integer> revisionesMes;
    private int kitInstalados;
    
    private Estatus estatus;

    @EJB
    private ConversionFacadeLocal conversionFacade;
    @EJB
    private ValoracionFacadeLocal valoracionFacade;
    @EJB
    private RevisionFacadeLocal revisionFacade;
    @EJB
    private EstatusFacadeLocal estatusFacade;

    public DashBoardManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            conversionesMes = conversionFacade.findCoversionesMes();
            valoracionesMes = valoracionFacade.findValoracionesMes();
            revisionesMes = revisionFacade.findRevisionesMes();
            estatus=estatusFacade.findByClase(Conversion.class.getName(),Constantes.ESTATUS_CONVERTIDO);
            kitInstalados=conversionFacade.findKitInstaladosAnio(estatus);
            createCharts();
        } catch (Exception e)
        {
            out.println("E/GVN ListaConversiones: " + e.getMessage());
        }
    }

    private void createCharts()
    {
        //BAR CHART VALORACIONES Y CONVERSIONES
        cvBarModel = new BarChartModel();
        Map<Object, Number> conversionesMesMap = new HashMap<>(conversionesMes);
        Map<Object, Number> valoracionesMesMap = new HashMap<>(valoracionesMes);
        Map<Object, Number> revisionesMesMap = new HashMap<>(revisionesMes);
        ChartSeries conversiones = new ChartSeries();
        ChartSeries valoraciones = new ChartSeries();
        conversiones.setLabel("Conversiones");
        conversiones.setData(conversionesMesMap);
        valoraciones.setLabel("Valoraciones");
        valoraciones.setData(valoracionesMesMap);
        cvBarModel.addSeries(conversiones);
        cvBarModel.addSeries(valoraciones);
        cvBarModel.setLegendPosition("w");

        revisionLineModel = new LineChartModel();
        LineChartSeries revisiones = new LineChartSeries();
        revisiones.setLabel("Revisiones");
        revisiones.setData(revisionesMesMap);
        revisionLineModel.addSeries(revisiones);
        revisionLineModel.setLegendPosition("w");
        CategoryAxis axis=new CategoryAxis();
        revisionLineModel.getAxes().put(AxisType.X, axis);
    }

    public BarChartModel getCvBarModel()
    {
        return cvBarModel;
    }

    public LineChartModel getRevisionLineModel()
    {
        return revisionLineModel;
    }

    public int getKitInstalados()
    {
        return kitInstalados;
    }

    

}
