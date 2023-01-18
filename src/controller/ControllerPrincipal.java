/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.kardex.UITablaTransferencias;
import vista.UIPrincipal;
import vista.kardex.UIIngresoBusquedaProducto;

/**
 *
 * @author reyan
 */
public class ControllerPrincipal implements ActionListener{

    private UIPrincipal vistaPrincipal;

    public ControllerPrincipal() {
        this.vistaPrincipal = new UIPrincipal();
        this.vistaPrincipal.kardexBtn.addActionListener(this);
        this.vistaPrincipal.setVisible(true);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaPrincipal.kardexBtn)) {
            ControllerIngresoBusquedaProducto ck = new ControllerIngresoBusquedaProducto(new UIIngresoBusquedaProducto());
        }

    }
}
