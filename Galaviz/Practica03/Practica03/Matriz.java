/* ----------------------------------------------------------------------
 * Matriz.java
 * version 1.0
 * Copyright (C) 2004  José Galaviz Casas,
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
/* package mx.unam.fciencias.eda; */

/**
 * Se definen las firmas de los
 * métodos asociados con la manipulación de matrices
 * numéricas. Se suponen entradas reales en las matrices.
 *
 * @since 2.0
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 1.0<br>
 * octubre 2003
 */
public interface Matriz {

   /**
    * Crea una matriz de dimensiones y tipo dados.
    * @param ren número de renglones en la matriz.
    * @param col número de columnas en la matriz.
    * @param valdefault valor en el que se establecen las
    * entradas de la matriz no establecidas explícitamente
    * con el método <code>setEntrada</code>.
    * @param tipo es una cadena que identifica el tipo de
    * matriz. Los valores posibles estan especificados en la
    * clase <code>ManejadorMatrizXML</code>:
    * <UL>
    * <LI><code>ManejadorMatrizXML.TRISD</code>:
    * Triangular superior con diagonal.
    * <LI><code>ManejadorMatrizXML.TRISN</code>:
    * Triangular superior sin diagonal.
    * <LI><code>ManejadorMatrizXML.TRIID</code>:
    * Triangular inferior con diagonal.
    * <LI><code>ManejadorMatrizXML.TRIIN</code>:
    * Triangular inferior sin diagonal.
    * <LI><code>ManejadorMatrizXML.SIMETRICA</code>:
    * Simétrica
    * <LI><code>ManejadorMatrizXML.REGULAR</code>:
    * Regular (cualquiera)
    * </UL>
    */
   public void creaMatriz(int ren, int col, double valdefault, String tipo);

   /**
    * Establece la entrada indicada de la matriz.
    * @param ren índice del renglón de la entrada a
    * establecer.
    * @param col índice de la columna de la entrada a
    * establecer.
    * @param valor valor a colocar en la entrada.
    * @throws IndexOutOfBoundsException si los valores de
    * índice de renglón o columna son erróneos de acuerdo
    * con las dimensiones establecidas para la matriz y/o de
    * acuerdo con el tipo de matriz especificado (por
    * ejemplo, si el tipo es triangular inferior sin
    * diagonal entonces debe cumplirse que <br><code>ren</code>>
    * > <code>col</code>).<br> Si la matriz es simétrica se
    * tratará como triangular inferior con diagonal.
    */
   public void setEntrada(int ren, int col, double valor)
      throws IndexOutOfBoundsException;

   /**
    * Regresa la cadena con el tipo de matriz. Véase el
    * método constructor para los posibles valores.
    * @return una cadena con el tipo de matriz de acuerdo
    * con la clase <code>ManejadorMatrizXML</code>.
    */
   public String getTipo();

   /**
    * Regresa el valor por omisión, aquel que se encuentra
    * en las entradas no establecidas explícitamente.
    * @return el valor por omisión.
    */
   public double getDefault();

   /**
    * Regresa el número de renglones en la matriz.
    * @return un entero no negativo con el número de
    * renglones de la matriz.
    */
   public int getRenglones();

   /**
    * Regresa el número de columnas en la matriz.
    * @return un entero no negativo con el número de
    * columnas de la matriz.
    */
   public int getColumnas();

   /**
    * Regresa el valor almacenado en una entrada de la
    * matriz.
    * @param ren es el índice del renglón de la entrada
    * requerida.
    * @param col es el índice de la columna de la entrada
    * requerida.
    * @return el valor almacenado en la entrada
    * especificada.
    * @throws IndexOutOfBoundsException si los índices
    * especificados son incorrectos. En este caso sólo se
    * considera que no rebasen las dimansiones especificadas
    * para la matriz.
    */
   public double getEntrada(int ren, int col)
      throws IndexOutOfBoundsException;

   /**
    * Despliega la matriz en la salida estándar.
    * @throws IndexOutOfBoundsException
    */
   public void despliega()
      throws IndexOutOfBoundsException;

   /**
   *Método que suma dos matrices
   *@param mat es la matriz a sumar con este objeto
   *@return La matriz resultado de sumar este objeto con la
   *matriz argumento
   */
   public Matriz suma(Matriz mat);

   /**
   *Método que multiplica dos matrices
   *@param mat es la matriz a multiplicar con este objeto
   *@return La matriz resultado de multiplicar este objeto con la
   *matriz argumento
   */
   public Matriz producto(Matriz mat);

   /**
   *Método que multiplica una matriz por un escalar
   *@param escalar es el escalar a multiplicar con este objeto
   *@return La matriz resultado de multiplicar este objeto con 
   *el escalar argumento
   */
   public Matriz productoEscalar(double escalar);

   /**
   *Método que calcula el determinante de una matriz
   *@return El determinante de esta matriz.
   */
   public double determinante();

   /**
   *Método que traspone esta matriz
   *@return La matriz resultado de trasponer esta matriz
   */
   public Matriz trasponer();

} // Fin de Matriz.java
