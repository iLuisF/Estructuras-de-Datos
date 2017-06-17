/* ----------------------------------------------------------------------
 * ArbolBinLigado.java
 * versión 3.0
 * Copyright (C) 2004  José Galaviz Casas,
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

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Clase que implementa árboles binarios.
 * @param <E> es el tipo de objetos almacenados en la estructura.
 *
 * @see ArbolBinario
 * @see IteradorArbolBinario
 * @see NodoBin
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0 <br>
 * marzo 2015
 *
 */
public class ArbolBinLigado<E> implements ArbolBinario<E> {

    /**
     * Mensaje de error cuando se pretende hacer operación en
     * árbol vacío.
     */
    private static final String ERR_AV = "Árbol vacío";

    /**
     * Mensaje de error cuando se pretende hacer operación en
     * la que se suponen vacios los subárboles.
     */
    private static final String ERR_ANV = "Subárbol no vacíó";

    /**
     * Mensaje de error cuando se pretende unir un ArbolBinLigado
     * con un ArbolBinario diferente.
     */
    private static final String ERR_TYP =
       "Implementaciones diferentes de ArbolBinario";
    /**
     * Mensaje de error cuando se pretende abrir el archivo dot para
     * escribir el árbol.
     */
    private static final String ERR_IO = "Error al crear archivo de salida";
    /**
     * El nombre genérico que aparecerá en los archivos dot.
     */
    private static final String NOMBREDEFAULT = "ArbolBin";

    /**
     * Referencia al nodo raíz del árbol.
     */
    private NodoBin<E> raiz;

    /**
     * Construye un árbol binario a partir de un nodo raíz.
     * @param nvaraiz nuevo nodo raíz.
     */
    private ArbolBinLigado(NodoBin<E> nvaraiz) {
        raiz = nvaraiz;
    }

    /**
     * Eransforma la instancia que invoca en un árbol
     * vacío.
     */
    private void hazVacio() {
        raiz = null;
    }

