
import java.util.StringTokenizer;

/**
 * Esta clase tiene la finalidad de implementar la interface Fraction.
 *
 * @author Flores Gonzalez Luis
 * @version 1.0
 */
public class MyFraction implements Fraction {

    private Integer numerador = new Integer(0);
    private Integer denominador = new Integer(1);

    /**
     * Este constructor toma el numerador y el denominador para construir una
     * fraccion(objeto) con ellos y lo inicializa.
     *
     * @param numerador es el numerador de la fracciòn.
     * @param denominador es el denominador de la fracciòn.
     * @exception IlegalDenominatorException si el denominador es cero.
     */
    public MyFraction(Integer numerador, Integer denominador)
            throws IlegalDenominatorException {
        // Este codigo inicializa las variables con la informaciòn.
        this.numerador = numerador;
        this.denominador = denominador;
        if (denominador.equals(0)) {
            throw new IlegalDenominatorException("No se puede tener 0 como denominador.");
        }
    }

    /**
     * Este constructor toma el numerador y denominador.
     *
     * @param numerador es el numero con el que construye el numerador y
     * denominador.
     */
    public MyFraction(Integer numerador) {
        this.numerador = numerador;
        this.denominador = 1;
    }

    /**
     * Este constructor toma una cadena de la forma "num/den" y la convierte a
     * una fracción.
     *
     * @param cadena es la cadena con la que construye el numerador y
     * denominador.
     * @throws FractionFormatException si el formato que se ingresa es
     * incorrecto.
     */
    public MyFraction(String cadena) throws FractionFormatException {
        StringTokenizer cad = new StringTokenizer(cadena.replace("\\s", ""), "/");
        int[] datos = new int[2];
        String nume = cad.nextToken();
        datos[0] = Integer.parseInt(nume);
        String den = cad.nextToken();
        datos[1] = Integer.parseInt(den);
        String nueva = nume.concat(den);
        int nums = cadena.length() - nueva.length();//Numero de la cadena original y la modificada.
        char result = den.charAt(0);
        if (result == '+' || result == '-' || nums == 2) {
            throw new FractionFormatException("El formato de la fracción es incorrecto.");
        } else {
            this.numerador = datos[0];
            this.denominador = datos[1];
        }
    }

    /**
     * Se usa para mostrar la información almacenada en los datos de las
     * variables de cada objeto.
     *
     * @return una fracción con el formato: numerador / denominador
     */
    @Override
    public String toString() {
        // Crea una variable local.
        String fractionString = numerador + " / " + denominador;
        // Regresa la variable local.
        return fractionString;
    }

    /**
     * Suma el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a sumar a este objeto.
     * @return La fracción que resulta de la suma.
     */
    @Override
    public Fraction add(Fraction fraccion) {
        // Obtiene el numerador y denominador de fraccion.
        Integer numerador2 = fraccion.getNumerador();
        Integer denominador2 = fraccion.getDenominador();
        // Operaciòn para conseguir el numerador.
        Integer numerador3 = numerador * denominador2 + numerador2 * denominador;
        // Multiplica ambos denominadores.
        Integer denominador3 = denominador * denominador2;
        // Crea (instancia) la nueva fraccion y reduce a su minima expresiòn.
        Fraction result = new MyFraction(numerador3, denominador3);
        result.reduceFraccion();
        return result;
    }

    /**
     * Resta el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a restar a este objeto.
     * @return La fracción que resulta de la resta.
     */
    @Override
    public Fraction substract(Fraction fraccion) {
        // Obtiene el numerador y denominador de fraccion.
        Integer numerador2 = fraccion.getNumerador();
        Integer denominador2 = fraccion.getDenominador();
        // Operaciòn para conseguir el numerador.
        Integer numerador3 = numerador * denominador2 - numerador2 * denominador;
        // Multiplica ambos denominadores.
        Integer denominador3 = denominador * denominador2;
        // Crea (instancia) la nueva fraccion y reduce a su minima expresiòn.
        Fraction result = new MyFraction(numerador3, denominador3);
        result.reduceFraccion();
        return result;
    }

    /**
     * Multiplica el valor de fraccion a la fracción actual.
     *
     * @param fraccion La fracción a multiplicar este objeto por.
     * @return La fracción que resulta de la multiplicación.
     */
    @Override
    public Fraction multiply(Fraction fraccion) {
        // Obtiene el numerador y denominador de fraccion.
        Integer numerador2 = fraccion.getNumerador();
        Integer denominador2 = fraccion.getDenominador();
        // Multiplica los numeradores.
        Integer numerador3 = numerador * numerador2;
        // Multiplica los denominadores.
        Integer denominador3 = denominador * denominador2;
        // Crea (instancia) la nueva fraccion y reduce a su minima expresión.
        Fraction result = new MyFraction(numerador3, denominador3);
        result.reduceFraccion();
        return result;
    }

