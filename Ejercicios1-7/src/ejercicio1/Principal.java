package ejercicio1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.lang.model.element.Element;
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
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class Principal {

	final static String FICHERO = "contactos.dat";
	static ArrayList<Contacto> contactos = new ArrayList<>();

	public static void rellenarFichero(ObjectOutputStream oos) throws IOException {
		oos.writeObject(new Contacto("Sergio", "Díaz Real", "sdiazr@valledeljerte.es", 678945610));
		oos.writeObject(new Contacto("Javier", "Díaz Real", "jdiazr@valledeljerte.es", 612451789));
		oos.writeObject(new Contacto("Alberto", "Martín Pérez", "amartinp@valledeljerte.es", 678731236));
		oos.writeObject(new Contacto("Jose", "Iniesta Monroy", "jiniestam@valledeljerte.es", 670005610));
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
			System.out.println("Se llegó al final del fichero...!");
		} catch (ClassNotFoundException e) {
			System.out.println("Casteo incorrecto");
		}
	}

	/**
	 * Relleno el ArrayList con los contactos del fichero.
	 * @param ois
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void obtenerContactos(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		Contacto contacto;
		while (true) {
			contacto = (Contacto) ois.readObject();
			contactos.add(contacto);
		}

	}

	/**
	 * Genera el fichero xml 
	 */
	public static void crearFicheroXML() {
		
		
		DocumentBuilderFactory creadorFichero = DocumentBuilderFactory.newInstance();
		try {
		
			
			DocumentBuilder constructor = creadorFichero.newDocumentBuilder();
			DOMImplementation implementacion = constructor.getDOMImplementation();
			Document documento = implementacion.createDocument(null, "Contacto", null);
			documento.setXmlVersion("1.0");
			Iterator it = contactos.iterator();

			while (it.hasNext()) {
				Contacto contacto = (Contacto) it.next();
				
				//Element nodo =  (Element) documento.createElement("contacto");
				org.w3c.dom.Element nodo =  documento.createElement("contacto");

				documento.getDocumentElement().appendChild((Node) nodo);
				crearElementosXml("nombre", contacto.getNombre(), nodo, documento);
				crearElementosXml("apellidos", contacto.getApellidos(), nodo, documento);
				crearElementosXml("email", contacto.getEmail(), nodo, documento);
				crearElementosXml("telefono", String.valueOf(contacto.getTelefono()), nodo, documento);

				
				
			}

			Source origen = new DOMSource(documento);
			
			Result resultado = new StreamResult(new java.io.File("contactos.xml"));
			Transformer transformador = TransformerFactory.newInstance().newTransformer();
			transformador.transform(origen, resultado);
			
			Result console = new StreamResult(System.out);
			transformador.transform(origen, console);
			
			
		} catch (ParserConfigurationException e) {
			System.out.println("Excepción en el Builder.");
		} catch (TransformerConfigurationException e) {
			System.out.println("Excepcion transformador");
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			System.out.println("Excepción al transformar");
		}

	}

	static void crearElementosXml(String datoContacto, String cadena, org.w3c.dom.Element nodo, Document documento) {
		org.w3c.dom.Element elemento =  documento.createElement(datoContacto);
		Text texto = documento.createTextNode(cadena);
		((Node) elemento).appendChild(texto);
		((Node) nodo).appendChild((Node) elemento);
	}

	public static void main(String[] args) {
		// escribirFichero();
		 leerFichero();
crearFicheroXML();
	}

}
