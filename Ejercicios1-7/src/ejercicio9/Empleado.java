package ejercicio9;

public class Empleado {
	private int id;
	private String nombre;
	private String apellidos;
	private int salario;
	private String cargo;
	private Direccion direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id->"+id+" | Nombre->"+nombre+" | Apellidos->"+apellidos+" | Salario->"+salario+" | Cargo->"+cargo+"]\n\tDirección->"+direccion+"\n";
	}
}
