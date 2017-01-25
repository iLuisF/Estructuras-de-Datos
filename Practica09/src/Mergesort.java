
/**
 * Esta clase implementa el algoritmo de ordenamiento por mezcla (mergesort) es
 * un algoritmo de ordenamiento externo estable basado en la técnica divide y
 * vencerás. Es de complejidad O(n log n).
 *
 * @author Flores González Luis Brandon.
 * @param <T> Tipo de dato.
 * @version 1.0
 */
public class Mergesort<T extends Comparable<? super T>> {

    private final T[] arreglo;
    private final int izq, der;

    /**
     * Constructor que inicia un arreglo de tipo T, con un limite inferior y un
     * limite superor, es decir, se ordena desde el indice del limite inferior
     * hasta el indice del limite superior.
     *
     * @param arreglo
     * @param izq Es el indice inicial del subarreglo a ordenar.
     * @param der Es el indice final del subarreglo a ordenar.
     */
    public Mergesort(T[] arreglo, int izq, int der) {
        this.arreglo = arreglo;
        this.izq = izq;
        this.der = der;
    }

    /**
     * Ordena el arreglo (ordenamiento por el método de mezcla) crecientemente.
     * Los elementos en el arreglo a ordenar deben ser de una clase que
     * implemente la interfaz <code>Comparable</code>.
     *
     * @return Regresa un arreglo de tipo T ordenado.
     */
    public T[] sort() {
        return this.Mergesort(arreglo, izq, der);
    }

    /**
     * Método que implementa el algoritmo de mergesort.
     *
     * @param arreglo Contiene los datos a ordenar.
     * @param izq Es el índice inicial del subarreglo a ordenar.
     * @param der Es el índice final del subarreglo a ordenar.
     * @return
     */
    public T[] Mergesort(T[] arreglo, int izq, int der) {
        if (izq < der) {
            int enmedio = (izq + der) / 2;
            Mergesort(arreglo, izq, enmedio);
            Mergesort(arreglo, enmedio + 1, der);
            merge(arreglo, izq, enmedio, der);
        }
        return arreglo;
    }

    /**
     * Hace la mezcla de los dos arreglos ordenados.
     *
     * @param arreglo es donde estan los elementos a ordenar.
     * @param primero es el índice inicial del subarreglo de la izquierda
     * @param enmedio es el índice inicial del subarreglo de la derecha
     * @param ultimo es el índice final del subarreglo de la derecha
     * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
     * se suponen implementaciones de <code>Comparable</code>.
     */
    @SuppressWarnings("unchecked")
    private void merge(T[] arreglo, int primero, int enmedio, int ultimo) {
        int i = primero, d = enmedio + 1;
        int r = 0;
        T[] resul = (T[]) new Comparable[ultimo - primero + 1];
        /* Se compara el i-esimo elemento de la parte izquierda y
         * el d-esimo de la derecha. T que resulte menor se pasa
         * al arreglo resultante.
         */
        while ((i <= enmedio) && (d <= ultimo)) {
            if (arreglo[i].compareTo(arreglo[d]) <= 0) {
                resul[r++] = arreglo[i++];
            } else {
                resul[r++] = arreglo[d++];
            }
        }
        /* Se acaba una o ambas mitades.
         * Si faltaron elementos por copiar, generalmente de una de
         * las mitades...
         */
        while (i <= enmedio) {
            resul[r++] = arreglo[i++];
        }
        while (d <= ultimo) {
            resul[r++] = arreglo[d++];
        }
        // se copia al arreglo arreglo
        for (i = primero, d = 0; i <= ultimo; i++, d++) {
            arreglo[i] = resul[d];
        }
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
     * Método principal para probar la implementación de Mergesort, este método
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
        String nombreNumero = args[0];
        ManejadorArchivos manejador = new ManejadorArchivos(args[1]);
        String linea = "";
        switch (nombreNumero) {
            case "e":
                Integer[] arregloEnteros = manejador.leerEnteros(500001);
                Mergesort<Integer> mezclaEnt;
                int count = 50000;
                int numerosOrdenadosEnt = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    mezclaEnt = new Mergesort<>(arregloEnteros, 1, count);
                    mezclaEnt.sort();
                    finT = System.currentTimeMillis();
                    count = count + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenadosEnt = numerosOrdenadosEnt + 50000;
                    linea = linea + (numerosOrdenadosEnt + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(mezclaEnt.mostrar(), "mergesort ordered.txt");
                    }
                }
                manejador.escribir(linea, "mergesort.txt");
                break;
            case "f":
                Float[] arregloFlotantes = manejador.leerFlotantes(500001);
                Mergesort<Float> mezclaFloat;
                int count2 = 50000;
                int numerosOrdenadosFlotantes = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    mezclaFloat = new Mergesort<>(arregloFlotantes, 1, count2);
                    mezclaFloat.sort();
                    finT = System.currentTimeMillis();
                    count2 = count2 + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenadosFlotantes = numerosOrdenadosFlotantes + 50000;
                    linea = linea + (numerosOrdenadosFlotantes + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(mezclaFloat.mostrar(), "mergesort ordered.txt");
                    }
                }
                manejador.escribir(linea, "mergesort.txt");
                break;
            default:
                System.out.println("Escoge una opción.");
                break;
        }
    }

}
