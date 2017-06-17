/* ----------------------------------------------------------------------
 * IteradorArbolBinario.java
 * version 3.0
 * Copyright (C) 2009  José Galaviz Casas,
 * Facultad de Ciencias,
 * Universidad Nacional Autónoma de México, Mexico.
 *
 * Este programa es software libre; se puede redistribuir
 * y/o modificar en los términos establecidos por la
 * Licencia Pública General de GNU tal como fue publicada
 * por la Free Software Foundation en la versión 2 o
 * superior.
 *
 * Este programa es distribuido con la esperanza de que
 * resulte de utilidad, pero SIN GARANTÍA ALGUNA; de hecho
 * sin la garantía implícita de COMERCIALIZACIÓN o
 * ADECUACIÓN PARA PROPÓSITOS PARTICULARES. Véase la
 * Licencia Pública General de GNU para mayores detalles.
 *
 * Con este programa se debe haber recibido una copia de la
 * Licencia Pública General de GNU, de no ser así, visite el
 * siguiente URL:
 * http://www.gnu.org/licenses/gpl.html
 * o escriba a la Free Software Foundation Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * ----------------------------------------------------------------------
 */
package mx.unam.fciencias.eda;

/**
 * Implementa los algoritmos de recorrido de árboles
 * binarios. Esta clase es genérica para todos los árboles
 * binarios. Con esta clase no es posible alterar el
 * contenido del árbol. Para árboles binarios con
 * representación ligada existe la clase
 * <code>IteradorArbolBinLigado</code> que permite alterar
 * el contenido del árbol y es más eficiente.
 * @param <E> es el tipo de objetos almacenados en la estructura.
 *
 * @since 2.0
 * @see Iterador
 * @see IteradorBidireccional
 * @see ArbolBinario
 * @see ArbolBinLigado
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0 <br>
 * marzo 2015
 */
public class IteradorArbolBinario<E> implements IteradorBidireccional<E> {
   /**
    * Mensaje de error: se intenta realizar un recorrido que resultó vacío.
    */
    private static final String ERR_VACIO = "Recorrido vacío";
   /**
    * Mensaje de error: se intenta avanzar un paso más cuando el recorrido
    * terminó.
    */
    private static final String ERR_OB = "Intento de sobrepasar límites";
   /**
    * Mensaje de error: el árbol o el iterador son incorrectos.
    */
    private static final String ERR_NR =
       "Árbol vacío o especificador de recorrido incorrecto";

    /**
     * Constante para especificar recorrido en orden:
     * <OL>
     * <LI> subárbol izquierdo.
     * <LI> raíz.
     * <LI> subárbol derecho.
     * </OL>
     */
    public static final int ENORDEN = 1;

    /**
     * Constante para especificar recorrido en preorden:
     * <OL>
     * <LI> raíz.
     * <LI> subárbol izquierdo.
     * <LI> subárbol derecho.
     * </OL>
     */
    public static final int PREORDEN = 2;

    /**
     * Constante para especificar recorrido en posorden:
     * <OL>
     * <LI> subárbol izquierdo.
     * <LI> subárbol derecho.
     * <LI> raíz.
     * </OL>
     */
    public static final int POSORDEN = 3;

    /**
     * Número de recorridos. Debe ser igual al máximo número asignado
     * arriba a los tipos de recorrido.
     */
    private static final int NUMRECORRIDOS = 3;

    /**
     * Los vértices del recorrido.
     */
    private E[]      nodos;
    /**
     * El número de paso en el que vamos.
     */
    private int      actual;

