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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Persona;
import modelo.dto.kardex.Contrato;

/**
 *
 * @author reyan
 */
public class ContratoDao extends FileSave implements Serializable {

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
                System.out.println("Se leyo");
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("no se leyo");
            }
        }
    }

    public void guardar() {
        try {
            this.salida = new ObjectOutputStream(new FileOutputStream(filePath));
            this.salida.writeObject(contratos);
            this.salida.close();
            System.out.println("Se guardo");
        } catch (Exception e) {
            System.out.println("no se guardo");
        }
    }

    public boolean ingresarContrato(Contrato c) {
        return contratos.add(c);
    }

    public Contrato buscarContratoReferencia(String referencia) {
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getReferencia().equals(referencia)) {
                return contratos.get(i);
            }
        }
        return null;
    }

    public Persona buscarProveedores(String empresa) {
        for (Contrato contrato : contratos) {
            if (contrato.getProveedor().getEmpresa().equals(empresa)) {
                return contrato.getProveedor().getRepresentanteLegal();
            } else if (contrato.getProveedor().getDocumento().equals(empresa)) {
                return contrato.getProveedor().getRepresentanteLegal();
            }
        }
        return null;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

}
