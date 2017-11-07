package ejercicio1;

import java.io.Serializable;

public class Contacto implements Serializable {
	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;

	public Contacto() {
		// TODO Auto-generated constructor stub
	}

	public Contacto(String nom, String ape, String correo, int numer) {
		nombre = nom;
		apellidos = ape;
		email = correo;
		telefono = numer;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Nombre-->" + nombre + ".\nApellidos-->" + apellidos + ".\nEmail-->" + email + ".\nTeléfono-->"
				+ telefono + ".";
	}
}
