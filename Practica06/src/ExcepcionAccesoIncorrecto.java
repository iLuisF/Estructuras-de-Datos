
/**
 * Esta clase implementa un excepción nueva en caso de que se intente acceder a
 * un indice invalido.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class ExcepcionAccesoIncorrecto extends Exception {

    /**
     * Constructor sin parametros.
     */
    public ExcepcionAccesoIncorrecto() {
    }

    /**
     * Constructor que recibe una cadena con los detalles de la excepcióm.
     *
     * @param msg Detalles del mensaje.
     */
    public ExcepcionAccesoIncorrecto(String msg) {
        super(msg);
    }
}
