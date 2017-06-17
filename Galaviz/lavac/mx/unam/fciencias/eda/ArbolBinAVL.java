/* ----------------------------------------------------------------------
 * ArbolBinAVL.java
 * versión 4.0
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

/**
 * Implementación de árboles binarios de búsqueda tipo AVL
 * (Adelson-Velsky-Landis). Es una subclase de los árboles
 * binarios de búsqueda.
 * @param <E> es el tipo de elementos almacenados en el árbol. Debe
 * implementar la interfaz <code>Comparable</code>.
 * @author José Galaviz (galaviz@fciencias.unam.mx)
 * @version 4.0 <br>
 * abril 2015
 */
public class ArbolBinAVL<E extends Object & Comparable<? super E>>
   extends ArbolBinBusqueda<E> {

   /**
    * Clase para implementar los nodos de los árboles AVL. La diferencia
    * con los nodos de un árbol binario convencional consiste en poseer un
    * campo adicional para guardar la altura del subárbol cuya raíz es el
    * nodo.
    * @param <E> es el tipo de elementos almacenados como dato en el nodo.
    * @since 4.0
    * @version 1.0 <br>
    * abril 2015
    */
   private class NodoAVL<E> extends NodoBin<E> {

      /**
       * Para almacenar la altura del nodo.
       */
      int  altnodo;

      /**
       * Construye un nodo vacío.
       */
      public NodoAVL() {
         super();
         altnodo = 0;
      }

      /**
       * Construye un nodo especificando su contenido.
       * @param elemento es el dato contenido del nodo.
       */
      public NodoAVL(E elemento) {
         super(elemento);
         altnodo = 0;
      }

      /**
       * Construye un nodo dados todos sus elementos.
       * @param elemento es el dato contenido en el nodo.
       * @param altura es la altura del nodo que se construye.
       * @param derecho es la referencia al  nodo que encabeza el subárbol
       * derecho del nodo que se construye.
       * @param izquierdo es la referencia al  nodo que encabeza el subárbol
       * izquierdo del nodo que se construye.
       */
      public NodoAVL(E elemento, int altura,
                     NodoAVL<E> derecho, NodoAVL<E> izquierdo) {
         super(elemento, derecho, izquierdo);
         altnodo = altura;
      }

      /**
       * Establece la altura del nodo.
       * @param altura es un entero mayor o igual a 0 que especifica la
       * altura del nodo dentro del árbol del que forma parte.
       */
      public void setAltura(int altura) {
         altnodo = altura;
      }

      /**
       * Regresa la altura del nodo.
       * @return un entero no negativo con la altura del nodo.
       */
      public int getAltura() {
         return altnodo;
      }

      /**
       * Permite obtener una referencia al nodo derecho de la
       * instancia que invoca al método.
       * @return Una referencia al nodo derecho del que
       * llama.
       */
      @SuppressWarnings("unchecked")
         public NodoAVL<E> getDerecho() {
         return (NodoAVL<E>) super.getDerecho();
      }

      /**
       * Permite obtener una referencia al nodo izquierdo de
       * la instancia que invoca al método.
       * @return Una referencia al nodo izquierdo del que
       * llama.
       */
      @SuppressWarnings("unchecked")
         public NodoAVL<E> getIzquierdo() {
         return (NodoAVL<E>) super.getIzquierdo();
      }
   } // Fin de NodoAVL

   /**
    * Constructora de árbol AVL. Construye un árbol vacío.
    */
   public ArbolBinAVL() {
      raiz = null;
   }

   /**
    * Rota a la derecha el subárbol cuyo nodo ráiz es
    * pasado como parámetro.
    * @param ndorz nodo raíz del subárbol a
    * rotar.
    * @return En nodo que debe reemplazar a
    * <code>ndorz</code> como raíz del subárbol que fue
    * rotado.
    */
   private NodoAVL<E> rotaDer(NodoAVL<E> ndorz) {
      NodoAVL<E> aux;

      // aux se refiere al hijo izquierdo del ndorz
      aux = ndorz.getIzquierdo();
      // a la izquierda de ndorz se pone el hijo derecho
      // de aux
      ndorz.setIzquierdo(aux.getDerecho());

      // ndorz es ahora hijo derecho de aux
      aux.setDerecho(ndorz);

      // aux debe ser el nuevo valor de ndorz
      return aux;
   }

   /**
    * Rota a la izquierda el subárbol cuyo nodo ráiz es
    * pasado como parámetro.
    * @param ndorz nodo raíz del subárbol a
    * rotar.
    * @return En nodo que debe reemplazar a
    * <code>ndorz</code> como raíz del subárbol que fue
    * rotado.
    */
   private NodoAVL<E> rotaIzq(NodoAVL<E> ndorz) {
      NodoAVL<E> aux;

      // aux se refiere al hijo derecho del ndorz
      aux = ndorz.getDerecho();

      // a la derecha de ndorz se pone el hijo izquierdo
      // de aux
      ndorz.setDerecho(aux.getIzquierdo());

      // ndorz es ahora hijo izquierdo de aux
      aux.setIzquierdo(ndorz);

      // aux debe ser el nuevo valor de ndorz
      return aux;
   }

   /**
    * Rota a la derecha el subárbol cuyo nodo ráiz es
    * pasado como parámetro, luego de rotar su subárbol
    * izquierdo a la izquierda.
    * @param ndorz nodo raíz del subárbol a
    * rotar.
    * @return En nodo que debe reemplazar a
    * <code>ndorz</code> como raíz del subárbol que fue
    * rotado.
    */
   private NodoAVL<E> rotaIzqDer(NodoAVL<E> ndorz) {
      // se rota a la izquierda el subárbol izquierdo del
      // ndorz
      ndorz.setIzquierdo(rotaIzq(ndorz.getIzquierdo()));

      // luego se rota a la derecha el árbol cuya raíz es
      // ndorz
      return rotaDer(ndorz);
   }

   /**
    * Rota a la izquierda el subárbol cuyo nodo ráiz es
    * pasado como parámetro, luego de rotar su subárbol
    * derecho a la derecha.
    * @param ndorz nodo raíz del subárbol a
    * rotar.
    * @return En nodo que debe reemplazar a
    * <code>ndorz</code> como raíz del subárbol que fue
    * rotado.
    */
   private NodoAVL<E> rotaDerIzq(NodoAVL<E> ndorz) {
      // se rota a la derecha el subárbol derecho
      // ndorz
      ndorz.setDerecho(rotaDer(ndorz.getDerecho()));

      // luego se rota a la izquierda el árbol cuya raíz
      // es ndorz
      return rotaIzq(ndorz);
   }

   /**
    * Rebalancea (de ser necesario) un árbol para minimizar
    * la altura.
    * Utiliza las transformaciones de rotación AVL.
    * @param ndorz Nodo raíz del subárbol a
    * rebalancear.
    * @return El nuevo nodo raíz del subárbol, el valor de
    * reemplazo de <code>ndorz</code>
    */
   private NodoAVL<E> rebalancea(NodoAVL<E> ndorz) {
      NodoAVL<E> sig;
      int asi = getAlturaNodo(ndorz.getIzquierdo());
      int asd = getAlturaNodo(ndorz.getDerecho());
      int asdi, asdd;
      int asii, asid;

      // ¿requiere rebalanceo?
      if (Math.abs(asi - asd) == 2) {
         // ¿qué subárbol es el más pesado?
         if (asi < asd) {
            // si es el derecho
            // hay que rotar a la izquierda
            sig = ndorz.getDerecho();
            asdi = getAlturaNodo(sig.getIzquierdo());
            asdd = getAlturaNodo(sig.getDerecho());
            // si el sobrepeso es ocasionado por nodo exterior
            if (asdi < asdd) {
               ndorz = rotaIzq(ndorz);
            } else { // si es por un nodo interior
               ndorz = rotaDerIzq(ndorz);
            }
         } else {
            // si el más pesado es el izquierdo
            // hay que rotar a la derecha
            sig = ndorz.getIzquierdo();
            asii = getAlturaNodo(sig.getIzquierdo());
            asid = getAlturaNodo(sig.getDerecho());
            // si el sobrepeso es ocasionado por nodo exterior
            if (asii > asid) {
               ndorz = rotaDer(ndorz);
            } else { // si es por un nodo interior
               ndorz = rotaIzqDer(ndorz);
            }
         }
      }

      return ndorz;
   }

   /**
    * Regresa la altura de un nodo.
    * @param n es el nodo del que se requiere la altura.
    * @return un entero mayor o igual a -1 con la altura del nodo.
    */
   private int getAlturaNodo(NodoAVL<E> n) {
      if (n == null) {
         return -1;
      } else {
         return n.getAltura();
      }
   }

   /**
    * Establece la altura de un nodo.
    * @param n es el nodo al que se le pondrá la altura.
    */
   private void setAlturaNodo(NodoAVL<E> n) {
      if (n != null) {
         n.setAltura(maxAlturas(n.getDerecho(), n.getIzquierdo()) + 1);
      }
   }

   /**
    * Para obtener la máxima de las alturas de dos nodos.
    * @param n1 es uno de los nodos.
    * @param n2 es el otro.
    * @return regresa el máximo de las alturas de ambos nodos.
    */
   private int maxAlturas(NodoAVL<E> n1, NodoAVL<E> n2) {
      int a1 = -1;

      if (n1 != null) {
         a1 = n1.getAltura();
      }
      int a2 = -1;

      if (n2 != null) {
         a2 = n2.getAltura();
      }
      return (a1 > a2) ? a1 : a2;
   }

   /**
    * Método de trabajo, recursivo, que inserta un nuevo
    * elemento en un árbol AVL a partir de el nodo raíz del
    * subárbol donde debe insertarse.
    * @param elem El elemento a insertar
    * @param ndorz El nodo en la raíz del
    * subárbol donde se insertará el elemento.
    * @return El nuevo nodo raíz del subárbol, el valor de
    * reemplazo de <code>ndorz</code>
    */
   private NodoAVL<E> insertaAVL(E elem, NodoAVL<E> ndorz) {
      if (ndorz == null) {
         ndorz = new NodoAVL<E>(elem);
      } else if (elem.compareTo(ndorz.getContenido()) < 0) {
         ndorz.setIzquierdo(insertaAVL(elem,
                                      (NodoAVL<E>) ndorz.getIzquierdo()));
      } else if (elem.compareTo(ndorz.getContenido()) > 0) {
         ndorz.setDerecho(insertaAVL(elem, (NodoAVL<E>) ndorz.getDerecho()));
      } else {
         System.out.println("InsertaAVL: ya estaba el elemento");
      }

      setAlturaNodo(ndorz);

      ndorz = rebalancea(ndorz);

      setAlturaNodo(ndorz.getDerecho());
      setAlturaNodo(ndorz.getIzquierdo());
      ndorz = rebalancea(ndorz);

      return ndorz;
   }

   /**
    * Método de trabajo, recursivo, que elimina un
    * elemento en un árbol AVL a partir de el nodo raíz del
    * subárbol donde debe insertarse.
    * @param elem El elemento a eliminar
    * @param ndorz El nodo en la raíz del
    * subárbol donde se insertará el elemento.
    * @return El nuevo nodo raíz del subárbol, el valor de
    * reemplazo de <code>ndorz</code>
    */
   private NodoAVL<E> eliminaAVL(E elem, NodoAVL<E> ndorz) {
      NodoAVL<E> sig;

      if (ndorz == null) {
         // no se encontró
         return null;
      } else {
         if (elem.compareTo(ndorz.getContenido()) < 0) {
            ndorz.setIzquierdo(eliminaAVL(elem,
                                          (NodoAVL<E>) ndorz.getIzquierdo()));
            ndorz.setAltura(maxAlturas(ndorz.getDerecho(),
                                       ndorz.getIzquierdo()) + 1);
            ndorz = rebalancea(ndorz);
         } else if (elem.compareTo(ndorz.getContenido()) > 0) {
            ndorz.setDerecho(eliminaAVL(elem,
                                        (NodoAVL<E>) ndorz.getDerecho()));
            ndorz.setAltura(maxAlturas(ndorz.getDerecho(),
                                       ndorz.getIzquierdo()) + 1);
            ndorz = rebalancea(ndorz);
         } else { // este es el nodo que hay que eliminar

            // si tiene dos hijos
            if (((NodoAVL<E>) ndorz.getDerecho() != null)
                && ((NodoAVL<E>) ndorz.getIzquierdo() != null)) {
               // el mínimo del subárbol derecho queda en
               // lugar del eliminado y lo eliminamos de
               // donde estaba originalmente
               ndorz.setContenido(getMin((NodoAVL<E>)
                                         ndorz.getDerecho()).getContenido());
               ndorz.setDerecho(eliminaAVL(ndorz.getContenido(),
                                           (NodoAVL<E>) ndorz.getDerecho()));
               ndorz.setAltura(maxAlturas(ndorz.getDerecho(),
                                          ndorz.getIzquierdo()) + 1);
               ndorz = rebalancea(ndorz);
            } else { // si solo tiene un hijo

               // el hijo ocupa el lugar del padre
               ndorz = ((NodoAVL<E>) ndorz.getDerecho() != null)
                  ? (NodoAVL<E>) ndorz.getDerecho()
                  : (NodoAVL<E>) ndorz.getIzquierdo();
               if (ndorz != null) {
                  ndorz.setAltura(maxAlturas(ndorz.getDerecho(),
                                             ndorz.getIzquierdo()) + 1);
               }
            }
         }
         return ndorz;
      }
   }

   /**
    * Inserta un nuevo elemento en un árbol AVL preservando
    * las propiedades de orden y balanceo.
    * @param elem Elemento a insertar.
    */
   @Override
      public void inserta(E elem) {
      raiz = insertaAVL(elem, (NodoAVL<E>) raiz);
   }

   /**
    * Elimina un elemento de un árbol AVL preservando las
    * propiedades de orden y balanceo.
    * @param elem Elemento a eliminar.
    */
   @Override
      public void elimina(E elem) {
      raiz = eliminaAVL(elem, (NodoAVL<E>) raiz);
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
   private String toString(NodoAVL<E> nodoraiz, int nivel) {
      char[] arreglotabs = null;
      String nueva = new String("");

      if (nodoraiz != null) {
         arreglotabs = new char[nivel];

         for (int i = 0; i < nivel; i++) {
            arreglotabs[i] = '\t';
         }

         nueva = nueva.concat(new String(arreglotabs)
                              + nodoraiz.getContenido().toString()
                              + " " + nodoraiz.getAltura() + "\n");
         nueva = nueva.concat(toString(nodoraiz.getDerecho(), nivel + 1));
         nueva = nueva.concat(toString(nodoraiz.getIzquierdo(), nivel + 1));
      }

      return nueva;
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
      return toString((NodoAVL<E>) raiz, 0);
   }

   /**
    * Programa de prueba.
    * @param args ninguno
    */
   public static void main(String[] args) {
      ArbolBinAVL<String> abb = new ArbolBinAVL<String>();

      abb.inserta(new String("Dnodo"));
      abb.inserta(new String("Bnodo"));
      abb.inserta(new String("Gnodo"));
      abb.inserta(new String("Anodo"));
      abb.inserta(new String("Cnodo"));
      abb.inserta(new String("Fnodo"));
      abb.inserta(new String("Hnodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("aAVL01");

      abb.inserta(new String("Inodo"));
      abb.generaDotPrg("aAVL01a");
      abb.inserta(new String("Jnodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("aAVL02");

      abb.inserta(new String("Knodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("aAVL03");

      abb.inserta(new String("Enodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("aAVL04");

      System.out.println("Mínimo: " + abb.getMin());
      System.out.println("Máximo: " + abb.getMax());

      if (abb.esElemento(new String("Anodo"))) {
         System.out.println("Si está Anodo");
      }

      if (!abb.esElemento(new String("Godo"))) {
         System.out.println("No está Godo");
      }

      abb.elimina(new String("Jnodo"));
      abb.generaDotPrg("aAVL05");
      abb.elimina(new String("Hnodo"));
      abb.generaDotPrg("aAVL06");
      abb.inserta(new String("Fpodo"));
      abb.generaDotPrg("aAVL07");
      abb.elimina(new String("Knodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("aAVL08");
   }
} // Fin de ArbolBinAVL.java
