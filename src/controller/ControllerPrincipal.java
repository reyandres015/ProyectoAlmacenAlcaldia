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
    private final ContratoDao modelo;

    public ControllerPrincipal() throws IOException {
        this.vista = new UIPrincipal();
        this.modelo = new ContratoDao();
        this.vista.BtnIngresoContrato.addActionListener(this);
        this.vista.BtnTablaContratos.addActionListener(this);
        this.vista.BtnTablaProductos.addActionListener(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.BtnIngresoContrato)) {
            try {
                ControllerIngresoContratos cC = new ControllerIngresoContratos(modelo);
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
            ArrayList<Producto> totalProductos = new ArrayList<>();
            for (Contrato cp : modelo.getContratos()) {
                if(cp.getProductos()==null){
                    System.out.println("arreglo vacio");
                }
                totalProductos.addAll(cp.getProductos());
            }
            for (Producto totalProducto : totalProductos) {
                System.out.print(totalProducto.getDescripcion() + ", ");
                
            }
            try {
                VentanaTablaProductos vP = new VentanaTablaProductos(totalProductos); 
                vP.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControllerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
