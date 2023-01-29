package vista.kardex;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author reyan
 */
public class Fecha {

    public Fecha() {
    }
    public String obtenerFecha(){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        LocalDate fecha = LocalDate.now();
        return fecha.format(formatter);
    }

}
