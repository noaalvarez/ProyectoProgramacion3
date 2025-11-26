package Ventanas;

import java.time.LocalDate;

public class Cita {
	
	private LocalDate fecha;
	
	public Cita(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
