package modelo.dto;

import java.io.Serializable;

public class Persona implements Serializable{
	private final String nombre;
	private final String indentificacion;
        private final String celular;
        private final String direccion;
        private final String correo;

    /**
     * @param nombre
     * @param cedula
     * @param celular
     * @param direccion
     * @param correo
     */
    public Persona(String nombre, String identificacion, String celular, String direccion, String correo) {
        this.nombre = nombre;
        this.indentificacion = identificacion;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return indentificacion;
    }

    public String getCelular() {
        return celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }
    
    
	
	
	
}
