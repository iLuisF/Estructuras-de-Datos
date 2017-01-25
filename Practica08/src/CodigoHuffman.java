
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que implementa el árbol de Huffman, construido desde caracteres de un
 * String.
 *
 * @author Flores González Luis Brandon.
 * @version 2.0
 */
public class CodigoHuffman {

    private final String cadena; //Actual cadena para codificar.    
    private final ArrayList<Character> caracteres = new ArrayList<>(); //Cada caracter en la cadena para codificar.    
    private HashMap frecuencias = new HashMap(); //Frecuencia de cada caracter: La llave es el caracter y el elemento es un entero.    
    private final HashMap codificaciones = new HashMap();  //Resultado de la codificación de cada caracter: La llave es el caracter y el elemento es la codificación.    
    private ArbolBinarioLigado arbolBinario; //Árbol binario para la implementacion del árbol de Huffman.        

    /**
     * Constructor que usa una cadena. Ademas de generar las frecuencias, 
     * codificaciones y construir el árbol binario.
     *
     * @param cadena cadena para codificar.
     */
    public CodigoHuffman(String cadena) {
        this.cadena = cadena;
        frecuencias = generarFrecuencias(cadena);                  
        construirArbolBinario();
        generarCodificaciones(arbolBinario, "");       
    }
    
    /**
     * Regresa todos los caracteres de la cadena.
     *
     * @return Caracteres de la cadena.
     */
    public ArrayList<Character> getCaracteres() {
        return this.caracteres;
    }

    /**
     * Regresa la frecuencia del caracter dado usando el HashMap.
     *
     * @param caracter Caracter dado.
     * @return Frecuencia del caracter.
     */
    public int getFrecuenciaCaracter(Character caracter) {
        Integer frecuencia = (Integer) frecuencias.get(caracter);
        if (frecuencia == null) {
            return 0;
        } else {
            return frecuencia;
        }
    }

    /**
     * Regresa el codigo de Huffman por el caracter dado usando el HashMap.
     *
     * @param caracter Caracter dado.
     * @return Codificación del caracter.
     */
    public String getCodificacionCaracter(Character caracter) {
        return (String) codificaciones.get(caracter);
    }

    /**
     * Analiza una cadena para determinar la frecuencia de cada caracter en esa
     * cadena. Ademas genera un HashMap con caracteres como llaves y frecuencias
     * como elementos(valores).
     *
     * @param cadena Cadena a analizar para contar los caracteres.
     * @return Regresa HashMap.
     */
    private HashMap generarFrecuencias(String cadena) {
        HashMap frecuencia = new HashMap();        
        for (int i = 0; i < cadena.length(); i++) { //Itera a traves de la cadena, caracter por caracter.
            Character caracter = cadena.charAt(i);
            int frequency = 1;            
            //Si el caracter existe en el HashMap, itera su frecuencia.
            if (frecuencia.containsKey(caracter)) {
                frequency = (((Integer) frecuencia.get(caracter))) + 1;
            //Si el caracter no esta en el HashMap, agregar a la lista de caracter, guarda mapa como 1.
            }else{
                caracteres.add(caracter);
            }
            frecuencia.put(caracter, frequency);
        }
        return frecuencia;
    }

    /**
     * Construye una cola de prioridad ascendente con los caracteres de la
     * cadena.
     *
     * @return Cola de prioridad.
     */
    private ColaPrioridad construirColaPrioridad() {
        ColaPrioridad colaPrioridad = new ColaPrioridad();
        int count = caracteres.size();        
        for (int i = 0; i < count; i++) {
            Character caracter = caracteres.get(i);
            int indice = ((Integer) frecuencias.get(caracter));
            //Inserta frecuencia en la cola con subarbol con caracter como raiz.
            colaPrioridad.insertar(indice, new ArbolBinarioLigado(new NodoArbol(String.valueOf(caracter)),
                    null, null));
        }
        return colaPrioridad;
    }

    /**
     * Construye el árbol binario completo correspondiente a los caracteres
     * de la cadena. Incia por construir una cola de prioridad y entonces combina
     * los dos elementos de frecuencia mas bajos en un momento hasta que la cola
     * sea vacia.
     */
    private void construirArbolBinario() {
        ColaPrioridad colaPrioridad = construirColaPrioridad();
        while (colaPrioridad.getTamanio() > 1) {
            //Obtener los dos elementos de frecuencia más bajas y sus índices.
            int min = colaPrioridad.getFrecuenciaMinima();
            ArbolBinarioLigado minTree = (ArbolBinarioLigado) colaPrioridad.eliminarMinimo();
            int nextMin = colaPrioridad.getFrecuenciaMinima();
            ArbolBinarioLigado nextMinTree = (ArbolBinarioLigado) colaPrioridad.eliminarMinimo();
            /* 
             * Concatena los dos elementos y agrega la suma a la cola
             * mientras trabajamos nuestro camino de regreso al árbol hacia la raiz.
             */
            colaPrioridad.insertar(min + nextMin, new ArbolBinarioLigado(
                    new NodoArbol(String.valueOf(min + nextMin)),
                    minTree, nextMinTree));
        }        
        arbolBinario = colaPrioridad.esVacia()
                ? new ArbolBinarioLigado() : (ArbolBinarioLigado) colaPrioridad.eliminarMinimo();
    }

    /**
     * Genera codificaciones para cada caracter en el arbol de Huffman. Llama al
     * árbol binario totalmente construido y las codificaciones HashMap se
     * llenará con los códigos de carácter.
     *
     * @param arbol
     * @param codigoActual
     */
    private void generarCodificaciones(ArbolBinarioLigado arbol, String codigoActual) {       
        if (arbol.getIzquierdo().esVacio()) {//Si no hay hijo izquierdo, entonces ponemos el codigo actual.            
            //Consigue el caracter del nodo actual y actualiza su código.
            Character caracter = arbol.getRaiz().getElemento().charAt(0);
            codificaciones.put(caracter, codigoActual);
        } 
        else {            
            generarCodificaciones(arbol.getIzquierdo(), codigoActual + "0");
            generarCodificaciones(arbol.getDerecho(), codigoActual + "1");
        }
    }

    /**
     * Regresa un lista de objetos de código, con cada elemento en la lista
     * correspondiente a un caracter y sus frecuencia y codificación.
     *
     * @return Lista de codigos.
     */
    public ArrayList<Codigo> getListaCodigos() {
        ArrayList<Codigo> codigos = new ArrayList<>();
        int count = caracteres.size();        
        for (int i = 0; i < count; i++) {//Itera a traves de los caracteres, unificando con frecuencias y codigos.
            Character caracter = caracteres.get(i);
            //Crea un nuevo objeto codigo.
            codigos.add(new Codigo(caracter, ((Integer) frecuencias.get(caracter)),
                    (String) codificaciones.get(caracter)));
        }
        return codigos;
    }

    /**
     * Regresa la cadena original completamente codificada usando los codigos de
     * Huffman generados previamente. Pasar en la cadena para ser codificada y
     * son recuperadas desde el HashMap de codificación.
     *
     * @return Cadena codificada.
     */
    public String getcadenaCodificada() {
        String codificacion = "";
        int count = cadena.length();
        for (int i = 0; i < count; i++) {
            codificacion += getCodificacionCaracter(cadena.charAt(i)) + " ";
        }
        return codificacion.substring(0, codificacion.length() - 1);
    }
}
