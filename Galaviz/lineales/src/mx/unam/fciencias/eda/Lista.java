/* -------------------------------------------------------------------
 * Lista.java
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
 * -------------------------------------------------------------------
 */
package mx.unam.fciencias.eda;

import mx.unam.fciencias.eda.ErrorAccesoException;
/**
 * Interfaz para definir los servicios de una lista no acotada de acceso
 * irrestricto a cualquier elemento en ella.
 *
 * @since 1.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0 <br>
 * marzo 2015
 */

public interface Lista<E> {

   /**
    * Establece el elemento al frente de la lista.
    * Este método es equivalente a
    * <code>instancialista.inserta(0,elem)</code>.
    * @param nvoprimero es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice cero.
    */
   public void insertaPrimero(E nvoprimero);

   /**
    * Establece el elemento al final de la lista
    * Este método es equivalente a
    * <code>instancialista.inserta(instancialista.longitud(),elem)</code>.
    * @param nvoultimo es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice
    * <code>longitud()</code>.
    */
   public void insertaUltimo(E nvoultimo);

   /**
    * Entrega una referencia al primer elemento de la
    * lista.
    * @return una referencia al primer objeto guardado en la
    * lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public E getPrimero()
      throws ErrorAccesoException;

   /**
    * Entrega una referencia al ultimo elemento de la
    * lista.
    * @return una referencia al ultimo objeto guardado en la
    * lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public E getUltimo()
      throws ErrorAccesoException;

   /**
    * Elimina el primer elemento de la lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public void eliminaPrimero()
      throws ErrorAccesoException;

   /**
    * Elimina el último elemento de la lista.
    * @throws ErrorAccesoException si la lista es
    * vacía.
    */
   public void eliminaUltimo()
      throws ErrorAccesoException;

   /**
    * Inserta un elemento en la lista.
    * @param idx Es el índice del lugar donde
    * el elemento será insertado. <code>idx</code> debe
    * estar en el conjunto {0, ..., <code>longitud()</code>}, de otro modo
    * se levanta una excepción <code>ErrorAccesoException</code>
    * (índice fuera de rango)
    * @param nvoelem El nuevo elemento a
    * insertar.
    * @see #longitud
    * @throws ErrorAccesoException en caso de pretender
    * insertar más allá del final de la lista
    * o antes del principio.
    */
   public void inserta(int idx, E nvoelem)
      throws ErrorAccesoException;

   /**
    * Reemplaza un elemento de la lista.
    * @param idx es el índice del lugar a reemplazar. Por
    * supuesto este índice debe estar en el conjunto {0,
    * ..., longitud()-1}.
    * @param elem es el elemento que reemplazará al que se
    * halla en la posición especificada.
    * @see #longitud
    * @throws ErrorAccesoException en caso de que el
    * índice sea incorrecto.
    */
   public void reemplaza(int idx, E elem)
      throws ErrorAccesoException;

   /**
    * Elimina el elemento almacenado en un lugar específico
    * de la lista.
    * @param idx índice de la celda a
    * eliminar. <code>idx</code> debeestar en el conjunto
    * {0, ..., longitud()-1}, de otro modo se levanta una
    * excepción <code>ErrorAccesoException</code> (índice fuera de
    * rango)
    * @see #longitud
    * @throws ErrorAccesoException en caso de pretender
    * eliminar más allá del final de la lista
    * o antes del principio.
    */
   public void elimina(int idx)
      throws ErrorAccesoException;

   /**
    * Permite conocer el elemento guardado en un lugar
    * específico de la lista.
    * @param idx índice del lugar
    * requerido. <code>idx</code> debe estar en el conjunto
    * {0, ..., longitud()-1}
    * @return el elemento guardado en la celda de índice
    * <code>idx</code>.
    * @see #longitud
    * @throws ErrorAccesoException en caso de pretender
    * obtener el elemento de más allá del
    * final de la lista o antes del principio.
    */
   public E getElemento(int idx)
      throws ErrorAccesoException;

   /**
    * Determina si la lista es vacía o no.
    * @return <code>true</code> Si la lista es
    * vacía, <code>false</code> en otro caso.
    */
   public boolean isVacia();

   /**
    * Determina si la lista es vacía o no.
    * @return <code>true</code> Si la lista es
    * vacía, <code>false</code> en otro caso.
    */
   public boolean esVacia();

   /**
    * Determina el número de elementos elmacenados
    * en la lista.
    * @return número de celdas en la lista
    */
   public int longitud();

   /**
    * Convierte la lista en lista vacía.
    */
   public void limpia();

   /**
    * Construye un iterador para la lista. Una vez obtenido
    * el iterador, cualquier modificación a la lista que
    * involucre eliminar o añadir elementos harán
    * inconsistente el comportamiento del iterador.
    * @return una nueva instancia de
    * <code>IteradorLista</code>, posicionado en el
    * primer elemento de la lista.
    */
     public IteradorLista<E> getIterador();
}  // fin de Lista.java
