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

	public static void leerFicheroXML() {

		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			Document documento = constructor.parse(new File(FICHERO));

			documento.getDocumentElement().normalize();
			NodeList contactos = documento.getElementsByTagName("contacto");
			
			//recorremos la lista
			for (int i = 0; i < contactos.getLength(); i++) {
				Node contacto = contactos.item(i);
				Element elem = (Element) contacto;
				//Se obtienen los elementos del nodo.
				System.out.println("nombre: ");
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
	public static String getNodo(String etiqueta, Elem elemento) {
		
	}
}
