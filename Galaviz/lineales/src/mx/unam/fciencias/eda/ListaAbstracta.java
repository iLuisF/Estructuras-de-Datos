/* -------------------------------------------------------------------
 * ListaAbstracta.java
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

/**
 * Contiene los métodos comunes a todas las implementaciones de Lista.
 *
 * @see Lista
 * @since 1.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 5.0 <br>
 * marzo 2015
 */

public abstract class ListaAbstracta<E> implements Lista<E> {

   /**
    * Mensaje de error
    */
   private static final String ERRMSG = "Lista vacía";

   /**
    * Establece el elemento al frente de la lista.
    * Este método es equivalente a
    * <code>instancialista.inserta(0,elem)</code>.
    * @param nvoprimero es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice cero.
    */
   public void insertaPrimero(E nvoprimero) {
      try {
         inserta(0, nvoprimero);
      } catch (ErrorAccesoException eai) {// nunca ocurre realmente
         ;
      }
   }

   /**
    * Establece el elemento al final de la lista
    * Este método es equivalente a
    * <code>instancialista.inserta(instancialista.longitud(),elem)</code>.
    * @param nvoultimo es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice
    * <code>longitud()</code>.
    */
   public void insertaUltimo(E nvoultimo) {
      try {
         inserta(longitud(), nvoultimo);
      } catch (ErrorAccesoException eai) {// nunca ocurre realmente
         ;
      }
   }

   /**
    * Entrega una referencia al primer elemento de la
    * lista.
    * @return una referencia al primer objeto guardado en la
    * lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public E getPrimero()
      throws ErrorAccesoException {
      if (!isVacia()) {
         return getElemento(0);
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".primero: " +
                                        ERRMSG);
      }
   }

   /**
    * Entrega una referencia al ultimo elemento de la
    * lista.
    * @return una referencia al ultimo objeto guardado en la
    * lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public E getUltimo()
      throws ErrorAccesoException {
      if (!isVacia()) {
         return getElemento(longitud() - 1);
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".ultimo: " +
                                        ERRMSG);
      }
   }

   /**
    * Elimina el primer elemento de la lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public void eliminaPrimero()
      throws ErrorAccesoException {
      if (!isVacia()) {
         elimina(0);
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".eliminaPrimero: " +
                                        ERRMSG);
      }
   }

   /**
    * Elimina el último elemento de la lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public void eliminaUltimo()
      throws ErrorAccesoException {
      if (!isVacia()) {
         elimina(longitud() - 1);
      } else {
         throw new ErrorAccesoException(this.getClass().getName() +
                                        ".eliminaPrimero: " +
                                        ERRMSG);
      }
   }

   /**
    * Determina si la lista es vacía o no.
    * @return <code>true</code> Si la lista es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean isVacia() {
      return (longitud() == 0);
   }

   /**
    * Determina si la lista es vacía o no.
    * @return <code>true</code> Si la lista es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean esVacia() {
      return isVacia();
   }
} // fin de ListaAbstracta.java
