package modelo.dto.kardex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Serializable {

    private int item;
    private final String descripcion;
    private final String referencia;
    private final String ubicacion;
    private final String proveedor;
    private int cantidadTotal;
    private long valorTotal;
    private final String metodo;
    private ArrayList<InventarioProducto> inventarios = new ArrayList<>();
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private final String filePath;

    public Producto(int item, String descripcion, String referencia, String ubicacion, String metodo, String proveedor) {
        super();
        this.item = item;
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.ubicacion = ubicacion;
        this.proveedor = proveedor;
        this.metodo = metodo;
        this.filePath = "C:\\Users\\reyan\\Documents\\NetBeansProjects\\ProyectoAlmacen\\BaseDatos\\inventarios\\inventario" + descripcion + ".dat";
        initDatos();
    }

    public void initDatos() {
        File file = new File(filePath);
        if (file.isFile()) {
            try {
                this.entrada = new ObjectInputStream(new FileInputStream(filePath));
                this.inventarios = (ArrayList<InventarioProducto>) (List<InventarioProducto>) entrada.readObject();
                this.entrada.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                guardar();
            }
        }
    }

    public void guardar() {
        try {
            this.salida = new ObjectOutputStream(new FileOutputStream(filePath));
            this.salida.writeObject(inventarios);
            this.salida.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean crearRegistro(InventarioProducto inventario) {
        return inventarios.add(inventario);
    }

    public boolean entradaProducto(int cantidadEntrada, long valorTotalEntrada) {
        this.cantidadTotal = (cantidadEntrada + this.cantidadTotal);
        this.valorTotal = (this.valorTotal + valorTotalEntrada);
        return true;
    }

    public boolean salidaProducto(int cantidadSalida, long valorTotalSalida) {
        if ((this.cantidadTotal - cantidadSalida) < 0) {
            return false;
        } else {
            this.cantidadTotal = this.cantidadTotal - cantidadSalida;
            this.valorTotal = this.valorTotal - valorTotalSalida;
            return true;
        }
    }

    public int getItem() {
        return item;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getMetodo() {
        return metodo;
    }

    public ArrayList<InventarioProducto> getInventarios() {
        return inventarios;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int tamañoArreglo() {
        return inventarios.size();
    }
}
