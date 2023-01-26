/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.dto.kardex.Producto;
import modelo.dao.ProductoDao;
import vista.kardex.UIIngresoBusquedaProducto;

/**
 *
 * @author reyan
 */
public class ControllerIngresoBusquedaProducto implements ActionListener {

    UIIngresoBusquedaProducto vista;
    ProductoDao modelo;

    public ControllerIngresoBusquedaProducto(UIIngresoBusquedaProducto vista) {
        this.vista = vista;
        this.modelo = new ProductoDao();
        this.vista.ingresarBtn.addActionListener(this);
        this.vista.buscarProductoBtn.addActionListener(this);
        this.vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.ingresarBtn)) {
            String descripcion = vista.descripcionField.getText();
            String referencia = vista.referenciaField.getText();
            String ubicacion = vista.ubicacionField.getText();
            String metodo = vista.metodoField.getText();
            String proveedor = vista.proveedorField.getText();
            Producto producto = new Producto(modelo.tamañoArreglo(), descripcion, referencia, ubicacion, metodo, proveedor);
            System.out.println(modelo.tamañoArreglo());
            if (modelo.ingresarProducto(producto)) {
                JOptionPane.showMessageDialog(null, "Se ha ingresado el producto satisfactoriamente");
                modelo.guardar();
                ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
                vista.dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible ingresar el producto");
            }

        }
        if (e.getSource().equals(this.vista.buscarProductoBtn)) {
            String descripcion = vista.buscarDescripcionField.getText();
            Producto producto = modelo.buscarProductoDescripcion(descripcion);
            if (producto == null) {
                if (vista.buscarReferenciaField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
                } else {
                    String referencia = vista.buscarReferenciaField.getText();
                    producto = modelo.buscarProductoReferencia(referencia);
                    if (producto == null) {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
                    } else {
                        producto.initDatos();
                        ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
                        vista.dispose();
                    }
                }
            } else {
                producto.initDatos();
                ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
            }

        }
        if (e.getSource().equals(this.vista.catalogoProductoBtn)) {
            
        }
        
    }

}
