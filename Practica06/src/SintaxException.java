
/**
 * Esta clase implementa un excepción nueva en caso de que la sintaxis este mal
 * formada.
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class SintaxException extends ArithmeticException {

    /**
     * Constructor por omisióm.
     */
    public SintaxException() {
    }

    /**
     * Constructor que recibe una cadena.
     * @param e String que evalua los detalles de la excepción.
     */
    public SintaxException(String e) {
        super(e);
    }
}
