package Ventanas;

import java.util.ArrayList;

public class Entrenador extends Usuario{
	
	private Deporte deporte;
	private ArrayList<Deportista>deportistas;

	public Entrenador(String nombre, String apellidos, String usuario, String contraseña, Deporte deporte) {
		super(nombre, apellidos, usuario, contraseña);
		this.deporte = deporte;
		this.deportistas = new ArrayList<Deportista>();
		// TODO Auto-generated constructor stub
	}
	
	public void agregarDeportista(Deportista d) {
		deportistas.add(d);
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public ArrayList<Deportista> getDeportistas() {
		return deportistas;
	}

	public void setDeportistas(ArrayList<Deportista> deportistas) {
		this.deportistas = deportistas;
	}

}
