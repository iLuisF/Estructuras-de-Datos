/* ----------------------------------------------------------------------
 * ArbolBinArreglo.java
 * versión 5.0
 * Copyright (C) 2015  José Galaviz Casas,
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
 * Clase que implementa árboles binarios. Utiliza
 * un arreglo para almacenar los datos.
 * @param <E> es el tipo de objetos almacenados en la estructura.
 *
 * @see ArbolBinario
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 5.0 <br>
 * marzo 2015
 *
 */
public class ArbolBinArreglo<E> implements ArbolBinario<E> {

    /**
     * Mensaje de error cuando se pretende hacer operación en
     * árbol vacío.
     */
    private static final String ERR_AV = "Árbol vacíó";

    /**
     * Mensaje de error cuando se pretende hacer operación en
     * la que se suponen vacios los subárboles.
     */
    private static final String ERR_ANV = "Subárbol no vacío";

    /**
     * Mensaje de error cuando se pretende unir un ArbolBinArreglo
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
     * Tamaño mínimo del árbol.
     */
    private static final int TAMMIN = 129;

    /**
     * Arreglo genérico en el que se almacenará el árbol.
     */
    private E[] arbol;

    /**
     * Construye un árbol a partir de un sub árbol de otro.
     * @param idx índice que el nodo raíz del árbol a
     * construir tiene en el árbol preexistente.
     * @param arorg árbol origen.
     */
    @SuppressWarnings("unchecked")
    private ArbolBinArreglo(int idx, ArbolBinArreglo<E> arorg) {
        if ((idx < arorg.arbol.length) && (arorg.arbol[idx] != null)) {
            int  tamano = pot2(arorg.altura(idx) + 1) - 1;
            E[]  arreglo = (E[]) new Object[tamano];

            llenaSubArbol(arreglo, 0, arorg.arbol, idx);
            arbol = arreglo;
        } else {
            arbol = (E[]) new Object[1];
            arbol[0] = null;
        }
    }

    /**
     * Construye una instancia de árbol binario vacío, pero
     * con un cierto monto de memoria reservada de antemano.
     * @param tam tamaño inicial del arreglo asociado al
     * árbol. Debe ser un entero positivo, de no serlo se
     * asignan tres lugares al arreglo
     */
    @SuppressWarnings("unchecked")
    private ArbolBinArreglo(int tam) {
        int i;

        if (tam > TAMMIN) {
            arbol = (E[]) new Object[tam];
        } else {
            arbol = (E[]) new Object[TAMMIN];
        }

        for (i = 0; i < arbol.length; arbol[i++] = null) {
            ;
        }
    }

    /**
     * Llena un arreglo a partir de otro siguiendo el formato
     * establecido para representar un árbol binario. Dado un
     * nodo de índice <code>k</code> en un arreglo:
     * <UL>
     * <LI> Su hijo izquierdo está en el índice
     * <code>2*k+1</code>.
     * <LI> Su hijo derecho en el índice
     * <code>2*(k+1)=2*k+2</code>.
     * <LI> Su padre en el índice <code>ceil(k/2)-1</code>
     * (esto no se usa aquí).
     * </UL>
     * @param arrdes es el arreglo destino que pretende
     * llenarse.
     * @param idxd índice a partir del que se desea llenar el
     * arreglo destino.
     * @param arrorg arreglo que contiene los datos que se
     * desean vaciar en el arreglo destino.
     * @param idxo es el índice del arreglo fuente a partir
     * del que se copian los datos.
     */
    private void llenaSubArbol(E[] arrdes, int idxd,
            E[] arrorg, int idxo) {
        if ((idxo < arrorg.length) && (arrorg[idxo] != null)) {
            arrdes[idxd] = arrorg[idxo];
            llenaSubArbol(arrdes, (idxd * 2) + 1, arrorg, (idxo * 2) + 1);
            llenaSubArbol(arrdes, (idxd * 2) + 2, arrorg, (idxo * 2) + 2);
        }
    }

