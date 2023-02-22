/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto.kardex;

import java.io.IOException;
import java.io.Serializable;
import modelo.dao.ProductoDao;

/**
 *
 * @author reyan
 */
public class Contrato implements Serializable {

    private final String fecha;
    private final String referencia;
    private final String objeto;
    private final Proveedor proveedor;
    private final long valorTotal;
    private ProductoDao modeloProductos;
    private static ProductoDao modeloTotalProductos;

    public Contrato(String fecha, String objeto, String referencia, Proveedor proveedor, long valorTotal, ProductoDao modeloTotalProductos) throws IOException{
        this.fecha = fecha;
        this.objeto = objeto;
        this.referencia = referencia;
        this.proveedor = proveedor;
        this.valorTotal = valorTotal;
        this.modeloProductos = new ProductoDao(referencia);
        Contrato.modeloTotalProductos = modeloTotalProductos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObjeto() {
        return objeto;
    }

    public String getReferencia() {
        return referencia;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public ProductoDao getModeloProductos() {
        return modeloProductos;
    }

    public static ProductoDao getModeloTotalProductos() {
        return modeloTotalProductos;
    }
    
    

}
