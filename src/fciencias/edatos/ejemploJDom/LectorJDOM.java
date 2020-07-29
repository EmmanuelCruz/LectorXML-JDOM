package fciencias.edatos.ejemploJDom;

import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import java.io.FileInputStream;
import org.jdom2.Element;
import java.util.List;

/**
 * Ejemplo de lectura de XML usando JDOM.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020
 * @since Estructuras de datos 2021-1
 */
public class LectorJDOM{

	/**
     * Realiza la lectura de un archivo XML.
     * @param nombre el nombre del archivo a leer.
     * @throws JDOMException si la estructura del archivo xml no es válida.
     * @throws IOException si ocurre un problema al abrir el archivo
     * o en la busqueda de este.
     * @throws IllegalStateException si no existe la raíz del archivo.
     */
	public void lee(String nombre) throws JDOMException, IOException,
		IllegalStateException{
		SAXBuilder constructor = new SAXBuilder();
		Document documento = constructor.build(new FileInputStream(nombre));
		Element raiz = documento.getRootElement();

		List<Element> libros = raiz.getChildren();
		System.out.println("Los libros de la biblioteca son:");

		List<Element> secciones;

		for(Element libro : libros){

			/* Se acceden a los valores de los atributos almacenados en 
            * la etiqueta perteneciente a un libro. */
            System.out.println("Libro: "+libro.getAttributeValue("titulo")+
                "\nAutor: "+libro.getAttributeValue("autor"));

            /* No todos los libros cuentan con tomo o anio. Así que se verifica
            * si existen esos atributos. */
            if(libro.getAttributeValue("tomo") != null)
                System.out.println("Tomo: "+libro.getAttributeValue("tomo"));
            if(libro.getAttributeValue("anio") != null)
                System.out.println("Año: "+libro.getAttributeValue("anio"));

            // Se almacena en secciones una lista con las secciones del libro en cuestión.
            secciones = libro.getChildren();

            // Se imprimen los atibutos del libro, así como el contenido de las etiquetas.
            for(Element seccion : secciones){
                System.out.println("    - Sección "+seccion.getAttributeValue("numero")
                    +": "+seccion.getValue());
            } // Termina for each

            System.out.println();
		}
	}

	public static void main(String[] args) throws JDOMException, IOException,
		IllegalStateException{
		LectorJDOM lector = new LectorJDOM();
		lector.lee("biblioteca.xml");
	}
}
