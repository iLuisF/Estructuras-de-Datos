/* -------------------------------------------------------------------
  * ErrorAccesoException.java
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
 /**
  * Excepción lanzada por las estructuras de datos cuando se violan las
  * restricciones de acceso.
  *
  * @since 1.0
  * @author José Galaviz (galaviz@ciencias.unam.mx)
  * @version 3.0 <br>
  * marzo 2015
  */

 public class ErrorAccesoException extends Exception {

    private static final long serialVersionUID = -3678470304459794916L;

   /**
    * Construye una instancia de la excepcion.
    * @param s es un mensaje de error dado por el objeto que
    * dispara la excepción.
    */
   public ErrorAccesoException(String s) {
      super(s + "\n");
      super.printStackTrace();
   }
} // fin de ErrorAccesoException.java
