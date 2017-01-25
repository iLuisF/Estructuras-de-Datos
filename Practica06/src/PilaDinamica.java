
/**
 * Clase que implementa el tipo de dato pila de manera dinamica.
 * @author Flores González Luis Brandon.
 * @param <E> Tipo de elemento.
 * @version 1.0
 */
public class PilaDinamica<E> {

    private NodoPila<E> tope;
    private int tamanio;
    
    /**
     * Constructor que inicializa una pila vacía.
     */
    public PilaDinamica(){
        tope = null;
        tamanio = 0;
    }
    
    /**
     * Método para saber cuando una pila esta vacía.
     * @return True si la lista vacía y false en otro caso.
     */
    public boolean esVacia(){
        return tope == null;
    }
    
    /**
     * Metodo para insertar(push) un elemento en el tope de la pila.
     * @param elem Elemento que se va insertar en el tope de la pila.
     */
    public void empujar(E elem){
        NodoPila nuevo = new NodoPila<>(elem);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }
    
    /**
     * Metodo para sacar(pop) un elemento de la pila.
     * @return Elemento en el tope de la pila.
     */
    public E sacar(){
        E auxiliar = tope.elem;
        tope = tope.siguiente;
        tamanio--;
        return auxiliar;
    }
    
    /**
     * Metodo para saber el elemento en la cima de la pila(peek).
     * @return Elemento en el tope de la pila.
     */
    public E tope(){
        return tope.elem;
    }
    
    /**
     * Método para saber el tamaño de la pila.
     * @return El tamaño de la pila.
     */
    public int tamanioPila(){
        return tamanio;               
    }
    
    /**
     * Método para limpiar la pila.
     */
    public void limpiarPila(){
        while(!esVacia()){
            sacar();
        }
    }
    
    /**
     * Clase privada para implementar nodos de una pila.
     * @author Flores González Luis Brandon.
     * @param <E> Tipo de elemento.
     */
    private static class NodoPila<E>{
        
        private E elem;
        private NodoPila<E> siguiente;
        
        /**
         * Constructor de una nueva celda apartir de une elemento y apuntando
         * al siguiente.
         * @param elem Elemento a insertar en el nodo.
         */
        public NodoPila(E elem) {
            this.elem = elem;
            this.siguiente = null;
        }

    }
}
