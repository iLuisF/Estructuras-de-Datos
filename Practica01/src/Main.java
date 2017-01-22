
/**
 * Pregunta al usuario el numerador y denominador de dos fracciones así como la
 * operación a realizar.
 *
 * @author Flores González Luis
 * @version 1.0
 */
public class Main {

    /**
     * Inicio del programa.
     *
     * @param args la operación a realizar(+, -, x, /, <, >, =), primera
     * fraccion de la forma "num/den", segunda fraccion de la forma "num/den".
     * @throws FractionFormatException si el formato es incorrecto.
     */
    public static void main(String[] args) throws FractionFormatException{

        // Crea dos objetos de fracciones por defecto.
        // Si una clase implementa una interfaz,
        // Entonces puede utilizar la interfaz para declarar una variable.
        // Syntax: NombreInterfaz NombreVariable = new Constructor(parametros);
        Fraction fraccion1 = new MyFraction(args[1]);
        Fraction fraccion2 = new MyFraction(args[2]);
        Fraction fraccion3 = new MyFraction(0, 1);
        try {
            switch (args[0]) {
                case "+":
                    System.out.println("Escogiste una suma.");
                    // Calcula la suma.
                    fraccion3 = fraccion1.add(fraccion2);
                    System.out.println(fraccion1.toString() + " + " + fraccion2.toString() + " = "
                            + fraccion3.toString());
                    break;
                case "-":
                    System.out.println("Escogiste una resta.");
                    // Calcula la resta.
                    fraccion3 = fraccion1.substract(fraccion2);
                    System.out.println("\n" + fraccion1.toString() + " - " + fraccion2.toString() + " = "
                            + fraccion3.toString());
                    break;
                case "x":
                    System.out.println("Escogiste una multiplicación.");
                    // Calcula la multiplicaciòn.
                    fraccion3 = fraccion1.multiply(fraccion2);
                    System.out.println("\n" + fraccion1.toString() + " x " + fraccion2.toString() + " = "
                            + fraccion3.toString());
                    break;
                case "/":
                    System.out.println("Escogiste una división.");
                    // Calcula la divisiòn.
                    fraccion3 = fraccion1.divide(fraccion2);
                    System.out.println("\n" + fraccion1.toString() + " / " + fraccion2.toString() + " = "
                            + fraccion3.toString());
                    break;
                case "=":
                    System.out.println("Escogiste comparación." + fraccion1.isEqualTo(fraccion2));
                    break;
                case "<":
                    System.out.println("Escogiste comparar la fracción para saber si es menor.");
                    System.out.println("\n" + fraccion1.toString() + " < " + fraccion2.toString() + " = "
                            + fraccion1.isLessThan(fraccion2));
                    break;

                case ">":
                    System.out.println("Escogiste comparar la fracción para saber si es mas grande.");
                    System.out.println("\n" + fraccion1.toString() + " > " + fraccion2.toString() + " = "
                            + fraccion1.isGreaterThan(fraccion2));
                    break;
                default:
                    System.out.println("El formato no es el indicado.");
            }
        } // Si el usuario ingresa 0 como denominador.
        catch (IlegalDenominatorException excepcion2) {
            String mensaje = excepcion2.toString();
            System.out.println(mensaje);
        }
    }
}
