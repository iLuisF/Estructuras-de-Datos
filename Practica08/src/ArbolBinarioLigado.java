
/**
 * Clase que implementa árboles binarios.
 *
 * @author Flores González Luis Brandon.
 * @see ArbolBinario
 * @see NodoArbol
 * @version 1.0 <br> mayo 2015
 */
public class ArbolBinarioLigado implements ArbolBinario {

    private final boolean esVacio; //Si el árbol es vacio o no.
    private NodoArbol raiz; //Referencia al nodo raíz del árbol.   

    /*
     *Utiliza árboles binarios completos para los hijos, para facilitar el 
     *recorrido recursivo en el algoritmo de codificación.
     */
    private ArbolBinarioLigado arbolIzquierdo, arbolDerecho;

    /**
     * Constructor para un árbol binario vacío.
     */
    public ArbolBinarioLigado() {
        this.esVacio = true;
    }

    /**
     * Constructor para un árbol binario que contiene un nodo, un árbol
     * izquierdo y un arbol derecho.
     *
     * @param raiz - Elemento de la raiz de tipo NodoArbol.
     * @param izquierdo - ArborBinario izquierdo.
     * @param derecho - ArbolBinario derecho.
     */
    public ArbolBinarioLigado(NodoArbol raiz, ArbolBinarioLigado izquierdo, ArbolBinarioLigado derecho)
            throws NullPointerException {
        this.esVacio = false;
        this.raiz = raiz;
        this.arbolIzquierdo = (izquierdo == null) ? new ArbolBinarioLigado() : izquierdo;
        this.arbolDerecho = (derecho == null) ? new ArbolBinarioLigado() : derecho;
    }

    /**
     * Determina si el árbol es vacío.
     *
     * @return <code> True </code> si el árbol es vacio, <code> false </code> en
     * otro caso.
     */
    @Override
    public boolean esVacio() {
        return this.esVacio;
    }

    /**
     * Regresa el contenido del nodo raíz del árbol.
     *
     * @return Referencia al objeto en la raíz del árbol.
     */
    @Override
    public NodoArbol getRaiz() {
        if (esVacio) {
            return null;
        } else {
            return this.raiz;
        }
    }

    /**
     * Metodo que establece el elemento de la raiz de este arbol
     *
     * @param nvaraiz el elemento a ser la nueva raiz de este arbol
     */
    @Override
    public void setRaiz(NodoArbol nvaraiz) {
        if (esVacio) {
            raiz = nvaraiz;
        }
    }

    /**
     * Metodo que establece el hijo izquierdo de este arbol
     *
     * @param nvoizq la referencia al nuevo hijo izquierdo de este arbol
     */
    @Override
    public void setIzquierdo(ArbolBinario nvoizq) {
        this.arbolIzquierdo = (ArbolBinarioLigado) nvoizq;
    }

    /**
     * Metodo que establece el hijo derecho de este arbol
     *
     * @param nvoder la referencia al nuevo hijo derecho de este arbol
     */
    @Override
    public void setDerecho(ArbolBinario nvoder) {
        this.arbolDerecho = (ArbolBinarioLigado) nvoder;
    }

    /**
     * Metodo que determina si este arbol es hoja de otro arbol o no
     *
     * @return true si este arbol es un arbol hoja, false en otro caso
     */
    @Override
    public boolean esHoja() {
        return (arbolIzquierdo == null && arbolDerecho == null);
    }

    /**
     * Metodo que devuelve una referenca al árbol izquierdo de este arbol.
     *
     * @return La referencia al arbol derecho de este arbol.
     */
    @Override
    public ArbolBinarioLigado getIzquierdo() {
        if (esVacio) {
            return null;
        } else {
            return arbolIzquierdo;
        }
    }

    /**
     * Metodo que devuelve una referenca al árbol derecho de este arbol
     *
     * @return la referencia al arbol derecho de este arbol
     */
    @Override
    public ArbolBinarioLigado getDerecho() {
        if (esVacio) {
            return null;
        } else {
            return arbolDerecho;
        }
    }
}
