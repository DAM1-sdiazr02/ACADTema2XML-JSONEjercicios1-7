package ejercicio7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import ejercicio2.Libro;

public class EscrituraXMLStAX {
	static ArrayList<Contacto> contactos;
	final static String FICHERO = "contactosStAX.dat";
	final static String FICHEROXML = "contactosStAX.xml";

	/**
	 * Usado para escribir en el fichero los contactos.
	 * @param oos flujo de escritura.
	 * @throws IOException excepción de error al escribir fichero.
	 */
	public static void rellenarFichero(ObjectOutputStream oos) throws IOException {
		oos.writeObject(new Contacto("Sergio", "Diaz Real", "sergio@gmail.com", 123456789));
		oos.writeObject(new Contacto("David", "Martín Monroy", "david@gmail.com", 234567890));
		oos.writeObject(new Contacto("Marco", "Hernandez Valiente", "marco@gmail.com", 345678901));
		oos.writeObject(new Contacto("Jose", "Díaz Maestro", "jose@gmail.com", 111222333));
	}

	/**
	 * Encargado de abrir y cerrar los flujos de escritura 
	 */
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

	/**
	 * Encargado de abrir y cerrar los flujos de lectura.
	 */
	public static void leerFichero() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(FICHERO);
			ois = new ObjectInputStream(fis);
			obtenerContactos(ois);
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("Fin de lectura del fichero...");
		} catch (ClassNotFoundException e) {
			System.out.println("casteo incorrecto de la clase.");
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
		escribirXML();
	}

	/**
	 * Obtiene los contactos y los añade en una lista.
	 * @param ois flujo de lectura.
	 * @throws ClassNotFoundException si se castea la clase incorrectamente
	 * @throws IOException al llegar al final del fichero.
	 */
	public static void obtenerContactos(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		contactos = new ArrayList<>();
		while (true) {
			contactos.add((Contacto) ois.readObject());
		}

	}

	/**
	 * Encargado de abrir y cerrar los flujos de escritura
	 */
	public static void escribirXML() {
		
		try {
			FileOutputStream fos = new FileOutputStream(FICHEROXML);
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = factory.createXMLStreamWriter(fos, "UTF-8");

			// ABRO DOCUMENTO
			xmlStreamWriter.writeStartDocument();
			// Abro nodo
			xmlStreamWriter.writeStartElement("contactos");

			for (int i = 0; i < contactos.size(); i++) {
				
				xmlStreamWriter.writeStartElement("contacto");

				xmlStreamWriter.writeStartElement("nombre");
				xmlStreamWriter.writeCharacters(contactos.get(i).getNombre());
				xmlStreamWriter.writeEndElement();

				xmlStreamWriter.writeStartElement("apellidos");
				xmlStreamWriter.writeCharacters(contactos.get(i).getApellidos());
				xmlStreamWriter.writeEndElement();

				xmlStreamWriter.writeStartElement("correo");
				xmlStreamWriter.writeCharacters(contactos.get(i).getEmail());
				xmlStreamWriter.writeEndElement();

				xmlStreamWriter.writeStartElement("telefono");
				xmlStreamWriter.writeCharacters(String.valueOf(contactos.get(i).getTelefono()));
				xmlStreamWriter.writeEndElement();

				xmlStreamWriter.writeEndElement();
				
			}

			// Cierro nodo
			xmlStreamWriter.writeEndElement();
			// CIERRO DOCUMENTO
			xmlStreamWriter.writeEndDocument();

			xmlStreamWriter.flush();
			xmlStreamWriter.close();

			System.out.println("Fichero ---> " + FICHEROXML + " creado correctamente.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
