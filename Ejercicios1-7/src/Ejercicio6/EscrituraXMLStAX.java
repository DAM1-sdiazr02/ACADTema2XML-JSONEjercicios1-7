package Ejercicio6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class EscrituraXMLStAX {
	public static final String FICHERO = "librosstax.xml";

	public static void leerXML() {

		try {
			FileOutputStream fos = new FileOutputStream(FICHERO);
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = factory.createXMLStreamWriter(fos,"UTF-8");
			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement("libros");
			
			xmlStreamWriter.writeStartElement("libro");
			xmlStreamWriter.writeAttribute("ISBN", "239-87-9964-088-4");
			
			xmlStreamWriter.writeStartElement("titulo");
			xmlStreamWriter.writeCharacters("Acceso a Datos");
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("autor");
			xmlStreamWriter.writeCharacters("Acceso a Datos");
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("titulo");
			xmlStreamWriter.writeCharacters("Acceso a Datos");
			xmlStreamWriter.writeEndElement();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
