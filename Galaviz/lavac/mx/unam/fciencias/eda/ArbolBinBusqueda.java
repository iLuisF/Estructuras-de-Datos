/* ----------------------------------------------------------------------
 * ArbolBinBusqueda.java
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

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Implementación de árboles binarios de búsqueda. Los
 * mecanismos de inserción y eliminación aseguran que los
 * datos permanecen ordenados en el árbol.
 * @param <E> es el tipo genérico de objetos que se almacenan en el árbol
 * binario de búsqueda. Debe implementar la interfaz
 * <code>Comparable</code>.
 *
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 4.0 <br>
 * marzo 2015
 */
public class ArbolBinBusqueda<E extends Object & Comparable<? super E>> {
    /**
     * Mensaje de error cuando se pretende abrir el archivo dot para
     * escribir el árbol.
     */
    private static final String ERR_IO = "Error al crear archivo de salida";
    /**
     * El nombre genérico que aparecerá en los archivos dot.
     */
    private static final String NOMBREDEFAULT = "ArbolBinBusqueda";
    /**
     * Referencia al nodo raíz del árbol.
     */
    protected NodoBin<E> raiz;

   /**
    * Construye un árbol binario de búsqueda s partir de
    * nada. Por supuesto el resultado es un árbo vacío.
    */
   public ArbolBinBusqueda() {
      raiz = null;
   }

   /**
    * Cosntruye un árbol binario de búsqueda dando el
    * elemento que debe quedar en la raíz, único nodo del
    * árbol resultante.
    * @param elemraiz es el elemento que será
    * almacenado en la raíz del árbol resultante. Este
    * elemento deberá ser instancia de
    * <code>Comparable</code>.
    */
   public ArbolBinBusqueda(E elemraiz) {
      raiz = new NodoBin<E>(elemraiz);
   }

   /**
    * Inserta un elemento ordenadamente a partir del nodo
    * referido en el parámetro.
    * @param elem el elemento a insertar
    * @param ndorz nodo raíz del subárbol donde
    * hay que insertar el nuevo elemento
    * @return el nuevo nodo raíz del subárbol
    */
   private NodoBin<E> inserta(E elem, NodoBin<E> ndorz) {
      // caso base de la recursión
      if (ndorz == null) {
         ndorz = new NodoBin<E>(elem);
      } else {
         if (elem.compareTo(ndorz.getContenido()) < 0) {
            ndorz.setIzquierdo(inserta(elem, ndorz.getIzquierdo()));
         } else if (elem.compareTo(ndorz.getContenido()) > 0) {
            ndorz.setDerecho(inserta(elem, ndorz.getDerecho()));
         } else { // elem ya está en el árbol
            System.out.println("el elemento ya está");
         }
      }

      return ndorz;
   }

   /**
    * Elimina un elemento preservando el orden.
    * @param ndorz NodoBin en la raíz del subárbol
    * donde hay que eliminar
    * @param elem el elemento a eliminar.
    * @return el nuevo nodo raíz del subárbol
    */
   private NodoBin<E> elimina(E elem, NodoBin<E> ndorz) {
      if (ndorz == null) {
         // no se encontró
         return null;
      } else {
         if (elem.compareTo(ndorz.getContenido()) < 0) {
            ndorz.setIzquierdo(elimina(elem, ndorz.getIzquierdo()));
         } else if (elem.compareTo(ndorz.getContenido()) > 0) {
            ndorz.setDerecho(elimina(elem, ndorz.getDerecho()));
         } else { // este es el nodo que hay que eliminar

            // si tiene dos hijos
            if ((ndorz.getDerecho() != null)
                  && (ndorz.getIzquierdo() != null)) {
               // el mínimo del subárbol derecho queda en
               // lugar del eliminado y lo eliminamos de
               // donde estaba originalmente
               ndorz.setContenido(
                     getMin(ndorz.getDerecho()).getContenido());
               ndorz.setDerecho(
                     elimina(ndorz.getContenido(),
                     ndorz.getDerecho()));
            } else { // si solo tiene un hijo

               // el hijo ocupa el lugar del padre
               ndorz = (ndorz.getDerecho() != null)
                     ? ndorz.getDerecho()
                     : ndorz.getIzquierdo();
            }
         }

         return ndorz;
      }
   }

