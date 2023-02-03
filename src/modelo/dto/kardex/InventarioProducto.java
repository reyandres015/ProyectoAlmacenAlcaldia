package modelo.dto.kardex;

import java.io.Serializable;
import java.time.LocalDate;

public class InventarioProducto implements Serializable {

    private int item;
    private String fecha;
    private String concepto;
    private String movimiento;
    private int cantidad;
    private long valorUnitario;
    private long valorTotal;
    private int cantidadDisponible;

    /**
     *
     * @param item
     * @param fecha
     * @param concepto
     * @param movimiento
     * @param cantidad
     * @param valorUnitario
     * @param valorTotal
     * @param cantidadDisponible
     */
    public InventarioProducto(int item, String fecha, String concepto, String movimiento, int cantidad, long valorUnitario, long valorTotal, int cantidadDisponible) {
        this.item = item;
        this.fecha = fecha;
        this.concepto = concepto;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getFecha() {
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

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    

    public void setItem(int item) {
        this.item = item;
    }
    

}
