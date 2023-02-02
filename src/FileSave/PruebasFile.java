/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FileSave;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author ubuntu
 */
public class PruebasFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        File carpeta = new File("BaseDatos");
        carpeta.mkdir();
        carpeta = new File("Productos");
        carpeta.mkdir();
        
        File datosProducto = new File(carpeta.getAbsolutePath() + "/nuevo.dat");
        datosProducto.createNewFile();
    }
    
}
