package ejercicio9;

public class Direccion {
	private String ciudad;
	private String provincia;
	private int cp;
	private String calle;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}
@Override
public String toString() {
	
	return "[Ciudad->"+ciudad+" | Provincia->"+provincia+" | Cp->"+cp+" | Calle->"+calle+"]";
}
}
