/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.ContratoDao;
import modelo.dao.ProductoDao;
import modelo.dto.kardex.Contrato;
import modelo.dto.kardex.Producto;
import vista.kardex.UIPrincipal;
import vista.kardex.VentanaTablaContratos;
import vista.kardex.VentanaTablaProductos;

/**
 *
 * @author reyan
 */
public class ControllerPrincipal implements ActionListener {

    private final UIPrincipal vista;
    private final ContratoDao modeloContratos;
    private final ProductoDao modeloTotalProductos;

    public ControllerPrincipal() throws IOException {
        this.vista = new UIPrincipal();
        this.modeloContratos = new ContratoDao();
        this.modeloTotalProductos = new ProductoDao();
        this.vista.BtnIngresoContrato.addActionListener(this);
        this.vista.BtnTablaContratos.addActionListener(this);
        this.vista.BtnTablaProductos.addActionListener(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.BtnIngresoContrato)) {
            try {
                ControllerIngresoContratos cC = new ControllerIngresoContratos(modeloContratos, modeloTotalProductos);
            } catch (IOException ex) {
                Logger.getLogger(ControllerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(this.vista.BtnTablaContratos)) {
            try {
                VentanaTablaContratos cp = new VentanaTablaContratos();
                cp.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControllerIngresoContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(this.vista.BtnTablaProductos)) {
            try {
                VentanaTablaProductos vP = new VentanaTablaProductos(modeloTotalProductos.getProductos());
                vP.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControllerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
