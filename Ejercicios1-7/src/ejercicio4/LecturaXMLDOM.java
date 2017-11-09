package ejercicio4;

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
	private static final String FICHERO = "libros.xml";

	public static void leerFicheroXML() {

		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			Document documento = constructor.parse(new File(FICHERO));

			documento.getDocumentElement().normalize();
			NodeList libros = documento.getElementsByTagName("libro");

			// recorremos la lista
			for (int i = 0; i < libros.getLength(); i++) {
				Node libro = libros.item(i); // Creo un nodo auxiliar contacto en el asigno el elemento por el que
												// itera.
				Element elem = (Element) libro; // Lo casteo a elemento para obtener los datos.
				// Se obtienen los elementos del nodo.
				System.out.println("Titulo: " + getNodo("titulo", elem) + "-->ISBN:" + getAtributo("ISBN", elem));
				System.out.println("Autor: " + getNodo("autor", elem));
				System.out.println("Editorial: " + getNodo("editorial", elem) + "\n");
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

	public static String getAtributo(String etiqueta, Element elem) {
		String aux = elem.getAttribute(etiqueta); //Obtengo el elemento.
		return aux;
	}

	public static String getNodo(String etiqueta, Element elemento) {
		Node aux = elemento.getElementsByTagName(etiqueta).item(0).getFirstChild(); // Obtengo por etiqueta el hijo del
																					// primer item.
		return aux.getTextContent();
	}
}
