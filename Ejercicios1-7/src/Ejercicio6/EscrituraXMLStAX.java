package Ejercicio6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class EscrituraXMLStAX {
	public static final String FICHERO = "librosstax.xml";

	public static String pideCadena(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.print(cadena + ": ");
		return entrada.nextLine();
	}

	public static void escribirXML() {

		try {
			FileOutputStream fos = new FileOutputStream(FICHERO);
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = factory.createXMLStreamWriter(fos, "UTF-8");
			
			//ABRO DOCUMENTO
			xmlStreamWriter.writeStartDocument();
			//Abro nodo
			xmlStreamWriter.writeStartElement("libros");

			xmlStreamWriter.writeStartElement("libro");
			xmlStreamWriter.writeAttribute("ISBN", "239-87-9964-088-4");

			xmlStreamWriter.writeStartElement("titulo");
			xmlStreamWriter.writeCharacters("Acceso a Datos");
			xmlStreamWriter.writeEndElement();

			do {
				xmlStreamWriter.writeStartElement("autor");
				xmlStreamWriter.writeCharacters(pideCadena("Introduce el nombre del autor"));
				xmlStreamWriter.writeEndElement();
			} while (pideCadena("¿Otro autor?").equalsIgnoreCase("si"));
			
			xmlStreamWriter.writeStartElement("editorial");
			xmlStreamWriter.writeCharacters("Garceta");
			xmlStreamWriter.writeEndElement();

			//Cierro nodo
			xmlStreamWriter.writeEndElement();
			//CIERRO DOCUMENTO
			xmlStreamWriter.writeEndDocument();
			
			System.out.println("Fichero ---> "+FICHERO+" creado correctamente.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
