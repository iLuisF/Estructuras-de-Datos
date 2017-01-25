
/**
 * Implementación de un nodo para el árbol binario.
 *
 * @author Flores González Luis Brandon.
 * @version 2.0
 */
public class NodoArbol {

    private final String elemento;

    /**
     * Constructor de un nodo para la implentación del árbol binario.
     *
     * @param elemento Elemento que esta contenido en el nodo.
     */
    public NodoArbol(String elemento) {
        this.elemento = elemento;
    }

    /**
     * Regresa el elemento actual.
     *
     * @return Elemento actual.
     */
    public String getElemento() {
        return this.elemento;
    }
}
