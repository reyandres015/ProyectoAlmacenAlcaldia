/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto.kardex;

import modelo.dao.ProductoDao;

/**
 *
 * @author reyan
 */
public class Contrato {

    private String fecha;
    private String objeto;
    private String concepto;
    private Proveedor proveedor;
    private ProductoDao modeloProducto;

    public Contrato(String fecha, String objeto, String concepto, Proveedor proveedor) {
        this.fecha = fecha;
        this.objeto = objeto;
        this.concepto = concepto;
        this.proveedor = proveedor;
        this.modeloProducto = modeloProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObjeto() {
        return objeto;
    }

    public String getConcepto() {
        return concepto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public ProductoDao getModeloProducto() {
        return modeloProducto;
    }

}
