/* ----------------------------------------------------------------------
 * LectorMatrizXML.java
 * version 3.0
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

import org.xml.sax.*;

import java.io.*;

import java.util.*;

import javax.xml.parsers.*;

/**
 * Lector de matrices bidimensionales a partir de archivos
 * XML acordes a la definición de documento
 * <code>matriz.dtd</code>. Utiiza un manejador SAX para el
 * análisis sintáctico.
 *
 * @see ManejadorMatrizXML
 *
 * @since 2.0
 * @author José Galaviz &lt;jgc@fciencias.unam.mx&gt;
 * @version 1.0 <br>
 * octubre 2003
 */
public class LectorMatrizXML {
   private static final String ERRMSG_IO = "Error de E/S";
   private static final String ERRMSG_SP = "Error de SAX al parsear";
   private EntradaMat[] entradas;
   private String tipo;
   private double defaultvalue;
   private int numrenglones;
   private int numcolumnas;
   private int current;

   /**
    * Construye un lector de matrices en archivos XML.
    * @param archivoxml es la cadena que contiene el nombre del
    * archivo XML que contiene la matriz.
    */
   public LectorMatrizXML(String archivoxml) {
      try {
         ManejadorMatrizXML manejador = new ManejadorMatrizXML();
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser parser;

         factory.setValidating(true);
         factory.setNamespaceAware(true);
         parser = factory.newSAXParser();
         parser.parse(new File(archivoxml), manejador);
         entradas = manejador.getEntradas();
         tipo = manejador.getTipo();
         defaultvalue = manejador.getDefault();
         numrenglones = manejador.getNRens();
         numcolumnas = manejador.getNCols();
         current = 0;
      } catch (IOException ioe) {
         System.err.println(
               this.getClass().getName() + ": " + ERRMSG_IO);
      } catch (SAXException saxe) {
         System.err.println(
               this.getClass().getName() + ": " + ERRMSG_SP);
      } catch (Exception ge) {
         System.err.println(
               this.getClass().getName() + ": " + ERRMSG_IO);
      }
   }

   /**
    * Regresa el valor por omisión que hay que poner en
    * todas las entradas no especificadas en el archivo XML
    * original.
    * @return el valor por omisión.
    */
   public double getDefault() {
      return defaultvalue;
   }

   /**
    * Regresa el número total de columnas que debe tener la
    * matriz.
    * @return un entero no negativo, el número de columnas
    * en la matriz.
    */
   public int getRenglones() {
      return numrenglones;
   }

   /**
    * Regresa el número total de renglones que debe tener la
    * matriz.
    * @return un entero no negativo, el número de renglones
    * en la matriz.
    */
   public int getColumnas() {
      return numcolumnas;
   }

   /**
    * Regresa el número total de entradas disponibles con
    * valor especificado en el archivo XML.
    * @return un entero no negativo con el número total de
    * entradas.
    */
   public int getEntradas() {
      return entradas.length;
   }

   /**
    * Regresa una cadena que especifica el tipo de matriz
    * tal como fue especificado el el archivo XML.
    * @return una cadena que especifica el tipo de matriz.
    * Los valores posibles estan especificados en la
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
   public String getTipo() {
      return tipo;
   }

   /**
    * Regresa el índice del renglón de la entrada actual.
    * @return entero no negativo con el índice de renglón
    * donde se encuentra la entrada actual en la matriz.
    * @throws IndexOutOfBoundsException si el índice de
    * entrada actual es excesivo.
    */
   public int getRenglon()
      throws IndexOutOfBoundsException {
      if (current < entradas.length) {
         return entradas[current].getIdxRenglon();
      } else {
         throw new IndexOutOfBoundsException("Índice fuera de rango");
      }
   }

   /**
    * Regresa el índice de la columna de la entrada actual.
    * @return entero no negativo con el índice de la columna
    * donde se encuentra la entrada actual en la matriz.
    * @throws IndexOutOfBoundsException si el índice de
    * entrada actual es excesivo.
    */
   public int getColumna()
      throws IndexOutOfBoundsException {
      if (current < entradas.length) {
         return entradas[current].getIdxColumna();
      } else {
         throw new IndexOutOfBoundsException("Índice fuera de rango");
      }
   }

   /**
    * Regresa el valor de la entrada actual. En las matrices
    * simétricas las entradas del triángulo superior no
    * aparecen, sólo aquellas del tríangulo inferior y la
    * diagonal, cómo en el caso de las matrices triangulares
    * inferiores.
    * @return double con el contenido de la entrada actual.
    * @throws IndexOutOfBoundsException si el índice de
    * entrada actual es excesivo.
    */
   public double getEntrada()
      throws IndexOutOfBoundsException {
      if (current < entradas.length) {
         return entradas[current].getContenido();
      } else {
         throw new IndexOutOfBoundsException("Índice fuera de rango");
      }
   }

   /**
    * Incrementa el índice de la entrada actual.
    * @throws IndexOutOfBoundsException si el índice de
    * entrada actual se excede al incrementar.
    */
   public void siguienteEntrada()
      throws IndexOutOfBoundsException {
      if (current < (entradas.length - 1)) {
         current++;
      } else {
         throw new IndexOutOfBoundsException("Índice fuera de rango");
      }
   }

   /**
    * Determina si hay una siguiente entrada o no.
    * @return <code>true</code> si hay siguiente entrada,
    * <code>false</code> en otro caso.
    */
   public boolean haySiguiente() {
      return (current < (entradas.length - 1));
   }

   /**
    * Reinicia el índice de lectura de entradas.
    */
   public void reinicia() {
      current = 0;
   }

   /**
    * Programa de ejemplo
    */
   public static void main(String[] args) {
      if (args.length != 1) {
         System.err.println("Uso: java LectorMatrizXML <archivo>");
         System.exit(1);
      }

      try {
         LectorMatrizXML lector = new LectorMatrizXML(args[0]);
         int i;

         System.out.println("Default " + lector.getDefault());
         System.out.println("Renglones " + lector.getRenglones());
         System.out.println("Columnas " + lector.getColumnas());
         System.out.println("Tipo " + lector.getTipo());
         System.out.println("Entradas " + lector.getEntradas());

         for (i = 0; i < lector.getEntradas(); i++) {
            System.out.print(
                  "i= " + i + "\tRenglón: " + lector.getRenglon());
            System.out.print("\tColumna: " + lector.getColumna());
            System.out.println("\tContenido: " + lector.getEntrada());

            if (lector.haySiguiente()) {
               lector.siguienteEntrada();
            }
         }
      } catch (IndexOutOfBoundsException exio) {
         System.err.println("Se salió");
      } catch (Exception ex) {
         System.err.println("Oops!");
      }
   }
} // Fin de LectorMatrizXML.java
