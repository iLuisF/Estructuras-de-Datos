
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * En esta clase se implementa los métodos necesarios para leer los datos de un
 * archivo y escribir los datos en un archivo.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class ManejadorArchivos {

    String leerArchivo;

    /**
     * Constructor que solo inicia el nombre de un archivo para ser leido.
     *
     * @param leerArchivo Nombre del archivo para leer.
     */
    public ManejadorArchivos(String leerArchivo) {
        this.leerArchivo = leerArchivo;
    }

    /**
     * Método para escribir en un archivo una cadena dada. Se usa FileWriter.
     *
     * @param cadena Cadena que se escribira en el archivo.
     * @param nombreArchivo Nombre del archivo que se usara para para escribir
     * la cadena. En caso de que no exista se creera y en caso de que si exista
     * se sobreescribira su contenido.
     */
    public void escribir(String cadena, String nombreArchivo) {
        FileWriter escritor = null;
        try {
            escritor = new FileWriter(nombreArchivo);
            escritor.write(cadena);
        } catch (IOException excepcion) {
            System.out.println(excepcion.getMessage());
        } finally {
            if (escritor != null) {
                try {
                    escritor.close();
                } catch (IOException excepcion) {
                    System.out.println(excepcion.getMessage());
                }
            }
        }
    }

    /**
     * Método para leer un archivo y regresar los numeros enteros que contiene.
     * En caso de que no exista el archivo se genera un error el cual se atrapa
     * con un catch.
     *
     * @param tamanio El tamaño del arreglo para guardar los números enteros. En
     * caso de que un archivo contenga 1000000 de números y se ingrese como
     * parametro 50000. Solo se leera esa cantidad de números.
     * @return Un arreglo con los números enteros leidos del archivo. Los
     * números leidos del archivos solo son los primeros N que se especifican en
     * el parametro.
     */
    public Integer[] leerEnteros(int tamanio) {
        Integer[] arreglo = new Integer[tamanio];
        try {
            int i = 1;
            Scanner leer = new Scanner(new File(leerArchivo));
            while (i != tamanio) {
                arreglo[i] = leer.nextInt();
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado :(.\n" + ex.getMessage());
        }
        return arreglo;
    }

    /**
     * Método para leer un archivo y regresar los numeros flotantes que
     * contiene. En caso de que no exista el archivo se genera un error el cual
     * se atrapa con un catch.
     *
     * @param tamanio El tamaño del arreglo para guardar los números flotantes.
     * En caso de que un archivo contenga 1000000 de números y se ingrese como
     * parametro 50000. Solo se leera esa cantidad de números.
     * @return Un arreglo con los números enteros leidos del archivo. Los
     * números leidos del archivos solo son los primeros N que se especifican en
     * el parametro.
     */
    public Float[] leerFlotantes(int tamanio) {
        Float[] arreglo = new Float[tamanio];
        try {
            int i = 1;
            Scanner leer = new Scanner(new File(leerArchivo));
            while (i != tamanio) {
                arreglo[i] = leer.nextFloat();
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado :(.\n" + ex.getMessage());
        }
        return arreglo;
    }

}
