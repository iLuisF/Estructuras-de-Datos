/* -------------------------------------------------------------------
  * NodoBin.java
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
  * -------------------------------------------------------------------
  */
package mx.unam.fciencias.eda;

/**
 * Implementa nodos de un árbol binario. Esta clase será
 * utilizada en <code>ArbolBin</code>
 *
 * @see ArbolBinLigado
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo 2015
 */
public class NodoBin<E> {
   /**
    * Un nodo contiene un elemento de cualquier tipo.
    */
   private E elem;

   /**
    * Una referencia al nodo de la derecha.
    */
   private NodoBin<E> der;

   /**
    * Una referencia al nodo de la izquierda.
    */
   private NodoBin<E> izq;

   /**
    * Construye un nodo vacío.
    */
   public NodoBin() {
      elem = null;
      der = izq = null;
   }

   /**
    * Construye un nodo dado el elemento que contendrá.
    * @param elemento elemento en el nodo.
    */
   public NodoBin(E elemento) {
      elem = elemento;
      der = izq = null;
   }

   /**
    * Construye un nodo dado el elemento que contendrá y
    * sus nodos hijos.
    * @param elemento elemento en el nodo.
    * @param derecho nodo a la derecha.
    * @param izquierdo nodo a la izquierda.
    */
   public NodoBin(E elemento, NodoBin<E> derecho, NodoBin<E> izquierdo) {
      elem = elemento;
      der = derecho;
      izq = izquierdo;
   }

   /**
    * Establece el elemento de un nodo ya existente.
    * @param elemento nuevo elemento en el
    * nodo.
    */
   public void setContenido(E elemento) {
      elem = elemento;
   }

   /**
    * Establece el hijo derecho del nodo.
    * @param derecho nodo a la derecha de la
    * instancia que invoca el método.
    */
   public void setDerecho(NodoBin<E> derecho) {
      der = derecho;
   }

   /**
    * Establece el hijo izquierdo del nodo.
    * @param izquierdo nodo a la izquierda de
    * la instancia que invoca el método.
    */
   public void setIzquierdo(NodoBin<E> izquierdo) {
      izq = izquierdo;
   }

   /**
    * Permite conocer el contenido del nodo.
    * @return Una referencia al objeto contenido en el
    * nodo.
    */
   public E getContenido() {
      return elem;
   }

   /**
    * Permite obtener una referencia al nodo derecho de la
    * instancia que invoca al método.
    * @return Una referencia al nodo derecho del que
    * llama.
    */
   public NodoBin<E> getDerecho() {
      return der;
   }

   /**
    * Permite obtener una referencia al nodo izquierdo de
    * la instancia que invoca al método.
    * @return Una referencia al nodo izquierdo del que
    * llama.
    */
   public NodoBin<E> getIzquierdo() {
      return izq;
   }

   /**
    * Regresa el número de hijos del nodo.
    * @return 0, 1 o 2 depemdiendo del número de hijos descendiendo del
    * nodo.
    */
   public int cuantosHijos() {
      int num = 0;
      if (izq != null) {
         ++num;
      }
      if (der != null) {
         ++num;
      }
      return num;
   }

   /**
    * Regresa verdadero si el nodo es hoja (tiene
    * referencias vacías a sus hijos izquierdo y derecho).
    * @return <code>true</code> si el nodo es hoja,
    * <code>false</code> en otro caso.
    * @since 1.1
    */
   public boolean sinHijos() {
      return ((izq == null) && (der == null));
   }
} // Fin de NodoBin.java
