package ejercicio9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LeerXMLStAX {
	static final String FICHERO = "empleados9.xml";

	public static void leerXMLStAXEMPLEADOS() {

		try {
			List<Empleado> ListEmpleados = new ArrayList<>();
			Empleado empleado = null;
			Direccion direccion = null;
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(FICHERO));

			// Lectura:
			while (reader.hasNext()) {
				int event = reader.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					String nodo = reader.getLocalName();
					switch (nodo) {
					case "empleado":
						empleado = new Empleado();
						empleado.setId(Integer.parseInt(reader.getAttributeValue(0)));
						break;
					case "nombre":
						empleado.setNombre(reader.getElementText());
						break;
					case "apellido":
						empleado.setApellidos(reader.getElementText());
						break;
					case "salario":
						empleado.setSalario(Integer.parseInt(reader.getElementText()));
						break;
					case "cargo":
						empleado.setCargo(reader.getElementText());
						break;
					case "direccion":
						// instancion la direccion.
						direccion = new Direccion();
						break;
					// a�ado los campos a la direccion
					case "ciudad":
						direccion.setCiudad(reader.getElementText());
						break;
					case "provincia":
						direccion.setProvincia(reader.getElementText());
						break;
					case "cp":
						direccion.setCp(Integer.parseInt(reader.getElementText()));
						break;
					case "calle":
						direccion.setCalle(reader.getElementText());
						break;
					default:
						break;
					}
				}
				// Cuando acabe direccion meto el objeto en el empleado.
				if ((event == XMLStreamConstants.END_ELEMENT) && (reader.getLocalName() == "direccion")) {
					empleado.setDireccion(direccion);
				}
				// Cuando acabe empleado lo meto en la lista.
				if ((event == XMLStreamConstants.END_ELEMENT) && (reader.getLocalName() == "empleado")) {
					ListEmpleados.add(empleado);
				}
			}
			
			//Ordeno la coleccion ( sort tiene @override ).
			Collections.sort(ListEmpleados);

			// Muestro los empleados
			for (int i = 0; i < ListEmpleados.size(); i++) {
				if (ListEmpleados.get(i).getSalario() >= 30000) {
					System.out.println(ListEmpleados.get(i));
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
