/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.theme.backing;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean
@SessionScoped
public class TemaManagedBean implements Serializable {

    private String layout = "moody";

    private String theme = "bluegrey";

    private boolean darkMenu;

    private boolean horizontal = false;

    private boolean orientationRTL;

    public String getTheme()
    {
        return theme;
    }

    public TemaManagedBean()
    {
    }

    public String getLayout()
    {
        return layout;
    }

    public void setLayout(String layout)
    {
        this.layout = layout;
    }

    public boolean isDarkMenu()
    {
        return darkMenu;
    }

    public void setDarkMenu(boolean darkMenu)
    {
        this.darkMenu = darkMenu;
    }

    public boolean isHorizontal()
    {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal)
    {
        this.horizontal = horizontal;
    }

    public boolean isOrientationRTL()
    {
        return orientationRTL;
    }

    public void setOrientationRTL(boolean orientationRTL)
    {
        this.orientationRTL = orientationRTL;
    }
    
    

}
