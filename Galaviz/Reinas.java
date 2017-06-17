/* ----------------------------------------------------------------------
 * Reinas.java
 * version 2.0
 * Copyright (C) 2004  José Galaviz Casas,
 * Facultad de Ciencias,
 * Universidad Nacional Autónoma de México, Mexico.
 *
 * Este programa es software libre; se puede redistribuir
 * y/o modificar en los términos establecidos por la
 * Licencia Pública General de GNU tal como fue publicada
 * por la Free Software Foundation en la versión 2 o
 * superior.
 *
 * Este programa es distribuido con la esperanza de que
 * resulte de utilidad, pero SIN GARANTÍA ALGUNA; de hecho
 * sin la garantía implícita de COMERCIALIZACIÓN o
 * ADECUACIÓN PARA PROPÓSITOS PARTICULARES. Véase la
 * Licencia Pública General de GNU para mayores detalles.
 *
 * Con este programa se debe haber recibido una copia de la
 * Licencia Pública General de GNU, de no ser así, visite el
 * siguiente URL:
 * http://www.gnu.org/licenses/gpl.html
 * o escriba a la Free Software Foundation Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * ----------------------------------------------------------------------
*/
/*
 *  Programa para encontrar una solución al problema
 *  de las 8 reinas (versión optimizada).
 *
 *  El problema consiste en colocar 8 reinas en un tablero de
 *  ajedrez de tal forma que no se ataquen mutuamente.
 *
 * @author Jos&eacute; Galaviz <jgc@fciencias.unam.mx>
 */
public class Reinas {
   /**
    * Tamaño del tablero, 8 por omisión.
    */
    static int TAM_TABLERO;

   /**
    * Tamaño del arreglo de diagonales (número de diagonales). Por
    * omisión 15.
    * T_DIAG = (2 * TAM_TABLERO) - 1;
    */
    static int T_DIAG;

    /**
     * Arreglo para guardar el estado de las diagonales con pendiente
     * positiva.
     * diagpos[i] = 0 si y sólo si no hay reinas en la i-ésima
     * diagonal.
     * Se inicializa con ceros.
     */
    static int[] diagpos;

    /**
     * Arreglo para guardar el estado de las diagonales con pendiente
     * negativa.
     * diagneg[i] = 0 si y sólo si no hay reinas en la i-ésima
     * diagonal.
     * Se inicializa con ceros.
     */
    static int[] diagneg;

    /**
     * Arreglo para guardar el estado de los renglones.
     * renglon[i] = 0 si no hay reinas en el renglón i-ésimo,
     * 1  en otro caso.
     * Se inicializa con ceros.
     */
    static int[] renglon;

    /**
     * Arreglo para guardar el estado del tablero.
     * tablero[i] = renglón donde va la reina de la i-ésima columna.
     * Se inicializa con -1.
     */
    static int[] tablero;

    /**
     * Despliega un mensaje de uso del programa.
     */
    public static void mensajeUso() {
        System.err.println("Programa para resolver el problema ");
        System.err.println("de las n reinas");
        System.err.println("\n se debe dar como parámetro de llamada");
        System.err.println("n, un entero positivo");
    }

    /**
     * Método recursivo para resolver el problema de las <i>n</i>
     * reinas haciendo "backtrack".
     * <b>Aviso:</b> Este método accede implícta y explícitamente variables
     * globales.
     * @param columna es el índice de la columna en la que
     * debe colocarse la siguiente reina.
     * @return <code>true</code> si se encontró solución,
     * <code>false</code> en otro caso.
     */
    public static boolean colocaReinas(int columna) {
        int renglon;
        boolean resuelto;
        // TAM_TABLERO es variable global
        if (columna == TAM_TABLERO) { // si ya no hay reinas por colocar
            resuelto = true;
        }
        else {
            resuelto = false;

            // Mientras no se terminen los renglones y no
            // se haya encontrado solución
            renglon = 0;
            while ((renglon < TAM_TABLERO) && !resuelto) {
                // se busca una propuesta de renglón para la reina
                if (atacada(renglon, columna)) {
                    renglon++;
                }
                else {
                    // se hace la propuesta
                    ponReina(renglon, columna);

                    // y se trata de encontrar posiciones seguras
                    // para las restantes
                    resuelto = colocaReinas(columna + 1);

                    // los retornos de la recursión llegan aquí
                    if (!resuelto) { // si no hubo solución
                        // se cambia la propuesta:
                        quitaReina(renglon, columna);
                        renglon++;
                    }
                }
            }
        }
        return resuelto;
    }

