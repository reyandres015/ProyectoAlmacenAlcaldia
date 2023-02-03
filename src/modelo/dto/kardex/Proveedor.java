/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto.kardex;

import java.io.Serializable;
import modelo.dto.Persona;

/**
 *
 * @author reyan
 */
public class Proveedor implements Serializable{

    private String empresa;
    private int documento;
    private Persona representanteLegal;

    public Proveedor(String empresa, int documento, Persona representanteLegal) {
        this.empresa = empresa;
        this.documento = documento;
        this.representanteLegal = representanteLegal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getDocumento() {
        return documento;
    }

    public Persona getRepresentanteLegal() {
        return representanteLegal;
    }
    
    
}
