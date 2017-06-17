 /* -------------------------------------------------------------------
 * Ordenamientos.java
 * versión 6.0
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
 * resulte de utilidad, pero SIN GARANElemento(ÍA ALGUNA; de hecho
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
 * Algoritmos de ordenamiento. Dado que la clase sólo contiene
 * algoritmos implementados como métodos estáticos, no puede ser
 * extendida.
 *
 * @since 1.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 6.0 <br>
 * abril 2015
 */
public final class Ordenamientos {
   /**
    * Constructor formal.
    * Sólo para garantizar que no hay uno público por omisión.
    */
   private Ordenamientos() {
      // jamás llamado.
   }

   /**
    * Intercambia dos elementos de un arreglo.
    * @param arreglo contiene los elementos entre los que están los que se
    * deben intercambiar.
    * @param i es el índice de un elemento válido del arreglo.
    * @param j es el índice de otro elemento del arreglo.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   private static <E> void swap(E[] arreglo, int i, int j) {
      E tmp;
      tmp = arreglo[i];
      arreglo[i] = arreglo[j];
      arreglo[j] = tmp;
   }

   /**
    * Invierte el orden de un arreglo.
    * @param arreglo es el arreglo a invertir
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E> void invierte(E[] arreglo) {
      int  tam = arreglo.length;
      int  i, j, mitad;

      for (i = 0, mitad = tam >>> 1, j = tam - 1; i < mitad; i++, j--) {
         swap(arreglo, i, j);
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por selección) crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void selectionSort(E[] arreglo) {
      if (arreglo.length <= 1) {
         return;
      }

      int i;
      int j;
      int idxmin;

      for (i = 0; i < arreglo.length - 1; i++) {
         idxmin = i;
         for (j = i + 1; j < arreglo.length; j++) {
            if (arreglo[idxmin].compareTo(arreglo[j]) > 0) {
               idxmin = j;
            }
         }
         if (idxmin != i) {
            swap(arreglo, i, idxmin);
         }
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por el método de la burbuja)
    * crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void bubbleSort(E[] arreglo) {
      if (arreglo.length <= 1) {
         return;
      }

      int i;
      int j;

      for (i = 0; i < (arreglo.length - 1); i++) {
         for (j = arreglo.length - 1; j > i; j--) {
            if (arreglo[j].compareTo(arreglo[j - 1]) < 0) {
               swap(arreglo, j, j - 1);
            }
         }
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por inserción) crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void insertionSort(E[] arreglo) {
      if (arreglo.length <= 1) {
         return;
      }

      int i;
      for (i = 1; i < arreglo.length; i++) {
         E tmp = arreglo[i];
         int j;
         for (j = i - 1; (j >= 0)
                 && (tmp.compareTo(arreglo[j]) < 0);
              j--) {
            arreglo[j + 1] = arreglo[j];
         }
         arreglo[j + 1] =  tmp;
      }
   }

   /**
    * Ordena un sub-arreglo (ordenamiento por inserción) crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>. Este es un método
    * llamado por la versión de Quicksort que hace un corte de recursión.
    * @param arreglo contiene los elementos a ordenar.
    * @param inicio es el índice a partir del cual se ordenará.
    * @param fin es el índice hasta el cual se ordenará.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   private static <E extends Comparable<? super E>>
                             void insertionSort(E[] arreglo,
                                                int inicio, int fin) {
      if ((fin - inicio + 1) <= 1) {
         return;
      }

      int i;
      for (i = inicio + 1; i <= fin; i++) {
         E tmp = arreglo[i];
         int j;
         for (j = i - 1;
              (j >= inicio) && tmp.compareTo(arreglo[j]) < 0;
              j--) {
            arreglo[j + 1] = arreglo[j];
         }
         arreglo[j + 1] = tmp;
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por el método de Shell)
    * crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void shellSort(E[] arreglo) {
      if (arreglo.length <= 1) {
         return;
      }

      // De acuerdo con:
      // Gonnet, Gaston H.; Baeza-Yates, Ricardo,
      // "Handbook of Algorithms and Data Structures",
      // Addison-Wesley. 1991, pp. 161–163.
      // 2.2 = 11/5 como divisor, minimiza la cantidad de comparaciones.
      int i;
      int distancia;
      for (distancia = arreglo.length / 2;
           distancia > 0;
           distancia = (distancia == 2) ? 1 : (int)(distancia/2.2) ) {

         // El segmento de código a continuación trata de hacer
         // evidente que InsertionSort es ShellSort con distancia = 1
         for (i = distancia; i < arreglo.length; i++) {
            E tmp = arreglo[i];
            int j;
            for (j = i - distancia; (j >= 0)
                    && (tmp.compareTo(arreglo[j]) < 0);
                 j -= distancia) {
               arreglo[j + distancia] = arreglo[j];
            }
            arreglo[j + distancia] = tmp;
         }
      }
   }

   /**
    * Hace la mezcla de las dos arreglos ordenados.
    * @param arreglo es donde estan los elementos a ordenar.
    * @param primero es el índice inicial del subarreglo de
    * la izquierda
    * @param enmedio es el índice inicial del subarreglo de
    * la derecha
    * @param ultimo es el índice final del subarreglo de la
    * derecha
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>>
                  void merge(E[] arreglo,
                             int primero, int enmedio, int ultimo) {
      int  i = primero, d = enmedio + 1;
      int  r = 0;
      E[] resul = (E[]) new Comparable[ultimo - primero + 1];

      // Se compara el i-esimo elemento de la parte izquierda y
      // el d-esimo de la derecha. EL que resulte menor se pasa
      // al arreglo resultante
      while ((i <= enmedio) && (d <= ultimo)) {
         if (arreglo[i].compareTo(arreglo[d]) <= 0) {
            resul[r++] = arreglo[i++];
         } else {
            resul[r++] = arreglo[d++];
         }
      }
      // se acaba una o ambas mitades.
      // Si faltaron elementos por copiar, generalmente de una de
      // las mitades...
      while (i <= enmedio) {
         resul[r++] = arreglo[i++];
      }
      while (d <= ultimo) {
         resul[r++] = arreglo[d++];
      }
      // se copia al arreglo arreglo
      for (i = primero, d = 0; i <= ultimo; i++, d++) {
         arreglo[i] = resul[d];
      }
   }

   /**
    * Algoritmo de Mergesort.
    * @param arreglo contiene los datos a ordenar
    * @param izq es el índice inicial del subarreglo a
    * ordenar
    * @param der es el índice final  del subarreglo a
    * ordenar.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   private static <E extends Comparable<? super E>>
                             void mergesort(E[] arreglo, int izq, int der) {
      if (izq < der) {
         int enmedio = (izq + der) / 2;

         int i;

         mergesort(arreglo, izq, enmedio);
         mergesort(arreglo, enmedio + 1, der);
         merge(arreglo, izq, enmedio, der);
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por mezcla) crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void mergeSort(E[] arreglo) {
      if (arreglo.length > 1) {
         mergesort(arreglo, 0, arreglo.length - 1);
      }
   }

   /**
    * Implementa el mecanismo de determinación de los nuevos
    * límites para quicksort. Parte el arreglo en dos.
    * @param arreglo es el arreglo a partir.
    * @param inf es el límite inferior del subarreglo a considerar.
    * @param sup es el límite superior del subarreglo a considerar.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    * @return un arreglo con los nuevos límites. El límite
    * menor en el lugar 0 del arreglo, el mayor en el lugar
    * 1.
    */
   private static <E extends Comparable<? super E>>
                             int[] particion(E[] arreglo, int inf, int sup) {
      E pivote;
      E eleminf;
      E elemsup;
      int[] res = new int[2];

      pivote = arreglo[(inf + sup) / 2];

      do {
         eleminf = arreglo[inf];
         elemsup = arreglo[sup];

         while (eleminf.compareTo(pivote) < 0) {
            inf++;
            eleminf = arreglo[inf];
         }

         while (elemsup.compareTo(pivote) > 0) {
            sup--;
            elemsup = arreglo[sup];
         }

         if (inf <= sup) {
            if (inf != sup) {
               swap(arreglo, inf, sup);
            }

            inf++;
            sup--;
         }
      } while (inf <= sup);

      res[0] = sup;
      res[1] = inf;

      return res;
   }

