/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.persistencia.KitFacadeLocal;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import static java.lang.System.out;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaKitManagedBean {

    private List<Kit> listaKit;
    private Kit kit;

    @EJB
    private KitFacadeLocal kitFacade;

    public ListaKitManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
     

        } catch (Exception e) {
            out.println("E/GVN ListaKit: " + e.getMessage());
            //Registrar en bitacora de exepciones
        }
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
    
    
}
