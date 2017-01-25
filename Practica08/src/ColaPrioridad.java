
import java.util.ArrayList;

/**
 * Clase que implementa un cola de prioridad usando ArrayList.
 *
 * @author Flores González Luis Brandon.
 * @version 2.0
 */
public class ColaPrioridad {

    public ArrayList<Elemento> elementos = new ArrayList<>();

    /**
     * Constructor para una cola vacía la cual agrega un elemento con frecuencia
     * negativa para así siempre permanecer en la parte delantera de la cola.
     */
    public ColaPrioridad() {
        elementos.add(new Elemento(-1, null));
    }

    /**
     * Determina si la cola es vacía o no.
     *
     * @return True si es vacía o false en otro caso.
     */
    public boolean esVacia() {
        return elementos.size() == 1;
    }

    /**
     * Tamaño de la cola (menos la cabeza).
     *
     * @return Regresa el tamaño de la cola.
     */
    public int getTamanio() {
        return elementos.size() - 1;
    }

    /**
     * Regresa la frecuencia minima de la cola.
     *
     * @return Frecuencia minima.
     */
    public int getFrecuenciaMinima() {
        return ((Elemento) elementos.get(1)).getFrequencia();
    }

    /**
     * Regresa el elemento con la minima frecuencia en la cola.
     *
     * @return Elemento minimo.
     */
    public Object getElementoMinimo() {
        return elementos.get(1);
    }

    /**
     * Inserta el objeto en la cola de prioridad mientras el orden de la
     * prioridad se mantenga. Se inserta el valor de la frecuencia, los
     * subarboles son los objetos (elementos).
     *
     * @param frecuencia - La frecuencia.
     * @param elemento - Objeto, es decir, el elemento.
     */
    public void insertar(int frecuencia, Object elemento) {
        int tamanio = elementos.size();
        int mitadCamino = tamanio / 2;
        elementos.add(null); //Consigue una pocision al final de la cola.
        Elemento padre = (Elemento) elementos.get(mitadCamino);
        while (padre.getFrequencia() > frecuencia) {
            elementos.set(tamanio, padre);
            tamanio = mitadCamino;
            mitadCamino = mitadCamino / 2;
            padre = (Elemento) elementos.get(mitadCamino);
        }
        elementos.set(tamanio, new Elemento(frecuencia, elemento));
    }

    /**
     * Elimina el elemento con la minima frecuencia en la cola y lo regresa
     *
     * @return El elemento minimo.
     */
    public Object eliminarMinimo() {
        Object minimo = ((Elemento) elementos.get(1)).getElemento();
        Elemento ultimo = (Elemento) elementos.remove(elementos.size() - 1);
        int frecuencia = ultimo.getFrequencia();
        int indice = 1;
        int marcador = 2;
        while (marcador < elementos.size()) {
            if (marcador + 1 < elementos.size()
                    && ((Elemento) elementos.get(marcador)).getFrequencia()
                    > ((Elemento) elementos.get(marcador + 1)).getFrequencia()) {
                marcador++;
            }
            if (((Elemento) elementos.get(marcador)).getFrequencia() > frecuencia) {
                break;
            }
            elementos.set(indice, elementos.get(marcador));
            indice = marcador;
            marcador *= 2;
        }
        if (indice < elementos.size()) {
            elementos.set(indice, ultimo);
        }
        return minimo;
    }

    /**
     * Se define la estructura de un elemento que se implementara en la cola de
     * prioridad. Contiene como información un objeto y un valor de frecuencia.
     */
    public class Elemento {

        private final int frecuencia;
        private final Object elemento;

        /**
         * Constructor para un elemento la cual contiene como información una
         * frecuencia y un elemento.
         *
         * @param frecuencia
         * @param elemento
         */
        public Elemento(int frecuencia, Object elemento) {
            this.frecuencia = frecuencia;
            this.elemento = elemento;
        }

        /**
         * Regresa la frecuencia.
         *
         * @return Frecuencia actual.
         */
        public int getFrequencia() {
            return this.frecuencia;
        }

        /**
         * Regresa el elemento.
         *
         * @return Elemento actual.
         */
        public Object getElemento() {
            return this.elemento;
        }
    }
}
