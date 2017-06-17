/* ----------------------------------------------------------------------
 * ColaLst.java
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

import mx.unam.fciencias.eda.ErrorAccesoException;
import mx.unam.fciencias.eda.Lista;
import mx.unam.fciencias.eda.ListaArreglo;
import mx.unam.fciencias.eda.ListaLigada;

/**
 * Clase que implementa colas usando listas.
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo de 2015
 */
public class ColaLst<E> implements Cola<E> {

   /**
    * Cola construida con arreglo
    */
   public static final boolean COLA_ARREGLO = false;

   /**
    * Cola construida con lista ligada
    */
   public static final boolean COLA_LIGADA = true;

   /**
    * Mecanismo de construccion por omision: lista ligada.
    */
   public static final boolean COLA_OMISION = COLA_LIGADA;

   /**
    * Los elementos en la cola se almacenan en una lista
    * ligada.
    */
   private Lista<E> lista;

   /**
    * Construye una cola vacía. El mecanismo de
    * representación es, por omisión, una estructura
    * ligada.
    */
   public ColaLst() {
      lista = new ListaDblLigada<E>();
   }

   /**
    * Construye una cola vacía especificando su mecanismo
    * de representación.
    * @param ligada indica si la instancia de cola debe ser
    * construida usando una representación ligada o bien un
    * arreglo de tamaño adaptable.
    * <UL>
    * <LI> <code>true</code>  o
    * <code>ColaLst.COLA_LIGADA</code> indica representación ligada.
    * <LI> <code>false</code>  o
    * <code>ColaLst.COLA_ARREGLO</code> indica representación
    * con arreglo.
    * </UL>
    */
   public ColaLst(boolean ligada) {
      if (ligada) {
         lista = new ListaDblLigada<E>();
      } else {
         lista = new ListaArreglo<E>();
      }
   }

   /**
    * Construye una cola vacía. El argumento indica el
    * tamaño inicial mínimo que debe tener la cola
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la cola.
    */
   public ColaLst(int tam) {
      lista = new ListaArreglo<E>(tam);
   }

   /**
    * Construye una cola vacía. El 2o argumento indica el
    * tamaño inicial mínimo que debe tener la cola,
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la cola.
    * @param bloque es el número de celdas que se añadena a
    * la cola cuando ya no caben más celdas.
    */
   public ColaLst(int tam, int bloque) {
      lista = new ListaArreglo<E>(tam, bloque);
   }

   /**
    * Determina si una cola es vacía o no.
    * @return <code>true</code> Si la cola es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean isVacia() {
      return lista.isVacia();
   }

   /**
    * Determina si una cola es vacía o no.
    * @return <code>true</code> Si la cola es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean esVacia() {
      return isVacia();
   }

   /**
    * Determina el número de elementos elmacenados en la
    * cola.
    * @return número de celdas en la cola
    */
   public int longitud() {
      return lista.longitud();
   }

   /**
    * Regresa el valor almacenado en el frente de la
    * cola.
    * @return El valor del objeto en el frente de la cola.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * siguiente elemento de una cola vacía
    */
   public E siguiente()
      throws ErrorAccesoException {
      if (!lista.isVacia()) {
         return lista.getPrimero();
      } else {
         throw new ErrorAccesoException("ColaLst.siguiente/frente");
      }
   }

   /**
    * Regresa el valor almacenado en el frente de la
    * cola.
    * @return El valor del objeto en el frente de la cola.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * siguiente elemento de una cola vacía
    */
   public E frente()
      throws ErrorAccesoException {
      return siguiente();
   }

   /**
    * Introduce un nuevo elemento en la cola.
    * @param nvoatras Nuevo elemento en
    * la cola.
    */
   public void enqueue(E nvoatras) {
      lista.insertaUltimo(nvoatras);
   }

   /**
    * Introduce un nuevo elemento en la cola.
    * @param nvoatras Nuevo elemento en
    * la cola.
    */
   public void mete(E nvoatras) {
      enqueue(nvoatras);
   }

   /**
    * Elimina el elemento que fue introducido más
    * tempranamente a la cola.
    * @return una referencia al elemento al frente de la
    * cola.
    * @throws ErrorAccesoException en caso de pretender sacar de
    * una cola vacía
    */
   public E dequeue()
      throws ErrorAccesoException {
      E frentecola;

      if (!lista.isVacia()) {
         frentecola = lista.getPrimero();
         lista.eliminaPrimero();

         return frentecola;
      } else {
         throw new ErrorAccesoException("ColaLst.dequeue/saca");
      }
   }

   /**
    * Elimina el elemento que fue introducido más
    * tempranamente a la cola.
    * @return una referencia al elemento al frente de la
    * cola.
    * @throws ErrorAccesoException en caso de pretender sacar de
    * una cola vacía
    */
   public E saca()
      throws ErrorAccesoException {
      return dequeue();
   }
} // Fin de ColaLst.java
