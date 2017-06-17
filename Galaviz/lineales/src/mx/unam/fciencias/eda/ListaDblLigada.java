/* ----------------------------------------------------------------------
 * ListaDblLigada.java
 * versión 3.0
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

import mx.unam.fciencias.eda.ErrorAccesoException;
import mx.unam.fciencias.eda.Lista;

/**
 * Clase que implementa listas doblemente ligadas.
 * @see Lista
 * @see ListaAbstracta
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 2.0 <br>
 * enero 2009
 */
public class ListaDblLigada<E> extends ListaAbstracta<E> implements Lista<E> {

   /**
    * Mensaje de error
    */
   private static final String ERRMSG = "Índice inválido";

   /**
    * Referencia a la primera celda de la lista.
    */
   private BiCelda<E> primero;

   /**
    * Referencia a la última celda de la lista.
    */
   private BiCelda<E> ultimo;

   /**
    * Número de elementos en la lista.
    */
   private int nelems;

   /**
    * Construye una lista vacía.
    */
   public ListaDblLigada() {
      primero = ultimo = null;
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
   public void inserta(int idx, E nvoelem)
      throws ErrorAccesoException {
      BiCelda<E> nvacelda;
      BiCelda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx <= nelems)) {
         // si es el primero de la lista
         if (idx == 0) {
            nvacelda = new BiCelda<E>(nvoelem, primero, null);

            if (nelems != 0) {
               primero.setAnterior(nvacelda);
            }

            primero = nvacelda;

            if (nelems == 0) {
               ultimo = primero;
            }
         } else if (idx == nelems) { // si es el último
            nvacelda = new BiCelda<E>(nvoelem, null, ultimo);
            ultimo.setSiguiente(nvacelda);
            ultimo = nvacelda;
         } else if (idx <= (nelems / 2)) { // primera mitad

            // recorremos la lista hasta llegar a la
            // celda que lo antecederá (actual)
            actual = primero;

            for (i = 0; i < (idx - 1); i++) {
               actual = actual.getSiguiente();
            }

            // la celda siguiente de la nueva será la
            // que antes seguía de la actual
            nvacelda = new BiCelda<E>(nvoelem, actual.getSiguiente(),
                                      actual);

            // la anterior de la siguiente es la nueva
            // celda
            actual.getSiguiente().setAnterior(nvacelda);

            // igual que la siguiente de la actual
            actual.setSiguiente(nvacelda);
         } else { // if (idx > nelems/2) i.e. segunda mitad

            // recorremos la lista hasta llegar a la
            // celda que lo sucederá (actual)
            actual = ultimo;

            for (i = nelems - 1; i > idx; i--) {
               actual = actual.getAnterior();
            }

            // la celda anterior de la nueva será la
            // que antecedía a la actual
            nvacelda = new BiCelda<E>(nvoelem, actual,
                                      actual.getAnterior());

            // la siguiente de la anterior es la nueva
            // celda
            actual.getAnterior().setSiguiente(nvacelda);

            // igual que la anterior de la actual
            actual.setAnterior(nvacelda);
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
   public void reemplaza(int idx, E elem)
      throws ErrorAccesoException {
      BiCelda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         // si es el primero de la lista
         if (idx == 0) {
            primero.setContenido(elem);
         } else if (idx == (nelems - 1)) { // si es el último
            ultimo.setContenido(elem);
         } else if (idx <= (nelems / 2)) { // primera mitad

            // recorremos la lista hasta llegar a la
            // celda objetivo
            actual = primero;

            for (i = 0; i < idx; i++) {
               actual = actual.getSiguiente();
            }

            actual.setContenido(elem);
         } else {
            actual = ultimo;

            for (i = nelems - 1; i > idx; i--) {
               actual = actual.getAnterior();
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
   public void elimina(int idx)
      throws ErrorAccesoException {
      BiCelda<E> actual; // referencia a la celda previa a la

      // que se elimina
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         if (idx == 0) { // si es la primera celda

            // y el primero coincide no coincide con el
            // ultimo
            if (primero.getSiguiente() != null) {
               primero.getSiguiente().setAnterior(null);
               primero = primero.getSiguiente();
            } else { // si coinciden sólo hay un elemento
               primero = ultimo = null;
            }
         } else if (idx == (nelems - 1)) { // si es el último
            ultimo.getAnterior().setSiguiente(null);
            ultimo = ultimo.getAnterior();
         } else if (idx <= (nelems / 2)) { // primera mitad

            // si no es la primera
            actual = primero;

            // recorremos hasta llegar a la celda
            // que queremos eliminar
            for (i = 0; i < idx; i++) {
               actual = actual.getSiguiente();
            }

            // eliminamos la celda
            actual.getSiguiente().setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(actual.getSiguiente());
         } else { // segunda mitad
            actual = ultimo;

            for (i = nelems - 1; i > idx; i--) {
               actual = actual.getAnterior();
            }

            actual.getSiguiente().setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(actual.getSiguiente());
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
   public E getElemento(int idx)
      throws ErrorAccesoException {
      BiCelda<E> actual;
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         if (idx == 0) { // si se requiere el primero

            return primero.getContenido();
         } else if (idx == (nelems - 1)) { // el último

            return ultimo.getContenido();
         } else if (idx <= (nelems / 2)) { // primera mitad

            // recorremos de adelante hacia atrás
            actual = primero;

            for (i = 0; i < idx; i++) {
               actual = actual.getSiguiente();
            }

            return actual.getContenido();
         } else { // segunda mitad

            // recorremos de atrás hacia adelante
            actual = ultimo;

            for (i = nelems - 1; i > idx; i--) {
               actual = actual.getAnterior();
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
   public int longitud() {
      return nelems;
   }

   /**
    * Convierte la lista en lista vacía.
    */
   public void limpia() {
      primero = ultimo = null;
      nelems = 0;
   }

   /**
    * Construye un iterador para la lista. Una vez obtenido
    * el iterador, cualquier modificación a la lista que
    * involucre eliminar o añadir elementos harán
    * inconsistente el comportamiento del iterador.
    * @return una nueva instancia de
    * <code>IteradorLista</code>, posicionado en el
    * primer elemento de la lista.
    * @see IteradorLista
    */
   public IteradorLista<E> getIterador() {
      return new IteradorListaDblLigada();
   }

   /**
    *
    *  Clase para implementar celdas doblemente ligadas.
    *
    * @see ListaDblLigada
    * @author José Galaviz (galaviz@ciencias.unam.mx)
    * @version 3.0 <br>
    * marzo de 2015
    *
    */
   private class BiCelda<E> {

      /**
       * Elemento de la celda.
       */
      private E elem;

      /**
       * Liga a la siguiente celda.
       */
      private BiCelda<E> sig;

      /**
       * Liga celda previa
       */
      private BiCelda<E> ant;

      /**
       * Construye una nueva instancia de <code>BiCelda</code> a
       * partir del elemento a colocar dentro de ella.
       * @param elemento Objeto que se
       * introducirá en la nueva celda.
       */
      public BiCelda(E elemento) {
         elem = elemento;
         ant = sig = null;
      }

      /**
       * Construye una nueva instancia de <code>BiCelda</code> a
       * partir del elemento a colocar dentro de ella y de la
       * liga a la siguiente celda.
       * @param elemento Objeto que se
       * introducirá en la nueva celda.
       * @param siguiente Siguiente
       * celda.
       * @param anterior celda anterior.
       */
      public BiCelda(E elemento, BiCelda<E> siguiente, BiCelda<E> anterior) {
         elem = elemento;
         sig = siguiente;
         ant = anterior;
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
      public void setSiguiente(BiCelda<E> siguiente) {
         sig = siguiente;
      }

      /**
       * Establece la liga a la celda anterior.
       * @param anterior celda previa.
       */
      public void setAnterior(BiCelda<E> anterior) {
         ant = anterior;
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
      public BiCelda<E> getSiguiente() {
         return sig;
      }

      /**
       * Regresa la referencia a la celda previa.
       * @return La liga a la celda anterior.
       */
      public BiCelda<E> getAnterior() {
         return ant;
      }
   } // Fin de BiCelda.java

   /**
    * Clase que implementa un iterador de doble sentido para
    * listas doblemente ligadas.
    *
    * @since 2.0
    * @see Iterador
    * @see IteradorBidireccional
    * @see ListaDblLigada
    * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
    * @version 2.0 <br>
    * enero 2009
    */
   private class IteradorListaDblLigada implements IteradorBidireccional<E>,
      IteradorLista<E> {

      private static final String ERR_LV = "Lista vacía";
      private static final String ERR_NA = "Imposible avanzar";
      private static final String ERR_NR = "Imposible retroceder";
      private int posicion;
      private BiCelda<E> actual;

      /**
       * Construye un iterador para lista doblemente
       * ligada. Este método
       * es invocado por el método <code>getIterador()</code>
       * de la clase <code>ListaDblLigada</code>. Una vez obtenido
       * un iterador de una instancia particular de lista, este
       * deja de ser consistente si la instancia de lista que
       * lo creó es modificada mediente sus métodos propios.
       * @see ListaDblLigada#getIterador
       * @param listalig es la lista doblemente ligada a recorrer
       * @param inicial es la referencia a la primera celda de
       * la lista.
       * @param fin es la referencia a la última celda.
       */
      public IteradorListaDblLigada() {
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
       * Posiciona el iterador en el último elemento de la
       * lista.
       * @throws ErrorAccesoException si la lista es vacía o nula.
       */
      @Override
         public void setFinal()
         throws ErrorAccesoException {
         int i;

         if (primero == null) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                           ".setFinal: " + ERR_LV);
         }

         posicion = longitud() - 1;
         actual = ultimo;
      }

      /**
       * Determina si hay un siguiente elemento en la lista o
       * no.
       * @return <code>true</code> si hay un siguiente
       * elemento, <code>false</code>  en otro caso.
       */
      @Override
         public boolean hasSiguiente() {
         return (posicion < (longitud() - 1));
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
       * Determina si hay un elemento anterior en la lista o
       * no.
       * @return <code>true</code> si hay un elemento previo,
       * <code>false</code>  en otro caso.
       */
      @Override
         public boolean hasAnterior() {
         return (posicion > 0);
      }

      /**
       * Determina si hay un elemento anterior en la lista o
       * no.
       * @return <code>true</code> si hay un elemento previo,
       * <code>false</code>  en otro caso.
       */
      @Override
         public boolean hayAnterior() {
         return hasAnterior();
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
       * Retrocede el iterador al elemento previo disponible en
       * la lista.
       * @throws ErrorAccesoException si ya no hay elemento previo.
       */
      @Override
         public void retrocede()
         throws ErrorAccesoException {
         if (posicion == 0) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                           ".retrocede: " + ERR_NR);
         } else {
            actual = actual.getAnterior();
            posicion--;
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
   } // Fin de IteradorListaDblLigada.java
} // Fin de ListaDblLigada.java
