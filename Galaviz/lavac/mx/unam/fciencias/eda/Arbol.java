/* ----------------------------------------------------------------------
 * Arbol.java
 * version 3.0
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
 * Clase que implementa árboles generales con número
 * arbitrario y variable de hijos en cada nodo. La
 * implementación utiliza una representación ligada.
 * @param <N> es el tipo de dato almacenado en los nodos.
 * @param <A> es el tipo de dato almacenado en las aristas.
 *
 * @since 3.0
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0  <br>
 * marzo 2015
 * @see NodoArbol
 * @see AristaArbol
 */
public class Arbol<N, A> {
   /**
    * Mensaje de error cuando se pretende hacer operación en
    * árbol vacío.
    */
   private static final String ERR_AV = "Árbol vacío";

   /**
    * Mensaje de error al crear archivo dot.
    */
   private static final String ERR_IO = "Error al crear archivo de salida";

   /**
    * Nombre por omisión del archivo dot.
    */
   private static final String NOMBREDEFAULT = "Arbol";

   /**
    * Referencia al nodo raíz.
    */
   protected NodoArbol<N, A> raiz;

   /**
    * Construye un árbol binario a partir de un nodo raíz.
    * @param nvaraiz nuevo nodo raíz
    */
   protected Arbol(NodoArbol<N, A> nvaraiz) {
      raiz = nvaraiz;
   }

   /**
    * Construye un árbol vacío.
    */
   public Arbol() {
      hazVacio();
   }

   /**
    * Construye un árbol con un sólo nodo (la raíz).
    * @param elemraiz elemento en la raíz del árbol.
    */
   public Arbol(N elemraiz) {
      raiz = new NodoArbol<N, A>(elemraiz);
   }

   /**
    * Transforma la instancia en un árbol vacío.
    */
   private void hazVacio() {
      raiz = null;
   }

   /**
    * Genera cadena ejecutable de dot.
    * @param nodoraiz es la raiz del árbol a trazar.
    * @return la cadena en formato dot con la especificación del árbol.
    */
   private String toDotString(NodoArbol<N, A> nodoraiz) {
      int nhijos = nodoraiz.getNumAristas();
      String cadena = new String("");

      if (nhijos == 0) {
         return new String("   " + nodoraiz.getElemento() + ";\n");
      }

      int i;

      try {
         for (i = 0; i < nhijos; i++) {
            if (nodoraiz.getArista(i).getElemento() == null) {
               cadena = cadena.concat(
                             "   " + nodoraiz.getElemento().toString()
                             + " -> "
                             + nodoraiz.getArista(i).getDestino().getElemento()
                             + " [arrowhead=\"none\"];\n");
            } else {
               cadena = cadena.concat(
                             "   " + nodoraiz.getElemento().toString()
                             + " -> "
                             + nodoraiz.getArista(i).getDestino().getElemento()
                             + " [arrowhead=\"none\", label=\""
                             + nodoraiz.getArista(i).getElemento() + "\"];\n");
            }
         }

         for (i = 0; i < nhijos; i++) {
            cadena = cadena.concat(
                            toDotString(nodoraiz.getArista(i).getDestino()));
         }
      } catch (ErrorAccesoException eai) {
         ;
      }

      return cadena;
   }

   /**
    * Determina la altura de un subárbol. Aquel cuyo nodo
    * raiz se pasa como argumento.
    * @param nodoraiz nodo en la raíz del
    * subárbol del que se reuiere la altura.
    * @return altura del subárbol
    */
   protected int altura(NodoArbol<N, A> nodoraiz) {
      int altmax = -1;
      int altsub = -1;

      if (nodoraiz != null) {
         int nhijos = nodoraiz.getNumAristas();

         if (nhijos > 0) {
            try {
               for (int i = 0; i < nhijos; i++) {
                  altsub = altura(nodoraiz.getArista(i).getDestino());

                  if (altsub > altmax) {
                     altmax = altsub;
                  }
               }
            } catch (ErrorAccesoException eai) {
               ;
            }
         }

         altmax++;
      }

      return altmax;
   }

