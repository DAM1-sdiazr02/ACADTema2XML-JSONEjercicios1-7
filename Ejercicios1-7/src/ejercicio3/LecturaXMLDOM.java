package ejercicio3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LecturaXMLDOM {
	private static final String FICHERO = "contactos.xml";

	/**
	 * Lee un fichero XML con DOM
	 */
	public static void leerFicheroXML() {

		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			Document documento = constructor.parse(new File(FICHERO));

			documento.getDocumentElement().normalize();
			NodeList contactos = documento.getElementsByTagName("contactos");

			// recorremos la lista
			for (int i = 0; i < contactos.getLength(); i++) {
				Node contacto = contactos.item(i);	//Creo un nodo auxiliar contacto en el asigno el elemento por el que itera.
				Element elem = (Element) contacto;	//Lo casteo a elemento para obtener los datos.
				// Se obtienen los elementos del nodo.
				System.out.println("Nombre: "+getNodo("nombre", elem));
				System.out.println("Apellidos: "+getNodo("apellidos", elem));
				System.out.println("Email: "+getNodo("email", elem));
				System.out.println("Teléfono: "+getNodo("telefono", elem));
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getNodo(String etiqueta, Element elemento) {
		Node aux = elemento.getElementsByTagName(etiqueta).item(0).getFirstChild(); //Obtengo por etiqueta el hijo del primer item.
		return aux.getTextContent();
	}
}
