
/**
 * Clase que crea un objeto Elemento con las propiedades peso, valor y
 * valorPeso.
 *
 * @version 1.0
 * @author Flores Gonzalez Luis Brandon.
 */
public class Elemento {

    private final float peso;
    private final int valor;
    private final float valorPeso;

    /**
     * Constructor de la clase para crear un Elemento.
     *
     * @param p Es el peso del elemento.
     * @param v Es el valor del objeto.
     */
    public Elemento(float p, int v) {
        peso = p;
        valor = v;
        valorPeso = (float) valor / peso;
    }

    /**
     * Metodo que regresa el peso del elemento.
     *
     * @return Es el valor del peso.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Metodo que regresa el valor del elemento.
     *
     * @return Regresa el valor del elemento.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Metodo que regresa la relacion valor/peso.
     *
     * @return Regresa la relacion valor/peso.
     */
    public float getValorPeso() {
        return valorPeso;
    }

}