    /**
     * Imprime un árbol binario en el formato aceptado por
     * dot.
     * @param nodoraiz nodo a partir del cual
     * se imprime el árbol
     * @return el código dot para trazar el árbol.
     */
    private String toDotString(NodoBin<E> nodoraiz) {
        String nueva = new String("");

        if ((nodoraiz.getDerecho() != null)
            || (nodoraiz.getIzquierdo() != null)) {
            if (nodoraiz.getIzquierdo() != null) {
                nueva = nueva.concat(
                        "   " + nodoraiz.getContenido().toString() + " -> "
                        + nodoraiz.getIzquierdo().getContenido().toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if (nodoraiz.getDerecho() != null) {
                nueva = nueva.concat(
                        "   " + nodoraiz.getContenido().toString() + " -> "
                        + nodoraiz.getDerecho().getContenido().toString()
                        + " [arrowhead=\"none\"];\n");
            }

            if (nodoraiz.getIzquierdo() != null) {
                nueva = nueva.concat(toDotString(nodoraiz.getIzquierdo()));
            }

            if (nodoraiz.getDerecho() != null) {
                nueva = nueva.concat(toDotString(nodoraiz.getDerecho()));
            }
        } else {
            nueva = nueva.concat(
                    "   " + nodoraiz.getContenido().toString() + ";\n");
        }

        return nueva;
    }

    /**
     * Imprime un árbol binario indentado de acuerdo a su
     * estructura.
     * @param nodoraiz nodo a partir del cual
     * se imprime el árbol
     * @param nivel nivel del nodo actual en la
     * jererquía del árbol.
     * @return una cadena que representa la estructura del árbol.
     */
    private String toString(NodoBin<E> nodoraiz, int nivel) {
        char[] arreglotabs = null;
        String nueva = new String("");

        if (nodoraiz != null) {
            arreglotabs = new char[nivel];

            for (int i = 0; i < nivel; i++) {
                arreglotabs[i] = '\t';
            }

            nueva = nueva.concat(
                    new String(arreglotabs) + nodoraiz.getContenido().toString()
                    + "\n");
            nueva = nueva.concat(toString(nodoraiz.getDerecho(), nivel + 1));
            nueva = nueva.concat(toString(nodoraiz.getIzquierdo(), nivel + 1));
        }

        return nueva;
    }

    /**
     * Determina la altura de un subárbol. Aquel cuyo nodo
     * raiz se pasa como argumento.
     * @param nodoraiz nodo en la raíz del
     * subárbol del que se reuiere la altura.
     * @return altura del subárbol
     */
    protected int altura(NodoBin<E> nodoraiz) {
        int alti;
        int altd;

        if (nodoraiz == null) {
            return -1;
        } else {
            alti = altura(nodoraiz.getIzquierdo());
            altd = altura(nodoraiz.getDerecho());

            if (alti >= altd) {
                return alti + 1;
            } else {
                return altd + 1;
            }
        }
    }

   /**
    * Regresa el <code>NodoBin</code> que contiene el elemento
    * máximo del árbol.
    * @param ndorz raíz del subárbol donde hay
    * que buscar el máximo.
    * @return el nodo con el elemento máximo.
    */
   protected NodoBin<E> getMax(NodoBin<E> ndorz) {
      if (ndorz.getDerecho() != null) {
         return getMax(ndorz.getDerecho());
      } else {
         return ndorz;
      }
   }

   /**
    * Regresa el <code>NodoBin</code> que contiene el elemento
    * mínimo del árbol.
    * @param ndorz raíz del subárbol donde hay
    * que buscar el mínimo.
    * @return el nodo con el elemento mínimo.
    */
   protected NodoBin<E> getMin(NodoBin<E> ndorz) {
      if (ndorz.getIzquierdo() != null) {
         return getMin(ndorz.getIzquierdo());
      } else {
         return ndorz;
      }
   }

   /**
    * Determina si un elemento está o no en el subárbol cuya
    * raíz se pasa como parámetro.
    * @param ndorz raíz del subárbol.
    * @param elem elemento a buscar.
    * @return <code>true</code> si el elemento está,
    * <code>false</code> si no.
    */
   private boolean esElemento(E elem, NodoBin<E> ndorz) {
      if (ndorz == null) {
         return false;
      } else {
         if (elem.compareTo(ndorz.getContenido()) < 0) {
            return esElemento(elem, ndorz.getIzquierdo());
         } else if (elem.compareTo(ndorz.getContenido()) > 0) {
            return esElemento(elem, ndorz.getDerecho());
         } else {
            return true;
         }
      }
   }

   /**
    * Inserta, preservando el orden en el árbol, el elemento
    * que es pasado como parámetro.
    * @param elem elemento a insertar
    */
   public void inserta(E elem) {
      raiz = inserta(elem, raiz);
   }

   /**
    * Elimina  del árbol el elemento pasado como
    * parámetro. Asegura que, luego de eliminarlo, el árbol
    * resultante seguirá siendo un árbol binario de búsqueda.
    * @param elem el elemento a eliminar
    */
   public void elimina(E elem) {
      raiz = elimina(elem, raiz);
   }

   /**
    * Determina si un elemento está o no en el árbol.
    * @param elem elemento a buscar.
    * @return <code>true</code> si el elemento está,
    * <code>false</code> si no.
    */
   public boolean isElemento(E elem) {
      return esElemento(elem, raiz);
   }

   /**
    * Determina si un elemento está o no en el árbol.
    * @param elem elemento a buscar.
    * @return <code>true</code> si el elemento está,
    * <code>false</code> si no.
    */
   public boolean esElemento(E elem) {
      return esElemento(elem, raiz);
   }

   /**
    * Regresa el elemento máximo de los que se encuentran en
    * el árbol.
    * @return el elemento de valor más alto de aquellos en
    * el árbol.
    */
   public Comparable getMax() {
      return (Comparable) getMax(raiz).getContenido();
   }

   /**
    * Regresa el elemento mínimo de los que se encuentran en
    * el árbol.
    * @return el elemento de valor más bajo de aquellos en
    * el árbol.
    */
   public Comparable getMin() {
      return (Comparable) getMin(raiz).getContenido();
   }

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    public boolean isVacio() {
        return (raiz == null);
    }

    /**
     * Determina si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío,
     * <code>false</code> si no lo es.
     */
    public boolean esVacio() {
        return isVacio();
    }

    /**
     * Determina la altura del árbol. Número máximo de
     * aristas que es necesario recorrer para llegar de la
     * raíz del árbol a un nodo hoja. Por convención la
     * altura del árbol vacío es -1.
     * @return altura del árbol
     */
    public int altura() {
        return altura(raiz);
    }

    /**
     * Genera una cadena de caracteres con indentación para
     * cada nivel del árbol.
     * @return una cadena con cambios de línea y tabuladores
     * para representar correctamente la jerarquía de los
     * nodos del árbol.
     */
    @Override
    public String toString() {
        return toString(raiz, 0);
    }

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    public boolean isHoja() {
        return (raiz.sinHijos());
    }

    /**
     * Informa si el árbol es hoja.
     * @return <code>true</code> si el árbol es una hoja,
     * <code>false</code> si no lo es.
     */
    public boolean esHoja() {
        return isHoja();
    }

    /**
     * Limpia el árbol. Lo convierte en árbol vacío.
     */
    public void limpia() {
       raiz = null;
    }

    /**
     * Método para obtner un archivo ejecutable para el
     * programa <code>dot</code> de <b>Graphviz</b>. El
     * archivo resultante pueder ser procesado usando:<br>
     * <code>dot -Tjpg archivo.dot -o archivo.jpg</code><br>
     * por ejemplo, lo que da como resultado un archivo JPEG
     * con la imagen del árbol construido.
     * @param nomarchivo es el nombre del archivo a
     * generar. La extensión "<code>.dot</code>" se añade
     * automáticamente. Si este parámetro es
     * <code>null</code> o la cadena posee longitud cero
     * entonces se asigna un nombre da archivo por omisión
     * (<code>ArbolBin.dot</code>).
     */
    public void generaDotPrg(String nomarchivo) {
        try {
            if ((nomarchivo == null) || (nomarchivo.length() == 0)) {
                nomarchivo = new String(NOMBREDEFAULT);
            }

            String nombre = new String(nomarchivo + ".dot");
            FileOutputStream fos = new FileOutputStream(nombre);
            PrintStream archsal = new PrintStream(fos, true);

            archsal.println("/* ");
            archsal.println(" * " + nombre);
            archsal.println(
                    " * Árbol binario generado por "
                            + this.getClass().getName());
            archsal.println(" * Copyright (C) 2004  José Galaviz Casas,");
            archsal.println(" * Facultad de Ciencias,");
            archsal.println(
                    " * Universidad Nacional Autónoma de México, Mexico.");
            archsal.println(" */");
            archsal.println("digraph G {");
            archsal.print(toDotString(raiz));
            archsal.println("}");
            archsal.flush();
            archsal.close();
        } catch (Exception exg) {
            System.err.println(ERR_IO);
            System.err.println(exg.getMessage());
            exg.printStackTrace();
        }
    }

   /**
    * Programa de prueba.
    * @param args ninguno
    */
   public static void main(String[] args) {
      ArbolBinBusqueda<String> abb = new ArbolBinBusqueda<String>();

      abb.inserta(new String("Enodo"));
      abb.inserta(new String("Bnodo"));
      abb.inserta(new String("Anodo"));
      abb.inserta(new String("Cnodo"));
      abb.inserta(new String("Hnodo"));
      abb.inserta(new String("Knodo"));
      abb.inserta(new String("Gnodo"));
      abb.inserta(new String("Fnodo"));
      abb.inserta(new String("Jnodo"));
      abb.inserta(new String("Inodo"));
      abb.inserta(new String("Mnodo"));
      abb.inserta(new String("Lnodo"));
      abb.inserta(new String("Nnodo"));
      System.out.println(abb.toString());
      abb.elimina(new String("Knodo"));
      System.out.println(abb.toString());
      abb.elimina(new String("Mnodo"));
      System.out.println(abb.toString());
      abb.elimina(new String("Nnodo"));
      System.out.println(abb.toString());
      abb.elimina(new String("Lnodo"));
      System.out.println(abb.toString());
      abb.inserta(new String("Dnodo"));
      abb.inserta(new String("Gpodo"));
      System.out.println(abb.toString());
      abb.generaDotPrg("ArbolBinBusqueda");
      System.out.println("Mínimo: " + abb.getMin());
      System.out.println("Máximo: " + abb.getMax());

      if (abb.esElemento(new String("Gpodo"))) {
         System.out.println("Si está Gpodo");
      }

      if (!abb.esElemento(new String("Godo"))) {
         System.out.println("No está Godo");
      }
   }
} // Fin de ArbolBinBusqueda.java
