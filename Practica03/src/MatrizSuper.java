
/**
 * Es una clase abstracta que implementa aquellos métodos que sean comunes a
 * amabas implementaciones y hacer que MatrizArreglo y MatrizPolinomio hereden
 * de esta.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public abstract class MatrizSuper implements Matriz {

    String tipomat;
    int renglones;
    int columnas;
    double omision;

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
     * Método que suma dos matrices
     *
     * @param mat es la matriz a sumar con este objeto
     * @return La matriz resultado de sumar este objeto con la matriz argumento
     */
    @Override
    public Matriz suma(Matriz mat) {
        MatrizArreglo a = new MatrizArreglo();
        int c = 0;
        double a1;
        if (!this.getTipo().equals(ManejadorMatrizXML.REGULAR) && this.getTipo().equals(mat.getTipo())) {
            a.creaMatriz(this.renglones, this.columnas, this.omision, this.getTipo());
            switch (this.getTipo()) {
                case ManejadorMatrizXML.SIMETRICA:
                    for (int i = 0; i < renglones; i++) {
                        c++;
                        for (int j = 0; j < c; j++) {
                            a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                            a.setEntrada(i, j, a1);
                        }
                    }
                    break;
                case ManejadorMatrizXML.TRISD:
                    for (int i = 0; i < renglones; i++) {
                        for (int j = columnas - 1; j >= c; j--) {
                            a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                            a.setEntrada(i, j, a1);
                        }
                        c++;
                    }
                    break;
                case ManejadorMatrizXML.TRISN:
                    for (int i = 0; i < renglones - 1; i++) {
                        c++;
                        for (int j = columnas - 1; j >= c; j--) {
                            a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                            a.setEntrada(i, j, a1);
                        }
                    }
                    break;
                case ManejadorMatrizXML.TRIID:
                    for (int i = 0; i < renglones; i++) {
                        c++;
                        for (int j = 0; j < c; j++) {
                            a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                            a.setEntrada(i, j, a1);
                        }
                    }
                    break;
                case ManejadorMatrizXML.TRIIN:
                    for (int i = 1; i < renglones; i++) {
                        c++;
                        for (int j = 0; j < c; j++) {
                            a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                            a.setEntrada(i, j, a1);
                        }
                    }
                    break;
            }
        } else {
            a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.REGULAR);
            for (int i = 0; i < renglones; i++) {
                for (int j = 0; j < columnas; j++) {
                    a1 = this.getEntrada(i, j) + mat.getEntrada(i, j);
                    a.setEntrada(i, j, a1);
                }
            }
        }
        return a;
    }

    /**
     * Método que multiplica dos matrices
     *
     * @param mat es la matriz a multiplicar con este objeto
     * @return La matriz resultado de multiplicar este objeto con la matriz
     * argumento
     */
    @Override
    public Matriz producto(Matriz mat) {
        MatrizArreglo a = new MatrizArreglo();
        a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.REGULAR);
        double a1 = 0;
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                for (int z = 0; z < columnas; z++) {
                    a1 += this.getEntrada(i, z) * mat.getEntrada(z, j);

                }
                a.setEntrada(i, j, a1);
                a1 = 0;
            }
        }
        return a;
    }

    /**
     * Método que multiplica una matriz por un escalar
     *
     * @param escalar es el escalar a multiplicar con este objeto
     * @return La matriz resultado de multiplicar este objeto con el escalar
     * argumento
     */
    @Override
    public Matriz productoEscalar(double escalar) {
        MatrizArreglo a = new MatrizArreglo();
        int c = 0;
        double a1;
        a.creaMatriz(this.renglones, this.columnas, this.omision, this.getTipo());
        switch (this.getTipo()) {
            case ManejadorMatrizXML.SIMETRICA:
                for (int i = 0; i < renglones; i++) {
                    c++;
                    for (int j = 0; j < c; j++) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.TRISD:
                for (int i = 0; i < renglones; i++) {
                    for (int j = columnas - 1; j >= c; j--) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                    c++;
                }
                break;
            case ManejadorMatrizXML.TRISN:
                for (int i = 0; i < renglones - 1; i++) {
                    c++;
                    for (int j = columnas - 1; j >= c; j--) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.TRIID:
                for (int i = 0; i < renglones; i++) {
                    c++;
                    for (int j = 0; j < c; j++) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.TRIIN:
                for (int i = 1; i < renglones; i++) {
                    c++;
                    for (int j = 0; j < c; j++) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.REGULAR:
                for (int i = 0; i < renglones; i++) {
                    for (int j = 0; j < columnas; j++) {
                        a1 = this.getEntrada(i, j) * escalar;
                        a.setEntrada(i, j, a1);
                    }
                }
        }
        return a;
    }

    /**
     * Método que traspone esta matriz
     *
     * @return La matriz resultado de trasponer esta matriz
     */
    @Override
    public Matriz trasponer() {
        MatrizArreglo a = new MatrizArreglo();
        int c = 0;
        double a1;
        switch (this.getTipo()) {
            case ManejadorMatrizXML.SIMETRICA:
                return this;
            case ManejadorMatrizXML.TRISD:
                a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.TRIID);
                for (int i = 0; i < renglones; i++) {
                    c++;
                    for (int j = 0; j < c; j++) {
                        a1 = this.getEntrada(j, i);
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.TRISN:
                a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.TRIIN);
                for (int i = 1; i < renglones; i++) {
                    c++;
                    for (int j = 0; j < c; j++) {
                        a1 = this.getEntrada(j, i);
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.TRIID:
                a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.TRISD);
                for (int i = 0; i < renglones; i++) {
                    for (int j = columnas - 1; j >= c; j--) {
                        a1 = this.getEntrada(j, i);
                        a.setEntrada(i, j, a1);
                    }
                    c++;
                }
                break;
            case ManejadorMatrizXML.TRIIN:
                a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.TRISN);
                for (int i = 0; i < renglones - 1; i++) {
                    c++;
                    for (int j = columnas - 1; j >= c; j--) {
                        a1 = this.getEntrada(j, i);
                        a.setEntrada(i, j, a1);
                    }
                }
                break;
            case ManejadorMatrizXML.REGULAR:
                a.creaMatriz(this.renglones, this.columnas, this.omision, ManejadorMatrizXML.REGULAR);
                for (int i = 0; i < renglones; i++) {
                    for (int j = 0; j < columnas; j++) {
                        a1 = this.getEntrada(i, j);
                        a.setEntrada(j, i, a1);
                    }
                }
        }
        return a;
    }

    /**
     * Despliega la matriz en la salida estándar.
     *
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void despliega() throws IndexOutOfBoundsException {
        int c = 0;
        if (renglones < 0 || columnas < 0 || renglones != columnas) {
            throw new IndexOutOfBoundsException("Los indices son incorrectos.");
        } else {
            switch (tipomat) {
                case ManejadorMatrizXML.SIMETRICA:
                    for (int i = 0; i < renglones; i++) {
                        c++;
                        for (int j = 0; j <= c; j++) {
                            if (j == c) {
                                System.out.println();
                            } else {
                                System.out.print(this.getEntrada(i, j));
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();
                    break;
                case ManejadorMatrizXML.TRISD:
                    for (int i = 0; i < renglones; i++) {
                        for (int j = columnas - 1; j >= c; j--) {
                            System.out.print(this.getEntrada(i, j));
                            System.out.print(" ");
                            if (j == c) {
                                System.out.println();
                            }
                        }
                        c++;
                    }
                    System.out.println();
                    break;
                case ManejadorMatrizXML.TRISN:
                    for (int i = 0; i < renglones - 1; i++) {
                        c++;
                        for (int j = columnas - 1; j >= c - 1; j--) {
                            if (j == c - 1) {
                                System.out.println();
                            } else {
                                System.out.print(this.getEntrada(i, j));
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();
                    break;
                case ManejadorMatrizXML.TRIID:
                    for (int i = 0; i < renglones; i++) {
                        c++;
                        for (int j = 0; j <= c; j++) {
                            if (j == c) {
                                System.out.println();
                            } else {
                                System.out.print(this.getEntrada(i, j));
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();
                    break;
                case ManejadorMatrizXML.TRIIN:
                    for (int i = 1; i < renglones; i++) {
                        c++;
                        for (int j = 0; j <= c; j++) {
                            if (j == c) {
                                System.out.println();
                            } else {
                                System.out.print(this.getEntrada(i, j));
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();
                    break;
                case ManejadorMatrizXML.REGULAR:
                    for (int i = 0; i < renglones; i++) {
                        for (int j = 0; j <= columnas; j++) {
                            if (j == columnas) {
                                System.out.println();
                            } else {
                                System.out.print(this.getEntrada(i, j));
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.println();
                    break;
            }
        }
    }
}
