
/**
 * Esta clase implementa el algoritmo de ordenamiento burbuja que funciona
 * revisando cada elemento del arreglo que va a ser ordenada con el siguiente,
 * intercambiándolos de posición si están en el orden equivocado. Es necesario
 * revisar varias veces toda la lista hasta que no se necesiten más
 * intercambios, lo cual significa que el arreglo esta ordenado. Es de
 * complejidad cuadratica.
 *
 * @author Flores González Luis Brandon.
 * @param <T> Tipo de dato.
 * @version 1.0
 */
public class Bubblesort<T extends Comparable<? super T>> {

    private final T[] arreglo;
    private final int inf, sup;

    /**
     * Constructor que inicia un arreglo de tipo T, con un limite inferior y un
     * limite superor, es decir, se ordena desde el indice del limite inferior
     * hasta el indice del limite superior.
     *
     * @param arreglo Arreglo de tipo T que sera ordenado.
     * @param inf Indice del limite inferior del arreglo.
     * @param sup Indice del limite superior del arreglo.
     */
    public Bubblesort(T[] arreglo, int inf, int sup) {
        this.arreglo = arreglo;
        this.inf = inf;
        this.sup = sup;
    }

    /**
     * Ordena el arreglo (ordenamiento por el método de la burbuja)
     * crecientemente. Los elementos en el arreglo a ordenar deben ser de una
     * clase que implemente la interfaz <code>Comparable</code>.
     *
     * @return Regresa un arreglo de tipo T ordenado.
     */
    public T[] sort() {
        for (int i = inf; i <= (sup); i++) {
            for (int j = i + 1; j <= sup; j++) {
                if (arreglo[i].compareTo(arreglo[j]) > 0) {
                    T tmp;
                    tmp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        return arreglo;
    }

    /**
     * Regresa los números que estan contenidos en el arreglo pero como una
     * cadena, es decir, convierte el contenido del arreglo en una sola cadena.
     *
     * @return Regresa el arreglo en una sola cadena.
     */
    public String mostrar() {
        String linea = "";
        for (int i = 1; i < arreglo.length; i++) {
            linea = linea + "[" + arreglo[i] + "]";
        }
        return linea;
    }

    /**
     * Método principal para probar la implementación de Bubblesort, este método
     * lee un archivo el cual contiene un listado de números enteros o números
     * flotantes. Ademas escribe en un archivo todos los números ordenanos,
     * estos fueron ordenados de 50000 en 50000. Y escribe otro archivo que
     * contiene el número de iteraciones, los números ordenados y el tiempo que
     * tomo en ordenar en cada iteracion.
     *
     * @param args <strong>args[0]: </strong> Se especifica si se leeran números
     * enteros o números flotantes, si son números enteros se ingresa "e" si son
     * números flotantes se ingresa "f". <strong>args[1]: </strong> Se
     * especifica el nombre del archivo que se leera para ordenar los números
     * que contiene tal archivo.
     */
    public static void main(String[] args) {
        double inicioT, finT, tiempo;
        String tipoNumero = args[0];
        ManejadorArchivos manejador = new ManejadorArchivos(args[1]);
        String linea = "";
        switch (tipoNumero) {
            case "e":
                Integer[] arregloEnteros = manejador.leerEnteros(500001);
                Bubblesort<Integer> burbujaEnteros;
                int count = 50000;
                int numerosOrdenados = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    burbujaEnteros = new Bubblesort<>(arregloEnteros, 1, count);
                    burbujaEnteros.sort();
                    finT = System.currentTimeMillis();
                    count = count + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenados = numerosOrdenados + 50000;
                    linea = linea + (numerosOrdenados + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(burbujaEnteros.mostrar(), "bubblesort ordered.txt");
                    }
                }
                manejador.escribir(linea, "bubblesort.txt");
                break;
            case "f":
                Float[] arregloFlotantes = manejador.leerFlotantes(500001);
                Bubblesort<Float> burbujaFlotantes;
                int count2 = 50000;
                int numerosOrdenadosFlot = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    burbujaFlotantes = new Bubblesort<>(arregloFlotantes, 1, count2);
                    burbujaFlotantes.sort();
                    finT = System.currentTimeMillis();
                    count2 = count2 + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenadosFlot = numerosOrdenadosFlot + 50000;
                    linea = linea + (numerosOrdenadosFlot + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(burbujaFlotantes.mostrar(), "bubblesort ordered.txt");
                    }
                }
                manejador.escribir(linea, "bubblesort.txt");
                break;
            default:
                System.out.println("Escoge una opción.");
                break;
        }
    }
}
