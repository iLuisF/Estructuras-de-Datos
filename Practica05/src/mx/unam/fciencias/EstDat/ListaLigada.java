package mx.unam.fciencias.EstDat;

/**
 * Se usan celdas para almacenar cada elemento de la lista. Las cuales tienen un
 * objeto cada una de ellas y un apuntador a la siguiente celda y la ultima
 * celda apunta a null. Ademas se implenta una clase privada llamada Celda que
 * representa una celda.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 * @param <E> Tipo de elemento.
 */
public class ListaLigada<E> extends ListaAbstracta<E> implements Lista<E> {

    private Celda<E> primero; //Referencia a la primer celda.
    private int numelem; // Número de elementos en la lista.

    /**
     * Inserta un elemento en la lista.
     *
     * @param idx Es el índice del lugar donde el elemento será insertado.
     * <code>idx</code> debe estar en el conjunto {0, ...,
     * <code>longitud()</code>}, de otro modo se levanta una excepción
     * <code>ExcepcionAccesoIncorrecto</code> (índice fuera de rango)
     * @param nvoelem El nuevo elemento a insertar.
     * @see #longitud
     * @throws ExcepcionAccesoIncorrecto en caso de pretender insertar más allá
     * del final de la lista o antes del principio.
     */
    @Override
    public void inserta(int idx, E nvoelem) throws ExcepcionAccesoIncorrecto {
        Celda<E> nuevacelda;
        Celda<E> actual;
        if ((idx < 0) || (idx > numelem)) {// Si el indice es invalido.
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            if (idx == 0) {
                nuevacelda = new Celda<E>(nvoelem, primero);
                primero = nuevacelda;
            } else {
                actual = primero;
                for (int i = 0; i < (idx - 1); i++) {
                    actual = actual.getSiguiente();
                }
                nuevacelda = new Celda<E>(nvoelem, actual.getSiguiente());
                actual.setSiguiente(nuevacelda);
            }
            numelem++;
        }

    }