   /**
    * regresa el elemento en la raíz del árbol.
    * @return Referencia al elemento almacenado en la raíz.
    * @throws ErrorAccesoException en caso de
    * pretender extraer la raíz de un árbol vacío
    */
   public N getRaiz()
      throws ErrorAccesoException {
      if (!isVacio()) {
         return raiz.getElemento();
      } else {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".getRaiz: " + ERR_AV);
      }
   }

   /**
    * Regresa el número de subárboles pendientes de la raíz de la
    * instancia que llama. Las llamadas a <code>getSubArbol()</code> pueden
    * entonces usar valores de índice en el conjunto:<br>
    * {0,..., getNumSubArboles() - 1}.
    * @return entero no negativo con el número de subárboles disponibles.
    * @throws ErrorAccesoException si la instancia que llama es árbol
    * vacío.
    */
   public int getNumSubArboles()
      throws ErrorAccesoException {
      if (isVacio()) {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".getNumHijos: " + ERR_AV);
      } else {
         return raiz.getNumAristas();
      }
   }

   /**
    * Regresa un subárbol de la instancia que
    * invoca el método. Si la instancia no tiene hijos
    * regresa árbol vacío.
    * @param nsub índice del subárbol solicitado. Si el
    * nodo raíz del árbol que llama tiene <i>n</i> hijos
    * entonces el índice debe estar en el conjunto <i>{0,
    * ..., n-1}</i> para no ser árbol vacío.
    * @return El subárbol solicitado del árbol que invoca el
    * método. Se regresa una instancia de <code>Arbol</code>.
    * @throws ErrorAccesoException en caso de
    * pretender extraer el subárbol de un árbol vacío
    */
   public Arbol<N, A> getSubArbol(int nsub)
      throws ErrorAccesoException {
      if (isVacio()) {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".getSubArbol: " + ERR_AV);
      } else {
         if (nsub >= raiz.getNumAristas()) {
            return new Arbol<N, A>();
         } else {
            return new Arbol<N, A>(raiz.getArista(nsub).getDestino());
         }
      }
   }

   /**
    * Establece el elemento en la raíz del árbol.
    * @param elemento Nuevo elemento en la
    * raíz del árbol.
    */
   public void setRaiz(N elemento) {
      if (isVacio()) {
         raiz = new NodoArbol<N, A>(elemento);
      } else {
         raiz.setElemento(elemento);
      }
   }

   /**
    * Establece un subárbol.
    * @param subarbol subárbol a añadir.
    * @throws ErrorAccesoException en caso de
    * pretender establecer el subárbol de un árbol vacío,
    * al menos debe ser un nodo raíz.
    */
   public void setSubArbol(Arbol<N, A> subarbol)
      throws ErrorAccesoException {
      if (isVacio()) {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".setSubArbol: " + ERR_AV);
      } else {
         raiz.setArista(new AristaArbol<N, A>(raiz, subarbol.raiz, null));
      }
   }

   /**
    * Establece un subárbol y asocia un objeto con la arista
    * que lo  une a este árbol.
    * @param subarbol subárbol a añadir.
    * @param liga es un objeto que será asociado con la
    * arista que une al este árbol con el subarbol dado como
    * argumento.
    * @throws ErrorAccesoException en caso de
    * pretender establecer el subárbol de un árbol vacío,
    * al menos debe ser un nodo raíz.
    */
   public void setSubArbol(Arbol<N, A> subarbol, A liga)
      throws ErrorAccesoException {
      if (isVacio()) {
         throw new ErrorAccesoException(this.getClass().getName()
                                        + ".setSubArbol: " + ERR_AV);
      } else {
         raiz.setArista(new AristaArbol<N, A>(raiz, subarbol.raiz, liga));
      }
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
    * Informa si el árbol es hoja.
    * @return <code>true</code> si el árbol es una hoja,
    * <code>false</code> si no lo es.
    */
   public boolean isHoja() {
      return (raiz.getNumAristas() == 0);
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
    * (<code>Arbol.dot</code>).
    */
   public void generaDotPrg(String nomarchivo) {
      try {
         if ((nomarchivo == null) || (nomarchivo.length() == 0)) {
            nomarchivo = new String(NOMBREDEFAULT);
         }

         String           nombre  = new String(nomarchivo + ".dot");
         FileOutputStream fos     = new FileOutputStream(nombre);
         PrintStream      archsal = new PrintStream(fos, true);

         archsal.println("/* ");
         archsal.println(" * " + nombre);
         archsal.println(
                         " * Árbol generado por " + this.getClass().getName());
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
    * Clase que modela los nodos de un árbol general.
    *
    * @since 2.0
    * @author José Galaviz (galaviz@ciencias.unam.mx)
    * @version 3.0<br>
    * marzo 2015
    * @see AristaArbol
    * @see Arbol
    */
   private class NodoArbol<N, A> {

      /**
       * Marca del vértice, puede ser un identificador de
       * color, por ejemplo, en los algoritmos de
       * coloración. En BFS y DFS este podría ser un booleano.
       */
      private int marca;

      /**
       * La lista ligada de aristas de salida del vértice.
       */
      private ListaLigada<AristaArbol<N, A>> aristas;

      /**
       * El contenido del nodo.
       */
      private N contenido;

      /**
       * Construye un nodo con contenido especificado.
       * @param elemento es el objeto que se desea almacenar en
       * el nodo.
       */
      public NodoArbol(N elemento) {
         marca = 0;
         aristas = new ListaLigada<AristaArbol<N, A>>();
         contenido = elemento;
      }

      /**
       * Regresa el elemento contenido en el nodo.
       * @return el objeto almacenado en el nodo.
       */
      public N getElemento() {
         return contenido;
      }

      /**
       * Establece la marca del nodo con un valor dado.
       * En aplicaciones donde se colorean nodos es útil asociar
       * códigos a los colores y usarlos como marca de los
       * nodos.
       * @param valmarca es el valor que se desea poner como
       * marca. Si este valor es cero el nodo no está marcado.
       */
      public void setMarca(int valmarca) {
         marca = valmarca;
      }

      /**
       * Establece el nodo como marcado. El nodo es
       * marcado con un valor por omisión. Esto es útil para
       * aquellos algoritmos en ls que sólo es importante
       * marcar un nodo como visitado.
       */
      public void setMarca() {
         marca = 1;
      }

      /**
       * Regresa el valor de la marca del nodo.
       * @return entero con el valor de la marca.
       */
      public int getMarca() {
         return marca;
      }

      /**
       * Determina si un nodo ha sido marcado o no.
       * @return <code>true</code> si el nodo ha sido
       * marcado, <codefalse</code> en otro caso.
       */
      public boolean estaMarcado() {
         return (marca != 0);
      }

      /**
       * Establece una nueva arista de salida de este
       * nodo.
       * @param salida es la arista que se añade.
       */
      public void setArista(AristaArbol<N, A> salida) {
         aristas.insertaUltimo(salida);
      }

      /**
       * Establece una nueva arista de salida de este
       * nodo. Inserta en una posición específica.
       * @param idx es el índice de la posición en la que se
       * pretende añadir la arista. Debe ser un entero no
       * negativo menor o igual al número actual de aristas en
       * el nodo.
       * @param salida es la arista que se añade.
       * @throws ErrorAccesoException si el índice
       * especificado es inválido.
       */
      public void setArista(int idx, AristaArbol<N, A> salida)
         throws ErrorAccesoException {
         aristas.inserta(idx, salida);
      }

      /**
       * Establece el elemento almacenado en el nodo.
       * @param elemento es el objeto que se desea esté
       * contenido en el nodo.
       */
      public void setElemento(N elemento) {
         contenido = elemento;
      }

      /**
       * Regresa una arista del nodo.
       * @param idx es el índice de la arista deseada. Debe ser
       * un entero no negativo menor que el número de aristas
       * del nodo.
       * @return la arista solicitada
       * @throws ErrorAccesoException si el íñdice dado
       * como argumento es inválido.
       */
      public AristaArbol<N, A> getArista(int idx)
         throws ErrorAccesoException {
         return (AristaArbol<N, A>) aristas.getElemento(idx);
      }

      /**
       * Elimina una arista.
       * @param idx es el índice de la arista a eliminar. Debe
       * ser entero no negativo menor al número de aristas del
       * nodo.
       * @throws ErrorAccesoException si el íñdice es
       * inválido.
       */
      public void eliminaArista(int idx)
         throws ErrorAccesoException {
         aristas.elimina(idx);
      }

      /**
       * Regresa el número de aristas de salida del nodo.
       * @return un entero no negativo con el número de
       * aristas.
       */
      public int getNumAristas() {
         return aristas.longitud();
      }

      /**
       * Determina si el nodo tiene aristas de salida.
       * @return <code>true</code> sii el nodo tiene aristas de
       * salida.
       */
      public boolean hasAristas() {
         return (aristas.longitud() > 0);
      }

      /**
       * Determina si el nodo tiene aristas de salida.
       * @return <code>true</code> sii el nodo tiene aristas de
       * salida.
       */
      public boolean hayAristas() {
         return hasAristas();
      }

   } // Fin de NodoArbol

   /**
    * Clase para representar las aristas que unen los vértices
    * de un árbol general.
    *
    * @since 2.0
    * @author José Galaviz (galaviz@ciencias.unam.mx)
    * @version 3.0<br>
    * marzo 2015
    * @see Arbol
    * @see NodoArbol
    */
   private class AristaArbol<N, A> {

      /**
       * El vértice destino.
       */
      private NodoArbol<N, A> adonde;

      /**
       * El vértice origen.
       */
      private NodoArbol<N, A> dedonde;

      /**
       * El contenido de la arista.
       */
      private A contenido;

      /**
       * Construye una arista dados los vertices de
       * origen y destino de la arista y su contenido. Este es un modelo
       * adecuado para árboles generales.
       * @param elemento es el contenido de la arista
       * @param origen es el vértice origen de la arista.
       * @param destino es el vértice destino de la arista.
       */
      public AristaArbol(NodoArbol<N, A> origen,
                         NodoArbol<N, A> destino, A elemento) {
         contenido = elemento;
         dedonde = origen;
         adonde = destino;
      }

      /**
       * Regresa el <code>NodoArbol</code> destino de la arista.
       * @return el vértice destino de la arista.
       */
      public NodoArbol<N, A> getDestino() {
         return adonde;
      }

      /**
       * Regresa el <code>NodoArbol</code> origen de la arista.
       * @return el vértice origen de la arista.
       */
      public NodoArbol<N, A> getOrigen() {
         return dedonde;
      }

      /**
       * Regresa el peso de la arista.
       * @return un número real con el peso asociado a la
       * arista.
       */
      public A getElemento() {
         return contenido;
      }

      /**
       * Establece el elemento guardado en la arista.
       * @param elemento es el objeto a almacenar en la
       * arista.
       */
      public void setElemento(A elemento) {
         contenido = elemento;
      }

      /**
       * establece el nodo destino de la arista.
       * @param destino es una referencia al nodo destino de la
       * arista.
       */
      public void setDestino(NodoArbol<N, A> destino) {
         adonde = destino;
      }

      /**
       * Estblece el nodo origen de la arista.
       * @param origen es una referenia al nodo de donde surge
       * la arista.
       */
      public void setOrigen(NodoArbol<N, A> origen) {
         dedonde = origen;
      }

   } // Fin de AristaArbol

   /**
    * Programa de prueba, no requiere argumentos para
    * ejecutarse. Genera automáticamente un archivo dot.
    * @param args vacío.
    */
   public static void main(String[] args) {
      try {
         Arbol<String,String> arbolA = new Arbol<String,String>(new String("raiz"));
         Arbol<String,String> arbolB = new Arbol<String,String>(new String("hijo1"));
         Arbol<String,String> arbolC = new Arbol<String,String>(new String("hijo2"));
         Arbol<String,String> arbolD = new Arbol<String,String>(new String("hijo3"));
         Arbol<String,String> arbolE = new Arbol<String,String>(new String("hijo4"));
         Arbol<String,String> arbolF = new Arbol<String,String>(new String("hijo5"));
         Arbol<String,String> arbolG = new Arbol<String,String>(new String("nieto1"));
         Arbol<String,String> arbolH = new Arbol<String,String>(new String("nieto2"));
         Arbol<String,String> arbolI = new Arbol<String,String>(new String("nieto3"));
         Arbol<String,String> arbolJ = new Arbol<String,String>(new String("hoja8"));
         Arbol<String,String> arbolK = new Arbol<String,String>(new String("hoja7"));
         Arbol<String,String> arbolL = new Arbol<String,String>(new String("hoja6"));
         Arbol<String,String> arbolM = new Arbol<String,String>(new String("hoja5"));
         Arbol<String,String> arbolN = new Arbol<String,String>(new String("hoja4"));
         Arbol<String,String> arbolO = new Arbol<String,String>(new String("hoja3"));
         Arbol<String,String> arbolP = new Arbol<String,String>(new String("hoja2"));
         Arbol<String,String> arbolQ = new Arbol<String,String>(new String("hoja1"));

         arbolG.setSubArbol(arbolQ);
         arbolG.setSubArbol(arbolP);
         arbolG.setSubArbol(arbolO);

         arbolH.setSubArbol(arbolN);
         arbolH.setSubArbol(arbolM);
         arbolH.setSubArbol(arbolL);
         arbolH.setSubArbol(arbolK);

         arbolI.setSubArbol(arbolJ);

         arbolB.setSubArbol(arbolG);
         arbolB.setSubArbol(arbolH);
         arbolB.setSubArbol(arbolI);

         arbolA.setSubArbol(arbolB);
         arbolA.setSubArbol(arbolC);
         arbolA.setSubArbol(arbolD);
         arbolA.setSubArbol(arbolE);
         arbolA.setSubArbol(arbolF);

         arbolA.generaDotPrg(null);
      } catch (ErrorAccesoException eai) {
         System.err.println(eai.getMessage());
         eai.printStackTrace();
      }
   }
} // Arbol.java ends here.
