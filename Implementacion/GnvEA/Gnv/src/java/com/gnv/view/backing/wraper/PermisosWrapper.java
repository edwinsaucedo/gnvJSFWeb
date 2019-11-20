/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.wraper;

import com.gnv.business.ejb.modelo.RolesPermissions;




/**
 *
 * @author _Gescalante
 */
public class PermisosWrapper {
    private Integer id;
    private String nombre;
    private Boolean renderCheckBox;
    private Boolean valor;
    private String tipo;
    private String descripcion;
    private RolesPermissions rolPermiso;

    public PermisosWrapper(Integer id, String nombre, Boolean renderCheckBox, Boolean valor, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.renderCheckBox = renderCheckBox;
        this.valor = valor;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getRenderCheckBox() {
        return renderCheckBox;
    }

    public void setRenderCheckBox(Boolean renderCheckBox) {
        this.renderCheckBox = renderCheckBox;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RolesPermissions getRolPermiso() {
        return rolPermiso;
    }

    public void setRolPermiso(RolesPermissions rolPermiso) {
        this.rolPermiso = rolPermiso;
    }
    
}
