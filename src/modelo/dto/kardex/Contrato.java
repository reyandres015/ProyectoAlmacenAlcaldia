/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto.kardex;

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

/**
 *
 * @author reyan
 */
public class Contrato extends FileSave implements Serializable{

    private String fecha;
    private String referencia;
    private String objeto;
    private Proveedor proveedor;
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private String filePath;

    public Contrato(String fecha, String objeto, String referencia, Proveedor proveedor) throws IOException {
        this.fecha = fecha;
        this.objeto = objeto;
        this.referencia = referencia;
        this.proveedor = proveedor;
        this.filePath = fileOrigin + fileSeparator + "Productos" + fileSeparator + "Productos" + this.referencia + ".dat";
        initDatos();
    }

    public void initDatos() {
        File file = new File(filePath);
        if (file.isFile()) {
            try {
                this.entrada = new ObjectInputStream(new FileInputStream(filePath));
                this.productos = (ArrayList<Producto>) (List<Producto>) entrada.readObject();
                this.entrada.close();
            } catch (Exception e) {
                System.out.println("no se leyo");
            }
        }
    }

    public void guardar() {
        try {
            this.salida = new ObjectOutputStream(new FileOutputStream(filePath));
            this.salida.writeObject(productos);
            this.salida.close();
        } catch (Exception e) {
            System.out.println("no se guardo");
        }
    }

    public boolean ingresarProducto(Producto producto) {
        return productos.add(producto);
    }

    public Producto buscarProductoDescripcion(String descripcion) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getDescripcion().equals(descripcion)) {
                return productos.get(i);
            }
        }
        return null;
    }

    public Producto buscarProductoReferencia(String referencia) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getReferencia().equals(referencia)) {
                return productos.get(i);
            }
        }
        return null;
    }

    public int tamañoArreglo() {
        return productos.size();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObjeto() {
        return objeto;
    }

    public String getReferencia() {
        return referencia;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

}
