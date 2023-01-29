import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author - Roberto García Ávila
 * @version - 1.0
 * @date - 2023-01-29
 * @description - Util to retrieve the logger name from a java source file
 * @TODO - Add retrieval of path from command line
 * @TODO - Add dynamic interpretation of file or directory and recursive search if directory
 */
class Log4JPropertiesBySources {
    public static void main(String[] args) {
	
	// load file ejemplo.java to String variable
	String texto = getFile("../test/ejemplo.java");
	
	if (texto != null && !texto.isEmpty()){
		System.out.println("OK Tenemos el fichero cargado");

		// check if texto contains logger name
		if (texto.contains("LogFactory.getLog")) {
		    System.out.println("Tiene logger");
		    String logger = getLogger(texto);
		    System.out.println("logger: " + logger);
		} else {
		}
	} else {
		System.out.println("No tenemos el fichero");
	}

    }

    private static String getFile(String path) {
	String texto = "";
	try {
	    texto = new String(Files.readAllBytes(Paths.get(path)));
	} catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
   	}
    	return texto;
    }	


    private static String getLogger(String texto) {
	String logger = "";
	for (String linea : texto.split("\\r?\\n")) {
	    if (linea.contains("LogFactory.getLog")) {
		logger = linea.split("\\(")[1].split("\\)")[0];
		break;
	    }
    	}
	return logger;
    }
}
