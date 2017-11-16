package ejercicio10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class crearXMLSerializando {

	public static void serializarXML(listaLibros listaLibros) {
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		
		//Hacemos alias para cambiar el nombre por defecto de las etiquetas.
		System.out.println("Falta hacer los alias");
		
		try {
			xstream.toXML(listaLibros,  new FileOutputStream("personas10.xml"));
			System.out.println("Se ha creado el fichero XML.");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		}
	}
	
	
	public static void serializandoAXML() {
		File fichero = new File("libros10.dat");
		listaLibros listaLibros = null;

		try {
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			System.out.println("Creando fichero XML...");
			
			listaLibros = new listaLibros();
			while(true) {
				Libro libro = (Libro) ois.readObject();
				listaLibros.aniadirPersona(libro);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Casteo incorrecto de la clase.");
		}
		serializarXML(listaLibros);
	}
}
