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
import modelo.dto.kardex.Producto;

/**
 *
 * @author reyan
 */
public class ProductoDao extends FileSave implements Serializable{

    private ArrayList<Producto> productos = new ArrayList<>();
    private String filePath;

    public ProductoDao() throws IOException{
        this.filePath = fileOrigin + fileSeparator + "Productos" + fileSeparator + "TotalProductos.dat";
        initDatos();
    }
    
    public ProductoDao(String referencia) throws IOException{
        this.filePath = fileOrigin + fileSeparator + "Productos" + fileSeparator + "Productos" + referencia + ".dat";
        initDatos();
    }
    
    public void initDatos() {
        File file = new File(filePath);
        if (file.isFile()) {
            try {
                this.entrada = new ObjectInputStream(new FileInputStream(filePath));
                this.productos = (ArrayList<Producto>) (List<Producto>) entrada.readObject();
                this.entrada.close();
                System.out.println("se leyo");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("no se leyo");
            }
        }
    }

    public void guardar() {
        try {
            this.salida = new ObjectOutputStream(new FileOutputStream(filePath));
            this.salida.writeObject(productos);
            this.salida.close();
            System.out.println("se guardo");
        } catch (IOException e) {
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
}
