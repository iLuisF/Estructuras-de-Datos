
import java.util.StringTokenizer;
import mx.unam.fciencias.EstDat.Pila;
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;

/**
 * Clase en la que se implementa una calculadora de expresiones aritméticas.
 * Estas expresiones estarán en notación posfija y para evaluarlas se usa una
 * pila.
 *
 * @author Flores Gonzalez Luis Brandon.
 * @version 1.0
 */
public class Calculadora {

    Pila<Object> pila = new Pila<>();
    String expresion;

    /**
     * Constructor que toma una expresión en notación infija y la convierte en
     * notacion postfija.
     *
     * @param expresion String de una expresión en notación postfija.
     */
    public Calculadora(String expresion) {
        try {
            this.expresion = convertirPosfija(expresion);
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println("Acceso incorrecto: " + ex);
        }
    }

    /**
     * Método para saber si una expresión esta balanceada, es decir, si sus
     * parentesis tienen una sintaxis adecuada.
     *
     * @return Regresa true si la pila esta balanceada, es decir, si tiene una
     * sintaxis adecuada con respecto a los parentesis.
     * @throws ExcepcionAccesoIncorrecto En caso de se requiera un indice
     * invalido.
     */
    public boolean esBalanceado() throws ExcepcionAccesoIncorrecto {
        int i = 0;
        while (i < expresion.length()) {//Recorremos la expresión carácter a carácter
            if (expresion.charAt(i) == '(') {
                pila.push("(");
            } //Si el paréntesis es de apertura apilamos siempre                               
            else if (expresion.charAt(i) == ')') {//Si el paréntesis es de cierre actuamos según el caso
                if (!pila.esVacia()) {
                    pila.pop();
                } //Si la pila no está vacía desapilamos
                else {
                    pila.push(")");
                    break;
                } //La pila no puede empezar con un cierre, apilamos y salimos
            }
            i++;
        }
        return pila.esVacia();
    }

    /**
     * Método que calcula la expresión aritmética en notación postfija.
     *
     * @return Regresa el resultado de la operación.
     * @throws ExcepcionAccesoIncorrecto En caso de que se intente ingresar a un
     * indice invalido.
     * @throws SintaxException En caso de que la sintasi este mal formada.
     */
    public double calcular() throws SintaxException, ExcepcionAccesoIncorrecto {
        if (!esBalanceado()) {
            throw new SintaxException("Por favor verifica la sintaxis.");
        } else {
            StringTokenizer tokenizer;
            String token;
            final String blanco = " \t";
            final String operadores = "+-*/%^";
            tokenizer = new StringTokenizer(expresion, blanco + operadores, true);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                if (blanco.contains(token))
                ; //Es un espacio en blanco, se ignora.
                else if (operadores.contains(token)) {
                    //Es operador, evalua lo de arriba de la pila.
                    double op1 = (Double) pila.pop();
                    double op2 = (Double) pila.pop();
                    double operacion = 0;
                    switch (token.charAt(0)) {
                        case '+':
                            operacion = op2 + op1;
                            break;
                        case '-':
                            operacion = op2 - op1;
                            break;
                        case '*':
                            operacion = op2 * op1;
                            break;
                        case '/':
                            operacion = op2 / op1;
                            break;
                        case '^':
                            operacion = Math.pow(op2, op1);
                            break;
                        case '%':
                            operacion = op2 % op1;
                            break;
                    }
                    pila.push(operacion);
                } else if (token.matches("[+-]?[0-9]+[.]?[0-9]*")) {
                    pila.push(new Double(token));
                } else {
                    System.out.println("Elemento no valido");
                }
            }
            return ((Double) pila.pop());
        }
    }

    /**
     * Convierte una expresión infija a postfija.
     *
     * @param infija Un string de la expresión en su forma infija.
     * @return Un string de la expresión en su forma postfija.
     * @throws ExcepcionAccesoIncorrecto En caso de que se trate de ingresar en
     * un indice de la pila invalido.
     */
    public static String convertirPosfija(String infija) throws ExcepcionAccesoIncorrecto {
        try {
            String postfij = "";
            Pila<String> pila = new Pila<>();
            StringTokenizer st = new StringTokenizer(infija, "()+-/%* ", true);
            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if (token.equals("")) {
                } else {
                    if (token.equals("(")) {
                        pila.push(token);
                    } else {
                        if (token.equals(")")) {
                            String op;
                            while (!(op = pila.pop()).equals("(")) {
                                postfij += " " + op;
                            }
                        } else {
                            if (token.equals("*") || token.equals("-") || token.equals("+")
                                    || token.equals("%") || token.equals("/")) {
                                int p = prioridad(token);
                                while (!pila.esVacia() && !pila.tope().equals("(")
                                        && prioridad(pila.tope()) >= p) {
                                    String op = pila.pop();
                                    postfij += " " + op;
                                }
                                pila.push(token);
                            } else {
                                Integer.parseInt(token);
                                postfij += " " + token;
                            }
                        }
                    }
                }
            }
            while (!pila.esVacia()) {
                String op = pila.pop();
                postfij += " " + op;
            }
            return postfij;
        } catch (SintaxException ex) {
            throw new SintaxException("Error: " + ex);
        }
    }

    /**
     * Regresa un entero que indica la prioridad dado un String. Para + o -
     * regresa 0, para * o / o % regresa 1,
     *
     * @param operator El operador de una operación.
     * @return Regresa la prioridad de un operador.
     * @throws SintaxException En caso de que el operador no este definido.
     */
    private static int prioridad(String operador) {
        switch (operador) {
            case "*":
            case "/":
            case "%":
                return 1;
            case "-":
            case "+":
                return 0;
            default:
                throw new SintaxException();
        }
    }
}
