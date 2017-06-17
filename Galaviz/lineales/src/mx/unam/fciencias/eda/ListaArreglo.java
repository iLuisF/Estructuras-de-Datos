/* -------------------------------------------------------------------
 * ListaArreglo.java
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
 * 59 Elememple Place - Suite 330, Boston, MA  02111-1307, USA.
 * -------------------------------------------------------------------
 */
package mx.unam.fciencias.eda;

import mx.unam.fciencias.eda.ErrorAccesoException;
import mx.unam.fciencias.eda.Lista;
import mx.unam.fciencias.eda.ListaAbstracta;
/**
 * Implementación de la clase lista basada en un arreglo.
 * @see Lista
 * @see ListaAbstracta
 * @since 1.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 5.0 <br>
 * marzo 2015
 */
public class ListaArreglo<E> extends ListaAbstracta<E> implements Lista<E> {

   /**
    * Mensaje de error
    */
   private static final String ERRMSG = "Índice inválido";

   /**
    * Tamaño de paso por omisión.
    */
   private static final int DEFAULTPASO = 10;

   /**
    * La lista propiamente dicha.
    */
   private E[] elementos;

   /**
    * Número de elementos en la lista. Máximo índice del
    * arreglo ocupado.
    */
   private int nelems;

   /**
    * Tamaño del arreglo
    */
   private int tamarr;

   /**
    * Tamaño del espacio añadido cada vez que se requiere.
    */
   private int paso;

   /**
    * Construye una lista vacía. El tamaño inicial y el
    * tamaño de bloque añadido toman valores por omisión.
    */
   @SuppressWarnings("unchecked")
      public ListaArreglo() {
      elementos = (E[]) new Object[DEFAULTPASO];
      nelems = 0;
      tamarr = DEFAULTPASO;
      paso = DEFAULTPASO;

      for (int i = 0; i < tamarr; elementos[i++] = null) {
         ;
      }
   }

   /**
    * Construye una lista vacía con un cierto tamaño
    * definido por el usuario. El tamaño de bloque añadido
    * adquiere un valor por omisión.
    * @param taminicial es un enetro no negativo con el
    * tamaño inicial de la lista.
    */
   @SuppressWarnings("unchecked")
   public ListaArreglo(int taminicial) {
      elementos = (E[]) new Object[taminicial];
      nelems = 0;
      tamarr = taminicial;
      paso = DEFAULTPASO;

      for (int i = 0; i < tamarr; elementos[i++] = null) {
         ;
      }
   }

   /**
    * Construye una lista vacía con un cierto tamaño incial
    * y tamaño de bloque añadido definidos por el usuario.
    * @param taminicial es un enetro no negativo con el
    * tamaño inicial de la lista.
    * @param tambloque es el tamaño de bloque que se añade
    * cuando se agota el arreglo
    */
   @SuppressWarnings("unchecked")
   public ListaArreglo(int taminicial, int tambloque) {
      elementos = (E[]) new Object[taminicial];
      nelems = 0;
      tamarr = taminicial;
      paso = tambloque;

      for (int i = 0; i < tamarr; elementos[i++] = null) {
         ;
      }
   }

   /**
    * Redefine el arreglo para hacerlo más grande. Añade un
    * bloque de tamaño <code>pas</code>.
    */
   @SuppressWarnings("unchecked")
   private void redefineArreglo() {
      int i;
      E[] nuevo = (E[]) new Object[nelems + paso];

      for (i = 0; i < nelems; i++) {
         nuevo[i] = elementos[i];
      }

      for (i = nelems; i < nuevo.length; i++) {
         nuevo[i] = null;
      }

      tamarr = nuevo.length;
      elementos = nuevo;
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
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx <= nelems)) {
         if (nelems == tamarr) {
            redefineArreglo();
         }

         for (i = nelems - 1; i >= idx; i--) {
            elementos[i + 1] = elementos[i];
         }

         elementos[idx] = nvoelem;
         nelems++;
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
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         elementos[idx] = elem;
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
      int i;

      // si el índice es válido
      if ((idx >= 0) && (idx < nelems)) {
         for (i = idx; i < (nelems - 1); i++) {
            elementos[i] = elementos[i + 1];
         }

         elementos[nelems - 1] = null;
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
      int i;

      if ((idx >= 0) && (idx < nelems)) {
         return elementos[idx];
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
   @SuppressWarnings("unchecked")
   public void limpia() {
		elementos = (E[]) new Object[tamarr];
      nelems = 0;

      for (int i = 0; i < tamarr; elementos[i++] = null) {
         ;
      }
   }

   /**
    * Construye un iterador para la lista.
    * @return una nueva instancia de
    * <code>IteradorLista</code>, posicionado en el
    * primer elemento de la lista.
    * @see IteradorLista
    */
   @Override
   public IteradorLista<E> getIterador() {
      return new IteradorListaArreglo();
   }

   /**
    * Clase que implementa iteradores para listas ligadas
    * simples. Sólo es posible recorrer la lista en un
    * sentido.
    *
    * @since 2.0
    * @see ListaArreglo
    * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
    * @version 2.0
    * marzo 2015
    */
   private class IteradorListaArreglo implements IteradorLista<E> {
      private static final String ERR_LV = "Lista vacía";
      private static final String ERR_NA = "Imposible avanzar";
      private int posicion;

      /**
       * Construye un iterador para lista ligada. Este método
       * es invocado por el método <code>getIterador()</code>
       * de la clase <code>ListaArreglo</code>. Una vez obtenido
       * un iterador de una instancia particular de lista, este
       * deja de ser consistente si la instancia de lista que
       * lo creó es modificada mediente sus métodos propios.
       * @see getIterador
       */
      public IteradorListaArreglo() {
         posicion = 0;
      }

      /**
       * Posiciona el iterador en el primer elemento de la
       * lista.
       * @throws ErrorAccesoException si la lista es vacía o nula.
       */
      @Override
      public void setInicio()
         throws ErrorAccesoException {
         if ((elementos == null) || (elementos.length == 0)) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                           ".setInicio: " + ERR_LV);
         } else {
            posicion = 0;
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
         if (!hasSiguiente()) {
            throw new ErrorAccesoException(this.getClass().getName() +
                                           ".avanza: " + ERR_NA);
         } else {
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
         return elementos[posicion];
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
         elementos[posicion] = elem;
      }
   } // Fin de IteradorListaArreglo.java
}  // fin de ListaArreglo.java
