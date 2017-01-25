
/**
 * En esta clase se implementa el ordenamiento por cuentas (countingsort) es un
 * algoritmo de ordenamiento en el que se cuenta el número de elementos de cada
 * clase para luego ordenarlos. Sólo puede ser utilizado por tanto para ordenar
 * elementos que sean contables (como los números enteros en un determinado
 * intervalo, pero no los números reales, por ejemplo).
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Countingsort {

    private final int rangoMaximo = 500000;
    Integer arreglo[];
    int sup;

    /**
     * Constructor que inicia un arreglo de tipo T con un limite superior, es
     * decir, se ordena desde el indice 0 hasta el indice del limite superior.
     *
     * @param arreglo Arreglo de tipo T que sera ordenado.
     * @param sup Indice del limite superior del arreglo.
     */
    public Countingsort(Integer[] arreglo, int sup) {
        this.arreglo = arreglo;
        this.sup = sup;
    }

    /**
     * Ordena el arreglo (ordenamiento por cuentas) crecientemente.
     *
     * @return Regresa un arreglo de tipo T ordenado.
     */
    public Integer[] sort() {
        if (arreglo.length == 0) {
            return null;
        }
        int max = arreglo[1], min = arreglo[1];//Encuentra el máximo y minimo.
        for (int i = 1; i < sup; i++) {
            if (arreglo[i] > max) {
                max = arreglo[i];
            }
            if (arreglo[i] < min) {
                min = arreglo[i];
            }
        }

        int rango = max - min + 1;

        if (rango > rangoMaximo) {
            System.out.println("\nError : Rango demasiado grande para ordenar.");
            return null;
        }

        int[] count = new int[rango];
        /**
         * Hace un recuento/frecuencia de cada elemento.*
         */
        for (int i = 1; i < sup; i++) {
            count[arreglo[i] - min]++;
        }
        /**
         * Modifica la cuenta de modo que se obtiene posiciones en la matriz
         * definitiva.*
         */
        for (int i = 1; i < rango; i++) {
            count[i] += count[i - 1];
        }
        /**
         * Modifica el arreglo original.*
         */
        int j = 0;
        for (int i = 0; i < rango; i++) {
            while (j < count[i]) {
                arreglo[j++] = i + min;
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
     * Método principal para probar la implementación de Countingsort, este
     * método lee un archivo el cual contiene un listado de números enteros.
     * Ademas escribe en un archivo todos los números ordenanos, estos fueron
     * ordenados de 50000 en 50000. Y escribe otro archivo que contiene el
     * número de iteraciones, los números ordenados y el tiempo que tomo en
     * ordenar en cada iteracion.
     *
     * @param args <strong>args[0]: </strong> Se especifica si se leeran números
     * enteros o números flotantes, si son números enteros se ingresa "e" si son
     * números flotantes se ingresa "f" pero lanzara un error en este caso.
     * <strong>args[1]: </strong> Se especifica el archivo que se leera para
     * ordenar los números que contiene. tal archivo.
     */
    public static void main(String[] args) {
        double inicioT, finT, tiempo;
        if ("e".equals(args[0])) {
            ManejadorArchivos manejador = new ManejadorArchivos(args[1]);
            String linea = "";
            Integer[] arreglo = manejador.leerEnteros(500001);
            Countingsort cuentas;
            int count = 50000;
            int numerosOrdenados = 0;
            for (int j = 1; j <= 10; j++) {
                inicioT = System.currentTimeMillis();
                cuentas = new Countingsort(arreglo, count);
                cuentas.sort();
                finT = System.currentTimeMillis();
                count = count + 50000;
                tiempo = (finT - inicioT);
                numerosOrdenados = numerosOrdenados + 50000;
                linea = linea + (numerosOrdenados + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                if (j == 10) {
                    manejador.escribir(cuentas.mostrar(), "countingsort ordered.txt");
                }
            }
            manejador.escribir(linea, "countingsort.txt");
        } else {
            System.out.println("Debes escoger números enteros.");
        }
    }
}
