/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import FileSave.FileSave;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Persona;
import modelo.dto.kardex.Contrato;
import modelo.dto.kardex.Producto;
import modelo.dto.kardex.Proveedor;

/**
 *
 * @author reyan
 */
public class ContratoDao extends FileSave{

    private ArrayList<Contrato> contratos = new ArrayList<>();
    private String filePath = fileOrigin + fileSeparator + "Contratos.dat";
    
    public ContratoDao() throws IOException {
        super();
        initDatos();
    }

    public void initDatos() {
        
        File file = new File(filePath);
        if (file.isFile()) {
            try {
                this.entrada = new ObjectInputStream(new FileInputStream(filePath));
                this.contratos = (ArrayList<Contrato>) (List<Contrato>) entrada.readObject();
                this.entrada.close();
            } catch (Exception e) {
                System.out.println("no se leyo");
            }
        }
    }

    public void guardar() {
        try {
            this.salida = new ObjectOutputStream(new FileOutputStream(filePath));
            this.salida.writeObject(contratos);
            this.salida.close();
        } catch (Exception e) {
            System.out.println("no se guardo");
        }
    }

    public boolean ingresarContrato(Contrato c) {
        return contratos.add(c);
    }

    public Contrato buscarContratoConcepto(String concepto) {
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getConcepto().equals(concepto)) {
                return contratos.get(i);
            }
        }
        return null;
    }

    public Persona buscarProveedores(String empresa) {
        for (Contrato contrato : contratos) {
            if (contrato.getProveedor().getEmpresa().equals(empresa)) {
                return contrato.getProveedor().getRepresentanteLegal();
            }
        }
        return null;
    }

    public Persona buscarProveedores(int documento) {
        for (Contrato contrato : contratos) {
            if (contrato.getProveedor().getDocumento() == documento) {
                return contrato.getProveedor().getRepresentanteLegal();
            }
        }
        return null;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

}
