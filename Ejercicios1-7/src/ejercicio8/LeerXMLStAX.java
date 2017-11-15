package ejercicio8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LeerXMLStAX {

	static final String FICHERO = "librosstax.xml";

	public void leerXMLconStAX() {

		try {
			List<Libro> ListLibros = new ArrayList<>();

			Libro lib = null;
			XMLInputFactory factory = XMLInputFactory.newInstance(); //Creo una instancia del fichero.
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(FICHERO)); //Inicializo el flujo de lectura y referencio al fichero.

			while (reader.hasNext()) {
				int event = reader.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					String nodo = reader.getLocalName();
					switch (nodo) {
					case "libro": {
						lib = new Libro();
						lib.setISBN(reader.getAttributeValue(0));
					}
						break;
					case "titulo":
						lib.setTitulo(reader.getElementText());
						break;
					case "autor": { // Si hay autor, lo añado a la lista.
						lib.getAutor().add(reader.getElementText());
					}
						break;
					case "editorial":
						lib.setEditorial(reader.getElementText());
						break;
					default:
						break;
					}
				}
				if ((event == XMLStreamConstants.END_ELEMENT) && (reader.getLocalName() == "libro")) {
					ListLibros.add(lib);
				}
			}
			for (int i = 0; i < ListLibros.size(); i++) {
				System.out.println(ListLibros.get(i));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