    /**
     * Divide el valor de la fracción actual entre fraccion.
     *
     * @param fraccion La fracción entre la que se debe dividir la actual.
     * @return La fracción que resulta de la división.
     * @throws ArithmeticException Esta excepción se lanza cuando fraccion tiene
     * el valor 0 y por tanto la división no está definida.
     */
    @Override
    public Fraction divide(Fraction fraccion) throws ArithmeticException {
        // Obtiene el numerador y denominador de fraccion.
        Integer numerador2 = fraccion.getNumerador();
        Integer denominador2 = fraccion.getDenominador();
        // Multiplica numerador1 * denominador2.
        Integer numerador3 = numerador * denominador2;
        // Multiplica denominador1 * numerador2.
        Integer denominador3 = denominador * numerador2;
        // Crea (instancia) la nueva fraccion y reduce a su minima expresiòn.
        Fraction result = new MyFraction(numerador3, denominador3);
        result.reduceFraccion();
        return result;
    }

    /**
     * Compara fraccion con la fracción actual e indica si son iguales
     *
     * @param fraccion La fracción a comparar
     * @return true si las fracciones son iguales, False en otro caso.
     */
    @Override
    public boolean isEqualTo(Fraction fraccion) {
        double decimal2 = ((double) fraccion.getNumerador() / (double) fraccion.getDenominador());
        if (decimal2 == toDouble()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compara fraccion con la fracción actual e indica si la actual es menor.
     *
     * @param fraccion La fracción a comparar
     * @return true si la fracción actual es menor que fraccion, False en otro
     * caso.
     */
    @Override
    public boolean isLessThan(Fraction fraccion) {
        double decimal2 = ((double) fraccion.getNumerador() / (double) fraccion.getDenominador());
        if (decimal2 > toDouble()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compara fraccion con la fracción actual e indica si la actual es mayor.
     *
     * @param fraccion La fracción a comparar
     * @return true si la fracción actual es mayor que fraccion, False en otro
     * caso.
     */
    @Override
    public boolean isGreaterThan(Fraction fraccion) {
        double decimal2 = ((double) fraccion.getNumerador() / (double) fraccion.getDenominador());
        if (decimal2 < toDouble()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Regresa el valor más cercano en double a la fracción actual.
     *
     * @return El valor más cercano en double a la fracción actual. eg. si la
     * fracción actual es 2/3=4/6=... se debe imprimir 0.6666666...
     */
    @Override
    public double toDouble() {
        Double result = ((double) getNumerador() / (double) getDenominador());
        return result;
    }

    /**
     * Este es un metodo para obtener un dato.
     *
     * @return el numerador.
     */
    @Override
    public Integer getNumerador() {
        // Regresa el numerador
        return numerador;
    }

    /**
     * Este es un metodo para obtener un dato.
     *
     * @return el denominador.
     */
    @Override
    public Integer getDenominador() {
        // Regresa el denominador.
        return denominador;
    }

    /**
     * Este es un metodo para asignar un dato.
     *
     * @param numerador es el numerador.
     */
    @Override
    public void setNumerador(Integer numerador) {
        // Asigna el numerador.
        this.numerador = numerador;
    }

    /**
     * Este es un metodo para asignar un dato.
     *
     * @param denominador es el denominador.
     * @exception IlegalDenominatorException si el denominador es cero.
     */
    @Override
    public void setDenominador(Integer denominador) throws IlegalDenominatorException {
        // Asigna el denominador.
        this.denominador = denominador;
        if (denominador == 0) {
            throw new IlegalDenominatorException("No se puede tener 0 como denominador.");
        }
    }

    /**
     * Este metodo reduce una fracciòn a su minima expresiòn.
     */
    @Override
    public void reduceFraccion() {
        // Determina el maximo comun divisor.
        Integer mcd = this.maximoComunDivisor(numerador, denominador);
        // Si mcd es negativo cambia a positivo.
        if (mcd < 0) {
            mcd = -mcd;
        }
        // Divide mcd en el nuerador y denominador.
        numerador = numerador / mcd;
        denominador = denominador / mcd;
    }

    /**
     * Se usa el algoritmo de Euclides para determinar el maximo comun divisor
     * de dos numeros.
     *
     * @param a es el primer numero.
     * @param b es el segundo numero.
     * @return el maximo comun divisor de los dos numeros.
     */
    private Integer maximoComunDivisor(Integer a, Integer b) {
        // % Es el modulo, es decir, el residuo de una divisiòn.
        // Caso base.
        if ((a % b) == 0) {
            return b;
        } // Caso recursivo.
        else {
            return maximoComunDivisor(b, a % b);
        }
    }

}