    /**
     * Reemplaza un elemento de la lista.
     *
     * @param idx es el índice del lugar a reemplazar. Por supuesto este índice
     * debe estar en el conjunto {0, ..., longitud()-1}.
     * @param elem es el elemento que reemplazará al que se halla en la posición
     * especificada.
     * @see #longitud
     * @throws ExcepcionAccesoIncorrecto en caso de que el índice sea
     * incorrecto.
     */
    @Override
    public void reemplaza(int idx, E elem) throws ExcepcionAccesoIncorrecto {
        Celda<E> actual;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido");
        } else {
            if (idx == 0) {
                primero.setElemento(elem);
            } else {
                actual = primero;
                for (int i = 0; i < idx; i++) {
                    actual = actual.getSiguiente();
                }
                actual.setElemento(elem);
            }
        }
    }

    /**
     * Elimina el elemento almacenado en un lugar específico de la lista.
     *
     * @param idx índice de la celda a eliminar. <code>idx</code> debeestar en
     * el conjunto {0, ..., longitud()-1}, de otro modo se levanta una excepción
     * <code>ExcepcionAccesoIncorrecto</code> (índice fuera de rango)
     * @see #longitud
     * @throws ExcepcionAccesoIncorrecto en caso de pretender eliminar más allá
     * del final de la lista o antes del principio.
     */
    @Override
    public void elimina(int idx) throws ExcepcionAccesoIncorrecto {
        Celda<E> anterior;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido");
        } else {
            if (idx == 0) {
                primero = primero.getSiguiente();
            } else {
                anterior = primero;
                for (int i = 0; i < (idx - 1); i++) {
                    anterior = anterior.getSiguiente();
                }
                anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
            }
            numelem--;
        }
    }

    /**
     * Permite conocer el elemento guardado en un lugar específico de la lista.
     *
     * @param idx índice del lugar requerido. <code>idx</code> debe estar en el
     * conjunto {0, ..., longitud()-1}
     * @return el elemento guardado en la celda de índice <code>idx</code>.
     * @see #longitud
     * @throws ExcepcionAccesoIncorrecto en caso de pretender obtener el
     * elemento de más allá del final de la lista o antes del principio.
     */
    @Override
    public E elemento(int idx) throws ExcepcionAccesoIncorrecto {
        Celda<E> actual;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido");
        } else {
            if (idx == 0) {
                return primero.getElemento();
            } else {
                actual = primero;
                for (int i = 0; i < idx; i++) {
                    actual = actual.getSiguiente();
                }
                return actual.getElemento();
            }
        }
    }

    /**
     * Determina el número de elementos elmacenados en la lista.
     *
     * @return número de celdas en la lista
     */
    @Override
    public int longitud() {
        return numelem;
    }

    /**
     * Convierte la lista en lista vacía.
     */
    @Override
    public void limpia() {
        primero = null;
        numelem = 0;
    }

    /**
     * Construye un iterador para la lista. Una vez obtenido el iterador,
     * cualquier modificación a la lista que involucre eliminar o añadir
     * elementos harán inconsistente el comportamiento del iterador.
     *
     * @return una nueva instancia de <code>IteradorLista</code>, posicionado en
     * el primer elemento de la lista.
     * @see IteradorListaArreglo
     * @see IteradorListaLigada
     */
    @Override
    public IteradorLista<E> getIterador() {
        return new IteradorListaLigada();
    }

    /**
     * Representacion de una celda que almacena un elemento de la lista.
     *
     * @see Lista
     * @see ListaLigada
     * @see Celda
     * @author Flores Gonzalez Luis Brandon
     */
    private static class Celda<E> {

        private E elem; //Elemento de la celda.
        private Celda<E> sig; //Liga a la siguiente celda.

        /**
         * Constructor para colocar un elemento dentro de la celda.
         *
         * @param elem Objeto que se colocara en la celda.
         */
        public Celda(E elem) {
            this.elem = elem;
            this.sig = null;
        }

        /**
         * Constructor para colocar un elemento dentro de la celda y de la liga
         * a la siguiente celda.
         */
        public Celda(E elem, Celda<E> sig) {
            this.elem = elem;
            this.sig = sig;
        }

        /**
         * Asigna el elemento de la celda.
         *
         * @param elem Nuevo elemento de la celda.
         */
        public void setElemento(E elem) {
            this.elem = elem;
        }

        /**
         * Asigna la liga a la siguiente celda.
         *
         * @param sig Siguiente celda.
         */
        public void setSiguiente(Celda<E> sig) {
            this.sig = sig;
        }

        /**
         * Regresa el elemento dentro de la celda.
         *
         * @return El elemento dentro de la celda.
         */
        public E getElemento() {
            return this.elem;
        }

        /**
         * Regresa la referencia a la siguiente celda.
         *
         * @return La referencia a la siguiente celda.
         */
        public Celda<E> getSiguiente() {
            return this.sig;
        }
    }//Fin de Celda.java

    /**
     * Clase que implementa iteradores pero solo para listas ligadas, es decir,
     * solo recorre la lista en un sentido.
     *
     * @author Flores González Luis Brandon
     */
    private class IteradorListaLigada implements IteradorLista<E> {

        private int posicion;
        private Celda<E> actual;

        /**
         * Constructor de un iterador para una lista ligada.
         */
        public IteradorListaLigada() {
            posicion = 0;
            actual = primero;
        }

        /**
         * Reemplaza el elemento en la posición actual con el dado como
         * argumento.
         *
         * @param elem es el elemento que substituye el contenido en la posición
         * actual.
         */
        @Override
        public void reemplaza(E elem) {
            actual.setElemento(elem);
        }

        /**
         * Posiciona el iterador en el primer elemento de la estructura
         *
         * @throws ExcepcionAccesoIncorrecto si la estructura es vacía
         */
        @Override
        public void setInicio() throws ExcepcionAccesoIncorrecto {
            if (primero == null) {
                throw new ExcepcionAccesoIncorrecto("Lista vacia.");
            } else {
                posicion = 0;
                actual = primero;
            }
        }

        /**
         * Determina si hay un siguiente elemento.
         *
         * @return <code>true</code> si hay un siguiente elemento,
         * <code>false</code> en otro caso.
         */
        @Override
        public boolean haySiguiente() {
            return (posicion < (numelem - 1));
            //|a|->|b|->|c|->null // posicion=2 y numelem=3-1
        }

        /**
         * Avanza el iterador al siguiente elemento disponible en la estructura.
         *
         * @throws ExcepcionAccesoIncorrecto si ya no hay siguiente elemento.
         */
        @Override
        public void avanza() throws ExcepcionAccesoIncorrecto {
            if (actual.getSiguiente() == null) {
                throw new ExcepcionAccesoIncorrecto("No es posible avanzar.");
            } else {
                actual = actual.getSiguiente();
                posicion++;
            }
        }

        /**
         * Regresa el elemento en la posición actual del iterador.
         *
         * @return el elemento almacenado en la posición actual de la
         * estructura.
         */
        @Override
        public E elementoActual() {
            return actual.getElemento();
        }

        /**
         * Regresa la posición actual del iterador.
         *
         * @return un enetero no negativo con la posición actual del iterador en
         * la estructura.
         */
        @Override
        public int posicion() {
            return posicion;
        }

    }

}
