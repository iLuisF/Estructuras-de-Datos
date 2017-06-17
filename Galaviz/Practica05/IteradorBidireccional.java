/* ----------------------------------------------------------------------
 * IteradorBidireccional.java
 * version 2.0
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
 * Interfaz que define las firmas de los métodos asociados
 * con iteradores en estructuras en las que es posible
 * retroceder .
 *
 * @since 2.0
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 2.0 <br>
 * enero 2009
 */
public interface IteradorBidireccional<T> extends Iterador<T> {

   /**
    * Posiciona el iterador en el último elemento de la
    * estructura.
    * @throws ExcepcionAccesoIncorrecto si la estructura es
    * vacía o nula.
    */
   public void setFinal()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Determina si hay un elemento anterior en la estructura o
    * no.
    * @return <code>true</code> si hay un elemento previo,
    * <code>false</code>  en otro caso.
    */
   public boolean hayAnterior();

   /**
    * Retrocede el iterador al elemento previo disponible en
    * la estructura.
    * @throws ExcepcionAccesoIncorrecto si ya no hay
    * elemento previo.
    */
   public void retrocede()
      throws ExcepcionAccesoIncorrecto;
} // Fin de IteradorBidireccional.java
