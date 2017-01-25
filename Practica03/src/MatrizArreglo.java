
/**
 * En esta clase se implementan las matrices como un arreglo de arreglos. El
 * primer arreglo representará cada uno de los renglones de la matriz. Cada
 * elemento de este arreglo será un arreglo que represente a las columnas de la
 * matriz.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class MatrizArreglo extends MatrizSuper {

    double[][] Matriz;

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
        tipomat = tipo;
        Matriz = new double[ren][];
        omision = valdefault;
        renglones = ren;
        columnas = col;
        int j = 0;
        switch (tipo) {
            case ManejadorMatrizXML.SIMETRICA:
                for (int i = 1; i <= col; i++) {
                    Matriz[j] = new double[i];
                    j++;
                }
                break;
            case ManejadorMatrizXML.TRISD:
                for (int i = col; i > 0; i--) {
                    Matriz[j] = new double[i];
                    j++;
                }
                break;
            case ManejadorMatrizXML.TRISN:
                for (int i = col - 1; i > 0; i--) {
                    Matriz[j] = new double[i];
                    j++;
                }
                break;
            case ManejadorMatrizXML.TRIID:
                for (int i = 1; i <= col; i++) {
                    Matriz[j] = new double[i];
                    j++;
                }
                break;
            case ManejadorMatrizXML.TRIIN:
                for (int i = 1; i <= col - 1; i++) {
                    Matriz[j] = new double[i];
                    j++;
                }
                break;
            case ManejadorMatrizXML.REGULAR:
                for (int i = 0; i < col; i++) {
                    Matriz[j] = new double[col];
                    j++;
                }
                break;
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
        if (renglones < 0 || columnas < 0 || renglones != columnas) {
            throw new IndexOutOfBoundsException("Los indices son incorrectos");
        } else {
            switch (tipomat) {
                case ManejadorMatrizXML.SIMETRICA:
                    if (ren < col) {
                        throw new IndexOutOfBoundsException("Indices incorrectos.");
                    } else {
                        Matriz[ren][col] = valor;
                    }
                    break;
                case ManejadorMatrizXML.TRISD:
                    if (ren > col) {
                        throw new IndexOutOfBoundsException("Indices incorrectos.");
                    } else {
                        Matriz[ren][col - ren] = valor;
                    }
                    break;
                case ManejadorMatrizXML.TRISN:
                    if (ren > col || col == ren) {
                        throw new IndexOutOfBoundsException("Indices incorrectos.");
                    } else {
                        int w = 1 + ren;
                        Matriz[ren][col - w] = valor;
                    }
                    break;
                case ManejadorMatrizXML.TRIID:
                    if (ren < col) {
                        throw new IndexOutOfBoundsException("Indices incorrectos.");
                    } else {
                        Matriz[ren][col] = valor;
                    }
                    break;
                case ManejadorMatrizXML.TRIIN:
                    if (ren < col || ren == col) {
                        throw new IndexOutOfBoundsException("Indices incorrectos.");
                    } else {
                        Matriz[ren - 1][col] = valor;
                    }
                    break;

                case ManejadorMatrizXML.REGULAR:
                    Matriz[ren][col] = valor;
                    break;
            }

        }

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
    public double getEntrada(int ren, int col) throws IndexOutOfBoundsException {
        if (renglones < 0 || columnas < 0 || renglones != columnas) {
            throw new IndexOutOfBoundsException("Los indices son incorrectos.");
        } else {
            switch (tipomat) {
                case ManejadorMatrizXML.SIMETRICA:
                    if (ren < col) {
                        return Matriz[col][ren];
                    } else {
                        return Matriz[ren][col];
                    }
                case ManejadorMatrizXML.TRISD:
                    if (ren > col) {
                        return omision;
                    } else {
                        return Matriz[ren][col - ren];
                    }
                case ManejadorMatrizXML.TRISN:
                    if (ren > col || col == ren) {
                        return omision;
                    } else {
                        int w = 1 + ren;
                        return Matriz[ren][col - w];
                    }

                case ManejadorMatrizXML.TRIID:
                    if (ren < col) {
                        return omision;
                    } else {
                        return Matriz[ren][col];
                    }
                case ManejadorMatrizXML.TRIIN:
                    if (ren < col || ren == col) {
                        return omision;
                    } else {
                        return Matriz[ren - 1][col];
                    }
                case ManejadorMatrizXML.REGULAR:
                    return Matriz[ren][col];
            }
        }
        return 10000000;
    }
}