    /**
     * Imprime un árbol binario en el formato aceptado por
     * dot.
     * @param nodoraiz nodo a partir del cual
     * se imprime el árbol
     * @return el código dot para trazar el árbol.
     */
    private String toDotString(NodoBin<E> nodoraiz) {
        String nueva = new String("");

        if ((nodoraiz.getDerecho() != null)
            ||(nodoraiz.getIzquierdo() != null)) {
            if (nodoraiz.getIzquierdo() != null) {
                nueva = nueva.concat(
                        "   " + nodoraiz.getContenido().toString() + " -> "
                        + nodoraiz.getIzquierdo().getContenido().toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if (nodoraiz.getDerecho() != null) {
                nueva = nueva.concat(
                        "   " + nodoraiz.getContenido().toString() + " -> "
                        + nodoraiz.getDerecho().getContenido().toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if (nodoraiz.getIzquierdo() != null) {
                nueva = nueva.concat(toDotString(nodoraiz.getIzquierdo()));
            }

            if (nodoraiz.getDerecho() != null) {
                nueva = nueva.concat(toDotString(nodoraiz.getDerecho()));
            }
        } else {
            nueva = nueva.concat(
                    "   " + nodoraiz.getContenido().toString() + ";\n");
        }

        return nueva;
    }

    /**
     * Imprime un árbol binario indentado de acuerdo a su
     * estructura.
     * @param nodoraiz nodo a partir del cual
     * se imprime el árbol
     * @param nivel nivel del nodo actual en la
     * jererquía del árbol.
     * @return una cadena que representa la estructura del árbol.
     */
    private String toString(NodoBin<E> nodoraiz, int nivel) {
        char[] arreglotabs = null;
        String nueva = new String("");

        if (nodoraiz != null) {
            arreglotabs = new char[nivel];

            for (int i = 0; i < nivel; i++) {
                arreglotabs[i] = '\t';
            }

            nueva = nueva.concat(
                    new String(arreglotabs) + nodoraiz.getContenido().toString()
                    + "\n");
            nueva = nueva.concat(toString(nodoraiz.getDerecho(), nivel + 1));
            nueva = nueva.concat(toString(nodoraiz.getIzquierdo(), nivel + 1));
        }

        return nueva;
    }

    /**
     * Determina la altura de un subárbol. Aquel cuyo nodo
     * raiz se pasa como argumento.
     * @param nodoraiz nodo en la raíz del
     * subárbol del que se reuiere la altura.
     * @return altura del subárbol
     */
    private int altura(NodoBin<E> nodoraiz) {
        int alti;
        int altd;

        if (nodoraiz == null) {
            return -1;
        } else {
            alti = altura(nodoraiz.getIzquierdo());
            altd = altura(nodoraiz.getDerecho());

            if (alti >= altd) {
                return alti + 1;
            } else {
                return altd + 1;
            }
        }
    }

    /**
     * Construye un árbol vacío.
     */
    public ArbolBinLigado() {
        hazVacio();
    }

    /**
     * Construye un árbol con un solo nodo (la raíz). Los
     * subárboles izquierdo y derecho son vacios.
     * @param elemraiz elemento en la raiz.
     */
    public ArbolBinLigado(E elemraiz) {
        raiz = new NodoBin<E>(elemraiz);
    }

    /**
     * Regresa el contenido del nodo raíz del árbol.
     * @return Referencia al objeto en la raíz del árbol.
     * @throws ErrorAccesoException en caso de
     * pretender extraer la raíz de un árbol vacío
     */
    @Override
    public E getRaiz()
        throws ErrorAccesoException {
        if (!isVacio()) {
            return raiz.getContenido();
        } else {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".getRaiz: " + ERR_AV);
        }
    }

    /**
     * Regresa el subárbol derecho de la instancia que
     * invoca el método. Si la instancia no tiene hijo
     * regresa árbol vacío
     * @return El subárbol derecho del árbol que invoca el
     * método. Es regresado como <code>ArbolBinario</code>
     * aunque de hecho es instancia de <code>ArbolBinLigado</code>.
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    @Override
    public ArbolBinario<E> getSubDer()
        throws ErrorAccesoException {
        ArbolBinLigado<E> arbolderecho;

        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".getSubDer: " + ERR_AV);
        } else {
            if (raiz.getDerecho() == null) {
                return new ArbolBinLigado<E>();
            } else {
                arbolderecho = new ArbolBinLigado<E>(raiz.getDerecho());

                // raiz.setDerecho(null);
                return arbolderecho;
            }
        }
    }

    /**
     * Regresa el subárbol izquierdo de la instancia que
     * invoca el método. Si la instancia no tiene hijo
     * regresa árbol vacío
     * @return El subárbol izquierdo del árbol que invoca el
     * método. Es regresado como <code>ArbolBinario</code>
     * aunque de hecho es instancia de <code>ArbolBinLigado</code>.
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    @Override
    public ArbolBinario<E> getSubIzq()
        throws ErrorAccesoException {
        ArbolBinLigado<E> arbolizquierdo;

        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".getSubIzq: " + ERR_AV);
        } else {
            if (raiz.getIzquierdo() == null) {
                return new ArbolBinLigado<E>();
            } else {
                arbolizquierdo = new ArbolBinLigado<E>(raiz.getIzquierdo());

                // raiz.setIzquierdo(null);
                return arbolizquierdo;
            }
        }
    }

    /**
     * Establece el elemento en la raíz del árbol.
     * @param elemento Nuevo elemento en la
     * raíz del árbol.
     */
    @Override
    public void setRaiz(E elemento) {
        if (isVacio()) {
            raiz = new NodoBin<E>(elemento);
        } else {
            raiz.setContenido(elemento);
        }
    }

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
     * tipo <code>ArbolBinLigado</code>.
     */
    @Override
    public void setSubDer(ArbolBinario<E> sarder)
        throws ErrorAccesoException {
        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_AV);
        } else if (raiz.getDerecho() != null) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_ANV);
        } else if (!(sarder instanceof ArbolBinLigado)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_TYP);
        } else {
            // se establece el nodo derecho de la raíz como
            // la raíz del árbol que se recibió como
            // argumento.
            raiz.setDerecho(((ArbolBinLigado<E>) sarder).raiz);

            // para impedir árboles múltiplemente referidos
            // eliminamos el árbol que recibimos como
            // argumento.
            // ((ArbolBinLigado<E>) sarder).hazVacio();
        }
    }

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
     * tipo <code>ArbolBinLigado</code>.
     */
    @Override
    public void setSubIzq(ArbolBinario<E> sarizq)
        throws ErrorAccesoException {
        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_AV);
        } else if (raiz.getIzquierdo() != null) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_ANV);
        } else if (!(sarizq instanceof ArbolBinLigado)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_TYP);
        } else {
            // se establece el nodo izquierdo de la raíz
            // como la raíz del árbol que se recibió como
            // argumento.
            raiz.setIzquierdo(((ArbolBinLigado<E>) sarizq).raiz);

            // para impedir árboles múltiplemente referidos
            // eliminamos el árbol que recibimos como
            // argumento.
            // ((ArbolBinLigado<E>) sarizq).hazVacio();
        }
    }

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
    @Override
    public void setSubArbs(ArbolBinario<E> sarder, ArbolBinario<E> sarizq)
        throws ErrorAccesoException {
        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_AV);
        } else if ((raiz.getDerecho() != null)
                   || (raiz.getIzquierdo() != null)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_ANV);
        } else if (!(sarder instanceof ArbolBinLigado)
                || !(sarizq instanceof ArbolBinLigado)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_TYP);
        } else {
            raiz.setDerecho(((ArbolBinLigado<E>) sarder).raiz);
            raiz.setIzquierdo(((ArbolBinLigado<E>) sarizq).raiz);
            // Esto era para evitar árboles multiplemente referidos, pero creo
            // que es útil
            /*
             ((ArbolBinLigado<E>) sarder).hazVacio();
             ((ArbolBinLigado<E>) sarizq).hazVacio();
             */
        }
    }

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean isVacio() {
        return (raiz == null);
    }

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean esVacio() {
        return isVacio();
    }

    /**
     * Determina la altura del árbol. Número máximo de
     * aristas que es necesario recorrer para llegar de la
     * raíz del árbol a un nodo hoja. Por convención la
     * altura del árbol vacío es -1.
     * @return altura del árbol
     */
    @Override
    public int altura() {
        return altura(raiz);
    }

    /**
     * Genera una cadena de caracteres con indentación para
     * cada nivel del árbol.
     * @return una cadena con cambios de línea y tabuladores
     * para representar correctamente la jerarquía de los
     * nodos del árbol.
     */
    @Override
    public String toString() {
        return toString(raiz, 0);
    }

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean isHoja() {
        return (raiz.sinHijos());
    }

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean esHoja() {
        return isHoja();
    }

    /**
     * Limpia el árbol. Lo convierte en árbol vacío.
     */
    @Override
    public void limpia() {
        hazVacio();
    }

    /**
     * Método para obtner un archivo ejecutable para el
     * programa <code>dot</code> de <b>Graphviz</b>. El
     * archivo resultante pueder ser procesado usando:<br>
     * <code>dot -Tjpg archivo.dot -o archivo.jpg</code><br>
     * por ejemplo, lo que da como resultado un archivo JPEG
     * con la imagen del árbol construido.
     * @param nomarchivo es el nombre del archivo a
     * generar. La extensión "<code>.dot</code>" se añade
     * automáticamente. Si este parámetro es
     * <code>null</code> o la cadena posee longitud cero
     * entonces se asigna un nombre da archivo por omisión
     * (<code>ArbolBin.dot</code>).
     */
    public void generaDotPrg(String nomarchivo) {
        try {
            if ((nomarchivo == null) || (nomarchivo.length() == 0)) {
                nomarchivo = new String(NOMBREDEFAULT);
            }

            String nombre = new String(nomarchivo + ".dot");
            FileOutputStream fos = new FileOutputStream(nombre);
            PrintStream archsal = new PrintStream(fos, true);

            archsal.println("/* ");
            archsal.println(" * " + nombre);
            archsal.println(
                    " * Árbol binario generado por "
                            + this.getClass().getName());
            archsal.println(" * Copyright (C) 2004  José Galaviz Casas,");
            archsal.println(" * Facultad de Ciencias,");
            archsal.println(
                    " * Universidad Nacional Autónoma de México, Mexico.");
            archsal.println(" */");
            archsal.println("digraph G {");
            archsal.print(toDotString(raiz));
            archsal.println("}");
            archsal.flush();
            archsal.close();
        } catch (Exception exg) {
            System.err.println(ERR_IO);
            System.err.println(exg.getMessage());
            exg.printStackTrace();
        }
    }
} // Fin de ArbolBinLigado.java
