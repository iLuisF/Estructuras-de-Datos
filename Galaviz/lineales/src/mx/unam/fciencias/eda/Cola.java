/* ----------------------------------------------------------------------
 * Cola.java
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
 * Interfaz para colas.
 * @author José Galaviz  (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo de 2015
 */
public interface Cola<E> {

   /**
    * Determina si una cola es vacía o no.
    * @return <code>true</code> Si la cola es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean isVacia();

   /**
    * Determina si una cola es vacía o no.
    * @return <code>true</code> Si la cola es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean esVacia();

   /**
    * Determina el número de elementos elmacenados en la
    * cola.
    * @return número de celdas en la cola
    */
   public int longitud();

   /**
    * Regresa el valor almacenado en el frente de la
    * cola.
    * @return El valor del objeto en el frente de la cola.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * siguiente elemento de una cola vacía
    */
   public E siguiente()
      throws ErrorAccesoException;

   /**
    * Regresa el valor almacenado en el frente de la
    * cola.
    * @return El valor del objeto en el frente de la cola.
    * @throws ErrorAccesoException en caso de pretender obtener el
    * siguiente elemento de una cola vacía
    */
   public E frente()
      throws ErrorAccesoException;

   /**
    * Introduce un nuevo elemento en la cola.
    * @param nvoatras Nuevo elemento en
    * la cola.
    */
   public void enqueue(E nvoatras);

   /**
    * Introduce un nuevo elemento en la cola.
    * @param nvoatras Nuevo elemento en
    * la cola.
    */
   public void mete(E nvoatras);

   /**
    * Elimina el elemento que fue introducido más
    * tempranamente a la cola.
    * @return una referencia al elemento al frente de la
    * cola.
    * @throws ErrorAccesoException en caso de pretender sacar de
    * una cola vacía
    */
   public E dequeue()
      throws ErrorAccesoException;

   /**
    * Elimina el elemento que fue introducido más
    * tempranamente a la cola.
    * @return una referencia al elemento al frente de la
    * cola.
    * @throws ErrorAccesoException en caso de pretender sacar de
    * una cola vacía
    */
   public E saca()
      throws ErrorAccesoException;
} // Fin de Cola.java
