/* ----------------------------------------------------------------------
 * Heap.java
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

import java.util.Comparator;

/**
 * Implementación de heap. Es configurable para ser un
 * MaxHeap o MinHeap.
 * @param <E> es el tipo de elementos almacenados en el Heap. Debe
 * implementar la interfaz <code>Comparable</code>.
 *
 * @since 2.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo 2015
 */
public class Heap<E extends Object & Comparable<? super E>> {
   /**
    * Constante que puede usarse para especificar en el constructor que se
    * construirá un Min-Heap.
    */
   public static final boolean MINHEAP = true;

   /**
    * Constante que puede usarse para especificar en el constructor que se
    * construirá un Max-Heap.
    */
   public static final boolean MAXHEAP = !MINHEAP;

   /**
    * Mensaje de error: intento de acceder a heap vacío.
    */
   private static final String ERR_HV = "Heap vacío";
   /**
    * Tamaño por omisión.
    */
   private static final int DEFAULT_TAM = 257;

   /**
    * Para preservar el número de elementos actualmente en el heap.
    */
   private int nelems;
   /**
    * El heap representado en un arreglo lineal.
    */
   private E[] arregloarbol;
   /**
    * Comparador para determinar donde se debe insertar un elemento.
    */
   private Comparator<? super E> cmp;

   /**
    * Verdadero si es un Min Heap, falso en caso de un Max Heap.
    */
   private boolean esminheap;

   /**
    * Percola un elemento recién ingresado al heap, hasta que quede
    * acomodado de acuerdo con las reglas del heap.
    * @param idx es el índice donde el elemento fue insertado.
    */
   private void percolaArriba(int idx) {
      int ie = idx;
      int ip = idxPadre(idx);
      // Si el padre es mayor que el hijo
      // en un minHeap, están mal comodados.
      while (tienePadre(ie)
             && (compara(arregloarbol[ip], arregloarbol[ie]) > 0)) {
         swap(ie, ip);
         ie = ip;
         ip = idxPadre(ie);
      }
   }

   /**
    * Percola un elemento recién ingresado al heap, hasta que quede
    * acomodado de acuerdo con las reglas del heap.
    * @param idx es el índice donde el elemento fue insertado.
    */
   private void percolaAbajo(int idx) {
      while (tieneHijoIzq(idx)) {
         // El menor de los hijos
         int hijomenor;
         // Si existen hijos, determinamos el menor de ellos
         if (tieneHijoDer(idx)
             && compara(arregloarbol[idxHijoIzq(idx)],
                        arregloarbol[idxHijoDer(idx)]) > 0) {
            hijomenor = idxHijoDer(idx);
         } else {
            hijomenor = idxHijoIzq(idx);
         }
         // E intercambiamos con el
         if (compara(arregloarbol[idx],
                     arregloarbol[hijomenor]) > 0) {
            swap(idx, hijomenor);
         } else {
            // Si no está mal acomodado, terminamos
            break;
         }
         idx = hijomenor;
      }
   }

   /**
    * Dado el índice de un elemento en el arreglo, considerando que éste
    * representa un árbol binario, regresa el índice que le corresponde al
    * elemento que sería su padre. Los elementos se empiezan a almacenar en
    * el índice cero.
    * @param h es el índice, en el arreglo, del hijo (izq o der).
    * @return un entero no negativo con el índice que le correspondería al
    * padre del elemento cuyo índice se dió como parámetro.
    */
   private int idxPadre(int h) {
      return (h - 1) / 2;
   }

   /**
    * Dado el índice de un elemento en el arreglo, considerando que éste
    * representa un árbol binario, regresa el índice que le corresponde al
    * elemento que sería su hijo izquierdo.  Los elementos se empiezan a
    * almacenar en el índice cero.
    * @param p es el índice, en el arreglo, del padre.
    * @return un entero no negativo con el índice que le correspondería al
    * hijo izquierdo del elemento cuyo índice se dió como parámetro.
    */
   private int idxHijoIzq(int p) {
      return (2 * p) + 1;
   }

   /**
    * Dado el índice de un elemento en el arreglo, considerando que éste
    * representa un árbol binario, regresa el índice que le corresponde al
    * elemento que sería su hijo derecho.  Los elementos se empiezan a
    * almacenar en el índice cero.
    * @param p es el índice, en el arreglo, del padre.
    * @return un entero no negativo con el índice que le correspondería al
    * hijo derecho del elemento cuyo índice se dió como parámetro.
    */
   private int idxHijoDer(int p) {
      return (2 * p) + 2;
   }

