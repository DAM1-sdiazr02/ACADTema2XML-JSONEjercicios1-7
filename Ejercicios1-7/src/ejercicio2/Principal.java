package ejercicio2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Principal {
	final static String FICHERO = "libros.dat";

	public static void rellenarFichero(ObjectOutputStream oos) throws IOException {
		oos.writeObject(new Libro("123456", "El Quijote", "Cervantes", "Anaya"));
		oos.writeObject(new Libro("234567", "Harry Potter", "J.K Rowlling", "Editorial2"));
		oos.writeObject(new Libro("345678", "El Codigo Da Vinci", "Dam Brown", "Editorial3"));
		oos.writeObject(new Libro("Jose", "Los pilares de la tierra", "Ken Follett", "Editorial4"));
	}

	public static void escribirFichero() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(FICHERO);
			oos = new ObjectOutputStream(fos);
			rellenarFichero(oos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static ArrayList<Libro> obtenerLibros(ObjectInputStream ois, ArrayList<Libro> libros)
			throws ClassNotFoundException, IOException {
				while (true) {
			libros.add((Libro) ois.readObject());
		}
	}

	/**
	 * 
	 * @param libros
	 */
	public static void crearLibrosDesdeXML(ArrayList<Libro> libros) {
		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

			DocumentBuilder constructor;
			constructor = fabrica.newDocumentBuilder();

			DOMImplementation implementacion = constructor.getDOMImplementation();

			Document documento = implementacion.createDocument(null, "libros", null);
			documento.setXmlVersion("1.0");

			Iterator it = libros.iterator();

			while (it.hasNext()) {// Mientras queden libros en la lista...
				Libro aux = (Libro) it.next(); // Obtengo uno.

				Element nodo = documento.createElement("libro"); // Creo el nodo
				nodo.setAttribute("ISBN", aux.getISBN()); // Le inserto un atributo...
				
				documento.getDocumentElement().appendChild(nodo); //Cuelgo el nodo del documento.
				crearElemento("titulo", aux.getTitulo(), nodo, documento); //Creo un elemento titulo, le asigno un valor y lo cuelgo del nodo.
				crearElemento("autor", aux.getAutor(), nodo, documento);
				crearElemento("editorial", aux.getEditorial(), nodo, documento);
			}

			Source source = new DOMSource(documento);
			Result result = new StreamResult(new java.io.File("libros.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	public static void crearElemento(String elemento, String valor, Element nodo, Document documento) {
		Element elem = documento.createElement(elemento);// Creamos un hijo
		Text texto = documento.createTextNode(valor); // Le datos el valor al campo
		elem.appendChild(texto); // Le asociamos al atributo elemento.
		nodo.appendChild(elem); // Añadimos el nodo al elemento.
	}

	public static void leerFichero() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<Libro> libros = new ArrayList<>();

		try {
			fis = new FileInputStream(FICHERO);
			ois = new ObjectInputStream(fis);
			obtenerLibros(ois, libros);
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("Fin de lectura del fichero...");
		} catch (ClassNotFoundException e) {
			System.out.println("Casteo incorrecto de la clase.");
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		crearLibrosDesdeXML(libros);

	}

	public static void main(String[] args) {
		// escribirFichero();
		leerFichero();
	}
}
