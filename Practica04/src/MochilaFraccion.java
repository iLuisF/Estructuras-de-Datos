
import java.util.Arrays;

/**
 * Clase que resuelve el problema de la mochila, pero en este caso se puede
 * fraccionar un objeto.
 *
 * @version 1.0
 * @author Flores Gonzalez Luis Brandon.
 */
public class MochilaFraccion {

    private float capacidad;

    /**
     * Constructor por omisión.
     */
    public MochilaFraccion() {
    }

    /**
     * Constructor que hace uso de la capacidad de la mochila.
     *
     * @param c Es la capacidad de la mochila.
     */
    public MochilaFraccion(float c) {
        capacidad = c;
    }

    /**
     * Metodo para calcular la ganancia maxima de los elementos posibles que se
     * pueden meter a la mochila, para lograr el mayor beneficio posible, ademas
     * se puede fraccionar un objeto.
     *
     * @param elementos Es un arreglo de objetos para insertar en la mochila.
     */
    public void llenarMochila(Elemento[] elementos) {
        Arrays.sort(elementos, new Comparador());//Ordena el arreglo de elementos en orden descreciente.
        float W = 0; //Es el peso actual de la mochila.
        float G = 0; //Es el valor de los elementos contenidos en la mochila.
        int i = 0;
        int[] MG = new int[elementos.length];
        while ((i < elementos.length) && (W + elementos[i].getPeso() <= capacidad)) {
            W += (float) elementos[i].getPeso();
            G += (float) elementos[i].getValor();
            MG[i] = i;
            i++;
        }
        if (i <= elementos.length && W < capacidad) {
            float capacidadRestante = capacidad - W;
            G += (float) elementos[i].getValorPeso() * capacidadRestante;
            W = capacidad; //Se llena la mochila, partiendo un elemento.
        }
        System.out.println("Valor máximo que se puede poner en la mochila es: " + G);
        System.out.print("Articulos en la mochila: ");
        System.out.println(Arrays.toString(MG));
    }
}