   /**
    * Implementación de QuickSort recursiva. Ordena el arreglo entre los
    * índices que se especifican.
    * @param arreglo contiene los datos a ordenar.
    * @param inf es el índice inferior a ser considerado para el
    * ordenamiento en la llamada actual.
    * @param sup es el índice superior a ser considerado para el
    * ordenamiento en la llamada actual.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   private static <E extends Comparable<? super E>>
                             void quicksort(E[] arreglo, int inf, int sup) {
      int i;
      int j;
      int[] lims = null;

      if (inf < sup) {
         i = inf;
         j = sup;
         lims = particion(arreglo, i, j);
         quicksort(arreglo, inf, lims[0]);
         quicksort(arreglo, lims[1], sup);
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por quicksort)
    * crecientemente.
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void quickSort(E[] arreglo) {
      if (arreglo.length <= 1) {
         return;
      }

      quicksort(arreglo, 0, arreglo.length - 1);
   }

   /*
     Otra versión de Quicksort optimizada
    */
   /**
    * Regresa la mediana de tres datos.
    * @param <E> es el tipo de elementos a considerar. Deben ser
    * implementaciones de la interfaz <code>Comparable</code>.
    * @param e1 elemento a considerar.
    * @param e2 elemento a considerar.
    * @param e3 elemento a considerar.
    * @return la mediana de los tres datos pasados como parámetro.
    */
   private static <E extends Comparable<? super E>>
                  E mediana(E e1, E e2, E e3) {
      E tmp;
      if (e2.compareTo(e1) < 0) {
         tmp = e2;
         e2 = e1;
         e1 = tmp;
      }
      if (e3.compareTo(e1) < 0) {
         tmp = e3;
         e3 = e1;
         e1 = tmp;
      }
      if (e3.compareTo(e2) < 0) {
         tmp = e3;
         e3 = e2;
         e2 = tmp;
      }
      return e2;
   }

