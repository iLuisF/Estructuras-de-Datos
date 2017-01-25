
import mx.unam.fciencias.EstDat.ColaList;
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;

/**
 * Extiende la clase ColaList para que se vuelva una cola concurrente mediante
 * el uso de métodos sincronizados.
 *
 * @author Flores González Luis Brandon.
 * @param <T> Tipo de dato.
 */
public class ColaConcurrente<T> extends ColaList<T> {

    /**
     * Construye una cola vacia
     */
    public ColaConcurrente() {
        super();
    }

    /**
     * Determina si una cola es vacía o no. Se usa synchronized en este método.
     *
     * @return <code>true</code> Si la cola es vacía, <code>false</code> en otro
     * caso.
     */
    @Override
    public synchronized boolean esVacia() {
        return super.esVacia();
    }

    /**
     * Determina el número de elementos elmacenados en la cola. Se usa
     * synchronized en este método.
     *
     * @return número de celdas en la cola
     */
    @Override
    public synchronized int longitud() {
        return super.longitud();
    }

    /**
     * Regresa el valor almacenado en el frente de la cola. Se usa synchronized
     * en este método.
     *
     * @return El valor del objeto en el frente de la cola.
     * @throws ExcepcionAccesoIncorrecto en caso de pretender obtener el
     * siguiente elemento de una cola vacía
     */
    @Override
    public synchronized T siguiente() throws ExcepcionAccesoIncorrecto {
        return super.siguiente();
    }

    /**
     * Introduce un nuevo elemento en la cola. Se usa synchronized en este
     * método.
     *
     * @param nvoatras Nuevo elemento en la cola.
     */
    @Override
    public synchronized void mete(T nvoatras) {
        super.mete(nvoatras);
    }

    /**
     * Elimina el elemento que fue introducido más tempranamente a la cola. Se
     * usa synchronized en este método.
     *
     * @return una referencia al elemento al frente de la cola.
     * @throws ExcepcionAccesoIncorrecto en caso de pretender sacar de una cola
     * vacía
     */
    @Override
    public synchronized T saca() throws ExcepcionAccesoIncorrecto {
        return super.saca();
    }
}
