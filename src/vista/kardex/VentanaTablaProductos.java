package vista.kardex;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import Tablas.GestionCeldas;
import Tablas.GestionEncabezadoTabla;
import Tablas.ModeloTabla;
import Tablas.UtilidadesProductos;
import controller.ControllerTablaTransferencias;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.dto.kardex.Contrato;
import modelo.dto.kardex.Producto;

public class VentanaTablaProductos extends JFrame implements MouseListener {

    private JPanel contentPane;
    private JScrollPane scrollPaneTabla;
    private JTable tablaProductos;
    ArrayList<Producto> listaProductos;//lista que simula la informaci�n de la BD

    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    private int filasTabla;
    private int columnasTabla;
    private Contrato contrato;

    /**
     * Create the frame.
     *
     * @throws java.io.IOException
     */
    public VentanaTablaProductos(Contrato contrato) throws IOException {
        this.contrato = contrato;
        setSize(1121, 453);

        iniciarComponentes();
        setLocationRelativeTo(null);
        construirTabla();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void iniciarComponentes() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblTablaPersonas = new JLabel("Tabla Productos");
        lblTablaPersonas.setFont(new Font("Rockwell", Font.BOLD, 25));
        contentPane.add(lblTablaPersonas, BorderLayout.NORTH);

        scrollPaneTabla = new JScrollPane();
        contentPane.add(scrollPaneTabla);

        tablaProductos = new JTable();
        tablaProductos.setBackground(Color.WHITE);
        tablaProductos.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        tablaProductos.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        tablaProductos.setOpaque(false);
        scrollPaneTabla.setViewportView(tablaProductos);

    }

    /**
     * Metodo que permite construir la tabla de personas se crean primero las
     * columnas y luego se asigna la informaci�n
     */
    private void construirTabla() throws IOException {
        listaProductos = contrato.getProductos();

        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Item");
        titulosList.add("Descripción");
        titulosList.add("Referencia");
        titulosList.add("Ubicacion");
        titulosList.add("Proveedor");
        titulosList.add("Cantidad Total");
        titulosList.add("Valor Total");
        titulosList.add(" ");
        titulosList.add(" ");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
		 * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);

    }

    /**
     * Llena la informaci�n de la tabla usando la lista de personas trabajada
     * anteriormente, guardandola en una matriz que se retorna con toda la
     * informaci�n para luego ser asignada al modelo
     *
     * @param titulosList
     * @return
     */
    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {

        /*se crea la matriz donde las filas son dinamicas pues corresponde
		 * a todos los usuarios, mientras que las columnas son estaticas
		 * correspondiendo a las columnas definidas por defecto
         */
        String informacion[][] = new String[listaProductos.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][UtilidadesProductos.ITEM] = listaProductos.get(x).getItem() + "";
            informacion[x][UtilidadesProductos.DESCRIPCION] = listaProductos.get(x).getDescripcion() + "";
            informacion[x][UtilidadesProductos.REFERENCIA] = listaProductos.get(x).getReferencia() + "";
            informacion[x][UtilidadesProductos.UBICACION] = listaProductos.get(x).getProveedor().getEmpresa() + "";
            informacion[x][UtilidadesProductos.PROVEEDOR] = listaProductos.get(x).getProveedor().getRepresentanteLegal().getCelular() + "";
            informacion[x][UtilidadesProductos.CANTIDADTOTAL] = listaProductos.get(x).getValorTotal() + "";
            informacion[x][UtilidadesProductos.VALORTOTAL] = listaProductos.get(x).getValorTotal() + "";
            //se asignan las plabras clave para que en la clase GestionCeldas se use para asignar el icono correspondiente
            informacion[x][UtilidadesProductos.PERFIL] = "PERFIL";
            informacion[x][UtilidadesProductos.EVENTO] = "EVENTO";
        }

        return informacion;
    }

    /**
     * Con los titulos y la informaci�n a mostrar se crea el modelo para poder
     * personalizar la tabla, asignando tama�o de celdas tanto en ancho como en
     * alto as� como los tipos de datos que va a poder soportar.
     *
     * @param titulos
     * @param data
     */
    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new ModeloTabla(data, titulos);
        //se asigna el modelo a la tabla
        tablaProductos.setModel(modelo);

        filasTabla = tablaProductos.getRowCount();
        columnasTabla = tablaProductos.getColumnCount();

        //se asigna el tipo de dato que tendr�n las celdas de cada columna definida respectivamente para validar su personalizaci�n
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.VALORTOTAL).setCellRenderer(new GestionCeldas("numerico"));
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.PERFIL).setCellRenderer(new GestionCeldas("icono"));
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.EVENTO).setCellRenderer(new GestionCeldas("icono"));

        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 3; i++) {//se resta 7 porque las ultimas 7 columnas se definen arriba
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }

        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.setRowHeight(25);//tama�o de las celdas
        tablaProductos.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tama�o de largo para cada columna y su contenido
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.ITEM).setPreferredWidth(130);//fecha
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.DESCRIPCION).setPreferredWidth(380);//objetoContrato
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.REFERENCIA).setPreferredWidth(350);//referenciaContrato
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.UBICACION).setPreferredWidth(130);//nombreEmpresa
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.PROVEEDOR).setPreferredWidth(280);//celularEmpresa
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.VALORTOTAL).setPreferredWidth(80);//valortotal
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.PERFIL).setPreferredWidth(30);//accion perfil
        tablaProductos.getColumnModel().getColumn(UtilidadesProductos.EVENTO).setPreferredWidth(30);//accion evento

        //personaliza el encabezado
        JTableHeader jtableHeader = tablaProductos.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        tablaProductos.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        scrollPaneTabla.setViewportView(tablaProductos);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //capturo fila o columna dependiendo de mi necesidad
        int fila = tablaProductos.rowAtPoint(e.getPoint());
        int columna = tablaProductos.columnAtPoint(e.getPoint());

        /*uso la columna para valiar si corresponde a la columna de perfil garantizando
		 * que solo se produzca algo si selecciono una fila de esa columna
         */
        if (columna == UtilidadesProductos.PERFIL) {
            try {
                //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
                validarSeleccionMouse(fila);
            } catch (IOException ex) {
                Logger.getLogger(VentanaTablaProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (columna == UtilidadesProductos.EVENTO) {//se valida que sea la columna del otro evento
            JOptionPane.showMessageDialog(null, "Evento del otro icono");
        }

    }

    /**
     * Este metodo simularia el proceso o la acci�n que se quiere realizar si se
     * presiona alguno de los botones o iconos de la tabla
     *
     * @param fila
     */
    private void validarSeleccionMouse(int fila) throws IOException {
        UtilidadesProductos.filaSeleccionada = fila;

        //teniendo la fila entonces se obtiene el objeto correspondiente para enviarse como parammetro o imprimir la informaci�n
        Producto producto = contrato.buscarProductoDescripcion(tablaProductos.getValueAt(fila, UtilidadesProductos.DESCRIPCION).toString());
        producto.initDatos();
        ControllerTablaTransferencias cp = new ControllerTablaTransferencias(producto.getItem(), contrato);
        
    }

    //estos metododos pueden ser usados dependiendo de nuestra necesidad, por ejemplo para cambiar el tama�o del icono al ser presionado
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
