/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ContratoDao;
import modelo.dto.kardex.Producto;
import modelo.dao.ProductoDao;
import modelo.dto.kardex.Contrato;
import vista.kardex.UIIngresoBusquedaProducto;

/**
 *
 * @author reyan
 */
public class ControllerIngresoBusquedaProducto implements ActionListener {

    private UIIngresoBusquedaProducto vista;
    private Contrato contrato;
    private ProductoDao modelo;
    private DefaultTableModel modeloTabla;
    DecimalFormat formato = new DecimalFormat("¤#,###");

    public ControllerIngresoBusquedaProducto(ContratoDao modeloContrato, int i) throws IOException {
        this.vista = new UIIngresoBusquedaProducto();
        this.contrato = modeloContrato.getContratos().get(i);
        this.modelo = new ProductoDao();
        this.vista.ingresarBtn.addActionListener(this);
        this.vista.buscarProductoBtn.addActionListener(this);
        this.modeloTabla = (DefaultTableModel) this.vista.tablaProductos.getModel();
        actualizarTabla();
        this.vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.ingresarBtn)) {
            String descripcion = vista.descripcionField.getText();
            if (modelo.buscarProductoDescripcion(descripcion) != null) {
                JOptionPane.showMessageDialog(null, "¡Ya existe un producto con la misma descripcion!");
            } else {
                String referencia = vista.referenciaField.getText();
                String ubicacion = vista.ubicacionField.getText();
                String metodo = vista.metodoField.getText();
                String proveedor = vista.proveedorField.getText();
                Producto producto;
                try {
                    producto = new Producto(modelo.tamañoArreglo(), descripcion, referencia, ubicacion, metodo, proveedor);
                    if (descripcion.equalsIgnoreCase("") | referencia.equalsIgnoreCase("") | ubicacion.equalsIgnoreCase("") | metodo.equalsIgnoreCase("") | proveedor.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Los datos del producto estan incompletos");
                    } else {
                        if (modelo.ingresarProducto(producto)) {
                            JOptionPane.showMessageDialog(null, "Se ha ingresado el producto satisfactoriamente");
                            modelo.guardar();
                            ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
                            vista.dispose();
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
    }
    public void actualizarTabla() {
        ArrayList<Producto> productos = modelo.getProductos();
        if (productos != null) {
            int filas = modeloTabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modeloTabla.removeRow(0);
            }

            for (Producto v : productos) {
                Object fila[] = {v.getItem(), v.getDescripcion(), v.getReferencia(), v.getUbicacion(), v.getProveedor(), v.getCantidadTotal(), formato.format(v.getValorTotal())};
                modeloTabla.addRow(fila);
            }
        }
    }
}
