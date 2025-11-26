package Ventanas;

public class Usuario {
	private int id_usuario;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contraseña;
	
	
	public Usuario(String nombre, String apellido, String usuario, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public Usuario(String nombre, String apellido, String usuario) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		
	}
	

	public Usuario(int id_usuario, String nombre, String apellido, String usuario, String contraseña) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + "]";
	}
	

}