   /**
    * Determina si un elemento dado del arreglo, tiene padre.
    * @param h es el índice en el arreglo, del presunto hijo.
    * @return verdadero si el elemento tiene padre.
    */
   private boolean tienePadre(int h) {
      return (h >= 1);
   }

   /**
    * Determina si un elemento dado del arreglo, tiene hijo izquierdo.
    * @param p es el índice en el arreglo, del presunto padre.
    * @return verdadero si el elemento tiene hijo izquierdo.
    */
   private boolean tieneHijoIzq(int p) {
      return (idxHijoIzq(p) < nelems);
   }

   /**
    * Determina si un elemento dado del arreglo, tiene hijo derecho.
    * @param p es el índice en el arreglo, del presunto padre.
    * @return verdadero si el elemento tiene hijo derecho.
    */
   private boolean tieneHijoDer(int p) {
      return (idxHijoDer(p) < nelems);
   }

   /**
    * Intercambia dos elementos del arreglo que representa al heap.
    * @param i es el índice de uno de los elementos.
    * @param j es el índice del otro de los elementos.
    */
   private void swap(int i, int j) {
      E  tmp   = arregloarbol[i];
      arregloarbol[i] = arregloarbol[j];
      arregloarbol[j] = tmp;
   }

   /**
    * Redefine el arreglo para incrementar su tamaño. Se agregan lugares
    * como para añadir otro nivel al árbol.
    */
   @SuppressWarnings("unchecked")
   private void redefineArreglo() {
      E[] nuevoarbol;

      nuevoarbol = (E[]) new Object[arregloarbol.length * 2];
      for (int i = 0; i < arregloarbol.length; i++) {
         nuevoarbol[i] = arregloarbol[i];
      }
      arregloarbol = nuevoarbol;
   }

   /**
    * Compara un par de elementos. Si no se definió un comparador para el
    * heap, se usa el método <code>compareTo</code> del tipo E.
    * @param eliz primer elemento a comparar.
    * @param elder segundo elemento a comparar.
    * @return entero: &lt; 0 si eliz &lt; elder, 0 si eliz = elder y &gt; 0 si
    * eliz &gt; elder.
    */
   @SuppressWarnings("unchecked")
   private int compara(E eliz, E elder) {
      if (cmp == null) {
         int sgn = (esminheap) ? 1 : -1;
         return sgn * ((Comparable<E>) eliz).compareTo(elder);
      } else {
         return cmp.compare(eliz, elder);
      }
   }

   /**
    * Construye un Min-Heap vacío. Por omisión se usará el método
    * <code>compareTo</code> de la clase de objetos almacenados en el
    * heap. El método <code>getElemento()</code> entregará el mínimo de los
    * elementos guardados en el heap.
    */
   @SuppressWarnings("unchecked")
   public Heap() {
      esminheap = MINHEAP;
      nelems = 0;
      cmp = null;
      arregloarbol = (E[]) new Object[DEFAULT_TAM];
   }

   /**
    * Construye un Heap vacío. Por omisión se usará el método
    * <code>compareTo</code> de la clase de objetos almacenados en el
    * heap. El método <code>getElemento()</code> entregará el mínimo o
    * máximo de los elementos guardados en el heap de acuerdo con el tipo
    * de heap creado.
    * @param tipo es el especificador del tipo de Heap.
    * @see #MINHEAP
    * @see #MAXHEAP
    */
   @SuppressWarnings("unchecked")
   public Heap(boolean tipo) {
      esminheap = tipo;
      nelems = 0;
      cmp = null;
      arregloarbol = (E[]) new Object[DEFAULT_TAM];
   }

   /**
    * Construye un heap vacío y define el comparador que se usará para
    * colocar a sus elementos. El heap resultante será un Min-Heap o
    * Max-Heap, según se comporte el comparador pasado como parámetro.
    * @param comparador es el <code>Comparator</code> que se usará para
    * determinar la posición de los elementos en el heap.
    */
   @SuppressWarnings("unchecked")
   public Heap(Comparator<? super E> comparador) {
      esminheap = MINHEAP;
      nelems = 0;
      cmp = comparador;
      arregloarbol = (E[]) new Object[DEFAULT_TAM];
   }