    /**
     * Imprime un árbol binario en el formato aceptado por
     * dot.
     * @param idx índice del nodo a partir del cual
     * se imprime el árbol
     * @return una cadena con el código dot para trazar el árbol.
     */
    private String toDotString(int idx) {
        String nueva = new String("");

        if (((arbol.length > ((idx * 2) + 1)) && (arbol[(idx * 2) + 1] != null))
                || ((arbol.length > ((idx * 2) + 2))
                        && (arbol[(idx * 2) + 2] != null))) {
            if ((arbol.length > ((idx * 2) + 1))
                    && (arbol[(idx * 2) + 1] != null)) {
                nueva = nueva.concat(
                        "   " + arbol[idx].toString() + " -> "
                        + arbol[(idx * 2) + 1].toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if ((arbol.length > ((idx * 2) + 2))
                    && (arbol[(idx * 2) + 2] != null)) {
                nueva = nueva.concat(
                        "   " + arbol[idx].toString() + " -> "
                        + arbol[(idx * 2) + 2].toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if ((arbol.length > ((idx * 2) + 1))
                    && (arbol[(idx * 2) + 1] != null)) {
                nueva = nueva.concat(toDotString((idx * 2) + 1));
            }

            if ((arbol.length > ((idx * 2) + 2))
                    && (arbol[(idx * 2) + 2] != null)) {
                nueva = nueva.concat(toDotString((idx * 2) + 2));
            }
        } else if ((idx < arbol.length) && (arbol[idx] != null)) {
            nueva = nueva.concat("   " + arbol[idx].toString() + ";\n");
        }

        return nueva;
    }

    /**
     * Imprime un árbol binario indentado de acuerdo a su
     * estructura.
     * @param idx índice del nodo a partir del cual
     * se imprime el árbol
     * @param nivel nivel del nodo actual en la
     * jererquía del árbol.
     * @return una cadena de varias líneas que contiene la descripción del
     * árbol.
     */
    private String toString(int idx, int nivel) {
        char[] arreglotabs = null;
        String nueva = new String("");

        if ((idx < arbol.length) && (arbol[idx] != null)) {
            arreglotabs = new char[nivel];

            for (int i = 0; i < nivel; i++) {
                arreglotabs[i] = '\t';
            }

            nueva = nueva.concat(
                    new String(arreglotabs) + arbol[idx].toString() + "\n");
            nueva = nueva.concat(toString((2 * idx) + 2, nivel + 1));
            nueva = nueva.concat(toString((2 * idx) + 1, nivel + 1));
        }

        return nueva;
    }

    /**
     * Obtiene 2 elevado a una potencia entera no negativa.
     * @param p es la potencia
     * @return 2 elevado a la <code>p</code>.
     */
    private static int pot2(int p) {
        int pot = 1;

        for (int i = 0; i < p; i++) {
            pot *= 2;
        }

        return pot;
    }

    /**
     * Determina la altura de un subárbol. Aquel cuyo nodo
     * raiz se pasa como argumento.
     * @param nodoraiz índice del nodo raíz del subárbol cuya
     * altura se desea.
     * @return altura del subárbol
     */
    private int altura(int nodoraiz) {
        int alti;
        int altd;

        if ((nodoraiz >= arbol.length) || (arbol[nodoraiz] == null)) {
            return -1;
        } else {
            alti = altura((nodoraiz * 2) + 1);
            altd = altura((nodoraiz * 2) + 2);

            return (alti >= altd) ? (alti + 1) : (altd + 1);
        }
    }

    /**
     * Construye una instancia de árbol binario vacío.
     */
    @SuppressWarnings("unchecked")
    public ArbolBinArreglo() {
        arbol = (E[]) new Object[1];
        arbol[0] = null;
    }

    /**
     * Construye un árbol con un solo nodo (la raíz). Los
     * subárboles izquierdo y derecho son vacios.
     * @param elemraiz elemento en la raiz.
     */
    @SuppressWarnings("unchecked")
    public ArbolBinArreglo(E elemraiz) {
        arbol = (E[]) new Object[1];
        arbol[0] = elemraiz;
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
        if (!esVacio()) {
            return (E) arbol[0];
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
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    @Override
    public ArbolBinario<E> getSubDer()
        throws ErrorAccesoException {
        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".getSubDer: " + ERR_AV);
        } else {
            if ((arbol.length > 2) && (arbol[2] != null)) {
                return new ArbolBinArreglo<E>(2, this);
            } else {
                return new ArbolBinArreglo<E>();
            }
        }
    }

    /**
     * Regresa el subárbol izquierdo de la instancia que
     * invoca el método. Si la instancia no tiene hijo
     * regresa árbol vacío
     * @return El subárbol izquierdo del árbol que invoca el
     * método. Es regresado como <code>ArbolBinario</code>
     * @throws ErrorAccesoException en caso de
     * pretender extraer el subárbol de un árbol vacío
     */
    @Override
    public ArbolBinario<E> getSubIzq()
        throws ErrorAccesoException {
        if (esVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".getSubIzq: " + ERR_AV);
        } else {
            if ((arbol.length > 1) && (arbol[1] != null)) {
                return new ArbolBinArreglo<E>(1, this);
            } else {
                return new ArbolBinArreglo<E>();
            }
        }
    }

    /**
     * Establece el elemento en la raíz del árbol.
     * @param elemento Nuevo elemento en la
     * raíz del árbol.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setRaiz(E elemento) {
        if (arbol == null) {
            arbol = (E[]) new Object[1];
        }

        arbol[0] = elemento;
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
     * mismo tipo que el árbol.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setSubDer(ArbolBinario<E> sarder)
        throws ErrorAccesoException {
        if (esVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_AV);
        } else if ((arbol.length > 2) && (arbol[2] != null)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_ANV);
        } else if (!(sarder instanceof ArbolBinArreglo)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubDer: " + ERR_TYP);
        } else {
            int altura = ((sarder.altura() + 1) > altura())
                    ? (sarder.altura() + 1)
                    : altura();
            E[] arreglo = (E[]) new Object[pot2(altura + 1) - 1];
            int i;

            for (i = 0; i < arreglo.length; arreglo[i++] = null) {
                ;
            }

            for (i = 0; i < arbol.length; i++) {
                arreglo[i] = arbol[i];
            }

            llenaSubArbol(arreglo, 2, ((ArbolBinArreglo<E>) sarder).arbol, 0);
            arbol = arreglo;
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
     * mismo tipo que el árbol.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setSubIzq(ArbolBinario<E> sarizq)
        throws ErrorAccesoException {
        if (isVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_AV);
        } else if ((arbol.length > 1) && (arbol[1] != null)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_ANV);
        } else if (!(sarizq instanceof ArbolBinArreglo)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubIzq: " + ERR_TYP);
        } else {
            int altura = ((sarizq.altura() + 1) > altura())
                    ? (sarizq.altura() + 1)
                    : altura();
            E[] arreglo = (E[]) new Object[pot2(altura + 1) - 1];
            int      i;

            for (i = 0; i < arreglo.length; arreglo[i++] = null) {
                ;
            }

            for (i = 0; i < arbol.length; i++) {
                arreglo[i] = arbol[i];
            }

            llenaSubArbol(arreglo, 1, ((ArbolBinArreglo<E>) sarizq).arbol, 0);
            arbol = arreglo;
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
        if (esVacio()) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_AV);
        } else if (((arbol.length > 1) && (arbol[1] != null))
                || ((arbol.length > 2) && (arbol[2] != null))) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_ANV);
        } else if (!(sarder instanceof ArbolBinArreglo)
                || !(sarizq instanceof ArbolBinArreglo)) {
            throw new ErrorAccesoException(
                    this.getClass().getName() + ".setSubArbs: " + ERR_TYP);
        } else {
            setSubDer(sarder);
            setSubIzq(sarizq);
        }
    }

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean isVacio() {
        return (arbol[0] == null);
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
        return altura(0);
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
        return toString(0, 0);
    }

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    @Override
    public boolean isHoja() {
        if (arbol.length == 1) {
            return true;
        } else if (arbol.length == 2) {
            return (arbol[1] == null);
        } else { // if (arbol.length>2)

            return ((arbol[1] == null) && (arbol[2] == null));
        }
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
    @SuppressWarnings("unchecked")
    @Override
    public void limpia() {
        arbol = (E[]) new Object[1];
        arbol[0] = null;
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

            String           nombre = new String(nomarchivo + ".dot");
            FileOutputStream fos = new FileOutputStream(nombre);
            PrintStream      archsal = new PrintStream(fos, true);

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
            archsal.print(toDotString(0));
            archsal.println("}");
            archsal.flush();
            archsal.close();
        } catch (Exception exg) {
            System.err.println(ERR_IO);
            System.err.println(exg.getMessage());
            exg.printStackTrace();
        }
    }
} // Fin de ArbolBinArreglo.java