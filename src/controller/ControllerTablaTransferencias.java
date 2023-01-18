package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ProductoDao;
import modelo.dto.kardex.*;
import vista.kardex.UITablaTransferencias;

public class ControllerTablaTransferencias implements ActionListener {

    private UITablaTransferencias vistaTabla;

    private Producto producto;
    private ProductoDao modeloProducto;
    private DefaultTableModel modeloTabla;

    public ControllerTablaTransferencias(int i) {
        this.vistaTabla = new UITablaTransferencias();
        this.modeloProducto = new ProductoDao();
        this.producto = modeloProducto.getProductos().get(i);
        this.modeloTabla = (DefaultTableModel) this.vistaTabla.tablaInformacionProducto.getModel();
        datosProducto();
        actualizarTabla();
        this.vistaTabla.ingresarRegistroBtn.addActionListener(this);
        this.vistaTabla.setVisible(true);

    }

    public void datosProducto() {
        vistaTabla.productoLabel.setText(producto.getDescripcion());
        vistaTabla.referenciaProducto.setText(producto.getReferencia());
        vistaTabla.ubicacionLabel.setText(producto.getUbicacion());
        vistaTabla.proveedorLabel.setText(producto.getProveedor());
        vistaTabla.cantidadTotalLabel.setText(String.valueOf(producto.getCantidadTotal()));
        vistaTabla.metodoLabel.setText(producto.getMetodo());
    }

    public void actualizarTabla() {
        List<InventarioProducto> inventarios = producto.getInventarios();
        if (inventarios != null) {
            int filas = modeloTabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modeloTabla.removeRow(0);
            }

            for (InventarioProducto v : inventarios) {
                LocalDate fecha = v.getFecha();
                DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                String formattedDate = fecha.format(formatter);
                Object fila[] = {v.getItem(), formattedDate, v.getConcepto(), v.getMovimiento(), v.getCantidad(), v.getValorUnitario(), v.getValorTotal(), v.getCantidadTotal()};
                modeloTabla.addRow(fila);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaTabla.ingresarRegistroBtn)) {
            ControllerRegistro cR = new ControllerRegistro(producto.getItem());
            vistaTabla.dispose();
        }
    }
}
