
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;

/**
 * Clase principal que sirve de prueba para implementar los servios de
 * PilaDinamica, Calculadora y SintaxisException. Esta clase pide al usuario por
 * entrada estandar una expresión infija para calcular la operación.
 *
 * @author Flores González Luis Brandon
 * @version 1.0
 */
public class Main {

    /**
     * Metodo que recibe por entrada estandar una expresion/operación por
     * entrada estandar de forma infija la cual se pasara a forma postfija para
     * así poder calcularla con la implementación de una pila.
     *
     * @param args Solo recibe un argumento el cual es una expresión en notación
     * infija.
     * @throws ExcepcionAccesoIncorrecto En caso de intentar acceder a un indice
     * invalido.
     */
    public static void main(String[] args) throws ExcepcionAccesoIncorrecto {
        Calculadora operacion = new Calculadora(args[0]);
        try {
            System.out.println(operacion.calcular());
        } catch (SintaxException error) {
            System.out.println("Error de sintaxis: " + error);
        }
    }
}
