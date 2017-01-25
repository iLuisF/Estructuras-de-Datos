
/**
 * Esta clase representa un codigo de Huffman, con la información
 * correspondiente al caracter, frecuencia y su codificación.
 *
 * @author Flores González Luis Brandon.
 * @version 2.0
 */
public class Codigo {

    private final Character caracter;
    private final int frecuencia;
    private final String codificacion;

    /**
     * Constructor para un codigo de Huffman que contiene un caracter, una
     * frecuencia y la codificación.
     *
     * @param caracter Caracter actual.
     * @param frecuencia Frecuencia actual.
     * @param codificacion Codificacion actual.
     */
    public Codigo(Character caracter, int frecuencia, String codificacion) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.codificacion = codificacion;
    }

    /**
     * Regresa el caracter actual.
     *
     * @return Caracter actual.
     */
    public Character getCaracter() {
        return this.caracter;
    }

    /**
     * Regresa la frecuencia actual.
     *
     * @return Frecuencia actual.
     */
    public int getFrecuencia() {
        return this.frecuencia;
    }

    /**
     * Regresa la codificación actual.
     *
     * @return Codificacion actual.
     */
    public String getCodificacion() {
        return this.codificacion;
    }

}
