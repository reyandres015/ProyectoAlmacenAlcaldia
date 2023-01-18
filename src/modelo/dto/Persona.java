package modelo.dto;

import java.io.Serializable;

public class Persona implements Serializable{
	private String nombre;
	private String cedula;
	private String cargo;
	private String celular;
	/**
	 * @param nombre
	 * @param cedula
	 * @param cargo
	 * @param celular
	 */
	public Persona(String nombre, String cedula, String cargo, String celular) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.cargo = cargo;
		this.celular = celular;
	}
	
	
}
