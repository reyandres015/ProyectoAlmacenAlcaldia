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
import modelo.dao.ContratoDao;
import modelo.dto.Persona;
import modelo.dto.kardex.Contrato;
import modelo.dto.kardex.Proveedor;
import vista.kardex.UIIngresoContratos;
import vista.kardex.VentanaTablaContratos;

/**
 * @author reyan
 */
public class ControllerIngresoContratos implements ActionListener {

    private final UIIngresoContratos vista;
    private final ContratoDao modelo;

    public ControllerIngresoContratos(ContratoDao modelo) throws IOException {
        this.vista = new UIIngresoContratos();
        this.modelo = modelo;
        this.vista.ingresarContratoBtn.addActionListener(this);
        this.vista.buscarProveedorBtn.addActionListener(this);
        this.vista.contratosBtn.addActionListener(this);

        this.vista.setVisible(true);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vista.buscarProveedorBtn)) {
            String empresa = vista.empresaField.getText();

            Persona persona = modelo.buscarProveedores(empresa);

            if (persona != null) {
                JOptionPane.showMessageDialog(null, "Se ha encontrado el proveedor");
                vista.nombreRepreField.setText(persona.getNombre());
                vista.identificacionRepreField.setText(String.valueOf(persona.getIdentificacion()));
                vista.celularRepre.setText(String.valueOf(persona.getCelular()));
                vista.direcionRepre.setText(String.valueOf(persona.getDireccion()));
                vista.correoRepre.setText(String.valueOf(persona.getCorreo()));
            } else if (vista.identificacionProveedor.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el proveedor");
            } else {
                String id = vista.identificacionProveedor.getText();
                persona = modelo.buscarProveedores(id);
                if (persona == null) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el proveedor");
                } else {
                    JOptionPane.showMessageDialog(null, "Se ha encontrado el proveedor");
                    vista.nombreRepreField.setText(persona.getNombre());
                    vista.identificacionRepreField.setText(String.valueOf(persona.getIdentificacion()));
                    vista.celularRepre.setText(String.valueOf(persona.getCelular()));
                    vista.direcionRepre.setText(String.valueOf(persona.getDireccion()));
                    vista.correoRepre.setText(String.valueOf(persona.getCorreo()));
                }
            }
        }
        if (e.getSource().equals(this.vista.ingresarContratoBtn)) {
            if (vista.fechaEntradaField.getText().equalsIgnoreCase("") | vista.objetoField.getText().equalsIgnoreCase("") | vista.conceptoField.getText().equalsIgnoreCase("") | vista.empresaField.getText().equalsIgnoreCase("") | vista.identificacionProveedor.getText().equalsIgnoreCase("") | vista.nombreRepreField.getText().equalsIgnoreCase("") | vista.identificacionRepreField.getText().equalsIgnoreCase("") | vista.celularRepre.getText().equalsIgnoreCase("") | vista.direcionRepre.getText().equalsIgnoreCase("") | vista.correoRepre.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Los datos del ingreso estan incompletos");
            } else {
                try {
                    if (modelo.ingresarContrato(new Contrato(vista.fechaEntradaField.getText(), vista.objetoField.getText(), vista.conceptoField.getText(), new Proveedor(vista.empresaField.getText(), vista.identificacionProveedor.getText(), new Persona(vista.nombreRepreField.getText(), vista.identificacionRepreField.getText(), vista.celularRepre.getText(), vista.direcionRepre.getText(), vista.correoRepre.getText())), Integer.valueOf(vista.valorContrato.getText())))) {
                        JOptionPane.showMessageDialog(null, "Se ha ingresado correctamente el contrato");
                        modelo.guardar();
                        vista.dispose();
                        ControllerIngresoBusquedaProducto ck = new ControllerIngresoBusquedaProducto(this.modelo, modelo.getContratos().size() - 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "No ha sido posible ingresar el contrato");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ControllerIngresoContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource().equals(this.vista.contratosBtn)) {
            try {
                VentanaTablaContratos cp = new VentanaTablaContratos();
                cp.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControllerIngresoContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