    /**
     * Determina si una posición del tablero es atacada o no.
     * <b>Aviso:</b> Este método accede (para lectura) a variables
     * globales.
     * @param ren índice del renglón del tablero.
     * @param col índice de la columna del tablero.
     * @return <code>true</code> si la posición dada como
     * entrada es atacada, <code>false</code> en otro caso.
     */
    public static boolean atacada(int ren, int col) {
        int resul;
        int i;
        int j;

        resul = 0;
        i = ren + col; // índice de la diagonal positiva
        j = col - ren + (TAM_TABLERO - 1); // índice de la diagonal negativa

        // se accede a las variables globales
        resul = diagpos[i] + diagneg[j] + renglon[ren];

        return (resul != 0); // si todos 0 entonces falso
    }

    /**
     * Coloca una reina en una posición del tablero.
     * <b>Aviso:</b> Este método accede (para escritura) a variables
     * globales.
     * @param ren renglón del tablero en el que se coloca la
     * reina.
     * @param col columna del tablero en la que se coloca la
     * reina.
     */
    public static void ponReina(int ren, int col) {
        int i;
        int j;

        i = ren + col; // índice de la diagonal positiva

        // donde se encuentra la casilla (ren, col)
        j = col - ren + (TAM_TABLERO - 1); // índice de la diagonal negativa

        // se accede a las variables globales
        diagpos[i] = 1; // se marcan las diagonales ocupadas
        diagneg[j] = 1;
        renglon[ren] = 1; // y el renglón
        tablero[col] = ren; // y se coloca la reina
    }

    /**
     * Quita una reina de una posición del tablero.
     * <b>Aviso:</b> Este método accede (para escritura) a variables
     * globales.
     * @param ren renglón del tablero en el que se quita la
     * reina.
     * @param col columna del tablero en la que se quita la
     * reina.
     */
    public static void quitaReina(int ren, int col) {
        int i;
        int j;

        i = ren + col;
        j = col - ren + (TAM_TABLERO - 1);

        // se accede a las variables globales
        diagpos[i] = 0; // se marcan las diagonales desocupadas
        diagneg[j] = 0;
        renglon[ren] = 0; // al igual que el renglón
        tablero[col] = -1; // y se quita la reina
    }

    /**
     * Despliega el tablero en pantalla.
     * <b>Aviso:</b> Este método accede (para lectura) a variables
     * globales.
     */
    public static void despliegaTablero() {
        int i;
        int j;

        // tablero auxiliar donde mapearemos los resultados
        int[][] tabaux = new int[TAM_TABLERO][TAM_TABLERO];

        // inicializamos a cero el tablero
        for (i = 0; i < TAM_TABLERO; i++) {
            for (j = 0; j < TAM_TABLERO; j++)
                tabaux[i][j] = 0;
        }

        for (i = 0; i < TAM_TABLERO; i++) { // recorremos el arreglo

            if (tablero[i] != -1) { // si hay reina
                tabaux[tablero[i]][i] = 1; // marcamos en el
            }

            // auxiliar que la casilla
            // está ocupada
        }

        for (i = 0; i < TAM_TABLERO; i++) { // por cada renglon y

            for (j = 0; j < TAM_TABLERO; j++) { // cada columna del auxiliar

                if (tabaux[i][j] == 1) { // si hay reina
                    System.err.print("R "); // ponemos "R"
                } else { // si no
                    System.err.print(". "); // ponemos "."
                }
            }

            System.err.println(" "); // al fin de cada renglón
        }
    }

    public static void main(String[] arg) {
        int i;
        int j;
        long inicio;
        long fin;
        long tiempo;
        boolean sol = false;

        if (arg.length != 1) {
            mensajeUso();
        } else {
            try {
                TAM_TABLERO = Integer.parseInt(arg[0]);
                T_DIAG = (2 * TAM_TABLERO) - 1;
                diagpos = new int[T_DIAG];
                diagneg = new int[T_DIAG];
                tablero = new int[TAM_TABLERO];
                renglon = new int[TAM_TABLERO];

                // se inicializa a -1 cada entrada del tablero
                for (i = 0; i < TAM_TABLERO; tablero[i++] = -1)
                    ;

                //  renglon y diagonales en ceros
                for (i = 0; i < TAM_TABLERO; renglon[i++] = 0)
                    ;

                for (i = 0; i < T_DIAG; i++)
                    diagneg[i] = diagpos[i] = 0;

                inicio = System.currentTimeMillis();
                sol = colocaReinas(0);
                if (sol) {
                   System.err.println("Resuelto");
                }
                fin = System.currentTimeMillis();
                tiempo = fin - inicio;

                despliegaTablero();
                System.err.println("\nReinas\tTiempo (ms)");
                System.err.println(" " + TAM_TABLERO + "\t" + tiempo);
            }
            catch (Exception ex) {
                mensajeUso();
            }
        }
    }
} // Reinas.java termina aquí
