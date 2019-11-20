/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.views.utils;

import java.util.ResourceBundle;

/**
 *
 * @author _Gescalante
 */
public class Propiedades {

    public static String getEtiqueta(String sEtiqueta)
    {
        try
        {
            ResourceBundle rs = ResourceBundle.getBundle("configweb");
            return rs.getString(rs.getString("ambiente") + "." + sEtiqueta);
        } catch (Exception e)
        {
            //e.printStackTrace();
            return "No existe la etiqueta.";
        }
    }

    public static String getMensaje(String sEtiqueta)
    {
        try
        {
            ResourceBundle rs = ResourceBundle.getBundle("mensajes");
            return rs.getString(sEtiqueta);
        } catch (Exception e)
        {
            //e.printStackTrace();
            return "No existe la etiqueta.";
        }
    }
}