   /**
    * Determina si el heap es vacío.
    * @return true si el heap es vacío, false en otro caso.
    */
   public boolean isVacio() {
      return (nelems == 0);
   }

   /**
    * Determina si el heap es vacío.
    * @return true si el heap es vacío, false en otro caso.
    */
   public boolean esVacio() {
      return isVacio();
   }

   /**
    * Inserta un nuevo elemento en el heap.
    * @param nvoelem es el elemento a insertar.
    */
   public void inserta(E nvoelem) {
      // si se acabó el espacio disponible
      if (nelems == arregloarbol.length - 1) {
         redefineArreglo();
      }
      arregloarbol[nelems] = nvoelem;
      percolaArriba(nelems);
      nelems++;
   }

   /**
    * Regresa el número de elementos en el heap. El heap puede contener
    * elementos repetidos, cada aparición de un elemento se cuenta, es
    * decir, no se regresa el número de elementos distintos, sino contando
    * duplicidades.
    * @return entero no negativo con el número de elementos en el heap.
    */
   public int numElementos() {
      return nelems;
   }

   /**
    * Vacía el heap.
    */
   @SuppressWarnings("unchecked")
   public void limpia() {
      nelems = 0;
      arregloarbol = (E[]) new Object[DEFAULT_TAM];
   }

   /**
    * Regresa el elemento mínimo o máximo almacenado en el heap. El
    * elemento entregado depende del tipo de heap: el mínimo en un
    * Min-Heap, el máximo en un Max-Heap.
    * @return el elemento mínimo/máximo almacenado según sea el caso.
    * @throws ErrorAccesoException si se pretende acceder a un heap vacío.
    */
   public E getElemento()
      throws ErrorAccesoException {
      if (isVacio()) {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".getElemento: " + ERR_HV);
      }
      return arregloarbol[0];
   }

   /**
    * Elimina y regresa el elemento mínimo o máximo almacenado en el
    * heap. El elemento eliminado y entregado depende del tipo de heap: el
    * mínimo en un Min-Heap, el máximo en un Max-Heap.
    * @return el elemento mínimo/máximo almacenado según sea el caso.
    * @throws ErrorAccesoException si se pretende extraer de un heap
    * vacío.
    */
   public E elimina()
      throws ErrorAccesoException {
      E elem = getElemento();

      arregloarbol[0] = arregloarbol[nelems - 1];
      percolaAbajo(0);
      nelems--;
      return elem;
   }

   /**
    * Programa de prueba.
    * @param args vacío.
    */
   public static void main(String[] args) {
      try {
         //         Heap<String> hs = new Heap<String>(new String());
         //         Heap<String> hs = new Heap<String>();
                  Heap<String> hs = new Heap<String>(Heap.MINHEAP);
         //         Heap<String> hs = new Heap<String>(Heap.MAXHEAP);

         if (hs.isVacio()) {
            System.out.println("Vacío, OK");
         } else {
            System.out.println("No vacío, Oops!");
         }

         hs.inserta(new String("Cadena 13"));
         hs.inserta(new String("Cadena 04"));
         hs.inserta(new String("Cadena 08"));
         hs.inserta(new String("Cadena 02"));
         hs.inserta(new String("Cadena 01"));
         hs.inserta(new String("Cadena 09"));
         hs.inserta(new String("Cadena 10"));
         hs.inserta(new String("Cadena 03"));
         hs.inserta(new String("Cadena 05"));
         hs.inserta(new String("Cadena 07"));
         hs.inserta(new String("Cadena 11"));
         hs.inserta(new String("Cadena 00"));
         hs.inserta(new String("Cadena 12"));
         hs.inserta(new String("Cadena 06"));
         hs.inserta(new String("Cadena 14"));
         hs.inserta(new String("Cadena 10"));

         if (hs.numElementos() == 16) {
            System.out.println("16 elementos, OK");
         } else {
            System.out.println("Otro tamaño: " + hs.numElementos() +", Oops!");
         }

         int tam = hs.numElementos();
         String min;
         for (int i = 0; i < tam; i++) {
            min = hs.elimina();
            System.out.println("Elemento: " + i + " : " +  min);
         }

         if (hs.isVacio()) {
            System.out.println("Vacío, OK");
         } else {
            System.out.println("No vacío, Oops!");
         }
      } catch (ErrorAccesoException eae) {
         System.err.println("Excepción");
      }
   }
} // Fin de Heap.java
