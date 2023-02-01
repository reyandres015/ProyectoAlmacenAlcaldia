/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Transferencia;
import modelo.dto.kardex.Producto;

/**
 *
 * @author reyan
 */
public class ProductoDao {
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Transferencia> transferencias;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String filePath = "C:\\Users\\reyan\\Documents\\NetBeansProjects\\ProyectoAlmacen\\BaseDatos\\productos\\productos" + LocalDate.now().getYear() + ".dat";
    
    public ProductoDao() {
        initDatos();
    }
    
    public void initDatos(){
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
    
    public boolean ingresarProducto(Producto producto){
        return productos.add(producto);
    }
    
    public Producto buscarProductoDescripcion(String descripcion){
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getDescripcion().equals(descripcion)){
                return productos.get(i);
            }
        }
        return null;
    }
    
    public Producto buscarProductoReferencia(String referencia){
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getReferencia().equals(referencia)){
                return productos.get(i);
            }
        }
        return null;
    }
    public int tamañoArreglo(){
        return productos.size();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    
}
