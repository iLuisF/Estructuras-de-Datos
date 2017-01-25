
package mx.unam.fciencias.EstDat;

/**
 * Clase que implementa pilas usando listas.
 * @author Flores González Luis Brandon.
 * @version 2.0
 * @param <E> Tipo de dato.
 */
public class Pila<E> {
    
    /**
    * Pila construida con arreglo
    */
   public static final boolean PILA_ARREGLO = false;

   /**
    * Pila construida con lista ligada
    */
   public static final boolean PILA_LIGADA = true;

   /**
    * Mecanismo de construccion por omision: lista ligada.
    */
   public static final boolean PILA_OMISION = PILA_LIGADA;

   /**
    * Los elementos en la pila se almacenan en una lista
    * ligada.
    */
   private Lista<E> lista;

   /**
    * Construye una pila vacía. El mecanismo de
    * representación es, por omisión, una estructura
    * ligada.
    */
   public Pila() {
      lista = new ListaLigada<E>();
   }

   /**
    * Construye una pila vacía especificando su mecanismo
    * de representación.
    * @param ligada indica si la instancia de pila debe ser
    * construida usando una representación ligada o bien un
    * arreglo de tamaño adaptable.
    * <UL>
    * <LI> <code>true</code>  o
    * <code>PilaLst.PILA_LIGADA</code> indica representación ligada.
    * <LI> <code>false</code>  o
    * <code>PilaLst.PILA_ARREGLO</code> indica representación
    * con arreglo.
    * </UL>
    */
   public Pila(boolean ligada) {
      if (ligada) {
         lista = new ListaLigada<>();
      } else {
         lista = new ListaArreglo<>();
      }
   }

   /**
    * Construye una pila vacía. El argumento indica el
    * tamaño inicial mínimo que debe tener la pila
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la pila.
    */
   public Pila(int tam) {
      lista = new ListaArreglo<>(tam);
   }

   /**
    * Construye una pila vacía. El 2o argumento indica el
    * tamaño inicial mínimo que debe tener la pila,
    * resultante. Se utiliza una represetnación con arreglo.
    * @param tam es el tamaño inicial mínimo del arreglo que
    * contiene la pila.
    * @param bloque es el número de celdas que se añadena a
    * la pila cuando ya no caben más celdas.
    */
   public Pila(int tam, int bloque) {
      lista = new ListaArreglo<>(tam, bloque);
   }

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */   
   public boolean isVacia() {
      return lista.esVacia();
   }

   /**
    * Determina si una pila es vacía o no.
    * @return <code>true</code> Si la pila es vacía,
    * <code>false</code> en otro caso.
    */
   public boolean esVacia() {
      return isVacia();
   }

   /**
    * Determina el número de elementos elmacenados en la
    * pila.
    * @return número de celdas en la pila
    */
   
   public int longitud() {
      return lista.longitud();
   }

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ExcepcionAccesoIncorrecto en caso de pretender obtener el
    * tope de la pila vacía.
    */   
   public E tope()
      throws ExcepcionAccesoIncorrecto {
      if (!lista.esVacia()) {
         return lista.getPrimero();
      } else {
         throw new ExcepcionAccesoIncorrecto("PilaLst.tope");
      }
   }

   /**
    * Regresa el valor almacenado en el tope de la
    * pila.
    * @return El valor del objeto en el tope de la pila.
    * @throws ExcepcionAccesoIncorrecto en caso de pretender obtener el
    * tope de la pila vacía.
    */   
   public E peek()
      throws ExcepcionAccesoIncorrecto {
      return tope();
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */   
   public void apila(E nvotope) {
      lista.insertaPrimero(nvotope);
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */   
   public void push(E nvotope) {
      apila(nvotope);
   }

   /**
    * Introduce un nuevo elemento en la pila.
    * @param nvotope Nuevo elemento en el tope
    * de la pila.
    */  
   public void mete(E nvotope) {
      apila(nvotope);
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ExcepcionAccesoIncorrecto en caso de pretender eliminar el
    * tope de la pila vacía.
    */   
   public E desapila()
      throws ExcepcionAccesoIncorrecto {
      E resul;

      if (!lista.esVacia()) {
         resul = lista.getPrimero();
         lista.eliminaPrimero();

         return resul;
      } else {
         throw new ExcepcionAccesoIncorrecto("PilaLst.desapila");
      }
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ExcepcionAccesoIncorrecto en caso de pretender eliminar el
    * tope de la pila vacía.
    */   
   public E pop()
      throws ExcepcionAccesoIncorrecto {
      return desapila();
   }

   /**
    * Elimina el elemento que fue introducido más
    * recientemente a la pila.
    * @return una referencia al objeto que es retirado del
    * tope.
    * @throws ExcepcionAccesoIncorrecto en caso de pretender eliminar el
    * tope de la pila vacía.
    */   
   public E saca()
      throws ExcepcionAccesoIncorrecto {
      return desapila();
   }
    
}
