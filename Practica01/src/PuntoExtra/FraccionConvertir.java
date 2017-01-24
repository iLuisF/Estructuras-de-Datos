package fraccionconvertir;

import java.util.StringTokenizer;

/**
 * Esta clase permite convertir un numero decimal a fracción.
 *
 * @author iLuis
 * @version 1.0
 */
public class FraccionConvertir {

    private Integer numerador = 0;
    private Integer denominador = 1;
    private double fraccion;

    /**
     * Este constructor toma el numero decimal para poder construir una
     * fracción.
     *
     * @param fraccion es el numero decimal.
     */
    public FraccionConvertir(double fraccion) {
        this.fraccion = fraccion;
        String cadena = String.valueOf(fraccion);
        StringTokenizer cad = new StringTokenizer(cadena.replace("\\s", ""), ".");
        //Numero de tokens.
        int[] datos = new int[2];
        datos[0] = Integer.parseInt(cad.nextToken());
        datos[1] = Integer.parseInt(cad.nextToken());
        int num = datos[1];
        if (datos[0] != 0) {
            String nume = String.valueOf(datos[0]);
            String deno = String.valueOf(datos[1]);
            String result = nume.concat(deno);
            int result2 = Integer.parseInt(result);
            this.numerador = result2;
        } else {
            this.numerador = (num * (int) Math.pow(10, numeroCifras(num))) / 10;
        }
        this.denominador = (int) Math.pow(10, numeroCifras(num));
    }

    /**
     * Se usa para contar el numero de cifras de un numero.
     *
     * @param n es el numero al cual se le contara los caracteres.
     * @return regresa el numero de cifrar que tiene el numero.
     */
    public static int numeroCifras(int n) {
        if (n < 10) //caso base
        {
            return 1;
        } else {
            return 1 + numeroCifras(n / 10);
        }
    }

    /**
     * Inicio del programa.
     *
     * @param args la operación a realizar con un double,
     */
    public static void main(String[] args) {
        Double valor = Double.parseDouble(args[0]);
        FraccionConvertir a = new FraccionConvertir(valor);
        System.out.println(a.numerador + "/" + a.denominador);
    }

}
