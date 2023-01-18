package modelo.dto.kardex;

import java.io.Serializable;
import java.time.LocalDate;

public class InventarioProducto implements Serializable {

    private int item;
    private LocalDate fecha;
    private String concepto;
    private String movimiento;
    private int cantidad;
    private long valorUnitario;
    private long valorTotal;
    private int cantidadTotal;

    /**
     * @param item
     * @param fecha
     * @param concepto
     */
    public InventarioProducto(int item, LocalDate fecha, String concepto, String movimiento, int cantidad, long valorUnitario, long valorTotal, int cantidadTotal) {
        this.item = item;
        this.fecha = fecha;
        this.concepto = concepto;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.cantidadTotal = cantidadTotal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public long getValorUnitario() {
        return valorUnitario;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public int getItem() {
        return item;
    }

    public String getConcepto() {
        return concepto;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }
    
    

    public void setItem(int item) {
        this.item = item;
    }
    

}
