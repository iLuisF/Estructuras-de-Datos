/* ----------------------------------------------------------------------
 * ListaAbstracta.java
 * versión 4.0
 * Copyright (C) 2009  José Galaviz Casas,
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
package mx.unam.fciencias.EstDat;

/**
 * Clase que implementa parcialmente la interfaz
 * <code>Lista</code>. Implementa los métodos comunes a
 * todas las listas.
 * @param <T> Tipo de elemento.
 * @see Lista
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 4.0 <br>
 * enero 2009
 */
public abstract class ListaAbstracta<T> implements Lista<T> {

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
   @Override
   public void alPrincipio(T nvoprimero) {
      try {
         inserta(0, nvoprimero);
      } catch (ExcepcionAccesoIncorrecto eai) {// nunca ocurre realmente
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
   @Override
   public void alFinal(T nvoultimo) {
      try {
         inserta(longitud(), nvoultimo);
      } catch (ExcepcionAccesoIncorrecto eai) {// nunca ocurre realmente
      }
   }

   /**
    * Entrega una referencia al primer elemento de la
    * lista.
    * @return una referencia al primer objeto guardado en la
    * lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   @Override
   public T primero()
      throws ExcepcionAccesoIncorrecto {
      if (!esVacia()) {
         return elemento(0);
      } else {
         throw new ExcepcionAccesoIncorrecto(
               this.getClass().getName() + ".primero: " + ERRMSG);
      }
   }

   /**
    * Entrega una referencia al ultimo elemento de la
    * lista.
    * @return una referencia al ultimo objeto guardado en la
    * lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   @Override
   public T ultimo()
      throws ExcepcionAccesoIncorrecto {
      if (!esVacia()) {
         return elemento(longitud() - 1);
      } else {
         throw new ExcepcionAccesoIncorrecto(
               this.getClass().getName() + ".ultimo: " + ERRMSG);
      }
   }

   /**
    * Elimina el primer elemento de la lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   @Override
   public void eliminaPrimero()
      throws ExcepcionAccesoIncorrecto {
      if (!esVacia()) {
         elimina(0);
      } else {
         throw new ExcepcionAccesoIncorrecto(
               this.getClass().getName() + ".eliminaPrimero: "
               + ERRMSG);
      }
   }

   /**
    * Elimina el último elemento de la lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   @Override
   public void eliminaUltimo()
      throws ExcepcionAccesoIncorrecto {
      if (!esVacia()) {
         elimina(longitud() - 1);
      } else {
         throw new ExcepcionAccesoIncorrecto(
               this.getClass().getName() + ".eliminaPrimero: "
               + ERRMSG);
      }
   }

   /**
    * Determina si la lista es vacía o no.
    * @return <code>true</code> Si la lista es vacía,
    * <code>false</code> en otro caso.
    */
   @Override
   public boolean esVacia() {
      return (longitud() == 0);
   }
} // Fin de ListaAbstracta.java
