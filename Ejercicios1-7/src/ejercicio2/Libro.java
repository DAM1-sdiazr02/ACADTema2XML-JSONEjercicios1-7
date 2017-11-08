package ejercicio2;

import java.io.Serializable;

public class Libro implements Serializable{
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(String ISBN, String titulo, String autor, String editorial) {
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
