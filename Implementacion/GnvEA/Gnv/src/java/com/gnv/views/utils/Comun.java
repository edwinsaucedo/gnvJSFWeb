/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.views.utils;

/**
 *
 * @author Administrador
 */
public class Comun {
    
    public static boolean isNumeric(String value){
       try {
		Long.parseLong(value);
		return true;
	} catch (NumberFormatException nfe){
		
            return false;
	}
    }
    
}
