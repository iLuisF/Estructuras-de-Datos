/* ----------------------------------------------------------------------
 * Lista.java
 * versión 3.0
 * Copyright (C) 2009  José Galaviz Casas,
 * Facultad de Ciencias,
 * Universidad Nacional Autónoma de México, México.
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
 * Interfaz que define las firmas de los métodos básicos de
 * una lista.
 *
 * @since 1.0
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 3.0 <br>
 * enero 2009
 */
public interface Lista<T> {

   /**
    * Establece el elemento al frente de la lista.
    * Este método es equivalente a
    * <code>instancialista.inserta(0,elem)</code>.
    * @param nvoprimero es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice cero.
    */
   public void alPrincipio(T nvoprimero);

   /**
    * Establece el elemento al final de la lista
    * Este método es equivalente a
    * <code>instancialista.inserta(instancialista.longitud(),elem)</code>.
    * @param nvoultimo es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice
    * <code>longitud()</code>.
    */
   public void alFinal(T nvoultimo);

   /**
    * Entrega una referencia al primer elemento de la
    * lista.
    * @return una referencia al primer objeto guardado en la
    * lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   public T primero()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Entrega una referencia al ultimo elemento de la
    * lista.
    * @return una referencia al ultimo objeto guardado en la
    * lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   public T ultimo()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Elimina el primer elemento de la lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   public void eliminaPrimero()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Elimina el último elemento de la lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   public void eliminaUltimo()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Inserta un elemento en la lista.
    * @param idx Es el índice del lugar donde
    * el elemento será insertado. <code>idx</code> debe
    * estar en el conjunto {0, ..., <code>longitud()</code>}, de otro modo
    * se levanta una excepción <code>ExcepcionAccesoIncorrecto</code>
    * (índice fuera de rango)
    * @param nvoelem El nuevo elemento a
    * insertar.
    * @see #longitud
    * @throws ExcepcionAccesoIncorrecto en caso de pretender
    * insertar más allá del final de la lista
    * o antes del principio.
    */
   public void inserta(int idx, T nvoelem)
      throws ExcepcionAccesoIncorrecto;

   /**
    * Reemplaza un elemento de la lista.
    * @param idx es el índice del lugar a reemplazar. Por
    * supuesto este índice debe estar en el conjunto {0,
    * ..., longitud()-1}.
    * @param elem es el elemento que reemplazará al que se
    * halla en la posición especificada.
    * @see #longitud
    * @throws ExcepcionAccesoIncorrecto en caso de que el
    * índice sea incorrecto.
    */
   public void reemplaza(int idx, T elem)
      throws ExcepcionAccesoIncorrecto;

   /**
    * Elimina el elemento almacenado en un lugar específico
    * de la lista.
    * @param idx índice de la celda a
    * eliminar. <code>idx</code> debeestar en el conjunto
    * {0, ..., longitud()-1}, de otro modo se levanta una
    * excepción <code>ExcepcionAccesoIncorrecto</code> (índice fuera de
    * rango)
    * @see #longitud
    * @throws ExcepcionAccesoIncorrecto en caso de pretender
    * eliminar más allá del final de la lista
    * o antes del principio.
    */
   public void elimina(int idx)
      throws ExcepcionAccesoIncorrecto;

   /**
    * Permite conocer el elemento guardado en un lugar
    * específico de la lista.
    * @param idx índice del lugar
    * requerido. <code>idx</code> debe estar en el conjunto
    * {0, ..., longitud()-1}
    * @return el elemento guardado en la celda de índice
    * <code>idx</code>.
    * @see #longitud
    * @throws ExcepcionAccesoIncorrecto en caso de pretender
    * obtener el elemento de más allá del
    * final de la lista o antes del principio.
    */
   public T elemento(int idx)
      throws ExcepcionAccesoIncorrecto;

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
    * @see IteradorListaArreglo
    * @see IteradorListaLigada
    */
   public IteradorLista<T> getIterador();
   
    /**
    * Entrega una referencia al primer elemento de la
    * lista.
    * @return una referencia al primer objeto guardado en la
    * lista.
    * @throws ExcepcionAccesoIncorrecto si la lista es
    * vacía.
    */
   public T getPrimero() throws ExcepcionAccesoIncorrecto;
   
   /**
    * Establece el elemento al frente de la lista.
    * Este método es equivalente a
    * <code>instancialista.inserta(0,elem)</code>.
    * @param nvoprimero es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice cero.
    */
   public void insertaPrimero(T nvoprimero);
   
   /**
    * Establece el elemento al final de la lista
    * Este método es equivalente a
    * <code>instancialista.inserta(instancialista.longitud(),elem)</code>.
    * @param nvoultimo es el objeto cuya referencia será
    * incluida en la lista en el lugar de índice
    * <code>longitud()</code>.
    */
   public void insertaUltimo(T nvoultimo);
} // Fin de Lista.java
