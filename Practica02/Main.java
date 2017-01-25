
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Esta clase busca calcular el numero de minas adyacentes que tiene una
 * casilla.
 *
 * @author Flores González Luis Brandon
 * @version 1.0
 */
public class Main {

    public Scanner lector;

    /**
     * Metodo para leer filas y columnas, dar formato, calcular minas adyacentes
     * e imprimir resultados.
     *
     * @param archivo Es el archivo que sera leido para tomar el contenido de
     * las filas y columnas.
     */
    public void minas(String archivo) {
        try {
            lector = new Scanner(new File(archivo));
            int a = 1;
            // Saldra del ciclo hasta que lector lea 0 0
            while (true) {
                // Primero lee filas y luego columnas.
                int r = lector.nextInt(); // Lee el primer numero(filas).
                int c = lector.nextInt(); // Lee el segundo numero(columnas).

                // Sale de la ejecución en caso de que sea lea 0 0
                if (c == 0 && r == 0) {
                    break;
                }

                // Salto de linea en cada caso.
                if (a > 1) {
                    System.out.print("\n");
                }

                // Muestra el numero de caso.
                System.out.println("Field #" + a + ":");
                /* Lee los "*" y "." con un arreglo bidimensional.
                 * Asigna un String a un array de caracteres mediante el método toCharArray() de la clase String.
                 * next() Devuelve el siguiente token como un String (delimitador por defecto es el espacio en blanco)
                 */
                char tablero[][] = new char[r][];
                for (int i = 0; i < r; i++) {
                    tablero[i] = lector.next().toCharArray();
                }

                // Revisa el arreglo en busca de '.'
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (tablero[i][j] == '.') {
                            // Cuenta el número de minas adyacentes.
                            int cuenta = 0;
                            for (int ii = i - 1; ii <= i + 1; ii++) {
                                for (int jj = j - 1; jj <= j + 1; jj++) {
                                    if (ii >= 0 && jj >= 0 && ii < r && jj < c && tablero[ii][jj] == '*') {
                                        ++cuenta;
                                    }
                                }
                            }

                            // Se remplaza '.' con el numero de minas adyacentes.
                            if (tablero[i][j] == '.') {
                                tablero[i][j] = (char) ('0' + cuenta);
                            }
                        }
                    }
                }

                // Finalizando se imprime el tablero.
                for (int i = 0; i < r; i++) {
                    System.out.println(new String(tablero[i]));
                }
                a++;
            }
        } catch (ArrayIndexOutOfBoundsException exc1) {
            System.err.println("Formato erróneo: Hay más renglones declarados"
                    + " que los leidos.");
        } catch (FileNotFoundException exc2) {
            System.err.println("No se encontro el archivo: " + archivo);
        }

    }

    /**
     *
     * @param args Toma como argumento el nombre del archivo con los casos a
     * resolver sobre el tablero de buscaminas.
     * @throws java.io.FileNotFoundException Lanza una excepción en caso de no
     * encontrar el archivo.
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Main minas1 = new Main();
            minas1.minas(args[0]);
        } catch (ArrayIndexOutOfBoundsException IO) {
            System.err.println("Formato de entrada incorrecto.");
        }
    }
}