    /**
     * Construye un iterador para un árbol binario. Este método
     * es invocado por el método <code>getIterador()</code>
     * de la clase <code>ArbolBinLigado</code>. Una vez obtenido
     * un iterador de una instancia particular de árbol, este
     * deja de ser consistente si la instancia de árbol que
     * lo creó es modificada mediente sus métodos propios.
     * @param arbol es el árbol a recorrer. Nótese que el
     * tipo de este argumento es <code>ArbolBinario</code>,
     * no se supone nada acerca del mecanismo de
     * representación del árbol, por lo que este iterador
     * funciona para cualquier clase que implemente la
     * interfaz <code>ArbolBinario</code>.
     * @param recorrido es alguna de las constantes que
     * aparecen arriba para especificar el orden del
     * recorrido.
     * <UL>
     * <LI> <code>IteradorArbolBinario.ENORDEN</code>
     * recorrido en orden (izq, raiz, der). </LI>
     * <LI> <code>IteradorArbolBinario.PREORDEN</code>
     * recorrido en orden (raiz, izq, der). </LI>
     * <LI> <code>IteradorArbolBinario.POSORDEN</code>
     * recorrido en orden (izq, der, raiz). </LI>
     * </UL>
     * @throws ErrorAccesoException en el acceso a la lista del recorrido.
     */
    @SuppressWarnings("unchecked")
    public IteradorArbolBinario(ArbolBinario<E> arbol, int recorrido)
        throws ErrorAccesoException {
        ListaLigada<E> lista = new ListaLigada<E>();
        int i;
        int tam;

        nodos = null;
        actual = 0;

        if (!arbol.esVacio() && (recorrido > 0)
            && (recorrido <= NUMRECORRIDOS)) {
            switch (recorrido) {
            case ENORDEN:
                recorreEnOrden(arbol, lista);

                break;

            case PREORDEN:
                recorrePreOrden(arbol, lista);

                break;

            case POSORDEN:
                recorrePosOrden(arbol, lista);

                break;

            default : // El rango ya fue verificado previemente.
               ;
            }

            tam = lista.longitud();
            nodos = (E[]) new Object[tam];

            for (i = 0; i < tam; i++) {
                nodos[i] = lista.getElemento(i);
            }
        } else {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".IteradorArbolBinario: "
                    + ERR_NR);
        }
    }

    /**
     * Recorre el árbol en orden:
     * primero el sub izquierdo <br>
     * luego la raiz<br>
     * luego el sub derecho.
     * @param arbol es el árbol a recorrer.
     * @param lista lista ligada en la que se almacenan las
     * referencias a los nodos del recorrido.
     */
    private void recorreEnOrden(ArbolBinario<E> arbol, ListaLigada<E> lista) {
        try {
            if (!arbol.isVacio()) {
                recorreEnOrden(arbol.getSubIzq(), lista);
                lista.insertaUltimo(arbol.getRaiz());
                recorreEnOrden(arbol.getSubDer(), lista);
            }
        } catch (ErrorAccesoException eai) { // no ocurre
           ;
        }
    }

    /**
     * Recorre el árbol en pre orden:
     * primero la raiz<br>
     * luego el sub izquierdo <br>
     * luego el sub derecho.
     * @param arbol es el árbol a recorrer.
     * @param lista lista ligada en la que se almacenan las
     * referencias a los nodos del recorrido.
     */
    private void recorrePreOrden(ArbolBinario<E> arbol, ListaLigada<E> lista) {
        try {
            if (!arbol.isVacio()) {
                lista.insertaUltimo(arbol.getRaiz());
                recorrePreOrden(arbol.getSubIzq(), lista);
                recorrePreOrden(arbol.getSubDer(), lista);
            }
        } catch (ErrorAccesoException eai) { // no ocurre
           ;
        }
    }

    /**
     * Recorre el árbol en pos orden:
     * primero el sub izquierdo <br>
     * luego el sub derecho <br>
     * luego la raiz.
     * @param arbol es el árbol a recorrer.
     * @param lista lista ligada en la que se almacenan las
     * referencias a los nodos del recorrido.
     */
    private void recorrePosOrden(ArbolBinario<E> arbol, ListaLigada<E> lista) {
        try {
            if (!arbol.isVacio()) {
                recorrePosOrden(arbol.getSubIzq(), lista);
                recorrePosOrden(arbol.getSubDer(), lista);
                lista.insertaUltimo(arbol.getRaiz());
            }
        } catch (ErrorAccesoException eai) { // no ocurre
           ;
        }
    }

    /**
     * Posiciona el iterador en el primer elemento del
     * recorrido.
     * @throws ErrorAccesoException el recorrido no existe
     */
    public void setInicio()
        throws ErrorAccesoException {
        if (nodos != null) {
            actual = 0;
        } else {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setInicio: " + ERR_VACIO);
        }
    }

    /**
     * Posiciona el iterador en el último elemento de la
     * del recorrido.
     * @throws ErrorAccesoException si el recorrido no existe
     */
    public void setFinal()
        throws ErrorAccesoException {
        if (nodos != null) {
            actual = nodos.length - 1;
        } else {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setFinal: " + ERR_VACIO);
        }
    }

    /**
     * Determina si hay un siguiente elemento en el recorrido.
     * @return <code>true</code> si hay un siguiente
     * elemento, <code>false</code>  en otro caso.
     */
    public boolean hasSiguiente() {
        return (actual < (nodos.length - 1));
    }

    /**
     * Determina si hay un siguiente elemento en el recorrido.
     * @return <code>true</code> si hay un siguiente
     * elemento, <code>false</code>  en otro caso.
     */
    public boolean haySiguiente() {
        return hasSiguiente();
    }

    /**
     * Determina si hay un elemento anterior en el recorrido.
     * @return <code>true</code> si hay un elemento previo,
     * <code>false</code>  en otro caso.
     */
    public boolean hasAnterior() {
        return (actual > 0);
    }

    /**
     * Determina si hay un elemento anterior en el recorrido.
     * @return <code>true</code> si hay un elemento previo,
     * <code>false</code>  en otro caso.
     */
    public boolean hayAnterior() {
        return hasAnterior();
    }

    /**
     * Avanza el iterador al siguiente elemento disponible en
     * el recorrido.
     * @throws ErrorAccesoException si ya no hay siguiente elemento.
     */
    public void avanza()
        throws ErrorAccesoException {
        if (actual >= nodos.length) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".avanza: " + ERR_OB);
        } else {
            actual++;
        }
    }

    /**
     * Retrocede el iterador al elemento previo disponible en
     * el recorrido.
     * @throws ErrorAccesoException si ya no hay elemento previo.
     */
    public void retrocede()
        throws ErrorAccesoException {
        if (actual == 0) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".retrocede: " + ERR_OB);
        } else {
            actual--;
        }
    }

    /**
     * Regresa el elemento en la posición actual del
     * iterador.
     * @return el elemento almacenado en la posición actual
     * de la lista.
     */
    public E elementoActual() {
        return nodos[actual];
    }

    /**
     * Regresa la posición actual del iterador (número de
     * elementos recorridos hasta ahora).
     * @return un entero no negativo con la posición actual
     * del iterador en el recorrido.
     */
    public int posicion() {
        return actual;
    }
} // Fin de IteradorArbolBinario.java
