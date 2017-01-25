package mx.unam.fciencias.EstDat;

/**
 * Lista basada en arreglos, los elementos se almacenan en un arreglo nativo de
 * Java. Cuando los elementos ya no quepan en el arreglo usado, este aumenta de
 * tamaño automaticamente de manera que permite seguir almacenando elementos de
 * la lista.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 * @param <E> Tipo de elemento.
 */
public class ListaArreglo<E> extends ListaAbstracta<E> implements Lista<E> {

    private static final int espaciomision = 5; //Tamaño de paso por omision.
    private E[] elementos; //Lista
    private int numelem; //Número de elementos en la lista.
    private int tamarr; //Tamaño del arreglo.
    private int espacio; //Tamaño del espacio cada vez que sea necesario.

    /**
     * Constructor de una lista vacia. Los tamaños son dados por espaciomision.
     */
    public ListaArreglo() {
        elementos = (E[]) new Object[espaciomision];
        numelem = 0;
        tamarr = espaciomision;
        espacio = espaciomision;
        for (int i = 0; i < tamarr; i++) {
            elementos[i] = null;
        }
    }

    /**
     * Constructor de una lista vacia pero con un tamaño inicial y tamaño del
     * espacio agregado definido por el usuario.
     *
     * @param taminicio Tamaño inicial de la lista.
     * @param tamespacio Tamaño del espacio agregado.
     */
    public ListaArreglo(int taminicio, int tamespacio) {
        elementos = (E[]) new Object[taminicio];
        numelem = 0;
        tamarr = taminicio;
        espacio = tamespacio;
        for (int i = 0; i < tamarr; i++) {
            elementos[i] = null;
        }
    }

    /**
     * Constructor de una lista vacia pero con un tamaño inicial
     *
     * @param taminicio Tamaño inicial de la lista.
     */
    public ListaArreglo(int taminicio) {
        elementos = (E[]) new Object[taminicio];
        numelem = 0;
        tamarr = taminicio;
        espacio = espaciomision;
        for (int i = 0; i < tamarr; i++) {
            elementos[i] = null;
        }
    }

    /**
     * Agranda el arreglo, es decir, agrega mas tamaño al arreglo para que así
     * pueda contener mas elementos.
     */
    private void agrandaArreglo() {
        int i;
        E[] nuevo = (E[]) new Object[numelem + espacio];
        for (i = 0; i < numelem; i++) {
            nuevo[i] = elementos[i];
        }
        for (i = numelem; i < nuevo.length; i++) {
            nuevo[i] = null;
        }
        tamarr = nuevo.length;
        elementos = nuevo;
    }

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
        int i;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            if (numelem == tamarr) {
                agrandaArreglo();
            }
            //Recorre el arreglo para poder insertar el nuevo elemento.
            for (i = (numelem - 1); i >= idx; i--) {
                elementos[i + 1] = elementos[i];
            }
            elementos[idx] = nvoelem;
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
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            elementos[idx] = elem;
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
        int i;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            for (i = idx; i < (idx - 1); i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[numelem - 1] = null;
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
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            return elementos[idx];
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
        elementos = (E[]) new Object[tamarr];
        numelem = 0;
        for (int i = 0; i < tamarr; i++) {
            elementos[i] = null;
        }
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
        return new IteradorArreglo();
    }

    /**
     * Regresa el primer elemento de la lista.
     *
     * @return Regresa el primer elemento.
     * @throws ExcepcionAccesoIncorrecto En caso de que la pila este vacia.
     */
    @Override
    public E getPrimero() throws ExcepcionAccesoIncorrecto {
        if (!esVacia()) {
            return elemento(0);
        } else {
            throw new ExcepcionAccesoIncorrecto("Lista vacia.");
        }
    }

    /**
     * Inserta un elemento en la primera pocision.
     *
     * @param nvoprimero Elemento a insertar.
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void insertaPrimero(E nvoprimero) {
        try {
            inserta(0, nvoprimero);
        } catch (ExcepcionAccesoIncorrecto ex) {
            ;
        }
    }

    /**
     * Establece el elemento al final de la lista Este método es equivalente a
     * <code>instancialista.inserta(instancialista.longitud(),elem)</code>.
     *
     * @param nvoultimo es el objeto cuya referencia será incluida en la lista
     * en el lugar de índice <code>longitud()</code>.
     */
    public void insertaUltimo(E nvoultimo) {
        try {
            inserta(longitud(), nvoultimo);
        } catch (ExcepcionAccesoIncorrecto msg) {// nunca ocurre realmente
            ;
        }
    }

    private class IteradorArreglo implements IteradorLista<E> {

        private int posicion;

        /**
         * Reemplaza el elemento en la posición actual con el dado como
         * argumento.
         *
         * @param elem es el elemento que substituye el contenido en la posición
         * actual.
         */
        @Override
        public void reemplaza(E elem) {
            elementos[posicion] = elem;
        }

        /**
         * Posiciona el iterador en el primer elemento de la estructura
         *
         * @throws ExcepcionAccesoIncorrecto si la estructura es vacía
         */
        @Override
        public void setInicio() throws ExcepcionAccesoIncorrecto {
            if ((elementos.length == 0) || (elementos == null)) {
                throw new ExcepcionAccesoIncorrecto("Lista vacia.");
            } else {
                posicion = 0;
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
        }

        /**
         * Avanza el iterador al siguiente elemento disponible en la estructura.
         *
         * @throws ExcepcionAccesoIncorrecto si ya no hay siguiente elemento.
         */
        @Override
        public void avanza() throws ExcepcionAccesoIncorrecto {
            if (!haySiguiente()) {
                throw new ExcepcionAccesoIncorrecto("No hay un siguiente elemento.");
            } else {
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
            return elementos[posicion];
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
