
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal para probar el funcionamiento del programa. Esta clase recibe
 * solo un argumento desde la terminal el cual es el nombre de un archivo sin
 * importar el idioma en que este.
 *
 * @author Flores González Luis Brandon.
 * @version 2.0
 */
public class Main {

    /**
     * Recibe solo un argumento desde la terminal, este argumento corresponde al
     * nombre de un archivo.
     *
     * @param args args[0] - Corresponde al nombre de un archivo.
     */
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            String nombreArchivo = args[0];
            String texto = "";
            try {
                Scanner scanner = new Scanner(new File(nombreArchivo));
                while (scanner.hasNextLine()) {
                    texto += scanner.nextLine() + " ";
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado :(.\n" + ex.getMessage());
                System.exit(0);
            }
            System.out.println("Archivo: " + nombreArchivo);
            System.out.println("Texto: " + texto);
            CodigoHuffman huffman = new CodigoHuffman(texto);
            //Crea la lista de codificaciones, ordenada ascendentemente por frecuencia.
            ArrayList<Codigo> codigos = huffman.getListaCodigos();
            ArrayList<Codigo> ordenar = new ArrayList<>();
            ordenar.add(codigos.get(0));
            for (int a = 1; a < codigos.size(); a++) {
                Codigo codigo = codigos.get(a);
                boolean semaforo = false;
                for (int b = 0; b < ordenar.size(); b++) {
                    Codigo verificador = ordenar.get(b);
                    //Insertar en lista ordenada por frecuencia ascendente.
                    if (codigo.getFrecuencia() < verificador.getFrecuencia()) {
                        ordenar.add(b, codigo);
                        semaforo = true;
                        break;
                    } //Si la frecuencia es igual, más o menor a la longitud del caracter de la codificación
                    else if (codigo.getFrecuencia() == verificador.getFrecuencia()) {
                        if (codigo.getCodificacion().length()
                                >= verificador.getCodificacion().length()) {
                            ordenar.add(b, codigo);
                            semaforo = true;
                            break;
                        }

                    }
                }
                if (!semaforo) {
                    ordenar.add(codigo);
                }
            }
            //Imprime el resultado de cada caracter.
            for (Codigo codigo : ordenar) {
                System.out.println(codigo.getCaracter() + " "
                        + codigo.getCodificacion() + " " + codigo.getFrecuencia());
            }
            //Imprime el codigo completo de la cadena.
            System.out.println("Cadena codificada: " + huffman.getcadenaCodificada());
        } else {
            System.out.println("Por favor escribe el nombre del archivo.");
        }
        System.exit(0);
    }
}
