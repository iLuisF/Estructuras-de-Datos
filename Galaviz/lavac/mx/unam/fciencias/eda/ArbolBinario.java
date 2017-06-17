/* ----------------------------------------------------------------------
 * ArbolBinario.java
 * versión 3.0
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
 * Interfaz para árboles binarios.
 *
 * @param <E> es el tipo de objetos almacenados en la estructura.
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0 <br>
 * marzo de 2015
 *
 */
public interface ArbolBinario<E> {

    /**
     * Regresa el contenido del nodo raíz del árbol.
     * @return Referencia al objeto en la raíz del árbol.
     * @throws ErrorAccesoException en caso de
     * pretender extraer la raíz de un árbol vacío
     */
    E getRaiz()
        throws ErrorAccesoException;

    /**
     * Regresa el subárbol derecho de la instancia que
     * invoca el método. Si la instancia no tiene hijo
     * regresa árbol vacío
     * @return El subárbol derecho del árbol que invoca el
     * método. Es regresado como <code>ArbolBinario</code>
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    ArbolBinario<E> getSubDer()
        throws ErrorAccesoException;

    /**
     * Regresa el subárbol izquierdo de la instancia que
     * invoca el método. Si la instancia no tiene hijo
     * regresa árbol vacío
     * @return El subárbol izquierdo del árbol que invoca el
     * método. Es regresado como <code>ArbolBinario</code>
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    ArbolBinario<E> getSubIzq()
        throws ErrorAccesoException;

    /**
     * Establece el elemento en la raíz del árbol.
     * @param elemento Nuevo elemento en la
     * raíz del árbol.
     */
    void setRaiz(E elemento);

    /**
     * Establece el subárbol derecho de un árbol.
     * Se debe tener cuidado ya que en la instancia que llama al método se
     * guardaran referencias a los árboles que son pasados como parámetros y por
     * tanto habrá efectos colaterales si posteriormente se modifican por
     * cuenta propia
     * @param sarder subárbol derecho
     * @throws ErrorAccesoException en caso de
     * pretender establecer el subárbol de un árbol vacío,
     * al menos debe ser un nodo raíz. O bien cuando se
     * pretende establecer un subárbol derecho que no sea del
     * mismo tipo que el árbol.
     */
    void setSubDer(ArbolBinario<E> sarder)
        throws ErrorAccesoException;

    /**
     * Establece el subárbol izquierdo de un árbol.
     * Se debe tener cuidado ya que en la instancia que llama al método se
     * guardaran referencias a los árboles que son pasados como parámetros y por
     * tanto habrá efectos colaterales si posteriormente se modifican por
     * cuenta propia
     * @param sarizq subárbol izquierdo
     * @throws ErrorAccesoException en caso de
     * pretender establecer el subárbol de un árbol vacío,
     * al menos debe ser un nodo raíz. O bien cuando se
     * pretende establecer un subárbol izquierdo que no sea del
     * mismo tipo que el árbol.
     */
    void setSubIzq(ArbolBinario<E> sarizq)
        throws ErrorAccesoException;

    /**
     * Establece ambos subárboles de un árbol binario.
     * Se debe tener cuidado ya que en la instancia que llama al método se
     * guardaran referencias a los árboles que son pasados como parámetros y por
     * tanto habrá efectos colaterales si posteriormente se modifican por
     * cuenta propia
     * @param sarder subárbol derecho.
     * @param sarizq subárbol izquierdo.
     * @throws ErrorAccesoException en caso de
     * pretender establecer subárboles de un árbol vacío o
     * cuando el tipo específico de estos no sea el mismo que
     * el del árbol.
     */
    void setSubArbs(ArbolBinario<E> sarder, ArbolBinario<E> sarizq)
        throws ErrorAccesoException;

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    boolean isVacio();

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    boolean esVacio();

    /**
     * Determina la altura del árbol. Número máximo de
     * aristas que es necesario recorrer para llegar de la
     * raíz del árbol a un nodo hoja. Por convención la
     * altura del árbol vacío es -1.
     * @return altura del árbol
     */
    int altura();

    /**
     * Genera una cadena de caracteres con indentación para
     * cada nivel del árbol.
     * @return una cadena con cambios de línea y tabuladores
     * para representar correctamente la jerarquía de los
     * nodos del árbol.
     */
    String toString();

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    boolean isHoja();

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    boolean esHoja();

    /**
     * Limpia el árbol *lo; convierte en árbol vacío).
     */
    void limpia();
} // Fin de ArbolBinario.java
