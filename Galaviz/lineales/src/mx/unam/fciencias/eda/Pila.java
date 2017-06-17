/* ----------------------------------------------------------------------
 * Pila.java
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

/**
 * Interfaz para pilas.
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo 2015
 */
public interface Pila<E> {

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean isVacia();

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean esVacia();

   /**
    * Determina el número de elementos elmacenados en la
    * pila.
    * @return número de celdas en la pila
    */
   public int longitud();

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * tope de la pila vacía.
    */
   public E tope()
      throws ErrorAccesoException;

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * tope de la pila vacía.
    */
   public E peek()
      throws ErrorAccesoException;

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   public void apila(E nvotope);

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   public void push(E nvotope);

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */
   public void mete(E nvotope);

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   public E desapila()
      throws ErrorAccesoException;

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   public E pop()
      throws ErrorAccesoException;

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ErrorAccesoException en caso de pretender eliminar el
    * tope de la pila vacía.
    */
   public E saca()
      throws ErrorAccesoException;
} // Fin de Pila.java
