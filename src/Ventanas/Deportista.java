package Ventanas;

public class Deportista extends Usuario {
	private Deporte deporte;
	
	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Deportista(String nombre, String apellidos, String usuario, String contraseña, Deporte deporte) {
		super(nombre, apellidos, usuario, contraseña);
		this.deporte = deporte;
	}

	@Override
	public String toString() {
		return "Deportista [deporte=" + deporte + ", getUsuario()=" + getUsuario() + ", getContraseña()="
				+ getContraseña() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	


	
}