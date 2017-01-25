
/**
 * En esta clase se almacenaran todos los elementos en un arreglo contiguo. Y
 * utilizará un polinomio de direccionamiento para indexar los elementos.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class MatrizPolinomio extends MatrizSuper {

    double[] mat;
    int tipocod;

    /**
     * Crea una matriz de dimensiones y tipo dados.
     *
     * @param ren número de renglones en la matriz.
     * @param col número de columnas en la matriz.
     * @param valdefault valor en el que se establecen las entradas de la matriz
     * no establecidas explícitamente con el método <code>setEntrada</code>.
     * @param tipo es una cadena que identifica el tipo de matriz. Los valores
     * posibles estan especificados en la clase <code>ManejadorMatrizXML</code>:
     * <UL>
     * <LI><code>ManejadorMatrizXML.TRISD</code>: Triangular superior con
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRISN</code>: Triangular superior sin
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRIID</code>: Triangular inferior con
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRIIN</code>: Triangular inferior sin
     * diagonal.
     * <LI><code>ManejadorMatrizXML.SIMETRICA</code>: Simétrica
     * <LI><code>ManejadorMatrizXML.REGULAR</code>: Regular (cualquiera)
     * </UL>
     */
    @Override
    public void creaMatriz(int ren, int col, double valdefault, String tipo) {
        switch (tipo) {
            case ManejadorMatrizXML.REGULAR:
                mat = new double[ren * col];
                break;
            case ManejadorMatrizXML.TRIID:
            case ManejadorMatrizXML.TRISD:
            case ManejadorMatrizXML.SIMETRICA:
                mat = new double[(ren * (ren + 1)) / 2];
                break;
            case ManejadorMatrizXML.TRIIN:
            case ManejadorMatrizXML.TRISN:
                mat = new double[(ren * (ren - 1)) / 2];
                break;
            default:
                mat = null;
                break;
        }
        inicializa(valdefault);
        tipomat = new String(tipo);
        estableceTipo(tipo);
        columnas = col;
        renglones = ren;
        omision = valdefault;
    }

    /**
     * Para inicializar la matriz a su valor default
     *
     * @param valor es el valor default
     */
    private void inicializa(double valor) {
        if (mat != null) {
            for (int i = 0; i < mat.length; mat[i++] = valor) {
            }
        }
    }

    /**
     * Establece un código para el tipo de matriz.
     *
     * @param tipo es una cadena que identifica el tipo de matriz. Los valores
     * posibles estan especificados en la clase <code>ManejadorMatrizXML</code>:
     * <UL>
     * <LI><code>ManejadorMatrizXML.TRISD</code>: Triangular superior con
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRISN</code>: Triangular superior sin
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRIID</code>: Triangular inferior con
     * diagonal.
     * <LI><code>ManejadorMatrizXML.TRIIN</code>: Triangular inferior sin
     * diagonal.
     * <LI><code>ManejadorMatrizXML.SIMETRICA</code>: Simétrica
     * <LI><code>ManejadorMatrizXML.REGULAR</code>: Regular (cualquiera)
     * </UL>
     */
    private void estableceTipo(String tipo) {
        switch (tipo) {
            case ManejadorMatrizXML.TRISD:
                tipocod = 1;
                break;
            case ManejadorMatrizXML.TRISN:
                tipocod = 2;
                break;
            case ManejadorMatrizXML.TRIID:
                tipocod = 3;
                break;
            case ManejadorMatrizXML.TRIIN:
                tipocod = 4;
                break;
            case ManejadorMatrizXML.SIMETRICA:
                tipocod = 5;
                break;
            case ManejadorMatrizXML.REGULAR:
                tipocod = 6;
                break;
        }
    }

    /**
     * Establece si es un indice correcto.
     *
     * @param ren índice del renglón de la entrada a establecer.
     * @param col índice de la columna de la entrada a establecer.
     */
    private boolean indiceValido(int ren, int col) {
        if ((ren >= 0) && (ren < renglones) && (col >= 0)
                && (col < columnas)) {
            switch (tipocod) {
                case 1:
                    return (ren <= col); // sd

                case 2:
                    return (ren < col); // sn

                case 3:
                    return (ren >= col); // id

                case 4:
                    return (ren > col); // in

                case 5:
                default:
                    return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Establece la entrada indicada de la matriz.
     *
     * @param ren índice del renglón de la entrada a establecer.
     * @param col índice de la columna de la entrada a establecer.
     * @param valor valor a colocar en la entrada.
     * @throws IndexOutOfBoundsException si los valores de índice de renglón o
     * columna son erróneos de acuerdo con las dimensiones establecidas para la
     * matriz y/o de acuerdo con el tipo de matriz especificado (por ejemplo, si
     * el tipo es triangular inferior sin diagonal entonces debe cumplirse que
     * <br><code>ren</code>> > <code>col</code>).<br> Si la matriz es simétrica
     * se tratará como triangular inferior con diagonal.
     */
    @Override
    public void setEntrada(int ren, int col, double valor)
            throws IndexOutOfBoundsException {
        if (indiceValido(ren, col)) {
            switch (tipocod) {
                case 1:
                    mat[((col * (col + 1)) / 2) + ren] = valor;
                    break;
                case 2:
                    mat[((col * (col - 1)) / 2) + ren] = valor;
                    break;
                case 3:
                    mat[((ren * (ren + 1)) / 2) + col] = valor;
                    break;
                case 4:
                    mat[((ren * (ren - 1)) / 2) + col] = valor;
                    break;
                case 5:
                    if (ren >= col) {
                        mat[((ren * (ren + 1)) / 2) + col] = valor;
                    } else {
                        mat[((col * (col + 1)) / 2) + ren] = valor;
                    }
                    break;
                default:
                    mat[(ren * columnas) + col] = valor;
            }
        } else {
            throw new IndexOutOfBoundsException("Índice incorrecto.");
        }
    }

    /**
     * Regresa la cadena con el tipo de matriz. Véase el método constructor para
     * los posibles valores.
     *
     * @return una cadena con el tipo de matriz de acuerdo con la clase
     * <code>ManejadorMatrizXML</code>.
     */
    @Override
    public String getTipo() {
        return tipomat;
    }

    /**
     * Regresa el valor por omisión, aquel que se encuentra en las entradas no
     * establecidas explícitamente.
     *
     * @return el valor por omisión.
     */
    @Override
    public double getDefault() {
        return omision;
    }

    /**
     * Regresa el número de renglones en la matriz.
     *
     * @return un entero no negativo con el número de renglones de la matriz.
     */
    @Override
    public int getRenglones() {
        return renglones;
    }

    /**
     * Regresa el número de columnas en la matriz.
     *
     * @return un entero no negativo con el número de columnas de la matriz.
     */
    @Override
    public int getColumnas() {
        return columnas;
    }

    /**
     * Regresa el valor almacenado en una entrada de la matriz.
     *
     * @param ren es el índice del renglón de la entrada requerida.
     * @param col es el índice de la columna de la entrada requerida.
     * @return el valor almacenado en la entrada especificada.
     * @throws IndexOutOfBoundsException si los índices especificados son
     * incorrectos. En este caso sólo se considera que no rebasen las
     * dimansiones especificadas para la matriz.
     */
    @Override
    public double getEntrada(int ren, int col)
            throws IndexOutOfBoundsException {
        if (indiceValido(ren, col)) {
            switch (tipocod) {
                case 1:
                    return mat[((col * (col + 1)) / 2) + ren];
                case 2:
                    return mat[((col * (col - 1)) / 2) + ren];
                case 3:
                    return mat[((ren * (ren + 1)) / 2) + col];
                case 4:
                    return mat[((ren * (ren - 1)) / 2) + col];
                case 5:
                    return (ren >= col) ? mat[((ren * (ren + 1)) / 2) + col]
                            : mat[((col * (col + 1)) / 2) + ren];
                default:
                    return mat[(ren * columnas) + col];
            }
        } else if ((ren >= 0) && (ren < renglones) && (col >= 0)
                && (col < columnas)) {
            return omision;
        } else {
            throw new IndexOutOfBoundsException("Índice incorrecto.");
        }
    }
}
