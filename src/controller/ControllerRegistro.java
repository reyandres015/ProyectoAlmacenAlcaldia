/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import modelo.dao.*;
import modelo.dto.kardex.InventarioProducto;
import modelo.dto.kardex.Producto;
import vista.kardex.UIRegistro;

/**
 *
 * @author reyan
 */
public class ControllerRegistro implements ActionListener {

    private UIRegistro vistaRegistro;
    private Producto producto;
    private ProductoDao modelo;
    DecimalFormat formato = new DecimalFormat("¤#,###");

    public ControllerRegistro(int i) {
        this.vistaRegistro = new UIRegistro();
        this.modelo = new ProductoDao();
        this.producto = modelo.getProductos().get(i);
        this.vistaRegistro.realizarEntradaBtn.addActionListener(this);
        this.vistaRegistro.realizarSalidaBtn.addActionListener(this);
        this.vistaRegistro.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaRegistro.realizarEntradaBtn)) {
            LocalDate fecha = LocalDate.now();
            String conceptoEntrada = vistaRegistro.conceptoEntradaField.getText();
            int cantidadEntrada = Integer.valueOf(vistaRegistro.cantidadEntradaField.getText());
            long valorUnitarioEntrada = Integer.valueOf(vistaRegistro.valorUnitarioEntradaField.getText());
            long valorTotalEntrada = cantidadEntrada * valorUnitarioEntrada;
            vistaRegistro.valorTotalEntradaLabel.setText(String.valueOf(formato.format(valorTotalEntrada)));
            if (producto.entradaProducto(cantidadEntrada, valorTotalEntrada)) {
                InventarioProducto ip = new InventarioProducto(producto.tamañoArreglo(), fecha, conceptoEntrada, "Entrada", cantidadEntrada, valorUnitarioEntrada, valorTotalEntrada, producto.getCantidadTotal());
                if (producto.crearRegistro(ip)) {
                    producto.guardar();
                    JOptionPane.showMessageDialog(null, "Se ha registrado la entrada satisfactoriamente");
                    ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem());
                    vistaRegistro.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible registrar la entrada");
            }
        }

        if (e.getSource().equals(this.vistaRegistro.realizarSalidaBtn)) {
            LocalDate fecha = LocalDate.now();
            String conceptoSalida = vistaRegistro.conceptoSalidaField.getText();
            int cantidadSalida = Integer.valueOf(vistaRegistro.cantidadSalidaField.getText());

            if (producto.salidaProducto(cantidadSalida)) {
                InventarioProducto ip = new InventarioProducto(producto.tamañoArreglo(), fecha, conceptoSalida, "Salida", cantidadSalida, 0, 0, producto.getCantidadTotal());
                if (producto.crearRegistro(ip)) {
                    modelo.guardar();
                    producto.guardar();
                    JOptionPane.showMessageDialog(null, "Se ha registrado la salida satisfactoriamente");
                    ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem());
                    vistaRegistro.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No ha sido posible registrar la entrada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No exiten suficientes unidades en el inventario para retirar.");
            }

        }

    }
}
