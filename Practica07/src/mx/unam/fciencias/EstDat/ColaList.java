package mx.unam.fciencias.EstDat;

/**
 * Se implementa la estructura de datos lineal Cola, debido a la forma de
 * almacenar los datos y la manera de implementar las operaciones se implementa
 * mediante otra estructura de datos, lista doblemente ligada.
 *
 * @author Flores González Luis Brandon.
 * @param <T> Tipo de dato.
 */
public class ColaList<T> implements Cola<T> {

    private final Lista<T> lista;

    /**
     * Construye una cola vacia. El mecanismo de representacion es por medio de
     * una estructura de una lista doblemente ligada.
     */
    public ColaList() {
        lista = new ListaDoblementeLigada<>();
    }

    /**
     * Determina si una cola es vacía o no.
     *
     * @return <code>true</code> Si la cola es vacía, <code>false</code> en otro
     * caso.
     */
    @Override
    public boolean esVacia() {
        return lista.esVacia();
    }

    /**
     * Determina el número de elementos elmacenados en la cola.
     *
     * @return número de celdas en la cola
     */
    @Override
    public int longitud() {
        return lista.longitud();
    }

    /**
     * Regresa el valor almacenado en el frente de la cola.
     *
     * @return El valor del objeto en el frente de la cola.
     * @throws ExcepcionAccesoIncorrecto en caso de pretender obtener el
     * siguiente elemento de una cola vacía
     */
    @Override
    public T siguiente() throws ExcepcionAccesoIncorrecto {
        if (lista.esVacia()) {
            throw new ExcepcionAccesoIncorrecto("Cola vacia.");
        } else {
            return lista.getPrimero();
        }
    }

    /**
     * Introduce un nuevo elemento en la cola.
     *
     * @param nvoatras Nuevo elemento en la cola.
     */
    @Override
    public void mete(T nvoatras) {
        lista.insertaUltimo(nvoatras);
    }

    /**
     * Elimina el elemento que fue introducido más tempranamente a la cola.
     *
     * @return una referencia al elemento al frente de la cola.
     * @throws ExcepcionAccesoIncorrecto en caso de pretender sacar de una cola
     * vacía
     */
    @Override
    public T saca() throws ExcepcionAccesoIncorrecto {
        T frentecola;
        if (lista.esVacia()) {
            throw new ExcepcionAccesoIncorrecto("Cola vacia.");
        } else {
            frentecola = lista.getPrimero();
            lista.eliminaPrimero();
            return frentecola;
        }
    }
}
