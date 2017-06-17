/* ----------------------------------------------------------------------
 * PilaLst.java
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
import mx.unam.fciencias.eda.ListaLigada;
import mx.unam.fciencias.eda.ListaArreglo;

/**
 * Clase que implementa pilas usando listas.
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo 2015
 */
public class PilaLst<E> implements Pila<E> {

   /**
    * Pila construida con arreglo
    */
   public static final boolean PILA_ARREGLO = false;

   /**
    * Pila construida con lista ligada
    */
   public static final boolean PILA_LIGADA = true;

   /**
    * Mecanismo de construccion por omision: lista ligada.
    */
   public static final boolean PILA_OMISION = PILA_LIGADA;

   /**
    * Los elementos en la pila se almacenan en una lista
    * ligada.
    */
   private Lista<E> lista;

   /**
    * Construye una pila vacía. El mecanismo de
    * representación es, por omisión, una estructura
    * ligada.
    */
   public PilaLst() {
      lista = new ListaLigada<E>();
   }

   /**
    * Construye una pila vacía especificando su mecanismo
    * de representación.
    * @param ligada indica si la instancia de pila debe ser
    * construida usando una representación ligada o bien un
    * arreglo de tamaño adaptable.
    * <UL>
    * <LI> <code>true</code>  o
    * <code>PilaLst.PILA_LIGADA</code> indica representación ligada.
    * <LI> <code>false</code>  o
    * <code>PilaLst.PILA_ARREGLO</code> indica representación
    * con arreglo.
    * </UL>
    */
   public PilaLst(boolean ligada) {
      if (ligada) {
         lista = new ListaLigada<E>();
      } else {
         lista = new ListaArreglo<E>();
      }
   }

   /**
    * Construye una pila vacía. El argumento indica el
    * tamaño inicial mínimo que debe tener la pila
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la pila.
    */
   public PilaLst(int tam) {
      lista = new ListaArreglo<E>(tam);
   }

   /**
    * Construye una pila vacía. El 2o argumento indica el
    * tamaño inicial mínimo que debe tener la pila,
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la pila.
    * @param bloque es el número de celdas que se añadena a
    * la pila cuando ya no caben más celdas.
    */
   public PilaLst(int tam, int bloque) {
      lista = new ListaArreglo<E>(tam, bloque);
   }

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */
   @Override
   public boolean isVacia() {
      return lista.isVacia();
   }

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */
   @Override
   public boolean esVacia() {
      return isVacia();
   }

   /**
    * Determina el número de elementos elmacenados en la
    * pila.
    * @return número de celdas en la pila
    */
   @Override
   public int longitud() {
      return lista.longitud();
   }

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * tope de la pila vacía.
    */
   @Override
   public E tope()
      throws ErrorAccesoException {
      if (!lista.isVacia()) {
         return lista.getPrimero();
      } else {
         throw new ErrorAccesoException("PilaLst.tope");
      }
   }

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * tope de la pila vacía.
    */
   @Override
   public E peek()
      throws ErrorAccesoException {
      return tope();
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   @Override
   public void apila(E nvotope) {
      lista.insertaPrimero(nvotope);
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   @Override
   public void push(E nvotope) {
      apila(nvotope);
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   @Override
   public void mete(E nvotope) {
      apila(nvotope);
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   @Override
   public E desapila()
      throws ErrorAccesoException {
      E resul;

      if (!lista.isVacia()) {
         resul = lista.getPrimero();
         lista.eliminaPrimero();

         return resul;
      } else {
         throw new ErrorAccesoException("PilaLst.desapila");
      }
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   @Override
   public E pop()
      throws ErrorAccesoException {
      return desapila();
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   @Override
   public E saca()
      throws ErrorAccesoException {
      return desapila();
   }
} // Fin de PilaLst.java
