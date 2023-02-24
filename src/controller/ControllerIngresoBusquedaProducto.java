/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.*;
import modelo.dto.kardex.Producto;
import modelo.dto.kardex.Contrato;
import vista.kardex.UIIngresoBusquedaProducto;
import vista.kardex.VentanaTablaProductos;

/**
 *
 * @author reyan
 */
public class ControllerIngresoBusquedaProducto implements ActionListener {

    private final UIIngresoBusquedaProducto vista;
    private Contrato contrato;
    private ProductoDao modeloProducto;
    private ProductoDao modeloTotalProductos;

    public ControllerIngresoBusquedaProducto(Contrato contrato) throws IOException {
        this.vista = new UIIngresoBusquedaProducto();
        this.contrato = contrato;
        this.modeloProducto = contrato.getModeloProductos();
        this.modeloTotalProductos = Contrato.getModeloTotalProductos();
        this.vista.ingresarBtn.addActionListener(this);
        this.vista.buscarProductoBtn.addActionListener(this);
        this.vista.productosBtn.addActionListener(this);
        this.vista.referenciaContratoLabel.setText(contrato.getReferencia());
        this.vista.referenciaField.setText(contrato.getReferencia());
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.ingresarBtn)) {
            String descripcion = vista.descripcionField.getText();
            if (modeloProducto.buscarProductoDescripcion(descripcion) != null) { 
                JOptionPane.showMessageDialog(null, "¡Ya existe un producto con la misma descripcion!");
            } else {
                String referencia = vista.referenciaField.getText();
                String ubicacion = vista.ubicacionField.getText();
                String metodo = vista.metodoField.getText();
                String proveedor = vista.proveedorField.getText();
                try {
                    Producto producto = new Producto(modeloProducto.tamañoArreglo(), descripcion, referencia, ubicacion, metodo, contrato.getProveedor());
                    if (descripcion.equalsIgnoreCase("") | referencia.equalsIgnoreCase("") | ubicacion.equalsIgnoreCase("") | metodo.equalsIgnoreCase("") | proveedor.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Los datos del producto estan incompletos");
                    } else {
                        if (modeloProducto.ingresarProducto(producto) && modeloTotalProductos.ingresarProducto(producto)) {
                            JOptionPane.showMessageDialog(null, "Se ha ingresado el producto satisfactoriamente");
                            modeloProducto.guardar();
                            modeloTotalProductos.guardar();

                            ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto, modeloProducto);
                        } else {
                            JOptionPane.showMessageDialog(null, "No ha sido posible ingresar el producto");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ControllerIngresoBusquedaProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource().equals(this.vista.buscarProductoBtn)) {
            String descripcion = vista.buscarDescripcionField.getText();
            Producto producto = modeloProducto.buscarProductoDescripcion(descripcion);
            if (producto == null) {
                if (vista.buscarReferenciaField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
                } else {
                    String referencia = vista.buscarReferenciaField.getText();
                    producto = modeloProducto.buscarProductoReferencia(referencia);
                    if (producto == null) {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
                    } else {
                        producto.initDatos();
                        ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto, modeloProducto);
                        vista.dispose();
                    }
                }
            } else {
                producto.initDatos();
                ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto, modeloProducto);
            }
        }
        if (e.getSource().equals(this.vista.productosBtn)) {
            try {
                VentanaTablaProductos cp = new VentanaTablaProductos(modeloProducto);
                cp.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControllerIngresoContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
