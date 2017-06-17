/**
 *
 * @author lazaro
 */
package mx.unam.fciencias.EstDat;


public class ExcepcionAccesoIncorrecto extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionAccesoIncorrecto</code> without detail message.
     */
    public ExcepcionAccesoIncorrecto() {
    }


    /**
     * Constructs an instance of <code>ExcepcionAccesoIncorrecto</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExcepcionAccesoIncorrecto(String msg) {
        super(msg);
    }
}
