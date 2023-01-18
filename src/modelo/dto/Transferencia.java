package modelo.dto;

import modelo.dto.Persona;
import java.io.Serializable;
import java.time.LocalDate;

public class Transferencia implements Serializable{
	private LocalDate fecha;
	private Persona elaborada;
	private Persona entregada;
	private Persona recibido;
	private String lugarEntrega;
}
