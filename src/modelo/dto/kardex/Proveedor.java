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

    private final String empresa;
    private final String documento;
    private final Persona representanteLegal;

    public Proveedor(String empresa, String documento, Persona representanteLegal) {
        this.empresa = empresa;
        this.documento = documento;
        this.representanteLegal = representanteLegal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getDocumento() {
        return documento;
    }

    public Persona getRepresentanteLegal() {
        return representanteLegal;
    }
    
    
}
