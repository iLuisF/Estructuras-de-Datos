
/**
 * Interfaz que determina las operaciones de un Árbol Binario.
 *
 * @author Armando Ballinas
 */
public interface ArbolBinario {

    /**
     * Metodo que obtiene el elemento almacenado en la raiz de este arbol
     *
     * @return el elemento almacenado en la raiz de este arbol
     */
    public NodoArbol getRaiz();

    /**
     * Metodo que devuelve una referencia al arbol izquierdo de este arbol
     *
     * @return la referencia al arbol izquierdo de este arbol
     */
    public ArbolBinario getIzquierdo();

    /**
     * Metodo que devuelve una referenca al albol derecho de este arbol
     *
     * @return la referenci al arbol derecho de este arbol
     */
    public ArbolBinario getDerecho();

    /**
     * Metodo que establece el elemento de la raiz de este arbol
     *
     * @param nvaraiz el elemento a ser la nueva raiz de este arbol
     */
    public void setRaiz(NodoArbol nvaraiz);

    /**
     * Metodo que establece el hijo izquierdo de este arbol
     *
     * @param nvoizq la referencia al nuevo hijo izquierdo de este arbol
     */
    public void setIzquierdo(ArbolBinario nvoizq);

    /**
     * Metodo que establece el hijo derecho de este arbol
     *
     * @param nvoder la referencia al nuevo hijo derecho de este arbol
     */
    public void setDerecho(ArbolBinario nvoder);

    /**
     * Metodo que determina si este arbol es hoja de otro arbol o no
     *
     * @return true si este arbol es un arbol hoja, false en otro caso
     */
    public boolean esHoja();
    
     /**
     * Determina si el árbol es vacío.
     *
     * @return <code> True </code> si el árbol es vacio, <code> false </code> en
     * otro caso.
     */    
    public boolean esVacio();

}
//fin de ArbolBinario.java
