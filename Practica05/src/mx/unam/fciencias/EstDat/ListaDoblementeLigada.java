package mx.unam.fciencias.EstDat;

/**
 * Se usan celdas para almacenar cada elemento de la lista. Pero en este caso
 * cada celda tiene apuntador a la celda anterior i otro a la celda siguiente.
 * El apuntador a la celda anterior de la primera celda es nulo y el apuntador a
 * la celda siguiente de la ultima tambien es nulo.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 * @param <E> Tipo de elemento.
 */
public class ListaDoblementeLigada<E> extends ListaAbstracta<E> implements Lista<E> {

    private CeldaDoble<E> primero; //Referencia a la primera celda de la lista.
    private CeldaDoble<E> ultimo; //Referencia a la ultima celda de la lista.
    private int numelem; //Número de elementos en la lista.

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
        CeldaDoble<E> nvacelda;
        CeldaDoble<E> actual;
        int i;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            if (idx == 0) {
                nvacelda = new CeldaDoble<E>(nvoelem, primero, null);
                if (numelem != 0) {
                    primero.setAnterior(nvacelda);
                }
                primero = nvacelda;
                if (numelem == 0) { //Si es el primero.
                    ultimo = primero;
                }
            } else {
                if (idx == numelem) { // Si es el último.
                    nvacelda = new CeldaDoble<E>(nvoelem, null, ultimo);
                    ultimo.setSiguiente(nvacelda);
                    ultimo = nvacelda;
                } else {
                    if (idx <= (numelem / 2)) { // Primera mitad                        
                        actual = primero;
                        for (i = 0; i < (idx - 1); i++) {
                            actual = actual.getSiguiente();
                        }
                        nvacelda = new CeldaDoble<E>(nvoelem, actual.getSiguiente(),
                                actual);
                        actual.getSiguiente().setAnterior(nvacelda);
                        actual.setSiguiente(nvacelda);
                    } else { //Segunda mitad
                        actual = ultimo;
                        for (i = numelem - 1; i > idx; i--) {
                            actual = actual.getAnterior();
                        }
                        nvacelda = new CeldaDoble<E>(nvoelem, actual,
                                actual.getAnterior());
                        actual.getAnterior().setSiguiente(nvacelda);
                        actual.setAnterior(nvacelda);
                    }
                }
            }
            numelem++;
        }

        /**
         * CeldaDoble<E> nuevacelda; CeldaDoble<E> actual; int i; if ((idx < 0) || (idx
         * > numelem)) { throw new ExcepcionAccesoIncorrecto("Indice
         * invalido."); } else { nuevacelda = new CeldaDoble<E>(nvoelem,
         * primero, null); if (numelem != 0) { primero.setAnterior(nuevacelda);
         * } primero = nuevacelda; if (numelem == 0) { ultimo = primero; } } if
         * (idx == numelem) { // Es el ultimo. nuevacelda = new
         * CeldaDoble<E>(nvoelem, null, ultimo);
         * ultimo.setSiguiente(nuevacelda); ultimo = nuevacelda; } else { if
         * (idx <= (numelem / 2)) { // Primera mitad. actual = primero; for (i =
         * 0; i < (idx - 1); i++) { actual = actual.getSiguiente(); } nuevacelda
         * = new CeldaDoble<E>(nvoelem, actual.getSiguiente(), actual);
         * actual.getSiguiente().setAnterior(nuevacelda);
         * actual.setSiguiente(nuevacelda); } else { // Segunda mitad. actual =
         * ultimo; for (i = numelem - 1; i > idx; i--) { actual =
         * actual.getAnterior(); } } nuevacelda = new CeldaDoble<E>(nvoelem,
         * actual, actual.getAnterior());
         * actual.getAnterior().setSiguiente(nuevacelda);
         * actual.setAnterior(nuevacelda); } numelem++;
         */
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
        CeldaDoble<E> actual;
        int i;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice incorrecto.");
        } else {
            if (idx == 0) {
                primero.setElemento(elem);
            } else {
                if (idx == (numelem - 1)) {
                    ultimo.setElemento(elem);
                } else {
                    if (idx <= (numelem / 2)) {
                        actual = primero;
                        for (i = 0; i < idx; i++) {
                            actual = actual.getSiguiente();
                        }
                        actual.setElemento(elem);
                    } else {
                        actual = ultimo;
                        for (i = numelem - 1; i > idx; i--) {
                            actual = actual.getAnterior();
                        }
                        actual.setElemento(elem);
                    }
                }
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
        int i = 0;
        CeldaDoble<E> actual;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            if (idx == 0) {
                if (primero.getSiguiente() != null) {
                    primero.getSiguiente().setAnterior(null);
                    primero = primero.getSiguiente();
                } else {
                    primero = ultimo = null;
                }
            } else {
                if (idx == (numelem - 1)) {//Si es el ultimo.
                    ultimo.getAnterior().setSiguiente(null);
                    ultimo = ultimo.getAnterior();
                } else {
                    if (idx <= (numelem / 2)) {//Primera mitad.
                        actual = primero;
                        for (i = 0; i < idx; i++) {
                            actual = actual.getSiguiente();
                        }
                        //Eleminamos la celda.
                        actual.getSiguiente().setAnterior(actual.getAnterior());
                        actual.getAnterior().setSiguiente(actual.getSiguiente());
                    } else {//Segunda mitad
                        actual = ultimo;
                        for (i = (numelem - 1); i > idx; i--) {
                            actual = actual.getAnterior();
                        }
                        actual.getSiguiente().setAnterior(actual.getAnterior());
                        actual.getAnterior().setSiguiente(actual.getSiguiente());
                    }
                }
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
        int i;
        CeldaDoble<E> actual;
        if ((idx < 0) || (idx > numelem)) {
            throw new ExcepcionAccesoIncorrecto("Indice invalido.");
        } else {
            if (idx == 0) {
                return primero.getElemento();
            } else {
                if (idx == (numelem - 1)) {//Ultimo
                    return ultimo.getElemento();
                } else {
                    if (idx <= (numelem / 2)) {//Primera mitad
                        actual = primero;
                        for (i = 0; i < idx; i++) {
                            actual = actual.getSiguiente();
                        }
                        return actual.getElemento();
                    } else {//Segunda mitad
                        actual = ultimo;
                        for (i = (numelem - 1); i > idx; i--) {
                            actual = actual.getAnterior();
                        }
                        return actual.getElemento();
                    }
                }
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
        primero = ultimo = null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class CeldaDoble<E> {

        private E elemento;//Elemento de la celda.
        private CeldaDoble<E> siguiente;//Referencia a la siguiente celda.
        private CeldaDoble<E> anterior;//Referencia a la celda anterior.   

        /**
         * Constructor de una nueva CeldaDoble con elemento
         *
         * @param elemento Objeto que se instroducira en la nueva celda.
         */
        public CeldaDoble(E elemento) {
            this.elemento = elemento;
            anterior = null;
            siguiente = null;
        }

        /**
         * Constructor de una nueva CeldaDoble con un nuevo elemento y la
         * referencia a la siguiente celda.
         *
         * @param elemento Elemento que se introducira en
         * @param siguiente
         * @param anterior
         */
        public CeldaDoble(E elemento, CeldaDoble<E> siguiente, CeldaDoble<E> anterior) {
            this.elemento = elemento;
            this.siguiente = siguiente;
            this.anterior = anterior;
        }

        /**
         * Coloca el elemento de la celda.
         *
         * @param elemento Nuevo elemento de la celda.
         */
        public void setElemento(E elemento) {
            this.elemento = elemento;
        }

        /**
         * Referencia la liga a la siguiente celda.
         *
         * @param siguiente Siguiente celda.
         */
        public void setSiguiente(CeldaDoble<E> siguiente) {
            this.siguiente = siguiente;
        }

        /**
         * Referencia la liga a la celda anterior.
         *
         * @param anterior Celda anterior.
         */
        public void setAnterior(CeldaDoble<E> anterior) {
            this.anterior = anterior;
        }

        /**
         * Regresa el elemento guardado en la celda.
         *
         * @param El elemento guardado en la celda.
         */
        public E getElemento() {
            return elemento;
        }

        /**
         * Regresa la celda siguiente.
         *
         * @return Celda siguiente.
         */
        public CeldaDoble<E> getSiguiente() {
            return siguiente;
        }

        /**
         * Regresa la celda anterior.
         *
         * @return Celda anterior.
         */
        public CeldaDoble<E> getAnterior() {
            return anterior;
        }

        /**
         * Clase que implementa un interador de doble sentido para listas
         * doblemente ligadas.
         *
         * @author Flores Gonzalez Luis Brandon
         */
        private class IteradorListaDoblementeLigada implements IteradorBidireccional<E>,
                IteradorLista<E> {

            private int posicion;
            private CeldaDoble<E> actual;

            /**
             * Constructor de un iterador para lista doblemente ligada.
             */
            private IteradorListaDoblementeLigada() {
                posicion = 0;
                actual = (CeldaDoble<E>) primero;
            }

            /**
             * Posiciona el iterador en el último elemento de la estructura.
             *
             * @throws ExcepcionAccesoIncorrecto si la estructura es vacía o
             * nula.
             */
            @Override
            public void setFinal() throws ExcepcionAccesoIncorrecto {
                if (primero == null) {
                    throw new ExcepcionAccesoIncorrecto("Lista vacia.");
                } else {
                    posicion = longitud() - 1;
                    actual = (CeldaDoble<E>) primero;
                }
            }

            /**
             * Determina si hay un elemento anterior en la estructura o no.
             *
             * @return <code>true</code> si hay un elemento previo,
             * <code>false</code> en otro caso.
             */
            @Override
            public boolean hayAnterior() {
                return (posicion > 0);
            }

            /**
             * Retrocede el iterador al elemento previo disponible en la
             * estructura.
             *
             * @throws ExcepcionAccesoIncorrecto si ya no hay elemento previo.
             */
            @Override
            public void retrocede() throws ExcepcionAccesoIncorrecto {
                if (posicion == 0) {
                    throw new ExcepcionAccesoIncorrecto("No hay elemento previo.");
                } else {
                    actual = actual.getAnterior();
                    posicion--;
                }
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
                    actual = (CeldaDoble<E>) primero;
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
                return (posicion < longitud() - 1);
            }

            /**
             * Avanza el iterador al siguiente elemento disponible en la
             * estructura.
             *
             * @throws ExcepcionAccesoIncorrecto si ya no hay siguiente
             * elemento.
             */
            @Override
            public void avanza() throws ExcepcionAccesoIncorrecto {
                if (primero == null) {
                    throw new ExcepcionAccesoIncorrecto("No hay elemento siguiente.");
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
             * @return un enetero no negativo con la posición actual del
             * iterador en la estructura.
             */
            @Override
            public int posicion() {
                return posicion;
            }

            /**
             * Reemplaza el elemento en la posición actual con el dado como
             * argumento.
             *
             * @param elem es el elemento que substituye el contenido en la
             * posición actual.
             */
            @Override
            public void reemplaza(E elem) {
                actual.setElemento(elem);
            }

        }
    }
}
