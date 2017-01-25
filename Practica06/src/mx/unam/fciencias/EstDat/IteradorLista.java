/* ----------------------------------------------------------------------
 * IteradorLista.java
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
 * con iteradores de listas.
 *
 * @since  2.0
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 2.0 <br>
 * enero 2009
 */
public interface IteradorLista<T> extends Iterador<T> {

   /**
    * Reemplaza el elemento en la posición actual con el
    * dado como argumento.
    * @param elem es el elemento que substituye el contenido
    * en la posición actual.
    */
   public void reemplaza(T elem);
} // Fin de IteradorLista.java
