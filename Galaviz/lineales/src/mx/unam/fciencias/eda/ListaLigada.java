/* -------------------------------------------------------------------
 * ListaLigada.java
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
 * -------------------------------------------------------------------
 */
package mx.unam.fciencias.eda;

import mx.unam.fciencias.eda.ErrorAccesoException;
import mx.unam.fciencias.eda.Lista;
import mx.unam.fciencias.eda.ListaAbstracta;

/**
 * Clase que implementa una lista símplemente ligada de acuerdo con la
 * interfaz <code>Lista</code>.
 * @see Lista
 * @see ListaAbstracta
 *
 * @since 1.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 5.0 <br>
 * marzo 2015
 */

public class ListaLigada<E> extends ListaAbstracta<E> implements Lista<E> {

   /**
    * Mensaje de error
    */
   private static final String ERRMSG = "Índice inválido";

   /**
    * Referencia a la primera celda de la lista.
    */
   private Celda<E> primero;

   /**
    * Número de elementos en la lista.
    */
   private int nelems;

   /**
    * Construye una lista vacía.
    */
   public ListaLigada() {
      primero = null;
      nelems = 0;
   }

   /**
    * Inserta un elemento en la lista.
    * @param idx Es el índice del lugar donde
    * el elemento será insertado. <code>idx</code> debe
    * estar en el conjunto {0, ..., longitud}, de otro modo
    * se levanta una excepción <code>ErrorAccesoException</code>
    * (índice fuera de rango)
    * @param nvoelem El nuevo elemento a
    * insertar.
    * @throws ErrorAccesoException en caso de pretender insertar
    * más allá del final de la lista o antes del
    * principio.
    */
   @Override
   public void inserta(int idx, E nvoelem)
      throws ErrorAccesoException {
      Celda<E> nvacelda;
      Celda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx <= nelems)) {
         // si es el primero de la lista
         if (idx == 0) {
            nvacelda = new Celda<E>(nvoelem, primero);
            primero = nvacelda;
         } else { // no es el primero de la lista

            // recorremos la lista hasta llegar a la
            // celda que lo antecederá (actual)
            actual = primero;

            for (i = 0; i < (idx - 1); i++) {
               actual = actual.getSiguiente();
            }

            // la celda siguiente de la nueva será la
            // que antes seguía de la actual
            nvacelda = new Celda<E>(nvoelem, actual.getSiguiente());

            // y la siguiente de la actual será la nueva
            actual.setSiguiente(nvacelda);
         }

         nelems++; // hay un elemento más
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".inserta: " + ERRMSG);
      }
   }

   /**
    * Reemplaza un elemento de la lista.
    * @param idx es el índice del lugar a reemplazar. Por
    * supuesto este índice debe estar en el conjunto {0,
    * ..., longitud-1}.
    * @param elem es el elemento que reemplazará al que se
    * halla en la posición especificada.
    * @throws ErrorAccesoException en caso de que el índice sea
    * incorrecto.
    */
   @Override
   public void reemplaza(int idx, E elem)
      throws ErrorAccesoException {
      Celda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         // si es el primero de la lista
         if (idx == 0) {
            primero.setContenido(elem);
         } else { // no es el primero de la lista
            // recorremos la lista hasta llegar a la
            // celda objetivo
            actual = primero;

            for (i = 0; i < idx; i++) {
               actual = actual.getSiguiente();
            }

            actual.setContenido(elem);
         }
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".reemplaza: " + ERRMSG);
      }
   }

   /**
    * Elimina el elemento almacenado en un lugar específico
    * de la lista.
    * @param idx índice de la celda a
    * eliminar. <code>idx</code> debeestar en el conjunto
    * {0, ..., longitud-1}, de otro modo se levanta una
    * excepción <code>ErrorAccesoException</code> (índice fuera de
    * rango)
    * @throws ErrorAccesoException en caso de pretender eliminar
    * más allá del final de la lista o antes del
    * principio.
    */
   @Override
   public void elimina(int idx)
      throws ErrorAccesoException {
      Celda<E> anterior; // referencia a la celda previa
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         if (idx == 0) { // si es la primera celda
            primero = primero.getSiguiente();
         } else {
            // si no es la primera
            anterior = primero;

            // recorremos hasta llegar una celda antes
            // de la que queremos eliminar
            for (i = 0; i < (idx - 1); i++) {
               anterior = anterior.getSiguiente();
            }

            // eliminamos la celda siguiente
            anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
         }

         nelems--; // hay un elemento menos
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".elimina: " + ERRMSG);
      }
   }

   /**
    * Permite conocer el elemento guardado en un lugar
    * específico de la lista.
    * @param idx índice del lugar
    * requerido. <code>idx</code> debe estar en el conjunto
    * {0, ..., longitud-1}
    * @return el elemento guardado en la celda de índice
    * <code>idx</code>.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * elemento de más allá del final de la lista o antes
    * del principio.
    */
   @Override
   public E getElemento(int idx)
      throws ErrorAccesoException {
      Celda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         if (idx == 0) { // si se requiere el primero de la
            // lista
            return primero.getContenido();
         } else { // si no recorremos hasta llegar al

            // elemento deseado
            actual = primero;

            for (i = 0; i < idx; i++) {
               actual = actual.getSiguiente();
            }

            return actual.getContenido();
         }
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".elemento: " + ERRMSG);
      }
   }

   /**
    * Determina el número de elementos elmacenados en la
    * lista.
    * @return número de celdas en la lista
    */
   @Override
   public int longitud() {
      return nelems;
   }

   /**
    * Convierte la lista en lista vacía.
    */
   @Override
   public void limpia() {
      primero = null;
      nelems = 0;
   }

   /**
    * Construye un iterador para la lista.
    * @return una nueva instancia de
    * <code>IteradorLista</code>, posicionado en el
    * primer elemento de la lista.
    */
   @Override
   public IteradorLista<E> getIterador() {
      return new IteradorListaLigada();
   }


   /**
    *
    * Clase para implementar celdas de contenido variable,
    * elemento fundamental de diversas estructuras de datos
    * lineales, por ejemplo <code>Lista</code>.
    *
    * @see Lista
    * @see ListaLigada
    * @see Celda
    * @author José Galaviz (galaviz@fciencias.unam.mx)
    * @version 4.0 <br>
    * marzo 2015
    *
    */
   private static class Celda<E> {

      /**
       * Elemento de la celda.
       */
      private E elem;

      /**
       * Liga a la siguiente celda.
       */
      private Celda<E> sig;

      /**
       * Construye una nueva instancia de <code>Celda</code> a
       * partir del elemento a colocar dentro de ella.
       * @param elemento Objeto que se
       * introducirá en la nueva celda.
       */
      public Celda(E elemento) {
         elem = elemento;
         sig = null;
      }

      /**
       * Construye una nueva instancia de <code>Celda</code> a
       * partir del elemento a colocar dentro de ella y de la
       * liga a la siguiente celda.
       * @param elemento Objeto que se
       * introducirá en la nueva celda.
       * @param siguiente Siguiente
       * celda.
       */
      public Celda(E elemento, Celda<E> siguiente) {
         elem = elemento;
         sig = siguiente;
      }

      /**
       * Establece el elemento de la celda.
       * @param elemento Nuevo elemento de la
       * celda.
       */
      public void setContenido(E elemento) {
         elem = elemento;
      }

      /**
       * Establece la liga a la siguiente celda.
       * @param siguiente Siguiente celda.
       */
      public void setSiguiente(Celda<E> siguiente) {
         sig = siguiente;
      }

      /**
       * Permite conocer el elemento guardado en la celda.
       * @return El elemento guardado en la celda.
       */
      public E getContenido() {
         return elem;
      }

      /**
       * Regresa la referencia a la siguiente celda.
       * @return La liga a la siguiente celda.
       */
      public Celda<E> getSiguiente() {
         return sig;
      }
   } // Fin de Celda.java

   /**
    * Clase que implementa iteradores para listas ligadas
    * simples. Sólo es posible recorrer la lista en un
    * sentido.
    *
    * @since 2.0
    * @see ListaLigada
    * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
    * @version 2.0
    * marzo 2015
    */
   private class IteradorListaLigada implements IteradorLista<E> {
      private static final String ERR_LV = "Lista vacía";
      private static final String ERR_NA = "Imposible avanzar";
      private int posicion;
      private Celda<E> actual;

      /**
       * Construye un iterador para lista ligada. Este método
       * es invocado por el método <code>getIterador()</code>
       * de la clase <code>ListaLigada</code>. Una vez obtenido
       * un iterador de una instancia particular de lista, este
       * deja de ser consistente si la instancia de lista que
       * lo creó es modificada mediente sus métodos propios.
       * @see getIterador
       */
      public IteradorListaLigada() {
         posicion = 0;
         actual = primero;
      }

      /**
       * Posiciona el iterador en el primer elemento de la
       * lista.
       * @throws ErrorAccesoException si la lista es vacía o nula.
       */
      @Override
      public void setInicio()
         throws ErrorAccesoException {
         if (primero == null) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                           ".setInicio: " + ERR_LV);
         } else {
            posicion = 0;
            actual = primero;
         }
      }

      /**
       * Determina si hay un siguiente elemento en la lista o
       * no.
       * @return <code>true</code> si hay un siguiente
       * elemento, <code>false</code>  en otro caso.
       */
      @Override
      public boolean hasSiguiente() {
         return (posicion < (nelems - 1));
      }

      /**
       * Determina si hay un siguiente elemento en la lista o
       * no.
       * @return <code>true</code> si hay un siguiente
       * elemento, <code>false</code>  en otro caso.
       */
      @Override
      public boolean haySiguiente() {
         return hasSiguiente();
      }

      /**
       * Avanza el iterador al siguiente elemento disponible en
       * la lista.
       * @throws ErrorAccesoException si ya no hay siguiente elemento.
       */
      @Override
      public void avanza()
         throws ErrorAccesoException {
         if (actual.getSiguiente() == null) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                                ".avanza: " + ERR_NA);
         } else {
            actual = actual.getSiguiente();
            posicion++;
         }
      }

      /**
       * Regresa el elemento en la posición actual del
       * iterador.
       * @return el elemento almacenado en la posición actual
       * de la lista.
       */
      @Override
      public E elementoActual() {
         return actual.getContenido();
      }

      /**
       * Regresa la posición actual del iterador.
       * @return un enetero no negativo con la posición actual
       * del iterador en la lista.
       */
      @Override
      public int posicion() {
         return posicion;
      }

      /**
       * Reemplaza el elemento en la posición actual con el
       * dado como argumento.
       * @param elem es el elemento que substituye el contenido
       * en la posición actual.
       */
      @Override
      public void reemplaza(E elem) {
         actual.setContenido(elem);
      }
   } // Fin de IteradorListaLigada.java
} // fin de ListaLigada.java
