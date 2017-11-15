package ejercicio8;

import java.util.ArrayList;

public class Libro {
	private String ISBN;
	private String titulo;
	private ArrayList<String> autores = new ArrayList<>(); //Creo un ArrayList ya que puede haber varios autores.
	private String editorial;

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(String ISBN, String titulo, ArrayList<String> autores, String editorial) {
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.autores = autores;
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

	public ArrayList<String> getAutor() {
		return autores;
	}

	public void setAutor(ArrayList<String> autor) {
		this.autores = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		
		return "Libro [ISBN=" + ISBN + ", título=" + titulo + ",autor=" + autores + ", editorial=" + editorial + "]";
	}
}
