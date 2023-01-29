/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public ControllerRegistro(int i, ProductoDao modelo) {
        this.vistaRegistro = new UIRegistro();
        this.modelo = modelo;
        this.modelo.initDatos();
        this.producto = this.modelo.getProductos().get(i);
        this.vistaRegistro.realizarEntradaBtn.addActionListener(this);
        this.vistaRegistro.realizarSalidaBtn.addActionListener(this);
        this.vistaRegistro.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaRegistro.realizarEntradaBtn)) {
            String fecha = vistaRegistro.fechaEntradaField.getText();
            if (vistaRegistro.conceptoEntradaField.getText().equalsIgnoreCase("") | vistaRegistro.cantidadEntradaField.getText().equalsIgnoreCase("") | vistaRegistro.valorUnitarioEntradaField.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Los datos del ingreso estan incompletos");
            } else {
                String conceptoEntrada = vistaRegistro.conceptoEntradaField.getText();
                int cantidadEntrada = Integer.valueOf(vistaRegistro.cantidadEntradaField.getText());
                long valorUnitarioEntrada = Integer.valueOf(vistaRegistro.valorUnitarioEntradaField.getText());
                long valorTotalEntrada = cantidadEntrada * valorUnitarioEntrada;
                vistaRegistro.valorTotalEntradaLabel.setText(String.valueOf(formato.format(valorTotalEntrada)));
                if (producto.entradaProducto(cantidadEntrada, valorTotalEntrada)) {
                    InventarioProducto ip = new InventarioProducto(producto.tamañoArreglo(), fecha, conceptoEntrada, "Entrada", cantidadEntrada, valorUnitarioEntrada, valorTotalEntrada, cantidadEntrada);
                    if (producto.crearRegistro(ip)) {
                        modelo.guardar();
                        producto.guardar();
                        JOptionPane.showMessageDialog(null, "Se ha registrado la entrada satisfactoriamente");
                        ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
                        vistaRegistro.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No ha sido posible registrar la entrada");
                }
            }
        }

        if (e.getSource().equals(this.vistaRegistro.realizarSalidaBtn)) {
            if (vistaRegistro.cantidadSalidaField.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Los datos de la salida estan incompletos");
            } else {
                int cantidadSalida = Integer.parseInt(vistaRegistro.cantidadSalidaField.getText());
                int restante = cantidadSalida;
                ArrayList<InventarioProducto> inventarios = producto.getInventarios();
                for (int i = 0; i < inventarios.size(); i++) {
                    if (restante > inventarios.get(i).getCantidadDisponible()) {
                        if (inventarios.get(i).getCantidadDisponible() != 0) {
                            restante = restante - inventarios.get(i).getCantidadDisponible();
                            if (generarSalidaOrdenLlegada(inventarios.get(i).getValorUnitario(), inventarios.get(i).getCantidadDisponible())) {
                                inventarios.get(i).setCantidadDisponible(0);
                            }
                        }
                    } else {
                        inventarios.get(i).setCantidadDisponible(inventarios.get(i).getCantidadDisponible() - restante);
                        generarSalidaOrdenLlegada(inventarios.get(i).getValorUnitario(), restante);
                        modelo.guardar();
                        producto.guardar();
                        JOptionPane.showMessageDialog(null, "Se ha registrado la salida satisfactoriamente");
                        ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), modelo);
                        vistaRegistro.dispose();
                        break;
                    }
                }
            }
        }
    }

    public boolean generarSalidaOrdenLlegada(long valorUnitario, int cantidadSalida) {
        if (producto.salidaProducto(cantidadSalida, valorUnitario * cantidadSalida)) {
            InventarioProducto ip = new InventarioProducto(producto.tamañoArreglo(), vistaRegistro.fechaSalidaField.getText(), vistaRegistro.conceptoSalidaField.getText(), "Salida", cantidadSalida, valorUnitario, cantidadSalida * valorUnitario, 0);
            if (producto.crearRegistro(ip)) {
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible registrar la entrada");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No exiten suficientes unidades en el inventario para retirar.");
            return false;
        }
    }
}
