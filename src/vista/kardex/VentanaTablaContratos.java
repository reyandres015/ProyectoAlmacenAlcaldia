package vista.kardex;

import controller.ControllerIngresoBusquedaProducto;
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
import Tablas.UtilidadesContratos;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.dao.ContratoDao;
import modelo.dto.kardex.Contrato;

public class VentanaTablaContratos extends JFrame implements MouseListener {

    private JPanel contentPane;
    private JScrollPane scrollPaneTabla;
    private JTable tablaContratos;
    DecimalFormat formato = new DecimalFormat("¤#,###");
    ArrayList<Contrato> listaPersonas;//lista que simula la informaci�n de la BD

    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    private int filasTabla;
    private int columnasTabla;

    /**
     * Create the frame.
     * @throws java.io.IOException
     */
    public VentanaTablaContratos() throws IOException {
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

        JLabel lblTablaPersonas = new JLabel("Tabla Contratos");
        lblTablaPersonas.setFont(new Font("Rockwell", Font.BOLD, 25));
        contentPane.add(lblTablaPersonas, BorderLayout.NORTH);

        scrollPaneTabla = new JScrollPane();
        contentPane.add(scrollPaneTabla);

        tablaContratos = new JTable();
        tablaContratos.setBackground(Color.WHITE);
        tablaContratos.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        tablaContratos.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        tablaContratos.setOpaque(false);
        scrollPaneTabla.setViewportView(tablaContratos);

    }

    /**
     * Metodo que permite construir la tabla de personas se crean primero las
     * columnas y luego se asigna la informaci�n
     */
    private void construirTabla() throws IOException {

        listaPersonas = consultarListaContratos();

        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Fecha");
        titulosList.add("Objeto");
        titulosList.add("Concepto");
        titulosList.add("Empresa");
        titulosList.add("Celular");
        titulosList.add("Valor Total");
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
     * Permite simular el llenado de personas en una lista que posteriormente
     * alimentar� la tabla
     *
     * @return
     */
    private ArrayList<Contrato> consultarListaContratos() throws IOException {
        ContratoDao cD = new ContratoDao();
        return cD.getContratos();
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
        String informacion[][] = new String[listaPersonas.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][UtilidadesContratos.FECHA] = listaPersonas.get(x).getFecha() + "";
            informacion[x][UtilidadesContratos.OBJETO] = listaPersonas.get(x).getObjeto() + "";
            informacion[x][UtilidadesContratos.REFERENCIA] = listaPersonas.get(x).getReferencia() + "";
            informacion[x][UtilidadesContratos.EMPRESA] = listaPersonas.get(x).getProveedor().getEmpresa() + "";
            informacion[x][UtilidadesContratos.CELULAR] = listaPersonas.get(x).getProveedor().getRepresentanteLegal().getCelular() + "";
            informacion[x][UtilidadesContratos.VALORTOTAL] = formato.format(listaPersonas.get(x).getValorTotal()) + "";
            //se asignan las plabras clave para que en la clase GestionCeldas se use para asignar el icono correspondiente
            informacion[x][UtilidadesContratos.PERFIL] = "PERFIL";
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
        tablaContratos.setModel(modelo);

        filasTabla = tablaContratos.getRowCount();
        columnasTabla = tablaContratos.getColumnCount();

        //se asigna el tipo de dato que tendr�n las celdas de cada columna definida respectivamente para validar su personalizaci�n
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.VALORTOTAL).setCellRenderer(new GestionCeldas("numerico"));
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.PERFIL).setCellRenderer(new GestionCeldas("icono"));

        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 2; i++) {//se resta 7 porque las ultimas 7 columnas se definen arriba
            tablaContratos.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }

        tablaContratos.getTableHeader().setReorderingAllowed(false);
        tablaContratos.setRowHeight(25);//tama�o de las celdas
        tablaContratos.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tama�o de largo para cada columna y su contenido
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.FECHA).setPreferredWidth(250);//fecha
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.OBJETO).setPreferredWidth(380);//objetoContrato
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.REFERENCIA).setPreferredWidth(350);//referenciaContrato
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.EMPRESA).setPreferredWidth(280);//nombreEmpresa
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.CELULAR).setPreferredWidth(280);//celularEmpresa
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.VALORTOTAL).setPreferredWidth(150);//valortotal
        tablaContratos.getColumnModel().getColumn(UtilidadesContratos.PERFIL).setPreferredWidth(30);//accion perfil

        //personaliza el encabezado
        JTableHeader jtableHeader = tablaContratos.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        tablaContratos.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        scrollPaneTabla.setViewportView(tablaContratos);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //capturo fila o columna dependiendo de mi necesidad
        int fila = tablaContratos.rowAtPoint(e.getPoint());
        int columna = tablaContratos.columnAtPoint(e.getPoint());

        /*uso la columna para valiar si corresponde a la columna de perfil garantizando
		 * que solo se produzca algo si selecciono una fila de esa columna
         */
        if (columna == UtilidadesContratos.PERFIL) {
            try {
                //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
                validarSeleccionMouse(fila);
            } catch (IOException ex) {
                Logger.getLogger(VentanaTablaContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Este metodo simularia el proceso o la acci�n que se quiere realizar si se
     * presiona alguno de los botones o iconos de la tabla
     *
     * @param fila
     */
    private void validarSeleccionMouse(int fila) throws IOException {
        UtilidadesContratos.filaSeleccionada = fila;

        //teniendo la fila entonces se obtiene el objeto correspondiente para enviarse como parammetro o imprimir la informaci�n
        ContratoDao miContrato = new ContratoDao();
        Contrato contrato = miContrato.buscarContratoReferencia(tablaContratos.getValueAt(fila, UtilidadesContratos.REFERENCIA).toString());
        ControllerIngresoBusquedaProducto cI = new ControllerIngresoBusquedaProducto(contrato);
        dispose();
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
