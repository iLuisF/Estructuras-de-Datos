
/**
 * En esta clase se implementa Quicksort que es un algoritmo de ordenamiento
 * óptimo pero en el peor de los casos es cuadratico. Se usa un arreglo de tipo
 * T para hacer el ordenamiento.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 * @param <T> Tipo de dato.
 */
public class Quicksort<T extends Comparable<? super T>> {

    private final T[] arreglo;
    int inf, sup;

    /**
     * Constructor para ordenar un arreglo de tipo T con un limite inferior y
     * con un limite superior.
     *
     * @param arreglo Arreglo a ordenar.
     * @param inf Limite inferior del arreglo a ordenar.
     * @param sup Limite superior del arreglo a ordenar.
     */
    public Quicksort(T[] arreglo, int inf, int sup) {
        this.arreglo = arreglo;
        this.inf = inf;
        this.sup = sup;
    }

    /**
     * Llama al método Quicksort para ordenar el arreglo. Este método sort solo
     * regresa el arreglo ordenado del método Quicksort.
     *
     * @return Regresa el arreglo ordenado, mediante la referencia al método
     * Quicksort.
     */
    public T[] sort() {
        return this.Quicksort(arreglo, inf, sup);
    }

    /**
     * Implementación de QuickSort recursiva. Ordena el arreglo entre los
     * índices que se especifican.
     *
     * @param arreglo Contiene los datos a ordenar.
     * @param inf Es el índice inferior a ser considerado para el ordenamiento
     * en la llamada actual.
     * @param sup Es el índice superior a ser considerado para el ordenamiento
     * en la llamada actual.
     * @return Regresa los datos ordenados en un arreglo.
     */
    public T[] Quicksort(T[] arreglo, int inf, int sup) {
        int i, j;
        int[] lims;
        if (inf < sup) {
            i = inf;
            j = sup;
            lims = particion(arreglo, i, j);
            Quicksort(arreglo, inf, lims[0]);
            Quicksort(arreglo, lims[1], sup);

        }
        return arreglo;
    }

    /**
     * Implementa el mecanismo de determinación de los nuevos límites para
     * quicksort. Parte el arreglo en dos.
     *
     * @param arreglo es el arreglo a partir.
     * @param inf es el límite inferior del subarreglo a considerar.
     * @param sup es el límite superior del subarreglo a considerar.
     * @param <T> es el tipo genérico de elementos en el arreglo, en esta clase
     * se suponen implementaciones de <code>Comparable</code>.
     * @return un arreglo con los nuevos límites. El límite menor en el lugar 0
     * del arreglo, el mayor en el lugar 1.
     */
    private int[] particion(T[] arreglo, int inf, int sup) {
        T pivote;
        T eleminf;
        T elemsup;
        int[] res = new int[2];
        pivote = arreglo[(inf + sup) / 2];
        do {
            eleminf = arreglo[inf];
            elemsup = arreglo[sup];
            while (eleminf.compareTo(pivote) < 0) {
                inf++;
                eleminf = arreglo[inf];
            }
            while (elemsup.compareTo(pivote) > 0) {
                sup--;
                elemsup = arreglo[sup];
            }
            if (inf <= sup) {
                if (inf != sup) {
                    swap(arreglo, inf, sup);
                }
                inf++;
                sup--;
            }
        } while (inf <= sup);
        res[0] = sup;
        res[1] = inf;
        return res;
    }

    /**
     * Intercambia dos elementos de un arreglo.
     *
     * @param arreglo contiene los elementos entre los que están los que se
     * deben intercambiar.
     * @param i es el índice de un elemento válido del arreglo.
     * @param j es el índice de otro elemento del arreglo.
     * @param <T> es el tipo genérico de elementos en el arreglo, en esta clase
     * se suponen implementaciones de <code>Comparable</code>.
     */
    private static <T> void swap(T[] arreglo, int i, int j) {
        T tmp;
        tmp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = tmp;
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
     * Método principal para probar la implementación de Quicksort, este método
     * lee un archivo el cual contiene un listado de números enteros o números
     * flotantes. Ademas escribe en un archivo todos los números ordenanos,
     * estos fueron ordenados de 50000 en 50000. Y escribe otro archivo que
     * contiene el número de iteraciones, los números ordenados y el tiempo que
     * tomo en ordenar en cada iteracion.
     *
     * @param args <strong>args[0]: </strong> Se especifica si se leeran números
     * enteros o números flotantes, si son números enteros se ingresa "e" si son
     * números flotantes se ingresa "f". <strong>args[1]: </strong> Se
     * especifica el archivo que se leera para ordenar los números que contiene
     * tal archivo.
     */
    public static void main(String[] args) {
        double inicioT, finT, tiempo;
        String nombreNumero = args[0];
        ManejadorArchivos manejador = new ManejadorArchivos(args[1]);
        String linea = "";
        switch (nombreNumero) {
            case "e":
                Integer[] arregloEnteros = manejador.leerEnteros(500001);
                Quicksort<Integer> rapidoEnt;
                int count = 50000;
                int numerosOrdenadosEnteros = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    rapidoEnt = new Quicksort<>(arregloEnteros, 1, count);
                    rapidoEnt.sort();
                    finT = System.currentTimeMillis();
                    count = count + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenadosEnteros = numerosOrdenadosEnteros + 50000;
                    linea = linea + (numerosOrdenadosEnteros + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(rapidoEnt.mostrar(), "quicksort ordered.txt");
                    }
                }
                manejador.escribir(linea, "quicksort.txt");
                break;
            case "f":
                Float[] arregloFloat = manejador.leerFlotantes(500001);
                Quicksort<Float> rapidoFloat;
                int count2 = 50000;
                int numerosOrdenados = 0;
                for (int j = 1; j <= 10; j++) {
                    inicioT = System.currentTimeMillis();
                    rapidoFloat = new Quicksort<>(arregloFloat, 1, count2);
                    rapidoFloat.sort();
                    finT = System.currentTimeMillis();
                    count2 = count2 + 50000;
                    tiempo = (finT - inicioT);
                    numerosOrdenados = numerosOrdenados + 50000;
                    linea = linea + (numerosOrdenados + "\t" + tiempo + "\n" + "Iteración: " + j + "\n");
                    if (j == 10) {
                        manejador.escribir(rapidoFloat.mostrar(), "quicksort ordered.txt");
                    }
                }
                manejador.escribir(linea, "quicksort.txt");
                break;
            default:
                System.out.println("Escoge una opción.");
                break;
        }
    }
}
