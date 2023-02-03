/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FileSave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ubuntu
 */
public class FileSave {

    protected ObjectInputStream entrada;
    protected ObjectOutputStream salida;
    protected String fileOrigin;
    protected String fileSeparator = System.getProperty("file.separator");
    
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.classpath"));
    }

    public FileSave() throws IOException {
        File carpeta = new File(System.getProperty("user.home") + fileSeparator + "Documents" + fileSeparator + "BaseDatos");
        carpeta.mkdir();
        this.fileOrigin = carpeta.getAbsolutePath();
        
        carpeta = new File(fileOrigin + fileSeparator + "Productos");
        carpeta.mkdir();

        carpeta = new File(fileOrigin + fileSeparator + "Inventarios");
        carpeta.mkdir();

    }

}
