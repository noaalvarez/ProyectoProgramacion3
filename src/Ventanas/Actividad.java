package Ventanas;

import java.time.LocalDate;

public class Actividad {
	
	private LocalDate fecha;
	private String nombre;
	private Deporte deporte;
	
	public Actividad(LocalDate fecha, String nombre, Deporte deporte) {
		super();
		this.fecha = fecha;
		this.nombre = nombre;
		this.deporte = deporte;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Deporte getDeporte() {
		return deporte;
	}
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	
	

}
