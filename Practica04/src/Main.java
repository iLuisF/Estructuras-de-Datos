
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase de prueba para el problema de la mochila con programacion dinamica,
 * exhaustiva y fraccionando un elemento.
 *
 * @author Flores Gonzalez Luis Brandon.
 * @version 1.0
 */
public class Main {

    /**
     * Metodo principal que se sirve para probar la resolucion del problema de
     * manera exhaustiva, dinamica y fraccionando un elemento.
     *
     * @param args No se usa.
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        MochilaDinamica a = new MochilaDinamica();
        MyTimer t1, t2;
        t1 = new MyTimer();
        t2 = new MyTimer();
        System.out.println("Escoge la implementación que quieres.(Solo teclea el numero)\n"
                + "\n1. Programación dinámica."
                + "\n2. Partiendo los objetos.");
        int opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("PROGRAMACIÓN DINAMICA.");
                System.out.println("Ingresa el numero de articulos:\t");
                int numart = leer.nextInt();
                int[] P = new int[numart];
                float[] G = new float[numart];
                for (int i = 0; i < numart; i++) {
                    System.out.println("Ingresa el peso del articulo: " + i);
                    P[i] = leer.nextInt();
                    System.out.println("Ingresa el valor del articulo: " + i);
                    G[i] = (float) (leer.nextFloat());
                }
                System.out.println("Ingresa la capacidad de la mochila: ");
                int W = leer.nextInt();
                t1.startTime();
                System.out.println("Valor máximo que se puede poner en la mochila es: "
                        + a.maxGan(P, G, W));
                t1.endTime();
                t2.startTime();
                System.out.println("Articulos en la mochila: "
                        + Arrays.toString(a.numElem(P, G, W)));

                t2.endTime();
                long tiempo1 = t1.getTimeMeassure();
                long tiempo2 = t2.getTimeMeassure();
                long tiempot = tiempo1 + tiempo2;
                System.out.println("Tiempo: " + tiempot);
                break;
            case 2:
                System.out.println("PARTIENDO LOS OBJETOS."
                        + "\nIngresa el numero de objetos: ");
                final int numobj = leer.nextInt();
                Elemento[] elementos = new Elemento[numobj];
                int[] pesos = new int[numobj];
                int[] valores = new int[numobj];
                for (int i = 0; i < numobj; i++) {
                    System.out.println("Ingresa el peso del articulo: " + i);
                    pesos[i] = leer.nextInt();
                    System.out.println("Ingresa el valor del articulo: " + i);
                    valores[i] = leer.nextInt();
                    elementos[i] = new Elemento(pesos[i], valores[i]);
                }
                System.out.println("Ingresa la capacidad de la mochila: ");
                final float capacidad = leer.nextInt();
                t1.startTime();
                MochilaFraccion mochila = new MochilaFraccion(capacidad);
                mochila.llenarMochila(elementos);
                t1.endTime();
                long tiempo = t1.getTimeMeassure();
                System.out.println("Tiempo: " + tiempo);
                break;
            default:
                System.out.println("Escoge una opción.");
        }
    }

}
