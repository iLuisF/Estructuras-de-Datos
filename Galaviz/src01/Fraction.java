/**
 * La siguiente interfaz representa el TDA Fracción. Su objetivo es definir a una fracción en términos de
 * las operaciones que soporta y no de la implementación de dichas operaciones. Algunas reglas que deben
 * cumplir las operaciones se mencionan en los comentarios.
 *
 * Nota: El denominador nunca puede ser 0. x/0 no cumple con el TDA Fracción.
 *
 * @author Armando Ballinas
 * @version 1.0
 */
public interface Fraction{

	/**
     * Obtiene el numerador de este objeto Fraction
     * @return El numerador de este objeto Fraction.
     */
	public int getNumerador();
	
	/**
     * Obtiene el denominador de este objeto Fraction
     * @return El denominador de este objeto Fraction.
     */
	public int getDenominador();
	/**
     * Suma el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a sumar a este objeto.
     * @return La fracción que resulta de la suma.
     */
    public Fraction add(Fraction fraccion);

    /**
     * Resta el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a restar a este objeto.
     * @return La fracción que resulta de la resta.
     */
    public Fraction substract(Fraction fraccion);

    /**
     * Multiplica el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a multiplicar este objeto por.
     * @return La fracción que resulta de la multiplicación.
     */
    public Fraction multiply(Fraction fraccion);

    /**
     * Divide el valor de la fracción actual entre fraccion.
     *
     * @param fraccion La fracción entre la que se debe dividir la actual.
     * @return La fracción que resulta de la división.
     * @throws ArithmeticException Esta excepción se lanza cuando fraccion
     * tiene el valor 0 y por tanto la división no está definida.
     */
    public Fraction divide(Fraction fraccion) throws ArithmeticException;

    /**
     * Compara fraccion con la fracción actual e indica si son iguales
     *
     * @param fraccion La fracción a comparar
     * @return true si las fracciones son iguales, False en otro caso.
     */
    public boolean isEqualTo(Fraction fraccion);

    /**
     * Compara fraccion con la fracción actual e indica si la actual es menor.
     *
     * @param fraccion La fracción a comparar
     * @return true si la fracción actual es menor que fraccion, False en otro caso.
     */
    public boolean isLessThan(Fraction fraccion);

    /**
     * Compara fraccion con la fracción actual e indica si la actual es mayor.
     *
     * @param fraccion La fracción a comparar
     * @return true si la fracción actual es mayor que fraccion, False en otro caso.
     */
    public boolean isGreaterThan(Fraction fraccion);

	/**
     * Imprime la fracción como una cadena de caracteres con formato.
     *
     * @return La representación estandar más simplificada de la fracción actual.
     * eg. si la fracción actual es 2/3=4/6=... se debe imprimir "2/3"
     */
    @Override
        public String toString();

    /**
     * Regresa el valor más cercano en double a la fracción actual.
     *
     * @return El valor más cercano en double a la fracción actual.
     * eg. si la fracción actual es 2/3=4/6=... se debe imprimir 0.6666666...
     */
    public double toDouble();

}
