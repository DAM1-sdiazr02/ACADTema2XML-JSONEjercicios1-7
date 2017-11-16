package ejercicio10;

import java.util.ArrayList;
import java.util.List;

public class listaLibros {
	private List<Libro> lista = new ArrayList<Libro>();

	public listaLibros() {

	}
	
	public List<Libro> getPersonas(){
		return lista;
	}
	
	public void aniadirPersona(Libro libro) {
		lista.add(libro);
	}
}
