
/**
 * Clase que crea una expeción que será lanzada cuando se intente construir una
 * fracción a partir de una cadena con un formato no válido. El formato válido
 * es: "blank [-] blank num blank / blank num blank". blank representa una
 * secuencia de cero o más espacios en blanco o tabuladores num representa una
 * secuencia de uno o más dígitos
 *
 * @author Flores González Luis
 * @version 1.0
 */
public class FractionFormatException extends Exception {

    /**
     * Constructor vacío de la clase.
     */
    public FractionFormatException() {;
    }

    /**
     * Constructor que recibe una cadena que será el mensaje desplegado por esta
     * excepción.
     */
    public FractionFormatException(String mensaje) {
        super(mensaje);
    }
}