   /**
    * Implementa el mecanismo de determinación de los nuevos
    * límites para quicksort usando la mediana de tres datos.
    * Parte el arreglo en dos.
    * @param arreglo es, claro, el que se debe partir.
    * @param inf es el límite inferior del arreglo.
    * @param sup es el límite superior.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    * @return un arreglo con los nuevos límites. El límite
    * menor en el lugar 0 del arreglo, el mayor en el lugar
    * 1.
    */
   private static <E extends Comparable<? super E>>
                             int[] particionMed(E[] arreglo, int inf, int sup) {
      E enmedio = arreglo[(inf + sup) >>> 1];
      E eleminf = arreglo[inf];
      E elemsup = arreglo[sup];
      E pivote;
      int[] res = new int[2];

      pivote = mediana(eleminf, enmedio, elemsup);

      do {
         eleminf = arreglo[inf];
         elemsup = arreglo[sup];

         while (eleminf.compareTo(pivote) < 0) {
            inf++;
            eleminf = arreglo[inf];
         }

         while (elemsup.compareTo(pivote) > 0) {
            sup--;
            elemsup = arreglo[sup];
         }

         if (inf <= sup) {
            if (inf != sup) {
               swap(arreglo, inf, sup);
            }
            inf++;
            sup--;
         }
      } while (inf <= sup);

      res[0] = sup;
      res[1] = inf;

      return res;
   }

   /**
    * Implementación de QuickSort recursiva optimizada. Ordena el arreglo
    * entre los índices que se especifican.
    * @param arreglo contiene los datos a ordenar.
    * @param inf es el índice inferior a ser considerado para el
    * ordenamiento en la llamada actual.
    * @param sup es el índice superior a ser considerado para el
    * ordenamiento en la llamada actual.
    * @param corte es el número mínimo de elementos para proceder con
    * quicksort, si el segmento de arreglo que queda por ordenar tiene
    * menos de este número, se ordena con insertion sort.
    * @param <E> es el tipo genérico de elementos en el arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   private static <E extends Comparable<? super E>>
                             void quickQuicksort(E[] arreglo, int inf, int sup,
                                            int corte) {
      int i;
      int j;
      int[] lims = null;

      if (inf < sup) {
         if ((sup - inf + 1) > corte) {
            i = inf;
            j = sup;
            lims = particionMed(arreglo, i, j);
            quickQuicksort(arreglo, inf, lims[0], corte);
            quickQuicksort(arreglo, lims[1], sup, corte);
         } else {
            insertionSort(arreglo, inf, sup);
         }
      }
   }

   /**
    * Ordena el arreglo (ordenamiento por quicksort)
    * crecientemente. Este método tiene dos optimizaciones:
    * <UL>
    * <LI> Tiene un umbral de corte para no continuar recursivamente si el
    * n'umero de elementos a ordenar es menor que la cota.</LI>
    * <LI> El pivote es la mediana de tres valores del arreglo.</LI>
    * </UL>
    * Los elementos en el arreglo a ordenar deben ser de una clase que
    * implemente la interfaz <code>Comparable</code>.
    * @param arreglo contiene los elementos a ordenar.
    * @param corte es el número mínimo de elementos para hacer recursión
    * con quicksort.
    * @param <E> es el tipo genérico de elementos en la arreglo, en esta clase
    * se suponen implementaciones de <code>Comparable</code>.
    */
   public static <E extends Comparable<? super E>>
                            void quickQuickSort(E[] arreglo, int corte) {
      if (arreglo.length <= 1) {
         return;
      }

      quickQuicksort(arreglo, 0, arreglo.length - 1, corte);
   }
}  // fin de Ordenamientos.java
